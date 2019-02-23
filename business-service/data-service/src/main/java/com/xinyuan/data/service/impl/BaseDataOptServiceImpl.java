package com.xinyuan.data.service.impl;

import com.xinyuan.base.common.entity.enums.Status;
import com.xinyuan.base.common.service.ParamCondition;
import com.xinyuan.base.common.service.SelectParam;
import com.xinyuan.base.common.util.DateFormatUtils;
import com.xinyuan.base.common.web.Param;
import com.xinyuan.data.entity.KeyDepot;
import com.xinyuan.data.model.BaseDataRemoveRequest;
import com.xinyuan.data.model.BaseDataSaveRequest;
import com.xinyuan.data.service.BaseDataOptService;
import com.xinyuan.data.service.KeyDepotService;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 基础数据操作服务
 *
 * @author liang
 */
@Service
public class BaseDataOptServiceImpl implements BaseDataOptService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private KeyDepotService keyDepotService;

    /**
     * 增加
     * 增加的同时需要保存起效时间 用户名 密码
     */
    @Transactional
    public synchronized void save(BaseDataSaveRequest baseDataSaveRequest) {
        //todo 获得登录用户
        //serialId是单位ID后面加上固定数
        //
        //通过表名和单位ID查询到最新的一条值是多少了
        String tableName = baseDataSaveRequest.getTableName();
        String companyId = baseDataSaveRequest.getCompanyId();

        List<SelectParam> selectParams = new ArrayList<>();
        selectParams.add(new SelectParam("tableName", tableName, ParamCondition.DB_EQUAL));
        selectParams.add(new SelectParam("prefix", companyId, ParamCondition.DB_EQUAL));
        KeyDepot keyDepot = keyDepotService.getByCondition(selectParams);
        Long value = keyDepot.getValue();
        value = value + 1;
        keyDepot.setValue(value);
        List<Param> paramList = baseDataSaveRequest.getParamList();
        for (Param param : paramList) {
            //todo 通过表和键获得栏目信息
            StringBuffer sql = new StringBuffer(" INSERT INTO ");
            sql.append("\"").append(tableName).append("\"");
            sql.append("(serial_id,data_value,column_name,start_time,operator,c_id,status,show_flag");
            if (baseDataSaveRequest.isMultiple()) {
                sql.append(",pk_id");
            }
            sql.append(")");
            sql.append("values");
            sql.append("(:serial_id,:data_value,:column_name,:start_time,:operator,:c_id,:status,:show_flag");
            if (baseDataSaveRequest.isMultiple()) {
                sql.append(",:pk_id");
            }
            sql.append(")");

            Query query = entityManager.createNativeQuery(sql.toString());
            String serialId = companyId + String.format("%05d", value);
            query.setParameter("serial_id", serialId);
            query.setParameter("data_value", param.getValue());
            query.setParameter("column_name", param.getKey());
            query.setParameter("start_time", param.getStartTime());
            query.setParameter("operator", "张三");
            query.setParameter("status", param.getStatus());
            //todo show_flag当起效一样的时候 要修改show_flag 默认为1
            query.setParameter("show_flag", baseDataSaveRequest.getShowFlag());
            query.setParameter("c_id", companyId);
            if (baseDataSaveRequest.isMultiple()) {
                Query pkQuery = entityManager.createNativeQuery("SELECT MAX(pk_id) FROM " + "\"" + tableName + "\"");
                String pkId = (String) pkQuery.getSingleResult();
                query.setParameter("pk_id", Long.valueOf(pkId) + 1);
            }
            query.executeUpdate();

            keyDepotService.save(keyDepot);
            //todo  如果栏目有特殊含义 就在增加数据完成最后做 比如最高学历 身份证
        }
    }

    /**
     * 删除
     *
     * @param removeRequest
     */
    @Transactional
    public void remove(BaseDataRemoveRequest removeRequest) {
        String tableName = removeRequest.getTableName();
        //todo 先查询出原来的记录

        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT serial_id,data_value,column_name,start_time,operator,c_id,status,show_flag ");
        if (removeRequest.isMultiple()) {
            sql.append(" ,pk_id ");
        }
        sql.append(" FROM ");
        sql.append(tableName);
        sql.append(" WHERE 1 = 1 ");
        sql.append(" AND id = :id ");
        Query nativeQuery = entityManager.createNativeQuery(sql.toString());
        nativeQuery.setParameter("id", removeRequest.getId());
        nativeQuery.unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        Map<String, String> singleResult = (Map<String, String>) nativeQuery.getSingleResult();

        //然后增加一条同样的记录
        BaseDataSaveRequest saveRequest = new BaseDataSaveRequest();
        saveRequest.setTableName(tableName);
        singleResult.put("status", Status.DELETED.getId().toString());
        List<Param> paramList = new ArrayList<>();
        for (String key : singleResult.keySet()) {
            paramList.add(new Param(key, DateFormatUtils.getSystemDateByYmd(), singleResult.get(key), Status.DELETED.getId()));
        }
        saveRequest.setMultiple(removeRequest.isMultiple());
        saveRequest.setParamList(paramList);
        save(saveRequest);
    }
}

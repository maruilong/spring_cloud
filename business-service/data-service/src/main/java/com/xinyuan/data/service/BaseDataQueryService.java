package com.xinyuan.data.service;

import com.xinyuan.base.common.service.PageBean;
import com.xinyuan.base.common.web.Conditions;
import com.xinyuan.data.model.BaseDataQueryRequest;
import com.xinyuan.data.model.QueryResponse;
import com.xinyuan.data.model.TableQuery;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @author liang
 */
@Service
public class BaseDataQueryService {

    @Autowired
    private EntityManager entityManager;

    /**
     * 基础数据结果查询
     * @param queryRequest
     * @return
     */
    public QueryResponse query(BaseDataQueryRequest queryRequest) {

        Date startTime = queryRequest.getStartTime();
        List<Integer> status = queryRequest.getStatus();
        List<TableQuery> tables = queryRequest.getTables();

        TableJoin tableJoin = new TableJoin(queryRequest);

        String querySql = tableJoin.getQuerySql();
        String countSql = tableJoin.getCountSql();
        Query nativeQuery = entityManager.createNativeQuery(querySql);
        Query nativeCountQuery = entityManager.createNativeQuery(countSql);

        nativeQuery.unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);

        List<Conditions> conditions = queryRequest.getConditions();

        for (TableQuery tableQuery : tables) {
            String tableName = tableQuery.getTableName();
            List<String> columns = tableQuery.getColumns();
            nativeQuery.setParameter(tableName + "_columns", columns);
            nativeCountQuery.setParameter(tableName + "_columns", columns);
        }

        if (startTime == null) {
            startTime = new Date();
        }

        nativeQuery.setParameter("startTime", startTime);
        nativeCountQuery.setParameter("startTime", startTime);
        nativeQuery.setParameter("status", status);
        nativeCountQuery.setParameter("status", status);

        /**参数设置*/
        for (Conditions condition : conditions) {
            nativeQuery.setParameter(condition.getKey(), condition.getValue());
            nativeCountQuery.setParameter(condition.getKey(), condition.getValue());
        }

        /**分页部分*/
        PageBean pageBean = queryRequest.getPageBean();
        int pageNumber = pageBean.getPageNumber();
        int pageSize = pageBean.getPageSize();
        nativeQuery.setFirstResult(pageNumber == 0 ? 1 : pageNumber);
        nativeQuery.setMaxResults(pageSize);
        List resultList = nativeQuery.getResultList();
        BigInteger count = (BigInteger) nativeCountQuery.getSingleResult();

        return new QueryResponse(count, resultList);
    }


    /**
     * 基础数据结果查询
     *
     * @param queryRequest
     * @return
     */
//    public List query(BaseDataQueryRequest queryRequest) {
//        //主表名
//        //其他表名
//        List<String> columns = queryRequest.getColumns();
//        Date startTime = queryRequest.getStartTime();
//
//        //todo 通过表名查询这个信息集是否是多记录
//        boolean isMultiple = true;
//
//        //sql拼接
//        SqlStitching sqlStitching = new SqlStitching(isMultiple, queryRequest);
//
//        //表格查询传几个 查几个
//        TableQuery tableQuery = new TableQuery(sqlStitching);
//
//        Query nativeQuery = entityManager.createNativeQuery(tableQuery.getQuerySql());
//        nativeQuery.unwrap(NativeQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//        ArrayList<Conditions> conditions = queryRequest.getConditions();
//        nativeQuery.setParameter("columns", columns);
//        if (startTime == null) {
//            startTime = new Date();
//        }
//        nativeQuery.setParameter("startTime", startTime);
//
//        /**参数设置*/
//        for (Conditions condition : conditions) {
//            nativeQuery.setParameter(condition.getKey(), condition.getValue());
//        }
//
//        /**分页部分*/
//        PageBean pageBean = queryRequest.getPageBean();
//        int pageNumber = pageBean.getPageNumber();
//        int pageSize = pageBean.getPageSize();
//        nativeQuery.setFirstResult(pageNumber == 0 ? 1 : pageNumber);
//        nativeQuery.setMaxResults(pageSize);
//        List resultList = nativeQuery.getResultList();
//
//        return resultList;
//    }

}

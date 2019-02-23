package com.xinyuan.data.service;

import com.codingapi.tx.annotation.ITxTransaction;
import com.xinyuan.base.common.service.ParamCondition;
import com.xinyuan.base.common.service.SelectParam;
import com.xinyuan.base.service.BaseService;
import com.xinyuan.data.entity.ColumnHabit;
import com.xinyuan.data.mapper.ColumnHabitRepository;
import com.xinyuan.data.web.request.HabitQueryRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hwz
 */
@Service
public class ColumnHabitService extends BaseService<ColumnHabitRepository, ColumnHabit, Long> implements ITxTransaction {

    public ColumnHabit getByUser(HabitQueryRequest habitQueryRequest) {

        ColumnHabit columnHabit = null;

        List<SelectParam> selectParams = new ArrayList<>();
        selectParams.add(new SelectParam("userId", habitQueryRequest.getUserId(), ParamCondition.DB_EQUAL));
        selectParams.add(new SelectParam("functionId", habitQueryRequest.getFunctionId(), ParamCondition.DB_EQUAL));
        columnHabit = getByCondition(selectParams);

        if (columnHabit == null) {
            selectParams = new ArrayList<>();
            selectParams.add(new SelectParam("userId", 0, ParamCondition.DB_EQUAL));
            selectParams.add(new SelectParam("functionId", habitQueryRequest.getFunctionId(), ParamCondition.DB_EQUAL));
            columnHabit = getByCondition(selectParams);
        }

        return columnHabit;
    }
}

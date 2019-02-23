package com.xinyuan.data.model;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * @author liang
 */
@Data
public class QueryResponse {

    private BigInteger count;

    private List dataList;

    public QueryResponse() {
    }

    public QueryResponse(BigInteger count, List dataList) {
        this.count = count;
        this.dataList = dataList;
    }
}

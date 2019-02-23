package com.xinyuan.data.service;


import com.xinyuan.data.model.BaseDataRemoveRequest;
import com.xinyuan.data.model.BaseDataSaveRequest;

public interface BaseDataOptService {

    void save(BaseDataSaveRequest baseDataSaveRequest);

    void remove(BaseDataRemoveRequest removeRequest);
}

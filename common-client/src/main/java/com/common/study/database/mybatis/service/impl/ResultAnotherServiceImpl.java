package com.common.study.database.mybatis.service.impl;

import com.common.study.database.mybatis.mapper.ResultMapper;
import com.common.study.database.mybatis.service.ResultAnotherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResultAnotherServiceImpl implements ResultAnotherService {

    @Autowired
    private ResultMapper resultMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int updateResult10() {
        return resultMapper.updateResult10();
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public int updateResult5() {
        return resultMapper.updateResult5();
    }
}

package com.common.study.database.mybatis.service.impl;

import com.common.study.database.mybatis.mapper.ResultMapper;
import com.common.study.database.mybatis.service.ResultAnotherService;
import com.common.study.database.mybatis.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class ResultServiceImpl implements ResultService {

    @Resource
    private ResultAnotherService resultAnotherService;

    @Autowired
    private ResultMapper resultMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int updateResult10() {
         resultMapper.updateResult10();
         return resultAnotherService.updateResult5();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int updateResult5() {
        return resultMapper.updateResult5();
    }
}

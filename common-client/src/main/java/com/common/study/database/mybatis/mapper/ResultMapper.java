package com.common.study.database.mybatis.mapper;

import com.common.study.database.mybatis.bean.Result;

import java.util.List;

public interface ResultMapper {

    public List<Result> queryByScore(int score);
}

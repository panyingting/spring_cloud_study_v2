package com.common.study.database.mybatis;

import com.common.study.bean.UserBean;
import com.common.study.database.mybatis.bean.Result;
import com.common.study.database.mybatis.mapper.ResultMapper;
import com.common.study.database.mybatis.service.ResultService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MybatisTest {


    @Autowired
    private ResultMapper resultMapper;

    @Resource
    private ResultService resultService;

    @Autowired
    private UserBean userBean;

    @Test
    public void testMapper(){

        List<Result> results = resultMapper.queryByScore(100);

        System.out.println(results);

        System.out.println(userBean);
    }

    @Test
    public void testTransactional(){

        int num = resultService.updateResult10();
        System.out.println("执行完成："+num);

    }

    public static void main(String[] args) {
        UserBean userBean = new UserBean();
        userBean.setAge(2);
        UserBean userBean2 = new UserBean();
        userBean2.setAge(2);

        List<UserBean> userBeans = new ArrayList<UserBean>(){{ add(userBean); add(userBean2);}};

        int count = userBeans.stream().map(UserBean::getAge).reduce(0, (a,b) -> a+b);
        System.out.println("count:"+count);
    }
}

package mybatis;

import com.common.study.bean.UserBean;
import com.common.study.database.mybatis.MapperApplication;
import com.common.study.database.mybatis.bean.Result;
import com.common.study.database.mybatis.mapper.ResultMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MapperApplication.class)
public class MybatisTest {

    @Autowired
    private ResultMapper resultMapper;

    @Test
    public void testMapper(){

        List<Result> results = resultMapper.queryByScore(100);

        System.out.println(results);
    }


}

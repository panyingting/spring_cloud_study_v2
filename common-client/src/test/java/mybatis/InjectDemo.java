package mybatis;

import com.common.study.bean.UserBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InjectDemo {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:aop.xml");

        UserBean userBean = context.getBean("userBean001", UserBean.class);
        System.out.println("\n\n");
        System.out.println("=========="+userBean);
        System.out.println("\n\n");

    }
}

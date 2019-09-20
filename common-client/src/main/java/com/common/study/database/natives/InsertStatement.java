package com.common.study.database.natives;

import java.sql.*;

public class InsertStatement {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;

        try
        {
            //加载驱动类
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_db_pyt","root","root");

            conn.setAutoCommit(false); //JDBC中默认是true，我们改成false，然后在后面手动提交

            ps1 = conn.prepareStatement("insert into Result (r_id, c_id,s_id, score) values (?,?,?,?)");//?是占位符
            ps1.setObject(1, 700334);
            ps1.setObject(2, 333);
            ps1.setObject(3, 9);
            ps1.setObject(4, 90);
            ps1.execute();
            System.out.println("插入一行数据");

            try
            {
                Thread.sleep(3000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            ps2 = conn.prepareStatement("insert into Result (c_id,s_id, score) values (?,?,?)");//?是占位符
            ps2.setObject(1, 332);
            ps2.setObject(2, 9);
            ps2.setObject(3, 90);
            ps2.execute();
            System.out.println("插入第二行数据\"");

            conn.commit();//提交事务
        }
        catch (Exception e) {
            if(conn != null)
                conn.rollback();//某一条数据添加失败时，回滚
        }finally
        {
            if(conn != null)
                conn.close();
            if(ps1 != null && ps2 != null){
                ps1.close();
                ps2.close();
            }
        }
    }
}

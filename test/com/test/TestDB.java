package com.test;

import com.zth.db.Db;
import com.zth.pojo.Admin;
import com.zth.utils.Md5Encrypt;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestDB {
    @Test
    public void query1(){
        String sql = "select * from admin";

        try {
            List<Admin> list = Db.query(sql,new BeanListHandler<Admin>(Admin.class));
            for (Admin admin:list) {
                System.out.println(admin.getId()+"\t"+admin.getEmail()+"\t"+admin.getUpwd());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void query2(){
        String sql = "select * from admin";

        try {
            Admin admin = Db.query(sql,new BeanHandler<Admin>(Admin.class));
            System.out.println(admin.getId()+"\t"+admin.getEmail()+"\t"+admin.getUpwd());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addAdmin(){
        //String sql = "insert into admin(email,upwd,upur) values(?,?,?)";
        String sql="insert into msg(title,content,admin_id,ctime) values(?,?,?,?)";

        try {
            Date date = new Date();//获得系统时间.

            SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
            String nowTime = sdf.format(date);

            Date time = sdf.parse( nowTime );


            //Db.update(sql,"@qq.com", Md5Encrypt.md5("admin"),"01000");
            //Db.update(sql,"标题","南京的雨不停的下不停的下",12,nowTime);
            System.out.println("插入成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

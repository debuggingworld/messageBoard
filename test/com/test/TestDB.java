package com.test;

import com.zth.db.Db;
import com.zth.pojo.Admin;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class TestDB {
    @Test
    public void query(){
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
}

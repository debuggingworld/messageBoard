package com.zth.servlet;

import com.zth.db.Db;
import com.zth.pojo.Msg;
import com.zth.servlet.core.ServletBase;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.servlet.annotation.WebServlet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/admin/msg")
public class MsgServlet extends ServletBase {
    @Override
    public void index(Mapping mapping) throws Exception {

        // 查询所有留言
        List<Msg> msgs = null;

        msgs = Db.query("select * from msg",new BeanListHandler<Msg>(Msg.class));

        mapping.setAttr("allmsg",msgs);
        mapping.forward("page/main.jsp");
    }

    public void msg_add(Mapping mapping) throws Exception {
        mapping.forward("page/msg_add.jsp");
    }

    public void msg_saveadd(Mapping mapping) throws Exception {

        Msg msg = new Msg();
        try {
            mapping.getBean(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        msg.setCtime(new Date());

        String sql="insert into msg(title,content,ctime,admin_id) values(?,?,?,?)";

        try {
            Db.update(sql,msg.getTitle(),msg.getContent(),msg.getCtime(),msg.getAdmin_id());
            mapping.setAttr("msg","发布成功");
        } catch (SQLException e) {
            mapping.setAttr("msg","发布失败");
            e.printStackTrace();
        }

        index(mapping);
    }
}

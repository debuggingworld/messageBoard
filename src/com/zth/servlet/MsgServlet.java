package com.zth.servlet;

import com.zth.db.Db;
import com.zth.db.PageDiv;
import com.zth.pojo.Msg;
import com.zth.servlet.core.ServletBase;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.servlet.annotation.WebServlet;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/admin/msg")
public class MsgServlet extends ServletBase {
    @Override
    public void index(Mapping mapping) throws Exception {

        // 查询所有留言
       /* List<Msg> msgs = null;

        msgs = Db.query(" select m.*,a.name as adminName from msg m inner join admin a on m.admin_id = a.id order by m.id desc",new BeanListHandler<Msg>(Msg.class));

        mapping.setAttr("allmsg",msgs);*/

       int pageSize = 5;
       int pageNo = 1;

       if (mapping.getInt("pageNo") >1){
           pageNo = mapping.getInt("pageNo");
       }

        PageDiv<Msg> pageDiv = null;

        List<Msg> list = Db.query(" select m.*,a.name as adminName from msg m inner join admin a on m.admin_id = a.id order by m.id desc limit ?,?",new BeanListHandler<Msg>(Msg.class),(pageNo-1)*pageSize,pageSize);

        Object object = Db.query(" select count(id) from msg",new ArrayHandler())[0];

        int totalCount = 0;

        if (object instanceof BigInteger){
            totalCount = ((BigInteger)object).intValue();
        }else if (object instanceof Long){
            totalCount = ((Long)object).intValue();
        }
        pageDiv = new PageDiv<>(pageNo,pageSize,totalCount,list);


        mapping.setAttr("pd",pageDiv);

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

            SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
            String nowTime = sdf.format(msg.getCtime());

            Db.update(sql,msg.getTitle(),msg.getContent(),nowTime,msg.getAdmin_id());
            mapping.setAttr("msg","发布成功");
        } catch (SQLException e) {
            mapping.setAttr("err","发布失败");
            e.printStackTrace();
        }

        index(mapping);
    }

    public void showMsg(Mapping mapping) throws Exception {

        int id  = mapping.getInt("id");

        if (id>0){
            String sql = "select * from  msg where id = ?";
            Msg msg = Db.query(sql,new BeanHandler<Msg>(Msg.class),id);
            mapping.setAttr("msg",msg);
        }
        mapping.forward("page/show.jsp");
    }


    public void edit(Mapping mapping) throws Exception {

        int id  = mapping.getInt("id");

        if (id>0){
            String sql = "select * from  msg where id = ?";
            Msg msg = Db.query(sql,new BeanHandler<Msg>(Msg.class),id);
            mapping.setAttr("msg",msg);
        }
        mapping.forward("page/msg_edit.jsp");
    }

    public void msg_saveedit(Mapping mapping) throws Exception {

        Msg msg = new Msg();
        mapping.getBean(msg);

        String sql = "update msg set title = ?,content = ? where id =?";

        try{
            Db.update(sql,msg.getTitle(),msg.getContent(),msg.getId());
            mapping.setAttr("msg","修改成功！");
        }catch (Exception e){
            mapping.setAttr("msg","修改失败！");
            e.printStackTrace();
        }

        index(mapping);

    }

    public void del(Mapping mapping) throws Exception {

       int id  = mapping.getInt("id");

       String sql = "delete from msg where id = ?";

        try{
            Db.update(sql,id);
            mapping.setAttr("msg","删除成功！");
        }catch (Exception e){
            mapping.setAttr("msg","删除失败！");
            e.printStackTrace();
        }
        index(mapping);
    }

}

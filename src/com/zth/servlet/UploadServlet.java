package com.zth.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@MultipartConfig
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter out = resp.getWriter();

        Part part = req.getPart("uppic");

        if (null!= part){
            String path = this.getServletContext().getRealPath("ups"+"/");
            System.out.println("path::::"+path);
            String newName =randName()+getExtName(getFileName(part));

            part.write(path+newName);

            out.write("{ newname:'"+newName+"',error:0 }");
        }else {
            out.write("{ error:1}");
        }
        out.close();
    }


    public String getFileName(Part part){

        return part.getSubmittedFileName();

    }

    private SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    public String randName(){
        String re = null;
        return  re = format.format(new Date())+"_"+(int)(Math.floor(Math.random()*1000));
    }

    public String getExtName(String fname){
        return fname.toLowerCase().substring(fname.lastIndexOf("."));

    }
}

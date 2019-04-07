package com.zth.servlet;

import com.zth.servlet.core.ServletBase;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

@WebServlet("/randomImage")
public class RandomImgAction extends ServletBase {
    private int width = 80;
    private int height = 27;
    //创建一个随机数生成器
    Random random = new Random();


    @Override
    public void index(Mapping mapping) throws Exception{
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);

        Graphics2D graph = image.createGraphics();
        graph.setColor(Color.WHITE);

        graph.fillRect(0,0,width,height);

        // 创建字体，字体的大小应该根据图片的高度来定
        Font font = new Font("Times New Roman",Font.PLAIN,22);
        // 设置字体
        graph.setFont(font);



        // 随机产生 160 条干扰线
        graph.setColor(new Color(160,160,160));
        for (int i = 0; i < 320; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            graph.drawLine(x,y,x+x1,y+y1);
        }
        // 画边框
        graph.setColor(Color.BLACK);
        graph.drawRect(0,0,width-1,height-1);

        // randomCode 用于保存随机产生的验证码，以便用户登录后进行验证
        StringBuffer randomCode = new StringBuffer();

        int red = 0,green = 0,blue = 0;

        // 随机产生 4 位数字的验证码
        for (int i = 0; i < 4; i++) {
            // 得到随机产生的验证码数字
            String rand = String.valueOf(random.nextInt(10));

            // 产生随机的颜色分量来构造颜色值，使输出的每位数字的颜色都不同

            red = random.nextInt(255);
            green = random.nextInt(10);
            blue = random.nextInt(10);

            graph.setColor(new Color(red,green,blue));
            graph.drawString(rand,13*i+12,23);

            // 将产生的随机数组合在一起
            randomCode.append(rand);
        }

        // 将四位数字的验证码保存到 Session 中

        try {
            mapping.setSesstionAttr("randomCode",randomCode.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //进制图像缓存
        mapping.getResponse().setHeader("Pragma","no-cache");
        mapping.getResponse().setHeader("Cache-Control","no-cache");
        mapping.getResponse().setDateHeader("Expires",0);

        mapping.getResponse().setContentType("image/jpeg");

        ServletOutputStream outputStream = mapping.getResponse().getOutputStream();

        ImageIO.write(image,"jpeg",outputStream);

        outputStream.close();
    }
}

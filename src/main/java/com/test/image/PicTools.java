package com.test.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 图片相关操作
 */
public class PicTools {

    private Font font = new Font("宋体",Font.PLAIN,12);

    private Graphics2D graphics2D = null;

    private int fontSize = 0;

    private int x = 0;

    private int y = 0;

    /**
     * 1、导入本地图片到缓冲区
     */
    public BufferedImage loadImageLocal(String imgPath){
        try {
            return ImageIO.read(new File(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 2、导入网络图片到缓冲区
     */
    public BufferedImage loadImageUrl(String imgUrl){
        try {
            URL url = new URL(imgUrl);
            return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 3、生成新图片到本地
     */
    public void writeImageLocal(String newImgPath,BufferedImage image){
        if (newImgPath!=null && image!=null){
            File outputFile = new File(newImgPath);
            try {
                ImageIO.write(image,"jpg",outputFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 4、设定文字的字体等
     */
    public void setFont(String fontStyle,int fontSize){
        this.fontSize = fontSize;
        this.font = new Font(fontStyle,Font.PLAIN,fontSize);
    }

    /**
     * 5、修改图片、返回修改后的图片缓冲区（只输出一行文本）
     */
    public BufferedImage modifyImage(BufferedImage image,Object content,int x,int y){
        int w = image.getWidth();
        int h = image.getHeight();
        graphics2D = image.createGraphics();
        graphics2D.setBackground(Color.WHITE);
        //设置字体颜色
        graphics2D.setColor(Color.ORANGE);
        if (this.font!=null){
            graphics2D.setFont(this.font);
        }
        //验证输出位置的纵坐标与横坐标
        if (x>=h||y>=w){
            this.x = h-this.fontSize+2;
            this.y = w;
        }else {
            this.x = x;
            this.y = y;
        }
        if (content!=null){
            graphics2D.drawString(content.toString(),this.x,this.y);
        }
        graphics2D.dispose();
        return image;
    }

    /**
     * 6、修改图片、返回修改后的图片缓冲区（输出多个文本段）xory：true表示将内容在一行中输出，false表示将内容多行输出
     */
    public BufferedImage modifyImage(BufferedImage image,Object[] contents,int x,int y,boolean xory) {
        int w = image.getWidth();
        int h = image.getHeight();
        graphics2D = image.createGraphics();
        graphics2D.setBackground(Color.WHITE);
        //设置字体颜色
        graphics2D.setColor(Color.ORANGE);
        if (this.font != null) {
            graphics2D.setFont(this.font);
        }
        //验证输出位置的纵坐标与横坐标
        if (x >= h || y >= w) {
            this.x = h - this.fontSize + 2;
            this.y = w;
        } else {
            this.x = x;
            this.y = y;
        }
        if (contents != null) {
            int arrLen = contents.length;
            if (xory) {
                for (int i = 0; i < arrLen; i++) {
                    graphics2D.drawString(contents[i].toString(), this.x, this.y);
                    //重新计算文本输出位置
                    this.x += contents[i].toString().length() * this.fontSize / 2 + 5;
                }
            } else {
                for (int i = 0; i < arrLen; i++) {
                    graphics2D.drawString(contents[i].toString(), this.x, this.y);
                    //重新计算文本输出位置
                    this.y += this.fontSize + 2;
                }
            }
        }
        graphics2D.dispose();
        return image;
    }

    /**
     * 8、将两张图片合成一张图片(覆盖式合并，即把image1覆盖在image2之上)
     *
     */
    public BufferedImage coverImagetogeter(BufferedImage image1,BufferedImage image2){
        int w = image1.getWidth();
        int h = image2.getHeight();

        graphics2D = image2.createGraphics();
        graphics2D.drawImage(image1,100,20,w,h,null);
        graphics2D.dispose();

        return image2;
    }

    /**
     * 9、main测试方法
     */
    public static void main(String[] args) {
        PicTools tt = new PicTools();

        BufferedImage image2 = tt.loadImageLocal("D:\\projects\\10、武进图片整合\\测试图片\\1115\\1115\\1).jpg");
        BufferedImage image1 = tt.loadImageLocal("D:\\projects\\10、武进图片整合\\测试图片\\1115\\1115\\2).jpg");
        //往图片上写文件
        //tt.writeImageLocal("E:\\ploanshare\\2\\22.jpg", tt.modifyImage(d, "000000", 90, 90));

        tt.writeImageLocal("D:\\projects\\10、武进图片整合\\测试图片\\1115\\1115\\3).jpg", tt.coverImagetogeter(image1, image2));
        //将多张图片合在一起
        System.out.println("success");
    }

}

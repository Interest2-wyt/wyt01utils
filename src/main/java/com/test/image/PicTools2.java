package com.test.image;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;
import java.util.Iterator;

public class PicTools2 {

    /**
     * 1、截图方法
     *
     * @param srcFile 源图片、targetFile截好后图片全名、startXAxis 开始截取位置横坐标、
     *                startYAxis开始截图位置纵坐标、width截取的长，hight截取的高
     */
    public static void cutImage(String srcFile, String targetFile, int startXAxis, int startYAxis, int width, int hight) {
        //1、获取图片读入器
        Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
        ImageReader reader = readers.next();
        //2、获取图片读入流
        InputStream source = null;
        ImageInputStream iis = null;
        try {
            source = new FileInputStream(srcFile);
            iis = ImageIO.createImageInputStream(source);
        } catch (Exception e) {
            System.out.println("获取图片流失败");
        }
        reader.setInput(iis, true);
        //3、图片参数对象
        ImageReadParam param = reader.getDefaultReadParam();
        Rectangle rect = new Rectangle(startXAxis, startYAxis, width, hight);
        param.setSourceRegion(rect);
        BufferedImage bi = null;
        try {
            bi = reader.read(0, param);
            ImageIO.write(bi, targetFile.split("\\.")[1], new File(targetFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2、图片拼接方法（注意：必须两张图片的长宽一致）
     *
     * @param files      要拼接的文件列表
     * @param type       拼接的类型：1表示横向拼接，2表示纵向拼接
     * @param targetFile
     */
    public static void mergeImage(String[] files, int type, String targetFile) {
        int len = files.length;
        if (len < 1) {
            throw new RuntimeException("图片数量小于1");
        }
        File[] src = new File[len];
        BufferedImage[] images = new BufferedImage[len];
        int[][] imgArray = new int[len][];
        int width = 0;
        int height = 0;
        for (int i = 0; i < len; i++) {
            src[i] = new File(files[i]);
            try {
                images[i] = ImageIO.read(src[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            width = images[i].getWidth();
            height = images[i].getHeight();
            imgArray[i] = new int[width * height];
            imgArray[i] = images[i].getRGB(0, 0, width, height, imgArray[i], 0, width);
        }
        int newHeight = 0;
        int newWidth = 0;
        //根据type设置，对图片进行横向或纵向拼接
        for (int i = 0; i < images.length; i++) {
            if (type == 1) {
                newHeight = newHeight > images[i].getHeight() ? newHeight : images[i].getHeight();
                newWidth += images[i].getWidth();
            } else if (type == 2) {
                newWidth = newWidth > images[i].getWidth() ? newWidth : images[i].getWidth();
                newHeight += images[i].getHeight();
            }
        }
        if (type == 1 && newWidth < 1) {
            return;
        }
        if (type == 2 && newHeight < 1) {
            return;
        }
        //生成新的图片
        BufferedImage imageNew = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        int height_i = 0;
        int width_i = 0;
        for (int i = 0; i < images.length; i++) {
            if (type == 1) {
                imageNew.setRGB(width_i, 0, images[i].getWidth(), newHeight, imgArray[i], 0,
                        images[i].getWidth());
                width_i += images[i].getWidth();
            } else if (type == 2) {
                imageNew.setRGB(0, height_i, newWidth, images[i].getHeight(), imgArray[i], 0, newWidth);
                height_i += images[i].getHeight();
            }
        }
        //输出想要的图片
        try {
            ImageIO.write(imageNew, targetFile.split("\\.")[1], new File(targetFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 3、将小图片贴到大图片形成一张图（合并）
     *
     * @param bigPath
     * @param smallPath
     * @param outFile
     */
    public static void overlapImage(String bigPath, String smallPath, String outFile) {
        try {
            BufferedImage big = ImageIO.read(new File(bigPath));
            BufferedImage small = ImageIO.read(new File(smallPath));
            Graphics2D g = big.createGraphics();
            int x = (big.getWidth() - small.getWidth()) / 2;
            int y = (big.getHeight() - small.getHeight()) / 2;
            g.drawImage(small, x, y, small.getWidth(), small.getHeight(), null);
            g.dispose();
            ImageIO.write(big, outFile.split("\\.")[1], new File(outFile));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String[] files = new String[]{
                "D:\\projects\\10、武进图片整合\\测试图片\\1115\\1115\\1).jpg",
                "D:\\projects\\10、武进图片整合\\测试图片\\1115\\1115\\2).jpg"
        };
        long timeStamp = new Date().getTime();
        String targetFile = System.getProperty("user.dir") +"/pictemp/"+ timeStamp + ".jpg";
//        String targetFile = "D:\\projects\\10、武进图片整合\\测试图片\\1115\\1115\\3).jpg";
        mergeImage(files,1,targetFile);
    }
}

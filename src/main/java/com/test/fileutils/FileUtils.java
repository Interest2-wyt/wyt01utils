package com.test.fileutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @Class : FileUtils
 * @Date : 2020/7/26 0026
 * @Author : wangyongtao
 * @Description : TODO
 **/
public class FileUtils {
    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 移动文件
     */
    public static void mvFile(File origin,String destPath,String fileName){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        File dest = null;
        File directory = new File(destPath);
        try {
            directory.mkdirs();
            dest = new File(destPath+fileName);
            fis = new FileInputStream(origin);
            fos = new FileOutputStream(dest);
            byte datas[] = new byte[1024*8];//创建搬运工具
            int len = 0;//创建长度
            while((len = fis.read(datas))!=-1){ //循环读取数据
                fos.write(datas,0,len);
            }
        } catch (Exception e) {
            logger.error("文件搬运时出现异常：[{}]",e);
        } finally {
            if (fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error("关闭文件输入流出现异常：",e);
                }
            }
            if (fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    logger.error("关闭文件输出流出现异常：",e);
                }
            }
            if (origin.exists()){
                boolean flag = origin.delete();
                logger.info("搬运文件 [{}] 成功，此时删除删除源文件：[{}]",fileName,flag);
            }else {
                logger.error("搬运文件后删除失败，因为该文件不存在：[{}]",fileName);
            }
        }
    }

    /**
     * 将字符串输出成文件
     */
    public static void createFileFromString(String context,String filename,String path){
        FileOutputStream fos = null;
        File dest = null;
        File directory = new File(path);
        try {
            directory.mkdirs();
            dest = new File(path+filename);
            fos = new FileOutputStream(dest);
            fos.write(context.getBytes());
        } catch (Exception e) {
            logger.error("文件搬运时出现异常：[{}]",e);
        } finally {
            if (fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    logger.error("关闭文件输出流出现异常：",e);
                }
            }
        }
    }

    /**
     * 以字符格式获取某文件内所有内容
     */
    public static String getStringFromFile(File file){
        StringBuilder result = new StringBuilder();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(s);
            }
            br.close();
        }catch (Exception e){
            logger.error("从文件获取字符串出现异常：",e);
        }finally {
            try {
                br.close();
            } catch (IOException e) {
                logger.error("getStringFromFile方法关闭文件流出现异常：",e);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
//        File file = new File("C:\\Users\\wangyongtao\\Desktop\\新建文件夹\\20200515085617914443556.zip");
//        File dest = new File("C:\\Users\\wangyongtao\\Desktop\\新建文件夹\\20200515085617914443556\\");
//        mvFile(file,"C:/Users/wangyongtao/Desktop/新建文件夹/20200515085617914443556/",file.getName());

        String text = "123456789中文乱码测试";
        createFileFromString(text,"1B173D37-C02A-7D4A-A65C-2D9A5B8F6C4B.txt","E:\\临时存放\\");
    }
}

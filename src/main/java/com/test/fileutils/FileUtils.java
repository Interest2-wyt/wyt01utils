package com.test.fileutils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ClassName: ${type_name}
 * @Description:文件操作工具类
 * @author: ${user}
 * @date: ${date} ${time}
 * ${tags}
 */
public class FileUtils {
    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

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

    public static void main(String[] args) {
        File file = new File("C:\\Users\\wangyongtao\\Desktop\\新建文件夹\\20200515085617914443556.zip");
        File dest = new File("C:\\Users\\wangyongtao\\Desktop\\新建文件夹\\20200515085617914443556\\");
        mvFile(file,"C:/Users/wangyongtao/Desktop/新建文件夹/20200515085617914443556/",file.getName());
    }
}

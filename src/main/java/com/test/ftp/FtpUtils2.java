package com.test.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class FtpUtils2 {
    private static Logger logger = LoggerFactory.getLogger(FtpUtils.class);
    public static FTPClient ftpClient = null;
    private static String encoding = "utf-8";

    /**
     * Description:登录接口
     * @param url  FTP服务器hostname
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     */
    public static boolean loginIn(String url, int port, String username,String password){
        int reply;
        boolean flag=false;
        try {
            ftpClient = new FTPClient();
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
//            ftpClient.connect(url);
            ftpClient.connect(url, port);// 连接FTP服务器
            // 登录
            flag = ftpClient.login(username, password);
            if (flag==false){
                return flag;
            }
            ftpClient.enterLocalPassiveMode();
            ftpClient.setControlEncoding(encoding);
            // 检验是否连接成功
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                System.out.println("连接失败");
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static void loginOut(){
        try {
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
    }
    /**
     * Description: 向FTP服务上传文件
     * @param path FTP服务器保存目录,如果是根目录则为“/”
     * @param filename  上传到FTP服务器上的文件名
     * @param input 本地文件输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String path, String filename, InputStream input) {
        boolean result = false;
        try {
            // 转移工作目录至指定目录下
            boolean change = ftpClient.changeWorkingDirectory(new String(path.getBytes(encoding),"iso-8859-1"));
            if (!change){
                ftpClient.makeDirectory(path);
                change = ftpClient.changeWorkingDirectory(new String(path.getBytes(encoding),"iso-8859-1"));
            }
            if (!change){
                createDirectorys(path);
                change = ftpClient.changeWorkingDirectory(new String(path.getBytes(encoding),"iso-8859-1"));
            }
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            if (change) {
                result = ftpClient.storeFile(new String(filename.getBytes(encoding),"iso-8859-1"), input);
                if (result) {
                    System.out.println("上传成功!");
                }
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将本地文件上传到FTP服务器上
     *
     */
    public static void testUpLoadFromDisk() {
        try {
            FileInputStream in = new FileInputStream(new File("F:/wangyongtao/projects/ctm01hatim/1583816549.jpg"));
            loginIn("127.0.0.1", 21, "wyt","123456");
            boolean flag = uploadFile( "/test2/teae", "12.jpg", in);
            loginOut();
            System.out.println(flag);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * Description: 从FTP服务器下载文件
     *
     * @param url  FTP服务器hostname
     * @param port FTP服务器端口
     * @param username   FTP登录账号
     * @param password   FTP登录密码
     * @param remotePath  FTP服务器上的相对路径
     * @param fileName   要下载的文件名
     * @param localPath  下载后保存到本地的路径
     * @return
     */
    public static boolean downFile(String url, int port, String username,
                                   String password, String remotePath, String fileName,
                                   String localPath) {
        boolean result = false;
        try {
            logger.info("进入ftp下载");
            int reply;
            ftpClient = new FTPClient();
            ftpClient.setControlEncoding(encoding);
            logger.info("开始连接ftp的ip和端口");
//            ftpClient.connect(url, port);
            ftpClient.connect(url);
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            logger.info("开始使用用户名和密码登录：");
            boolean flag = ftpClient.login(username, password);// 登录
            logger.info("用户名密码登录的结果是：[{}]",flag);
            // 设置文件传输类型为二进制
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 获取ftp登录应答代码
            reply = ftpClient.getReplyCode();
            // 验证是否登陆成功
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                logger.error("FTP server refused connection.");
                return result;
            }
            // 修改ftp连接从默认的主动模式到被动模式
            ftpClient.enterLocalPassiveMode();
            
            logger.info("更改ftp模式后，切换ftp服务的路径：");
            ftpClient.changeWorkingDirectory(new String(remotePath.getBytes(encoding),"iso-8859-1"));
            logger.info("[{}] 远程路径下的文件数：[{}]",remotePath,ftpClient.listFiles().length);
            // 获取文件列表
            logger.info("不切换文件目录，直接访问对应路径下文件：[{}]",remotePath);
            FTPFile[] fs = ftpClient.listFiles();
            logger.info("[{}] 远程路径下的文件数：[{}]",remotePath,fs.length);
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    logger.info("找到目标文件，开始下载：[{}]",ff.getName());
                    File localFile = new File(localPath + "/" + ff.getName());
                    OutputStream is = new FileOutputStream(localFile);
                    ftpClient.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }
            ftpClient.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    /**
     * 将FTP服务器上文件下载到本地
     *
     */
    public void testDownFile() {
        try {
            boolean flag = downFile("10.0.0.102", 21, "admin",
                    "123456", "/", "ip.txt", "E:/");
            System.out.println(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建多级目录
     */
    public static  boolean createDirectorys(String paths){
        String[] path = paths.replaceFirst("/","").split("/");
        boolean flag = true;
        String pathTemp = "";
        for (int i=0;i<path.length;i++){
            try {
                pathTemp = pathTemp+"/"+path[i];
                flag = ftpClient.makeDirectory(new String(pathTemp.getBytes(encoding),"iso-8859-1"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public static void main(String[] args) {
//        FtpUtils ftpTestModule = new FtpUtils();
//        ftpTestModule.testUpLoadFromDisk();
        testUpLoadFromDisk();
    }
}

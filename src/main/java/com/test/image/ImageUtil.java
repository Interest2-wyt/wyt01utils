package com.test.image;

import cn.hutool.core.codec.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Objects;

public class ImageUtil {
    private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);
    /**
     * 网络图片转BASE64 (http)
     */
    public static String onlineImage2Base64(String imagePath) {
        final ByteArrayOutputStream data = new ByteArrayOutputStream();
        InputStream is = null;
        try {
            // 创建URL
            URL url = new URL(imagePath);
            final byte[] by = new byte[1024];
            // 创建链接
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            is = conn.getInputStream();
            // 将内容读取内存中
            int len = -1;
            while ((len = is.read(by)) != -1) {
                data.write(by, 0, len);
            }
            // 对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(data.toByteArray());
        } catch (IOException e) {
            logger.error("图片转base64异常，异常信息为："+e.toString());
        } finally {
            if (is != null) {
                // 关闭流
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("图片流关闭进入异常，异常信息为："+e.toString());
                }
            }
        }
        return null;
    }

    /**
     * 网络图片转BASE64 (https)
     */
    public static String httpsImage2Base64(String httpUrl) {
        BufferedReader input = null;
        StringBuilder sb = null;
        URL url = null;
        HttpURLConnection con = null;
        final ByteArrayOutputStream data = new ByteArrayOutputStream();
        InputStream is = null;
        try {
            url = new URL(httpUrl);
            try {
                // trust all hosts
                trustAllHosts();
                HttpsURLConnection https = (HttpsURLConnection)url.openConnection();
                if (url.getProtocol().toLowerCase().equals("https")) {
                    https.setHostnameVerifier(DO_NOT_VERIFY);
                    con = https;
                } else {
                    con = (HttpURLConnection)url.openConnection();
                }
                final byte[] by = new byte[1024];
                is = con.getInputStream();
                // 将内容读取内存中
                int len = -1;
                while ((len = is.read(by)) != -1) {
                    data.write(by, 0, len);
                }
                // 对字节数组Base64编码
                BASE64Encoder encoder = new BASE64Encoder();
                return encoder.encode(data.toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } finally {
            // close buffered
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }}

            // disconnecting releases the resources held by a connection so they may be closed or reused
            if (con != null) {
                con.disconnect();
            }
        }
        return null;
    }

    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
    /**
     * Trust every server - dont check for any certificate
     */
    private static void trustAllHosts() {
        final String TAG = "trustAllHosts";
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[] {};
            }
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                logger.info(TAG, "checkClientTrusted");
            }
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                logger.info(TAG, "checkServerTrusted");
            }
        } };
        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 本地图片转换Base64的方法
     */
    public static String localImage2Base64(String imgPath) {
        InputStream in = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgPath);
            byte[] data = new byte[in.available()];
            in.read(data);
            // 对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            // 返回Base64编码过的字节数组字符串
            return encoder.encode(Objects.requireNonNull(data));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * base64编码转存成本地图片
     */
    public static String base642LocalImage(String base64Images){
//        String filePath = System.getProperty("user.dir").replace("\\", "/") + "/config/";
        String filePath = System.getProperty("user.dir").replace("\\", "/") + "/";
        String picName = System.currentTimeMillis() + ".jpg";
        try {
            //Base64解码
            byte[] b = new BASE64Decoder().decodeBuffer(base64Images);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {
                    //调整异常数据
                    b[i]+=256;
                }
            }
            //新生成的图片
            OutputStream out = new FileOutputStream(filePath+picName);
            out.write(b);
            out.flush();
            out.close();
            return filePath+picName;
        }catch (Exception e){
            logger.warn("base64编码转存成本地图片失败，报错信息为："+e.toString());
        }
        return null;
    }

    /**
     * 证件照上传云存储后，删除本地缓存
     */
    public static void deleteLocalImage(String filePath){
        File file = new File(filePath);
        if (file.exists()){
            file.delete();
        }
    }

    public static void main(String[] args) {
//        String netPath = "https://32.58.1.10/ngx/proxy?i=aHR0cDovLzE3My4yMC40MS4xMjo2MTIwL3BpYz81ZGRiODVpMzYtZSo0ZDExNTgxYjNtOGVwPXQ9aTFwKmk9ZDFzKmk3ZDg5KjNkNj0qOGIwaTdlZTNiMjg2YjRlZDAtLTgwYzU1OC01ODdzNjZjN3o2Y2VpPTY9";
//        String localPath = "F:\\项目集合\\10、南通TIM平台\\微信图片_20200209184032.png";
//        String netBase64 = httpsImage2Base64(netPath).replaceAll("\r\n","");
//        System.out.println(netBase64);
        String str = "normal://repository-builder/20191226/w4PiHcDotG1saF8qUzDb5Q==@1";

        System.out.println(Base64.encode(str));
//        String path = base642LocalImage(netBase64);
//        System.out.println(path);
//        deleteLocalImage(path);
//        String localBase64 = localImage2Base64(localPath);
//        System.out.println(localBase64);


    }


}

package com.test.httpmethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class CommonHttpUitl {
    private static Logger logger  = LoggerFactory.getLogger(CommonHttpUitl.class);
    private RestTemplate restTemplate = new RestTemplate();

    public CommonHttpUitl() {
        this.restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
    }

    /**
     * post请求
     */
    public String doPostByTemplate(String url, String request) {
        //1、请求头
        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//        headers.setContentType(type);
        headers.add("Content-Type","application/json;charset=utf-8");
        headers.add("connection","Keep-Alive");
        headers.add("accept", "*/*");
//        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        //4、用json字符串请求进行调用
        String str = null;
        HttpEntity<String> httpEntity = null;
        try {
            httpEntity = new HttpEntity<>(request,headers);
            str = restTemplate.postForObject(url,httpEntity,String.class);
            logger.info("用json字符串请求进行调用的结果是：[{}]",str);
        } catch (Exception e) {
            logger.error("用json字符串请求进行调用进入异常，异常信息为：",e);
        }
        return str;
    }

    /**
     * post请求(再请求头中添加session_id)
     */
    public String doPostHeaderByTemplate(String url, String request,String session_id) {
        //1、请求头
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("session_id",session_id);
        //4、用json字符串请求进行调用
        String str = null;
        HttpEntity<String> httpEntity = null;
        try {
            httpEntity = new HttpEntity<>(request,headers);
            str = restTemplate.postForObject(url,httpEntity,String.class);
            logger.info("用带请求头的json字符串请求进行调用的结果是：[{}]",str);
        } catch (Exception e) {
            logger.error("用带请求头的json字符串请求进行调用进入异常，异常信息为：",e);
        }
        return str;
    }

    /**
     * 以post或get方式调用对方接口方法(原生的http连接类，万金油，resttemplate不好用的时候可以尝试该方法，毕竟该方法是最原始的http调用)
     * @param pathUrl
     */
    public String doPostOrGetByJdk(String pathUrl, String data,String session_id){
        OutputStreamWriter out = null;
        BufferedReader br = null;
        String result = "";
        try {
            URL url = new URL(pathUrl);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //请求方式
            conn.setRequestMethod("POST");
            //conn.setRequestMethod("GET");

            //设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            if (session_id!=null){
                conn.setRequestProperty("session_id",session_id);
//                conn.setConnectTimeout(600*1000);
//                conn.setReadTimeout(600*1000);
            }
            //DoOutput设置是否向httpUrlConnection输出，DoInput设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
            conn.setDoOutput(true);
            conn.setDoInput(true);

            /**
             * 下面的三句代码，就是调用第三方http接口
             */
            //获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            //发送请求参数即数据
            out.write(data);
            //flush输出流的缓冲
            out.flush();

            /**
             * 下面的代码相当于，获取调用第三方http接口后返回的结果
             */
            //获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();
            //构造一个字符流缓存
            br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            while ((str = br.readLine()) != null){
                result += str;
            }
            //关闭流
            is.close();
            //断开连接，disconnect是在底层tcp socket链接空闲时才切断，如果正在被其他线程使用就不切断。
            conn.disconnect();
            return result;
        } catch (Exception e) {
            logger.error("调用网络接口出现异常，异常信息为：",e);
            return null;
        }finally {
            try {
                if (out != null){
                    out.close();
                }
                if (br != null){
                    br.close();
                }
            } catch (IOException e) {
                logger.error("调用网络接口结束，关闭流出现异常，异常信息为：",e);
            }
        }
    }







}

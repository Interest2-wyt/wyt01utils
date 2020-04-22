package com.test.httpmethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public class JdkHttp {
    private static final Logger LOG = LoggerFactory.getLogger(JdkHttp.class);
    //设置连接超时时间
    public static final int CONN_TIMEOUT =600000;
    public static final String CHARSET = "UTF-8";

    /**
     * 公共请求方法
     * @param urlPath
     * @param method
     * @return
     * @throws Exception
     */
    public static String request(String urlPath, String json, String method){
        HttpURLConnection conn = null;
        OutputStream out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL url = new URL(urlPath);
            conn = (HttpURLConnection)url.openConnection();
            if (StringUtils.isEmpty(method)) {
                method = "GET";
            }
            conn.setRequestMethod(method);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setReadTimeout(CONN_TIMEOUT);
            conn.setConnectTimeout(CONN_TIMEOUT);
            conn.setRequestProperty("Charset", CHARSET);
            conn.setRequestProperty("Content-type", "application/json; charset=UTF-8");
            conn.setRequestProperty("Accept-Charset", CHARSET);
            conn.setRequestProperty("contentType", CHARSET);
            // conns.setRequestProperty("X-Auth-Token", "token"); //设置请求的token
            // 处理参数为空的情况
            resoveEmptyParam(json,out,conn);
            // 分别得到整数 200 和 401 。 如果无法识别响应(例如,该响应不是有效的 HTTP)，则返回 -1。
            if (200 == conn.getResponseCode()) {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                String inputLine;
                StringBuffer str = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    str.append(inputLine);
                }
                LOG.info("调用接口返回的信息"+str.toString());
                return str.toString();
            }
        } catch (Exception e) {
            LOG.debug("http请求出现异常", e);
        } finally {
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    /**
     * 公共请求方法
     * @param urlPath
     * @param method
     * @param header 自定义头信息
     * @return
     * @throws Exception
     */
    public static String request(String urlPath, String json, String method, Map<String, String> header) throws Exception {
        HttpURLConnection conn = null;
        OutputStream out = null;
        BufferedReader in = null;
        String result = null;
        try {
            URL url = new URL(urlPath);
            conn = (HttpURLConnection)url.openConnection();
            if (StringUtils.isEmpty(method)) {
                method = "GET";
            }
            conn.setRequestMethod(method);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setReadTimeout(CONN_TIMEOUT);
            conn.setConnectTimeout(CONN_TIMEOUT);
            conn.setRequestProperty("Charset", CHARSET);
            conn.setRequestProperty("Accept-Charset", CHARSET);
            if (null == header || header.size() == 0) {
                // 默认头信息
                conn.setRequestProperty("Content-type", "text/html");
            } else {
                // 自定义头信息
                Iterator<Map.Entry<String, String>> it = header.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, String> entry = it.next();
                    conn.setRequestProperty(entry.getKey(), header.get(entry.getKey()));
                }
            }
            // 处理参数为空的情况
            resoveEmptyParam(json,out,conn);
            // 分别得到整数 200 和 401 。 如果无法识别响应(例如,该响应不是有效的 HTTP)，则返回 -1。
            if (200 == conn.getResponseCode()) {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream(), CHARSET));
                String inputLine;
                StringBuffer str = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    str.append(inputLine);
                }
                return str.toString();
            }
        } catch (Exception e) {
            LOG.error("http请求出现异常", e);
            throw e;
        } finally {
            if (conn != null) {
                try {
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static void resoveEmptyParam(String json,OutputStream out,HttpURLConnection conn) throws Exception{
        if (StringUtils.isEmpty(json)) {
            out = conn.getOutputStream();
            String params = json;
            out.write(params.getBytes("UTF-8"));
            out.flush();
        }
    }
}

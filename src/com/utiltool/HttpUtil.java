package com.utiltool;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

//http请求工具
public class HttpUtil
{
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String _GET = "GET"; // GET
    private static final String _POST = "POST";// POST


    private static HttpUtil instance=null;

    public static HttpUtil getInstance() {
        if (instance==null)
            instance= new HttpUtil();
        return instance;
    }

    /**
     * 初始化http请求参数
     */
    private static HttpURLConnection initHttp(String url, String method, Map<String, String> headers)
            throws IOException
    {
        URL _url = new URL(url);
        HttpURLConnection http = (HttpURLConnection) _url.openConnection();
        // 连接超时
        http.setConnectTimeout(25000);
        // 读取超时 --服务器响应比较慢，增大时�?
        http.setReadTimeout(25000);
        http.setRequestMethod(method);

        if (null != headers && !headers.isEmpty()) {
            for (Entry<String, String> entry : headers.entrySet()) {
                http.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        http.setDoOutput(true);
        http.setDoInput(true);
        http.connect();
        return http;
    }

    /**
     * 初始化http请求参数
     */
    private static HttpsURLConnection initHttps(String url, String method, Map<String, String> headers)
            throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException
    {
        TrustManager[] tm = {new MyX509TrustManager()};
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, tm, new java.security.SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        URL _url = new URL(url);
        HttpsURLConnection http = (HttpsURLConnection) _url.openConnection();
        // 设置域名校验
        http.setHostnameVerifier(new TrustAnyHostnameVerifier());
        http.setSSLSocketFactory(ssf);
        // 连接超时
        http.setConnectTimeout(25000);
        // 读取超时 --服务器响应比较慢，增大时�?
        http.setReadTimeout(50000);
        http.setRequestMethod(method);

        if (null != headers && !headers.isEmpty()) {
            for (Entry<String, String> entry : headers.entrySet()) {
                http.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        http.setDoOutput(true);
        http.setDoInput(true);
        http.connect();
        return http;
    }


    public static String get(String url, Map<String, String> params, Map<String, String> headers) {
        StringBuffer bufferRes = null;
        try {
            HttpURLConnection http = null;
            if (isHttps(url)) {
                http = initHttps(initParams(url, params), _GET, headers);
            } else {
                http = initHttp(initParams(url, params), _GET, headers);
            }

            InputStream in = http.getInputStream();

            byte[] bytes=StringUtil.readStream(in);
            in.close();
            http.disconnect();// 关闭连接

            return StringUtil.UnGzip(bytes);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String get(String url) {
        return get(url, null);
    }


    public static String get(String url, Map<String, String> params) {
        return get(url, params, null);
    }


    public static String post(String url, String params, Map<String, String> headers) {
        /*System.out.println("url: " +url);
        System.out.println("data:" + params);*/
        for (Map.Entry<String,String> entry : headers.entrySet()) {
            System.out.println( entry.getKey() + " :" + entry.getValue());
        }

            StringBuffer bufferRes = null;
        try {
            HttpURLConnection http = null;
            if (isHttps(url)) {
                http = initHttps(url, _POST, headers);
            } else {
                http = initHttp(url, _POST, headers);
            }
            OutputStream out = http.getOutputStream();
            out.write(params.getBytes(DEFAULT_CHARSET));
            out.flush();
            out.close();

            InputStream in = http.getInputStream();

            byte[] bytes=StringUtil.readStream(in);
            in.close();
            http.disconnect();// 关闭连接

            return StringUtil.UnGzip(bytes);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static byte[] postRetByte(String url, String params, Map<String, String> headers) {

        StringBuffer bufferRes = null;
        try {
            HttpURLConnection http = null;
            if (isHttps(url)) {
                http = initHttps(url, _POST, headers);
            } else {
                http = initHttp(url, _POST, headers);
            }
            OutputStream out = http.getOutputStream();
            out.write(params.getBytes(DEFAULT_CHARSET));
            out.flush();
            out.close();
            InputStream in = http.getInputStream();

            return StringUtil.readStream(in);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static byte[] postByte(String url, byte[] params, Map<String, String> headers) {

        try {
            HttpURLConnection http = null;
            if (isHttps(url)) {
                http = initHttps(url, _POST, headers);
            } else {
                http = initHttp(url, _POST, headers);
            }
            OutputStream out = http.getOutputStream();
            out.write(params);
            out.flush();
            out.close();
            InputStream in = http.getInputStream();
            return StringUtil.readStream(in);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String post(String url, Map<String, String> params) {
        return post(url, map2Url(params), null);
    }


    public static String post(String url, Map<String, String> params, Map<String, String> headers) {
        return post(url, map2Url(params), headers);
    }


    public static String initParams(String url, Map<String, String> params) {
        if (null == params || params.isEmpty()) {
            return url;
        }
        StringBuilder sb = new StringBuilder(url);
        if (url.indexOf("?") == -1) {
            sb.append("?");
        }
        sb.append(map2Url(params));
        return sb.toString();
    }


    public static String map2Url(Map<String, String> paramToMap) {
        if (null == paramToMap || paramToMap.isEmpty()) {
            return null;
        }
        StringBuffer url = new StringBuffer();
        boolean isfist = true;
        for (Entry<String, String> entry : paramToMap.entrySet()) {
            if (isfist) {
                isfist = false;
            } else {
                url.append("&");
            }
            url.append(entry.getKey()).append("=");
            String value = entry.getValue();
            if (null == value || "".equals(value.trim())) {
                try {
                    url.append(URLEncoder.encode(value, DEFAULT_CHARSET));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return url.toString();
    }


    private static boolean isHttps(String url) {
        return url.startsWith("https");
    }


    private static class TrustAnyHostnameVerifier implements HostnameVerifier
    {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }


    private static class MyX509TrustManager implements X509TrustManager
    {

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException
        {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException
        {
        }
    }
}


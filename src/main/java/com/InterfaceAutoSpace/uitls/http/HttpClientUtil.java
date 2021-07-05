package com.InterfaceAutoSpace.uitls.http;


/*
发送请求
1、初始化http连接；
2、定义URL；
3、定义请求对象；
4、发送请求；
5、关闭连接
 */


import com.InterfaceAutoSpace.model.http.HttpClientRequest;
import com.InterfaceAutoSpace.model.http.HttpClientResponse;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class HttpClientUtil {
    private static Logger logger = Logger.getLogger(HttpClientUtil.class);
    private CloseableHttpClient httpClient;

    public static HttpClientResponse doGet(HttpClientRequest httpClientRequest){
        System.out.println("开始执行get请求");
        HttpClientUtil httpClientUtil= new HttpClientUtil();
        httpClientUtil.init();

        HttpGet get = new HttpGet(httpClientRequest.getUrl());
        return httpClientUtil.sendRequest(get,httpClientRequest);
    }

    public static HttpClientResponse doPost(HttpClientRequest httpClientRequest){
        System.out.println("开始执行post请求");
        HttpClientUtil httpClientUtil= new HttpClientUtil();
        httpClientUtil.init();

        HttpPost httpPost = new HttpPost(httpClientRequest.getUrl());
        return httpClientUtil.sendRequest(httpPost,httpClientRequest);
    }

    private void init(){
        httpClient=HttpClientBuilder.create().build();
        logger.info("初始化");
        System.out.println("初始化");

    }

    private HttpClientResponse sendRequest(HttpRequestBase httpRequestBase, HttpClientRequest httpClientRequest){
        HttpClientResponse httpClientResponse = new HttpClientResponse();

        System.out.println("sendRequest方法 ");


        //String url = httpClientRequest.getUrl();
        //HttpUriRequest post = new HttpPost(url);


        Map<String,String> headers = httpClientRequest.getHeaders();
        for(String key:headers.keySet()){
            httpRequestBase.setHeader(key,headers.get(key));
            System.out.println("打印header："+headers.get(key));
        }



        //设置请求body
        try{
            if (httpRequestBase instanceof HttpEntityEnclosingRequestBase){
             ((HttpEntityEnclosingRequestBase) httpRequestBase).setEntity(new StringEntity(httpClientRequest.getBody()));
            }
        }catch (UnsupportedEncodingException e1){
            e1.printStackTrace();
        }

        System.out.println("发送请求hahahh ");



        //发送请求
        try{
            CloseableHttpResponse response = httpClient.execute(httpRequestBase);

            //处理response
            //statusCode
            String statusCode = response.getStatusLine().toString().split(" ")[1];
            httpClientResponse.setStatusCode(statusCode);
            System.out.println("响应码："+statusCode);


            //响应header
            Header[] reHeaders = response.getAllHeaders();
            Map<String,String> responseHeaders = new HashMap<String,String>();
            for(Header header : reHeaders){
                logger.info(header.getName()+":"+ header.getValue());
                responseHeaders.put(header.getName(),header.getValue());
            }
            httpClientResponse.setHeaders(responseHeaders);

            //响应body
            HttpEntity entity = response.getEntity();

            String body = EntityUtils.toString(entity, "utf-8");
            httpClientResponse.setBody(body);
            System.out.println("body:"+body);


            this.close();


        } catch (ClientProtocolException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return httpClientResponse;

    }

    private void close(){
        try {
            httpClient.close();
            logger.info("成功关闭连接");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }


}

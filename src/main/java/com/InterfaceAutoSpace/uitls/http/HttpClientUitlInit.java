package com.InterfaceAutoSpace.uitls.http;

import com.InterfaceAutoSpace.model.http.HttpClientRequest;
import com.InterfaceAutoSpace.model.http.HttpClientResponse;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class HttpClientUitlInit {

    private static Logger logger = Logger.getLogger("");

    /*步骤
    1、初始化http连接；
    2、定义URl；
    3、定义请求消息对象；
    4、发送请求；
    5、关闭连接。
     */
    public void testMethod(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String url = "https://api.66mz8.com/api/translation.php?info=I%20come%20from%20China&appkey=2d4c769bf1776bb280fa6a567775eea1";
        HttpGet get = new HttpGet(url);
        try{
            CloseableHttpResponse response = httpClient.execute(get);

            HttpEntity entity = response.getEntity();
            String result = null;
            if (entity != null) {
                result = EntityUtils.toString(entity, "utf-8");
                System.out.println(result);
            }


                httpClient.close();
        } catch (ClientProtocolException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }


    }

    public void testMethodPost(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String url = "https://way.jd.com/he/freeweather?city=beijing&appkey=2d4c769bf1776bb280fa6a567775eea1";
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");

        try{
            post.setEntity(new StringEntity("{\"info\":\"i am mary\",\n" +
                    "\"appkey\":\"2d4c769bf1776bb280fa6a567775eea1\"}"));

        }catch (UnsupportedEncodingException e1){
            e1.printStackTrace();
        }
        try{

            CloseableHttpResponse response = httpClient.execute(post);

            logger.info(response.getStatusLine().toString().split(" ")[1]);
            Header[] headers = response.getAllHeaders();
            for(Header header : headers){
                logger.info(header.getName()+":"+ header.getValue());
            }

            HttpEntity entity = response.getEntity();
            String body = entity.getContent().toString();
            //logger.info(body);

            System.out.println("body:"+body);
            httpClient.close();
        } catch (ClientProtocolException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public HttpClientResponse doPost(HttpClientRequest request){
        HttpClientResponse httpClientResponse = new HttpClientResponse();

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String url = request.getUrl();
        HttpPost post = new HttpPost(url);

        Map<String,String> headers = request.getHeaders();
        for(String key:headers.keySet()){
            post.setHeader(key,headers.get(key));
        }

        //设置请求body
        try{
            ((HttpPost) post).setEntity(new StringEntity(request.getBody()));
        }catch (UnsupportedEncodingException e1){
            e1.printStackTrace();
        }

        //处理response
        try{
            CloseableHttpResponse response = httpClient.execute(post);

            //statusCode
            String statusCode = response.getStatusLine().toString().split(" ")[1];
            httpClientResponse.setStatusCode(statusCode);
            System.out.println(statusCode);


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
            String body = entity.getContent().toString();
            httpClientResponse.setBody(body);

            //logger.info(body);
            System.out.println(body);

            httpClient.close();


        } catch (ClientProtocolException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return httpClientResponse;
    }

    public static void main(String[] args){
        HttpClientUitlInit util = new HttpClientUitlInit();
        //util.testMethod();
        //util.testMethodPost();
        HttpClientRequest request=new HttpClientRequest();
        request.setUrl("https://way.jd.com/he/freeweather?city=beijing&appkey=2d4c769bf1776bb280fa6a567775eea1");

        Map<String,String> requestHeaders=new HashMap<String,String>();
        requestHeaders.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        request.setHeaders(requestHeaders);

        request.setBody("");

        //util.doPost(request);





    }








































}

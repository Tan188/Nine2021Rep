package com.InterfaceAutoSpace.uitls.http;

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
import java.util.logging.Logger;

public class HttpClientUitl {

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
        String url = "https://way.jd.com/he/freeweather";
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
                //logger.info(header.getName()+":"+ header.getValue());
            }

            HttpEntity entity = response.getEntity();
            String body = entity.getContent().toString();
            //logger.info(body);
            System.out.println(body);

            httpClient.close();
        } catch (ClientProtocolException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        HttpClientUitl util = new HttpClientUitl();
        util.testMethod();
        //util.testMethodPost();

    }








































}

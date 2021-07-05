package interfaceAutoSpace;

import com.InterfaceAutoSpace.model.http.HttpClientRequest;
import com.InterfaceAutoSpace.model.http.HttpClientResponse;
import com.InterfaceAutoSpace.uitls.http.HttpClientUtil;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class HttpClienUtilTest {

    public static void main(String[] args){

        HttpClientRequest request=new HttpClientRequest();
        request.setUrl("https://api.66mz8.com/api/translation.php?info=I%20come%20from%20China&appkey=2d4c769bf1776bb280fa6a567775eea1");

        Map<String,String> requestHeaders=new HashMap<String,String>();
        requestHeaders.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        request.setHeaders(requestHeaders);

        request.setBody("{}");

        HttpClientResponse response = HttpClientUtil.doPost(request);
        System.out.println("响应body:"+response.getBody());
    }

    @Test
    public void testMethod(){




    }


}

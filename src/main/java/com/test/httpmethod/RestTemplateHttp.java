package com.test.httpmethod;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName RestTemplateHttp
 * @Description @TODO
 * @Author wangyongtao
 * @Date 2019/12/30 21:30
 * @Version 1.0
 */

public class RestTemplateHttp {
    public static void main(String[] args) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(15000);
        factory.setReadTimeout(5000);
        RestTemplate restTemplate = new RestTemplate(factory);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://localhost:17001/springbdemo/visit/getString", String.class);
        System.out.println(responseEntity.getBody());
        String str = restTemplate.postForObject("http://localhost:17001/springbdemo/visit/comprosizeBody",new TwoInfo("123","456"),String.class);
        System.out.println(str);
    }
}

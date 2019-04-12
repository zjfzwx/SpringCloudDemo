package com.itlaoqi.springcloud.member.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name="book") //book是service-id , @FeignClient(name="book")指明这是Book微服务的调用客户端
//Feign默认就支持Ribbon负载均衡，我们只需要部署多个book微服务后，客户端便可自动进行负载均衡选择
public interface BookClient {
    @GetMapping("/info") //当调用getInfo方法的时候，自动向book微服务的/info发起请求
    //调用时自动会将?bid=xxx附加到url中
    public RestResult getInfo(@RequestParam("bid") Long bid);

    /*Get请求对应GetMapping
        Get请求使用RequestParam注解发送参数
    Post 请求对应PostMapping
        @RequestBody
    @PostMapping("/create")
    public String create(@RequestBody Map rec);*/

}

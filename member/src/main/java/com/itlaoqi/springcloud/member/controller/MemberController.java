package com.itlaoqi.springcloud.member.controller;

import com.itlaoqi.springcloud.member.client.BookClient;
import com.itlaoqi.springcloud.member.client.RestResult;
import com.itlaoqi.springcloud.member.entity.Member;
import com.itlaoqi.springcloud.member.service.MemberService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

//在浏览器中如果向其他域名（端口不一样也算其他域名）的url发送请求，浏览器自身的安全策略是不允许的
//我们需要设置CORS（跨域访问），来解决跨域问题
@Controller
@CrossOrigin //在Controller上利用CrossOrigin解决跨域访问问题
@DefaultProperties(defaultFallback = "defaultFallBack")
public class MemberController {

    @Resource
    private MemberService memberService;
    //这是负载均衡客户端 , 这是Ribbon的核心组件
//    @Resource
//    private LoadBalancerClient loadBalancerClient;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private BookClient bookClient;

    @GetMapping("/check")
    @ResponseBody
    public Map checkMobile(String mobile) {
        Map result = new HashMap();
        try {
            result.put("code", "0");
            result.put("message", "success");
            Member member = memberService.checkByMobile(mobile);
            result.put("data" , member);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code", e.getClass().getSimpleName());
            result.put("message", e.getMessage());
        }
        return result;
    }
    @GetMapping("/test")
    @ResponseBody
    //@HystrixCommand(fallbackMethod = "fallback")
    @HystrixCommand
    public String test(Long bid){
        //1. 最简单的情况是使用SpringCloud内置的RestTemplate对象(不推荐)
        //RestTemplate底层进行的Http传输就是使用的Apache HttpClient组件
//        RestTemplate restTemplate = new RestTemplate();
//        String json =  restTemplate.getForObject("http://localhost:8100/info?bid=" + bid , String.class);
        //2. 采用Ribbon进行客户端负载均衡（讲解原理，但并不适用这种方式）
        /*RestTemplate restTemplate = new RestTemplate();
        //获取服务列表,提供负载均衡
        ServiceInstance serviceInstance = loadBalancerClient.choose("book");
        String host = serviceInstance.getHost();//获取主机名
        Integer port = serviceInstance.getPort(); //获取端口号
        String json = restTemplate.getForObject("http://" + host + ":"+ port +"/info?bid=" + bid , String.class);
        */
        //3，利用注解简化URL通信
        //利用注解开发的时候，URL的主机名端口号都要换成serviceid
        String json = null;
        if(bid % 2 == 0) {
            json = restTemplate.getForObject("http://book/info?bid=" + bid, String.class);
        }else{
            json = "success";
        }
        return json;
    }
    //服务降级的方法要求返回值、参数与目标方法保持一致
    private String fallback(Long bid){
        return "当前系统正忙，请稍后再试";
    }

    @GetMapping("/test1")
    @ResponseBody
    public String test1(Long bid){
        RestResult rr = bookClient.getInfo(bid);
        return rr.getData().getName();
    }

    //全局默认的降级方法，不需要参数，且返回String或者任何可以被JSON序列化的对象
    //在默认情况下，Hystrix默认的timeout时间为1s
    private String defaultFallBack(){
        return "[默认系统降级]当前系统正忙，请稍后再试";
    }

}

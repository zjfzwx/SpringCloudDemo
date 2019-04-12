package com.itlaoqi.springcloud.book.controller;

import com.itlaoqi.springcloud.book.entity.Book;
import com.itlaoqi.springcloud.book.service.BookService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController //创建Rest风格的Controller控制器，这意味着Controller中所有的方法将返回json
//就不用再写@ResponseBody
@CrossOrigin
public class BookController {
    @Resource
    private BookService bookService;

    @GetMapping("/list")
    private Map list(){
        Map result = new HashMap();
        try{
            List list = bookService.findAll();
            result.put("code" , "0");
            result.put("message" , "success");
            result.put("data" , list);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code" , e.getClass().getSimpleName());
            result.put("message" , e.getMessage());
        }

        return result;
    }

    @GetMapping("/info")
    private Map info(Long bid){
        /*try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        Map result = new HashMap();
        try{
            Book book = bookService.getInfo(bid);
            result.put("code" , "0");
            result.put("message" , "success");
            result.put("data" , book);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code" , e.getClass().getSimpleName());
            result.put("message" , e.getMessage());
        }
        return result;
    }

    @RequestMapping("/borrow")
    public Map borrow(String mobile , Long bid , String takedate , String returndate){
        Date tdate = null;
        Date rdate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            tdate = sdf.parse(takedate);
            rdate = sdf.parse(returndate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Map result = new HashMap();
        try{
            bookService.borrow(bid , mobile,tdate , rdate);
            result.put("code" , "0");
            result.put("message" , "success");
        }catch (Exception e){
            e.printStackTrace();
            result.put("code", e.getClass().getName());
            result.put("message", e.getMessage());
        }
        return result;
    }
}

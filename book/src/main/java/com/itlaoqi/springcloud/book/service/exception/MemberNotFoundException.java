package com.itlaoqi.springcloud.book.service.exception;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException(String msg){
        super(msg);
    }
}

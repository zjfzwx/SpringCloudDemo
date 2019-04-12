package com.itlaoqi.springcloud.book.service.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String msg){
        super(msg);
    }
}

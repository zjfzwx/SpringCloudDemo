package com.itlaoqi.springcloud.member.service.exception;

public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(String msg) {
        super(msg);
    }
}

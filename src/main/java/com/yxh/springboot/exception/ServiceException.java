package com.yxh.springboot.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
public class ServiceException extends RuntimeException {
    private String code;
    public ServiceException(String code,String msg){
        super(msg);
        this.code=code;
    }
}

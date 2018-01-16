package com.meow.proxy.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class Request {
    
	/**请求方法*/
    private String method;

    /**网页编码方式*/
    private String charSet;
    
    /**请求头信息*/
    private Map<String, String> headers = new HashMap<String, String>();

    /**post的一些参数*/
    Map<String, Object> params = new HashMap<>();
}

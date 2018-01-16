package com.meow.proxy.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {

	private String url;

	private int statusCode;

	private String content;
}

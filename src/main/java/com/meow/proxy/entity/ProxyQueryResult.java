package com.meow.proxy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProxyQueryResult implements Serializable {
	private static final long serialVersionUID = 2852922997233474219L;
	
	/**请求状态(success/failed)*/
	private String status = "failed";
	
	/**返回的代理数量*/
	private int resProxyCount;
	
	/**代理总数*/
	private int totalProxyCount;
	
	/**返回的代理详情*/
	private List<Proxy> proxies = new ArrayList<Proxy>(200);
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}

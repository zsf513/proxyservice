package com.meow.proxy.entity;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class Proxy {
	private Integer id;
	
	private String ip;
	
	private int port;

	/** 代理匿名类型 */
	private String anonymousType;

	/** 代理协议类型 */
	private String protocolType;

	/** 代理所在国家 */
	private String country;

	/** 代理所在地区 */
	private String area;

	/** 是否有效 */
	private boolean valid;

	/** 代理失效时间(时间戳) */
	private Long invalidTime;

	/** 上次存活时长 */
	private Long lastSurviveTime;

	/** 代理验证时间 */
	private Long checkTime;

	/** 代理验证状态(0:未验证；1:已验证) */
	private Integer checkStatus;

	/** 代理评分 */
	private float score;

	/** 代理来源站点 */
	private String sourceSite;

	/** 代理有效次数 */
	private Integer validTime;

	/** 代理采集时间 */
	private Long crawlTime;

	/** 代理响应时间 */
	private Long responseTime;

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}

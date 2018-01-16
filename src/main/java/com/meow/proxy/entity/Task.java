package com.meow.proxy.entity;

import com.alibaba.fastjson.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Task {
	
	/**任務url*/
	private String url;
	
	/**是否翻頁採集*/
	private boolean subPageCrawl;
	
	/**翻頁數量*/
	private int subPageSize;
	
	/**下載類class路徑*/
	private String downLoadClassName;
	
	/**抽取类class路径*/
	private String extractClassName;
	
	/**站点名称*/
	private String siteName;

	public String toString() {
		return JSONObject.toJSONString(this);
	}

	public Task(String url, boolean subPageCrawl, int subPageSize, String downLoadClassName, String extractClassName,
			String siteName) {
		this.url = url;
		this.subPageCrawl = subPageCrawl;
		this.subPageSize = subPageSize;
		this.downLoadClassName = downLoadClassName;
		this.extractClassName = extractClassName;
		this.siteName = siteName;
	}
}

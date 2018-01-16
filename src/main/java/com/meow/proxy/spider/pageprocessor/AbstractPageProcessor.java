package com.meow.proxy.spider.pageprocessor;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meow.proxy.enums.ProxyAnonymousType;
import com.meow.proxy.spider.UrlSelector;

import us.codecraft.webmagic.Site;

/**
 * @author zhushunfu
 *
 */
public abstract class AbstractPageProcessor implements UrlSelector {

	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);
	
	protected final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Override
	public Site getSite() {
		return site;
	}
	
	/**
	 * 代理匿名类型清洗
	 *
	 * @param str
	 * @return
	 */
	protected String getAnonymousType(String str) {
		String result = str;
		if (StringUtils.isNoneBlank(str)) {
			switch (str) {
			case "高匿":
				result = ProxyAnonymousType.elite.getAnonymousType();
				break;
			case "透明":
				result = ProxyAnonymousType.transparent.getAnonymousType();
				break;
			default:
				LOG.error("Can not verify the anonymousType of proxy from XiciDaili>>>:" + str);
			}
		}
		return result;
	}

}

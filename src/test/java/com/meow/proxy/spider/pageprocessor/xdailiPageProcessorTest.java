package com.meow.proxy.spider.pageprocessor;

import org.junit.Test;

import com.meow.proxy.AbstractJunitTest;
import com.meow.proxy.spider.UrlSelector;
import com.meow.proxy.spider.pipeline.DbPipeline;

import us.codecraft.webmagic.Spider;

/**
 * @author zhushunfu
 *
 */
public class xdailiPageProcessorTest extends AbstractJunitTest{

	@Test
	public void test(){
		UrlSelector urlSelector = new XdailiPageProcessor();
		Spider.create(urlSelector).addPipeline(new DbPipeline()).addUrl(urlSelector.urls()).run();
	}
}

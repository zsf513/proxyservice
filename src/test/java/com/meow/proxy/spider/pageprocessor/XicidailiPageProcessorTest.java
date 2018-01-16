package com.meow.proxy.spider.pageprocessor;

import org.junit.Test;

import com.meow.proxy.AbstractJunitTest;
import com.meow.proxy.enums.ProxySite;
import com.meow.proxy.spider.pipeline.DbPipeline;

import us.codecraft.webmagic.Spider;

/**
 * @author zhushunfu
 *
 */
public class XicidailiPageProcessorTest extends AbstractJunitTest{

	@Test
	public void test(){
		Spider.create(new XicidailiPageProcessor()).addPipeline(new DbPipeline()).addUrl(ProxySite.xicidaili.getProxySiteDomain()).run();
	}
}

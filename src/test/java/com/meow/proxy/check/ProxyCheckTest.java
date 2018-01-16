package com.meow.proxy.check;

import org.apache.http.HttpHost;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProxyCheckTest {

	Logger log = LoggerFactory.getLogger(getClass());

	ProxyCheck proxyCheck = null;

	@Before
	public void init() {
		proxyCheck = ProxyCheck.getInstance();
	}

	@Test
	public void checkProxy() {
		// HttpHost httpHost = new HttpHost("113.218.191.170",8888);
		// HttpHost httpHost = new HttpHost("223.241.119.16",8180);
		// HttpHost httpHost = new HttpHost("121.31.103.33",6666);
		// HttpHost httpHost = new HttpHost("139.59.169.81",8118);
		HttpHost httpHost = new HttpHost("218.66.151.90", 29988);
		log.info("结果:{}", proxyCheck.checkProxyBySocket(httpHost, false));
	}
}

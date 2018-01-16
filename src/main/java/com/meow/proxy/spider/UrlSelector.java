package com.meow.proxy.spider;

import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @author zhushunfu
 *
 */
public interface UrlSelector extends PageProcessor {

	String[] urls();
}

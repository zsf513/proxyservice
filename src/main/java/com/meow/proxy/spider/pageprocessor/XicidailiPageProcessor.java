package com.meow.proxy.spider.pageprocessor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import com.meow.proxy.entity.Proxy;
import com.meow.proxy.enums.CountryType;
import com.meow.proxy.enums.ProxySite;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.selector.Selectable;

/**
 * @author zhushunfu
 *
 */
@Component
public class XicidailiPageProcessor extends AbstractPageProcessor {

	@Override
	public void process(Page page) {
		List<Selectable> selects = page.getHtml().xpath("//table[@id=ip_list]").$("tr:gt(1)").nodes();
		List<Proxy> proxyList = new ArrayList<Proxy>();
		List<String> links = page.getHtml().xpath("//ul[@id=\"nav\"]/li/a[@class=\"false\" and @href!=\"/articles/\"]")
				.links().all();
		page.addTargetRequests(links);

		for (Selectable select : selects) {
			String ip = select.xpath("/tr//td[2]/text()").toString();
			int port = NumberUtils.toInt(select.xpath("/tr//td[3]/text()").toString());
			String area = select.xpath("/tr//td[4]/text()").toString();
			String anonymousType = getAnonymousType(select.xpath("/tr//td[5]/text()").toString());
			String protocol = select.xpath("/tr//td[6]/text()").toString();

			Proxy proxy = new Proxy();
			proxy.setCountry(CountryType.china.getCountryName());
			proxy.setIp(ip);
			proxy.setPort(port);
			proxy.setArea(area);
			proxy.setCheckStatus(0);
			proxy.setAnonymousType(anonymousType);
			proxy.setProtocolType(protocol);
			proxy.setSourceSite(ProxySite.xicidaili.getProxySiteName());

			proxy.setCheckTime(System.currentTimeMillis());
			proxy.setCrawlTime(System.currentTimeMillis());
			proxy.setValidTime(1);
			proxy.setLastSurviveTime(-1L);
			proxy.setInvalidTime(-1L);
			proxy.setValid(false);
			proxyList.add(proxy);
		}

		page.putField("proxyList", proxyList);
	}

	@Override
	public Site getSite() {
		return super.getSite().setDomain(ProxySite.xicidaili.getProxySiteDomain());
	}
	
	@Override
	public String[] urls() {
		return new String[] { "http://"+ProxySite.xicidaili.getProxySiteDomain() };
	}
}

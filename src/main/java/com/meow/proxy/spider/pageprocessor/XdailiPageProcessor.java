package com.meow.proxy.spider.pageprocessor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
public class XdailiPageProcessor extends AbstractPageProcessor {

	@Override
	public void process(Page page) {
		List<Selectable> rows = page.getJson().jsonPath("$.RESULT.rows").nodes();
		List<Proxy> proxyList = new ArrayList<Proxy>();
		for(Selectable row : rows){
			Proxy proxy = handleRow(row);
			if(proxy != null){
				proxyList.add(proxy);
			}
		}
		page.putField("proxyList", proxyList);
	}

	private Proxy handleRow(Selectable row){
		String jsonStr = row.get();
		JSONObject jsonObject = JSON.parseObject(jsonStr);
		String ip = jsonObject.getString("ip");
		int port = jsonObject.getIntValue("port");
		
		Proxy proxy = new Proxy();
		proxy.setIp(ip);
		proxy.setPort(port);		
		proxy.setCountry(CountryType.china.getCountryName());
		proxy.setArea(jsonObject.getString("position"));
		proxy.setCheckStatus(0);
		proxy.setAnonymousType(getAnonymousType(jsonObject.getString("anony")));
		proxy.setProtocolType(jsonObject.getString("type"));
		proxy.setSourceSite(ProxySite.xdaili.getProxySiteName());

		proxy.setCheckTime(System.currentTimeMillis());
		proxy.setCrawlTime(System.currentTimeMillis());
		proxy.setValidTime(1);
		proxy.setLastSurviveTime(-1L);
		proxy.setInvalidTime(-1L);
		proxy.setValid(false);
		
		return proxy;
	}
	
	@Override
	public Site getSite() {
		return super.getSite().setDomain(ProxySite.xdaili.getProxySiteDomain());
	}
	
	@Override
	public String[] urls() {
		return new String[] {"http://"+ProxySite.xdaili.getProxySiteDomain()+"/ipagent/freeip/getFreeIps" };
	}

}

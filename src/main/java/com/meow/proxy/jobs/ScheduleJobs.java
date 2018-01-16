package com.meow.proxy.jobs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.meow.proxy.appcontext.AppcontextUtil;
import com.meow.proxy.check.ProxyRecheckSender;
import com.meow.proxy.entity.Proxy;
import com.meow.proxy.service.ProxyService;
import com.meow.proxy.spider.UrlSelector;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;

@Component
public class ScheduleJobs {
    private final static Logger LOG = LoggerFactory.getLogger(ScheduleJobs.class);
   
    @Autowired
    ProxyService proxyService;
    
    @Autowired
    ProxyRecheckSender proxyRecheckSender;

    @Autowired
	Pipeline dbPipeline;
    
    @Scheduled(fixedRateString = "${com.meow.proxy.jobs.ScheduleJobs.proxyCrawl.period}")
    public void proxyCrawl() {
    	for(Spider spider : getSpiders()){
    		spider.start();
    	}
    }
    
    //@Scheduled(fixedRateString = "${com.meow.proxy.jobs.ScheduleJobs.proxyRecheck.period}")
    public void proxyRecheck() {
        long begin = System.currentTimeMillis();
        List<Proxy> proxyList = proxyService.queryValidProxies();
        proxyRecheckSender.sendRecheckProxies(proxyList);
        LOG.info("可用代理检测完成，用时: " + (System.currentTimeMillis() - begin) + " ms");
    }

    private List<Spider> getSpiders(){
		Map<String,UrlSelector> map = AppcontextUtil.getContext().getBeansOfType(UrlSelector.class);
		List<Spider> spiders = new ArrayList<Spider>();
		for(Map.Entry<String,UrlSelector> entry : map.entrySet()){
			UrlSelector urlSelector = entry.getValue();
			spiders.add(Spider.create(urlSelector).addPipeline(dbPipeline).addUrl(urlSelector.urls()));			
		}
		return spiders;
	}
}



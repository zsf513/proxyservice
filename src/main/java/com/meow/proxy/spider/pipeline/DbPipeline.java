package com.meow.proxy.spider.pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.meow.proxy.entity.Proxy;
import com.meow.proxy.service.ProxyService;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @author zhushunfu
 *
 */
@Component
public class DbPipeline implements Pipeline {

	@Autowired
	ProxyService proxyService;
	
	@Override
	public void process(ResultItems resultItems, Task task) {
		Map<String,Object> fieldMap = resultItems.getAll();
		@SuppressWarnings("unchecked")
		List<Proxy> proxyList = (List<Proxy>) fieldMap.get("proxyList");
		List<Proxy> newProxyList = new ArrayList<Proxy>(); 
		if(CollectionUtils.isNotEmpty(proxyList)){
			for(Proxy item : proxyList){
				Proxy proxy = proxyService.getByIpAndPort(item.getIp(),item.getPort());
				if(proxy == null){
					newProxyList.add(item);
				}
			}
		}
		if(newProxyList.size() > 0){
			proxyService.saveProxies(newProxyList);
		}
	}

	
}

package com.meow.proxy.enums;

/**
 * Created by Jwnie on 2017/12/16.
 */
public enum ProxySite {
    xicidaili("www.xicidaili.com", "西刺免费代理IP"),
    goubanjia("www.goubanjia.com", "全网代理IP"),
    ip3366("www.ip3366.net", "云代理IP"),
    freeProxyList("free-proxy-list.net", "Free Proxy List"),
    data5u("www.data5u.com", "无忧代理IP"),
    xdaili("www.xdaili.cn", "讯代理IP"),
    nianshao("www.nianshao.me", "年少HTTP PROXY"),
    proxydb("proxydb.net", "proxydb"),
    kxdaili("kxdaili.com", "开心代理"),
    coderbusy("proxy.coderbusy.com", "coderbusy"),

    ;

    private String proxySiteDomain;
    
    private String proxySiteName;

    public String getProxySiteDomain() {
        return proxySiteDomain;
    }

    public String getProxySiteName() {
        return proxySiteName;
    }

    ProxySite(String proxySiteDomain, String proxySiteName) {
        this.proxySiteDomain = proxySiteDomain;
        this.proxySiteName = proxySiteName;
    }
}

package com.meow.proxy.dao;

import com.meow.proxy.entity.Proxy;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author Alex
 *         date:2017/12/18
 *         email:jwnie@foxmail.com
 */
public interface ProxyDao {

    void saveProxies(List<Proxy> proxyList);

    void updateProxies(List<Proxy> proxyList);

    List<Proxy> queryValidProxies();

    List<Proxy> queryAll();

    /**
     * 查询前一百条有效的代理
     *
     * @return
     */
    List<Proxy> queryProxy(@Param("protocolType") String protocolType,@Param("isDemostic") String isDemostic,@Param("anonymousType") String anonymousType);

    List<Map<String,String>> proxyStatisticBySite();

    int queryValidProxyCount(@Param("protocolType") String protocolType,@Param("isDemostic") String isDemostic,@Param("anonymousType") String anonymousType);

    @Select("SELECT * FROM cfg_proxy WHERE ip=#{ip} AND port=#{port}")
    Proxy queryByIpAndPort(@Param("ip")String ip,@Param("port")int port);
}

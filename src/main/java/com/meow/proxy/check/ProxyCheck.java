package com.meow.proxy.check;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.http.HttpHost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meow.proxy.deduplicate.SimpleBloomFilter;

public class ProxyCheck {

	private final static Logger LOG = LoggerFactory.getLogger(ProxyCheck.class);

	private final static int CHECKPROXY_TIMEOUT = 30000;
	/**
	 * 使用布隆过滤器进行去重
	 */
	private SimpleBloomFilter simpleBloomFilter = SimpleBloomFilter.getInstance();

	public static ProxyCheck getInstance() {
		return ProxyCheckSingleton.PROXY_CHECK;
	}

	/**
	 * 检测代理前检查之前是否已经爬取过
	 *
	 * @param proxy
	 * @return
	 */
	private boolean isHadCheck(HttpHost proxy) {
		String value = new StringBuilder().append(proxy.getHostName()).append(":").append(proxy.getPort()).toString();
		if (simpleBloomFilter.contains(value)) {
			// LOG.info("已经检验过的代理: " + value);
			return true;
		}
		return false;
	}

	/**
	 * 检测过的代理加入布隆过滤器
	 *
	 * @param proxy
	 */
	private void addChecked(HttpHost proxy) {
		String value = new StringBuilder().append(proxy.getHostName()).append(":").append(proxy.getPort()).toString();
		simpleBloomFilter.add(value);
	}

	/**
	 * @param proxy
	 * @return
	 */
	public boolean checkProxyBySocket(HttpHost proxy, boolean deduplicate) {
		if (proxy == null) {
			return false;
		}
		if (deduplicate) {
			if (isHadCheck(proxy)) {
				return false;
			}
		}
		Socket socket = null;
		try {
			// 失败重试三次
			for (int i = 0; i < 2; i++) {
				try {
					socket = new Socket();
					InetSocketAddress endpointSocketAddr = new InetSocketAddress(proxy.getHostName(), proxy.getPort());
					socket.connect(endpointSocketAddr, CHECKPROXY_TIMEOUT);
					return true;
				} catch (Exception e) {
					LOG.warn("连接失败, remote: " + proxy.getHostName() + ":" + proxy.getPort());
				} finally {
					if (socket != null) {
						try {
							socket.close();
						} catch (IOException e) {
							LOG.warn("Socket关闭异常：", e);
						}
					}
				}
			}
			return false;
		} finally {
			if (deduplicate) {
				addChecked(proxy);
			}
		}
	}

	private static class ProxyCheckSingleton {
		private final static ProxyCheck PROXY_CHECK = new ProxyCheck();
	}

}

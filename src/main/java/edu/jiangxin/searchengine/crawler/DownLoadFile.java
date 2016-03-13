package edu.jiangxin.searchengine.crawler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;

import edu.jiangxin.searchengine.constant.Constant;

/**
 * 描述：根据URL下载网页到本地. 作者：蒋鑫
 **/
public class DownLoadFile {

	// 根据 url 和网页类型生成需要保存的网页的文件名 去除掉 url 中非文件名字符
	public String getFileNameByUrl(String url) {
		url = StringUtils.removeStartIgnoreCase(url, "https://");
		url = StringUtils.removeStartIgnoreCase(url, "http://");
		url = url.replaceAll("[\\?/:*|<>\"]", "_"); // 将特殊字符替换，以生成合法的本地文件名
		return url;
	}

	// 保存网页字节数组到本地文件 filePath 为要保存的文件的相对地址
	private void saveToLocal(InputStream data, String filePath) {
		try {
			File result = new File(filePath);
			result.getParentFile().mkdirs();
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(result));
			BufferedInputStream in = new BufferedInputStream(data);
			int r;
			while ((r = in.read()) != -1) {
				out.write((byte) r);
			}
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 下载 url 指向的网页
	public String downloadFile(String url) {

		String filePath = null;
		HttpRequestRetryHandler myRetryHandler = new HttpRequestRetryHandler() {

			@Override
			public boolean retryRequest(final IOException arg0, int executionCount, final HttpContext arg2) {
				if (executionCount > 5) { // 最多重试5次
					return false;
				}
				return false;
			}
		};
		CloseableHttpClient httpClient = HttpClients.custom().setRetryHandler(myRetryHandler).build(); // 生成
																										// HttpClinet
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(Constant.DEFAULT_CONNECT_TIMEOUT)
				.setConnectionRequestTimeout(Constant.DEFAULT_CONNECTION_REQUEST_TIMEOUT).build();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requestConfig);
		CloseableHttpResponse response;
		try {
			response = httpClient.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + statusLine);
				filePath = null;
			}
			HttpEntity entity = response.getEntity();
			if (entity != null) { // 处理流内容
				InputStream entityContent = entity.getContent();
				filePath = "target/html/" + getFileNameByUrl(url);
				saveToLocal(entityContent, filePath);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("成功下载文件" + filePath + "到本地"); // not very bad
		return filePath;
	}

}

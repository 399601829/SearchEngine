/**
 * ����������URL������ҳ������
 * ���ߣ�����
**/
package Crawler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.StatusLine;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.*;

public class DownLoadFile {
	
	//���� url ����ҳ����������Ҫ�������ҳ���ļ��� ȥ���� url �з��ļ����ַ�
	public  String getFileNameByUrl(String url) {
		url=url.substring(7); //remove http://
		url= url.replaceAll("[\\?/:*|<>\"]", "_"); //�������ַ��滻�������ɺϷ��ı����ļ���
		return url;
	}

	//������ҳ�ֽ����鵽�����ļ� filePath ΪҪ������ļ�����Ե�ַ
	private void saveToLocal(InputStream data, String filePath) {
		try {
			File result = new File(filePath);
			result.getParentFile().mkdirs();
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(result));
			BufferedInputStream in = new BufferedInputStream(data);
			int r;
			while((r=in.read())!=-1) {
				out.write((byte)r);
			}
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//���� url ָ�����ҳ 
	public String downloadFile(String url) {
		
		String filePath = null;
		HttpRequestRetryHandler myRetryHandler =  new HttpRequestRetryHandler() {

			@Override
			public boolean retryRequest(IOException arg0, int executionCount,
					HttpContext arg2) {
				if(executionCount > 5) { //�������5��
					return false;
				}
				return false;
			}
		};
		CloseableHttpClient httpClient = HttpClients.custom()
				.setRetryHandler(myRetryHandler)
				.build(); //���� HttpClinet
		RequestConfig requestConfig = RequestConfig.custom()
				.setConnectTimeout(5000)
				.setConnectionRequestTimeout(5000)
				.build();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requestConfig);
		CloseableHttpResponse response;
		try {
			response = httpClient.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			if(statusLine.getStatusCode()!=HttpStatus.SC_OK) {
				System.err.println("Method failed: " + statusLine);
				filePath = null;
			}
			HttpEntity entity = response.getEntity();
			if(entity != null) { //����������
				InputStream entityContent = entity.getContent();
				filePath = "html/" + getFileNameByUrl(url);
				saveToLocal(entityContent, filePath);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("�ɹ������ļ�" + filePath + "������"); //not very bad
		return filePath;
	}

}

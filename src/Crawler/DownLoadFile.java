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

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

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
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
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
		
		HttpClient httpClient = new HttpClient(); //���� HttpClinet 
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000); //���� Http ���ӳ�ʱ 5s
		
		GetMethod getMethod = new GetMethod(url); //���� GetMethod
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000); //���� get ����ʱ 5s
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler()); // �����������Դ���

		
		try { // ִ�� HTTP GET ����
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) { //�жϷ��ʵ�״̬��
				System.err.println("Method failed: " + getMethod.getStatusLine());
				filePath = null;
			}

			//���� HTTP ��Ӧ���� 
			InputStream responseBody = getMethod.getResponseBodyAsStream(); // ��ȡΪ�ֽ�����
				filePath = "html/" + getFileNameByUrl(url);
				saveToLocal(responseBody, filePath);
			//}
		} catch (HttpException e) {
			System.out.println("Please check your provided http address!"); //�����������쳣��������Э�鲻�Ի��߷��ص�����������
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace(); // ���������쳣
		} finally {
			getMethod.releaseConnection(); // �ͷ�����
		}
		System.out.println("�ɹ������ļ�" + filePath + "������"); //not very bad
		return filePath;
	}
}

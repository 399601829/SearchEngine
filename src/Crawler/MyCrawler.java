/**
 * ����������������
 * ���ߣ�����
 **/
package Crawler;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyCrawler {
	final static int MAXNUM = 50; // the max amount of the web pages that
									// crawled.
	static int downloadFileNum = 0;
	final int MAXSEEDSNUM = 10; // the max amount of the seeds.
	String[] seeds = new String[MAXSEEDSNUM];
	static long timeForDownLoad = 0;
	static long timeForHtmlParser = 0;

	public MyCrawler() {
	}

	public MyCrawler(String[] seeds) {
		for (int i = 0; i < seeds.length; i++) {
			LinkQueue.addUnvisitedUrl(seeds[i]);
		}
	}

	private static class Crawling implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// �������������ȡ��http://www.nju.edu.cn��ͷ������
			LinkFilter filter = new LinkFilter() {
				public boolean accept(String url) {
					if (url.startsWith("http://news.nju.edu.cn")
							&& (url.endsWith(".html") || (url
									.contains("show_article") && !url
									.contains("#"))))
						return true;
					else
						return false;
				}
			};

			// ѭ����������ץȡ�����Ӳ�����ץȡ����ҳ������1000
			while (!LinkQueue.unVisitedUrlsEmpty() && downloadFileNum < MAXNUM) {
				// ��ͷURL������
				String visitUrl = (String) LinkQueue.unVisitedUrlDeQueue();
				if (visitUrl.contains("show_article")) {
					long start = System.currentTimeMillis();
					DownLoadFile downLoader = new DownLoadFile(); // ������ҳ
					downLoader.downloadFile(visitUrl);
					long end = System.currentTimeMillis();
					timeForDownLoad += (end - start);
					downloadFileNum++;
				}
				LinkQueue.addVisitedUrl(visitUrl); // �� url ���뵽�ѷ��ʵ� URL ��
				long start = System.currentTimeMillis();
				Set<String> links = HtmlParserTool
						.extracLinks(visitUrl, filter); // ��ȡ��������ҳ�е� URL
				long end = System.currentTimeMillis();
				timeForHtmlParser += end - start;
				// �µ�δ���ʵ� URL ���
				for (String link : links) {
					LinkQueue.addUnvisitedUrl(link);
				}
			}

		}

	}

	public static void main(String[] args) {
		long start=0,end=0;
		start = System.currentTimeMillis();
		new MyCrawler(new String[] { "http://news.nju.edu.cn/index.html" });
		ExecutorService executors = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			executors.execute(new Crawling());
		}
		while (executors.isTerminated()) {
			end = System.currentTimeMillis();
			System.out.println("������򹲻���ʱ�䣺" + (end - start));
			System.out.println("������ҳ������ʱ�䣺" + timeForDownLoad);
			System.out.println("������ҳ������ʱ�䣺" + timeForHtmlParser);
		}

	}
}

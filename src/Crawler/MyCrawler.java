package Crawler;

import java.util.Set;

public class MyCrawler {
	final int MAXNUM = 100; // the max amount of the web pages that crawled.
	int downloadFileNum = 0;
	final int MAXSEEDSNUM = 10; //the max amount of the seeds.
	String[] seeds = new String[MAXSEEDSNUM];

	public MyCrawler() {
	}
	
	public MyCrawler(String[] seeds) {
		for(int i=0;i<seeds.length;i++) {
			LinkQueue.addUnvisitedUrl(seeds[i]);
		}
	}

	public void crawling() {
		//�������������ȡ��http://www.nju.edu.cn��ͷ������
		LinkFilter filter = new LinkFilter() {
			public boolean accept(String url) {
				if(url.startsWith("http://news.nju.edu.cn") 
						&& (url.endsWith(".html") 
								|| (url.contains("show_article") && !url.contains("#"))))
					return true;
				else
					return false;
			}
		};

		//ѭ����������ץȡ�����Ӳ�����ץȡ����ҳ������1000
		while(!LinkQueue.unVisitedUrlsEmpty() && downloadFileNum<MAXNUM)
		{
			//��ͷURL������
			String visitUrl=(String)LinkQueue.unVisitedUrlDeQueue();
			if(visitUrl.contains("show_article")) {
				DownLoadFile downLoader=new DownLoadFile(); //������ҳ
				downLoader.downloadFile(visitUrl);
				downloadFileNum ++;
			}
			LinkQueue.addVisitedUrl(visitUrl); //�� url ���뵽�ѷ��ʵ� URL ��
			Set<String> links=HtmlParserTool.extracLinks(visitUrl,filter); //��ȡ��������ҳ�е� URL
			//�µ�δ���ʵ� URL ���
			for(String link:links)
			{
				LinkQueue.addUnvisitedUrl(link);
				
			}
		}
	}
	public static void main(String[]args)
	{
		MyCrawler crawler = new MyCrawler(new String[]{"http://news.nju.edu.cn/index.html"});
		crawler.crawling();
	}
}

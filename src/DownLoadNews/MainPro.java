/*
 * ������ץȡָ����վ������ҳ�е�100����ҳ������ҳ�����ݴ�����url��ַ������txt�ĵ���
 * �Ա������ִʡ����⽫���µı���ץ���������������ű��Ϊ����txt�ĵ��С�
 * ���ߣ�����
 * */
package DownLoadNews;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainPro {
	public MainPro() {
		for (int i = 33234; i < 33334; i++) {
			try {
				String url = "http://news.nju.edu.cn/show_article_1_" + i;
				String content = this.getUrlSrc(url);

				// ץȡ����
				String result = this.DealHtml(this.getUrlSrc(url));
				String str = url.substring(7);// ȥ��http://
				str = str.replace('/', '_');
				ReadAndWrite.writeFileByChars("srcDoc/" + str + ".txt", result);

				// ץȡ����
				String titleResult = content;
				int titleStart = titleResult.indexOf("<div class=\"article_title\">");
				if (titleStart > 0)
					titleResult = titleResult.substring(titleStart);
				int titleEnd = titleResult.indexOf("</div>");
				if (titleEnd > 0)
					titleResult = titleResult.substring(0, titleEnd);
				titleResult = this.DealHtml(titleResult);
				ReadAndWrite.writeFileByChars("titleDoc/" + i
						+ ".txt", titleResult);
				// System.out.println(url + "   " + titleResult);

			} catch (Exception e) {
				continue;
			}
		}
	}

	public String DealHtml(Object o) {
		String str = o.toString();
		str = str.replaceAll("\\<(img)[^>]*>|<\\/(img)>", "");
		str = str
				.replaceAll(
						"\\<(table|tbody|tr|td|th|)[^>]*>|<\\/(table|tbody|tr|td|th|)>",
						"");
		str = str
				.replaceAll(
						"\\<(div|blockquote|fieldset|legend)[^>]*>|<\\/(div|blockquote|fieldset|legend)>",
						"");
		str = str.replaceAll(
				"\\<(font|i|u|h[1-9]|s)[^>]*>|<\\/(font|i|u|h[1-9]|s)>", "");
		str = str.replaceAll("\\<(style|strong)[^>]*>|<\\/(style|strong)>", "");
		str = str.replaceAll("\\<a[^>]*>|<\\/a>", "");
		str = str
				.replaceAll(
						"\\<(meta|iframe|frame|span|tbody|layer)[^>]*>|<\\/(iframe|frame|meta|span|tbody|layer)>",
						"");
		str = str.replaceAll("\\<br[^>]*", "");
		str = str.replaceAll("<script[\\s\\s]+</script *>", "");
		str = str.replaceAll("\\s", "");
		return str;
	}

	public String getUrlSrc(String url) {
		StringBuffer str = new StringBuffer(" ");
		String fileLine = " ";
		try {
			java.net.URL pageUrl = new java.net.URL(url);
			pageUrl.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					pageUrl.openStream(), "UTF-8"));
			while ((fileLine = in.readLine()) != null) {
				str.append(fileLine);
			}
			in.close();
		} catch (Exception e) {
			str = new StringBuffer(" ");
		}
		return str.toString().toLowerCase();
	}

	public static void main(String[] args) {
		new MainPro();

	}
}

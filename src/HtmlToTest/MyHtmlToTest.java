/**
 * ����������֮ǰץȡ����ҳ������ȡ���ı����洢�ڶ�Ӧ�ļ����Ա������ִʣ����⽫���µı���ץ���������������ű��Ϊ����txt�ĵ���
 * ���ߣ�����
**/
package HtmlToTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyHtmlToTest {
	public MyHtmlToTest() throws IOException {
		File directory = new File("html");
		File[] fileList = directory.listFiles();

		for (int i = 0; i < fileList.length; i++) {
			System.out.println(fileList[i].getName());
			String fileName = fileList[i].getName();
			StringBuilder content = new StringBuilder();
			String str = new String();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					new FileInputStream(fileList[i]), "UTF-8"));
			while ((str = in.readLine()) != null) {
				content.append(str);
			}
			in.close();
			String result = this.DealHtml(content); // ץȡ����
			ReadAndWrite
					.writeFileByChars("srcDoc/" + fileName + ".txt", result);

			// ץȡ����
			String titleResult = content.toString();
			int titleStart = titleResult
					.indexOf("<div class=\"article_title\">");
			if (titleStart > 0)
				titleResult = titleResult.substring(titleStart);
			int titleEnd = titleResult.indexOf("</div>");
			if (titleEnd > 0)
				titleResult = titleResult.substring(0, titleEnd);
			titleResult = this.DealHtml(titleResult);
			ReadAndWrite.writeFileByChars("titleDoc/" + fileName + ".txt",
					titleResult);
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

	public static void main(String[] args) throws IOException {
		new MyHtmlToTest();

	}
}

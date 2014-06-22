/*
 * ���������ֺôʵ��ĵ�ͳ�ƴ�Ƶ�����γɵ��������ļ�������index.txt�ĵ���
 * ���ߣ�����
 * */
package CreateIndex;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class MyCreateIndex {
	long start, end;
	long temp = 0;

	public MyCreateIndex() {
		HashMap<String, String> hashResult = new HashMap<String, String>();
		File dirFile = new File("wordDoc");
		File[] fileList = dirFile.listFiles();

		System.out.println("���ڶ��ı����ݽ��з��������ܻ���Ҫ�ϳ�ʱ�䣬�����ĵȴ�����");
		start = System.currentTimeMillis();
		for (int i = 0; i < fileList.length; i++) {
			String fileName = fileList[i].getName();
			System.out.println("\t�������ڶ��ļ�" + fileName + "���з���");
			HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
			String content = ReadAndWrite.readFileByChars(
					"wordDoc/" + fileName, "gbk");
			String[] wordArray = content.split(" ");
			for (int j = 0; j < wordArray.length; j++) {
				if (hashMap.keySet().contains(wordArray[j])) {
					Integer integer = (Integer) hashMap.get(wordArray[j]);
					int value = integer.intValue() + 1;
					hashMap.put(wordArray[j], new Integer(value));
				} else
					hashMap.put(wordArray[j], new Integer(1));
			}
			// ��ñ���
			String title_origin = ReadAndWrite.readFileByChars("titleDoc/"
					+ fileName, "gbk");
			// ��ø�����صĲ�������
			String fullContent_origin = ReadAndWrite.readFileByChars("srcDoc/"
					+ fileName, "gbk");
			for (String str : hashMap.keySet()) {
				String title = title_origin;
				String fullContent = fullContent_origin;
				String partContent = "";
				int wordStart = fullContent.indexOf(str);// �����ʵ�λ��
				while (wordStart > 0) {
					String strTmp;
					int s = 0, e = fullContent.length();
					if (wordStart > 10)
						s = wordStart - 10;
					else
						s = 0;
					if (e > (wordStart + 10))
						e = wordStart + 10;
					strTmp = fullContent.substring(s, e);
					// partContent.append(fullContent.substring(s,
					// e)).append("......");
					partContent += (strTmp + "......");
					fullContent = fullContent.substring(e);
					wordStart = fullContent.indexOf(str);
				}
				// �γɵ�������
				String tmp = fileName + "#split#" + title + "#split#"
						+ partContent + "#split#" + hashMap.get(str);
				if (hashResult.keySet().contains(str)) {// �����ô�
					String value = (String) hashResult.get(str);
					value += ("#next#" + tmp);
					hashResult.put(str, value);
				} else
					hashResult.put(str, tmp);
			}

		}
		end = System.currentTimeMillis();
		System.out.println("�ļ����ݷ�����ϣ�����ʱ��" + (end - start) + "ms");

		if (hashResult.size() > 0) {
			StringBuilder value = new StringBuilder("");

			System.out.println("�������ڽ����������ݣ����ܻ���Ҫ�ϳ�ʱ�䣬�����ĵȴ�����");
			start = System.currentTimeMillis();
			for (String str : hashResult.keySet()) {
				StringBuilder tmp = new StringBuilder(str).append("  ").append(
						hashResult.get(str));
				// String tmp = str + "  " + hashResult.get(str); // �����ո�
				value.append(tmp).append("#LINE#");
				// value += (tmp + "#LINE#");

			}
			end = System.currentTimeMillis();
			System.out.println("�������ݽ�����ϣ�����ʱ��" + (end - start) + "ms");

			System.out.println("�������ڽ���������д����̣����ܻ���Ҫ�ϳ�ʱ�䣬�����ĵȴ�����");
			start = System.currentTimeMillis();
			this.writeFileByChars("WebRoot/index.txt", value.toString());
			end = System.currentTimeMillis();
			System.out.println("��������д����ϣ�����ʱ��" + (end - start) + "ms");
		}

	}

	public void writeFileByChars(String fileName, String value) {
		String path = fileName;
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(path, false), "UTF-8"));

			String[] arryStr = value.split("#LINE#");
			for (int i = 0; i < arryStr.length; i++) {
				bw.write(arryStr[i]);
				bw.write(13);
				bw.write(10);
			}

			bw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new MyCreateIndex();

	}

}

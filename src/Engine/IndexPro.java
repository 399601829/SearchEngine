/*
 * �������������棬��ȡ�����ļ����������ϴ�Ƶ��������ʾ���
 * ���ߣ�����
 * */
package Engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

import org.wltea.analyzer.IKSegmentation;
import org.wltea.analyzer.Lexeme;

public class IndexPro {
	String indexFile; // the index file
	Vector<String> vecKey = new Vector<String>();
	HashMap<String, String> hashWord = null;
	long time = 0;

	public IndexPro() {
	}

	public IndexPro(String indexFile) {
		this.indexFile = indexFile;// �����ļ�
		long begin = System.currentTimeMillis();
		hashWord = new HashMap<String, String>();
		try {
			String line = null;
			BufferedReader rin = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File(indexFile)), "UTF-8"));
			while ((line = rin.readLine()) != null) {
				String[] array = line.split("  ");
				hashWord.put(array[0], array[1]); // array[0]keyword,array[1]others
			}
			rin.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		this.time = end - begin;
	}

	// ��ý������
	public ArrayList<ResultModel> getResultSet(String key) {
		ArrayList<ResultModel> modList = new ArrayList<ResultModel>();
		if (this.hashWord.size() > 0) {
			long begin = System.currentTimeMillis();
			ResultModel[] modArray = null;
			// �Թؼ��ִַ�
			StringReader strReader = new StringReader(key);
			IKSegmentation iksegmentation = new IKSegmentation(strReader);
			Lexeme lexeme = null;
			try {
				while ((lexeme = iksegmentation.next()) != null) {
					System.out.println(lexeme.getLexemeText());
					vecKey.add(lexeme.getLexemeText());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// �ֱ���Ҹ������������е�ƥ��
			for (String strKey : vecKey) {
				String result = this.hashWord.get(strKey);
				if (result != null) {
					String[] array = result.split("#next#"); // �õ����ڸùؼ��ֵ������ı��ļ���Ϣ
					modArray = new ResultModel[array.length]; // ÿ���ı��ļ���Ϣ�����Ի��һ��ResultModel
					for (int i = 0; i < array.length; i++)
						modArray[i] = new ResultModel(key, array[i]);
				}
				// }

				if (modArray != null) {
					for (int i = 0; i < modArray.length; i++) {
						modList.add(modArray[i]);
					}

					// �ϲ���ͬ�������ݵĴ�Ƶ
					this.ResultMerger(modList);
					// ��������մ�Ƶ����
					Collections.sort(modList, new sortByWordNum());
				}
			}
			long end = System.currentTimeMillis();
			this.time += (end - begin);
		}
		return modList;
	}

	// ��ô���ʱ��
	public long getTime() {
		return this.time;
	}

	// �ϲ���ͬ�������ݵĴ�Ƶ
	private void ResultMerger(ArrayList<ResultModel> modList) {
		for (int i = 0; i < modList.size(); i++)
			for (int j = i + 1; j < modList.size(); j++) {
				if (modList.get(i) != null && modList.get(j) != null) {
					if (modList.get(i).getUrl().trim()
							.equals(modList.get(j).getUrl().trim())) {
						modList.get(i).addWordV(modList.get(j).getWordV());// ���Ƶ��
						modList.remove(j);
					}
				}
			}
	}

	// �Թؼ��ʸ�����ʾ
	public String HighLightKey(String content) {
		content = content.replaceAll(" ", "");
		for (String word : this.vecKey) {
			content = content.replaceAll(word,
					"<font style='color:#ff0000;font-weight:bold;'>" + word
							+ "</font>");
		}

		return content.replaceAll(
				"</font>[\\W]*<font style='color:#ff0000;font-weight:bold;'>",
				"");
	}
	public static void main(String[] argv) {
		//IndexPro index = new IndexPro("WebRoot/index.txt", "WebRoot/wordtable");
		//ArrayList<ResultModel>testList = index.getResultSet("�й�");
	}
}

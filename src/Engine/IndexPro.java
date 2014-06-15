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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import SplitWord.SplitWordPro;

public class IndexPro {
	String indexFile; // the index file
	String wordtableFile; // the dictionary file
	Vector<String> vecKey;
	HashMap<String, String> hashWord = null;
	long time = 0;

	public IndexPro() {
	}

	public IndexPro(String indexFile, String wordtableFile) {
		this.indexFile = indexFile;// �����ļ�
		this.wordtableFile = wordtableFile;// �ʱ�
		long begin = System.currentTimeMillis();
		hashWord = new HashMap<String, String>();
		try {

			String line = null;
			BufferedReader rin = new BufferedReader(new InputStreamReader(new FileInputStream(new File(indexFile)),"UTF-8"));
			while ((line = rin.readLine()) != null) {
				// System.out.println(line);//��ȡÿһ��
				String[] array = line.split("  ");
				hashWord.put(array[0], array[1]);
				//System.out.println(array[0]);
				//System.out.println(array[1]);
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
		ArrayList<ResultModel> modList = null;
		if (this.hashWord.size() > 0) {
			long begin = System.currentTimeMillis();

			ResultModel[] modArray = null;
			// �Թؼ��ִַ�
			SplitWordPro swp = new SplitWordPro(this.wordtableFile);
			this.vecKey = swp.getWord(key);
			// �ֱ���Ҹ������������е�ƥ��
			for (String strKey : vecKey) {
				String result = this.hashWord.get(strKey);
				if (result != null) {
					modList = new ArrayList<ResultModel>();
					String[] array = result.split("#next#");
					modArray = new ResultModel[array.length];
					for (int i = 0; i < array.length; i++)
						modArray[i] = new ResultModel(key, array[i]);
				}
			}

			if (modArray != null) {
				// �ϲ���ͬ�������ݵĴ�Ƶ
				this.ResultMerger(modArray);
				// ��������մ�Ƶ����
				for (int i = 0; i < modArray.length; i++) {
					for (int j = i + 1; j < modArray.length; j++) {
						if (modArray[i].getWordV() < modArray[j].getWordV()) {
							ResultModel modTmp = modArray[i];
							modArray[i] = modArray[j];
							modArray[j] = modTmp;
						}
					}
				}
				modList = new ArrayList<ResultModel>();
				for (int i = 0; i < modArray.length; i++)
					modList.add(modArray[i]);
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
	private void ResultMerger(ResultModel[] modArray) {
		for (int i = 0; i < modArray.length; i++)
			for (int j = i + 1; j < modArray.length; j++) {
				if (modArray[i] != null && modArray[j] != null) {
					if (modArray[i].getUrl().trim()
							.equals(modArray[j].getUrl().trim())) {
						modArray[i].addWordV(modArray[j].getWordV());// ���Ƶ��
						modArray[j] = null;
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
}

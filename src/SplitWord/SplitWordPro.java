/*
 * ���������������������ƥ��ִ�
 * ���ߣ�����
 * */

package SplitWord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class SplitWordPro {
	Vector<String> vecWord;

	public SplitWordPro(String wordtableFile) {
		String strFile = wordtableFile;
		this.vecWord = new Vector<String>();
		File f = new File(strFile);
		try {

			String line = null;
			FileInputStream in = new FileInputStream(f);
			InputStreamReader r = new InputStreamReader(in, "GB2312");
			BufferedReader rin = new BufferedReader(r);
			while ((line = rin.readLine()) != null) {
				// System.out.println(line);//��ȡÿһ��
				vecWord.add(line);
			}
			rin.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Vector<String> getWord(String str) {
		Vector<String> vecSplitWord = new Vector<String>();
		String tmp = str;
		String tmpcontent = str;
		while (tmpcontent.length() > 0) {
			if (this.vecWord.contains(tmp)) {
				vecSplitWord.add(tmp);// ���뱾��
				tmpcontent = tmpcontent.substring(0,
						tmpcontent.length() - tmp.length());// ɾ���ҵ��Ĵ�
				tmp = tmpcontent;

			} else {
				if (tmp.length() > 1)
					;
				tmp = tmp.substring(1);
				if (tmp.length() == 1) {
					vecSplitWord.add(tmp);// ���뱾������
					tmpcontent = tmpcontent.substring(0,
							tmpcontent.length() - 1);
					tmp = tmpcontent;
				}
			}
		}
		return vecSplitWord;
	}
}

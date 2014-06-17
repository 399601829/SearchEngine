/*
 * ���������ģ�ͣ�������ŵ����ļ������
 * ���ߣ�����
 * */
package Engine;

/* String tmp = str + " " + fileName +"#split#" + title + "#split#" + hashMap.get(str) + "#next#";*/
public class ResultModel {
	private String word;// ��
	private String url;// Դҳ��url��ַ
	private int wordV;// �ڱ��ĵ��ڵĴ�Ƶ
	private String title;// ���ĵ��ı���
	private String partContent;// �����ʵĲ�����������

	public ResultModel() {
	}

	public ResultModel(String word, String result) {
		this.word = word;
		if (result.indexOf("#split#") > 0) {
			String[] array = result.split("#split#");
			//this.url = "http://" + array[0].replaceAll("_", "/").replaceAll(".txt", "");
			this.url = "http://" + array[0].replaceFirst("_", "/").replaceAll(".txt", "");
			this.title = array[1];
			this.partContent = array[2];
			this.wordV = Integer.parseInt(array[3].trim());
		}
	}

	public String word() {
		return word;
	}

	public String getUrl() {
		return this.url;
	}

	public String getTitle() {
		return this.title;
	}

	public int getWordV() {
		return this.wordV;
	}

	public String getPartContent() {
		return this.partContent;
	}

	public void addWordV(int v) {
		this.wordV += v;
	}
}

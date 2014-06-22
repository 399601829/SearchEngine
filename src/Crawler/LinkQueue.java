/**
 * ��������Ҫ���ݽṹ���ඨ��
 * ���ߣ�����
 **/
package Crawler;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LinkQueue {
	private static Lock lock = new ReentrantLock();
	private static Condition isEmpty = lock.newCondition();
	// �ѷ��ʵ� url ���ϣ�֮������ΪSet����Ҫ��֤����������Ԫ�ز��ظ�
	private static Set<String> visitedUrl = new HashSet<String>();
	// �����ʵ� url ����
	private static Queue<String> unVisitedUrl = new PriorityQueue<String>();

	// ��ӵ����ʹ���URL������
	public static synchronized void addVisitedUrl(String url) {
		visitedUrl.add(url);
		System.out.println("����visitedUrl�����й��У�" + LinkQueue.getVisitedUrlNum()
				+ "��Ԫ��"); // not very bad
	}

	// �Ƴ����ʹ���URL
	public static synchronized void removeVisitedUrl(String url) {
		visitedUrl.remove(url);
	}

	// ����Ѿ����ʵ�URL��Ŀ
	public static synchronized int getVisitedUrlNum() {
		return visitedUrl.size();
	}

	// ���URL����
	public static synchronized Queue<String> getUnVisitedUrl() {
		return unVisitedUrl;
	}

	// δ���ʵ�URL������
	public static Object unVisitedUrlDeQueue() {
		lock.lock();
		String visitUrl = null;

		try {
			while (unVisitedUrlsEmpty()) {
				System.out.println("����������");
				isEmpty.await();
				System.out.println("���Ѿ��������");
			}
				visitUrl = unVisitedUrl.poll();
				System.out.println(visitUrl + "�뿪unVisitedUrl����"); // bad
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

		return visitUrl;
	}

	// ��֤ÿ�� url ֻ������һ��
	public static void addUnvisitedUrl(String url) {
		lock.lock();
		try {
			if (url != null && !url.trim().equals("")
					&& !visitedUrl.contains(url) && !unVisitedUrl.contains(url)) {
				unVisitedUrl.add(url);
				System.out.println(url + "����unVisitedUrl����"); // not very bad
				System.out.println("����unVisitedUrl�����й���:"
						+ LinkQueue.getUnVisitedUrlNum() + "��Ԫ��"); // not very
																	// bad
				isEmpty.signalAll();
			}
		} finally {
			lock.unlock();
		}

	}

	public static synchronized int getUnVisitedUrlNum() {
		return unVisitedUrl.size();
	}

	// �ж�δ���ʵ�URL�������Ƿ�Ϊ��
	public static synchronized boolean unVisitedUrlsEmpty() {
		return unVisitedUrl.isEmpty();
	}

}

/**
 * @author jiangxin
 * ɾ��Ŀ¼
 */

package AllInOne;
import java.io.File;

public class DeleteDirectory {

	/**
	 * �ݹ�ɾ��Ŀ¼�µ������ļ�����Ŀ¼�������ļ�
	 * 
	 * @param dir
	 *            :��Ҫɾ�����ļ�Ŀ¼
	 * @return boolean Returns "true" if all deletions were successful. If a
	 *         deletion fails, the method stops attempting to delete and returns
	 *         "false".
	 */
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
			// �ݹ�ɾ��Ŀ¼�е���Ŀ¼��
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// Ŀ¼��ʱΪ�գ�����ɾ��
		return dir.delete();
	}
	public static boolean deleteDirs(String[] deleteDirs) {
		for(int i=0;i<deleteDirs.length;i++) {
			File dir = new File(deleteDirs[i]);
			boolean isSuccessful = deleteDir(dir);
			if(isSuccessful == true) {
				System.out.println("�ɹ�ɾ��Ŀ¼��" + dir);
			}
		}
		return false;
		
	}

	/**
	 * ����
	 */
	public static void main(String[] args) {
		String newDir2 = "new_dir2";
		boolean success = deleteDir(new File(newDir2));
		if (success) {
			System.out.println("Successfully deleted populated directory: "
					+ newDir2);
		} else {
			System.out.println("Failed to delete populated directory: "
					+ newDir2);
		}
	}
}
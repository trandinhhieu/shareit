package library;

public class CheckResult {

	/**
	 * Kiểm tra câu lệnh truy vấn UPDATE, INSERT, DELETE
	 * 
	 * @param number
	 * @return TRUE, FALSE
	 */
	public static boolean checkRs(int number) {
		if (number > 0) {
			return true;
		}
		return false;
	}
}

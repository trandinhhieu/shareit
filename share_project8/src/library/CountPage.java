package library;

import constant.Define;

public class CountPage {
	
	
	/**
	 * TÍNH SỐ TRANG
	 * @param sumCount
	 * @return
	 */
	public static int countPage(int sumCount) {
		int numPage = 0;
		numPage = (int)Math.ceil((float)sumCount/Define.ROW_COUNT);
		return numPage;
	}
	
	/**
	 * TÍNH OFFSET
	 * @param currentPage
	 * @return
	 */
	public static int countOffset(int currentPage) {
		int offset = 0;
		offset = (currentPage - 1) * Define.ROW_COUNT;
		return offset;
	}

}

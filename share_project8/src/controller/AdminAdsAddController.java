package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import library.CheckResult;
import library.CheckSession;
import library.FileLibrary;
import model.bean.Ads;
import model.dao.ADsDao;

/**
 * Servlet implementation class PublicIndexController
 */
@MultipartConfig
public class AdminAdsAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAdsAddController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!CheckSession.Check(request, response)) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		ADsDao adsDao = new ADsDao();
		String name = request.getParameter("name");
		String link = request.getParameter("link");

		// xử lý upload file
		String picture = "";
		Part part = request.getPart("hinhanh");
		// trỏ đường dẫn thư mục
		final String path = request.getServletContext().getRealPath("/files");

		File dirPath = new File(path);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}

		// đổi tên file
		final String fileName = FileLibrary.getFileName(part);
		picture = FileLibrary.renameFile(fileName);
		OutputStream out = null;
		InputStream fileContent = null;
		try {
			out = new FileOutputStream(new File(path + File.separator + picture));

			// tiến hành ghi file
			fileContent = part.getInputStream();
			int read = 0;
			final byte[] bytes = new byte[1024];
			while ((read = fileContent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			if (fileContent != null) {
				fileContent.close();
			}
		}
		Ads objAD = new Ads(0, name, picture, link, 1);

		/**
		 * Kiểm tra kết quả
		 */
		if (!CheckResult.checkRs(adsDao.addItem(objAD))) {
			response.sendRedirect(request.getContextPath() + "/admin/ads?msg=0");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/ads?msg=1");
			return;
		}

	}

}

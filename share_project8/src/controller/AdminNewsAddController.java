package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import library.CheckResult;
import library.CheckSession;
import library.FileLibrary;
import model.bean.News;
import model.bean.User;
import model.dao.NewsDao;

/**
 * Servlet implementation class PublicIndexController
 */
@MultipartConfig
public class AdminNewsAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminNewsAddController() {
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
		HttpSession session = request.getSession();
		User userInfor = (User) session.getAttribute("objUser");
		NewsDao newsDao = new NewsDao();
		String name = request.getParameter("name");
		int cid = Integer.parseInt(request.getParameter("danhmuc"));
		int slide = Integer.parseInt(request.getParameter("slide"));
		String preview = request.getParameter("mota");
		String detail = request.getParameter("chitiet");
		Timestamp date = new Timestamp(new Date().getTime());

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
		News objNews = new News(name, cid, slide, picture, preview, detail, date, 0, userInfor.getUid(), 0);

		/**
		 * Kiểm tra kết quả
		 */
		if (!CheckResult.checkRs(newsDao.addItem(objNews))) {
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=0");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=1");
			return;
		}

	}

}

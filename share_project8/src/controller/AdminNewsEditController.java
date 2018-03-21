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
import model.bean.News;
import model.dao.NewsDao;

/**
 * Servlet implementation class PublicIndexController
 */
@MultipartConfig
public class AdminNewsEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminNewsEditController() {
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
		NewsDao newsDao = new NewsDao();
		int nid = 1;
		try {
			nid = Integer.parseInt(request.getParameter("nid"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
		String name = request.getParameter("name");
		int cid = Integer.parseInt(request.getParameter("danhmuc"));
		String preview = request.getParameter("mota");
		String detail = request.getParameter("chitiet");

		// xử lý upload file
		String picture = "";
		final Part part = request.getPart("hinhanh");
		// trỏ đường dẫn thư mục
		final String path = request.getServletContext().getRealPath("/files");

		File dirPath = new File(path);
		if (!dirPath.exists()) {
			dirPath.mkdir();
		}

		// lấy tên file
		final String filename = FileLibrary.getFileName(part);
		if (!filename.isEmpty()) {
			if (!newsDao.getItem(nid).getPicture().isEmpty()) {
				// xóa hình ảnh cũ
				String urlDelFile = path + File.separator + newsDao.getItem(nid).getPicture();
				File delFile = new File(urlDelFile);
				delFile.delete();
			}
			// đổi tên file
			picture = FileLibrary.renameFile(filename);
			OutputStream out = null;
			InputStream filecontent = null;
			try {
				out = new FileOutputStream(new File(path + File.separator + picture));

				// tiến hành ghi file
				filecontent = part.getInputStream();
				int read = 0;
				final byte[] bytes = new byte[1024];
				while ((read = filecontent.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				if (out != null) {
					out.close();
				}
				if (filecontent != null) {
					filecontent.close();
				}
			}

		} else {
			// lấy lại tên hình cũ
			picture = newsDao.getItem(nid).getPicture();
		}
		News objNews = new News(nid, name, cid, picture, preview, detail);
		/**
		 * Kiểm tra kết quả
		 */
		if (!CheckResult.checkRs(newsDao.editItem(objNews))) {
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=0");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=2");
			return;
		}

	}

}

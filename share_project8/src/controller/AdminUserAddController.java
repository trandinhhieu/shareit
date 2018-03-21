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
import library.StringLibrary;
import model.bean.User;
import model.dao.UserDao;

/**
 * Servlet implementation class AdminIndexCatController
 */
@MultipartConfig
public class AdminUserAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUserAddController() {
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
		UserDao userDao = new UserDao();
		String username = new String(request.getParameter("username").getBytes("ISO-8859-1"), "UTF-8");

		String fullname = new String(request.getParameter("fullname").getBytes("ISO-8859-1"), "UTF-8");
		String password = StringLibrary
				.md5(new String(request.getParameter("password").getBytes("ISO-8859-1"), "UTF-8"));
		String email = new String(request.getParameter("email").getBytes("ISO-8859-1"), "UTF-8");

		// xử lý upload file
		String picture = "";
		Part part1 = request.getPart("avatar");
		// trỏ đường dẫn thư mục
		final String path = request.getServletContext().getRealPath("/files");

		File dirPath = new File(path);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}

		// đổi tên file
		final String fileName = FileLibrary.getFileName(part1);
		picture = FileLibrary.renameFile(fileName);
		OutputStream out = null;
		InputStream fileContent = null;
		try {
			out = new FileOutputStream(new File(path + File.separator + picture));

			// tiến hành ghi file
			fileContent = part1.getInputStream();
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
		User objUser = new User(0, username, fullname, 0, password, email, 1, picture);

		if (userDao.checkUsername(username) != null) {
			// đã tồn tại
			response.sendRedirect(request.getContextPath() + "/admin/useradd?msg=0");
			return;
		} else {
			if (!CheckResult.checkRs(userDao.addItem(objUser))) {
				response.sendRedirect(request.getContextPath() + "/admin/user?msg=0");
				return;
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/user?msg=1");
				return;
			}
		}
	}

}

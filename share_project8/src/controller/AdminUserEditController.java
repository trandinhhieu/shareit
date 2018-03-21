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
import javax.servlet.http.HttpSession;
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
public class AdminUserEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUserEditController() {
		super();
		// TODO Auto-generated constructor stub
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
		// sửa user
		UserDao userDao = new UserDao();
		HttpSession session = request.getSession();
		User obj = (User) session.getAttribute("objUser");
		int uid = 1;
		try {
			uid = Integer.parseInt(request.getParameter("uid"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
		String fullname = new String(request.getParameter("fullname").getBytes("ISO-8859-1"), "UTF-8");

		String password = new String(request.getParameter("password1").getBytes("ISO-8859-1"), "UTF-8");

		String email = new String(request.getParameter("email").getBytes("ISO-8859-1"), "UTF-8");

		if ("".equals(password)) {
			// nếu pass rỗng thì lấy lại pass cũ
			password = userDao.getItem(uid).getUpass();
		} else {
			// cập nhật pass mới
			password = StringLibrary.md5(password);
		}

		// xử lý upload file
		String picture = "";
		final Part part = request.getPart("avatar");
		// trỏ đường dẫn thư mục
		final String path = request.getServletContext().getRealPath("/files");

		File dirPath = new File(path);
		if (!dirPath.exists()) {
			dirPath.mkdir();
		}

		// lấy tên file
		final String filename = FileLibrary.getFileName(part);
		if (!filename.isEmpty()) {
			if (!userDao.getItem(uid).getAvatar().isEmpty()) {
				// xóa hình ảnh cũ
				String urlDelFile = path + File.separator + userDao.getItem(uid).getAvatar();
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
			picture = userDao.getItem(uid).getAvatar();
		}

		User objUser = new User(uid, fullname, password, email, picture);

		if (uid == obj.getUid() || userDao.getItem(obj.getUid()).getRole() > 0) {
			if (!CheckResult.checkRs(userDao.editUser(objUser))) {
				response.sendRedirect(request.getContextPath() + "/admin/user?msg=0");
				return;
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/user?msg=2");
				return;
			}
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/user?msg=5");
			return;
		}

	}

}

package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.CheckSession;
import model.bean.User;
import model.dao.UserDao;

/**
 * Servlet implementation class AdminIndexCatController
 */
public class AdminUserDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUserDelController() {
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

		User objUser = userDao.getItem(uid);
		if (objUser.getRole() == 1) {
			// không có quyền xóa
			response.sendRedirect(request.getContextPath() + "/admin/user?msg=4");
			return;
		} else {
			if (obj.getRole() > 0) {
				if (userDao.delItem(uid) > 0) {
					response.sendRedirect(request.getContextPath() + "/admin/user?msg=3");
					return;
				} else {
					response.sendRedirect(request.getContextPath() + "/admin/user?msg=0");
					return;
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/admin/user?msg=5");
				return;
			}

		}
	}

}

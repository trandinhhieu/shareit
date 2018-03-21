package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
public class AdminShowUserEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminShowUserEditController() {
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
		if (!CheckSession.Check(request, response)) {
			response.sendRedirect(request.getContextPath() + "/auth/login/");
			return;
		}
		HttpSession session = request.getSession();
		User userInfor = (User) session.getAttribute("objUser");
		UserDao userDao = new UserDao();
		int uid = 1;
		try {
			uid = Integer.parseInt(request.getParameter("uid"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}

		if (userInfor.getRole() > 0 || uid == userInfor.getUid()) {
			request.setAttribute("objUser", userDao.getItem(uid));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/user/editUser.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/user?msg=5");
			return;
		}

	}

}

package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CheckSession;
import library.CountPage;
import model.dao.UserDao;

/**
 * Servlet implementation class AdminShowNewsController
 */
public class AdminShowUserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminShowUserController() {
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
		int currentPage = 1;
		int offset;
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		offset = CountPage.countOffset(currentPage);
		request.setAttribute("numPage", CountPage.countPage(userDao.sumCount()));
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("alUser", userDao.getPanigation(offset));
		RequestDispatcher rd = request.getRequestDispatcher("/admin/user/index.jsp");
		rd.forward(request, response);
	}

}

package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.CountPage;
import library.StringLibrary;
import model.bean.User;
import model.dao.NewsDao;
import model.dao.UserDao;

/**
 * Servlet implementation class AdminIndexCatController
 */
public class PublicLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicLoginController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDao userDao = new UserDao();
		NewsDao newsDao = new NewsDao();
		int currentPage = 1;
		int offset;
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		offset = CountPage.countOffset(currentPage);
		request.setAttribute("numPage", CountPage.countPage(newsDao.sumCount()));
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("alNews", newsDao.getPanigation(offset));
		String username = new String(request.getParameter("taikhoan").getBytes("ISO-8859-1"), "UTF-8");
		String password = StringLibrary
				.md5(new String(request.getParameter("matkhau").getBytes("ISO-8859-1"), "UTF-8"));
		User objUser = new User(username, password);
		User obj = userDao.getItem(objUser);
		if (obj != null) {
			HttpSession session = request.getSession();
			session.setAttribute("objUser", obj);
			response.sendRedirect(request.getContextPath() + "/admin/news");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/login?msg=0");
		}
	}

}

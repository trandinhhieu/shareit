package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CheckSession;
import library.CountPage;
import model.dao.NewsDao;

/**
 * Servlet implementation class AdminShowNewsController
 */
public class AdminShowNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminShowNewsController() {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/news/index.jsp");
		dispatcher.forward(request, response);
	}

}

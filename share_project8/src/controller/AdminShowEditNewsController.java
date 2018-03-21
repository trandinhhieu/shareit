package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CheckSession;
import model.dao.CatDao;
import model.dao.NewsDao;

/**
 * Servlet implementation class AdminShowNewsController
 */
public class AdminShowEditNewsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminShowEditNewsController() {
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

		CatDao catDao = new CatDao();
		NewsDao newsDao = new NewsDao();
		int nid = 1;
		try {
			nid = Integer.parseInt(request.getParameter("nid"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
		request.setAttribute("objNews", newsDao.getItem(nid));
		request.setAttribute("alCat", catDao.getItems());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/news/editNews.jsp");
		dispatcher.forward(request, response);
	}

}

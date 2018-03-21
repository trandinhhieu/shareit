package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CountPage;
import model.bean.News;
import model.dao.NewsDao;

/**
 * Servlet implementation class PublicIndexController
 */
public class PublicSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicSearchController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
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
		String name = "";
		if (request.getParameter("txtsearch") != null) {
			name = request.getParameter("txtsearch");
			if (request.getParameter("page") != null) {
				currentPage = Integer.parseInt(request.getParameter("page"));
				if (currentPage > CountPage.countPage(newsDao.sumSearch(name)) || currentPage < 0) {
					RequestDispatcher dispatcher9 = request.getRequestDispatcher("/error.jsp");
					dispatcher9.forward(request, response);
					return;
				}
			}
			offset = CountPage.countOffset(currentPage);
			ArrayList<News> alNews = newsDao.getPanigationS(offset, name);

			request.setAttribute("numPage", CountPage.countPage(newsDao.sumSearch(name)));
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("alSlide", newsDao.getItemsSlide());
			request.setAttribute("alNews", alNews);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/blog-sidebar.jsp");
			dispatcher.forward(request, response);
		}

	}

}

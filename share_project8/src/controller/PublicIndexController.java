package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CountPage;
import model.dao.NewsDao;

/**
 * Servlet implementation class PublicIndexController
 */
public class PublicIndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicIndexController() {
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
		int value = 0;
		if (request.getParameter("value") != null) {
			try {
				value = Integer.parseInt(request.getParameter("value"));
			} catch (NumberFormatException e) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
				dispatcher.forward(request, response);
				return;
			}
			switch (value) {
			case 1:

				if (request.getParameter("page") != null) {
					currentPage = Integer.parseInt(request.getParameter("page"));

					if (currentPage > CountPage.countPage(newsDao.sumHot()) || currentPage < 0) {
						RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
						dispatcher.forward(request, response);
						return;
					}
				}

				offset = CountPage.countOffset(currentPage);
				request.setAttribute("alNews", newsDao.getHotNews(offset));
				request.setAttribute("numPage", CountPage.countPage(newsDao.sumHot()));
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("alSlide", newsDao.getItemsSlide());
				RequestDispatcher dispatcher = request.getRequestDispatcher("/blog-sidebar.jsp");
				dispatcher.forward(request, response);
				break;
			case 2:
				if (request.getParameter("page") != null) {
					currentPage = Integer.parseInt(request.getParameter("page"));
					if (currentPage > CountPage.countPage(newsDao.sumCount()) || currentPage < 0) {
						RequestDispatcher dispatcher9 = request.getRequestDispatcher("/error.jsp");
						dispatcher9.forward(request, response);
						return;
					}
				}
				offset = CountPage.countOffset(currentPage);
				request.setAttribute("alNews", newsDao.getRecentNews(offset));
				request.setAttribute("numPage", CountPage.countPage(newsDao.sumCount()));
				request.setAttribute("alSlide", newsDao.getItemsSlide());
				request.setAttribute("currentPage", currentPage);
				RequestDispatcher rd = request.getRequestDispatcher("/blog-sidebar.jsp");
				rd.forward(request, response);
				break;
			case 3:
				if (request.getParameter("page") != null) {
					currentPage = Integer.parseInt(request.getParameter("page"));
					if (currentPage > CountPage.countPage(newsDao.sumCount()) || currentPage < 0) {
						RequestDispatcher dispatcher9 = request.getRequestDispatcher("/error.jsp");
						dispatcher9.forward(request, response);
						return;
					}
				}
				offset = CountPage.countOffset(currentPage);
				request.setAttribute("alNews", newsDao.getOldNews(offset));
				request.setAttribute("numPage", CountPage.countPage(newsDao.sumCount()));
				request.setAttribute("currentPage", currentPage);
				request.setAttribute("alSlide", newsDao.getItemsSlide());
				RequestDispatcher rd1 = request.getRequestDispatcher("/blog-sidebar.jsp");
				rd1.forward(request, response);
				break;
			default:
				RequestDispatcher rd6 = request.getRequestDispatcher("/error.jsp");
				rd6.forward(request, response);
				break;
			}
		} else {
			if (request.getParameter("page") != null) {
				try {
					currentPage = Integer.parseInt(request.getParameter("page"));
					if (currentPage > CountPage.countPage(newsDao.sumCount()) || currentPage < 0) {
						RequestDispatcher dispatcher9 = request.getRequestDispatcher("/error.jsp");
						dispatcher9.forward(request, response);
						return;
					}
				} catch (NumberFormatException e) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
					dispatcher.forward(request, response);
					return;
				}
			}

			offset = CountPage.countOffset(currentPage);
			request.setAttribute("numPage", CountPage.countPage(newsDao.sumCount()));
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("alSlide", newsDao.getItemsSlide());
			request.setAttribute("alNews", newsDao.getPanigation(offset));
			RequestDispatcher dispatcher = request.getRequestDispatcher("/blog-sidebar.jsp");
			dispatcher.forward(request, response);
		}
	}

}

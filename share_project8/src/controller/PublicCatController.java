package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CountPage;
import model.dao.CatDao;
import model.dao.NewsDao;

/**
 * Servlet implementation class PublicIndexController
 */
public class PublicCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicCatController() {
		super();
		// TODO Auto-generated constructor stub
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
		CatDao catDao = new CatDao();
		int cid = 1;
		try {
			cid = Integer.parseInt(request.getParameter("cid"));
		} catch (NumberFormatException e) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
			return;
		}
		int currentPage = 1;
		int offset;
		if (request.getParameter("page") != null) {
			try {
				currentPage = Integer.parseInt(request.getParameter("page"));
				if (currentPage > CountPage.countPage(newsDao.sumCount(cid)) || currentPage < 0) {
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

		int check = catDao.CountID(cid);
		if (check == 0) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
			return;
		}

		offset = CountPage.countOffset(currentPage);
		request.setAttribute("objCat", catDao.getItem(cid));
		request.setAttribute("numPage", CountPage.countPage(newsDao.sumCount(cid)));
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("alNews", newsDao.getPaginationbyID(cid, offset));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/cat-sidebar.jsp");
		dispatcher.forward(request, response);
	}

}

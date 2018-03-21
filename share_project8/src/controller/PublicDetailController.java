package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CommentDao;
import model.dao.NewsDao;

/**
 * Servlet implementation class PublicDetailController
 */
public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicDetailController() {
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
		CommentDao comDao = new CommentDao();
		int nid = 1;
		try {
			nid = Integer.parseInt(request.getParameter("did"));
		} catch (NumberFormatException e) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
			return;
		}

		int check = newsDao.CountID(nid);
		if (check == 0) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
			dispatcher.forward(request, response);
			return;
		}

		newsDao.updateView(nid);
		request.setAttribute("objNews", newsDao.getItem(nid));
		request.setAttribute("alNews", newsDao.getItemsbyID(nid));
		request.setAttribute("alCom", comDao.getItems(nid));
		request.setAttribute("alRep", comDao.getItems());
		request.setAttribute("count", comDao.countCmt(nid));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/single-post-2.jsp");
		dispatcher.forward(request, response);
	}

}

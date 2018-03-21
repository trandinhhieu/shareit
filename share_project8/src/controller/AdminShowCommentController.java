package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CheckSession;
import library.CountPage;
import model.dao.CommentDao;

/**
 * Servlet implementation class AdminShowNewsController
 */
public class AdminShowCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminShowCommentController() {
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
		CommentDao cmtDao = new CommentDao();
		int currentPage = 1;
		int offset = 0;
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		offset = CountPage.countOffset(currentPage);
		request.setAttribute("numPage", CountPage.countPage(cmtDao.sumCount()));
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("alCom", cmtDao.getPagination(offset));
		RequestDispatcher rd = request.getRequestDispatcher("/admin/comment/index.jsp");
		rd.forward(request, response);
	}

}

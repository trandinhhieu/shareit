package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CheckSession;
import model.dao.CommentDao;

/**
 * Servlet implementation class PublicIndexController
 */
public class AdminActiveCommentUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminActiveCommentUpdateController() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		CommentDao cmtDao = new CommentDao();
		int id = Integer.parseInt(request.getParameter("id"));
		int slide = Integer.parseInt(request.getParameter("slide"));
		cmtDao.updateActive(slide, id);
	}

}

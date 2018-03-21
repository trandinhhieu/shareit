package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CheckResult;
import library.CheckSession;
import model.dao.NewsDao;

/**
 * Servlet implementation class PublicIndexController
 */
public class AdminSlideUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminSlideUpdateController() {
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
		NewsDao newsDao = new NewsDao();
		int id = Integer.parseInt(request.getParameter("id"));
		int slide = Integer.parseInt(request.getParameter("slide"));
		/**
		 * Kiểm tra kết quả
		 */
		if (!CheckResult.checkRs(newsDao.updateSlide(slide, id))) {
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=0");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=1");
			return;
		}

	}

}

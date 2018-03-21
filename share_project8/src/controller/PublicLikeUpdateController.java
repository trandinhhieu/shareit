package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.NewsDao;

/**
 * Servlet implementation class PublicIndexController
 */
public class PublicLikeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicLikeUpdateController() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		NewsDao newsDao = new NewsDao();
		int heart = Integer.parseInt(request.getParameter("heart"));
		int id = Integer.parseInt(request.getParameter("id"));

		heart += 1;
		newsDao.updateLike(heart, id);
		String result = "";
		result = "<li><a href='javascript:void(0)' onclick='return doUpdate( " + id + ", " + heart
				+ ");'><i class='ion-heart'></i>&nbsp;&nbsp;" + heart + "</a></li>";
		PrintWriter out = response.getWriter();
		out.println(result);
	}

}

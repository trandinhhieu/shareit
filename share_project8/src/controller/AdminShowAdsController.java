package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CheckSession;
import library.CountPage;
import model.dao.ADsDao;

/**
 * Servlet implementation class AdminShowNewsController
 */
public class AdminShowAdsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminShowAdsController() {
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
		ADsDao adsDao = new ADsDao();
		int currentPage = 1;
		int offset;
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		offset = CountPage.countOffset(currentPage);
		request.setAttribute("numPage", CountPage.countPage(adsDao.sumCount()));
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("alAD", adsDao.getPanigation(offset));
		RequestDispatcher rd = request.getRequestDispatcher("/admin/ads/index.jsp");
		rd.forward(request, response);
	}

}

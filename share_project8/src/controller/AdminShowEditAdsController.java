package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CheckSession;
import model.dao.ADsDao;

/**
 * Servlet implementation class AdminShowNewsController
 */
public class AdminShowEditAdsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminShowEditAdsController() {
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
		int aid = 1;
		try {
			aid = Integer.parseInt(request.getParameter("aid"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
		request.setAttribute("objAD", adsDao.getItem(aid));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/ads/editAds.jsp");
		dispatcher.forward(request, response);
	}

}

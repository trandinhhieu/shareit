package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CheckSession;
import model.dao.CatDao;

/**
 * Servlet implementation class AdminShowNewsController
 */
public class AdminShowEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminShowEditCatController() {
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
		CatDao catDao = new CatDao();
		int cid = 1;
		try {
			cid = Integer.parseInt(request.getParameter("cid"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
		request.setAttribute("objCat", catDao.getItem(cid));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/cat/editCat.jsp");
		dispatcher.forward(request, response);
	}

}

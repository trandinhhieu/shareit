package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CheckResult;
import library.CheckSession;
import model.bean.Category;
import model.dao.CatDao;

/**
 * Servlet implementation class PublicIndexController
 */
public class AdminCatEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminCatEditController() {
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
		CatDao catDao = new CatDao();
		int cid = 1;
		try {
			cid = Integer.parseInt(request.getParameter("cid"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/admin/cat?msg=9");
			return;
		} 
		String cname = request.getParameter("name");
		Category objCat = new Category(cid,cname,0);
		
		/**
		 * Kiểm tra kết quả
		 */
		if (!CheckResult.checkRs(catDao.editItem(objCat))) {
			response.sendRedirect(request.getContextPath() + "/admin/cat?msg=0");
			return;
		}else {
			response.sendRedirect(request.getContextPath() + "/admin/cat?msg=2");
			return;
		}
		
	}

}

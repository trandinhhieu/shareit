package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CheckResult;
import library.CheckSession;
import model.bean.Ads;
import model.dao.ADsDao;

/**
 * Servlet implementation class AdminIndexCatController
 */
public class AdminAdsDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAdsDelController() {
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
		Ads objAD = adsDao.getItem(aid);
		String fileName = objAD.getPicture();
		if (!fileName.isEmpty()) {
			String filePath = request.getServletContext().getRealPath("/files") + File.separator + fileName;
			File file = new File(filePath);
			file.delete();
		}

		if (!CheckResult.checkRs(adsDao.delItem(aid))) {
			response.sendRedirect(request.getContextPath() + "/admin/ads?msg=0");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/ads?msg=3");
			return;
		}
	}

}

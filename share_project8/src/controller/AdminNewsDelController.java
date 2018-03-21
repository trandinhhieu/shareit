package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.CheckResult;
import library.CheckSession;
import model.bean.News;
import model.dao.NewsDao;

/**
 * Servlet implementation class AdminIndexCatController
 */
public class AdminNewsDelController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminNewsDelController() {
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
		NewsDao newsDao = new NewsDao();
		int nid = 1;
		try {
			nid = Integer.parseInt(request.getParameter("nid"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
		News objNews = newsDao.getItem(nid);
		String fileName = objNews.getPicture();
		if (!fileName.isEmpty()) {
			String filePath = request.getServletContext().getRealPath("/files") + File.separator + fileName;
			File file = new File(filePath);
			file.delete();
		}

		if (!CheckResult.checkRs(newsDao.delItem(nid))) {
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=0");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/news?msg=3");
			return;
		}
	}

}

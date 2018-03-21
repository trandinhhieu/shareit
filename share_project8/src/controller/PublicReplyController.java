package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Comment;
import model.dao.CommentDao;

/**
 * Servlet implementation class PublicIndexController
 */
public class PublicReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicReplyController() {
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
		CommentDao cmtDao = new CommentDao();

		String repFor = request.getParameter("com_name");
		String com_name = request.getParameter("name");
		String com_email = request.getParameter("email");
		String com_mess = request.getParameter("message");

		if (!com_mess.isEmpty()) {
			Timestamp date = new Timestamp(new Date().getTime());
			String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
			int id_news = Integer.parseInt(request.getParameter("id"));
			int id_com = Integer.parseInt(request.getParameter("id_com"));
			Comment objCmt = new Comment(0, com_name, com_email, com_mess, date, id_news, id_com, "", 1);
			cmtDao.addItem(objCmt);
			String result = "";
			result = "<li class='comment'><h5 class='reply-for'>Reply for <a href='#'><b>" + repFor
					+ "</b></a></h5><div class='post-info'><div class='middle-area'><a class='name' href='#'><b>"
					+ com_name + "</b></a><h6 class='date'>&nbsp;" + timeStamp
					+ "</h6></div></div><!-- post-info --><p>" + com_mess + "</p></li>";
			PrintWriter out = response.getWriter();
			out.println(result);
		}

	}

}

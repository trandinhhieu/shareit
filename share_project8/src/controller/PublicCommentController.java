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
public class PublicCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PublicCommentController() {
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

		String com_name = request.getParameter("name");
		String com_email = request.getParameter("email");
		String com_mess = request.getParameter("message");

		if (!com_mess.isEmpty()) {
			Timestamp date = new Timestamp(new Date().getTime());
			String timeStamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);
			int id_news = Integer.parseInt(request.getParameter("id"));
			Comment objCmt = new Comment(0, com_name, com_email, com_mess, date, id_news, 0, "", 1);
			cmtDao.addItem(objCmt);
			String result = "";
			result = "<li class='commnets-area text-left'><div class='comment'><div class='post-info'><div class='middle-area'><a class='name' href='#'><b>"
					+ com_name + "</b></a><h6 class='date'>&nbsp;" + timeStamp
					+ "</h6></div><div class='right-area'><h5 class='reply-btn' ><a href='#'><b>REPLY</b></a></h5></div></div><!-- post-info --><p>"
					+ com_mess + "</p></div></li><!-- commnets-area -->";
			PrintWriter out = response.getWriter();
			out.println(result);
		}

	}

}

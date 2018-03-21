<%@page import="model.dao.CommentDao"%>
<%@page import="model.bean.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
<%@include file="/templates/public/inc/header.jsp" %>
	<%
		Category item = (Category)request.getAttribute("objCat");
	%>
	<section class="blog-area section">
		<div class="container">

			<div class="row">

				<div class="col-lg-8 col-md-12">
					<div class="row">

						<%
							CommentDao comDao = new CommentDao();
							int numPage = (Integer) request.getAttribute("numPage");
							int currentPage = (Integer) request.getAttribute("currentPage");
							if(request.getAttribute("alNews") != null){
								@SuppressWarnings("unchecked")
								ArrayList<News> alNews = (ArrayList<News>)request.getAttribute("alNews");
								if(alNews.size() > 0){
									for(News itemNews: alNews){
										String urlSlug3 = request.getContextPath() + "/" + StringLibrary.makeSlug(itemNews.getCname()) + "/" + StringLibrary.makeSlug(itemNews.getName()) + "-" + itemNews.getId_news() + ".html";
						%>
							<div class="col-md-6 col-sm-12">
								<div class="card h-100">
									<div class="single-post post-style-1">
	
										<div class="blog-image"><img src="<%=request.getContextPath()%>/files/<%=itemNews.getPicture()%>" alt="Blog Image"></div>
	
										<a class="avatar" href="#"><img src="<%=request.getContextPath()%>/files/<%=itemNews.getAvatar()%>" alt="Profile Image"></a>
	
										<div class="blog-info">
	
											<h4 class="title"><a href="<%=urlSlug3%>"><b><%=itemNews.getName()%></b></a></h4>
	
											<ul class="post-footer">
												<li><a class="item-like<%=itemNews.getId_news()%>" href="javascript:void(0)" onclick="return doUpdate(<%=itemNews.getId_news()%>,<%=itemNews.getHeart()%>);"><i class="ion-heart"></i><%=itemNews.getHeart()%></a></li>
												<li><a><i class="ion-chatbubble"></i><%=comDao.countCmt(itemNews.getId_news())%></a></li>
												<li><a><i class="ion-eye"></i><%=itemNews.getView()%></a></li>
											</ul>
	
										</div><!-- blog-info -->
									</div><!-- single-post -->
								</div><!-- card -->
							</div><!-- col-md-6 col-sm-12 -->
						<%}}}%>

					</div><!-- row -->

					<div class="text-center">
						<ul class="pagination pagination-info">
							<%
								if(currentPage > 1){
									String urlSlug = request.getContextPath() + "/" + StringLibrary.makeSlug(item.getName()) + "-" + item.getId_cat() + "/page-" + (currentPage -1) + ".html";
							%>
								<li><a href="<%=urlSlug%>">< prev</a></li>
							<%}%>
								<%
									String active = "";
									for(int i = 1; i <= numPage; i++){
										String urlSlug = request.getContextPath() + "/" + StringLibrary.makeSlug(item.getName()) + "-" + item.getId_cat() + "/page-" + i + ".html";
										if(currentPage == i){
											active = "class='active'";
										}else{
											active = "";
										}
								%>
									
									<li <%=active%>><a href="<%=urlSlug%>"><%=i%></a></li>
									
								<%}%>
							<%
								if(currentPage < numPage){
									String urlSlug = request.getContextPath() + "/" + StringLibrary.makeSlug(item.getName()) + "-" + item.getId_cat() + "/page-" + (currentPage + 1) + ".html";
							%>
								<li><a href="<%=urlSlug%>">next ></a></li>
							<%}%>
	                    </ul>
	                </div>

				</div><!-- col-lg-8 col-md-12 -->

				<%@include file="/templates/public/inc/rightbar.jsp" %>

			</div><!-- row -->

		</div><!-- container -->
	</section><!-- section -->


	<%@include file="/templates/public/inc/footer2.jsp" %>
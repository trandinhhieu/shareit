<%@page import="model.dao.CommentDao"%>
<%@page import="model.bean.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
<%@include file="/templates/public/inc/header.jsp" %>

	<section class="blog-area section">
		<div class="container">
			
			<div class="row">
				
				<div class="col-lg-8 col-md-12">
					<div class="row">
						<div class="col-md-12 col-sm-12">
							<div class="container">
							  <div id="myCarousel" class="carousel slide" data-ride="carousel">
							    <!-- Wrapper for slides -->
							    <div class="carousel-inner">
							      <%
							      	int dem = 0;
									if(request.getAttribute("alSlide") != null){
										@SuppressWarnings("unchecked")
										ArrayList<News> alSlide = (ArrayList<News>)request.getAttribute("alSlide");
										if(alSlide.size() > 0){
											for(News itemSlide: alSlide){
												String urlSlug6 = request.getContextPath() + "/" + StringLibrary.makeSlug(itemSlide.getCname()) + "/" + StringLibrary.makeSlug(itemSlide.getName()) + "-" + itemSlide.getId_news() + ".html";
												dem++;
												if(dem == 1){
								%>
							      <div class="item active">
							        <img src="<%=request.getContextPath()%>/files/<%=itemSlide.getPicture()%>" alt="image Slide" style="width:100%; height: 400px">
							        <div style="width: 720px;height: 93px;opacity: 0.7;background-color: #000000;position: absolute;bottom: 4px;">
							        	<div class="carousel-caption">
								          <h3><a class="slide-title" href="<%=urlSlug6%>"><%=itemSlide.getName()%></a></h3>
								        </div>
							        </div>
							        
							      </div>
							  	<%}else{%>
							  		<div class="item">
							        <img src="<%=request.getContextPath()%>/files/<%=itemSlide.getPicture()%>" alt="image Slide" style="width:100%; height: 400px">
							        <div style="width: 720px;height: 93px;opacity: 0.7;background-color: #000000;position: absolute;bottom: 4px;">
							        	<div class="carousel-caption">
								          <h3><a class="slide-title" href="<%=urlSlug6%>"><%=itemSlide.getName()%></a></h3>
								        </div>
							        </div>
							      </div>
								<%}}}}%>
							  
							    </div>
							
							    <!-- Left and right controls -->
							    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
							      <span class="glyphicon glyphicon-chevron-left"></span>
							      <span class="sr-only">Previous</span>
							    </a>
							    <a class="right carousel-control" href="#myCarousel" data-slide="next">
							      <span class="glyphicon glyphicon-chevron-right"></span>
							      <span class="sr-only">Next</span>
							    </a>
							  </div>
							</div>
						</div>
						<div class="col-md-12 col-sm-12">
							<div class="box-select">
								<div class="dropdown">
								  <button class="dropbtn">--- Sắp xếp theo ---</button>
								  <div class="dropdown-content">
								    <a href="<%=request.getContextPath()%>/public/?value=1">Tin hot nhất</a>
								    <a href="<%=request.getContextPath()%>/public/?value=2">Tin mới nhất</a>
								    <a href="<%=request.getContextPath()%>/public/?value=3">Tin cũ nhất</a>
								  </div>
								</div>
							</div>
						</div>
						<ul class="list-news">
							<%
								int numPage = (Integer) request.getAttribute("numPage");
								int currentPage = (Integer) request.getAttribute("currentPage");
								CommentDao comDao = new CommentDao();
								if(request.getAttribute("alNews") != null){
									@SuppressWarnings("unchecked")
									ArrayList<News> alNews = (ArrayList<News>)request.getAttribute("alNews");
									if(alNews.size() > 0){
										for(News itemNews: alNews){
											
											String urlSlug3 = request.getContextPath() + "/" + StringLibrary.makeSlug(itemNews.getCname()) + "/" + StringLibrary.makeSlug(itemNews.getName()) + "-" + itemNews.getId_news() + ".html";
							%>
								<li class="col-md-6 col-sm-12">
									<div class="card h-100">
										<div class="single-post post-style-1">
		
											<div class="blog-image"><img src="<%=request.getContextPath()%>/files/<%=itemNews.getPicture()%>" alt="Blog Image"  /></div>
		
											<div class="avatar"><img src="<%=request.getContextPath()%>/files/<%=itemNews.getAvatar()%>" alt="Profile Image"></div>
		
											<div class="blog-info">
		
												<div class="area-blog">
													<h4 class="title"><a href="<%=urlSlug3%>"><b><%=itemNews.getName()%></b></a></h4>
												</div>
												<div class="area-blog">
													<p><%=itemNews.getPreview_text()%></p>
												</div>
												<ul class="post-footer">
													<li><a class="item-like<%=itemNews.getId_news()%>" href="javascript:void(0)" onclick="return doUpdate(<%=itemNews.getId_news()%>,<%=itemNews.getHeart()%>);"><i class="ion-heart"></i><%=itemNews.getHeart()%></a></li>
													<li><a><i class="ion-chatbubble"></i><%=comDao.countCmt(itemNews.getId_news())%></a></li>
													<li><a><i class="ion-eye"></i><%=itemNews.getView()%></a></li>
												</ul>
		
											</div><!-- blog-info -->
										</div><!-- single-post -->
									</div><!-- card -->
								</li><!-- col-md-6 col-sm-12 -->
							<%}}}%>
						</ul>
					</div><!-- row -->

					<div class="text-center">
						<ul class="pagination pagination-info">
							<%
								if(currentPage > 1){
									String urlSlug = request.getContextPath() + "/news/page-" + (currentPage - 1) + ".html";
							%>
								
								<li><a href="<%=urlSlug%>">< prev</a></li>
							<%}%>
								<%
									String active = "";
									for(int i = 1; i <= numPage; i++){
										String urlSlug1 = request.getContextPath() + "/news/page-" + i + ".html";
										if(currentPage == i){
											active = "class='active'";
										}else{
											active = "";
										}
								%>
									
									<li <%=active%>><a href="<%=urlSlug1%>"><%=i%></a></li>
									
								<%}%>
							<%
								if(currentPage < numPage){
									String urlSlug2 = request.getContextPath() + "/news/page-" + (currentPage + 1) + ".html";
							%>
								<li><a href="<%=urlSlug2%>">next ></a></li>
							<%}%>
	                    </ul>
	                </div>

				</div><!-- col-lg-8 col-md-12 -->

				<%@include file="/templates/public/inc/rightbar.jsp" %>

			</div><!-- row -->

		</div><!-- container -->
	</section><!-- section -->
	<script>
		function doUpdate(id,heart){
			$.ajax({
				url: '<%=request.getContextPath()%>/public/news/like',
				type: 'POST',
				cache: false,
				data: {
					//Dữ liệu gửi đi
					id: id,
					heart: heart
				},
				success : function(data) {
					$(".item-like" + id).html(data);
				}
			});
			
		}
	</script>
	<script>
	function getList(value){
		$.ajax({
			url: '<%=request.getContextPath()%>/public/news/list',
			type: 'POST',
			cache: false,
			data: {
				//Dữ liệu gửi đi
				value: value
			},
			success : function(data) {
				$(".item-like" + id).html(data);
			}
		});
		
	}
	</script>
	<%@include file="/templates/public/inc/footer2.jsp" %>
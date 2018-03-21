<!DOCTYPE HTML>
<%@page import="library.StringLibrary"%>
<%@page import="model.dao.CommentDao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.bean.Comment"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.News"%>
<html lang="en">
<head>
	<link rel="apple-touch-icon" sizes="76x76" href="<%=request.getContextPath()%>/templates/admin/assets/img/apple-icon.png" />
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/templates/admin/assets/img/favicon.png" />
	<title>share IT</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8">


	<!-- Font -->

	<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">


	<!-- Stylesheets -->

	<link href="<%=request.getContextPath()%>/templates/public/common-css/bootstrap.css" rel="stylesheet">

	<link href="<%=request.getContextPath()%>/templates/public/common-css/ionicons.css" rel="stylesheet">


	<link href="<%=request.getContextPath()%>/templates/public/single-post-2/css/styles.css" rel="stylesheet">

	<link href="<%=request.getContextPath()%>/templates/public/single-post-2/css/responsive.css" rel="stylesheet">

</head>
<body >

	<%@include file="/templates/public/inc/header.jsp" %>

	<div style="height: 400px; width: 100%; background-image: url(<%=request.getContextPath()%>/templates/public/images/slider-1-1600x900.jpg); background-size: cover;">

	</div><!-- slider -->
	
	<section class="post-area">
		<div class="container">

			<div class="row">
				<%
					News objNews = (News)request.getAttribute("objNews");
					String date_create = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(objNews.getCreate_date());
					int count = (int)request.getAttribute("count");
				%>

				<div class="col-lg-1 col-md-0"></div>
				<div class="col-lg-10 col-md-12">

					<div class="main-post">

						<div class="post-top-area">

							<h5 class="pre-title"><%=objNews.getCname()%></h5>

							<h3 class="title"><b><%=objNews.getName()%></b></h3>

							<div class="post-info">

								<div class="left-area">
									<a class="avatar" href="#"><img src="<%=request.getContextPath()%>/files/<%=objNews.getAvatar()%>" alt="Profile Image"></a>
								</div>

								<div class="middle-area">
									<a class="name" href="#"><b><%=objNews.getUname()%></b></a>
									<h6 class="date">&nbsp;<%=date_create%></h6>
								</div>

							</div><!-- post-info -->

							<p class="para"><%=objNews.getDetail_text()%></p>

						</div><!-- post-top-area -->

						<div class="post-image"><img src="<%=request.getContextPath()%>/files/<%=objNews.getPicture()%>" alt="Blog Image"></div>

						<div class="post-bottom-area">

							<p class="para"><%=objNews.getDetail_text()%></p>

							<ul class="tags">
								<li><a href="#"><%=objNews.getCname()%></a></li>
							</ul>

							<div class="post-icons-area">
								<ul class="post-icons">
									<li><a class="item-like<%=objNews.getId_news()%>" href="javascript:void(0)" onclick="return doUpdate(<%=objNews.getId_news()%>,<%=objNews.getHeart()%>);"><i class="ion-heart"></i><%=objNews.getHeart()%></a></li>
									<li><a><i class="ion-chatbubble"></i><%=count%></a></li>
									<li><a><i class="ion-eye"></i><%=objNews.getView()%></a></li>
								</ul>
								<ul class="icons">
									<li>SHARE : </li>
									<li><div class="fb-share-button" data-href="https://www.facebook.com/vinaenter.edu/" data-layout="button" data-size="small" data-mobile-iframe="true"><a class="fb-xfbml-parse-ignore" target="_blank" href="https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fwww.facebook.com%2Fvinaenter.edu%2F&amp;src=sdkpreparse">Chia sẻ</a></div></li>
								</ul>
							</div>

							<div class="post-footer post-info">

								<div class="left-area">
									<a class="avatar" href="#"><img src="<%=request.getContextPath()%>/files/<%=objNews.getAvatar()%>" alt="Profile Image"></a>
								</div>

								<div class="middle-area">
									<a class="name" href="#"><b><%=objNews.getUname()%></b></a>
									<h6 class="date">&nbsp;<%=date_create%></h6>
								</div>

							</div><!-- post-info -->

						</div><!-- post-bottom-area -->

					</div><!-- main-post -->
				</div><!-- col-lg-8 col-md-12 -->
			</div><!-- row -->
		</div><!-- container -->
	</section><!-- post-area -->


	<section class="recomended-area section">
		<div class="container">
			<div class="row">
				<%	
					CommentDao comDao = new CommentDao();
					if(request.getAttribute("alNews") != null){
						@SuppressWarnings("unchecked")
						ArrayList<News> alNews = (ArrayList<News>)request.getAttribute("alNews");
						if(alNews.size() > 0){
							for(News objN: alNews){
								if(objN.getId_news() != objNews.getId_news()){
									String urlSlug4 = request.getContextPath() + "/" + StringLibrary.makeSlug(objN.getCname()) + "/" + StringLibrary.makeSlug(objN.getName()) + "-" + objN.getId_news() + ".html";
				%>
					<div class="col-lg-4 col-md-6">
						<div class="card h-100">
							<div class="single-post post-style-1">
	
								<div class="blog-image"><img src="<%=request.getContextPath()%>/files/<%=objN.getPicture()%>" alt="Blog Image"></div>
	
								<a class="avatar" href="#"><img src="<%=request.getContextPath()%>/files/<%=objN.getAvatar()%>" alt="Profile Image"></a>
	
								<div class="blog-info">
	
									<h4 class="title"><a href="<%=urlSlug4%>"><b><%=objN.getName()%></b></a></h4>
	
									<ul class="post-footer">
										<li><a class="item-like<%=objN.getId_news()%>" href="javascript:void(0)" onclick="return doUpdate(<%=objN.getId_news()%>,<%=objN.getHeart()%>);"><i class="ion-heart"></i><%=objN.getHeart()%></a></li>
										<li><a><i class="ion-chatbubble"></i><%=comDao.countCmt(objN.getId_news())%></a></li>
										<li><a><i class="ion-eye"></i><%=objN.getView()%></a></li>
									</ul>
	
								</div><!-- blog-info -->
							</div><!-- single-post -->
						</div><!-- card -->
					</div><!-- col-md-6 col-sm-12 -->	
				<%}}}}%>
			</div><!-- row -->

		</div><!-- container -->
	</section>

	<section class="comment-section center-text">
		<div class="container">
			<div class="fb-comments" data-href="https://www.facebook.com/vinaenter.edu/?nid=<%=objNews.getId_news()%>" data-width="730px" data-numposts="5"></div>
			<h4><b>POST COMMENT</b></h4>
			<div class="row">

				<div class="col-lg-2 col-md-0"></div>

				<div class="col-lg-8 col-md-12">
					<div class="comment-form">
						<form>
							<div class="row">

								<div class="col-sm-6">
									<input id="contact-form-name" type="text" aria-required="true" name="contact-form-name" class="form-control"
										placeholder="Enter your name" aria-invalid="true" required >
								</div><!-- col-sm-6 -->
								<div class="col-sm-6">
									<input id="contact-form-email" type="email" aria-required="true" name="contact-form-email" class="form-control"
										placeholder="Enter your email" aria-invalid="true" required>
								</div><!-- col-sm-6 -->

								<div class="col-sm-12">
									<textarea id="contact-form-message" name="contact-form-message" rows="2" class="text-area-messge form-control"
										placeholder="Enter your comment" aria-required="true" aria-invalid="false"></textarea >
								</div><!-- col-sm-12 -->
								<div class="col-sm-12">
									<button class="submit-btn" type="submit" id="form-submit"><b>POST COMMENT</b></button>
								</div><!-- col-sm-12 -->

							</div><!-- row -->
						</form>
					</div><!-- comment-form -->
					
					<h4><b>COMMENTS(<%=count%>)</b></h4>
					
					<ul class="com-list">
						<%
							String date = "";
							if(request.getAttribute("alCom") != null){
								@SuppressWarnings("unchecked")
								ArrayList<Comment> alCom = (ArrayList<Comment>)request.getAttribute("alCom");
								@SuppressWarnings("unchecked")
								ArrayList<Comment> alRep = (ArrayList<Comment>)request.getAttribute("alRep");
								if(alCom.size() > 0){
									for(Comment itemCom: alCom){
										date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(itemCom.getDate_create());
										if(itemCom.getParent_id() == 0){
						%>
							<li class="commnets-area text-left">
								<ul class="list-item" id="list-item<%=itemCom.getId_com()%>">
									<li class="comment">
			
										<div class="post-info">
											<div class="middle-area">
												<a class="name" href="#"><b><%=itemCom.getName()%></b></a>
												<h6 class="date">&nbsp;<%=date%></h6>
											</div>
			
											<div class="right-area">
												<h5 class="reply-btn" ><a href="javascript:void(0)" class="rep-a" data-a="<%=itemCom.getId_com()%>"><b>REPLY</b></a></h5>
											</div>
			
										</div><!-- post-info -->
			
										<p><%=itemCom.getContent()%></p>
									</li>
									<!-- REPLY-area -->
									
										<%
											String dateRep = "";
											for(Comment repItem: alRep){
												if(repItem.getParent_id() == itemCom.getId_com()){
													dateRep = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(repItem.getDate_create());
										%>
											<li class="comment">
												<h5 class="reply-for">Reply for <a href="#"><b><%=itemCom.getName()%></b></a></h5>
					
												<div class="post-info">
					
													<div class="middle-area">
														<a class="name" href="#"><b><%=repItem.getName()%></b></a>
														<h6 class="date">&nbsp;<%=dateRep%></h6>
													</div>
					
												</div><!-- post-info -->
					
												<p><%=repItem.getContent()%></p>
					
											</li>
										<%}}%>
								</ul>
								<div class="f-rep<%=itemCom.getId_com()%>" id="f-rep">
									<div class="comment-form">
										<form>
											<div class="row">
				
												<div class="col-sm-6">
													<input id="rep-form-name<%=itemCom.getId_com()%>" type="text" aria-required="true" name="contact-form-name" class="form-control"
														placeholder="Enter your name" aria-invalid="true" required >
												</div><!-- col-sm-6 -->
												<div class="col-sm-6">
													<input id="rep-form-email<%=itemCom.getId_com()%>" type="email" aria-required="true" name="contact-form-email" class="form-control"
														placeholder="Enter your email" aria-invalid="true" required>
												</div><!-- col-sm-6 -->
				
												<div class="col-sm-12">
													<textarea id="rep-form-message<%=itemCom.getId_com()%>" name="contact-form-message" rows="2" class="text-area-messge form-control"
														placeholder="Enter your comment" aria-required="true" aria-invalid="false"></textarea >
												</div><!-- col-sm-12 -->
												<div class="col-sm-12">
													<button class="submit-btn" type="submit" id="form-submit" data-comname="<%=itemCom.getName()%>" data-comid="<%=itemCom.getId_com()%>"><b>REPLY</b></button>
												</div><!-- col-sm-12 -->
				
											</div><!-- row -->
										</form>
									</div><!-- comment-form -->
								</div>
							</li><!-- commnets-area -->
						<%}}}}%>
					</ul>

					<a class="more-comment-btn" href="#"><b>VIEW MORE COMMENTS</a>

				</div><!-- col-lg-8 col-md-12 -->

			</div><!-- row -->
		<script type="text/javascript">
			$().ready(function() {
				$("#form-submit").click(function() {
					var name = $("#contact-form-name").val();
					var email = $("#contact-form-email").val();
					var message = $("#contact-form-message").val();
					$("#contact-form-name").val("");
					$("#contact-form-email").val("");
					$("#contact-form-message").val("");
					var id = <%=objNews.getId_news()%>;
					$.ajax({
						url : "<%=request.getContextPath()%>/public/comment/",
						type : "POST",
						data : "name=" + name + "&email=" + email + "&message=" + message + "&id=" + id,
						async : true,
						success : function(data) {
							if ($(".com-list li").length == 0) {
								$(".com-list").html(data);
							} else {
								$(".com-list li:eq(0)").before(data);
							}
	
						}
					})
					return false;
				});				
				
				$(".submit-btn").click(function(){
					var id_com = $(this).attr("data-comid");
					var com_name = $(this).attr("data-comname");
					var name = $("#rep-form-name" + id_com).val();
					var email = $("#rep-form-email" + id_com).val();
					var message = $("#rep-form-message" + id_com).val();
					var id = <%=objNews.getId_news()%>;
					$("#rep-form-name" + id_com).val("");
					$("#rep-form-email" + id_com).val("");
					$("#rep-form-message" + id_com).val("");
					
					$.ajax({
						url : "<%=request.getContextPath()%>/public/reply/",
						type : "POST",
						data : "id_com=" + id_com + "&name=" + name + "&email=" + email + "&message=" + message + "&id=" + id + "&com_name=" + com_name,
						async : true,
						success : function(data) {
								$("#list-item" + id_com).append(data);
						}
					})
					return false;
				});
				
				$(".rep-a").click(function(){
					var id = $(this).attr("data-a");
					$(".f-rep" + id).slideToggle();
				});
				
			});
		</script>
		<script type="text/javascript">
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
		</div><!-- container -->
	</section>

	<%@include file="/templates/public/inc/footer2.jsp" %>

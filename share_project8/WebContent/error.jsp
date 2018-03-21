<%@page import="model.dao.CommentDao"%>
<%@page import="model.bean.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
<%@include file="/templates/public/inc/header.jsp" %>
	
	<section class="blog-area section">
		<div class="container">

			<div class="row">

				<div class="col-lg-8 col-md-12">
					
					<div class="col-lg-12 col-md-12">
							<div class="card h-100">
								<div class="single-post post-style-2">

									<div class="alert alert-danger">
									    <div class="container-fluid">
										  <div class="alert-icon">
										    <i class="material-icons">error_outline</i>
										  </div>
										  <button id="button-404" type="button" class="close" data-dismiss="alert" aria-label="Close">
											<span aria-hidden="true"><i class="material-icons">clear</i></span>
										  </button>
									      <b>Error Alert:</b> Damn man! 404 not found
									    </div>
									</div>
									<div class="text-center">
										<a href="<%=request.getContextPath()%>/tin-tuc" class="btn btn-warning">Home</a>
									</div>

								</div><!-- single-post extra-blog -->

							</div><!-- card -->
						</div><!-- col-lg-12 col-md-12 -->
					

				</div><!-- col-lg-8 col-md-12 -->

				<%@include file="/templates/public/inc/rightbar.jsp" %>

			</div><!-- row -->

		</div><!-- container -->
	</section><!-- section -->


	<%@include file="/templates/public/inc/footer2.jsp" %>
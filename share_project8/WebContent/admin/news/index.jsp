<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.bean.News"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/admin/inc/slidebar.jsp"%>

<!--
        Tip 1: You can change the color of the sidebar using: data-color="purple | blue | green | orange | red"

        Tip 2: you can also add an image using data-image tag
    -->

<div class="main-panel">

	<%@include file="/templates/admin/inc/nav.jsp"%>

	<div class="content">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
						<div>
							<a href="<%=request.getContextPath()%>/admin/news/show-add" class="btn btn-primary btn-round">
								<i class="material-icons">note_add</i> ADD
							</a>
						</div>
						<div>
							<%
								if(request.getParameter("msg") != null){
									int msg = Integer.parseInt(request.getParameter("msg"));
									switch(msg){
										case 1: out.print("<div class='alert alert-success'><div class='container-fluid'><div class='alert-icon'><i class='material-icons'>check</i></div><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'><i class='material-icons'>clear</i></span></button><b>Success Alert:</b> Yuhuuu! Bạn đã thêm thành công</div></div>");
											break;
										case 2: out.print("<div class='alert alert-success'><div class='container-fluid'><div class='alert-icon'><i class='material-icons'>check</i></div><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'><i class='material-icons'>clear</i></span></button><b>Success Alert:</b> Yuhuuu! Bạn đã sửa thành công</div></div>");
											break;
										case 3: out.print("<div class='alert alert-success'><div class='container-fluid'><div class='alert-icon'><i class='material-icons'>check</i></div><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'><i class='material-icons'>clear</i></span></button><b>Success Alert:</b> Yuhuuu! Bạn đã xóa thành công</div></div>");
											break;
										default: out.print("<div class='alert alert-danger'><div class='container-fluid'><div class='alert-icon'><i class='material-icons'>error_outline</i></div><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'><i class='material-icons'>clear</i></span></button><b>Error Alert:</b> Damn man! Đã xảy ra lỗi</div></div>");
											break;
									}
								}
							%>
						</div>
						<div class="card">
							<div class="card-header" data-background-color="purple">
								<h4 class="title">News</h4>
							</div>
							<div class="card-content table-responsive">
								<table class="table">
								    <thead>
								        <tr>
								            <th class="text-center" width="10%">ID</th>
								            <th>Name</th>
								            <th width="10%">Category</th>
								            <th width="10%" class="text-center">Image</th>
								            <th width="15%">Create date</th>
								            <th width="10%">Slide</th>
								            <th width="10%">Actions</th>
								        </tr>
								    </thead>
								    <tbody>
								        <%
									        int numPage = (Integer) request.getAttribute("numPage");
											int currentPage = (Integer) request.getAttribute("currentPage");
								        	String date = "";
								        	if(request.getAttribute("alNews") != null){
								        		@SuppressWarnings("unchecked")
								        		ArrayList<News> alNews = (ArrayList<News>)request.getAttribute("alNews");
								        		if(alNews.size() > 0 ){
								        			for(News objNews: alNews){
								        				date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(objNews.getCreate_date());
								        %>
									        <tr>
									            <td class="text-center"><%=objNews.getId_news()%></td>
									            <td><%=objNews.getName()%></td>
									            <td><%=objNews.getCname()%></td>
									            <td>
								                    <img src="<%=request.getContextPath()%>/files/<%=objNews.getPicture()%>" alt="Rounded Image" class="img-rounded img-responsive" />
									            </td>
									            <td class="text-primary"><%=date%></td>
									            <td>
									            	<form>
											            <div class="togglebutton">
															<label>
																<%
																	String status = "";
																	if(objNews.getSlide() > 0){
																%>
														    		<input class="slide<%=objNews.getId_news()%>" name="slide" type="checkbox" checked='checked' onclick="return doUpdate(<%=objNews.getId_news()%>);" />
														    	<%}else{%>
														    		<input class="slide<%=objNews.getId_news()%>" name="slide" type="checkbox" onclick="return doUpdate(<%=objNews.getId_news()%>);" />
														    	<%}%>
															</label>
														</div>
													</form>
												</td>
									            <td class="td-actions">
									                <a href="<%=request.getContextPath()%>/admin/news/show-edit?nid=<%=objNews.getId_news()%>" rel="tooltip" title="Edit" class="btn btn-success btn-simple btn-xs" >
									                    <i class="fa fa-edit"></i>
									                </a>
									                <a href="<%=request.getContextPath()%>/admin/news/del?nid=<%=objNews.getId_news()%>" rel="tooltip" title="Remove" class="btn btn-danger btn-simple btn-xs">
									                    <i class="fa fa-times"></i>
									                </a>
									            </td>
									        </tr>
									        <script>
												function doUpdate(id_tmp){
													//var id_tmp = $(this).attr("id");
													//Kiểu này nó không lấy được, Hiếu truyền theo kiểu pramam thử
													console.log('id_tmp: '+ id_tmp);
													var slide_tmp = 0;
													if ($('.slide' + id_tmp).prop('checked') == true) {
														slide_tmp = 1;
													}
													console.log('slide_tmp: '+slide_tmp);
													$.ajax({
														url: '<%=request.getContextPath()%>/admin/news/slide-update',
														type: 'POST',
														cache: false,
														data: {
															//Dữ liệu gửi đi
															id: id_tmp,
															slide: slide_tmp
														}
													});
													
												}
											</script>
								        <%}}}%>
								    </tbody>
								</table>
							</div>
						</div>
						<div class="text-center">
							<ul class="pagination pagination-info">
								<%
									if(currentPage > 1){
								%>
									<li><a href="<%=request.getContextPath()%>/admin/news?page=<%=currentPage - 1%>">< prev</a></li>
								<%}%>
									<%
										String active = "";
										for(int i = 1; i <= numPage; i++){
											if(currentPage == i){
												active = "class='active'";
											}else{
												active = "";
											}
									%>
										
										<li <%=active%>><a href="<%=request.getContextPath()%>/admin/news?page=<%=i%>"><%=i%></a></li>
										
									<%}%>
								<%
									if(currentPage < numPage){
								%>
									<li><a href="<%=request.getContextPath()%>/admin/news?page=<%=currentPage + 1%>">next ></a></li>
								<%}%>
		                    </ul>
		                </div>
	            </div>
				</div>
			</div>
		</div>
	</div>
	
	<%@include file="/templates/admin/inc/footer.jsp"%>
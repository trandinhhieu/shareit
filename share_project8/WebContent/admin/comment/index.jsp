<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.bean.Comment"%>
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
								<h4 class="title">Comment</h4>
							</div>
							<div class="card-content table-responsive">
								<table class="table">
								    <thead>
								        <tr>
								            <th class="text-center" width="10%">ID</th>
								            <th>Name</th>
								            <th width="10%">Email</th>
								            <th width="30%">Content</th>
								            <th width="15%">News</th>
								            <th width="10%">Create date</th>
								            <th width="10%">Active</th>
								            <th width="10%">Actions</th>
								        </tr>
								    </thead>
								    <tbody>
								        <%
									        int numPage = (Integer) request.getAttribute("numPage");
											int currentPage = (Integer) request.getAttribute("currentPage");
								        	String date_create = "";
								        	if(request.getAttribute("alCom") != null){
								        		@SuppressWarnings("unchecked")
								        		ArrayList<Comment> alCom = (ArrayList<Comment>)request.getAttribute("alCom");
								        		if(alCom.size() > 0 ){
								        			for(Comment objCom: alCom){
								        				date_create = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(objCom.getDate_create());
								        %>
									        <tr>
									            <td class="text-center"><%=objCom.getId_com()%></td>
									            <td><%=objCom.getName()%></td>
									            <td><%=objCom.getEmail()%></td>
									            <td><%=objCom.getContent()%></td>
									            <td>
								                    <img src="<%=request.getContextPath()%>/files/<%=objCom.getPicture()%>" alt="Rounded Image" class="img-rounded img-responsive" />
									            </td>
									            <td class="text-primary"><%=date_create%></td>
									            <td>
									            	<form>
											            <div class="togglebutton">
															<label>
																<%
																	String status = "";
																	if(objCom.getActive() > 0){
																%>
														    		<input class="slide<%=objCom.getId_com()%>" name="slide" type="checkbox" checked='checked' onclick="return doUpdate(<%=objCom.getId_com()%>);" />
														    	<%}else{%>
														    		<input class="slide<%=objCom.getId_com()%>" name="slide" type="checkbox" onclick="return doUpdate(<%=objCom.getId_com()%>);" />
														    	<%}%>
															</label>
														</div>
													</form>
												</td>
									            <td class="td-actions">
									                <a href="<%=request.getContextPath()%>/admin/comment/del?cid=<%=objCom.getId_com()%>" rel="tooltip" title="Remove" class="btn btn-danger btn-simple btn-xs">
									                    <i class="fa fa-times"></i>
									                </a>
									            </td>
									        </tr>
									        <script>
												function doUpdate(id_tmp){
													var slide_tmp = 0;
													if ($('.slide' + id_tmp).prop('checked') == true) {
														slide_tmp = 1;
													}
													$.ajax({
														url: '<%=request.getContextPath()%>/admin/comment/update',
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
									<li><a href="<%=request.getContextPath()%>/admin/comment?page=<%=currentPage - 1%>">< prev</a></li>
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
										
										<li <%=active%>><a href="<%=request.getContextPath()%>/admin/comment?page=<%=i%>"><%=i%></a></li>
										
									<%}%>
								<%
									if(currentPage < numPage){
								%>
									<li><a href="<%=request.getContextPath()%>/admin/comment?page=<%=currentPage + 1%>">next ></a></li>
								<%}%>
		                    </ul>
		                </div>
	            </div>
				</div>
			</div>
		</div>
	</div>
	
	<%@include file="/templates/admin/inc/footer.jsp"%>
<%@page import="model.bean.User"%>
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
							<a href="<%=request.getContextPath()%>/admin/user/show-add" class="btn btn-primary btn-round">
								<i class="material-icons">note_add</i> ADD
							</a>
						</div>
						<div>
							<%
								User obj = (User)session.getAttribute("objUser");
								if(request.getParameter("msg") != null){
									int msg = Integer.parseInt(request.getParameter("msg"));
									switch(msg){
										case 1: out.print("<div class='alert alert-success'><div class='container-fluid'><div class='alert-icon'><i class='material-icons'>check</i></div><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'><i class='material-icons'>clear</i></span></button><b>Success Alert:</b> Yuhuuu! Bạn đã thêm thành công</div></div>");
											break;
										case 2: out.print("<div class='alert alert-success'><div class='container-fluid'><div class='alert-icon'><i class='material-icons'>check</i></div><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'><i class='material-icons'>clear</i></span></button><b>Success Alert:</b> Yuhuuu! Bạn đã sửa thành công</div></div>");
											break;
										case 3: out.print("<div class='alert alert-success'><div class='container-fluid'><div class='alert-icon'><i class='material-icons'>check</i></div><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'><i class='material-icons'>clear</i></span></button><b>Success Alert:</b> Yuhuuu! Bạn đã xóa thành công</div></div>");
											break;
										case 4: out.print("<div class='alert alert-danger'><div class='container-fluid'><div class='alert-icon'><i class='material-icons'>error_outline</i></div><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'><i class='material-icons'>clear</i></span></button><b>Error Alert:</b> Damn man! Không có quyền xóa admin</div></div>");
										break;
										default: out.print("<div class='alert alert-danger'><div class='container-fluid'><div class='alert-icon'><i class='material-icons'>error_outline</i></div><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'><i class='material-icons'>clear</i></span></button><b>Error Alert:</b> Damn man! Đã xảy ra lỗi</div></div>");
											break;
									}
								}
							%>
						</div>
						<div class="card">
							<div class="card-header" data-background-color="purple">
								<h4 class="title">User</h4>
							</div>
							<div class="card-content table-responsive">
								<table class="table">
									<thead class="text-primary">
										<th>Username</th>
										<th>Fullname</th>
										<th>Email</th>
										<th>Role</th>
										<th>Avatar</th>
										<th>Active</th>
										<th>Feature</th>
									</thead>
									<tbody>
										<%
											int numPage = (Integer) request.getAttribute("numPage");
											int currentPage = (Integer) request.getAttribute("currentPage");
											String nameRole = "";
											if(request.getAttribute("alUser") != null){
												@SuppressWarnings("unchecked")
												ArrayList<User> alUser = (ArrayList<User>)request.getAttribute("alUser");
												if(alUser.size() > 0){
													for(User objUser: alUser){
														if(objUser.getRole() > 0){
															nameRole = "Admin";
														}else{
															nameRole = "User";
														}
										%>
											<tr>
												<td><%=objUser.getUname()%></td>
												<td><%=objUser.getUfullname() %></td>
												<td><%=objUser.getEmail()%></td>
												<td><%=nameRole%></td>
												<td>
								                    <img src="<%=request.getContextPath()%>/files/<%=objUser.getAvatar()%>" alt="Rounded Image" class="img-rounded img-responsive" />
									            </td>
									            <td>
									            	<form>
											            <div class="togglebutton">
															<label>
																<%
																	String status = "";
																	if(objUser.getActive() > 0){
																%>
														    		<input class="slide<%=objUser.getUid()%>" name="slide" type="checkbox" checked='checked' onclick="return doUpdate(<%=objUser.getUid()%>);" />
														    	<%}else{%>
														    		<input class="slide<%=objUser.getUid()%>" name="slide" type="checkbox" onclick="return doUpdate(<%=objUser.getUid()%>);" />
														    	<%}%>
															</label>
														</div>
													</form>
												</td>
												<td>
													<%
														if(obj.getRole() > 0){
													%>
														<a rel="tooltip" title="Edit" href="<%=request.getContextPath()%>/admin/user/show-edit?uid=<%=objUser.getUid()%>">
								                            <i class="material-icons">build</i>
								                        </a>
								                        <a rel="tooltip" title="Remove" href="<%=request.getContextPath()%>/admin/user/del?uid=<%=objUser.getUid()%>">
								                            <i class="material-icons">delete</i>
								                        </a>
							                        <%}else if(obj.getUid() == objUser.getUid()){%>
							                        	<a rel="tooltip" title="Edit" href="<%=request.getContextPath()%>/admin/user/show-edit?uid=<%=objUser.getUid()%>">
								                            <i class="material-icons">build</i>
								                        </a>
							                        <%}%>
												</td>
											</tr>
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
									<li><a href="<%=request.getContextPath()%>/admin/user?page=<%=currentPage - 1%>">< prev</a></li>
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
										
										<li <%=active%>><a href="<%=request.getContextPath()%>/admin/user?page=<%=i%>"><%=i%></a></li>
										
									<%}%>
								<%
									if(currentPage < numPage){
								%>
									<li><a href="<%=request.getContextPath()%>/admin/user?page=<%=currentPage + 1%>">next ></a></li>
								<%}%>
		                    </ul>
		                </div>
	            </div>
				</div>
			</div>
		</div>
	</div>
	<script>
		function doUpdate(id_tmp){
			console.log('id_tmp: '+id_tmp);
			var slide_tmp = 0;
			if ($('.slide' + id_tmp).prop('checked') == true) {
				slide_tmp = 1;
			}
			console.log('slide_tmp: '+slide_tmp);
			$.ajax({
				url: '<%=request.getContextPath()%>/admin/user/active-update',
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
	<%@include file="/templates/admin/inc/footer.jsp"%>
<%@page import="model.bean.Category"%>
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
							<a href="<%=request.getContextPath()%>/admin/cat/show-add" class="btn btn-primary btn-round">
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
								<h4 class="title">Category</h4>
							</div>
							<div class="card-content table-responsive">
								<table class="table">
									<thead class="text-primary">
										<th>Name</th>
										<th>Feature</th>
									</thead>
									<tbody>
										<%
											int numPage = (Integer) request.getAttribute("numPage");
											int currentPage = (Integer) request.getAttribute("currentPage");
											if(request.getAttribute("alCat") != null){
												@SuppressWarnings("unchecked")
												ArrayList<Category> alCat = (ArrayList<Category>)request.getAttribute("alCat");
												if(alCat.size() > 0){
													for(Category objCat: alCat){
														if(objCat.getId_parent() == 0){
										%>
											<tr>
												<td><%=objCat.getName()%></td>
												<td>
													<a rel="tooltip" title="Edit" href="<%=request.getContextPath()%>/admin/cat/show-edit?cid=<%=objCat.getId_cat()%>">
							                            <i class="material-icons">build</i>
							                        </a>
							                        <a rel="tooltip" title="Remove" href="<%=request.getContextPath()%>/admin/cat/del?cid=<%=objCat.getId_cat()%>">
							                            <i id="delete" class="material-icons">delete</i>
							                        </a>
												</td>
											</tr>
											<%
												for(Category item: alCat){
													if(objCat.getId_cat() == item.getId_parent()){
											%>
												<tr>
													<td>&ensp;&ensp;- <%=item.getName()%></td>
													<td>
														<a rel="tooltip" title="Edit" href="<%=request.getContextPath()%>/admin/cat/show-edit?cid=<%=item.getId_cat()%>">
								                            <i class="material-icons">build</i>
								                        </a>
								                        <a rel="tooltip" title="Remove" href="<%=request.getContextPath()%>/admin/cat/del?cid=<%=item.getId_cat()%>">
								                            <i class="material-icons">delete</i>
								                        </a>
													</td>
												</tr>
											<%}}%>
										<%}}}}%>
									</tbody>
								</table>
							</div>
						</div>
						<div class="text-center">
							<ul class="pagination pagination-info">
								<%
									if(currentPage > 1){
								%>
									<li><a href="<%=request.getContextPath()%>/admin/cat?page=<%=currentPage - 1%>">< prev</a></li>
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
										
										<li <%=active%>><a href="<%=request.getContextPath()%>/admin/cat?page=<%=i%>"><%=i%></a></li>
										
									<%}%>
								<%
									if(currentPage < numPage){
								%>
									<li><a href="<%=request.getContextPath()%>/admin/cat?page=<%=currentPage + 1%>">next ></a></li>
								<%}%>
		                    </ul>
		                </div>
	            </div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/templates/admin/inc/footer.jsp"%>
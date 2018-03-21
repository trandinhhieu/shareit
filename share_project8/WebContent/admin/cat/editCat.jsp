<%@page import="model.bean.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/admin/inc/slidebar.jsp"%>
        <div class="main-panel">
            <%@include file="/templates/admin/inc/nav.jsp"%>
            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-header" data-background-color="purple">
                                    <h4 class="title">Edit Category</h4>
                                </div>
                                <div class="card-content">
                               		<%
                                   		if(request.getAttribute("objCat") != null){
                                   			Category objCat = (Category)request.getAttribute("objCat");
                                   	%>
	                                    <form method="post" action="<%=request.getContextPath()%>/admin/cat/edit?cid=<%=objCat.getId_cat()%>">
	                                        <div class="row">
	                                            <div class="col-md-6">
	                                            	
		                                                <div class="form-group label-floating">
		                                                    <label class="control-label">Name</label>
		                                                    <input value="<%=objCat.getName()%>" name="name" type="text" class="form-control" />
		                                                </div>
	                                               
	                                            </div>
	                                        </div>
	                                        <button type="submit" class="btn btn-primary">Edit</button>
	                                        <button type="reset" class="btn btn-primary">Cancel</button>
	                                        <div class="clearfix"></div>
	                                    </form>
                                     <%}%>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
   <%@include file="/templates/admin/inc/footer.jsp"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/admin/inc/slidebar.jsp"%>
        <div class="main-panel">
            <%@include file="/templates/admin/inc/nav.jsp"%>
            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header" data-background-color="purple">
                                    <h4 class="title">Add Category</h4>
                                </div>
                                <div class="card-content">
                                    <form method="post" action="<%=request.getContextPath()%>/admin/cat/add">
                                    	<div class="row">
                                            <div class="col-md-5">
                                                <div class="form-group label-floating">
                                                    <label class="control-label">ID (disabled)</label>
                                                    <input type="text" class="form-control" disabled>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group label-floating">
                                                    <label class="control-label">Name</label>
                                                    <input name="name" type="text" class="form-control" />
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                               <select name="danhmuc" class="form-control">
                                               		<option disabled="disabled" selected="selected">---Danh mục cha---</option>
                                                	<%
														if(request.getAttribute("alCat") != null){
															@SuppressWarnings("unchecked")
															ArrayList<Category> alCat = (ArrayList<Category>)request.getAttribute("alCat");
															for(Category itemCat:alCat){
																if(itemCat.getId_parent() == 0){
													%>
														<option value="<%=itemCat.getId_cat()%>"><%=itemCat.getName()%></option>
													<%}}}%>
                                                </select>
                                            </div>
                                        </div>
                                        <button type="submit" class="btn btn-primary">Add</button>
                                        <button type="reset" class="btn btn-primary">Cancel</button>
                                        <div class="clearfix"></div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
   <%@include file="/templates/admin/inc/footer.jsp"%>
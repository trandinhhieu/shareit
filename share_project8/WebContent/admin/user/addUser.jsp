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
                                    <h4 class="title">Add User</h4>
                                </div>
                                <div class="card-content">
                                    <form class="form" method="post" action="<%=request.getContextPath()%>/admin/user/add" enctype="multipart/form-data">
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
                                                    <label class="control-label">Username</label>
                                                    <input name="username" type="text" class="form-control" />
                                                    <label class="error" for="username"></label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group label-floating">
                                                    <label class="control-label">Fullname</label>
                                                    <input name="fullname" type="text" class="form-control" />
                                                    <label class="error" for="fullname"></label>
                                                </div>
                                            </div>
                                            <div class="col-md-6 checkbox">
                                            	<label>
                                            		Avatar
													<input type="file" name="avatar" checked>
												</label>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group label-floating">
                                                    <label class="control-label">Password</label>
                                                    <input name="password" id="password" type="password" class="form-control" />
                                                    <label class="error" for="password"></label>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                               <div class="form-group label-floating">
                                                    <label class="control-label">Confirm Password</label>
                                                    <input name="repassword" type="password" class="form-control" />
                                                    <label class="error" for="repassword"></label>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group label-floating">
                                                    <label class="control-label">Email</label>
                                                    <input name="email" id="email" type="text" class="form-control" />
                                                    <label class="error" for="email"></label>
                                                </div>
                                            </div>
                                            <div class="col-md-6">
                                               <div class="form-group label-floating">
                                                    <label class="control-label">Confirm Email</label>
                                                    <input name="remail" type="text" class="form-control" />
                                                    <label class="error" for="remail"></label>
                                                </div>
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
<%@page import="model.bean.Ads"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/templates/admin/inc/slidebar.jsp"%>
		<%
			Ads objAD = (Ads)request.getAttribute("objAD");
		%>
        <div class="main-panel">
            <%@include file="/templates/admin/inc/nav.jsp"%>
            <div class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="card">
                                <div class="card-header" data-background-color="purple">
                                    <h4 class="title">Edit Ads</h4>
                                </div>
                                <div class="card-content">
                                    <form method="post" action="<%=request.getContextPath()%>/admin/edit-ads?aid=<%=objAD.getId_ad()%>" enctype="multipart/form-data" >
                                        <div class="row">
                                            <div class="col-md-5">
                                                <div class="form-group label-floating">
                                                    <label class="control-label">ID (disabled)</label>
                                                    <input value="<%=objAD.getId_ad()%>" type="text" class="form-control" disabled>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group label-floating">
                                                    <label class="control-label">Name</label>
                                                    <input name="name" type="text" class="form-control" value="<%=objAD.getName()%>" />
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group">
		                                                 <input type="file" name="hinhanh" />
		                                                 <button class="btn btn-white">Image</button>
		                                                 <%
															if(!"".equals(objAD.getPicture())){
														%>
															<img class="news-image" src="<%=request.getContextPath()%>/files/<%=objAD.getPicture()%>" alt="Rounded Image" class="img-rounded img-responsive" />
														<%}%>
		                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <div class="form-group label-floating">
                                                    <label class="control-label">Link</label>
                                                    <input value="<%=objAD.getLink()%>" name="link" type="text" class="form-control" />
                                                </div>
                                            </div>
                                        </div>
                                        
                                        <button type="submit" class="btn btn-primary">Edit</button>
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
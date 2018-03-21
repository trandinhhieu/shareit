<%@page import="model.bean.News"%>
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
                                    <h4 class="title">Edit News</h4>
                                </div>
                                <div class="card-content">
	                                <%
										if(request.getAttribute("objNews") != null){
											News objNews = (News)request.getAttribute("objNews");
									%>
	                                    <form method="post" action="<%=request.getContextPath()%>/admin/news/edit?nid=<%=objNews.getId_news()%>" enctype="multipart/form-data" >
	                                        <div class="row">
	                                            <div class="col-md-5">
	                                                <div class="form-group label-floating">
	                                                    <label class="control-label">ID (disabled)</label>
	                                                    <input type="text" class="form-control" value="<%=objNews.getId_news()%>" disabled>
	                                                </div>
	                                            </div>
	                                        </div>
	                                        <div class="row">
	                                            <div class="col-md-6">
	                                                <div class="form-group label-floating">
	                                                    <label class="control-label">Name</label>
	                                                    <input name="name" value="<%=objNews.getName()%>" type="text" class="form-control" />
	                                                </div>
	                                            </div>
	                                            <div class="col-md-6">
	                                               <select name="danhmuc" class="form-control">
	                                                	<%
															if(request.getAttribute("alCat") != null){
																@SuppressWarnings("unchecked")
																ArrayList<Category> listCat = (ArrayList<Category>)request.getAttribute("alCat");
																String selected = "";
																if(listCat.size() > 0){
																	for(Category objCat: listCat){
																		if(objCat.getId_cat() == objNews.getId_cat()){
																			selected = "selected = 'selected'";
																		}else{
																			selected = "";
																		}
														%>
															<option <%=selected %> value="<%=objCat.getId_cat()%>"><%=objCat.getName()%></option>
														<%}}}%>
													</select>
	                                            </div>
	                                        </div>
	                                        <div class="row">
	                                            <div class="col-md-6">
	                                            	<div class="form-group">
		                                                 <input type="file" name="hinhanh" />
		                                                 <button class="btn btn-white">Image</button>
		                                                 <%
															if(!"".equals(objNews.getPicture())){
														%>
															<img class="news-image" src="<%=request.getContextPath()%>/files/<%=objNews.getPicture()%>" alt="Rounded Image" class="img-rounded img-responsive" />
														<%}%>
		                                            </div>
	                                            </div>
	                                        </div>
	                                        <div class="row">
	                                            <div class="col-md-12">
	                                                <div class="form-group">
	                                                    <label>Describe</label>
	                                                    <div class="form-group label-floating">
	                                                        <textarea name="mota" class="form-control" rows="5"><%=objNews.getPreview_text()%></textarea>
	                                                    </div>
	                                                </div>
	                                            </div>
	                                        </div>
	                                        <div class="row">
	                                            <div class="col-md-12">
	                                                <div class="form-group">
	                                                    <label>Detail</label>
	                                                    <div class="form-group label-floating">
	                                                        <textarea name="chitiet" class="form-control" rows="5"><%=objNews.getDetail_text()%></textarea>
	                                                    </div>
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
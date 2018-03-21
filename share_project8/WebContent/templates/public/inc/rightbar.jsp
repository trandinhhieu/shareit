<%@page import="library.StringLibrary"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.CatDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="col-lg-4 col-md-12 ">

					<div class="single-post info-area ">
						<div class="tag-area">

							<h4 class="title"><b>Category</b></h4>
							
							<%
								CatDao catDao = new CatDao();
								ArrayList<Category> alCat = catDao.getItems();
								if(alCat.size() > 0){
									for(Category itemCat: alCat){
										if(itemCat.getId_parent() == 0){
											String urlSlug = request.getContextPath() + "/" +StringLibrary.makeSlug(itemCat.getName()) + "-" + itemCat.getId_cat() + ".html";
							%>
								<div class="menu-item">
								  	<div class="item-title"><a href="<%=urlSlug%>"><%=itemCat.getName()%></a></div>
								  		<div class="menu-content">
									  		<%
									  			for(Category objCat: alCat){
									  				if(objCat.getId_parent() == itemCat.getId_cat()){
									  					String urlSlug1 = request.getContextPath() + "/" +StringLibrary.makeSlug(objCat.getName()) + "-" + objCat.getId_cat() + ".html";
									  		%>
											    <a href="<%=urlSlug1%>"><%=objCat.getName()%></a>
										  <%}}%>
									   </div>
								</div>
								
							<%}}}%>

						</div><!-- subscribe-area -->
						
						<div class="subscribe-area">

							<h4 class="title"><b>SUBSCRIBE</b></h4>
							<div class="fb-page" data-href="https://www.facebook.com/vinaenter.edu/" data-tabs="timeline" data-height="70px" data-small-header="false" data-adapt-container-width="true" data-hide-cover="false" data-show-facepile="true"><blockquote cite="https://www.facebook.com/vinaenter.edu/" class="fb-xfbml-parse-ignore"><a href="https://www.facebook.com/vinaenter.edu/">Trung tâm đào tạo VinaEnter</a></blockquote></div>
							<div class="fb-like" data-href="https://www.facebook.com/vinaenter.edu/" data-width="300px" data-layout="standard" data-action="like" data-size="small" data-show-faces="true" data-share="true"></div>

						</div><!-- subscribe-area -->

						<div class="about-area">
							<h4 class="title"><b>ABOUT VINAENTER</b></h4>
							<p>Công ty TNHH Giải pháp Công nghệ VinaENTER (tiền thân là Công ty CP Phú Hải Sơn, thành lập năm 2009) với mục đích cung cấp các sản phẩm công nghệ tốt nhất cho khách hàng. Với đội ngũ nhân viên trẻ trung, năng động, sáng tạo và đầy nhiệt huyết, từ năm 2009 đến nay, VinaENTER đã cung cấp sản phẩm website, các giải pháp phần mềm đến hơn 500 khách hàng trong và ngoài nước.</p>
						</div>
					</div><!-- info-area -->

				</div><!-- col-lg-4 col-md-12 -->
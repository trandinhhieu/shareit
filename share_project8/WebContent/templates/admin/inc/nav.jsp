<%@page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <nav class="navbar navbar-transparent navbar-absolute">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                        <a class="navbar-brand" href="#"> Manager </a>
                    </div>
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav navbar-right">
                            <li class="dropdown">
                                <a href="#pablo" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="material-icons">person</i>
                                   
                                    	<p class="hidden-lg hidden-md"></p>
                                   
                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="dashboard.html">
				                            <i class="material-icons">face</i>
				                             <%
		                                    	if(session.getAttribute("objUser") != null){
		                                    		User objUser = (User)session.getAttribute("objUser");
		                                    %>
				                            	<p><%=objUser.getUfullname()%></p>
				                            <%}%>
				                        </a>
                                    </li>
                                    <li>
                                        <a href="<%=request.getContextPath()%>/logout">
				                            <i class="material-icons">vpn_key</i>
				                            <p>Log out</p>
				                        </a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                        
                    </div>
                </div>
            </nav>
 
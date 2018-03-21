<%@page import="model.bean.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="<%=request.getContextPath()%>/templates/admin/assets/img/apple-icon.png" />
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/templates/admin/assets/img/favicon.png" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>ShareIT</title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />
    <!-- Bootstrap core CSS     -->
    <link href="<%=request.getContextPath()%>/templates/admin/assets/css/bootstrap.min.css" rel="stylesheet" />
    <!--  Material Dashboard CSS    -->
    <link href="<%=request.getContextPath()%>/templates/admin/assets/css/material-dashboard.css?v=1.2.0" rel="stylesheet" />
    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="<%=request.getContextPath()%>/templates/admin/assets/css/demo.css" rel="stylesheet" />
    <!--     Fonts and icons     -->
   <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300|Material+Icons' rel='stylesheet' type='text/css'>
    <script src="<%=request.getContextPath()%>/templates/admin/assets/js/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/templates/admin/assets/js/jquery.validate.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		$().ready(function() {
	
			// validate signup form on keyup and submit
			$(".form").validate({
				rules: {
					username: {
						required: true,
						minlength: 8
					},
					fullname: {
						required: true,
						minlength: 8
					},
					password: {
						required: true,
						minlength: 6
					},
					repassword: {
						required: true,
						minlength: 6,
						equalTo: "#password"
					},
					email: {
						required: true,
						email: true
					},
					remail: {
						required: true,
						email: true,
						equalTo: "#email"
					}
				},
				messages: {
					username: {
						required: "Please enter a username",
						minlength: "Your username must consist of at least 8 characters"
					},
					fullname: {
						required: "Please enter a fullname",
						minlength: "Your fullname must consist of at least 8 characters"
					},
					password: {
						required: "Please provide a password",
						minlength: "Your password must be at least 6 characters long"
					},
					confirm_password: {
						required: "Please provide a password",
						minlength: "Your password must be at least 6 characters long",
						equalTo: "Please enter the same password as above"
					},
					email: "Please enter a valid email address",
					remail:{
						email: "Please enter a valid email address",
						equalTo: "Please enter the same email as above"
					}
				}
			});
		});
	</script>
</head>

<body>
    <div class="wrapper">
        <div class="sidebar" data-color="purple" data-image="<%=request.getContextPath()%>/templates/admin/assets/img/sidebar-1.jpg">
            <!--
        Tip 1: You can change the color of the sidebar using: data-color="purple | blue | green | orange | red"

        Tip 2: you can also add an image using data-image tag
    -->
            <div class="logo">
                <a href="http://vinaenter.edu.vn" class="simple-text">
                    DA POET
                </a>
            </div>
            <div class="sidebar-wrapper">
                <ul class="nav">
                    <li>
                        <a href="<%=request.getContextPath()%>/admin/news">
                            <i class="material-icons">dashboard</i>
                            <p>News</p>
                        </a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/admin/cat">
                            <i class="material-icons">flip_back</i>
                            <p>Category</p>
                        </a>
                    </li>
                    <li>
                        <a href="<%=request.getContextPath()%>/admin/user">
                            <i class="material-icons">account_box</i>
                            <p>User</p>
                        </a>
                    </li>
                    <li>
                        <a href="./typography.html">
                            <i class="material-icons">comment</i>
                            <p>Comments</p>
                        </a>
                    </li>
                    
                    <%
                    	if(session.getAttribute("objUser") != null){
                    		User objUser = (User)session.getAttribute("objUser");
                    		if(objUser.getRole() > 0){
                    %>
                    	<li>
	                        <a href="<%=request.getContextPath()%>/admin/ads">
	                            <i class="material-icons">redeem</i>
	                            <p>Advertisement</p>
	                        </a>
	                    </li>
                    <%}}%>
                </ul>
            </div>
        </div>

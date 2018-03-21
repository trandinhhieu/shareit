<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE HTML>

<html lang="en">
<head>
	<link rel="apple-touch-icon" sizes="76x76" href="<%=request.getContextPath()%>/templates/admin/assets/img/apple-icon.png" />
    <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/templates/admin/assets/img/favicon.png" />
	<title>share IT</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<meta charset="UTF-8">


	<!-- Font -->

	<link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500" rel="stylesheet">
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	  
	  
	

	<!-- Stylesheets -->

	<link href="<%=request.getContextPath()%>/templates/public/common-css/bootstrap.css" rel="stylesheet">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	
	<link href="<%=request.getContextPath()%>/templates/public/blog-sidebar/css/hieu.css" rel="stylesheet" />

	<link href="<%=request.getContextPath()%>/templates/public/common-css/ionicons.css" rel="stylesheet">
	<link href="<%=request.getContextPath()%>/templates/admin/assets/css/bootstrap.min.css" rel="stylesheet" />

	<link href="<%=request.getContextPath()%>/templates/public/blog-sidebar/css/styles.css" rel="stylesheet">

	<link href="<%=request.getContextPath()%>/templates/public/blog-sidebar/css/responsive.css" rel="stylesheet">
	
	
	
</head>
<body >
    
<header>
		<div class="container-fluid position-relative no-side-padding">

			<a href="<%=request.getContextPath()%>/tin-tuc" class="logo"><img src="<%=request.getContextPath()%>/templates/public/images/gI_121884_download-next-it-logo.png" alt="Logo Image"></a>

			<div class="menu-nav-icon" data-nav-menu="#main-menu"><i class="ion-navicon"></i></div>
			
			<ul class="main-menu visible-on-click" id="main-menu">
				<li><a href="<%=request.getContextPath()%>/tin-tuc">Home</a></li>
			</ul><!-- main-menu -->
		
			<div class="src-area">
				<form action="<%=request.getContextPath()%>/public/news/search" method="post">
					<input name="txtsearch" class="src-input" type="text" placeholder="Type of search">
					<button class="src-btn" type="submit"><i class="ion-ios-search-strong"></i></button>
				</form>
			</div>

		</div><!-- conatiner -->
	</header>
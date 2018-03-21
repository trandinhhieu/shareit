<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="<%=request.getContextPath()%>/templates/admin/assets/img/apple-icon.png">
	<link rel="icon" type="image/png" href="<%=request.getContextPath()%>/templates/admin/assets/img/favicon.png">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title>Sign In</title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />

	<!--     Fonts and icons     -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/admin/assets/css/css.css" />
	<link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Roboto:400,700,300|Material+Icons' rel='stylesheet' type='text/css'>

	<!-- CSS Files -->
    <link href="<%=request.getContextPath()%>/templates/admin/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link href="<%=request.getContextPath()%>/templates/admin/assets/css/material-kit.css" rel="stylesheet"/>

</head>

<body class="signup-page">
    <div class="wrapper">
		<div class="header header-filter" style="background-image: url('<%=request.getContextPath()%>/templates/admin/assets/img/city.jpg'); background-size: cover; background-position: top center;">
			<div class="container">
				<div class="row">
					<div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
						<div class="card card-signup">
							<form class="form" method="post" action="<%=request.getContextPath()%>/login">
								<div class="header header-primary text-center">
									<h4>Sign In</h4>
								</div>
								<div class="content">
									<%
										if(request.getParameter("msg") != null){
											int msg = Integer.parseInt(request.getParameter("msg"));
											switch(msg){
												case 0: out.print("<div class='alert alert-danger'><div class='container-fluid'><div class='alert-icon'><i class='material-icons'>error_outline</i></div><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'><i class='material-icons'>clear</i></span></button><b>Error Alert:</b> Damn man! Đăng nhập thất bại</div></div>");
													break;
											}
										}
									%>
									<div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">face</i>
										</span>
										<input name="taikhoan" type="text" class="form-control" placeholder="Username...">
									</div>

									<div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">lock_outline</i>
										</span>
										<input name="matkhau" type="password" placeholder="Password..." class="form-control" />
									</div>
								</div>
								<div class="footer text-center">
									<button type="submit" class="btn btn-simple btn-primary btn-lg">Get Started</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

    </div>


</body>
	<!--   Core JS Files   -->
	<script src="<%=request.getContextPath()%>/templates/admin/assets/js/jquery.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/templates/admin/assets/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/templates/admin/assets/js/material.min.js"></script>

	<!--  Plugin for the Sliders, full documentation here: http://refreshless.com/nouislider/ -->
	<script src="<%=request.getContextPath()%>/templates/admin/assets/js/nouislider.min.js" type="text/javascript"></script>

	<!--  Plugin for the Datepicker, full documentation here: http://www.eyecon.ro/bootstrap-datepicker/ -->
	<script src="<%=request.getContextPath()%>/templates/admin/assets/js/bootstrap-datepicker.js" type="text/javascript"></script>

	<!-- Control Center for Material Kit: activating the ripples, parallax effects, scripts from the example pages etc -->
	<script src="<%=request.getContextPath()%>/templates/admin/assets/js/material-kit.js" type="text/javascript"></script>

</html>

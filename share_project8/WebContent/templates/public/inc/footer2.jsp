<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<footer>
		<div class="container">
			<div class="row">

				<div class="col-md-12">
					<div class="footer-section">

						<a class="logo" href="#"><img src="<%=request.getContextPath()%>/templates/public/images/logo.png" alt="Logo Image"></a>
						<p class="copyright">Bona @ 2017. All rights reserved.</p>
						<p class="copyright">Designed by <a href="https://colorlib.com" target="_blank">Colorlib</a></p>
					</div><!-- footer-section -->
				</div><!-- col-md-12 -->

			</div><!-- row -->
		</div><!-- container -->
	</footer>


	<!-- SCIPTS -->

	<script src="<%=request.getContextPath()%>/templates/public/common-js/jquery-3.1.1.min.js"></script>
	
	<script src="<%=request.getContextPath()%>/templates/public/common-js/toggle-nav.js"></script>

	<script src="<%=request.getContextPath()%>/templates/public/common-js/tether.min.js"></script>

	<script src="<%=request.getContextPath()%>/templates/public/common-js/bootstrap.js"></script>

	<script src="<%=request.getContextPath()%>/templates/public/common-js/scripts.js"></script>
	<script src="<%=request.getContextPath()%>/templates/public/common-js/nouislider.min.js" type="text/javascript"></script>

</body>
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = 'https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v2.11';
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
</html>
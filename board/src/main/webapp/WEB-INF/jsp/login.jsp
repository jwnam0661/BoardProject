<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
	<title>Login</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" href="images/icons/favicon.ico"/>
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/hamburgers.min.css">
	<link rel="stylesheet" href="assets/css/animsition.min.css">
	<link rel="stylesheet" href="assets/css/select2.min.css">
	<link rel="stylesheet" href="assets/css/mainlogin.css" />
	<link rel="stylesheet" href="assets/css/util.css">

	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<!-- <script src="js/login.js"></script> -->
</head>
<script type="text/javascript">
$(document).ready(function(){
	$('.login100-form-btn').click(function() {
		console.log('test');
		$('#MAINFORM').submit();
	});
	/* $('#MAINFORM').submit(function(){

	}); */
});
</script>
<body>

	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<c:url var="actionUrl" value="/login.do" />
				<form:form
						id="MAINFORM"
						action="${actionUrl}"
						method="post"
						modelAttribute="userBean"
						class="login100-form validate-form p-l-55 p-r-55 p-t-178">
					<span class="login100-form-title">
						Sign In
					</span>

					<div class="wrap-input100 validate-input m-b-16" data-validate="Please enter username">
						<input class="input100" type="text" name="userId" placeholder="UserId">
						<%-- <form:input path="userId"/> --%>
						<form:errors path="userId" />
						<span class="focus-input100"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate = "Please enter password">
						<input class="input100" type="password" name="password" placeholder="Password">
						<span class="focus-input100"></span>
					</div>

					<div class="text-right p-t-13 p-b-23">
						<span class="txt1">
							Forgot
						</span>

						<a href="#" class="txt2">
							Username / Password?
						</a>
					</div>

					<div class="container-login100-form-btn">
						<button class="login100-form-btn">
							Sign in
						</button>
					</div>

					<div class="flex-col-c p-t-170 p-b-40">
						<span class="txt1 p-b-9">
							Don’t have an account?
						</span>

						<a href="#" class="txt3">
							Sign up now
						</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>
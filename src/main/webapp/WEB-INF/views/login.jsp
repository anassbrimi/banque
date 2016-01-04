<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<spring:url value="/resources/css/bootstrap.css" var="bootstrapCss"></spring:url>
<spring:url value="/resources/css/style.css" var="styleCss"></spring:url>
<spring:url value="/resources/css/animate.css" var="animatedCss"></spring:url>
<spring:url value="/resources/js/jquery-1.11.3.min.js" var="jquery"></spring:url>
<spring:url value="/resources/css/font-awesome.min.css" var="font"></spring:url>
<spring:url value="/resources/js/bootstrap.min.js" var="bootJs"></spring:url>
<spring:url value="/resources/js/js.js" var="js"></spring:url>
<meta name="viewport" content="width=device-width, initial-scale=1">

<%-- <link rel="stylesheet" href="${bootstrapCss}"> --%>
<link rel="stylesheet" href="${styleCss}">
<link rel="stylesheet" href="${animatedCss}">
<link rel="stylesheet" href="${font}">
<script src="${jquery}"></script>
<%-- <script src="${bootJs}"></script> --%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<h1>Please Enter Your Email And Your Password</h1>



	<form class="form-signin" action="j_spring_security_check"
		method="post">
		<label> <input id="fname" type="email" placeholder="E-mail"
			autofocus name="j_username" /> <span>Email</span>

		</label> <label> <input type="password" placeholder="Password"
			name="j_password"> <span>Password</span>

		</label>

		<button type="submit">
			<i class="fa fa-sign-in "></i> Login
		</button>



	</form>

	<script>
		$(function() {
			$('input').on('change', function() {
				var input = $(this);
				if (input.val().length) {
					input.addClass('populated');
				} else {
					input.removeClass('populated');
				}
			});

			setTimeout(function() {
				$('#fname').trigger('focus');
			}, 500);
		});
	</script>
</body>
</html>
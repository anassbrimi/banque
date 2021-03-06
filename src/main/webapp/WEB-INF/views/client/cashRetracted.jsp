<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<spring:url value="/resources/css/bootstrap.css" var="bootstrapCss"></spring:url>
<spring:url value="/resources/css/espaceClient.css"
	var="espaceClientCss"></spring:url>
<spring:url value="/resources/css/animate.css" var="animatedCss"></spring:url>
<spring:url value="/resources/js/jquery-1.11.3.min.js" var="jquery"></spring:url>
<spring:url value="/resources/css/font-awesome.min.css" var="font"></spring:url>
<spring:url value="/resources/js/bootstrap.min.js" var="bootJs"></spring:url>
<spring:url value="/resources/js/espaceClient.js" var="espaceClientJs"></spring:url>
<spring:url value="/resources/css/loader.css" var="loaderCss"></spring:url>




<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="${jquery}"></script>
<link rel="stylesheet" href="${bootstrapCss}">
<link rel="stylesheet" href="${espaceClientCss}">
<link rel="stylesheet" href="${animatedCss}">
<link rel="stylesheet" href="${font}">
<link rel="stylesheet" href="${loaderCss}">
<script src="${bootJs}"></script>
<script src="${espaceClientJs}"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>Espace Client - Cash Retracted</title>
</head>
<body>
	<div id="wrapper">
		<div class="overlay"></div>
		<!-- Sidebar -->
		<nav class="navbar navbar-inverse navbar-fixed-top"
			id="sidebar-wrapper" role="navigation">
			<ul class="nav sidebar-nav">
				<li class="sidebar-brand"><a href="#"> Project </a></li>
				<li><a href="home"><i class="fa fa-home"></i> Home</a></li>
				<li><a href="transaction"><i class="fa fa-money"></i>
						Transactions</a></li>
				<li><a href="cashRetracted"><i class="fa fa-credit-card"></i>
						Cash-Retracted</a></li>
				<li><a href="settings"><i class="fa fa-cogs"></i> Settings</a></li>
				<li><a href="<c:url value="/j_spring_security_logout" />"><i
						class="fa fa-sign-out"></i> Sign Out</a></li>
			</ul>
		</nav>
		<!-- /#sidebar-wrapper -->
		<!-- Page Content -->
		<div id="page-content-wrapper">
			<button type="button" class="hamburger is-closed"
				data-toggle="offcanvas">
				<span class="hamb-top"></span> <span class="hamb-middle"></span> <span
					class="hamb-bottom"></span>
			</button>
			<div class="container">

				<div class="row">


					<div class="col-md-3"></div>
					<div class="col-md-6">
						<div>

							<div class="panel panel-default">
								<div class="panel-heading">Yours Cash-Retracted</div>
								<div class="panel-body">


									<div>
										<table class="table table-hover">

											<thead>
												<tr>

													<th>TYPE</th>
													<th>DATE</th>

													<th>STATUS</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${failedOps}" var="op">
													<tr>


														<td>${op.dateOperation}</td>


														<td>${op.montant}</td>
														<td><section class="section section-2">
																<span class="loader loader-double"></span> Pending...
															</section></td>

													</tr>

												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>




				</div>
			</div>
		</div>
		<!-- /#page-content-wrapper -->
	</div>

</body>
</html>
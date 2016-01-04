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




<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="${jquery}"></script>
<link rel="stylesheet" href="${bootstrapCss}">
<link rel="stylesheet" href="${espaceClientCss}">
<link rel="stylesheet" href="${animatedCss}">
<link rel="stylesheet" href="${font}">
<script src="${bootJs}"></script>
<script src="${espaceClientJs}"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>Transactions</title>
</head>
<body>
	<div id="wrapper">
		<div class="overlay"></div>
		<!-- Sidebar -->
		<nav class="navbar navbar-inverse navbar-fixed-top"
			id="sidebar-wrapper" role="navigation">
			<ul class="nav sidebar-nav">
				<li class="sidebar-brand"><a href="#"> Project </a></li>
				<li ><a href="home"><i class="fa fa-home"></i>
						Home</a></li>
				<li><a href="transaction"><i class="fa fa-money"></i> Transactions</a></li>
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


					<div class="col-md-5">
						<div class="row">

							<div class="panel panel-default">
								<div class="panel-heading">Yours Credits</div>
								<div class="panel-body">


									<div>
										<table class="table table-hover">

											<thead>
												<tr>

													<th>TYPE</th>
													<th>DATE</th>
													<th>EMPLOYE</th>
													<th>BALANCE</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${retraits}" var="op">
													<tr>

														<td>D</td>
														<td>${op.dateOperation}</td>

														<td>${op.employe.nomPersonne}</td>
														<td>${op.montant} <b> MAD</b></td>
													</tr>

												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-2"></div>
					<div class="col-md-5">
						<div class="row">
							<div class="panel panel-default col-offset-2">
								<div class="panel-heading">Yours Debits</div>
								<div class="panel-body">
									<div>
										<table class="table table-hover">

											<thead>
												<tr>

													<th>TYPE</th>
													<th>DATE</th>
													<th>EMPLOYE</th>
													<th>BALANCE</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${versements}" var="op">
													<tr>

														<td>C</td>
														<td>${op.dateOperation}</td>
														<td>${op.employe.nomPersonne}</td>
														<td>${op.montant}<b> MAD</b></td>
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
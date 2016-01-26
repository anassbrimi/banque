<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!DOCTYPE html>
<html>
<head>
<spring:url value="/resources/css/bootstrap.css" var="bootstrapCss"></spring:url>
<spring:url value="/resources/css/espaceClient.css"
	var="espaceClientCss"></spring:url>

<spring:url value="/resources/css/loader.css" var="loaderCss"></spring:url>
<spring:url value="/resources/css/animate.css" var="animatedCss"></spring:url>
<spring:url value="/resources/js/jquery-1.11.3.min.js" var="jquery"></spring:url>
<spring:url value="/resources/css/font-awesome.min.css" var="font"></spring:url>
<spring:url value="/resources/js/bootstrap.min.js" var="bootJs"></spring:url>
<spring:url value="/resources/js/espaceClient.js" var="espaceClientJs"></spring:url>
<spring:url value="/resources/js/js.js" var="Js"></spring:url>
<spring:url value="/resources/js/angular.min.js" var="AngJs"></spring:url>





<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="${jquery}"></script>
<%-- <link rel="stylesheet" href="${loaderCss}"> --%>
<link rel="stylesheet" href="${bootstrapCss}">
<link rel="stylesheet" href="${espaceClientCss}">
<link rel="stylesheet" href="${animatedCss}">
<link rel="stylesheet" href="${font}">
<script src="${Js}"></script>
<script src="${bootJs}"></script>
<script src="${espaceClientJs}"></script>
<script src="${AngJs}"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<title>Espace Employe - Clients</title>
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
				<li><a href="addClient"><i class="fa fa-plus"></i> Add Client</a></li>
				<li><a href="clients"><i class="fa fa-users"></i> Clients</a></li>
				<li><a href="accounts"><i class="fa fa-credit-card"></i>
						Accounts</a></li>
				<li><a href="transactions"><i class="fa fa-money"></i>
						Transactions</a></li>
				<!-- 				<li><a href="settings"><i class="fa fa-cogs"></i> Settings</a></li> -->
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
			<div class="container well well-lg">

				<f:form method="post" action="searchClient"
					>
					<table>
						<tr>
							<td class="col-md-3">Search Client :</td>
							<td class="col-md-3"><input name="keywords"/></td>
			
						</tr>
						<tr>
							<td class="col-md-3" colspan="3"><input type="submit"
								value="Consult" class="btn btn-primary " /></td>
						</tr>
					</table>
				</f:form>
			</div>
			
			<div class="row">
					<div class="col-lg-8 col-lg-offset-2">
						
						<table class="table table-hover">
							<caption>Clients</caption>
							<thead>
								<tr>
									<th>CODE</th>
									<th>FIRST NAME</th>
									<th>LAST NAME</th>
									<th>EMAIL</th>
									<th>MOBILE</th>
									<th>ADRESSE</th>
									<th>      </th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${clients}" var="c">
									<tr>

										<td>${c.idPersonne}</td>
										<td>${c.prenomPersonne}</td>
										<td>${c.nomPersonne}</td>
										<td>${c.emailPersonne}</td>
										<td>${c.telePersonne}</td>
										<td>${c.adressePersonne}</td>
										<td><a href="consultClient?idCli=${c.idPersonne}">Consult</a></td>
									</tr>

								</c:forEach>
							</tbody>
						</table>
		</div>
		<!-- /#page-content-wrapper -->
	</div></div></div>
</body>
</html>
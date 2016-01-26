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


<title>Espace Employe - Add Client</title>
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
			<div class="container">

				<div class="row">


					<div class="col-md-3"></div>
					<div class="col-md-6">
						<div>
							<f:form modelAttribute="banqueForm" method="post"
								action="saveClient" class="form-horizontal" role="form">
								
								<div class="form-group">
								<h3>Client</h3>
								
									<label class="control-label col-sm-2" for="Nom">Last Name:</label>
									<div class="col-sm-8">
										<f:input path="client.nomPersonne" class="form-control" />
									</div>
									<div class="col-sm-2">
										<f:errors path="client.nomPersonne" cssClass="errors"></f:errors>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2" for="Prénom">First Name:</label>

									<div class="col-sm-8">
										<f:input path="client.prenomPersonne" class="form-control" />
									</div>
									<div class="col-sm-2">
										<f:errors path="client.prenomPersonne" cssClass="errors"></f:errors>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2" for="Téléphone">Mobile:</label>
									<div class="col-sm-8">
										<f:input path="client.telePersonne" class="form-control" />
									</div>
									<div class="col-sm-2">
										<f:errors path="client.telePersonne" cssClass="errors"></f:errors>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2" for="mail">E-mail:</label>
									<div class="col-sm-8">
										<f:input path="client.emailPersonne" class="form-control" />
									</div>
									<div class="col-sm-2">
										<f:errors path="client.emailPersonne" cssClass="errors"></f:errors>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-sm-2" for="Adresse">Adresse:</label>
									<div class="col-sm-8">
										<f:textarea path="client.adressePersonne" class="form-control" />
									</div>
									<div class="col-sm-2">
										<f:errors path="client.adressePersonne" cssClass="errors"></f:errors>
									</div>
								</div>
								
								<h3>Account</h3>
								
								<div class="form-group">
									<label class="control-label col-sm-2" for="solde">First Disposit:</label>
									<div class="col-sm-8">
										<f:textarea path="compte.solde" class="form-control" />
									</div>
									<div class="col-sm-2">
										<f:errors path="compte.solde" cssClass="errors"></f:errors>
									</div>
								</div>
								
								
								<div class="form-group">
									<div class="col-sm-12">
										<input type="submit" value="saveClient" class="btn btn-default" />
									</div>
								</div>
							</f:form>



						</div>




					</div>
				</div>
			</div>
			<!-- /#page-content-wrapper -->
		</div>
	</div>
</body>
</html>
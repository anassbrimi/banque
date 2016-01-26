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
<script type="text/javascript">
	$(function() {
		mafunction(elm);

	});

	function mafunction(elm) {
		if (elm.value == "VIR") {
			$("#ver").show();
			$("#vir").show();
			$("#ret").show();
		} else {
			$("#ver").show();
			$("#vir").hide();
			$("#ret").show();
		}
		;
	}
</script>

<title>Espace Employe - Details</title>
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

			<div class="container well">
				<div>
					<table>
						<tr>
							<td>FIRST NAME :</td>
							<td>${banqueForm.client.prenomPersonne }</td>
						</tr>
						<tr>
							<td>LAST NAME :</td>
							<td>${banqueForm.client.nomPersonne}</td>
						</tr>
						<tr>
							<td>EMAIL :</td>
							<td>${banqueForm.client.emailPersonne }</td>
						</tr>
						<tr>
							<td>MOBILE :</td>
							<td>${banqueForm.client.telePersonne }</td>
						</tr>
						<tr>
							<td>ADRESSE :</td>
							<td>${banqueForm.client.adressePersonne }</td>
						</tr>
						<tr>
							<td>BALANCE :</td>
							<td>${banqueForm.compte.solde} <b> MAD</b></td>
						</tr>
						<tr>
							<td>CREATION :</td>
							<td>${banqueForm.compte.dateCreation}</td>
						</tr>
					</table>
				</div>




			</div>





			<div class="container">
				<f:form modelAttribute="banqueForm" action="saveOperation"
					method="post">
					<f:hidden path="client.idPersonne" />
					<f:hidden path="compte.idCompte" />
					<table class="table">
						<tr>
							<td>CREDIT :<f:radiobutton path="typeOperation" value="VER"
									onclick="mafunction(this)" /></td>
							<td>DEBIT :<f:radiobutton path="typeOperation" value="RET"
									onclick="mafunction(this)" /></td>
							<td>TRANSFER :<f:radiobutton path="typeOperation"
									value="VIR" id="VIR" onclick="mafunction(this)" /></td>
						</tr>



						<tr id="ver" style="display: none" class="slideInRight animated">
							<td>BALANCE :</td>
							<td><f:input path="montant" /></td>
							<td><f:errors path="montant"></f:errors></td>
						</tr>

						<tr id="vir" style="display: none" class="slideInRight animated">
							<td>TO ACCOUNT :</td>
							<td><f:input path="code2" /></td>
							<td><f:errors path="code2"></f:errors></td>
						</tr>


						<tr id="ret" style="display: none" class="slideInRight animated">
							<td colspan="3"><input type="submit" value="SAVE"
								class="btn btn-primary" name="action" />
						</tr>




					</table>
				</f:form>

				<div class="container">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>DATE</th>
								<th>EMPLOYE</th>
								<th>TYPE</th>
								<th>BLANCE</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${banqueForm.operations}" var="op">
								<tr>
									<td>${op.dateOperation}</td>
									<td>${op.employe.nomPersonne}</td>
									<td><b>${op}</b></td>
									<td>${op.montant}<b> MAD</b></td>
								</tr>

							</c:forEach>

						</tbody>
					</table>
				</div>

			</div>
		</div>

	</div>
</body>
</html>
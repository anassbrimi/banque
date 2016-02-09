<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
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
<spring:url value="/resources/js/angular.min.js" var="AngJs"></spring:url>




<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="${jquery}"></script>
<link rel="stylesheet" href="${bootstrapCss}">
<link rel="stylesheet" href="${espaceClientCss}">
<link rel="stylesheet" href="${animatedCss}">
<link rel="stylesheet" href="${font}">
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

<title>Espace Client - Transactions</title>
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
			</div>

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
												<c:forEach items="${versements}" var="op">
													<tr>

														<td><b>DEBIT</b></td>
														<td>${op.dateOperation}</td>

														<td>${op.employe.nomPersonne}</td>
														<td>${op.montant}<b> MAD</b> </td>
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
												<c:forEach items="${retraits}" var="op">
													<tr>

														<td><b>CREDIT</b></td>
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
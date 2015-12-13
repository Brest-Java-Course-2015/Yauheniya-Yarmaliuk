<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css" />">
	<script type="text/javascript" src="<c:url value="/resources/js/jquery-2.1.1.js" />"></script>
	<title>Home</title>
</head>
<body>
		<div class="container" style="width: 800px;">
			<header class="main-header" role="banner">
				<ul class="nav nav-tabs">
					<li role="presentation"><a href="${contextPath}/">Home</a></li>
					<li role="presentation"><a href="${contextPath}/voucherList">Voucher</a></li>
					<li role="presentation"><a href="${contextPath}/employeeList">Employee</a></li>
					<li role="presentation"><a href="${contextPath}/costList">Cost</a></li>
				</ul>
			</header>
			<div id="spring-message"><span id="Addmessage"><spring:message code="voucher.create"/></span>
				<a href="/inputVoucher" id="AddVoucher"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true">
				</span></a></div>
			<div class="table-responsive">
				<table class="table table-condensed table-striped table-bordered">
					<thead>
						<tr>
							<th>Id</th>
							<th>Country</th>
							<th>Price $</th>
							<th>Discaunt %</th>
							<th>Reserved</th>
							<th>Edit</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="voucherdto" items="${voucherdto}">
							<tr>
								<td>${voucherdto.vouchId}</td>
								<td>${voucherdto.country}</td>
								<td>${voucherdto.price}</td>
								<td>${voucherdto.discaunt}</td>
								<td>${voucherdto.countPeopl}</td>
								<td><form action="/voucherById">
									<input  type="hidden" name="vouchId" value="${voucherdto.vouchId}">
									<button class="btn btn-link"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
										</button></form></td>
								<td><button value="btn" class="btn btn-link"
								onclick="delVoucher(${voucherdto.vouchId});"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
									</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>

		<script>
			function delVoucher(vouchId) {
				$.ajax({
					type: 'DELETE',
					url: '<c:url value="/delete/voucher/"/>' + vouchId,

					success: function (data) {
						location.reload();
					},
					error: function (jqXHR, textStatus, errorThrown) {
						alert('Voucher is used employee, delete : ' + textStatus);
						location.reload();
					}
				});
			}
		</script>
</body>
</html>
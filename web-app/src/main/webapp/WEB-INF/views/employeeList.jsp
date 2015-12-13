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
	<div id="spring-message"><span id="Addmessage"><spring:message code="employee.create"/></span>
		<a href="/inputEmployee" id="AddEmployee"><span class="glyphicon glyphicon-plus-sign" aria-hidden="true">
	    </span></a></div>
	<div class="container" style="width: 800px;">
		<span><spring:message code="employee.search"/></span>
		<form action="/searchVacation" method="get" class="form-inline">
			<label class="control-label">from</label>
			<input  type="text" name="vacation" placeholder="yyyy-MM-dd">
			<label class="control-label">till</label>
			<input  type="text" name="vacation2" placeholder="yyyy-MM-dd">
			<input type="submit" value="Search" class="btn btn-success btn-sm">
		</form>

	</div>
	<div class="table-responsive">
		<table class="table table-condensed table-striped table-bordered">
			<thead>
			<tr>
				<td>emplId</td>
				<td>Name</td>
				<td>Surname</td>
				<td>idVoucher</td>
				<td>Vacation</td>
				<td>Edit</td>
				<td>Delete</td>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${dto.employees}" var="employees">
				<tr>
					<td>${employees.emplId}</td>
					<td>${employees.name}</td>
					<td>${employees.surname}</td>
					<td>${employees.idVoucher}</td>
					<td>${employees.vacation}</td>
					<td><form action="/employeeById" method="get">
						<input  type="hidden" name="emplId" value="${employees.emplId}">
						<button class="btn btn-link"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></button></form></td>
					<td><button value="btn" class="btn btn-link" onclick="delEmployee(${employees.emplId});">
						<span class="glyphicon glyphicon-trash" aria-hidden="true"></span></button></td>
				</tr>
			</c:forEach>
			</tbody>
			<tr>
				<td><h4><spring:message code="employee.count"/> ${dto.count}</h4></td>
			</tr>
		</table>
	</div>

</div>
</body>
<script>
	function delEmployee(emplId) {
		$.ajax({
			type: 'DELETE',
			url: '<c:url value="/delete/employee/"/>' + emplId,
		}).done(function() {
			location.reload();
		});
	}
</script>
</html>
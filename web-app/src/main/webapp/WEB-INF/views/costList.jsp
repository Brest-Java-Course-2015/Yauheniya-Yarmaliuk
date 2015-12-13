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
		<div class="table-responsive">
			<table class="table table-condensed table-striped table-bordered">
				<thead>
				<tr>
					<th>Name</th>
					<th>Surname</th>
					<th>Price</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="cost" items="${cost}">
					<tr>
						<td>${cost.name}</td>
						<td>${cost.surname}</td>
						<td>${cost.yourprice}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="<c:url value="/resources/css/style.css" />">
  <link rel="stylesheet"
        href="<c:url value="/resources/css/bootstrap.min.css" />">
  <script type="text/javascript"
          src="<c:url value="/resources/js/jquery-2.1.1.js" />"></script>
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
    <h1>Hello,</h1>
    <p>This web-application for employee, who can choose voucher from company.</p>
    <h6>See documentation in the doc</h6>
 </div>
</body>
</html>
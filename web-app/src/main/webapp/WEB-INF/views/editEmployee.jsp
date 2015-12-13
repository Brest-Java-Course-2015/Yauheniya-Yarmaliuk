<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
  <div class="container" style="width: 500px;">
    <header class="main-header" role="banner">
      <ul class="nav nav-tabs">
        <li role="presentation"><a href="${contextPath}/">Home</a></li>
        <li role="presentation"><a href="${contextPath}/voucherList">Voucher</a></li>
        <li role="presentation"><a href="${contextPath}/employeeList">Employee</a></li>
        <li role="presentation"><a href="${contextPath}/costList">Cost</a></li>
      </ul>
    </header>
    <div id="spring-message"><span id="Addmessage"><spring:message code="employee.edit"/></span></div>
      <form action="#" id="updateEmplForm" var="oneemployee" items="${oneemployee}" >
        <div class="form-group">
            <label for="emplId"></label>
            <input type="hidden" class="form-control" name="emplId"  value="${oneemployee.emplId}" disabled>
        </div>
          <div  class="form-group">
          <label for="name"></label>
          <input type="hidden" class="form-control" name="name"  value="${oneemployee.name}" disabled>
        </div>
        <div class="form-group">
          <label for="surname"></label>
          <input type="text" class="form-control" name="surname"  value="${oneemployee.surname}">
          </div>
          <div class="form-group">
            <label for="idVoucher"></label>
            <input type="text" class="form-control" name="idVoucher" value="${oneemployee.idVoucher}">
        </div>
        <div class="form-group">
          <label for="date"></label>
          <input type="text" class="form-control" name="date" value ="<fmt:formatDate value="${oneemployee.vacation}" pattern="yyyy-MM-dd" />"/>
        </div>
        <input type="submit" value="Save" class="btn btn-success btn-sm" class="btn btn-success btn-sm" />
      </form>
    <a href="${contextPath}/employeeList"><button  type="button" class="btn btn-large" >Cancel</button></a>
  </div>
</body>
<script>
  $("#updateEmplForm")
          .submit(
            function(event) {
                event.preventDefault();
                var emplId = $("input[name=emplId]", this)[0].value;
                var name = $("input[name=name]", this)[0].value;
                var surname = $("input[name=surname]", this)[0].value;
                var idVoucher = $("input[name=idVoucher]", this)[0].value;
                var date = $("input[name=date]", this)[0].value;

                $.ajax({

                      type : 'PUT',
                      url :  '<c:url value="/update/employee/"/>'

                      + emplId
                      + "/"
                      + name
                      + "/"
                      + surname
                      + "/"
                      + idVoucher
                      + "/"
                      + date,

                      success: function (data) {
                        document.location.href = "${contextPath}/employeeList";
                      }

                })

            });

</script>
</html>








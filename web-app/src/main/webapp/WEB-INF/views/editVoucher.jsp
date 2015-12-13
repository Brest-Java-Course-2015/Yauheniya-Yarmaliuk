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
  <div class="container" style="width: 500px;">
      <header class="main-header" role="banner">
          <ul class="nav nav-tabs">
              <li role="presentation"><a href="${contextPath}/">Home</a></li>
              <li role="presentation"><a href="${contextPath}/voucherList">Voucher</a></li>
              <li role="presentation"><a href="${contextPath}/employeeList">Employee</a></li>
              <li role="presentation"><a href="${contextPath}/costList">Cost</a></li>
          </ul>
      </header>
    <div id="spring-message"><span id="Addmessage"><spring:message code="voucher.edit"/></span></div>
      <form action="#" id="updateVouchForm" var="onevoucher" items="${onevoucher}" >
          <div class="form-group">
              <label for="vouchId"></label>
              <input type="hidden" class="form-control" name="vouchId"  value="${onevoucher.vouchId}" disabled>
          </div>
          <div class="form-group">
              <label for="country"></label>
              <input type="hidden" class="form-control" name="country"  value="${onevoucher.country}"disabled>
          </div>
          <div class="form-group">
              <label for="price"></label>
              <input type="text" class="form-control" name="price" value="${onevoucher.price}">
          </div>
          <div class="form-group">
              <label for="discaunt"></label>
              <input type="text" class="form-control" name="discaunt" value="${onevoucher.discaunt}">
          </div>
          <input type="submit" value="Save" class="btn btn-success btn-sm" class="btn btn-success btn-sm" />
      </form>
      <a href="${contextPath}/voucherList"><button  type="button" class="btn btn-large" >Cancel</button></a>
  </div>
</body>
<script>
    $("#updateVouchForm")
            .submit(

                function(event) {
                    event.preventDefault();
                    var vouchId = $("input[name=vouchId]", this)[0].value;
                    var country = $("input[name=country]", this)[0].value;
                    var price = $("input[name=price]", this)[0].value;
                    var discaunt = $("input[name=discaunt]", this)[0].value;

                    $.ajax({

                        type : 'PUT',
                        url :  '<c:url value="/update/voucher/"/>'

                        + vouchId
                        + "/"
                        + country
                        + "/"
                        + price
                        + "/"
                        + discaunt,

                        success: function (data) {
                            document.location.href = "${contextPath}/voucherList";
                        }

                    })

                });

</script>
</html>








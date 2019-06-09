<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>选择收货地址</title>
<script type="text/javascript">
	$(function(){
		$("input[name='addressRadio']").click(function(){
			$("#addressid").val(this.id);
			$("#nextBtn").removeAttr("disabled");
		});
		$("button").click(function(){
			window.history.go(-1);
			return false;
		});
	});
</script>
</head>
<body>
<div class="container">
<h2>以下是你的收货地址列表：</h2>
	<table class="table table-bordered">
		<thead>
		<tr>
			<th>收货人</th>
			<th>地 址</th>
			<th>电 话</th>
			<th colspan="2">选 择</th>
		</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="address">
				<tr>
					<td>${address.name}</td>
					<td>${address.location}</td>
					<td>${address.cellphone}</td>
					<td><input name="addressRadio" type="radio" id="${address.id}"/></td>
					<td></td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
		<tr>
			<td colspan="5" style="text-align: right;">
				<form action="${pageContext.request.contextPath}/client/OrderServlet?method=makeOrder" method="post">
					<input id="addressid" type="hidden" name="addressid"/>	
					<input id="nextBtn" type="submit" value="生成订单" disabled="disabled">
					<a href="${pageContext.request.contextPath}/client/order/addAddress.jsp" >增加</a>
				</form>
			</td>
		</tr>
		</tfoot>
	</table>
	<div class="divider"><hr /></div>
	<button class="btn btn-primary btn-block btn-lg">返回</button>
</div>
</body>
</html>
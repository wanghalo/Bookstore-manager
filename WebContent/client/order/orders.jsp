<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>查看订单列表</title>
</head>
<body>
<div class="container">
<h2>我的订单</h2>
<div class="divider"><hr /></div>
	<table class="table table-bordered">
		<thead>
		<tr class="active">
			<th>订单编号</th>
			<th>下单时间</th>
			<th>订单状态</th>
			<th>订单价格</th>
			<th>查看订单</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="order" items="${list}">
		<tr>
			<td>${order.number }</td>
			<td>${order.ordertime }</td>
			<td>${order.status?'已发货':'未发货' }</td>
			<td>${order.price }</td>
			<td>
				<a href="${pageContext.request.contextPath}/client/OrderServlet?method=showOrder&orderid=${order.id}&addressid=${order.addressid}">查看详细</a>
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="divider"><hr /></div>
	<button class="btn btn-primary btn-block btn-lg" onclick="location.href='javascript:history.go(-1);'">返回</button>
</div>
</body>
</html>
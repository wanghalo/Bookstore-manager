<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>后台-查看订单详细信息</title>
</head>
<body>
<div class="container">
<h2>订单的详细信息</h2>
<div class="divider"><hr /></div>
	<table class="table table-bordered">
		<caption>下订单的用户信息</caption>
		<tbody>
			<tr>
				<th class="active">用户名</th>
				<td>${user.username }</td>
			</tr>
			<tr>
				<th class="active">电子邮件</th>
				<td>${user.email }</td>
			</tr>
		</tbody>
	</table>
	<table class="table table-bordered">
		<caption>收货人信息</caption>
			<tr>
				<th class="active">收货人姓名</th>
				<td>${address.name }</td>
			</tr>
			<tr>
				<th class="active">收货人地址</th>
				<td>${address.location }</td>
			</tr>
			<tr>
				<th class="active">收货人电话</th>
				<td>${address.cellphone }</td>
			</tr>
		</table>
	<table class="table table-bordered">
		<caption>订单商品列表</caption>
		<thead>
			<tr class="active">
				<th colspan="2">商品</th>
				<th>数量</th>
				<th>小计</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${orderItems }">
				<tr>
					<td>
						<img width="90px" height="90px" src="${pageContext.request.contextPath }${item.book.imagepath }" />
					</td>
					<td><a href="${pageContext.request.contextPath}/manager/BookServlet?method=showBook&bookid=${item.book.id }">${item.book.name }</a></td>
					<td>${item.quantity }</td>
					<td>${item.price }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
<div class="divider"><hr /></div>
	<button class="btn btn-primary btn-block btn-lg" onclick="location.href='javascript:history.go(-1);'">返回上一级</button>
</div>
</body>
</html>

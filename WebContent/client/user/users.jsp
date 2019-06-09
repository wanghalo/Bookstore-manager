<%@ page import="com.twinkle.bookstore.bean.OnLineBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>用户列表</title>
<script type="text/javascript">
	$(function(){
		$("button").click(function(){
			window.history.go(-1);
			return false;
		});
	});
</script>
</head>
<body>
<div class="container">
<h2>目前共有${OnLineBean.totalCount}人在线，其中登陆用户${OnLineBean.userCount}</h2>
<div class="divider"><hr /></div>
	<table class="table table-bordered">
		<thead>
			<tr class="active">
				<th><h4>登陆用户列表</h4></th>
				<th><h4>匿名用户列表</h4></th>
			</tr>
		</thead>
		<tbody>
			<tr>
			<c:choose>
				<c:when test="${empty OnLineBean.userMap}">
					<td>无</td>
				</c:when>
				<c:otherwise>
					<c:forEach var="item" items="${OnLineBean.userMap}">
							<td>${item.value }</td>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${empty OnLineBean.visitorSet}">
					<td>无</td>
				</c:when>
				<c:otherwise>
					<c:forEach var="item" items="${OnLineBean.visitorSet}">
						<td>${item}</td>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			</tr>
		</tbody>
		<tfoot>
	</table>
	<div class="divider"><hr /></div>
	<button class="btn btn-primary btn-block btn-lg">返回</button>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
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
<title>图书浏览历史记录</title>
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
<c:choose>
	<c:when test="${empty list}">
		<h2>还没有浏览过任何图书！</h2>
	</c:when>
	<c:otherwise>
		<h2>所有浏览过的图书</h2>
		<div class="divider"><hr /></div>
		<table class="table table-bordered">
			<thead>
				<tr class="active">
					<th>书名</th>
					<th>详情</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="map" items="${list }">
				<tr>	
					<td>${map.name}</td>
					<td><a href="${pageContext.request.contextPath}/client/BookServlet?method=showBook&bookid=${map.id}">查看书的详情</a></td>
				</tr>
				</c:forEach>
			</tbody>
			<tfoot>
			<tr class="warning"><td style="text-align: right;" colspan="2"><a href="${pageContext.request.contextPath}/client/BookServlet?method=clearHistory">清空浏览记录</a></td></tr>
			</tfoot>
		</table>
		<div class="divider"><hr /></div>
		<button class="btn btn-primary btn-block btn-lg">返回</button>
	</c:otherwise>
	</c:choose>
</div>
</body>
</html>
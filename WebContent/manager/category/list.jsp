<%@page import="com.twinkle.bookstore.bean.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.BookStore.cn/jsp/myjstl/study"
	prefix="study"%>
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
<title>后台-查看所有图书分类</title>
<script type="text/javascript">
$(function(){
	$(".updateBtn").click(function(){
		var categoryid = this.name.trim();
		var name = $("#"+categoryid).val();
		if(name.trim()=="")
			return false;
		return true;
	});
	
	$(".deleteBtn").click(function(){
		if(window.confirm("你确认要删除"+this.name+"吗?")) {
			window.location = "${pageContext.request.contextPath }/manager/CategoryServlet?method=delete&categoryid=" + this.id;
		}
	});
});
</script>
</head>
<body>
<div class="container">
<h2>所有的图书分类</h2>
<div class="divider"><hr /></div>
<c:choose>
	<c:when test="${empty list}">
		<div style="color: red; font-size: 30px;">无分类</div>
	</c:when>
	<c:otherwise>
		<table class="table table-bordered">
		<c:forEach var="category" items="${list}">
			<form action="${pageContext.request.contextPath }/manager/CategoryServlet" method="post">
				<input type="hidden" name="id" value="${category.id}" />
				<!-- 表单隐藏域 -->
				<input type="hidden" name="method" value="update" />
				<tr>
					<td>
						${category.name}
						<input class="form-control" id="${category.id}" type="text" name="name" value="${category.name }" />
					</td>
					<td>
						<button class="updateBtn btn btn-info btn-block btn-lg" name="${category.id }">修改</button>
					</td>
			</form>
					<td>
						<study:checkcategory id="${category.id}">
							<button class="deleteBtn btn btn-danger btn-block btn-lg" name="${category.name}" id="${category.id}">删除</button>
						</study:checkcategory>
					</td>
				</tr>
		</c:forEach>
		</table>
	</c:otherwise>
</c:choose>
</div>
</body>
</html>
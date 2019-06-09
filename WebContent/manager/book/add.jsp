<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<title>后台-添加图书</title>
</head>
<body>
<div class="container">
<h2>添加图书</h2>
<div class="divider"><hr /></div>
	<form action="${pageContext.request.contextPath }/manager/BookServlet?method=add" method="post" enctype="multipart/form-data">
		<input type="hidden" name="salesamount" value="0"><!-- 默认销量为0 -->
		<table class="table table-bordered">
			<tr>
				<th><h5><strong>图书名称</strong></h5></th>
				<td><input type="text" class="form-control" name="name" placeholder="请输入图书名称" /></td>
			</tr>
			<tr>
				<th><h5><strong>作者</strong></h5></th>
				<td><input type="text" class="form-control" name="author" placeholder="请输入作者名称" /></td>
			</tr>
			<tr>
				<th><h5><strong>售价</strong></h5></th>
				<td>
					<input type="text" class="form-control" placeholder="请输入图书售价" 
						name="price" onkeypress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" />
				</td>
			</tr>
			<tr>
				<th><h5><strong>库存</strong></h5></th>
				<td>
					<input type="text" class="form-control" name="storenumber" placeholder="请输入图书库存" 
						onkeypress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" />
				</td>
			</tr>
			<tr>
   				<th><h5><strong>图书封面</strong></h5></th>
   				<td>
   					<input type="file" id="inputfile" name="imagepath" />
				</td>
   				</tr>
			<tr>
				<th><h5><strong>图书类别</strong></h5></th>
				<td>
				<select class="form-control" name="categorysid">
					<c:forEach var="category" items="${allCategorys }">
						<option value="${category.id }">${category.name }</option>
					</c:forEach>
				</select>
				</td>
			</tr>
		</table>
		<div class="divider"><hr /></div>
		<button type="submit" class="btn btn-success btn-block btn-lg" name="Btn">添加图书</button>
	</form>
</div>
</body>
</html>
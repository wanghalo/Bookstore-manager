<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>图书详细信息</title>
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
	<table class="table table-bordered">
		<thead><tr><th colspan="5">${book.name}&nbsp;的详细信息</th></tr></thead>
		<tbody>
			<tr>
				<td rowspan="3"><img style="width: 300px; height: 300" src="${pageContext.request.contextPath}${book.imagepath}"></td>
				<td>书名</td>
				<td>${book.name}</td>
				<td>价格</td>
				<td>${book.price}</td>
			</tr>
			<tr>
				<td>分类</td>
				<td>${category.name}</td>
				<td>销量</td>
				<td>${book.salesamount}</td>
			</tr>
			<tr>
				<td>作者</td>
				<td>${book.author}</td>
				<td>库存</td>
				<td>${book.storenumber}</td>
			</tr>
		</tbody>
	</table>
<div class="divider"><hr /></div>
<button class="btn btn-primary btn-block btn-lg" onclick="location.href='javascript:history.go(-1);'">返回上一级</button>
</div>
</body>
</html>
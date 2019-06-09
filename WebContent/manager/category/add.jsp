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
<title>后台-添加图书分类</title>
<script type="text/javascript">
$(function(){
	$("#nameText")[0].focus();
	$(".button").click(function(){
		var name = $("#nameText").val();
		if(name.trim()=="") {
			$("#nameText")[0].focus();
			alert("分类名称不能为空");
			return false;
		} else {
			return true;
		}
	});
});
</script>
</head>
<body>
<div class="container">
<h2>添加图书分类</h2>
<div class="divider"><hr /></div>
	<!-- <form role="form" action="${pageContext.request.contextPath }/manager/CategoryServlet?method=add" method="post"> -->
	<form action="${pageContext.request.contextPath }/manager/CategoryServlet?method=add" method="post">
		<div class="form-group">
			<label>分类名称</label><br />
			<input type="text" class="form-control input-lg" placeholder="请输入分类名称" id="nameText" name="name" value="${param.name}" />
			<p class="help-block" style="color: red;">${message}</p>
		</div>
		<div class="divider"><hr /></div>
		<button type="submit" class="btn btn-success btn-block btn-lg" id="addBtn">添加分类</button>
	</form>
</div>
</body>
</html>
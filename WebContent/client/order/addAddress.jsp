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
<title>添加地址</title>
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
		<h2>添加收货地址</h2>
		<form
			action="${pageContext.request.contextPath}/client/OrderServlet?method=addAddress"
			method="post">
			<table class="table table-bordered">
				<thead>
					<tr>
						<th colspan="2"><h4>收货人信息</h4></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>收货人姓名</td>
						<td><input type="text" name="name" /></td>
					</tr>
					<tr>
						<td>收货人地址</td>
						<td><input type="text" name="location" /></td>
					</tr>
					<tr>
						<td>收货人电话</td>
						<td><input type="text" name="cellphone" /></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2"><input type="submit" value="添加收货地址" /></td>
					</tr>
					<tr>
						<td colspan="2"><button class="btn btn-primary btn-block btn-lg">返回</button></td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>
</html>
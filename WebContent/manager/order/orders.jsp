<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<title>后台-查看所有订单</title>
<script type="text/javascript">
function send(orderid) {
	window.location = "${pageContext.request.contextPath }/manager/OrderServlet?method=send&orderid="+orderid;
}
</script>
</head>
<body>
<div class="container">
<h2>所有的订单</h2>
<div class="divider"><hr /></div>
	<table class="table table-bordered">
		<thead>
		<tr class="active">
			<th>订单编号</th>
			<th>下单时间</th>
			<th>订单状态</th>
			<th>订单价格</th>
			<th colspan="2">操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="order" items="${orders }">
		<tr>
			<td>${order.number }</td>
			<td>${order.ordertime }</td>
			<td>${order.status?'已发货':'未发货' }</td>
			<td>${order.price }</td>
			<td>
				<button class="btn btn-info btn-lg btn-block" 
					onclick="location.href='${pageContext.request.contextPath }/manager/OrderServlet?method=showOrder&orderid=${order.id}&addressid=${order.addressid}'">
						查看订单信息
				</button>
			</td>
			<td>
				<button class="btn btn-success btn-lg btn-block" onclick="send('${order.id }')" ${order.status?'disabled':'' }>发货</button>
			</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>
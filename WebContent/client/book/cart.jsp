<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>购物车</title>
<script type="text/javascript">
	$(function(){
		$("button").click(function(){
			window.history.go(-1);
			return false;
		});
		$("#clear").click(function(){
			return window.confirm("你确认清空购物车吗?");
		});
		
		$(".deleteClass").click(function(){
			var name = this.name;
			return window.confirm("你确认删除"+name+"吗?");
		});
		
		$(".increaseCount").click(function(){
			var bookid = this.name;
			var $bookCountEle = $("#"+bookid);
			var count = parseInt($bookCountEle.val())+1;
			$bookCountEle.val(count);
			updateBookCount($bookCountEle, bookid, count);
		});
		
		$(".decreaseCount").click(function(){
			var bookid = this.name;
			var $bookCountEle = $("#"+bookid);
			var count = parseInt($bookCountEle.val())-1;
			$bookCountEle.val(count);
			updateBookCount($bookCountEle,bookid, count);
		});
		
		function updateBookCount($bookCountEle, bookid, count) {
			//发送一个ajax请求去更新购物车
			$.post(
				"${pageContext.request.contextPath}/client/CartServlet",
				{method:"update",count:count, bookid:bookid},
				function(data){
					//alert(data);
					//"{"count":2,"itemTotalPrice":23,"totalPrice":2323,totalCount:23}"
					var count = data.count;
					var itemTotalPrice = data.itemTotalPrice;
					var totalPrice = data.totalPrice;
					var totalCount = data.totalCount;
					
					$bookCountEle.attr("value", count);
					$("td[id='"+bookid+"_price']").text(itemTotalPrice);
					$("#totalCountId").text(totalCount);
					$("#totalPriceId").text(totalPrice);
				},
				"json"
			);
		}
	});
</script>
</head>
<body>
<div class="container">
	<c:choose>
		<c:when test="${CART==null || CART.totalCount==0}">
			<h2>购物车中没有任何图书, 去<a href="${pageContext.request.contextPath}/client/BookServlet?method=getBooks"><strong>逛逛</strong></a>！</h2>
		</c:when>
		<c:otherwise>
			<h2>我的购物车</h2>
			<div class="divider"><hr /></div>
			<table class="table table-bordered">
			<thead>
				<tr class="active">
					<th>书名</th>
					<th>单价</th>
					<th>数量</th>
					<th>小计</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${CART.map}" var="map">
					<tr>
						<td>${map.value.book.name}</td>
						<td>${map.value.book.price}</td>
						<td>
							<button class="increaseCount btn btn-default btn-xs" name="${map.value.book.id}">+</button>
							<input value="${map.value.count}" style="width: 40px; height: 20px; text-align: center;" class="countClass" id="${map.value.book.id}"/>
							<button class="decreaseCount btn btn-default btn-xs" name="${map.value.book.id}">-</button>
						</td>
						<td id="${map.value.book.id}_price">${map.value.itemPrice}</td>
						<td><a href="${pageContext.request.contextPath}/client/CartServlet?method=delete&pageNum=${param.pageNum}&bookid=${map.value.book.id}" class="deleteClass" name="${map.value.book.name}">删除</a></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr class="warning">
					<td><a href="${pageContext.request.contextPath}/client/CartServlet?method=clear&pageNum=${param.pageNum}" id="clear">清空购物车</a></td>
					<td><a href="${pageContext.request.contextPath}/client/BookServlet?method=getBooks">继续购物</a></td>
					<th>共<span id="totalCountId">${CART.totalCount}</span>本书</th>
					<th>总价:<span id="totalPriceId">${CART.totalPrice}</span>元</th>
					<td><a href="${pageContext.request.contextPath}/client/OrderServlet?method=toAddressUI">去结算</a></td>
				</tr>
			</tfoot>
			</table>
			<div class="divider"><hr /></div>
			<button class="btn btn-primary btn-block btn-lg">返回</button>
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>
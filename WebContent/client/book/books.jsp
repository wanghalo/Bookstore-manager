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
<title>图书们</title>
<script type="text/javascript">
		$(function(){
			$("button").click(function(){
				window.history.go(-1);
				return false;
			});
			//alert("=====");
			$("a").each(function(){
				this.href = this.href+"&categorysid=${category.id}&minprice=${param.minprice}&maxprice=${param.maxprice}";
			});
			
			$("#toPage").blur(function(){
				var num = this.value;
				window.location = "${pageContext.request.contextPath}/client/BookServlet?method=getBooks&minprice=${param.minprice}&maxprice=${param.maxprice}&pageNum="+num;
			});
			$("#cart_3").hide();
			var hasBook = ${CART!=null && CART.totalCount>0};
			if(hasBook) {
				$("#cart_1").show();
				$("#cart_2").hide();
			} else {
				$("#cart_1").hide();
				$("#cart_2").show();
			}
			
			$(".addCartBtn").click(function(){
				var bookid = this.id;
				var url = "${pageContext.request.contextPath}/client/CartServlet?method=add&bookid="+bookid+"&pageNum=${page.pageNum}&time="+new Date().toLocaleString();
				
			$.post(url, function(json){
				//{"name":"Java编程者思想",totalCount:23}
				var name = json.name;
				var totalCount = json.totalCount;
					$("#cart_1").hide();
					$("#cart_2").hide();
					$("#cart_3").show();
					$("#addNameId").text(name);
					$("#totalCountId").text(totalCount);
				}, "json");
			});
		});
</script>
</head>
<body>
	<div align="center">
		<div style="font-size: 20px; border-bottom: 1px solid; background: #CCCCCC; width: 80%; height: 30px">
				<div id="cart_1">
					购物车中有${CART.totalCount} 本书， 
					<a href="${pageContext.request.contextPath}/client/book/cart.jsp?1=1">查看购物车</a> 
				</div>
				<div id="cart_2">
					购物车中还没有一个购物项
				</div>
				
				<div id="cart_3">
					您已将<span id="addNameId" style="color: red; font-size: 15px;"></span>加入购物车,
					购物车中有<span id="totalCountId" style="color: red; font-size: 15px;"></span>本书，
					<a href="${pageContext.request.contextPath}/client/book/cart.jsp?1=1">查看购物车</a> 
				</div>
			</div>
			<div style="border-top: 1px solid;border-bottom: 1px solid; width: 60%;">
				<form action="${pageContext.request.contextPath}/client/BookServlet">
					<input type="hidden" name="method" value="getBooks"/>
					<input type="hidden" name="categorysid" value="${category.id}"/>
					价格区间：<input type="text" name="minprice" style="width: 40px;text-align: center;" value="${param.minprice }"/>
					----
					<input type="text" name="maxprice" style="width: 40px;text-align: center;" value="${param.maxprice }"/>
					<input type="submit" value="查 询"/> 
				</form>
			</div>
			
			<c:if test="${page!=null}">
				<div style="border-top: 1px solid; border-bottom: 1px solid; 
					text-align: left;font-size: 20px; background: #CCCCCC; width: 60%; height: 30px"> 当前分类为: ${category.name}</div>
				
				<c:forEach items="${page.list}" var="book">
					<div style="width:40%; height:170px; border-bottom: 1px solid;">
			   			<div style="width:150px; height:150px; border:1px #000000 solid; float:left ; margin-top: 10px;" >
				   			<img width="150px" height="150px" src="${pageContext.request.contextPath }${book.imagepath }">
				   		</div>
				   		<div style="width:400px; height:150px; text-align: right;"  >
				   			<br>
				   			书名: <a href="${pageContext.request.contextPath}/client/BookServlet?method=showBook&bookid=${book.id }">${book.name }</a><br>
				   			作者：${book.author }<br>
				   			售价: ${book.price }<br>
				   			销量: ${book.storenumber }<br>
				   			库存: ${book.salesamount }<br><br>
				   			<input class="addCartBtn" type="button" value="加入购物车" id="${book.id}">
				   		</div>
			   		</div>
				</c:forEach>
				<br>
				<div style="text-align: center;">
					<c:if test="${page.pageNum>1}">
					 	<a href="${pageContext.request.contextPath}/client/BookServlet?method=getBooks&pageNum=${page.pageNum-1}">上一页</a>
						&nbsp;
					 </c:if>
					 
					 <c:if test="${page.firstPageNum>1}">
					 	<a href="${pageContext.request.contextPath}/client/BookServlet?method=getBooks&pageNum=1">1</a>
						&nbsp;
					 </c:if>
					 
					 <c:if test="${page.firstPageNum>2}">
					 	...
					 </c:if>
					 
					 
					<c:forEach begin="${page.firstPageNum}" end="${page.lastPageNum}" var="num">
						<c:choose>
							<c:when test="${num==page.pageNum}">${num}</c:when>
							<c:otherwise><a href="${pageContext.request.contextPath}/client/BookServlet?method=getBooks&pageNum=${num}">${num}</a></c:otherwise>
						</c:choose>&nbsp;
					</c:forEach>
					
					<c:if test="${page.lastPageNum<page.totalPageCount-1}">
					 	...
					 </c:if>
					
					<c:if test="${page.lastPageNum<page.totalPageCount}">
					 	<a href="${pageContext.request.contextPath}/client/BookServlet?method=getBooks&pageNum=${page.totalPageCount}">${page.totalPageCount}</a>
					 	&nbsp;
					 </c:if>
					 
					 <c:if test="${page.pageNum<page.totalPageCount}">
					 	<a href="${pageContext.request.contextPath}/client/BookServlet?method=getBooks&pageNum=${page.pageNum+1}">下一页</a>
					 	&nbsp;
					 </c:if>
					 
					到第<input type="text" id="toPage" style="width: 30px;text-align: center;" value="${page.pageNum}"/>页
					<br>
					当前第&nbsp;${page.pageNum}&nbsp;页 总共&nbsp;${page.totalPageCount}&nbsp;页 总共 &nbsp;${page.totalRecord}&nbsp;条记录
					
				</div>
			</c:if>
			
		<div>
			<c:if test="${page==null}">
				<h1>没有匹配条件的书！</h1>
			</c:if>
		</div>
	</div>
</body>
</html>
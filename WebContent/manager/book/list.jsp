<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<title>后台-显示图书列表</title>
<script type="text/javascript">
$(function(){
	$(".deleteBtn").click(function(){
		if(window.confirm("确认要删除"+this.name+"吗?")){
			window.location = "${pageContext.request.contextPath }/manager/BookServlet?method=delete&bookid=" + this.id;
		}
	});
});
</script>
</head>
<body>
<div class="container">
<h2>所有的图书</h2>
<div class="divider"><hr /></div>
	<table class="table table-bordered">
    	<thead>
    	<tr class="active">
    		<th>封面</th>
    		<th>图书名称</th>
    		<th>作者</th>
    		<th>售价</th>
    		<th>库存</th>
   		 	<th>销量</th>
    		<th colspan="2">操作</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var="book" items="${list}">
		<tr>
			<td><img style="width: 50px; height: 50px;" src="${pageContext.request.contextPath}${book.imagepath}"></td>
			<!-- <td>${category.name}</td> -->
			<%-- 判断够不够10个，不够10个，直接显示，够10个显示前面10个加。。。 --%>
	    	<td>
	    		<a href="${pageContext.request.contextPath }/client/BookServlet?method=showBook&bookid=${book.id }">
	    			${fn:substring(book.name, 0, 10)}${fn:length(book.name)>10? '...': '' }
	    		</a>
	    	</td>
	    	<td>${book.author }</td>
	    	<td>${book.price }</td>
	    	<td>${book.storenumber }本</td>
	    	<td>${book.salesamount }本</td>
			<td>
				<button class="updateBtn btn btn-info btn-block btn-lg" 
					onclick="location.href='${pageContext.request.contextPath}/manager/BookServlet?method=toUpdateUI&bookid=${book.id}'">修改</button>
			</td>
			<td>
				<button class="deleteBtn btn btn-danger btn-block btn-lg" name="${book.name }" id="${book.id}">删除</button>
			</td>
  		 </tr>
    	</c:forEach>
    	</tbody>
	</table>
</div>
</body>
</html>
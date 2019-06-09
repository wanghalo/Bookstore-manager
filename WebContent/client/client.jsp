<%@ page import="com.twinkle.bookstore.bean.Category"%>
<%@ page import="java.util.List"%>
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
<title>欢迎使用网上书城</title>
</head>
<body>
<div class="container-fluid">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="page-header">
				<h2>
					欢迎使用图书销售管理系统 <small>前台用户模块</small>
				</h2>
			</div>
			<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">折叠</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="${pageContext.request.contextPath}/client/ClientServlet?method=toClientUI">并夕夕网上书城</a>
				</div>
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<c:choose>
					<c:when test="${sessionScope.user!=null}">
					<ul class="nav navbar-nav navbar-right">
						<li class="active">
							<a href="#"><strong>${sessionScope.user.username}</strong></a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/client/UserServlet?method=logout">注销登录</a>
						</li>
					</ul>
					</c:when>
					<c:otherwise>
					<ul class="nav navbar-nav navbar-right">
						<li>
							 <a target="content_frame" href="${pageContext.request.contextPath}/client/user/login.jsp">登录</a>
						</li>
						<li>
							 <a target="content_frame" href="${pageContext.request.contextPath}/client/user/regist.jsp">注册</a>
						</li>
					</ul>
					</c:otherwise>
				</c:choose>
				</div>
				
			</nav>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-3 column">
			<ul class="nav nav-stacked nav-pills">
				<li class="active">
					 <a target="content_frame" href="${pageContext.request.contextPath}/client/BookServlet?method=getBooks">全部分类</a>
				</li>
				
				<!-- 遍历集合 -->
				<c:forEach items="${allCategorys}" var="category">
				<li class="text-center">
					<a target="content_frame" href="<%=request.getContextPath() %>/client/BookServlet?method=getBooks&categorysid=${category.id}">${category.name}</a><br />
				</li>
				</c:forEach>
				<li class="nav-divider">
				</li>
				<li class="active">
					<a target="content_frame" href="${pageContext.request.contextPath}/client/BookServlet?method=getBooks">用户功能</a>
				</li>
				<li class="text-center">
					<a target="content_frame" href="${pageContext.request.contextPath}/client/OrderServlet?method=listOrders">查看订单</a>
				</li>
				<li class="text-center">
					<a target="content_frame" href="${pageContext.request.contextPath}/client/book/cart.jsp">查看购物车</a>
				</li>
				<li class="nav-divider">
				</li>
				<li class="dropdown active">
					<a href="#" data-toggle="dropdown" class="dropdown-toggle"><strong>更多功能</strong><strong class="caret"></strong></a>
					<ul class="dropdown-menu" role="menu">
						<li>
							<a target="content_frame" href="${pageContext.request.contextPath}/client/BookServlet?method=showHistoryBooks">查看浏览记录</a>
						</li>
						<li>
							<a target="content_frame" href="${pageContext.request.contextPath}/client/user/users.jsp">查看在线用户</a>
						</li>
						<li>
							<a href="${pageContext.request.contextPath}/manager/manager.jsp">进入后台</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>
		<div class="col-md-9 column">
			<iframe name="content_frame" src="${pageContext.request.contextPath}/client/BookServlet?method=getBooks" style="height: 700px; width: 100%;"></iframe>
			<!-- 外域 -->
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-12 column">
			<ul class="list-unstyled">
				<li class="divider">
					<hr />
				</li>
				<li class="text-right">
					 Version&nbsp;2.1&nbsp;&nbsp;by WHL
				</li>
			</ul>
		</div>
	</div>
</div>
</body>
</html>

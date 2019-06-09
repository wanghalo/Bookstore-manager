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
<title>后台管理模块</title>
</head>
<body>
<div class="container-fluid">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<div class="page-header">
				<h2>
					欢迎使用图书销售管理系统 <small>后台管理模块</small>
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
					<a class="navbar-brand" href="${pageContext.request.contextPath}/manager/manager.jsp">图书销售管理系统</a>
				</div>
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav navbar-right">
						<li>
							 <a href="${pageContext.request.contextPath}/index.jsp">返回前台</a>
						</li>
					</ul>
				</div>
				
			</nav>
		</div>
	</div>
	<div class="row clearfix">
		<div class="col-md-3 column">
			<ul class="nav nav-stacked nav-pills">
				<li class="active">
					<a href="#"><strong>分类管理</strong></a>
				</li>
				<li class="text-center">
					 <a target="content_frame" href="${pageContext.request.contextPath}/manager/category/add.jsp">添加分类</a>
				</li>
				<li class="text-center">
					 <a target="content_frame" href="${pageContext.request.contextPath}/manager/CategoryServlet?method=list">查看分类</a>
				</li>
				<li class="active">
					<a href="#"><strong>图书管理</strong></a>
				</li>
				<li class="text-center">
					<a target="content_frame" href="${pageContext.request.contextPath}/manager/BookServlet?method=toAddUI">添加图书</a>
				</li>
				<li class="text-center">
					<a target="content_frame" href="${pageContext.request.contextPath}/manager/BookServlet?method=listAll">查看图书</a>
				</li>
				<li class="active">
					<a href="#"><strong>订单管理</strong></a>
				</li>
				<li class="text-center">
					<a target="content_frame" href="${pageContext.request.contextPath}/manager/OrderServlet?method=showOrders&status=true">已发货订单管理</a>
				</li>
				<li class="text-center">
					<a target="content_frame" href="${pageContext.request.contextPath}/manager/OrderServlet?method=showOrders&status=false">未发货订单管理</a>
				</li>
<!--
				<li class="divider">
					<hr />
				</li>
-->
			</ul>
		</div>
		<div class="col-md-9 column">
			<iframe name="content_frame" src="<%=request.getContextPath()%>/client/BookServlet?method=getBooks" style="height: 700px; width: 100%;"></iframe>
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

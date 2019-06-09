<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>开始</title>
</head>
<body>
	<%
		response.sendRedirect(request.getContextPath() + "/client/ClientServlet?method=toClientUI");
	%>
</body>
</html>
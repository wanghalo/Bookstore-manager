<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<title>用户注册</title>
<script type="text/javascript">
	$(function(){
		$("#submitBtn").click(function(){
			//检查用户名: 长度为6到12位, 只能输入数字和英文和下划线
			//alert("--");
			var $nameEle = $("#name");
			var reg = /^\w{6,12}$/;
			if(!reg.test($nameEle.val())) {
				alert("用户名长度为6到12位, 只能输入数字和英文和下划线");
				$nameEle[0].focus();
				return false;
			}
			
			//检查密码: 长度为6位, 只能输入数字和英文
			var $passwordEle = $("#password");
			reg = /^[a-zA-Z0-9]{6,8}$/;
			if(!reg.test($passwordEle.val())) {
				alert("密码的长度为6位, 只能输入数字和英文");
				$passwordEle[0].focus();
				return false;
			}
			
			//确认密码必须与密码相同
			var $password2Ele= $("#password2");
			if($password2Ele.val()!=$passwordEle.val()) {
				$password2Ele[0].focus();
				alert("确认密码必须与密码相同");
				return false;
			}
			
			//邮箱格式必须正确, 可以不输入
			var $emailEle = $("#email");
			reg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			if($emailEle.val()!="" && !reg.test($emailEle.val())) {
				$emailEle[0].focus();
				alert("邮箱格式不正确!");
				return false;
			}
			return true;
		});
		
		$("img").click(function(){
			this.src = this.src;
		});
		
		$("#name").blur(function(){
			var reg = /^\w{6,12}$/;
			if(!reg.test(this.value)) {
				this.focus();
				return false;
			} else {
				var url = "${pageContext.request.contextPath}/client/UserServlet";
				$.post(
					url,
					{method:"checkNameExist",username:this.value},
					function(data) {
						$("#result").html(data);
					}
				);
			}
		});
	});
</script>
</head>
<body>
<div class="container">
	<div class="col-sm-12" style="height: 100px;"></div>
<h2>新用户注册</h2>
<div class="divider"><hr /></div>
	<form class="form-horizontal" role="form" action="${pageContext.request.contextPath }/client/UserServlet" method="post">
	<input type="hidden" name="method" value="regist">
	
		<div class="col-sm-12" style="height: 30px;"></div>
		
		<div class="form-group">
			<div class="col-sm-push-2 col-sm-8 input-group">
				<span class="input-group-addon" id="basic-addon1">
					<span class="glyphicon glyphicon-user"></span>
				</span>
				<input class="txt form-control input-block" id="name" type="text" name="username" value="${param.username}" placeholder="请输入用户名" />
				<font color="red">${message }</font>
				<span id="result"></span>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-push-2 col-sm-8 input-group">
				<span class="input-group-addon" id="basic-addon2">
					<span class="glyphicon glyphicon-lock"></span>
				</span>
				<input class="txt form-control input-block" id="password" type="password" name="password" placeholder="请输入密码" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-push-2 col-sm-8 input-group">
				<span class="input-group-addon" id="basic-addon3">
					<span class="glyphicon glyphicon-lock"></span>
				</span>
				<input class="txt form-control input-block" id="password2" type="password" name="password2" placeholder="请再次输入密码" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-push-2 col-sm-8 input-group">
				<span class="input-group-addon" id="basic-addon4">
					<span class="glyphicon glyphicon-envelope"></span>
				</span>
				<input class="txt form-control input-block" id="email" type="text" name="email" value="${param.email}" placeholder="请输入邮箱" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-push-2 col-sm-8 input-group">
				<span class="input-group-addon">
					<span><img src="${pageContext.request.contextPath }/client/ValidateColorServlet"></span>
				</span>
				<input class="form-control input-lg" id="code" type="text" name="code" placeholder="请输入验证码" />
			</div>
		</div>
				
	<div class="col-sm-12" style="height: 50px;"></div>	
		
		<div class="form-group">
			<div class="col-sm-push-2 col-sm-8">
				<button type="submit" class="btn btn-success btn-block btn-lg" id="submitBtn">注册</button>
			</div>
		</div>
		
	<div class="col-sm-12" style="height: 50px;"></div>
	
	</form>
		<div class="form-group text-right col-sm-push-6 col-sm-6">
			已有账号？去<a href="${pageContext.request.contextPath}/client/user/login.jsp">登录</a>！
		</div>
</div>
</body>
</html>
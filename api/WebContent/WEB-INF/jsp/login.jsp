<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>驿站app官网</title>
</head>
    <!-- Theme style -->
    <link rel="stylesheet" href="css/adminlte.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<body style="background:url('img/background.jpg');background-repeat:no-repeat"> 
<h1 style="text-align:center">驿站app官网</h1>
<button style="margin-left:20px;margin-top:20px;width:150px" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" onclick="login()">管理员登录</button>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<!--登陆框头部-->
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					管理员登录
				</h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    ×
                </button>
			</div>
			<!--登陆框中间部分(from表单)-->
			<div class="modal-body">
				<form id="form1" class="form-horizontal" role="form" method="post" action="main.jsp">
					<!--用户框-->
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="username" placeholder="username" required="required">
						</div>
					</div>
					<!--密码框-->
					<div class="form-group">
						<label for="password" class="col-sm-2 control-label" >密码</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" id="password" placeholder="password" required="required">
						</div>
					</div>
					<!--记住密码-->
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label>
									<input type="checkbox"> 记住密码
								</label>
							</div>
						</div>
					</div>
					<!--登陆按钮-->
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
						<button type="button" onclick="check()" class="btn btn-default">登录</button>
						</div>
					</div>
				</form>
		</div>	
	</div>
</div>
</div>
    <script src="js/jquery.min.js"></script>
    <!-- Bootstrap 4 -->
    <script src="js/bootstrap.bundle.min.js"></script>
    <!-- DataTables -->


    <!-- AdminLTE App -->
  
    <!-- AdminLTE for demo purposes -->

<script type="text/javascript">
function login(){
	    var username = document.getElementById("username");
		$('#myModal').modal('show') //显示模态框
}
function check(){
	var account=document.getElementById("username").value;
	var password=document.getElementById("password").value;
	if(account=="admin"&&password=="123"){		
		document.getElementById("form1").submit();
		alert("欢迎你！尊贵的"+account+"管理员");
	}
	else{
		alert("验证失败")
	}
}
</script>
</body>
</html>
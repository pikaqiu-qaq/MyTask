<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>AdminLTE 3 | DataTables</title>

    <!-- Google Font: Source Sans Pro -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="css/all.min.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="css/dataTables.bootstrap4.min.css">
    <link rel="stylesheet" href="css/responsive.bootstrap4.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="css/adminlte.min.css">
</head>

<body class="hold-transition sidebar-mini">
    <div class="wrapper">
        <!-- Navbar -->
        <nav class="main-header navbar navbar-expand navbar-white navbar-light">
            <!-- Left navbar links -->
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a>
                </li>
            </ul>

            <!-- Right navbar links -->
            <ul class="navbar-nav ml-auto">
                <!-- Messages Dropdown Menu -->

                <!-- Notifications Dropdown Menu -->

                <li class="nav-item">
                    <a class="nav-link" data-widget="control-sidebar" data-slide="true" href="#" role="button">
                        <i class="fas fa-th-large"></i>
                    </a>
                </li>
            </ul>
        </nav>
        <!-- /.navbar -->

        <!-- Main Sidebar Container -->
        <aside class="main-sidebar sidebar-dark-primary elevation-4">
            <!-- Brand Logo -->
            <a href="index3.html" class="brand-link">
                <img src="img/cartonhorse.jpg" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
                <span class="brand-text font-weight-light">se驿站</span>
            </a>


            <!-- Sidebar -->
            <div class="sidebar">
                <!-- Sidebar user panel (optional) -->
                <div class="user-panel mt-3 pb-3 mb-3 d-flex">
                    <div class="image">
                        <img src="img/hanfei.jpg" class="img-circle elevation-2" alt="User Image">
                    </div>
                    <div class="info">
                        <a href="#" class="d-block">Yuyue</a>
                    </div>
                </div>

                <!-- Sidebar Menu -->
                <nav class="mt-2">
                    <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                        <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
                        <li class="nav-item has-treeview menu-open">
            <a href="#" class="nav-link active">
              <i class="nav-icon fas fa-tachometer-alt"></i>
              <p>
                面板
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="主界面.html" class="nav-link active"> 
                   <i class="nav-icon fas fa-circle"></i>
                  <p>主面板</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="user_table.jsp" class="nav-link active">
                   <i class="nav-icon fas fa-circle"></i>
                  <p>用户管理</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="comment_table.jsp" class="nav-link active">
                   <i class="nav-icon fas fa-circle"></i>
                  <p>评论管理</p>
                </a>
              </li>
            </ul>
          </li> 
                    </ul>
                </nav>
                <!-- /.sidebar-menu -->
            </div>
            <!-- /.sidebar -->
        </aside>

        <!-- Content Wrapper. Contains page content -->
        <div class="content-wrapper">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <div class="container-fluid">
                    <div class="row mb-2">
                        <div class="col-sm-6">
                            <h1>用户管理</h1>
                        </div>
                        <div class="col-sm-6">
                            <ol class="breadcrumb float-sm-right">
                                <li class="breadcrumb-item"><a href="#">Home</a></li>
                                <li class="breadcrumb-item active">DataTables</li>
                            </ol>
                        </div>
                    </div>
                </div>
                <!-- /.container-fluid -->
            </section>

            <!-- Main content -->
            <section class="content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h3 class="card-title">se驿站</h3>
                                </div>
                                <!-- /.card-header -->
                                <div class="card-body">
                                    <table id="example1" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                                <th>用户id</th>
                                                <th>邮箱</th>
                                                <th>用户名</th>
                                                <th>性别</th>
                                                <th>注册时间</th>
                                                <th>账号状态</th>
                                                <th>管理</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                com.mybatis.MyBatiser m = new com.mybatis.MyBatiser();
                                                java.util.List<com.bean.User> users = m.selectAllUsers();
                                                for(int i=0;i<users.size();i++){
                                            %>
                                            <tr>
                                                <td><%=users.get(i).getUser_id()%></td>
                                                <td><%=users.get(i).getEmail()%></td>
                                                <td><%=users.get(i).getUser_name()%></td>
                                                <td><%=users.get(i).getGender()==0?"未知":(users.get(i).getGender()==1?"男":"女")%></td>
                                                <td><%=users.get(i).getRegister_time()%></td>
                                                <%
                                                    String color;
                                                    if(users.get(i).getBan()==0)color="Green";
                                                    else color="red";
                                                %>
                                                <td style="color:<%=color%>" id="ban<%=i%>"><%=users.get(i).getBan()==0?"正常":"冻结"%></td>
                                                <td><button type="button" id="bt<%=i %>" onclick="go('<%=users.get(i).getUser_id()%>','<%=((users.get(i).getBan()==0)?"冻结":"解冻")%>','<%=i%>')" class="btn btn-default"><%=(users.get(i).getBan()==0)?"冻结":"解冻"%></button></td>
                                            </tr>
                                            <%
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                                <!-- /.card-body -->
                            </div>
                            <!-- /.card -->
                            <!-- /.card -->
                        </div>
                        <!-- /.col -->
                    </div>
                    <!-- /.row -->
                </div>
                <!-- /.container-fluid -->
            </section>
            <!-- /.content -->
        </div>
        <!-- /.content-wrapper -->
        <footer class="main-footer">
            <div class="float-right d-none d-sm-block">
                <b>Version</b> 3.1.0-pre
            </div>
            <strong>Copyright &copy; 2014-2020 <a href="https://adminlte.io">AdminLTE.io</a>.</strong> All rights reserved.
        </footer>

        <!-- Control Sidebar -->
        <aside class="control-sidebar control-sidebar-dark">
            <!-- Control sidebar content goes here -->
        </aside>
        <!-- /.control-sidebar -->
    </div>
    <!-- ./wrapper -->

    <!-- jQuery -->
    <script src="js/jquery.min.js"></script>
    <!-- Bootstrap 4 -->
    <script src="js/bootstrap.bundle.min.js"></script>
    <!-- DataTables -->
    <script src="js/jquery.dataTables.min.js"></script>
    <script src="js/dataTables.bootstrap4.min.js"></script>
    <script src="js/dataTables.responsive.min.js"></script>
    <script src="js/responsive.bootstrap4.min.js"></script>
    <!-- AdminLTE App -->
    <script src="js/adminlte.min.js"></script>
    <!-- AdminLTE for demo purposes -->
    <script src="js/demo.js"></script>
    <script>
        $(function() {
            $("#example1").DataTable({
                "responsive": true,
                "autoWidth": false,
            });
            $('#example2').DataTable({
                "paging": true,
                "lengthChange": false,
                "searching": false,
                "ordering": true,
                "info": true,
                "autoWidth": false,
                "responsive": true,
            });
        });
        
        function go(user_id,action,index){
    		$.post("http://"+window.location.hostname+":"+window.location.port+"/api/v1/ban",{
    			user_id:user_id,
    			action:action,
    		},
    		function(data,status){
    			var status = JSON.parse(data);
    			if(status=="203"){
    				var td=document.getElementById("ban"+index);
    				alert(td.innerHTML);
    				td.innerHTML="冻结";
    				td.style.color="red";
    				var bt=document.getElementById("bt"+index);
    				bt.innerHTML="解冻";
    				alert("冻结成功");
    			}
    			if(status=="204"){
    				var td=document.getElementById("ban"+index);
    				alert(td.innerHTML);
    				td.innerHTML="正常";
    				td.style.color="green";
    				var bt=document.getElementById("bt"+index);
    				bt.innerHTML="冻结";
    				alert("解冻成功");
    			}
    			if(status=="500")alert("账户不存在");
    			if(status=="402")alert("参数格式错误");
    			if(status=="403")alert("参数缺失");
    		});
        } 
    </script>
</body>
</html>
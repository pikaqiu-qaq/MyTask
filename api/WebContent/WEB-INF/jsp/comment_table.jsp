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
                    <a class="nav-link" data-widget="pushmenu" href="#" role="button"><img src="img/open.jpg" style="width:30px;height:30px"></a>
                </li>
            </ul>

            <!-- Right navbar links -->
            <ul class="navbar-nav ml-auto">
                <!-- Messages Dropdown Menu -->

                <!-- Notifications Dropdown Menu -->

                <li class="nav-item">
                    <a class="nav-link" data-widget="control-sidebar" data-slide="true" href="#" role="button">
                        <img src="img/head.jpg" style="width:35px;height:35px">
                    </a>
                </li>
            </ul>
        </nav>
        <!-- /.navbar -->

        <!-- Main Sidebar Container -->
        <aside class="main-sidebar sidebar-dark-primary elevation-4">
            <!-- Brand Logo -->
            <a href="main.jsp" class="brand-link">
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
               <img src="img/red.png" style="width:32px;height:32px" class="img-circle"></img>
               <p style="margin-left:5px"> 面板</p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="main.jsp" class="nav-link active"> 
                <img src="img/yellow.png" style="width:32px;height:32px" class="img-circle"></img>
               <p style="margin-left:5px"> 数据总览</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="user_table.jsp" class="nav-link active">
                  
                   <img src="img/blue.png" style="width:32px;height:32px" class="img-circle"></img>
               <p style="margin-left:5px"> 用户管理</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="comment_table.jsp" class="nav-link active">
                  <img src="img/green.png" style="width:32px;height:32px" class="img-circle"></img>
               <p style="margin-left:5px"> 评论管理</p>
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
                            <h1>评论管理
                            <button id="news" class="btn btn-default" style="color:green" onclick="goto_news()">新闻</button>
                            <button id="anime" class="btn btn-default" style="color:green" onclick="goto_anime()">番剧</button>
                            </h1>
                            
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
                <div class="container-fluid" id="frame" style="width:100%;height:6650px;">
                    <iframe src="news_table.jsp" style="width: 100%;height: 100%;" frameborder="0"></iframe>
                   <!-- iframe -->
                
                </div>
                <!-- /.container-fluid -->
            </section>
            <!-- /.content -->
        </div>


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
    <!-- page script -->
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
        function goto_news() {
        	document.getElementById("frame").innerHTML = "<iframe src='news_table.jsp'"+"style='width: 100%;height: 100%;'"+"></iframe>";
        	document.getElementById("frame").frameborder="0";
        }
        function goto_anime() {
        	document.getElementById("frame").innerHTML = "<iframe src='anime_table.jsp'"+"style='width: 100%;height: 100%;'"+"></iframe>";
        	document.getElementById("frame").frameborder="0";
        }
    </script>
</body>

</html>
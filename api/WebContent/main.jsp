<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>AdminLTE 3 | Dashboard</title>

  <!-- Google Font: Source Sans Pro -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="css/all.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Tempusdominus Bootstrap 4 -->
  <link rel="stylesheet" href="css/tempusdominus-bootstrap-4.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="css/icheck-bootstrap.min.css">
  <!-- JQVMap -->
  <link rel="stylesheet" href="css/jqvmap/jqvmap.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="css/adminlte.min.css">
  <!-- overlayScrollbars -->
  <link rel="stylesheet" href="css/OverlayScrollbars.min.css">
  <!-- Daterange picker -->
  <link rel="stylesheet" href="css/daterangepicker.css">
  <!-- summernote -->
  <link rel="stylesheet" href="css/summernote-bs4.min.css">
  <!-- <link rel="stylesheet" href="css/jquery.ui.min.css">-->

</head>
<body class="hold-transition sidebar-mini layout-fixed" onload="getData()">
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
            <a href="main.html" class="brand-link">
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
    <div class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1 class="m-0 text-dark">数据总览</h1>
          </div><!-- /.col -->
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Home</a></li>
              <li class="breadcrumb-item active">主面板</li>
            </ol>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <!-- Small boxes (Stat box) -->
        <div class="row">
 
          <!-- ./col -->
      
          <!-- ./col -->
        </div>
        <!-- /.row -->
        <!-- Main row -->
        <div class="row">
          <!-- Left col -->
          <section class="col-lg-7 connectedSortable">
            <!-- Custom tabs (Charts with tabs)-->
            <div class="card">
              <div class="card-header">
                <h3 class="card-title" id="title513">

                                 用户增长趋势
                </h3>
                <div class="card-tools">
                  <ul class="nav nav-pills ml-auto">
                    <li class="nav-item">
                      <a class="nav-link active" href="#revenue-chart" data-toggle="tab" onclick="user_date()">折线图</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="#sales-chart" data-toggle="tab" onclick="user_rate()">饼状图</a>
                    </li>
                  </ul>
                </div>
              </div><!-- /.card-header -->
              <div class="card-body">
                <div class="tab-content p-0">
                  <!-- Morris chart - Sales -->
                  <div class="chart tab-pane active" id="revenue-chart"
                       style="position: relative; height: 300px;">
                      <canvas id="revenue-chart-canvas" height="300" style="height: 300px;"></canvas>
                   </div>
                  <div class="chart tab-pane" id="sales-chart" style="position: relative; height: 300px;">
                    <canvas id="sales-chart-canvas" height="300" style="height: 300px;"></canvas>
                  </div>
                </div>
              </div><!-- /.card-body -->
            </div>
            <!-- /.card -->

            
            <!-- /.card -->
          </section>
          <!-- /.Left col -->
          <!-- right col (We are only adding the ID to make the widgets sortable)-->
          <section class="col-lg-5 connectedSortable">

            <!-- Map card -->
           
            <!-- /.card -->

            <!-- solid sales graph -->
            <div class="card bg-gradient-info">
              <div class="card-header border-0">
                <h3 class="card-title">
                  评论数
                </h3>

                <div class="card-tools">
                  <button type="button" class="btn bg-info btn-sm" data-card-widget="collapse">
                     <img src="img/hide.jpg" style="width:20px;height:20px" class="img-circle"></img>
                  </button>
                  <button type="button" class="btn bg-info btn-sm" data-card-widget="remove">
                     <img src="img/close.jpg" style="width:20px;height:20px" class="img-circle"></img>
                  </button>
                </div>
              </div>
              <div class="card-body">
                <canvas class="chart" id="line-chart" style="min-height: 250px; height: 250px; max-height: 250px; max-width: 100%;"></canvas>
              </div>
              <!-- /.card-body -->
             
              <!-- /.card-footer -->
            </div>
            <!-- /.card -->
            <div class="">
              <div class="">
                
                <!-- card tools -->
                
                <!-- /.card-tools -->
              </div>
              
              <!-- /.card-body-->
              <div class="">
                <div class="">
                  <div class="">
                    <div id="sparkline-1"></div>
                    <div class="text-white"></div>
                  </div>
                  <!-- ./col -->
                  <div class="">
                    <div id="sparkline-2"></div>
                    <div class="text-white"></div>
                  </div>
                  <!-- ./col -->
                  <div class="">
                    <div id="sparkline-3"></div>
                    <div class="text-white"></div>
                  </div>
                  <!-- ./col -->
                </div>
                <!-- /.row -->
              </div>
            </div>
            <!-- Calendar -->
            
            <!-- /.card -->
          </section>
          <!-- right col -->
        </div>
        <!-- /.row (main row) -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <strong>Copyright &copy; 2014-2020 <a href="https://adminlte.io">AdminLTE.io</a>.</strong>
    All rights reserved.
    <div class="float-right d-none d-sm-inline-block">
      <b>Version</b> 3.1.0-pre
    </div>
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
<!-- jQuery UI 1.11.4 -->
<script src="js/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
  $.widget.bridge('uibutton', $.ui.button)
</script>
<!-- Bootstrap 4 -->
<script src="js/bootstrap.bundle.min.js"></script>
<!-- ChartJS -->
<script src="js/Chart.min.js"></script>
<!-- Sparkline -->
<script src="js/sparkline.js"></script>
<!-- JQVMap -->
<script src="js/jquery.vmap.min.js"></script>
<script src="js/jquery.vmap.usa.js"></script>
<!-- jQuery Knob Chart -->
<script src="js/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script src="js/moment.min.js"></script>
<script src="js/daterangepicker.js"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="js/tempusdominus-bootstrap-4.min.js"></script>
<!-- Summernote -->
<script src="js/summernote-bs4.min.js"></script>
<!-- overlayScrollbars -->
<script src="js/jquery.overlayScrollbars.min.js"></script>
<!-- AdminLTE App -->
<script src="js/adminlte.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<!-- AdminLTE for demo purposes -->
<script src="js/demo.js"></script>

<script > 
function user_date(){
	var x = document.getElementById("title513");
	x.innerHTML = "用户增长趋势"
}
function user_rate(){
	var x = document.getElementById("title513");
	x.innerHTML = "用户性别比例"
}
function getData()
{
 $.ajax({
  type:"get",  
  url:"http://"+window.location.hostname+":"+window.location.port+"/api/v1/manager_index",
  data: {"rn": Math.floor(Math.random() * 100 + 1) },
  success:function(data)
  {
    /* Chart.js Charts */
  // Sales chart  
  var salesChartCanvas = document.getElementById('revenue-chart-canvas').getContext('2d')
  // $('#revenue-chart').get(0).getContext('2d');
  var dat = eval ("(" + data + ")"); 
  var one_day=dat["one_day"];
  var one_value=dat["one_value"];
  var two_day=dat["two_day"];
  var two_value=dat["two_value"];
  var three_day=dat["three_day"];
  var three_value=dat["three_value"];
  var four_day=dat["four_day"];
  var four_value=dat["four_value"];
  var five_day=dat["five_day"];
  var five_value=dat["five_value"];
  var six_day=dat["six_day"];
  var six_value=dat["six_value"];
  var seven_day=dat["seven_day"];
  var seven_value=dat["seven_value"];
  var zero_day=dat["zero_day"];
  var fone_day=dat["fone_day"];
  var ftwo_day=dat["ftwo_day"];
  var alien_value=dat["alien_value"];
  var man_value=dat["man_value"];
  var woman_value=dat["woman_value"];
  var ftwo_covalue=dat["ftwo_covalue"];
  var fone_covalue=dat["fone_covalue"];
  var zero_covalue=dat["zero_covalue"];
  var one_covalue=dat["one_covalue"];
  var two_covalue=dat["two_covalue"];
  var three_covalue=dat["three_covalue"];
  var four_covalue=dat["four_covalue"];
  var five_covalue=dat["five_covalue"];
  var six_covalue=dat["six_covalue"];
  var seven_covalue=dat["seven_covalue"];


  var salesChartData = {
    labels: [one_day, two_day, three_day, four_day, five_day, six_day, seven_day],
    datasets: [
     /*
      {
        label: 'Digital Goods',
        backgroundColor: 'rgba(60,141,188,0.9)',
        borderColor: 'rgba(60,141,188,0.8)',
        pointRadius: false,
        pointColor: '#3b8bba',
        pointStrokeColor: 'rgba(60,141,188,1)',
        pointHighlightFill: '#fff',
        pointHighlightStroke: 'rgba(60,141,188,1)',
        data: [28, 48, 40, 19, 86, 27, 90]
      },
      */
      {
        label: 'Electronics',
        backgroundColor: 'rgba(255, 255, 255, 1)',
        borderColor: 'rgba(110, 114, 222, 1)',
        pointRadius: false,
        pointColor: 'rgba(210, 214, 222, 1)',
        pointStrokeColor: '#c1c7d1',
        pointHighlightFill: '#fff',
        pointHighlightStroke: 'rgba(220,220,220,1)',
        data: [one_value, two_value, three_value, four_value, five_value, six_value, seven_value]
      }
    ]
  }

  var salesChartOptions = {
    maintainAspectRatio: false,
    responsive: true,
    legend: {
      display: false
    },
    scales: {
      xAxes: [{
        gridLines: {
          display: false
        }
      }],
      yAxes: [{
        gridLines: {
          display: false
        }
      }]
    }
  }

  // This will get the first returned node in the jQuery collection.
  // eslint-disable-next-line no-unused-vars
  var salesChart = new Chart(salesChartCanvas, {
    type: 'line',
    data: salesChartData,
    options: salesChartOptions
  })

  // Donut Chart
  var pieChartCanvas = $('#sales-chart-canvas').get(0).getContext('2d')
  var pieData = {
    
    labels: [
      '男' ,
      '女',
      '外星人'
    ],
    datasets: [
      {
        data: [man_value, woman_value,alien_value],
        backgroundColor: ['#f56954', '#00a65a', '#f39c12']
      }
    ]
  }
  var pieOptions = {
    legend: {
      display: false
    },
    maintainAspectRatio: false,
    responsive: true
  }
  // Create pie or douhnut chart
  // You can switch between pie and douhnut using the method below.
  // eslint-disable-next-line no-unused-vars
  var pieChart = new Chart(pieChartCanvas, {
    type: 'doughnut',
    data: pieData,
    options: pieOptions
  })

  // Sales graph chart
  var salesGraphChartCanvas = $('#line-chart').get(0).getContext('2d')
  // $('#revenue-chart').get(0).getContext('2d');

  var salesGraphChartData = {
    labels: [ftwo_day, fone_day, zero_day, one_day, two_day, three_day, four_day, five_day, six_day, seven_day],
    datasets: [
      {
        label: 'Digital Goods',
        fill: false,
        borderWidth: 2,
        lineTension: 0,
        spanGaps: true,
        borderColor: '#f39c12',
        pointRadius: 3,
        pointHoverRadius: 7,
        pointColor: '#efefef',
        pointBackgroundColor: '#efefef',
        data: [ftwo_covalue, fone_covalue, zero_covalue, one_covalue, two_covalue, three_covalue, four_covalue, five_covalue, six_covalue, seven_covalue]
      }
    ]
  }

  var salesGraphChartOptions = {
    maintainAspectRatio: false,
    responsive: true,
    legend: {
      display: false
    },
    scales: {
      xAxes: [{
        ticks: {
          fontColor: '#efefef'
        },
        gridLines: {
          display: false,
          color: '#efefef',
          drawBorder: false
        }
      }],
      yAxes: [{
        ticks: {
          stepSize: 200,
          fontColor: '#efefef'
        },
        gridLines: {
          display: true,
          color: '#efefef',
          drawBorder: false
        }
      }]
    }
  }

  // This will get the first returned node in the jQuery collection.
  // eslint-disable-next-line no-unused-vars
  var salesGraphChart = new Chart(salesGraphChartCanvas, {
    type: 'line',
    data: salesGraphChartData,
    options: salesGraphChartOptions
  })
  }
  })
}

</script>

</body>
</html>

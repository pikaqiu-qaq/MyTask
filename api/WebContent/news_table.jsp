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
<body>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<!--登陆框头部-->
			<div class="modal-header">
				<h4 class="modal-title" id="myModalLabel">
					评论内容
				</h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    ×
                </button>
			</div>
			<!--登陆框中间部分(from表单)-->
			<div class="modal-body">
			<textarea id="content" placeholder="此处显示评论内容" readonly="readonly" style="border:0;height:300px;width:450px"></textarea>
		</div>	
		</div>	
	</div>
</div>

        <div class="row">
            <div class="col-12">
                <div class="card">
                    <div class="card-header">
                        <h3 class="card-title">新闻评论列表</h3>
                     </div>
                     <div class="card-body">
                        <table id="example1" class="table table-bordered table-hover">
                             <thead>
                                 <tr>
                                     <th>评论id</th>
                                     <th>资讯id</th>
                                     <th>评论者user_id</th>
                                     <th>发表时间</th>
                                     <th>评论内容</th>
                                     <th>管理</th>
                                 </tr>
                             </thead>
                             <tbody>
                                 <%
                                     com.mybatis.MyBatiser m = new com.mybatis.MyBatiser();
                                     java.util.List<com.bean.Comment_news> comment_news_all = m.selectAllComment_news();
                                     for(int i=0;i<comment_news_all.size();i++){
                                 %>
                                 <tr id="tr<%=i%>">
                                     <td><%=comment_news_all.get(i).getId()%></td>
                                     <td><%=comment_news_all.get(i).getNews_id()%></td>
                                     <td><%=comment_news_all.get(i).getUser_id()%></td>
                                     <td><%=new java.util.Date(comment_news_all.get(i).getCreate_time())%></td>
                                     <td><button id="look<%=i%>" onclick="read('<%=comment_news_all.get(i).getContent()%>')" class="btn btn-default" style="color:green">查看</button></td>
                                     <td><button id="delete<%=i%>" onclick="remove('<%=i%>','<%=comment_news_all.get(i).getId()%>')" class="btn btn-default" style="color:red">删除</button></td>
                                 </tr>
                                 <%
                                     } 
                                 %>
                             </tbody>
                        </table>
                    </div>    
                </div>
            </div>
    </div>
    <!-- /.content-wrapper -->
        <footer class="main-footer">
            <div class="float-right d-none d-sm-block">
                <b>Version</b> 3.1.0-pre
            </div>
            <strong>Copyright &copy; 2014-2020 <a href="https://adminlte.io">AdminLTE.io</a>.</strong> All rights reserved.
        </footer>
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
        
        function look(content){
        	alert(content);
        }
        
        function remove(index,id){
        	$.post("http://"+window.location.hostname+":"+window.location.port+"/api/v1/cut",{
    			comment_type:"news",
    			id:id,
    		},
    		function(data,status){
    			var status = JSON.parse(data);
    			if(status=="200"){
    				tr = document.getElementById("tr"+index);
        			tr.parentNode.removeChild(tr);
        			alert("删除成功");
    			}
    			else if(status=="500"){
    				alert("服务器出错");
    			}
    		});
        }
        function read(content){
    	    var username = document.getElementById("content");
    	    username.value = content;
    		$('#myModal').modal('show') //显示模态框
        }
    </script> 
</body>
</html>
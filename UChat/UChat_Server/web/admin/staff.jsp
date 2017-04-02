<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>校园社交管理系统</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<base href="<%=basePath%>">
<link rel="stylesheet" href="admin/css/bootstrap.min.css" />
<link rel="stylesheet" href="admin/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="admin/css/uniform.css" />
<link rel="stylesheet" href="admin/css/select2.css" />
<link rel="stylesheet" href="admin/css/matrix-style.css" />
<link rel="stylesheet" href="admin/css/matrix-media.css" />
<link href="admin/font-awesome/css/font-awesome.css" rel="stylesheet" />
</head>
<body>

<!--Header-part-->
<div id="header">
	<h3><img src="admin/img/logo2.png" alt="Logo" style="margin-left: 35px; margin-top: 25px;" /></h3>
</div>
<!--close-Header-part-->


<!--头部导航 开始-->
<div id="user-nav" class="navbar navbar-inverse">
	<ul class="nav">
		<li  class="dropdown" id="profile-messages" >
			<a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle">
				<i class="icon icon-user"></i>
				<span class="text">员工：${sesson.admin.username}
			</span><b class="caret"></b>
			</a>
			<ul class="dropdown-menu">
				<li><a href="admin/login.jsp"><i class="icon-key"></i> 修改密码</a></li>
			</ul>
		</li>
		<li class=""><a href="admin/login.jsp"><i class="icon-off"></i> <span class="text">退出系统</span></a></li>
	</ul>
</div>
<!--头部导航 结束-->
<div id="day">今日是<span id="today"></span></div>


<!--左侧导航 开始-->
<div id="sidebar"><a href="#" class="visible-phone"><i class="icon icon-home"></i> UChat</a>
	<ul>
		<li class="submenu"><a href="#"><i class="icon icon-group"></i> <span>人员管理</span>
			<span class="icon-chevron-down menu-right"></span></a>
			<ul>
				<li><a href="/AdminServlet?type=select">管理员管理</a></li>
				<li><a href="/UserServlet?type=select">用户管理</a></li>
			</ul>
		</li>

		<li><a href="/ChartServlet?type=selectday"><i class="icon icon-th-list"></i> <span>考勤报表</span>

		</li>
	</ul>
</div>
<!--sidebar-menu-->


<div id="content">
  <div id="content-header">
    <div id="breadcrumb"><a href="admin/index.jsp" title="前往首页" class="tip-bottom"><i class="icon-home"></i>首页</a><a href="/AdminServlet?type=select" class="current">管理员管理</a> </div>
  </div>
  <a class="btn btn-success" href="admin/addstaff.jsp" style="float:right; margin-top:10px; margin-right:20px;">新增员工</a>
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="widget-box">
          <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
            <h5>管理员列表</h5>
          </div>
          <div class="widget-content nopadding">
            <table class="table table-bordered data-table">
              <thead>
                <tr>
                  <th>序号</th>
                  <th>工号</th>
                  <th>姓名</th>
                  <th>入职日期</th>
                  <th>操作</th>
                </tr>
              </thead>
              <tbody>

				<c:forEach items="${adminList}" var="admin" varStatus="stat">
                <tr class="gradeX" id="trr${stat.index+1}">
                  	<td>${stat.index+1}</td>
					<td>${admin.loginname}</td>
					<td>${admin.username}</td>
					<td>${admin.createtime}</td>
                  	<td>
				  &nbsp;&nbsp;&nbsp;&nbsp;<a class="tip" href="/AdminServlet?type=select_update&id=${admin.id}" title="修改"><i class="icon-pencil"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="deletenono(${stat.index+1},${admin.id})" class="tip" href="javascript:;" title="删除"><i class="icon-remove"></i></a> </td>
                </tr>
				</c:forEach>

              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
	<input value="${fn:length(adminList)}" id="listsize" type="hidden">
</div>


<!--Footer-part-->
<div class="row-fluid">
  <div id="footer" class="span12"> 2016 &copy; Supermarket. By Xu Ge </div>
</div>
<!--end-Footer-part-->
<script src="admin/js/jquery.min.js"></script> 
<script src="admin/js/jquery.ui.custom.js"></script> 
<script src="admin/js/bootstrap.min.js"></script> 
<script src="admin/js/jquery.uniform.js"></script> 
<script src="admin/js/select2.min.js"></script> 
<script src="admin/js/jquery.dataTables.min.js"></script> 
<script src="admin/js/matrix.js"></script> 
<script src="admin/js/matrix.tables.js"></script>
<script src="admin/js/authority.js"></script>
<script type="text/javascript">
$(document).ready(function(){

//    删除后隐藏
    <%--var listsiz = $("#listsize").val();--%>

    <%--var i=1;--%>
    <%--while(i<=listsiz){--%>
        <%--var del = document.getElementById("delete"+i);--%>
        <%--if(del.onclick){--%>
            <%--del.onclick = function(){ //给button加上一个点击事件--%>
				<%--alert("aaa");--%>
                <%--//alert("/AdminServlet?type=delete&id=${admin.id}");--%>
                <%--// var del = document.getElementById("#tr"+i).style.display = "none";--%>
        <%--}else {--%>
                <%--i++;--%>
                <%--alert("ii:"+i);--%>
			<%--}--%>
    <%--}--%>
	<%--}--%>

// 日期
	var dd = new Date(); 
	var y = dd.getFullYear(); 
	var m = dd.getMonth()+1;//获取当前月份的日期 
	var d = dd.getDate(); 
	document.getElementById("today").innerHTML=y+"年"+m+"月"+d+"日";


    var logoff =document.getElementById("logoff");//获取btn元素
    logoff.onclick = function(){ //给button加上一个点击事件
        var answer = confirm("是否想退出登录") //把确认框赋值给answer
        if(answer) //判断是否点击确定
            window.location ="admin/login.jsp"; //确定的话游览器自身跳转
    }

});

function deletenono(index,delid) {
    var del = document.getElementById("trr"+index).style.display = "none";
    location.href='/AdminServlet?type=delete&id='+delid;

}
</script>
</body>
</html>

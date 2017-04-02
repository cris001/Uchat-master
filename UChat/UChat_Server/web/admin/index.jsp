<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%--<%@taglib prefix="s" uri="/struts-tags"%>--%>
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
	<link rel="stylesheet" href="admin/css/fullcalendar.css" />
	<link rel="stylesheet" href="admin/css/matrix-style.css" />
	<link rel="stylesheet" href="admin/css/matrix-media.css" />
	<link href="admin/font-awesome/css/font-awesome.css" rel="stylesheet" />
	<link rel="stylesheet" href="admin/css/jquery.gritter.css" />
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
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
		<li  class="dropdown" id="profile-messages" ><a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle"><i class="icon icon-user"></i>  <span class="text">员工：<s:property value="%{#session.staff.staffName}"/></span><b class="caret"></b></a>
			<ul class="dropdown-menu">
				<li><a href="admin/login.jsp"><i class="icon-key"></i> 修改密码</a></li>
			</ul>
		</li>
		<li class=""><a href="" id="logoff"><i class="icon-off"></i> <span class="text">退出系统</span></a></li>
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

<!--main-container-part 内容开始-->
<div id="content">
	<!--breadcrumbs 内容头-->
	<div id="content-header">
		<div id="breadcrumb"> <a href="admin/index.jsp" title="前往首页" class="tip-bottom"><i class="icon-home"></i>首页</a></div>
	</div>
	<!--End-breadcrumbs-->
	<!--Action boxes-->
	<div class="container-fluid">
		<div class="quick-actions_homepage">
			<ul class="quick-actions">
				<li class="bg_lb span3"> <a href="/AdminServlet?type=select"><i class="icon icon-retweet"></i>管理员</a> </li>
				<li class="bg_lo span5"><a href="/UserServlet?type=select"> <i class="icon icon-group"></i>用户</a> </li>
				<li class="bg_lg span2"><a href="/ChartServlet?type=selectday"> <i class="icon icon-check"></i>考勤报表</a> </li>

			</ul>
		</div>
		<!--End-Action boxes-->


		<div class="container-fluid">
			<hr>
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box widget-calendar">
						<div class="widget-title"> <span class="icon"><i class="icon-calendar"></i></span>
							<h5>日历</h5>

						</div>
						<div class="widget-content">
							<div id="fullcalendar"></div>
							<div id="external-events" class="panel-right">            </div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--Footer-part-->
<div class="row-fluid">
	<div id="footer" class="span12"> 2017 &copy; UChat. By Xu Ge </div>
</div>
<!--end-Footer-part-->
<script src="admin/js/jquery.min.js"></script>
<script src="admin/js/jquery.ui.custom.js"></script>
<script src="admin/js/bootstrap.min.js"></script>
<script src="admin/js/fullcalendar.min.js"></script>
<script src="admin/js/matrix.js"></script>
<script src="admin/js/matrix.calendar.js"></script>
<script src="admin/js/authority.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
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
</script>
</body>
</html>

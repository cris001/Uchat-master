<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<link rel="stylesheet" href="admin/css/styles.css" />
<link rel="stylesheet" href="admin/css/matrix-style.css" />
<link rel="stylesheet" href="admin/css/matrix-media.css" />
<link href="admin/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800'
	rel='stylesheet' type='text/css'>
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
				<%--<s:property value="%{#session.admin.username}"/>--%>
			</span><b class="caret"></b>
			</a>
			<ul class="dropdown-menu">
				<!-- <li><a href="#"><i class="icon-user"></i> 个人信息</a></li>
                <li class="divider"></li> -->
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
		<%--<li class="submenu"><a href="#"><i class="icon icon-gift"></i> <span>商品管理</span>--%>
		<%--<span class="icon-chevron-down menu-right"></span></a>--%>
		<%--<ul>--%>
		<%--<s:if test="%{#session.staff.position.goodsAuth==1}">--%>
		<%--<li><a href="parentType/selectAction!findAllParType">商品类型管理</a></li>--%>
		<%--<li><a href="manufacturer/selectAction!findAllManufacturer">生产厂商管理</a></li>--%>
		<%--<li><a href="goods/selectAction!findAllGoods">商品信息管理</a></li>--%>
		<%--<li><a href="redemption/selectAction!findRedeAndGoods">换购商品管理</a></li>--%>
		<%--</s:if>--%>
		<%--<s:else>--%>
		<%--<li><a href="javascript:;" onclick="cannot('3')">商品类型管理</a></li>--%>
		<%--<li><a href="javascript:;" onclick="cannot('4')">生产厂商管理</a></li>--%>
		<%--<li><a href="javascript:;" onclick="cannot('5')">商品信息管理</a></li>--%>
		<%--<li><a href="javascript:;" onclick="cannot('6')">换购商品管理</a></li>--%>
		<%--</s:else>--%>
		<%--</ul>--%>
		<%--</li>--%>
		<%--<li class="submenu"><a href="#"><i class="icon icon-retweet"></i> <span>库存管理</span>--%>
		<%--<span class="icon-chevron-down menu-right"></span></a>--%>
		<%--<ul>--%>
		<%--<s:if test="%{#session.staff.position.stockAuth==1}">--%>
		<%--<li><a href="supplier/selectAction!findAllSupplier">进货厂商管理</a></li>--%>
		<%--<li><a href="instock/selectAction!findAllForSto">库存信息管理</a></li>--%>
		<%--<li><a href="instock/selectAction!findAllInstock">进货管理</a></li>--%>
		<%--</s:if>--%>
		<%--<s:else>--%>
		<%--<li><a href="javascript:;" onclick="cannot('7')">进货厂商管理</a></li>--%>
		<%--<li><a href="javascript:;" onclick="cannot('8')">库存信息管理</a></li>--%>
		<%--<li><a href="javascript:;" onclick="cannot('9')">进货管理</a></li>--%>
		<%--</s:else>--%>
		<%--</ul>--%>
		<%--</li>--%>
		<li><a href="/ChartServlet?type=selectday"><i class="icon icon-th-list"></i> <span>考勤报表</span>
			<%--<span class="icon-chevron-down menu-right"></span></a>--%>
			<%--<ul>--%>
			<%--<s:if test="%{#session.staff.position.sellAuth==1}">--%>
			<%--<li><a href="returnGoods/selectAction!findAllReturn">退货管理</a></li>--%>
			<%--<li><a href="sale/selectAction!findAllSale">销售报表</a></li>--%>
			<%--</s:if>--%>
			<%--<s:else>--%>
			<%--<li><a href="javascript:;" onclick="cannot('10')">退货管理</a></li>--%>
			<%--<li><a href="javascript:;" onclick="cannot('11')">销售报表</a></li>--%>
			<%--</s:else>--%>
			<%--</ul>--%>
		</li>
	</ul>
</div>
<!--sidebar-menu-->
	<div id="content">
		<div id="content-header">
			<div id="breadcrumb">
				<a href="admin/index.jsp" title="前往首页" class="tip-bottom"><i class="icon-home"></i>首页</a><a href="staff/selectAction!findAllStaff" class="tip-bottom">员工管理</a><a
					href="#" class="current">修改员工</a>
			</div>
			<h1>修改员工</h1>
		</div>

		<div class="container-fluid">
			<hr>
			<div class="row-fluid">
				<div class="add-staff">
					<div class="widget-box add-box">
						<div class="widget-title">
							<span class="icon"> <i class="icon-align-justify"></i>
							</span>
							<h5>个人信息</h5>
						</div>
						<div class="widget-content nopadding">
							<form action="AdminServlet" method="get" class="form-horizontal contact_form" id="form" name="form1">
								<div class="control-group">
									<label class="control-label">工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号&nbsp;:</label>
									<div class="controls">
										<input type="hidden" name="id" value="${admin.id}" />
										<input type="hidden" name="type" value="update" />
										<input type="text" class="span11" id="add-input" readonly="readonly" name="loginname" value="${admin.loginname}" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;:</label>
									<div class="controls">
										<input type="text" class="span11" id="add-input" name="username" value="${admin.username}" />
									</div>
								</div>

								<div class="control-group">
									<label class="control-label">入职日期&nbsp;:</label>
									<div class="controls">
										<input type="text" class="span11" id="add-input" readonly="readonly" value="${admin.createtime}" />
									</div>
								</div>

								<div class="form-actions">
									<%--<button type="reset" class="btn btn-warning add-btn">&nbsp;&nbsp;重&nbsp;&nbsp;&nbsp;&nbsp;置&nbsp;&nbsp;</button>--%>
									<button type="submit" class="btn btn-success add-btn"
										onclick="updateSta()">&nbsp;&nbsp;修&nbsp;&nbsp;&nbsp;&nbsp;改&nbsp;&nbsp;</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			</div>
		</div>
		<!--Footer-part-->
<div class="row-fluid">
  <div id="footer" class="span12"> 2016 &copy; Supermarket. By Xu Ge </div>
</div>
<!--end-Footer-part-->
		<script src="admin/js/jquery.min.js"></script>
		<script src="admin/js/jquery.ui.custom.js"></script>
		<script src="admin/js/bootstrap.min.js"></script>
		<script src="admin/js/bootstrap-colorpicker.js"></script>
		<script src="admin/js/bootstrap-datepicker.js"></script>
		<script src="admin/js/jquery.toggle.buttons.html"></script>
		<script src="admin/js/masked.js"></script>
		<script src="admin/js/jquery.uniform.js"></script>
		<script src="admin/js/select2.min.js"></script>
		<script src="admin/js/matrix.js"></script>
		<script src="admin/js/matrix.form_common.js"></script>
		<script src="admin/js/wysihtml5-0.3.0.js"></script>
		<script src="admin/js/jquery.peity.min.js"></script>
		<script src="admin/js/bootstrap-wysihtml5.js"></script>
		<script src="admin/js/form.js"></script>
		<script src="admin/js/moreselect.js"></script>
<script src="admin/js/authority.js"></script>
		<script>
			$('.textarea_editor').wysihtml5();
			first("selectp", "selectc", "form1", 0, 0);
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

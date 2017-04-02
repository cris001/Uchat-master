<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--<%@taglib prefix="s" uri="/struts-tags"%>--%>

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
<link rel="stylesheet" href="admin/css/jquery.gritter.css" />
<link rel="stylesheet" href="admin/css/uniform.css" />
<link rel="stylesheet" href="admin/css/select2.css" />
<link rel="stylesheet" href="admin/css/matrix-style.css" />
<link rel="stylesheet" href="admin/css/matrix-media.css" />
<link href="admin/font-awesome/css/font-awesome.css" rel="stylesheet" />
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

<div id="content">
  <div id="content-header">
    <div id="breadcrumb"><a href="admin/index.jsp" title="前往首页" class="tip-bottom"><i class="icon-home"></i>首页</a><a href="/ChartServlet?type=selectday" class="current">日报表</a></div>
    <h1>日出勤情况</h1>
  </div>
  <div class="container-fluid">
  <hr>
    <div class="row-fluid">
      <div class="span12">
      	
      	<div class="widget-box">
          <div class="widget-title"> <span class="icon"> <i class="icon-signal"></i> </span>
            <h5>日出勤</h5>
          </div>
          <div class="widget-content">
            <div id="placeholder"></div>
            <p id="choices"></p>
          </div>
        </div>
      	
        <div class="widget-box">
          <div class="widget-title"> <span class="icon"><i class="icon-th"></i></span>
            <h5>日出勤报表</h5>
          </div>
          <div class="widget-content nopadding">
            <table class="table table-bordered data-table">
              <thead>
                <tr>
                  <th>序号</th>
                  <th>日期</th>
                  <th>签到</th>
                </tr>
              </thead>
              <tbody>
				<c:forEach items="${chartList}" var="chart" varStatus="stat">
					<tr class="gradeX">
						<td>${stat.index+1}</td>
						<td>${chart.date}</td>
						<td>${chart.yon}</td>
                </tr>
				</c:forEach>
              </tbody>
            </table>
          </div>
        </div>
        <a class="btn btn-info pull-right" href="#" target=main onclick ="javascript:history.go(-1);">确认</a> 
      </div>
    </div>
  </div>
</div>

<%--<a href="/ChartServlet?type=add">签到</a>--%>

<c:forEach items="${chartJanList}" var="chartJan" varStatus="stata">
	<input type="hidden" value="${chartJan.date}" id="dateJan${stata.index}">
	<input type="hidden" value="${chartJan.yon}" id="yonJan${stata.index}">
</c:forEach>
<c:forEach items="${chartFebList}" var="chartFeb" varStatus="stata">
	<input type="hidden" value="${chartFeb.date}" id="dateFeb${stata.index}">
	<input type="hidden" value="${chartFeb.yon}" id="yonFeb${stata.index}">
</c:forEach>
<c:forEach items="${chartMarList}" var="chartMar" varStatus="stata">
	<input type="hidden" value="${chartMar.date}" id="dateMar${stata.index}">
	<input type="hidden" value="${chartMar.yon}" id="yonMar${stata.index}">
</c:forEach>
<c:forEach items="${chartAprList}" var="chartApr" varStatus="stata">
	<input type="hidden" value="${chartApr.date}" id="dateApr${stata.index}">
	<input type="hidden" value="${chartApr.yon}" id="yonApr${stata.index}">
</c:forEach>
<c:forEach items="${chartMayList}" var="chartMay" varStatus="stata">
	<input type="hidden" value="${chartMay.date}" id="dateMay${stata.index}">
	<input type="hidden" value="${chartMay.yon}" id="yonMay${stata.index}">
</c:forEach>
<c:forEach items="${chartJuneList}" var="chartJune" varStatus="stata">
	<input type="hidden" value="${chartJune.date}" id="dateJune${stata.index}">
	<input type="hidden" value="${chartJune.yon}" id="yonJune${stata.index}">
</c:forEach>
<c:forEach items="${chartJulyList}" var="chartJuly" varStatus="stata">
	<input type="hidden" value="${chartJuly.date}" id="dateJuly${stata.index}">
	<input type="hidden" value="${chartJuly.yon}" id="yonJuly${stata.index}">
</c:forEach>
<c:forEach items="${chartAugList}" var="chartAug" varStatus="stata">
	<input type="hidden" value="${chartAug.date}" id="dateAug${stata.index}">
	<input type="hidden" value="${chartAug.yon}" id="yonAug${stata.index}">
</c:forEach>
<c:forEach items="${chartSeptList}" var="chartJan" varStatus="stata">
	<input type="hidden" value="${chartSept.date}" id="dateSept${stata.index}">
	<input type="hidden" value="${chartSept.yon}" id="yonSept${stata.index}">
</c:forEach>
<c:forEach items="${chartOctList}" var="chartOct" varStatus="stata">
	<input type="hidden" value="${chartOct.date}" id="dateOct${stata.index}">
	<input type="hidden" value="${chartOct.yon}" id="yonOct${stata.index}">
</c:forEach>
<c:forEach items="${chartNovList}" var="chartNov" varStatus="stata">
	<input type="hidden" value="${chartNov.date}" id="dateNov${stata.index}">
	<input type="hidden" value="${chartNov.yon}" id="yonNov${stata.index}">
</c:forEach>
<c:forEach items="${chartDecList}" var="chartDec" varStatus="stata">
	<input type="hidden" value="${chartDec.date}" id="dateDec${stata.index}">
	<input type="hidden" value="${chartDec.yon}" id="yonDec${stata.index}">
</c:forEach>

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
<script src="admin/js/jquery.peity.min.js"></script>
<script src="admin/js/jquery.flot.min.js"></script>
<script src="admin/js/jquery.flot.resize.min.js"></script> 
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



    var dateJan = [];
    var dateFeb = [];
    var dateMar = [];
    var dateApr = [];
    var dateMay = [];
    var dateJune = [];
    var dateJuly = [];
    var dateAug = [];
    var dateSept = [];
    var dateOct = [];
    var dateNov = [];
    var dateDec = [];

	var j = 0;
	for(var i=0; i<31; i++){
        dateJan[i] = 0;
        dateFeb[i] = 0;
        dateMar[i] = 0;
        dateApr[i] = 0;
        dateMay[i] = 0;
        dateJune[i] = 0;
        dateJuly[i] = 0;
        dateAug[i] = 0;
        dateSept[i] = 0;
        dateOct[i] = 0;
        dateNov[i] = 0;
        dateDec[i] = 0;
	}

	while($("#dateJan"+j).val()){
	 	var index = $("#dateJan"+j).val().lastIndexOf ("-");
		var dindex = $("#dateJan"+j).val().substring(index+1);
        dateJan[dindex]=$("#yonJan"+j).val();
		j++;
	}

	j=0;
	while($("#dateFeb"+j).val()){
	 	var indexLast = $("#dateFeb"+j).val().lastIndexOf ("-");
		var dindexLast = $("#dateFeb"+j).val().substring(indexLast+1);
        dateFeb[dindexLast]=$("#yonFeb"+j).val();
		j++;
	}

    j=0;
    while($("#dateMar"+j).val()){
        var indexLast = $("#dateMar"+j).val().lastIndexOf ("-");
        var dindexLast = $("#dateMar"+j).val().substring(indexLast+1);
        dateMar[dindexLast]=$("#yonMar"+j).val();
        j++;
    }

    j=0;
    while($("#dateApr"+j).val()){
        var indexLast = $("#dateApr"+j).val().lastIndexOf ("-");
        var dindexLast = $("#dateApr"+j).val().substring(indexLast+1);
        dateApr[dindexLast]=$("#yonApr"+j).val();
        j++;
    }

    j=0;
    while($("#dateMay"+j).val()){
        var indexLast = $("#dateMay"+j).val().lastIndexOf ("-");
        var dindexLast = $("#dateMay"+j).val().substring(indexLast+1);
        dateMay[dindexLast]=$("#yonMay"+j).val();
        j++;
    }

    j=0;
    while($("#dateJune"+j).val()){
        var indexLast = $("#dateJune"+j).val().lastIndexOf ("-");
        var dindexLast = $("#dateJune"+j).val().substring(indexLast+1);
        dateJune[dindexLast]=$("#yonJune"+j).val();
        j++;
    }

    j=0;
    while($("#dateJuly"+j).val()){
        var indexLast = $("#dateJuly"+j).val().lastIndexOf ("-");
        var dindexLast = $("#dateJuly"+j).val().substring(indexLast+1);
        dateJuly[dindexLast]=$("#yonJuly"+j).val();
        j++;
    }

    j=0;
    while($("#dateAug"+j).val()){
        var indexLast = $("#dateAug"+j).val().lastIndexOf ("-");
        var dindexLast = $("#dateAug"+j).val().substring(indexLast+1);
        dateAug[dindexLast]=$("#yonAug"+j).val();
        j++;
    }

    j=0;
    while($("#dateSept"+j).val()){
        var indexLast = $("#dateSept"+j).val().lastIndexOf ("-");
        var dindexLast = $("#dateSept"+j).val().substring(indexLast+1);
        dateSept[dindexLast]=$("#yonSept"+j).val();
        j++;
    }

    j=0;
    while($("#dateOct"+j).val()){
        var indexLast = $("#dateOct"+j).val().lastIndexOf ("-");
        var dindexLast = $("#dateOct"+j).val().substring(indexLast+2);
        dateOct[dindexLast]=$("#yonOct"+j).val();
        j++;
    }

    j=0;
    while($("#dateNov"+j).val()){
        var indexLast = $("#dateNov"+j).val().lastIndexOf ("-");
        var dindexLast = $("#dateNov"+j).val().substring(indexLast+2);
        dateNov[dindexLast]=$("#yonNov"+j).val();
        j++;
    }

    j=0;
    while($("#dateDec"+j).val()){
        var indexLast = $("#dateDec"+j).val().lastIndexOf ("-");
        var dindexLast = $("#dateDec"+j).val().substring(indexLast+2);
        dateDec[dindexLast]=$("#yonDec"+j).val();
        j++;
    }
    var datasets = {
         "一月": {
             label: "一月",
             data: [[1, dateJan[0]], [2, dateJan[1]], [3, dateJan[2]], [4, dateJan[3]], [5, dateJan[4]], [6, dateJan[5]], [7, dateJan[6]], [8, dateJan[7]], [9, dateJan[8]], [10, dateJan[9]], [11, dateJan[10]], [12, dateJan[11]], [13, dateJan[12]], [14, dateJan[13]], [15, dateJan[14]], [16, dateJan[15]], [17, dateJan[16]], [18, dateJan[17]], [19, dateJan[18]], [20, dateJan[19]], [21, dateJan[20]], [22, dateJan[21]], [23, dateJan[22]], [24, dateJan[23]], [25, dateJan[24]], [26, dateJan[25]], [27, dateJan[26]], [28, dateJan[27]], [29, dateJan[28]], [30, dateJan[29]], [31, dateJan[30]]]
         },

        "二月": {
            label: "二月",
          data: [[1, dateFeb[0]], [2, dateFeb[1]], [3, dateFeb[2]], [4, dateFeb[3]], [5, dateFeb[4]], [6, dateFeb[5]], [7, dateFeb[6]], [8, dateFeb[7]], [9, dateFeb[8]], [10, dateFeb[9]], [11, dateFeb[10]], [12, dateFeb[11]], [13, dateFeb[12]], [14, dateFeb[13]], [15, dateFeb[14]], [16, dateFeb[15]], [17, dateFeb[16]], [18, dateFeb[17]], [19, dateFeb[18]], [20, dateFeb[19]], [21, dateFeb[20]], [22, dateFeb[21]], [23, dateFeb[22]], [24, dateFeb[23]], [25, dateFeb[24]], [26, dateFeb[25]], [27, dateFeb[26]], [28, dateFeb[27]], [29, dateFeb[28]], [30, dateFeb[29]], [31, dateFeb[30]]]
        },

        "三月": {
            label: "三月",
            data: [[1, dateMar[0]], [2, dateMar[1]], [3, dateMar[2]], [4, dateMar[3]], [5, dateMar[4]], [6, dateMar[5]], [7, dateMar[6]], [8, dateMar[7]], [9, dateMar[8]], [10, dateMar[9]], [11, dateMar[10]], [12, dateMar[11]], [13, dateMar[12]], [14, dateMar[13]], [15, dateMar[14]], [16, dateMar[15]], [17, dateMar[16]], [18, dateMar[17]], [19, dateMar[18]], [20, dateMar[19]], [21, dateMar[20]], [22, dateMar[21]], [23, dateMar[22]], [24, dateMar[23]], [25, dateMar[24]], [26, dateMar[25]], [27, dateMar[26]], [28, dateMar[27]], [29, dateMar[28]], [30, dateMar[29]], [31, dateMar[30]]]
        },

        "四月": {
            label: "四月",
            data: [[1, dateApr[0]], [2, dateApr[1]], [3, dateApr[2]], [4, dateApr[3]], [5, dateApr[4]], [6, dateApr[5]], [7, dateApr[6]], [8, dateApr[7]], [9, dateApr[8]], [10, dateApr[9]], [11, dateApr[10]], [12, dateApr[11]], [13, dateApr[12]], [14, dateApr[13]], [15, dateApr[14]], [16, dateApr[15]], [17, dateApr[16]], [18, dateApr[17]], [19, dateApr[18]], [20, dateApr[19]], [21, dateApr[20]], [22, dateApr[21]], [23, dateApr[22]], [24, dateApr[23]], [25, dateApr[24]], [26, dateApr[25]], [27, dateApr[26]], [28, dateApr[27]], [29, dateApr[28]], [30, dateApr[29]], [31, dateApr[30]]]
        },

        "五月": {
            label: "五月",
            data: [[1, dateMay[0]], [2, dateMay[1]], [3, dateMay[2]], [4, dateMay[3]], [5, dateMay[4]], [6, dateMay[5]], [7, dateMay[6]], [8, dateMay[7]], [9, dateMay[8]], [10, dateMay[9]], [11, dateMay[10]], [12, dateMay[11]], [13, dateMay[12]], [14, dateMay[13]], [15, dateMay[14]], [16, dateMay[15]], [17, dateMay[16]], [18, dateMay[17]], [19, dateMay[18]], [20, dateMay[19]], [21, dateMay[20]], [22, dateMay[21]], [23, dateMay[22]], [24, dateMay[23]], [25, dateMay[24]], [26, dateMay[25]], [27, dateMay[26]], [28, dateMay[27]], [29, dateMay[28]], [30, dateMay[29]], [31, dateMay[30]]]
        },

        "六月": {
            label: "六月",
            data: [[1, dateJune[0]], [2, dateJune[1]], [3, dateJune[2]], [4, dateJune[3]], [5, dateJune[4]], [6, dateJune[5]], [7, dateJune[6]], [8, dateJune[7]], [9, dateJune[8]], [10, dateJune[9]], [11, dateJune[10]], [12, dateJune[11]], [13, dateJune[12]], [14, dateJune[13]], [15, dateJune[14]], [16, dateJune[15]], [17, dateJune[16]], [18, dateJune[17]], [19, dateJune[18]], [20, dateJune[19]], [21, dateJune[20]], [22, dateJune[21]], [23, dateJune[22]], [24, dateJune[23]], [25, dateJune[24]], [26, dateJune[25]], [27, dateJune[26]], [28, dateJune[27]], [29, dateJune[28]], [30, dateJune[29]], [31, dateJune[30]]]
        },

        "七月": {
            label: "七月",
            data: [[1, dateJuly[0]], [2, dateJuly[1]], [3, dateJuly[2]], [4, dateJuly[3]], [5, dateJuly[4]], [6, dateJuly[5]], [7, dateJuly[6]], [8, dateJuly[7]], [9, dateJuly[8]], [10, dateJuly[9]], [11, dateJuly[10]], [12, dateJuly[11]], [13, dateJuly[12]], [14, dateJuly[13]], [15, dateJuly[14]], [16, dateJuly[15]], [17, dateJuly[16]], [18, dateJuly[17]], [19, dateJuly[18]], [20, dateJuly[19]], [21, dateJuly[20]], [22, dateJuly[21]], [23, dateJuly[22]], [24, dateJuly[23]], [25, dateJuly[24]], [26, dateJuly[25]], [27, dateJuly[26]], [28, dateJuly[27]], [29, dateJuly[28]], [30, dateJuly[29]], [31, dateJuly[30]]]
        },

        "八月": {
            label: "八月",
            data: [[1, dateAug[0]], [2, dateAug[1]], [3, dateAug[2]], [4, dateAug[3]], [5, dateAug[4]], [6, dateAug[5]], [7, dateAug[6]], [8, dateAug[7]], [9, dateAug[8]], [10, dateAug[9]], [11, dateAug[10]], [12, dateAug[11]], [13, dateAug[12]], [14, dateAug[13]], [15, dateAug[14]], [16, dateAug[15]], [17, dateAug[16]], [18, dateAug[17]], [19, dateAug[18]], [20, dateAug[19]], [21, dateAug[20]], [22, dateAug[21]], [23, dateAug[22]], [24, dateAug[23]], [25, dateAug[24]], [26, dateAug[25]], [27, dateAug[26]], [28, dateAug[27]], [29, dateAug[28]], [30, dateAug[29]], [31, dateAug[30]]]
        },

        "九月": {
            label: "九月",
            data: [[1, dateSept[0]], [2, dateSept[1]], [3, dateSept[2]], [4, dateSept[3]], [5, dateSept[4]], [6, dateSept[5]], [7, dateSept[6]], [8, dateSept[7]], [9, dateSept[8]], [10, dateSept[9]], [11, dateSept[10]], [12, dateSept[11]], [13, dateSept[12]], [14, dateSept[13]], [15, dateSept[14]], [16, dateSept[15]], [17, dateSept[16]], [18, dateSept[17]], [19, dateSept[18]], [20, dateSept[19]], [21, dateSept[20]], [22, dateSept[21]], [23, dateSept[22]], [24, dateSept[23]], [25, dateSept[24]], [26, dateSept[25]], [27, dateSept[26]], [28, dateSept[27]], [29, dateSept[28]], [30, dateSept[29]], [31, dateSept[30]]]
        },

        "十月": {
            label: "十月",
            data: [[1, dateOct[0]], [2, dateOct[1]], [3, dateOct[2]], [4, dateOct[3]], [5, dateOct[4]], [6, dateOct[5]], [7, dateOct[6]], [8, dateOct[7]], [9, dateOct[8]], [10, dateOct[9]], [11, dateOct[10]], [12, dateOct[11]], [13, dateOct[12]], [14, dateOct[13]], [15, dateOct[14]], [16, dateOct[15]], [17, dateOct[16]], [18, dateOct[17]], [19, dateOct[18]], [20, dateOct[19]], [21, dateOct[20]], [22, dateOct[21]], [23, dateOct[22]], [24, dateOct[23]], [25, dateOct[24]], [26, dateOct[25]], [27, dateOct[26]], [28, dateOct[27]], [29, dateOct[28]], [30, dateOct[29]], [31, dateOct[30]]]
        },

        "十一月": {
            label: "十一月",
            data: [[1, dateNov[0]], [2, dateNov[1]], [3, dateNov[2]], [4, dateNov[3]], [5, dateNov[4]], [6, dateNov[5]], [7, dateNov[6]], [8, dateNov[7]], [9, dateNov[8]], [10, dateNov[9]], [11, dateNov[10]], [12, dateNov[11]], [13, dateNov[12]], [14, dateNov[13]], [15, dateNov[14]], [16, dateNov[15]], [17, dateNov[16]], [18, dateNov[17]], [19, dateNov[18]], [20, dateNov[19]], [21, dateNov[20]], [22, dateNov[21]], [23, dateNov[22]], [24, dateNov[23]], [25, dateNov[24]], [26, dateNov[25]], [27, dateNov[26]], [28, dateNov[27]], [29, dateNov[28]], [30, dateNov[29]], [31, dateNov[30]]]
        },

        "十二月": {
            label: "十二月",
            data: [[1, dateDec[0]], [2, dateDec[1]], [3, dateDec[2]], [4, dateDec[3]], [5, dateDec[4]], [6, dateDec[5]], [7, dateDec[6]], [8, dateDec[7]], [9, dateDec[8]], [10, dateDec[9]], [11, dateDec[10]], [12, dateDec[11]], [13, dateDec[12]], [14, dateDec[13]], [15, dateDec[14]], [16, dateDec[15]], [17, dateDec[16]], [18, dateDec[17]], [19, dateDec[18]], [20, dateDec[19]], [21, dateDec[20]], [22, dateDec[21]], [23, dateDec[22]], [24, dateDec[23]], [25, dateDec[24]], [26, dateDec[25]], [27, dateDec[26]], [28, dateDec[27]], [29, dateDec[28]], [30, dateDec[29]], [31, dateDec[30]]]
        }
        
        
    };

    // hard-code color indices to prevent them from shifting as
    // countries are turned on/off
    var i = 0;
    $.each(datasets, function(key, val) {
        val.color = i;
        ++i;
    });
    
    // insert checkboxes 
    var choiceContainer = $("#choices");
    $.each(datasets, function(key, val) {
        choiceContainer.append('<br/><input type="checkbox" name="' + key +
                               '" checked="checked" id="id' + key + '">' +
                               '<label for="id' + key + '">'
                                + val.label + '</label>');
    });
    choiceContainer.find("input").click(plotAccordingToChoices);

    
    function plotAccordingToChoices() {
        var data = [];

        choiceContainer.find("input:checked").each(function () {
            var key = $(this).attr("name");
            if (key && datasets[key])
                data.push(datasets[key]);
        });

        if (data.length > 0)
            $.plot($("#placeholder"), data, {
                yaxis: { min: 0 },
                xaxis: { tickDecimals: 0 }
            });
    }

    plotAccordingToChoices();
    
}); 
</script> 
<script src="admin/js/matrix.dashboard.js"></script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>Matrix Admin</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <base href="<%=basePath%>">

    <link rel="stylesheet" href="admin/css/uniform.css" />
    <link rel="stylesheet" href="admin/css/select2.css" />
    <link rel="stylesheet" href="admin/css/matrix-style.css" />
    <link rel="stylesheet" href="admin/css/matrix-media.css" />
    <link rel="stylesheet" href="admin/css/bootstrap.min.css" />
    <link rel="stylesheet" href="admin/css/bootstrap-responsive.min.css" />
    <link rel="stylesheet" href="admin/css/matrix-login.css" />
    <link rel="stylesheet" href="admin/css/styles.css" />
    <link href="admin/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <!--<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>-->
</head>
<body>
<div id="loginbox">
    <form id="loginform" class="form-vertical" action="LoginServlet" method="post">
        <div class="control-group normal_text"> <h3><img src="admin/img/logo2.png" alt="Logo" /></h3></div>
        <div class="control-group">
            <div class="controls">
                <div class="main_input_box">
                    <span class="add-on bg_lg"><i class="icon-user"></i></span><input type="text" pattern="[0-9]{6}" placeholder="工号" name="loginname"/>
                </div>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">
                <div class="main_input_box">
                    <span class="add-on bg_ly"><i class="icon-lock"></i></span><input type="password" placeholder="密码" name="loginpsw"/>
                </div>
            </div>
        </div>
        <div class="form-actions">
            <span class="pull-left"><button type="reset" class="flip-link btn btn-info" id="to-recover">&nbsp;修改密码&nbsp;</button></span>
            <span class="pull-right"><button type="submit" class="btn btn-success" > &nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;</button></span>
        </div>
    </form>
    <form id="recoverform" action="AdminServlet?type=update_login" method="post" class="form-vertical" onsubmit="return checkPasswords()" name="form1">
        <p class="normal_text"><font size="+2"><strong>修改密码</strong></font></p>

        <div class="controls">
            <div class="main_input_box">
                <span class="add-on bg_lg"><i class="icon-user"></i></span>

                <input required="required" type="text" placeholder="工号" name="loginname" />
                <br/>
                <br/>
                <span class="add-on bg_ly"><i class="icon-key"></i></span><input required="required" type="password" placeholder="原始密码" name="loginpsw"/>
                <br/>
                <br/>
                <span class="add-on bg_lo"><i class="icon-unlock"></i></span><input required="required" type="password" placeholder="新密码" name="newloginpsw" id="password1"/>
                <br/>
                <br/>
                <span class="add-on bg_lb"><i class="icon-ok"></i></span><input required="required" type="password" placeholder="确认密码" id="password2" />
                <br/>
                <br/>
            </div>
        </div>

        <div class="form-actions">
            <span class="pull-left"><a href="admin/login.jsp" class="flip-link btn btn-success" id="to-login">&laquo; 返回登录</a></span>
            <span class="pull-right"><button class="btn btn-info" type="submit">&nbsp;修&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;改&nbsp;</button></span>
        </div>
    </form>
</div>

<script src="admin/js/jquery.min.js"></script>
<script src="admin/js/matrix.login.js"></script>
<script src="admin/js/form.js"></script>
<script src="admin/js/jquery.ui.custom.js"></script>
<script src="admin/js/bootstrap.min.js"></script>
<script src="admin/js/matrix.js"></script>
<script>
    function checkPasswords() {
        var pass1 = document.getElementById("password1");
        var pass2 = document.getElementById("password2");
        if (pass1.value != pass2.value){
            alert("两次输入的密码不匹配");
//            pass2.setCustomValidity();
            return false;
        }
        else
            pass1.setCustomValidity("");
    }

</script>
</body>

</html>

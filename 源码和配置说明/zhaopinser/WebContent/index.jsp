<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
    
<head>
    <title>网上人才招聘系统</title><meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<link rel="stylesheet" href="css/bootstrap.min.css" />
	<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
    <link rel="stylesheet" href="css/matrix-login.css" />
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
    <style type="text/css">
        input{
            font-family: "Microsoft Yahei";
        }
        .control-label{
            color: #B2DFEE;
            padding-left: 4px;
        }
    </style>
</head>
<body onkeydown="keydown()">
    <div id="loginbox">  
        <div class="control-group normal_text"> 
            <h2 style="color:#B2DFEE;font-size:28px;">网上人才招聘系统</h2>
            <span style="font-size:14px;color:gray;">版权所有 &copy; 网上人才招聘系统</span>
        </div>          
        <form id="loginform" class="form-vertical" action="login" method="post" >
            <div class="control-group">
                <label class="control-label">登陆账号</label>
                <div class="controls">
                    <div class="main_input_box">
                        <span class="add-on bg_lg"><i class="icon-user" style="font-size:16px;"></i></span><input type="text" value="${userName }" name="userName" id="userName"/>
                    </div>
                </div>
            </div>
            <div class="control-group2">
                <label class="control-label">登陆密码</label>
                <div class="controls">
                    <div class="main_input_box">
                        <span class="add-on bg_ly"><i class="icon-lock" style="font-size:16px;"></i></span><input type="password" value="${password }" name="password" id="password"/>
                    </div>
                </div>
            </div>
            <div class="form-actions">
            	<span class="pull-left"><a href="zhuce.jsp" class="flip-link btn btn-info" id="to-recover">注册</a></span>
                <span class="pull-right"><input type="submit" class="btn btn-success" style="width:335px;" value=" 登&nbsp;&nbsp;&nbsp;&nbsp;录"/></span>
            </div>
            <div class="control-group normal_text">
            	<li><font color="red">${error }</font></li>
                <div style="font-size:14px;color:gray;">推荐使用360及火狐浏览器</div>
            </div>
        </form>

    </div>
    
    <script src="js/jquery.min.js"></script>  
    <script src="js/matrix.login.js"></script> 
</body>

</html>
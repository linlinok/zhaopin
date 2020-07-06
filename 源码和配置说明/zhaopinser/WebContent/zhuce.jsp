<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
<title>网上人才招聘系统</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/colorpicker.css" />
<link rel="stylesheet" href="css/datepicker.css" />
<link rel="stylesheet" href="css/uniform.css" />
<link rel="stylesheet" href="css/select2.css" />
<link rel="stylesheet" href="css/matrix-style2.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link rel="stylesheet" href="css/bootstrap-wysihtml5.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800'
	rel='stylesheet' type='text/css'>

<style>
.control-group .controls label {
	display: inline-block;
}
</style>
</head>
<body>

	<div id="content">
		<div id="content-header">
			<h1>网上人才招聘系统</h1>
		</div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span6">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"> <i class="icon-align-justify"></i>
							</span>
							<h5>用户注册</h5>
						</div>
						<div class="widget-content nopadding">
							<form name="form1" action="userZhuce" method="post"
								class="form-horizontal" onSubmit="return check()">
								<div class="control-group">
									<label class="control-label">登录名:</label>
									<div class="controls">
										<input type="text" class="span11" placeholder="登录名"
											name="userName" id="userName" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">密码:</label>
									<div class="controls">
										<input type="password" class="span11" placeholder="密码"
											name="userPassword" id="userPassword" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">密码:</label>
									<div class="controls">
										<input type="password" class="span11" placeholder="重复密码"
											name="userPassword1" id="userPassword1" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">名称</label>
									<div class="controls">
										<input type="text" class="span11" placeholder="名称"
											name="userXingming" id="userXingming" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">年龄</label>
									<div class="controls">
										<input type="text" class="span11" placeholder="年龄"
											name="userAge" id="userAge" />
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">性别</label>
									<div class="controls">
										<select id="userSex" name="userSex" class="span11">
											<option value="男">男</option>
											<option value="女">女</option>
										</select>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label">选择角色</label>
									<div class="controls">
										<input class="easyui-combobox" id="roleId" name="roleId" size="10" data-options="panelHeight:'auto',editable:false,valueField:'roleId',textField:'roleName',url:'roleComboList'"/>
									</div>
								</div>
								<div class="form-actions">
									<button type="submit" class="btn btn-success">注册</button>
								</div>
								<li><font color="red">${error }</font></li>
							</form>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function check() {
			var userName = document.form1.userName.value;
			var userPassword = document.form1.userPassword.value;
			var userPassword1 = document.form1.userPassword1.value;
			var userXingming = document.form1.userXingming.value;
			var userAge = document.form1.userAge.value;
			var reg = /^[a-zA-Z0-9_]{6,}$/;
			if (!reg.test(userName)) {
				document.form1.userName.focus();
				alert("用户名不符合规则，6位以上");
				return false;
			}
			if (!reg.test(userPassword)) {
				document.form1.userPassword.focus();
				alert("密码不符合规则，6位以上");
				return false;
			}

			if (document.form1.userPassword1.value == "") {
				alert("请输入确认密码");
				document.form1.userPassword1.focus();
				return false;
			}
			if (document.form1.userPassword.value != document.form1.userPassword1.value) {
				alert("两次输入密码不一致");
				document.form1.userPassword1.focus();
				return false;
			}
			if (document.form1.userXingming.value == "") {
				alert("姓名为必填");
				document.form1.userXingming.focus();
				return false;
			}
			
			//定义正则表达式部分    
			var reg1 = /^[0-9]{0,}$/;
			var reg2 = /^[1-9]{1}[0-9]{0,}$/;
			//alert(numberValue);
			if(userAge ==null || userAge.length==0 || userAge.length>2){
				alert('请输入有效的年龄！');
				document.form1.userAge.focus();
				return false;
			}
			if((!reg1.test(userAge))||(!reg2.test(userAge))){
				alert('请输入有效的年龄！');
				document.form1.userAge.focus();
				return false;
			}
		}
	</script>
</body>
</html>
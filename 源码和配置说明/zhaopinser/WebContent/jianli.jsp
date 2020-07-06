<%@ page language="java" contentType="text/html; charset=utf-8"
	import="com.model.Jianli" pageEncoding="utf-8"%>

<%
	// 权限验证
	Jianli jianli = (Jianli) session.getAttribute("jianli");
	if (jianli != null) {
		System.out.println(jianli.getJineng());
	} else {
		System.out.println("简历为空");
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户信息管理</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/matrix-style2.css" />
<link rel="stylesheet" href="css/matrix-media.css" />
<link href="font-awesome/css/font-awesome.css" rel="stylesheet" />
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800'
	rel='stylesheet' type='text/css'>
</head>
<body>

	<div id="content">
		<div id="content-header">
			<h1>简历详情</h1>
		</div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"> <i class="icon-briefcase"></i>
							</span>
							<h5>简历</h5>
						</div>
						<div class="widget-content">
							<div class="row-fluid">
								<div class="span6">
									<table class="table table-bordered table-invoice">
										<tbody>
										<%
											if (jianli != null) {
										%>
											<tr>
											<tr>
												<td class="width30">学校：</td>
												<td class="width70"><strong><%=jianli.getXuexiao()%></strong></td>
											</tr>
											<tr>
												<td>专业：</td>
												<td><strong><%=jianli.getZhuanye()%></strong></td>
											</tr>
											<tr>
												<td>技能：</td>
												<td><strong><%=jianli.getJineng()%></strong></td>
											</tr>
											<td class="width30">详细信息：</td>
											<td class="width70"><strong><%=jianli.getXiangxi()%></strong></td>
											</tr>
										<%
											}else{
										%>	
											<tr>
												<td>用户未填写简历</td>
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
				</div>
			</div>
		</div>
	</div>

	<script src="js/jquery.min.js"></script>
	<script src="js/jquery.ui.custom.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/matrix.js"></script>
</body>
</html>

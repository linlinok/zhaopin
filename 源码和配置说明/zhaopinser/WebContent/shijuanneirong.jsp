<%@page import="java.util.List,com.model.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	import="com.model.Shiti" pageEncoding="utf-8"%>

<%
	// 权限验证
	List shitis = (List) session.getAttribute("shitis");
	User user = (User) session.getAttribute("user");
	int userId = user.getUserId();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>试题信息管理</title>
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
			<h1>试题详情</h1>
		</div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"> <i class="icon-briefcase"></i>
							</span>
							<h5>试题</h5>
						</div>
						<div class="widget-content">
							<div class="row-fluid">
								<div class="span6">
									<table class="table table-bordered table-invoice">
										<tbody>
										<%
											if (shitis.size()!=0) {
										%>
										<form name="form1" action="datiSave?userId=<%=userId %>" method="post">
										<%
												for(int i=0;i<shitis.size();i++){
													Shiti shiti =(Shiti)shitis.get(i);
										%>											
											<tr>
												<td class="width30"><%=shiti.getShitiName() %></td>
												<td class="width170"><strong>
												
												<label><input name="userDaan[<%=i %>]" type="radio" value="A" /><%=shiti.getShitiD1() %></label>
												<label><input name="userDaan[<%=i %>]" type="radio" value="B" /><%=shiti.getShitiD2() %> </label>
												<label><input name="userDaan[<%=i %>]" type="radio" value="C" /><%=shiti.getShitiD3() %> </label>
												<label><input name="userDaan[<%=i %>]" type="radio" value="D" /><%=shiti.getShitiD4() %> </label>
												
												</strong></td>
											</tr>
											
										<%
												}%>
											<tr>
												<td class="width30">
												<button type="submit">提交</button>
												</td>
												<td class="width170"><strong></strong></td>
											</tr>
										</form>
										<%
											}else{
										%>	
											<tr>
												<td>该职位没有试题</td>
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

<%@ page language="java" contentType="text/html; charset=utf-8"  import="com.model.User"  pageEncoding="utf-8"%>
   

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户信息管理</title>
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;
	
	function searchUser(){
		$('#dg').datagrid('load',{
			departmentId : $('#s_departmentId').combobox("getValue"),
		});
	}
	
	function deleteUser(){
		var selectedRows=$("#dg").datagrid('getSelections');
		//$.messager.alert("selectedRows.length=" + selectedRows.length);
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var dianyingTpyeIds=[];
		for(var i=0;i<selectedRows.length;i++){
			dianyingTpyeIds.push(selectedRows[i].userId);
		}
		var ids=dianyingTpyeIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("../userDelete",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].userName+'</font>'+result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	
	function openUserAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加用户信息");
		url="../userSave";
	}
	
	function openUserModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑用户信息");
		$("#fm").form("load",row);
		url="../userSave?userId="+row.userId;
	}
	
	function closeUserDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function resetValue(){
	}
	
	
	function saveUser(){
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
				if(result.errorMsg){
					$.messager.alert("系统提示",result.errorMsg);
					return;
				}else{
					$.messager.alert("系统提示","保存成功");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}
			}
		});
	}
	
	function formatPrice(userId, row) {  
	    return '<a style="color:red;"target="_blank" href="../jianliList?userId=' + userId + '">查看简历' + '</a>';  
	}
	
</script>
</head>
<body style="margin: 5px;">
	<table id="dg" title="用户信息" class="easyui-datagrid" fitColumns="true"
	 pagination="true" rownumbers="true" url="../userList?roleId=2" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="userId" width="50" formatter="formatPrice" >查看简历</th>
				<th field="userName" width="50">用户名</th>
				<th field="userPassword" width="50" hidden="true">密码</th>
				<th field="userXingming" width="50">姓名</th>
				<th field="userAge" width="50">年龄</th>
				<th field="userSex" width="20">性别</th>
				<th field="roleId" width="50" hidden="true">权限Id</th>
				<th field="roleName" width="50">权限</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openUserModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">查看简历</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width: 500px;height: 260px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr hidden="true">
					<td>用户名：</td>
					<td><input type="text" name="userName" id="userName" class="easyui-validatebox" required="true"/></td>
					<td></td>
					<td>密码：</td>
					<td><input type="password" name="userPassword" id="userPassword" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>姓名：</td>
					<td><input type="text" name="userXingming" id="userXingming" class="easyui-validatebox" required="true"/></td>
					<td></td>
					<td>权限：</td>
					<td><input class="easyui-combobox" id="roleId" name="roleId" size="10" data-options="panelHeight:'auto',editable:false,valueField:'roleId',textField:'roleName',url:'../roleComboList'"/></td>
				</tr>
				<tr>
					<td>年龄：</td>
					<td><input type="text" name="userAge" id="userAge" class="easyui-validatebox" required="true"/></td>
					<td></td>
					<td>性别：</td>
					<td><select class="easyui-combobox" id="userSex" name="userSex" editable="false" panelHeight="auto" style="width: 155px">
					    <option value="">请选择...</option>
						<option value="男">男</option>
						<option value="女">女</option>
					</select></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveUser()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeUserDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>
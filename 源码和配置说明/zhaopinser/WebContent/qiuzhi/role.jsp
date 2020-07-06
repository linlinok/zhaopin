<%@ page language="java" contentType="text/html; charset=utf-8" import="com.model.Admin;"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>权限信息管理</title>
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;
	
	function searchRole(){
		$('#dg').datagrid('load',{
			roleName:$('#s_roleName').val()
		});
	}
	
	function deleteRole(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].roleId);
		}
		var ids=strIds.join(",");
		//输出选择的行
		//$.messager.alert("ids:" + ids);
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("../roleDelete",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].roleName+'</font>'+result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	function openRoleAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加角色信息");
		url="../roleSave";
	}
	
	function saveRole(){
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
	
	function resetValue(){
		$("#roleName").val("");
		$("#roleMark").val("");
	}
	
	function closeRoleDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function openRoleModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑角色信息");
		$("#fm").form("load",row);
		url="../roleSave?roleId="+row.roleId;
	}
</script>
</head>
<body style="margin: 5px;">
	<table id="dg" title="权限信息" class="easyui-datagrid" fitColumns="true"
	 pagination="true" url="../roleList" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="roleId" width="50">编号</th>
				<th field="roleName" width="100">权限名称</th>
				<th field="roleMark" width="250">权限描述</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>&nbsp;部门名称：&nbsp;<input type="text" name="s_roleName" id="s_roleName"/><a href="javascript:searchRole()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width: 400px;height: 280px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td>角色名称：</td>
					<td><input type="text" name="roleName" id="roleName" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td valign="top">角色描述：</td>
					<td><textarea rows="7" cols="30" name="roleMark" id="roleMark"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveRole()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeRoleDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8" import="com.model.Admin;"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>试卷信息管理</title>
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;
	
	function searchShijuan(){
		$('#dg').datagrid('load',{
			shijuanName:$('#s_shijuanName').val()
		});
	}
	
	function deleteShijuan(){
		var selectedRows=$("#dg").datagrid('getSelections');
		//$.messager.alert("selectedRows.length=" + selectedRows.length);
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].shijuanId);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("../shijuanDelete",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].shijuanName+'</font>'+result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	
	function openShijuanAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加试卷信息");
		url="../shijuanSave";
	}
	
	function openShijuanModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑试卷信息");
		$("#fm").form("load",row);
		url="../shijuanSave?shijuanId="+row.shijuanId;
	}
	
	function closeShijuanDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function resetValue(){
		$("#shijuanName").val("");
		$("#shijuanNum").val("");
	}
	
	
	function saveShijuan(){
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
	
	
</script>
</head>
<body style="margin: 5px;">
	<table id="dg" title="试卷信息" class="easyui-datagrid" fitColumns="true"
	 pagination="true" rownumbers="true" url="../shijuanList" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="shijuanId" width="50">编号</th>
				<th field="shijuanName" width="100">名称</th>
				<th field="shijuanMark" width="250">备注</th>
				<th field="zhiweiId" width="50" hidden="true">职位Id</th>
				<th field="zhiweiName" width="50">职位</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openShijuanAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openShijuanModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteShijuan()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width: 500px;height: 160px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td>名称：</td>
					<td><input type="text" name="shijuanName" id="shijuanName" class="easyui-validatebox" required="true"/></td>
					<td></td>
					<td>职位：</td>
					<td><input class="easyui-combobox" id="zhiweiId" name="zhiweiId" size="10" data-options="panelHeight:'auto',editable:false,valueField:'zhiweiId',textField:'zhiweiName',url:'../zhiweiComboList'"/></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveShijuan()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeShijuanDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>
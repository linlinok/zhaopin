<%@ page language="java" contentType="text/html; charset=utf-8" import="com.model.Admin;"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>职位信息管理</title>
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;
	
	function searchZhiwei(){
		$('#dg').datagrid('load',{
			zhiweiName:$('#s_zhiweiName').val()
		});
	}
	
	function deleteZhiwei(){
		var selectedRows=$("#dg").datagrid('getSelections');
		//$.messager.alert("selectedRows.length=" + selectedRows.length);
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].zhiweiId);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("../zhiweiDelete",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].zhiweiName+'</font>'+result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	
	function openZhiweiAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加职位信息");
		url="../zhiweiSave";
	}
	
	function openZhiweiModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑职位信息");
		$("#fm").form("load",row);
		url="../zhiweiSave?zhiweiId="+row.zhiweiId;
	}
	
	function closeZhiweiDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function resetValue(){
		$("#zhiweiName").val("");
		$("#zhiweiNum").val("");
	}
	
	
	function saveZhiwei(){
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
	<table id="dg" title="职位信息" class="easyui-datagrid" fitColumns="true"
	 pagination="true" rownumbers="true" url="../zhiweiList" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="zhiweiId" width="50">编号</th>
				<th field="zhiweiName" width="100">名称</th>
				<th field="zhiweiMark" width="250">备注</th>
				<th field="userId" width="50" hidden="true">用户Id</th>
				<th field="userXingming" width="50">招聘人</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openZhiweiAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openZhiweiModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteZhiwei()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width: 800px;height: 300px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td>名称：</td>
					<td><input type="text" name="zhiweiName" id="zhiweiName" class="easyui-validatebox" required="true"/></td>
					<td></td>
					<td>备注：</td>
					<td><input type="text" name="zhiweiMark" id="zhiweiMark" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>招聘人：</td>
					<td><input class="easyui-combobox" id="userId" name="userId" size="10" data-options="panelHeight:'auto',editable:false,valueField:'userId',textField:'userXingming',url:'../userComboList'"/></td>
					<td></td>
					<td>职位编号：</td>
					<td><input type="text" name="zhiweiNum" id="zhiweiNum" class="easyui-validatebox" required="true"/></td>
					
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveZhiwei()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeZhiweiDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>
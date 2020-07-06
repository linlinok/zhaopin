<%@ page language="java" contentType="text/html; charset=utf-8" import="com.model.Admin;"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>试题信息管理</title>
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;
	
	function searchShiti(){
		$('#dg').datagrid('load',{
			shitiName:$('#s_shitiName').val()
		});
	}
	
	function deleteShiti(){
		var selectedRows=$("#dg").datagrid('getSelections');
		//$.messager.alert("selectedRows.length=" + selectedRows.length);
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].shitiId);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("../shitiDelete",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].shitiName+'</font>'+result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	
	function openShitiAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加试题信息");
		url="../shitiSave";
	}
	
	function openShitiModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑试题信息");
		$("#fm").form("load",row);
		url="../shitiSave?shitiId="+row.shitiId;
	}
	
	function closeShitiDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function resetValue(){
		$("#shitiName").val("");
		$("#shitiNum").val("");
	}
	
	
	function saveShiti(){
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
	<table id="dg" title="试题信息" class="easyui-datagrid" fitColumns="true"
	 pagination="true" rownumbers="true" url="../shitiList" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="shitiId" width="20">编号</th>
				<th field="shitiName" width="100">名称</th>
				<th field="shitiD1" width="50">答案1</th>
				<th field="shitiD2" width="50">答案2</th>
				<th field="shitiD3" width="50">答案3</th>
				<th field="shitiD4" width="50">答案4</th>
				<th field="shitiDaan" width="50">正确答案</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openShitiAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openShitiModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteShiti()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width: 500px;height: 200px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td>题目：</td>
					<td><input type="text" name="shitiName" id="shitiName" class="easyui-validatebox" required="true"/></td>
					<td></td>
					<td>正确答案：</td>
					<td><input type="text" name="shitiDaan" id="shitiDaan" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>答案1：</td>
					<td><input type="text" name="shitiD1" id="shitiD1" class="easyui-validatebox" required="true"/></td>
					<td></td>
					<td>答案2：</td>
					<td><input type="text" name="shitiD2" id="shitiD2" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>答案3：</td>
					<td><input type="text" name="shitiD3" id="shitiD3" class="easyui-validatebox" required="true"/></td>
					<td></td>
					<td>答案4：</td>
					<td><input type="text" name="shitiD4" id="shitiD4" class="easyui-validatebox" required="true"/></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveShiti()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeShitiDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>
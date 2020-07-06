<%@ page language="java" contentType="text/html; charset=utf-8" import="com.model.Admin;"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>生成试卷信息管理</title>
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;
	
	function searchJuanti(){
		$('#dg').datagrid('load',{
			juantiName:$('#s_juantiName').val()
		});
	}
	
	function deleteJuanti(){
		var selectedRows=$("#dg").datagrid('getSelections');
		//$.messager.alert("selectedRows.length=" + selectedRows.length);
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].juantiId);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("../juantiDelete",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].juantiName+'</font>'+result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	
	function openJuantiAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加生成试卷信息");
		url="../juantiSave";
	}
	
	function openJuantiModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑生成试卷信息");
		$("#fm").form("load",row);
		url="../juantiSave?juantiId="+row.juantiId;
	}
	
	function closeJuantiDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function resetValue(){
		$("#juantiName").val("");
		$("#juantiNum").val("");
	}
	
	
	function saveJuanti(){
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
	<table id="dg" title="生成试卷信息" class="easyui-datagrid" fitColumns="true"
	 pagination="true" rownumbers="true" url="../juantiList" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="juantiId" width="50">编号</th>
				<th field="shijuanId" width="50" hidden="true">试卷Id</th>
				<th field="shijuanName" width="50">试卷</th>
				<th field="shitiId" width="50" hidden="true">试题Id</th>
				<th field="shitiName" width="50">试题</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openJuantiAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openJuantiModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteJuanti()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width: 500px;height: 160px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<td>试卷：</td>
					<td><input class="easyui-combobox" id="shijuanId" name="shijuanId" size="10" data-options="panelHeight:'auto',editable:false,valueField:'shijuanId',textField:'shijuanName',url:'../shijuanComboList'"/></td>
					<td></td>
					<td>试题：</td>
					<td><input class="easyui-combobox" id="shitiId" name="shitiId" size="10" data-options="panelHeight:'auto',editable:false,valueField:'shitiId',textField:'shitiName',url:'../shitiComboList'"/></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveJuanti()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeJuantiDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>
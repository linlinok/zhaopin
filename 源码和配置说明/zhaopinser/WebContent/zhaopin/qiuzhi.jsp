<%@ page language="java" contentType="text/html; charset=utf-8" import="com.model.Admin;"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>求职信息管理</title>
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="../jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;
	
	function searchQiuzhi(){
		$('#dg').datagrid('load',{
			qiuzhiName:$('#s_qiuzhiName').val()
		});
	}
	
	function deleteQiuzhi(){
		var selectedRows=$("#dg").datagrid('getSelections');
		//$.messager.alert("selectedRows.length=" + selectedRows.length);
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].qiuzhiId);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("../qiuzhiDelete",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].qiuzhiName+'</font>'+result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	
	function openQiuzhiAddDialog(){
		$("#dlg").dialog("open").dialog("setTitle","添加求职信息");
		url="../qiuzhiSave";
	}
	
	function openQiuzhiModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑求职信息");
		$("#fm").form("load",row);
		url="../qiuzhiSave?qiuzhiId="+row.qiuzhiId;
	}
	
	function closeQiuzhiDialog(){
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function resetValue(){
		$("#qiuzhiName").val("");
		$("#qiuzhiNum").val("");
	}
	
	
	function saveQiuzhi(){
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
	
	function typetostr(qiuzhiType, row) {
		if(qiuzhiType==0){
			return "未发通知";
		}
		if(qiuzhiType==1){
			return "等待面试";
		}
		if(qiuzhiType==2){
			return "面试完成";
		}
	}
	
	
</script>
</head>
<body style="margin: 5px;">
	<table id="dg" title="求职信息" class="easyui-datagrid" fitColumns="true"
	 pagination="true" rownumbers="true" url="../qiuzhiList" fit="true" toolbar="#tb">
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="qiuzhiId" width="50">编号</th>
				<th field="zhiweiId" width="50" hidden="true">职位Id</th>
				<th field="zhiweiName" width="50">职位</th>
				<th field="userId" width="50" hidden="true">用户Id</th>
				<th field="userXingming" width="50">求职人</th>
				<th field="qiuzhiMark" width="250">备注</th>
				<th field="qiuzhiType" width="50" formatter="typetostr">状态</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openQiuzhiModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">通知</a>
		</div>
	</div>
	
	<div id="dlg" class="easyui-dialog" style="width: 350px;height: 120px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
			<table>
				<tr>
					<select class="easyui-combobox" id="qiuzhiType" name="qiuzhiType" editable="false" panelHeight="auto" style="width: 155px">
					    <option value="">请选择...</option>
						<option value="1">通知面试</option>
						<option value="2">面试完成</option>
					</select>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveQiuzhi()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeQiuzhiDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改个人信息页面</title>
<link rel="stylesheet" type="text/css" href="/jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="/jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="/jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="/jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="/jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" charset="UTF-8" src="/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="UTF-8" src="/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="UTF-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
	
	function submitData(){
		debugger;
		var nickName=$("#nickName").val();
		var sign=$("#sign").val();
		var imageFile=$("#imageFile").val();
		var proFile=UE.getEditor('proFile').getContent();
		
		if(nickName==null || nickName==''){
			alert("请输入昵称！");
		}else if(sign==null || sign==''){
			alert("请输入个性签名！");
		}else if(proFile==null || proFile==''){
			alert("请输入个性简介！");
		}
		else if(imageFile==null || imageFile=='') {
			alert("请上传个人图像")
		}
			else
		{
			$("#pF").val(proFile);
			$('#form1').submit();
		}

	}
	
	
	
</script>
</head>
<body style="margin: 10px">
<div id="p" class="easyui-panel" title="修改个人信息" style="padding: 10px">
	<form id="form1" action="/blogger/update" method="post" enctype="multipart/form-data">
	 	<table cellspacing="20px">
	   		<tr>
	   			<td width="80px">用户名：</td>
	   			<td>
	   				<input type="hidden" id="id" name="id" value="${currentUser.id }"/>
	   				<input type="text" id="userName" name="userName" style="width: 200px;" value="${currentUser.username }" readonly="readonly"/>
	   			</td>
	   		</tr>
	   		<tr>
	   			<td>昵称：</td>
	   			<td><input type="text" id="nickName" name="nickName"  style="width: 200px;"/></td>
	   		</tr>
	   		<tr>
	   			<td>个性签名：</td>
	   			<td><input type="text" id="sign" name="sign" value="${currentUser.sign }" style="width: 400px;"/></td>
	   		</tr>
	   		<tr>
	   			<td>个人头像：</td>
	   			<td><input type="file" id="imageFile" name="imageFile" style="width: 400px;"/></td>
	   		</tr>
	   		<tr>
	   			<td valign="top">个人简介：</td>
	   			<td>
					   <script id="proFile" type="text/plain" style="width:100%;height:500px;"></script>
					   <input type="hidden" id="pF" name="proFile"/>
	   			</td>
	   		</tr>
	   		<tr>
	   			<td></td>
	   			<td>
	   				<a href="javascript:submitData()" class="easyui-linkbutton" data-options="iconCls:'icon-submit'">提交</a>
	   			</td>
	   		</tr>
	   	</table>
   	</form>
 </div>
 
 <script type="text/javascript">

    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('proFile');

    ue.addListener("ready",function(){
        //通过ajax请求数据
        UE.ajax.request("/blogger/find",
            {
                method:"post",
                async : false,  
                data:{},
                onsuccess:function(result){
                	result = eval("(" + result.responseText + ")");  
                	$("#nickName").val(result.nickName);
                	$("#sign").val(result.sign);
                	$("#nickName").val(result.nickName);
       				UE.getEditor('proFile').setContent(result.proFile);
                }
            }
        );
    });
    
</script>
</body>
</html>
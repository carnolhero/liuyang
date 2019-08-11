<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<title>办公管理系统</title>

<!-- 1.引入layUI的CSS样式.  -->
<link rel="stylesheet" href="${ctx}/resources/js/layui/css/layui.css">

<script type="text/javascript" src="${ctx}/resources2/jedate/jedate.js"></script>

</head>
<body class="layui-layout-body">

		<div class="layui-body" style="margin-left: 176px">
			<!-- 内容主体区域 -->
			<div style="width: 50%;font-size:35px;margin-left: 97px;margin-top: 60px;">
					<h1><strong>修改管理员密码</strong></h1>
			</div>
				
			<div style="width: 50%;margin-top: 20px;">
				
				<form class="layui-form">
				
					<div class="layui-form-item" style="width:450px;border:1px darkgray solid;">
						<label class="layui-form-label" style="width: 116px">输入原密码：</label>
						<div class="layui-input-inline">
							<input type="password" name="oldPwd"
								style="width: 174%;" required lay-verify="required"
								placeholder="请输入原密码验证" autocomplete="off" class="layui-input"/>
						</div>
					</div>
					
					<div class="layui-form-item" style="width:450px;border:1px darkgray solid;">
						<label class="layui-form-label" style="width: 116px">输入密码：</label>
						<div class="layui-input-inline">
							<input type="text" name="password"
								style="width: 174%;" required lay-verify="required"
								placeholder="请输入新密码" autocomplete="off" class="layui-input"/>
						</div>
					</div>

					<div class="layui-form-item" style="width:450px;border:1px darkgray solid;">
						<label class="layui-form-label" style="width: 116px">输入密码：</label>
						<div class="layui-input-inline">
							<input type="text" name="password2"
								style="width: 174%;" required lay-verify="required"
								placeholder="请再次输入新密码" autocomplete="off" class="layui-input"/>
						</div>
					</div>
					
					<div class="layui-form-item">
						<div class="layui-input-block" style="margin-left: 150px">
							<button class="layui-btn" lay-submit lay-filter="formDemo">提交</button>
							<button type="reset" class="layui-btn">重置</button>
						</div>
					</div>

				</form>
			</div>
	</div>

	<!-- 3.必须引入: layUI的js脚本.
			   同时引入jquery的脚本.
 -->
	<script src="${ctx}/resources/js/layui/layui.js"></script>
	<script type="text/javascript" src="${ctx}/resources/js/jquery-1.8.3.min.js"></script>

	<!--  4.引入element元素对象. -->
	<script>
		//JavaScript代码区域
		layui.use('element', function() {
			var element = layui.element;
		});
	</script>
	
	<script type="text/javascript">
		
		layui.use('form', function() {
			var form = layui.form;

			//监听提交
			form.on('submit(formDemo)', function(data) {

				//转json字符串展示: layer.msg(JSON.stringify(data.field));
				JSON.stringify(data.field);

				//2.提交表单.
				$.ajax({
					url : '${pageContext.request.contextPath }/my1_updatePassword',
					type : 'post',
					dataType : 'json',
					data : data.field,
					success : function(data2) {
						alert("修改成功！为保证数据安全，请重新登录！");
						window.location.href = "${ctx}/my1_logout";
					},
					error : function() {
						alert("修改失败！");
					}
				});
				return false;
			});
		});
	
		layui.use(['form', 'layedit', 'laydate'], function(){
		  var form = layui.form
		  ,layer = layui.layer
		  ,layedit = layui.layedit
		  ,laydate = layui.laydate;
		  
		  //日期
		  laydate.render({
		    elem: '#date'
		  });
		  laydate.render({
		    elem: '#date1'
		  });
		  
		});
	</script>

</body>
</html>
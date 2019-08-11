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
<title>layout 后台大布局 - Layui</title>

<!-- 1.引入layUI的CSS样式.  -->
<link rel="stylesheet" href="${ctx}/resources/js/layui/css/layui.css">

</head>
<body class="layui-layout-body">
	<div class="layui-body" style="margin-top:30px">
		<div
			style="width: 50%;margin-left: 100px;text-align: center;font-size: 22px;">
			<h1>
				<strong>员工详情</strong>
			</h1>
		</div>

		<div style="width: 50%;margin-left: 140px;margin-top: 30px">
		
			<div class="layui-form-item" style="width:500px;border:1px darkgray solid;">
				<label class="layui-form-label" style="width: 80px">员工编号：</label>
				<div class="layui-input-block" style="margin-left: 30px">
					<input type="text" name="username"
						value="<s:property value='user.id'/>" style="width: 82.9%;"
						class="layui-input">
				</div>
			</div>

			<div class="layui-form-item" style="width:500px;border:1px darkgray solid;">
				<label class="layui-form-label" style="width: 80px">员工账号：</label>
				<div class="layui-input-block" style="margin-left: 30px">
					<input type="text" name="username"
						value="<s:property value='user.username'/>" style="width: 82.9%;"
						class="layui-input">
				</div>
			</div>

			<div class="layui-form-item"
				style="width:500px;border:1px darkgray solid;">
				<label class="layui-form-label" style="width: 80px">员工姓名：</label>
				<div class="layui-input-block" style="margin-left: 30px">
					<input type="text" name="nickname"
						value="<s:property value='user.nickname'/>" style="width: 82.9%;"
						class="layui-input" readonly="readonly" />
				</div>
			</div>

			<div class="layui-form-item"
				style="width:500px;border:1px darkgray solid;">
				<label class="layui-form-label" style="width: 80px">员工密码：</label>
				<div class="layui-input-inline">
					<input type="text" name="password" style="width: 205%;"
						value="<s:property value='user.password'/>" class="layui-input"
						readonly="readonly"/>
				</div>
			</div>

			<div class="layui-form-item" style="width:500px;height:39px;border:1px darkgray solid;">
				<div class="layui-inline">
					<label class="layui-form-label" style="width: 80px">员工生日：</label>
					<div class="layui-input-block">
						<input type="text" name="birthday" class="layui-input"
							value="<s:property value='user.birthday'/>"
							style="width: 390px;height: 39px" readonly="readonly" />
					</div>
				</div>
			</div>

			<div class="layui-form-item"
				style="width:500px;height:38px;border:1px darkgray solid;">
				<label class="layui-form-label" style="width: 80px">员工角色：</label>
				<div class="layui-input-block" style="margin-left: 100px">
					<input type="text" name="user.role.rolename" class="layui-input"
						value="<s:property value='user.role.rolename'/>"
						readonly="readonly" style="width: 97.5%;height: 37.5px"/>
				</div>
			</div>

			<div class="layui-form-item" style="width: 502px;">
				<input style="width: 100%" type="button" value="返回"
					class="layui-btn" onclick="history.back(-1)" />
			</div>
		</div>
	</div>
	<script src="${ctx}/resources/js/layui/layui.js"></script>
	<script type="text/javascript"
		src="${ctx}/resources/js/jquery-1.8.3.min.js"></script>


</body>
</html>
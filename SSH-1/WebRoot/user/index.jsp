<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>layout 后台大布局 - Layui</title>

<!-- 1.引入layUI的CSS样式.  -->
<link rel="stylesheet" href="${ctx}/resources/js/layui/css/layui.css">

</head>
<body class="layui-layout-body">
	<div class="layui-layout layui-layout-admin">
		<div class="layui-header">
			<div class="layui-logo">办公系统</div>
			<!-- 头部区域（可配合layui已有的水平导航） -->
			<ul class="layui-nav layui-layout-left">
				<li class="layui-nav-item"><a href="">控制台</a></li>
				<li class="layui-nav-item"><a href="">商品管理</a></li>
				<li class="layui-nav-item"><a href="">用户</a></li>
				<li class="layui-nav-item"><a href="javascript:;">其它系统</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">邮件管理</a>
						</dd>
						<dd>
							<a href="">消息管理</a>
						</dd>
						<dd>
							<a href="">授权管理</a>
						</dd>
					</dl></li>
			</ul>
			<ul class="layui-nav layui-layout-right">
				<li class="layui-nav-item"><a href="javascript:;">
				<img src="http://t.cn/RCzsdCq" class="layui-nav-img">
				${user.username}
				</a>
					<dl class="layui-nav-child">
						<dd>
							<a href="">基本资料</a>
						</dd>
						<dd>
							<a href="">安全设置</a>
						</dd>
					</dl></li>
				<li class="layui-nav-item"><a
					href="${ctx}/my1_logout">退出</a></li>
			</ul>
		</div>

		<div class="layui-side layui-bg-black">
			<div class="layui-side-scroll">
		<!-- 
      		2. 左侧菜单ul:  必须要有class.  和ID.
        -->
				<ul class="layui-nav layui-nav-tree" id="nav" lay-filter="test">
				</ul>

			</div>
		</div>

		<div class="layui-body" style="width: 100%;height: 100%;">
			<!-- 内容主体区域 -->
			<iframe frameborder="0" name="layadmin-iframe" style="width: 100%;height:83%;"></iframe>
		</div>

		<div class="layui-footer" style="text-align: center;">
			<!-- 底部固定区域 -->
			© 北大青鸟-刘飏
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

	<!-- 5.通过ajax请求后台服务器:  返回菜单集合. 并动态构建菜单.
		
	-->
	<script type="text/javascript">
	
		
		
		//菜单循环遍历打印拼接操作
		$(function() {

			//方式一: 提供的ajax请求方法.得到数组集合.
			$
					.ajax({
						url : '${pageContext.request.contextPath}/my1_initMenu',
						type : 'post',
						dataType : 'json',
						success : function(data) {

							//动态构建菜单.
							var menu = ""; //定义变量存储
							for ( var i = 0; i < data.length; i++) {
								menu += "<li class='layui-nav-item '>"
								if (data[i].pareMenuId == 0) { //取出父元素的菜单，拼进页面
									menu += "<a href='javascript:;'>"
											+ data[i].menuName + "</a>"
									for ( var j = 0; j < data.length; j++) { //继续遍历这几条数据
										if (data[j].pareMenuId === data[i].mid) { //取出这个父元素所对应的子元素
											menu += "<dl class='layui-nav-child'>"
											menu += "<dd>"
											menu += "<a href='${ctx}"+data[j].menuPath+"' target='layadmin-iframe'>"
													+ '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
													+ data[j].menuName + "</a>"
											menu += "</dd>"
											menu += "</dl>"
										}
									}
								}
								menu += "</li>";
							}

							//给ul菜单列表增加列表项:
							$("#nav").html(menu);

							//给菜单项提供展示子菜单功能.
							var element = layui.element;
							element.init()//初始化element事件，使菜单展开

						},
						error: function(){
							alert("菜单加载失败,请检查网络!!!");
						}
					});

		})
	</script>

</body>
</html>
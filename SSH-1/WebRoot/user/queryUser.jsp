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
<body>
	<!-- 搜索功能: -->
	<div>
		<input style="height:30px;margin-left: 3px;margin-top: 7px;" name="username" id="username" />
		<input style="height:35px" class="layui-btn" type="button" value="搜索" onclick="f1();" />
	</div>
	
	<div>
		<!-- 数据表格主体区域:  -->
		<table id="demo" lay-filter="test"></table>
	
		<!-- 定义一个列工具条: -->
		<script type="text/html" id="barDemo">
		<a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	</script>
	</div>
	
	<script type="text/javascript" src="${ctx}/resources/js/jquery-1.8.3.min.js"></script>
	<script src="${ctx}/resources/js/layui/layui.js"></script>

	<script type="text/javascript">
		//第一次请求后台,构建第一页数据:  无需条件搜索.
		layui
				.use(
						'table',
						function() {
							//创建一个临时对象table:  注: 没有var定义声明,默认变量作用域为全局变量.
							table = layui.table;

							//第一个实例 :   table.render() :  渲染表格.
							table
									.render({
										elem : '#demo',
										height : 312,
										url : '${pageContext.request.contextPath}/my1_queryUser' //数据接口
										,
										page : true //开启分页
										,
										parseData : function(res) { //res 即为原始返回的数据
											return {
												"code" : res.status, //解析接口状态
												"msg" : res.message, //解析提示文本
												"count" : res.total, //解析数据长度
												"data" : res.data
											//解析数据列表
											};
										},
										cols : [ [ //表头
										{
											field : 'id',
											title : 'ID',
											width : 160,
											sort : true,
											fixed : 'left'
										}, {
											field : 'username',
											title : '用户名',
											width : 180
										}, {
											field : 'password',
											title : '密码',
											width : 180
										}, {
											field : 'nickname',
											title : '姓名',
											width : 180
										}, {
											field : 'birthday',
											title : '生日',
											width : 180
										}, {
											title : '工具栏',
											fixed : 'right',
											width : 290,
											align : 'center',
											toolbar : '#barDemo' // 绑定列工具barDemo
										} ] ]
									});

							/* 增加数据表格的工具条的监听事件: */
							//监听工具条
							table
									.on(
											'tool(test)',
											function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"

												var data = obj.data; //获得当前行数据

												var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）

												var tr = obj.tr; //获得当前行 tr 的DOM对象

												if (layEvent === 'detail') { //查看

													window.location.href = "${pageContext.request.contextPath}/my1_userInfo?username="
															+ data.username;

												} else if (layEvent === 'del') { //删除
													//询问: 
													layer
															.confirm(
																	'确定删除吗',
																	function(
																			index) {

																		console
																				.log(index);
																		console
																				.log(data);

																		$
																				.ajax({
																					url : '${pageContext.request.contextPath }/my1_delUser',
																					type : 'post',
																					dataType : 'json',
																					data : {
																						'id' : data.id
																					},
																					success : function(
																							data2) {
																						// 删除这一行
																						obj
																								.del();
																						// 关闭弹窗
																						alert("删除成功！");
																						layer.closeAll();
																						parent.location.href="${ctx}/user/queryUser.jsp";
																						
																					},
																					error : function(
																							msg) {
																						alert("删除失败！");
																					}
																				});
																	});
												} else if (layEvent === 'edit') { //编辑
													window.location.href = "${pageContext.request.contextPath}/my1_update?id="
															+ data.id;
												}
											});

						});

		//搜索功能:
		function f1() {
			table.reload('demo', {
				where : { //设定异步数据接口的额外参数，任意设
					username : $("#username").val()
				},
				page : {
					curr : 1
				//重新从第 1 页开始
				}
			}); //只重载数据
			
		}
	</script>

</body>
</html>
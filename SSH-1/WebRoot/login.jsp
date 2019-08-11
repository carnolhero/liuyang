<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>

<!DOCTYPE html>
<html lang="en">

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Bootstrap Login Form Template</title>

        <!-- CSS -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
        <link rel="stylesheet" href="${ctx }/assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="${ctx }/assets/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="${ctx }/assets/css/form-elements.css">
        <link rel="stylesheet" href="${ctx }/assets/css/style.css">

        <!-- Favicon and touch icons -->
        <link rel="shortcut icon" href="${ctx }/assets/ico/favicon.png">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="${ctx }/assets/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="${ctx }/assets/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="${ctx }/assets/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="${ctx }/assets/ico/apple-touch-icon-57-precomposed.png">

    </head>

    <body>

        <!-- Top content -->
        <div class="top-content">
        	
            <div class="inner-bg">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-8 col-sm-offset-2 text">
                            <h1><strong>协同公司</strong> 办公 系统</h1>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3 form-box">
                        	<div class="form-top">
                        		<div class="form-top-left">
                        			<h3>登陆</h3>
                            		<p>输入你的用户名和密码:</p>
                        		</div>
                        		<div class="form-top-right">
                        			<i class="fa fa-key"></i>
                        		</div>
                            </div>
                            <div class="form-bottom">
			                    <form role="form" action="${ctx }/my1_login" method="post" class="login-form">
			                    	<div class="form-group">
			                    		<label class="sr-only" for="form-username">用户名</label>
			                        	<input type="text" name="username" placeholder="Username..." class="form-username form-control" id="form-username">
			                        </div>
			                        <div class="form-group">
			                        	<label class="sr-only" for="form-password">密码</label>
			                        	<input type="password" name="password" placeholder="Password..." class="form-password form-control" id="form-password">
			                        </div>
			                        <div class="form-group">
			                        	<button type="submit" class="btn">登录</button>
			                        </div>
			                        <div class="form-group">
			                        	<a href="${ctx }/updateUserPwd.jsp">
			                        		<button type="" class="btn">忘记密码</button>
			                        	</a>
			                    	</div>
			                    </form>
		                    </div>
                        </div>
                    </div>
                    
                </div>
            </div>
            
        </div>

        <!-- Javascript -->
        <script src="${ctx }/assets/js/jquery-1.11.1.min.js"></script>
        <script src="${ctx }/assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="${ctx }/assets/js/jquery.backstretch.min.js"></script>
        <script src="${ctx }/assets/js/scripts.js"></script>
        
    </body>

</html>
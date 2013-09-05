<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	type="text/javascript" src="js/jquery-1.10.1.js" ></script>
<script type="text/javascript">
	$(function() {
		$("#login").click(function() {
			$.post("Admin_login.action", {
				"admin.username" : "sa",
				"admin.password" : "sa"
			}, function(data, status) {
				alert("Data: " + data + "\nStatus: " + status);
				if("success"==status){
					window.location.href="index.jsp";
				}else{
					alert("密码错误！！！");
				}
			});
		});

	});
</script>
</head>
<body>
	<form action="Admin_login.action" method="post" name="form1">

		用户名：
		<s:textfield name="admin.username" />
		<br /> 密 码：
		<s:password name="admin.password" />
		<s:submit value="提交"></s:submit>
		<!--  <br /> <input type="button" value="提交" id="login" />-->
	</form>
</body>
</html>
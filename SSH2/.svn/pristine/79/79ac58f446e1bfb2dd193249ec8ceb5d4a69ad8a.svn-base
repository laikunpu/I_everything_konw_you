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
<script type="text/javascript" src="<%=basePath%>js/jquery-1.10.1.js"></script>
<script type="text/javascript">
	$(function() {
		$("#scanAll").click(function() {
			$.get("Admin_scanAll.action", function(data, status) {
				alert("Data: " + data + "\nStatus: " + status);
			});
		});
		$("#stopScan").click(function() {
			$.get("Admin_stopScan.action", function(data, status) {
				
				  // alert("Data: " + data + "\nStatus: " + status);
				
				alert(data);
			});
		});
	});
</script>
</head>
<body>
	<h1>你好！管理者！！</h1>
	<br />
	<button id="scanAll">扫描所有小说</button>  <button id="stopScan">停止扫描</button>  
</body>
</html>
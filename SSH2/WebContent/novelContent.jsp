<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="common/top.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function setBookmark(novelname, chaptertitle, chapternumber) {
		//alert(chapternumber);
		$.post("Login_setBookMark.action", {
			"bookMark.bookname" : novelname,
			"bookMark.bookchapter" : chaptertitle,
			"bookMark.chapternumber" : chapternumber
		}, function(data, status) {
			var json = jQuery.parseJSON(data);
			if ("success" == json.message) {
				alert("加入成功!!!");
			} else if ("error" == json.message) {
				alert("请先登录!!!");
			} else if ("1" == json.message) {
				alert("书架已经存在此书，无需重复添加!!!");
			} else if ("2" == json.message) {
				alert("此章节已加入书签，无需重复添加!!!");
			} else if ("3" == json.message) {
				alert("加入书签成功!!!");
			}

		});
	}
</script>
</head>
<body>
	<h2>${chapter.novelname}</h2>




	<span style="float: right;"><s:a href="#"
			onclick="setBookmark('%{chapter.novelname}','%{chapter.chaptertitle}','%{chapter.chapternumber}')">
			<font color="blue">加入书签</font>
		</s:a></span>


	<br />
	<h3>${chapter.chaptertitle}</h3>

	<br /> ${content}
	<br />
	<s:url action="Novel_goContent" id="preUrl">
		<s:param name="chapter.chapternumber" value='chapter.chapternumber-1'></s:param>
		<s:param name="chapter.novelname" value='chapter.novelname'></s:param>
	</s:url>
	<s:a href="%{preUrl}">
				上一章</s:a>
	<s:url action="Novel_goChapter" id="listUrl">
		<s:param name="book.bookname" value='chapter.novelname'></s:param>
	</s:url>
	<s:a href="%{listUrl}">
				回目录</s:a>
	<s:url action="Novel_goContent" id="nextUrl">
		<s:param name="chapter.chapternumber" value='chapter.chapternumber+1'></s:param>
		<s:param name="chapter.novelname" value='chapter.novelname'></s:param>
	</s:url>
	<s:a href="%{nextUrl}">
				下一章</s:a>

</body>
</html>
<jsp:include page="common/bottom.jsp" />
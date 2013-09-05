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
</head>
<body>
	<h3>${book.bookname}</h3>



	<s:iterator value="chapters" status="st">
		<s:if test="(#st.index%3)==0">
			<br />
		</s:if>

		<s:a action="Novel_goContent">
			<s:param name="chapter.chaptertitle" value='chaptertitle'></s:param>
			<s:param name="chapter.chapternumber" value='chapternumber'></s:param>
			<s:param name="chapter.novelname" value='novelname'></s:param>
			<s:property value='chaptertitle' />
		</s:a>



	</s:iterator>
</body>
</html>
<jsp:include page="common/bottom.jsp"/>>
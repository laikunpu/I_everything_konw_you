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
	<ul>
		${message}
		<!--  迭代数据 -->
		<s:iterator value="books" status='st'>
			<li><span> <s:property value="#st.index+1" />
			</span> <s:a action="Novel_goDetail">
					<s:param name="book.bookname" value='bookname'></s:param>
					<s:property value='bookname' />
				</s:a></li>
		</s:iterator>
		
		<!--  分页 -->
		<s:iterator begin="page.startPage" end="page.endPage" status='st'>
			<s:if test="page.startPage!=page.endPage">
				<s:if test="page.currentPage!=#st.index+page.startPage-1">
					<s:url action="Novel_goList" id="url">
						<s:param name="page.currentPage"
							value='#st.index+page.startPage-1'></s:param>
						<s:param name="book.booktype" value='booktype'></s:param>
					</s:url>
					<s:a href="%{url}">
				[<s:property />]</s:a>
				</s:if>
				<s:else>
					<font color="red"><s:property /></font>
				</s:else>
			</s:if>
		</s:iterator>
	</ul>
</body>
</html>
<jsp:include page="common/bottom.jsp" />
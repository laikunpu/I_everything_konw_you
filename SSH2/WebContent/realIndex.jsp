<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include  page="common/top.jsp"/>
<jsp:include  page="common/left.jsp"/>

<link href="css/index.css" rel="stylesheet" />
<script src="js/index.js" type="text/javascript"></script>
<div class="silde-box">
	<ul class="lst-content">
		<li>
			<a href="#" title="图片标题"><img src="images/demo1.jpg" title="图片标题" alt="图片标题" width="760" height="230"/></a>
		</li>
		<li>
			<a href="#" title="图片标题"><img src="images/demo2.jpg" title="图片标题" alt="图片标题" width="760" height="230"/></a>
		</li>
		<li>
			<a href="#" title="图片标题"><img src="images/demo3.jpg" title="图片标题" alt="图片标题" width="760" height="230"/></a>
		</li>
		<li>
			<a href="#" title="图片标题"><img src="images/demo4.jpg" title="图片标题" alt="图片标题" width="760" height="230"/></a>
		</li>
	</ul>
	<ul class="lst-nav">
		<li>图片标题1</li>
		<li>图片标题1</li>
		<li>图片标题1</li>
		<li>图片标题1</li>
	</ul>
</div>

<div class="empty-height10"></div>

<div class="search-box border">
	<form action="Novel_goList" method="get">
		<span class="title">站内搜索</span>
		<select name="searchItem.item" id="category">
			<option value="bookname">书名</option>
			<option value="bookauthor">作者</option>
		</select>
		<input type="text" name="searchItem.keyword" required="" value="" id="keyword" x-webkit-speech lang="zh-CN" x-webkit-grammar="builtin:translate"/>
		<input type="submit" value="搜索" id="btnSearch"/>
	</form>
</div>

<div class="empty-height10"></div>

<div class="hd line-height30">书籍分类</div>
<!-- 
	<s:iterator begin="page.startPage" end="page.endPage" status='st'>
		<s:if test="page.currentPage!=#st.index+page.startPage-1">
			<s:url action="Novel_goIndex" id="url">
				<s:param name="page.currentPage" value='#st.index+page.startPage-1'></s:param>
			</s:url>
			<s:a href="%{url}">
				[<s:property />]</s:a>
		</s:if>
		<s:else>
			<font color="red"><s:property /></font>
		</s:else>
	</s:iterator>
	-->
<jsp:include page="common/bottom.jsp"/>
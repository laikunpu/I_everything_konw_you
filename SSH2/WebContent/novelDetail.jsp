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
	$(function() {

	});
	function goVote(data) {
		$.post("Novel_goRecommend.action", {
			"book.bookname" : data
		}, function(data, status) {
			var json = jQuery.parseJSON(data);
			if ("success" == json.message) {
				alert("推荐成功!!!");
			} else {
				alert("推荐失败!!!");
			}

		});
	}
	function goDownlaod() {
		alert("木有下载!!!");
	}
	function setBookmark(data) {
		$.post("Login_setBookMark.action", {
			"bookMark.bookname" : data
		}, function(data, status) {
			var json = jQuery.parseJSON(data);
			if ("success" == json.message) {
				alert("加入成功!!!");
			} else if ("error" == json.message) {
				alert("请先登录!!!");
			} else if ("1" == json.message) {
				alert("本书已在您的书架，无需重复添加。!!!");
			}

		});
	}

	function comment() {
		if ($.trim($("#tet_comment").val())) {
			$('#form_comment').ajaxForm(function(data) {
				var json = jQuery.parseJSON(data);
				if ("success" == json.message) {
					alert("评论成功!!!");
					window.location.href = window.location.href;
				} else if ("error" == json.message) {
					alert("评论失败!!!");
				}
			});
			$("#form_comment").submit();

		} else {
			alert("评论不可为空!!!");
		}

	}
</script>
</head>
<body>
	<s:a action="Novel_goChapter">点击阅读<s:param name="book.bookname"
			value='book.bookname'></s:param>
	</s:a>
	<br />
	<s:a href="#" onclick="setBookmark('%{book.bookname}')">加入书架</s:a>
	<br />
	<s:a href="#" onclick="goVote('%{book.bookname}')">投推荐票 </s:a>
	<br />
	<s:a href="#" onclick="goDownlaod()">下载阅读</s:a>
	<br />
	<br />
	<br />
	<br />
	<br />
	<s:iterator value="book.bookComments">
		<HR color=red SIZE=3>
		用户:<s:property value="readername" />
		<br />
		<s:property value="comment" />
		<br />
		<br />
		<br />
	</s:iterator>
	<br />
	<br />
	<br />
	<br /> 发表评论:
	<br /> 内容:
	<br />
	<s:form id="form_comment" action="Novel_comment" method="get">
		<textarea id="tet_comment" name="bookComment.comment"
			style="resize: none; width: 500px; height: 200px;"></textarea>
		<s:hidden name="book.bookname" value="%{book.bookname}" />
		<br />
		<input type="button" id="btn_booComment" value="提交"
			onclick="comment()" />
	</s:form>
</body>
</html>
<jsp:include page="common/bottom.jsp" />

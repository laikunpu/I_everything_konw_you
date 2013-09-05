<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<jsp:include page="../../../common/top.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function deleteBookmark() {
		var objTable = document.getElementsByName("ckx_bookMark");
		if (objTable.length > 0) {
			var checked = 0;
			var ckx_checked = 0;
			for ( var i = 0; i < objTable.length; i++) {
				//判断是不是选中
				if (objTable[i].checked) {
					ckx_checked++;
					if (!$(".span_bookMark").eq(i).is(":hidden")) {
						checked++;
						$(".span_bookMark").eq(i).hide();
					}

				}

			}
			if (ckx_checked == objTable.length) {
				$("#btn_booMark").hide();
			}
			if (checked > 0) {
				$('#form_bookMark').ajaxForm(function(data) {
					var json = jQuery.parseJSON(data);
					if ("success" == json.message) {
						alert("删除成功!!!");
					} else if("error" == json.message) {
						alert("删除失败!!!");
					}
				});
				$("#form_bookMark").submit();
			} else {
				alert("至少选择一项!!!");
			}

		}
	}
</script>
</head>
<body>

	<font color="red"><s:label>我的书架</s:label></font>
	<br />
	<s:form action="Login_deleteBookmark" method="get" id="form_bookMark">
		<s:iterator value="reader.bookMarks">
			<span class="span_bookMark"> <s:checkbox name="ckx_bookMark"
					fieldValue='%{bookname}' /> <s:a action="Novel_goDetail">
					<s:param name="book.bookname" value='bookname'></s:param>
					<s:property value='bookname' />
				</s:a>  
				
				<s:a action="Novel_goContent">
			<s:param name="chapter.chaptertitle" value='bookchapter'></s:param>
			<s:param name="chapter.chapternumber" value='chapternumber'></s:param>
			<s:param name="chapter.novelname" value='bookname'></s:param>
			<s:property value='bookchapter' />
		</s:a>
				
				
				<br />
			</span>
		</s:iterator>
		<s:if test="reader.bookMarks.size()>0">
			<input type="button" id="btn_booMark" value="删除所选项"
				onclick="deleteBookmark()" />
		</s:if>
		<s:else>
		书架为空!!!
		</s:else>


	</s:form>
</body>
</html>
<jsp:include page="../../../common/bottom.jsp" />
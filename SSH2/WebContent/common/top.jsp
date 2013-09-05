<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的小说网站</title>
<link href="css/main.css" type="text/css" rel="stylesheet" />
<link href="css/easyui.css" type="text/css" rel="stylesheet" />
<link href="css/icon.css" type="text/css" rel="stylesheet" />
<script src="js/jquery-1.9.0.js" type="text/javascript"></script>
<script src="js/jquery.validate.js" type="text/javascript"></script>
<script src="js/jquery.form.min.js" type="text/javascript"></script>
<script src="js/jquery.easyui.min.js" type="text/javascript"></script>
<script src="js/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$('#dlg').dialog('close');
		$('#dlg_register').dialog('close');
		$('#tip').hide();
		$('#register_tip').hide();
		$("#a_logout").click(function() {
			$.get("Login_logout.action", function(data, status) {
				var json = jQuery.parseJSON(data);
				if ("success" == json.message) {
					alert("注销成功!!!");
					window.location.href = window.location.href;
				} else if ("error" == json.message) {
					alert("注销失败!!!");
				}
			});
		});
	});
	function writeMessage() {
		alert("请拨911找奥观海");
	}
	function login() {
		$('#form_login').ajaxForm(function(data) {

			var json = jQuery.parseJSON(data);
			if ("success" == json.message) {
				$('#dlg').dialog('close');
				$('#tip').hide();
				window.location.href = window.location.href;
			} else if ("error" == json.message) {
				$('#tip').show();
			}
		});
	}
	function register() {
		$('#form_register').ajaxForm(function(data) {
			var json = jQuery.parseJSON(data);
			if ("success" == json.message) {
				$('#dlg_register').dialog('close');
				$('#register_tip').hide();
				window.location.href = window.location.href;
			} else if ("error" == json.message) {
				$('#register_tip').show();
			}
		});
	}
</script>
</head>
<body>
	<!-- 导航 -->
	<div class="top-box line-height30">
		<div class="layout1000 line-height30">
			<s:if test="#session.user==null">
				<s:label>您好,游客!!! 请</s:label>
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="$('#dlg').dialog('open')">登录</a>|
				<a href="javascript:void(0)" class="easyui-linkbutton"
					onclick="$('#dlg_register').dialog('open')">注册</a>








			</s:if>
			<s:else>
				<span>您好,<s:a action="Login_goUser">
						<s:property value="#session.user.nickname" />
					</s:a>,欢迎访问我的小说网!<a id="a_logout" class="easyui-linkbutton">注销</a></span>
			</s:else>




		</div>


		<div id="dlg" class="easyui-dialog" title="登录"
			data-options="iconCls:'icon-save'"
			style="width: 400px; height: 300px; padding: 10px;">
			<s:form id="form_login" action="Login_login">
				<p>
					<label>用 户 名:</label><input type="text" required=""
						name="reader.username" />
				</p>
				<p>
					<label>密 码:</label><input type="password" required=""
						name="reader.password" />
				</p>
				<p id="tip">
					<font color="red"><label>登录名或密码错误!!!</label></font>
				</p>
				<s:submit value="提交" onclick="login()" />
			</s:form>
		</div>



		<div id="dlg_register" class="easyui-dialog" title="登录"
			data-options="iconCls:'icon-save'"
			style="width: 400px; height: 300px; padding: 10px;">
			<s:form id="form_register" action="Login_register">
				<p>
					<label>用 户 名:</label><input type="text" required=""
						name="reader.username" />
				</p>
				<p>
					<label>密 码:</label><input type="password" required=""
						name="reader.password" />
				</p>
				<p>
					<label>验证密码:</label><input id="confirm_password" required=""
						type="password" name="confirm" />
				</p>
				<p>
					<label>昵 称:</label><input type="text" required=""
						name="reader.nickname" />
				</p>
				<p>
					<label>邮 箱:</label><input type="email" required=""
						name="reader.email" />
				</p>
				<p id="register_tip">
					<font color="red"><label>用户名已经存在,注册失败!!!</label></font>
				</p>
				<p>
					<s:submit value="提交" onclick="register()" />
				</p>
			</s:form>
		</div>









		<ul class="line-height30">
			<li><a href="index.jsp" title="网站首页">网站首页</a>|</li>
			<li><a href="#" title="给我留言" onclick="writeMessage();">给我留言</a></li>
		</ul>
		<div class="clear"></div>
	</div>
	</div>
	<!-- 招牌 -->
	<div class="banner-box layout1000"></div>
	<!-- 菜单 -->
	<div class="menu-box line-height30">
		<ul class="menu-nav layout1000 line-height30">
			<li><a href="index.jsp" title="网站首页">网站首页</a></li>
			<li><s:a action="Novel_goList">书库|</s:a></li>
			<s:iterator value="bookTypes" id="bookType" status="st">
				<li><s:a action="Novel_goList">
						<s:property value="booktype" />
						<s:param name="book.booktype" value='booktype'></s:param>
					</s:a> <s:if test="!#st.last">|</s:if></li>
			</s:iterator>

		</ul>
		<div class="clear"></div>
	</div>

	<div class="empty-height10"></div>
	<div id="content" class="layout1000">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="float-left left-content-box">
	<div class="hotbook-box border">
		<h3 class="line-height30 hd">热门书籍</h3>
		<ul>
			<s:iterator value="hotkooks" status='st'>
				<li>
					<span>
						<s:property value="#st.index+1" />
					</span>
					<s:a action="Novel_goDetail">
						<s:param name="book.bookname" value='bookname'></s:param>
						<s:property value='bookname' />
					</s:a>
				</li>
			</s:iterator>
		</ul>
	</div>
	<div class="empty-height10"></div>
	<div class="hotbook-box border">
		<h3 class="line-height30 hd">排行榜</h3>
		<ul>
			<s:iterator value="books" status='st'>
				<li>
					<span>
						<s:property value="#st.index+1" />
					</span>
					<s:a action="Novel_goDetail">
						<s:param name="book.bookname" value='bookname'></s:param>
						<s:property value='bookname' />
					</s:a>
				</li>
			</s:iterator>
		</ul>
	</div>
	<div class="empty-height10"></div>
	<div class="hotbook-box border">
		<h3 class="line-height30 hd">读者推荐榜</h3>
		<ul>
			<s:iterator value="recommendBooks" status='st'>
				<li>
					<span>
						<s:property value="#st.index+1" />
					</span>
					<s:a action="Novel_goDetail">
						<s:param name="book.bookname" value='bookname'></s:param>
						<s:property value='bookname' />
					</s:a>
				</li>
			</s:iterator>
		</ul>
	</div>
</div>
<div class="empty-width10 float-left">&nbsp;</div>
<div class="float-left right-content-box">
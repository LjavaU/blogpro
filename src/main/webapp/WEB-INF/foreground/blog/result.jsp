<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="data_list">
		<div class="data_list_title">
		<img src="/images/search_icon.png"/>
		搜索&nbsp;<font color="red">${q }</font>&nbsp;的结果 &nbsp;(总共搜索到&nbsp;${resultTotal}&nbsp;条记录) </div>
		<div class="datas search">
			<ul>
				<c:choose>
					<c:when test="${blogList.size()==0 }">
						<div align="center" style="padding-top: 20px">未查询到结果，请换个关键字试试看！</div>
					</c:when>
					<c:otherwise>
						<c:forEach var="blog" items="${blogList }">
					  	  <li style="margin-bottom: 20px">
						  	<span class="title"><a href="/articles/${blog.id}" target="_blank">${blog.title }</a></span>
						  	<span class="summary">摘要: ${blog.content }...</span>
						  	<span class="link"><a href="/articles/${blog.id}">http://blog.java1234.com/blog/articles/${blog.id}</a>&nbsp;&nbsp;&nbsp;&nbsp;发布日期：${blog.releaseDateStr }</span>
						  </li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		${pageCode }
   </div>
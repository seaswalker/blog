<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%    
	String path = request.getContextPath();    
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<link rel="shortcut icon" href="ico/icon.ico">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索</title>
<link rel="stylesheet" href="css/search.css">
<script type="text/javascript">
	//返回博客主页
	function to_index() {
		window.location.href = "index.html";
		return false;
	}
</script>
</head>
<body>
	<!--头部-->
	<div class="search_head">
		<form action="search.html" method="post">
			<img src="images/love.png"> <input type="text" name="search"
				class="search_input">
			<button type="submit" class="button">搜索</button>
			<button class="button" onclick="return to_index();">返回博客</button>
		</form>
	</div>
	<!--搜索信息提示栏-->
	<div class="search_info">找到结果${pageBean.recordCount}个，耗时约${pageBean.cost / 1000}秒</div>
	<!--搜索内容-->
	<div class="search_content">
		<!--一条记录-->
		<c:forEach items="${pageBean.records}" var="article">
			<div class="content">
				<div class="content_title">
					<a href="article.html?id=${article.id}">${article.title}</a>
				</div>
				<div class="content_content">
					${article.digest}
				</div>
				<!--发表时间、点击量、回复量-->
				<div class="content_info">
					<img src="images/time.png"> <fmt:formatDate value="${article.createTime}" type="date"/>  
					&nbsp;&nbsp; 
					<img src="images/click.png"> 点击(${article.clickCount}) &nbsp;&nbsp; 
					<img src="images/comment.png">
					评论(${article.replyCount})
				</div>
				<!--地址-->
				<div class="content_addr">
					http://localhost:8080/Blog/article.html?id=${article.id}
				</div>
			</div>
		</c:forEach>
	</div>
	
	<!--分页-->
	<div class="page">
		<c:if test="${pageBean.pageCount > 0}">
			<ul>
				<c:choose>
					<c:when test="${1 == pageBean.currentPage}">
						<li class="active"><span>首页</span></li>
					</c:when>
					<c:otherwise>
						<li><a href="search.html?search=${search}&pn=1">首页</a></li>
					</c:otherwise>
				</c:choose>
				<c:forEach begin="${pageBean.pageBeginIndex}" end="${pageBean.pageEndIndex}" var="i">
					<c:choose>
						<c:when test="${i == pageBean.currentPage}">
							<li class="active"><span>${i}</span></li>
						</c:when>
						<c:otherwise>
							<li><a href="search.html?search=${search}&pn=${i}">${i}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${pageBean.pageCount == pageBean.currentPage}">
						<li class="active"><span>尾页</span></li>
					</c:when>
					<c:otherwise>
						<li><a href="search.html?search=${search}&pn=${pageBean.pageCount}">尾页</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</c:if>
	</div>
	<!--小尾巴-->
	<div class="foot">©Copyright skywalker</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%    
	String path = request.getContextPath();    
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!--引入css-->
    <link rel="stylesheet" href="css/head.css">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="css/foot.css">
    <link rel="stylesheet" href="css/common.css">
    <link rel="shortcut icon" href="ico/icon.ico">
    <!--引入日历控件-->
    <link rel="stylesheet" href="css/calendar.css">
    <script src="script/calendar.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href = "<%=basePath%>">
	<title>skywalker</title>
</head>
<body>
		<jsp:include page="share/head.jsp"></jsp:include>
        <!--以上是头部-->
        
        <!--主体div-->
        <div class="main">
            <!--左侧-->
            <jsp:include page="share/left.jsp"></jsp:include>
            <!--右侧-->
            <div class="main-right">
                <!--迭代文章开始-->
                <!--右侧一篇文章-->
                <c:forEach items="${pageBean.records}" var="article" >
	                <div class="article-digest">
	                    <div class="article-title">
	                        <a href="article.html?id=${article.id}" target="_blank">${article.title}</a>
	                    </div>
	                    <!--发表时间和作者-->
	                    <div class="article-author-date">
	                        <span class="article-date">
	                        	<fmt:formatDate value="${article.createTime}" pattern="yyyy-MM-dd"/>
	                        </span>
	                        &nbsp;&nbsp;
	                        <span class="article-author">skywalker</span>
	                    </div>
	                    <!--正文-->
	                    <div class="article-content">
							 ${article.digest}
	                    </div>
	                    <!--阅读全文-->
	                    <div class="article-more">
	                        <a href="article.html?id=${article.id}" target="_blank">阅读全文&gt;&gt;</a>
	                    </div>
	                    <!--标签-->
	                    <div class="article-others">
	                        <!--点击量-->
	                       	 点击(${article.clickCount}) 回复(${article.replyCount})
	                        &nbsp;&nbsp;
	                      	标签：${article.tagsStr}
	                    </div>
	                </div>
                </c:forEach>
                <!--迭代文章结束-->
                
                <!--分页-->
                <div class="left-page">
                	<c:if test="${pageBean.currentPage == 1}">
                		首页
                	</c:if>
                	<c:if test="${pageBean.currentPage > 1}">
						<a href="index.html?page=1&date=${date}&cy=${cy}&tag=${tag}&search=${search}">首页</a> &nbsp;第
                	</c:if>
					<!-- 迭代页码 -->
					<c:forEach begin="${pageBean.pageBeginIndex}" end="${pageBean.pageEndIndex}" var="code">
						<c:if test="${pageBean.currentPage == code}">
							<span class="current">${code}</span>
						</c:if>
						<c:if test="${pageBean.currentPage != code}">
							<a href="index.html?page=${code}&date=${date}&cy=${cy}&tag=${tag}&search=${search}">${code}</a>&nbsp;
						</c:if>
					</c:forEach>
					页
					<!-- 尾页 -->
					<c:if test="${pageBean.currentPage == pageBean.pageCount}">
						尾页
					</c:if>
					<c:if test="${pageBean.currentPage < pageBean.pageCount}">
						<a href="index.html?page=${pageBean.pageCount}&date=${date}&cy=${cy}&tag=${tag}&search=${search}">尾页</a>
					</c:if>
				</div>
            </div>
            <div style="clear: both;"></div>
        </div>
        <!--主体结束-->
        
        <!-- 引入尾巴 -->
        <jsp:include page="share/foot.jsp"></jsp:include>
</body>
</html>
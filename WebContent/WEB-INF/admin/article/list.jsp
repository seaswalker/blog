<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%    
	String path = request.getContextPath();    
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
%> 
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href = "<%=basePath%>">
	<title>博客列表</title>
	<style type="text/css">
		.title {
			font-size: 20px;
		}
		.main {
			width: 800px;
			margin: 40px auto 0px;
			text-align: center;
		}
		.main table {
			width: 100%;
			margin-top: 20px;
		}
		td {
			text-align: center;
		}
		.page {
			margin: 20px auto 0px;
			font-size: 14px;
		}
		.page a {
			text-decoration: none;
		}
	</style>
</head>
<body>
	<div class="main">
		<div class="title">
			博文列表：
		</div>
		<table border="1">
			<thead>
                <th>
					序号
                </th>
                <th>
					 标题
                </th>
                <th>
					 回复
                </th>
                <th>
					点击
                </th>
                <th>
					所属类别
                </th>
                <th>
					 发表时间
                </th>
                <th>
                	操作
                </th>
            </thead>
            <tbody>
                <!--迭代开始-->
                <c:forEach items="${pageBean.records}" var="article">
	                <tr>
	                    <td>
	                        ${article.id}
	                    </td>
	                    <td>${article.title}</td>
	                    <td>${article.replyCount}</td>
	                    <td>${article.clickCount}</td>
	                    <td>${article.category.name}</td>
	                    <td>${article.createTime}</td>
	                    <td>
	                    	<a href="admin/article/edit.html?id=${article.id}">修改</a>
	                    	&nbsp;
	                    	<a href="admin/article/delete.html?id=${article.id}">删除</a>
	                    </td>
	                </tr>
                </c:forEach>
                <!--迭代结束-->
            </tbody>
		</table>
		<!-- 分页 -->
		<div class="page">
			<a href="admin/article/list.html?pageCode=1">首页</a>
			&nbsp;第
			<!-- 迭代页码 -->
			<c:forEach begin="${pageBean.pageBeginIndex}" end="${pageBean.pageEndIndex}" var="code">
				<a href="admin/article/list.html?pageCode=${code}">${code}</a>&nbsp;
			</c:forEach>
			页
			<!-- 尾页 -->
			<a href="admin/article/list.html?pageCode=${pageBean.pageCount}">尾页</a>
		</div>
	</div>
</body>
</html>
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
	<title>回复列表</title>
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
			回复列表：
		</div>
		<table border="1">
			<thead>
                <th>
					序号
                </th>
                <th>
					名称
                </th>
                <th>
                	操作
                </th>
            </thead>
            <tbody>
                <!--迭代开始-->
                <c:forEach items="${pageBean.records}" var="reply">
	                <tr>
	                    <td>
	                        ${reply.id}
	                    </td>
	                    <td>${reply.content}</td>
	                    <td>
	                    	<a href="admin/reply/delete.html?id=${reply.id}">删除</a>
	                    </td>
	                </tr>
                </c:forEach>
                <!--迭代结束-->
            </tbody>
		</table>
		<!-- 分页 -->
		<div class="page">
			<a href="admin/reply/list.html?pageCode=1">首页</a>
			&nbsp;第
			<!-- 迭代页码 -->
			<c:forEach begin="${pageBean.pageBeginIndex}" end="${pageBean.pageEndIndex}" var="code">
				<a href="admin/reply/list.html?pageCode=${code}">${code}</a>&nbsp;
			</c:forEach>
			页
			<!-- 尾页 -->
			<a href="admin/reply/list.html?pageCode=${pageBean.pageCount}">尾页</a>
		</div>
	</div>
</body>
</html>
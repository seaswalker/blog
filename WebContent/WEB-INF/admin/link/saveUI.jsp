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
	<script type="text/javascript">
		//检查输入
		function check() {
			var link = document.forms[0].link;
			var url = document.forms[0].url;
			if(link.value == "") {
				alert("请输入链接名称");
				link.focus();
				return false;
			}
			if(url.value == "") {
				alert("请输入地址");
				url.focus();
				return false;
			}
			return true;
		}
	</script>
	<style type="text/css">
		.main {
			text-align: center;
			margin-top: 40px;
		}
	</style>
</head>
<body>
	<div class="main">
		<form action="admin/link/save.html" method="post" onsubmit="return check();">
			<!-- 提交id -->
			<input type="hidden" name="id" value="${link.id}"> 
			链接名称：
			<input type="text" name="name" value="${link.name}">
			<br><br>
			链接地址：
			<input type="text" name = "url" value = "${link.url}">
			<br><br>
			<input type="submit" value="提交">
		</form>
	</div>
</body>
</html>
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
			var tag = document.forms[0].tag;
			if(tag.value == "") {
				alert("请输入标签");
				tag.focus();
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
		<form action="admin/tag/save.html" method="post" onsubmit="return check();">
			<!-- 提交id -->
			<input type="hidden" name="id" value="${tag.id}"> 
			标签名称：
			<input type="text" name="tag" value="${tag.name}">
			<br><br>
			<input type="submit" value="提交">
		</form>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<!--引入css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>skywalker</title>
</head>
<body>
		<!--主窗口-->
    <div class="main">
        <form action="${pageContext.request.contextPath}/admin/login.html" method="post" name="loginform">
            <input type="text" name="username" class="inputusername" value="${username}">
            <input type="password" name="password" class="inputpassword">
            <!--错误信息回显-->
            <div class="login-error">
                	${error}
            </div>
            <!--两个按钮-->
            <input type="image" src="${pageContext.request.contextPath}/images/loginbtn.png" class="btn">
            <input type="image" src="${pageContext.request.contextPath}/images/resetbtn.png" class="btn" onclick="javascript:loginform.reset();return false;">
        </form>
    </div>
</body>
</html>
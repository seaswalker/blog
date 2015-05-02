<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%    
	String path = request.getContextPath();    
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href = "<%=basePath%>">
	<!--引入css-->
    <link rel="stylesheet" href="css/head.css">
    <link rel="stylesheet" href="css/foot.css">
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/error.css">
    <link rel="shortcut icon" href="ico/icon.ico">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>出错啦</title>
</head>
<body>
		<jsp:include page="share/head.jsp"></jsp:include>
        <!--以上是头部-->
        
        <!--主体div-->
        <div class="main">
            <br>
            <div class="content">
                <!--错误图片-->
                <img src="images/error.png">
                &nbsp;&nbsp;
                <a href="index.html">出错啦...去首页看看吧...</a>
            </div>
        </div>
        <!--主体结束-->
        
        <!-- 引入尾巴 -->
        <jsp:include page="share/foot.jsp"></jsp:include>
</body>
</html>
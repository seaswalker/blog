<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="css/about.css">
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
            <div class="about-right">
                <!--标题-->
                <div class="about-title">
                  	  关于：
                </div>
                <!--正文-->
                <div class="about-content">
                	${about}
                </div>
            </div>
            <div style="clear: both;"></div>
        </div>
        <!--主体结束-->
        
        <!-- 引入尾巴 -->
        <jsp:include page="share/foot.jsp"></jsp:include>
</body>
</html>
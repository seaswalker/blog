<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%    
	String path = request.getContextPath();    
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href = "<%=basePath%>">
	<!--引入css-->
    <link rel="stylesheet" href="css/write.css">
    <script src="script/write.js"></script>
    <!-- 引入Umeditor -->
 	<link rel="stylesheet" href = "umeditor/themes/default/css/umeditor.min.css"/>
    <!-- 配置文件 -->
    <script src = "umeditor/third-party/jquery.min.js"></script>
    <script src="umeditor/umeditor.config.js"></script>
    <script src="umeditor/umeditor.min.js"></script>
    <script src = "umeditor/lang/zh-cn/zh-cn.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>写博客</title>
</head>
<body>
   <!--主体div-->
   <div class="main">
       <!--提示-->
       <div class="write-tips">
          	 留言板：
       </div>
       <form action="admin/message/save.html" method="post" onsubmit="return check();">
       	   <!-- 提交id -->
       	   <input type="hidden" name="id" value="${message.id}">
           <!--富文本编辑器-->
           <div class="write-editor">
               <script type="text/plain" id="myEditor" style="width:800px;height:300px;">
					${message.content}
               </script>
               <script type = "text/javascript">
                   editor = UM.getEditor("myEditor");
               </script>
           </div>
           <!--发表博文-->
           <div class="write-button">
               <input type="submit" value="保存">
           </div>
       </form>
   </div>
   <!--主体结束-->
</body>
</html>
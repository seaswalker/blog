<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ckeditor" uri="http://ckeditor.com" %>
<%@taglib prefix="ckfinder" uri="http://cksource.com/ckfinder" %>
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
    <script type="text/javascript" src="script/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="ckfinder/ckfinder.js"></script>
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
               <ckfinder:setupCKEditor editor="ckeditor" basePath="ckfinder/"/>
               <textarea name="content" id="ckeditor" style="width:800px;height:300px;">
					${message.content}
               </textarea>
               <ckeditor:replace replace="ckeditor" basePath="ckeditor/"></ckeditor:replace>
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
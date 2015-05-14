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
 	<script type="text/javascript" src="script/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>写博客</title>
</head>
<body>
   <!--主体div-->
   <div class="main">
       <!--提示-->
       <div class="write-tips">
          	 随便写点什么：
       </div>
       <form action="admin/article/save.html" method="post" onsubmit="return check();">
       	   <input type = "hidden" name = "id" value = "${article.id}">
           <!--标题-->
           <div class="write-title">
              	 标题：
               <input type="text" name="title" style="width:300px;" value = "${article.title}">
            </div>
           <!--富文本编辑器-->
           <div class="write-editor">
               <textarea name="content" class="ckeditor" style="width:800px;height:300px;">
					${article.content}
               </textarea>
           </div>
           <!--选择类别-->
           <div class="write-category">
               	分类：
               <select name="categoryid">
                   <option value="0">请选择所属类别</option>
                   <c:forEach items="${categories}" var="category">
                   		<option value="${category.id}" <c:if test="${category.id == article.category.id}">selected</c:if> >${category.name}</option>
                   </c:forEach>
               </select>
           </div>
           <!--标签-->
           <div class="write-tags">
               	标签(最多使用5个，以空格分开)：
               <input type="text" name="tags" value = "${article.tagsStr}">
           </div>
           <!-- 摘要 -->
           <div class="write_summary">
           		博文摘要:(默认将使用博文内容的前30字)
           		<br><br>
           		<textarea style="width:800px;height:100px;" name="summary"></textarea>
           </div>
           <!--发表博文-->
           <div class="write-button">
               <input type="submit" value="发博文">
           </div>
       </form>
   </div>
   <!--主体结束-->
</body>
</html>
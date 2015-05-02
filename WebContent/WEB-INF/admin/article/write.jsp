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
               <script type="text/plain" id="myEditor" style="width:800px;height:300px;">
					${article.content}
               </script>
               <script type = "text/javascript">
                   editor = UM.getEditor("myEditor");
               </script>
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
           <!--发表博文-->
           <div class="write-button">
               <input type="submit" value="发博文">
           </div>
       </form>
   </div>
   <!--主体结束-->
</body>
</html>
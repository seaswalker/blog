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
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>管理页面</title>
	<script src="script/admin/prototype.lite.js" type="text/javascript"></script>
	<script src="script/admin/moo.fx.js" type="text/javascript"></script>
	<script src="script/admin/moo.fx.pack.js" type="text/javascript"></script>
	<style type="text/css">
		body {
			font:12px Arial, Helvetica, sans-serif;
			color: #000;
			background-color: #EEF2FB;
			margin: 0px;
		}
		#container {
			width: 182px;
		}
		H1 {
			font-size: 12px;
			margin: 0px;
			width: 182px;
			cursor: pointer;
			height: 30px;
			line-height: 20px;	
		}
		H1 a {
			display: block;
			width: 182px;
			color: #000;
			height: 30px;
			text-decoration: none;
			moz-outline-style: none;
			background-image: url(images/admin/menu_bgS.gif);
			background-repeat: no-repeat;
			line-height: 30px;
			text-align: center;
			margin: 0px;
			padding: 0px;
		}
		.content{
			width: 182px;
			height: 26px;
			
		}
		.MM ul {
			list-style-type: none;
			margin: 0px;
			padding: 0px;
			display: block;
		}
		.MM li {
			font-family: Arial, Helvetica, sans-serif;
			font-size: 12px;
			line-height: 26px;
			color: #333333;
			list-style-type: none;
			display: block;
			text-decoration: none;
			height: 26px;
			width: 182px;
			padding-left: 0px;
		}
		.MM {
			width: 182px;
			margin: 0px;
			padding: 0px;
			left: 0px;
			top: 0px;
			right: 0px;
			bottom: 0px;
			clip: rect(0px,0px,0px,0px);
		}
		.MM a:link {
			font-family: Arial, Helvetica, sans-serif;
			font-size: 12px;
			line-height: 26px;
			color: #333333;
			background-image: url(images/admin/menu_bg1.gif);
			background-repeat: no-repeat;
			height: 26px;
			width: 182px;
			display: block;
			text-align: center;
			margin: 0px;
			padding: 0px;
			overflow: hidden;
			text-decoration: none;
		}
		.MM a:visited {
			font-family: Arial, Helvetica, sans-serif;
			font-size: 12px;
			line-height: 26px;
			color: #333333;
			background-image: url(images/admin/menu_bg1.gif);
			background-repeat: no-repeat;
			display: block;
			text-align: center;
			margin: 0px;
			padding: 0px;
			height: 26px;
			width: 182px;
			text-decoration: none;
		}
		.MM a:active {
			font-family: Arial, Helvetica, sans-serif;
			font-size: 12px;
			line-height: 26px;
			color: #333333;
			background-image: url(images/admin/menu_bg1.gif);
			background-repeat: no-repeat;
			height: 26px;
			width: 182px;
			display: block;
			text-align: center;
			margin: 0px;
			padding: 0px;
			overflow: hidden;
			text-decoration: none;
		}
		.MM a:hover {
			font-family: Arial, Helvetica, sans-serif;
			font-size: 12px;
			line-height: 26px;
			font-weight: bold;
			color: #006600;
			background-image: url(images/admin/menu_bg2.gif);
			background-repeat: no-repeat;
			text-align: center;
			display: block;
			margin: 0px;
			padding: 0px;
			height: 26px;
			width: 182px;
			text-decoration: none;
		}
	</style>
</head>
<body>
	<table width="100%" height="280" border="0" cellpadding="0" cellspacing="0" bgcolor="#EEF2FB">
  <tr>
    <td width="182" valign="top">
    <div id="container">
	      <h1 class="type"><a href="javascript:void(0)">博文管理</a></h1>
	      <div class="content">
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td><img src="images/admin/menu_topline.gif" width="182" height="5" /></td>
	          </tr>
	        </table>
	        <ul class="MM">
	          <li><a href="admin/article/list.html" target="main">列表</a></li>
	          <li><a href="admin/article/write.html" target="main">写点什么</a></li>
	        </ul>
	      </div>
	      <h1 class="type"><a href="javascript:void(0)">回复管理</a></h1>
	      <div class="content">
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td><img src="images/admin/menu_topline.gif" width="182" height="5" /></td>
	          </tr>
	        </table>
	        <ul class="MM">
	          <li><a href="admin/reply/list.html" target="main">回复列表</a></li>
	        </ul>
	      </div>
	      <h1 class="type"><a href="javascript:void(0)">类别管理</a></h1>
	      <div class="content">
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td><img src="images/admin/menu_topline.gif" width="182" height="5" /></td>
	          </tr>
	        </table>
	        <ul class="MM">
	          <li><a href="admin/category/list.html" target="main">类别列表</a></li>
	          <li><a href="admin/category/add.html" target="main">添加类别</a></li>
	        </ul>
	      </div>
	      <h1 class="type"><a href="javascript:void(0)">标签管理</a></h1>
	      <div class="content">
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td><img src="images/admin/menu_topline.gif" width="182" height="5" /></td>
	          </tr>
	        </table>
	        <ul class="MM">
	          <li><a href="admin/tag/list.html" target="main">标签列表</a></li>
	          <li><a href="admin/tag/add.html" target="main">增加标签</a></li>
	        </ul>
	      </div>
	      <h1 class="type"><a href="javascript:void(0)">链接管理</a></h1>
	      <div class="content">
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td><img src="images/admin/menu_topline.gif" width="182" height="5" /></td>
	          </tr>
	        </table>
	        <ul class="MM">
	          <li><a href="admin/link/list.html" target="main">链接列表</a></li>
	          <li><a href="admin/link/add.html" target="main">增加链接</a></li>
	        </ul>
	      </div>
	      <h1 class="type"><a href="javascript:void(0)">留言板</a></h1>
	      <div class="content">
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td><img src="images/admin/menu_topline.gif" width="182" height="5" /></td>
	          </tr>
	        </table>
	        <ul class="MM">
	          <li><a href="admin/message/update.html" target="main">管理</a></li>
	        </ul>
	      </div>
	      <h1 class="type"><a href="javascript:void(0)">关于</a></h1>
	      <div class="content">
	        <table width="100%" border="0" cellspacing="0" cellpadding="0">
	          <tr>
	            <td><img src="images/admin/menu_topline.gif" width="182" height="5" /></td>
	          </tr>
	        </table>
	        <ul class="MM">
	          <li><a href="admin/about/update.html" target="main">关于我们</a></li>
	        </ul>
	      </div>
      </div>
        <script type="text/javascript">
		var contents = document.getElementsByClassName('content');
		var toggles = document.getElementsByClassName('type');
	
		var myAccordion = new fx.Accordion(
			toggles, contents, {opacity: true, duration: 400}
		);
		myAccordion.showThisHideOpen(contents[0]);
	</script>
        </td>
  </tr>
</table>
</body>
</html>
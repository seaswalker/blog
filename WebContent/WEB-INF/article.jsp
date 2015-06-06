<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
    <link rel="stylesheet" href="css/article.css">
    <link rel="shortcut icon" href="ico/icon.ico">
    <!--引入日历控件-->
    <link rel="stylesheet" href="css/calendar.css">
    <script src="script/calendar.js"></script>
    <script src="script/ajax.js"></script>
    <script src="script/ListenerUtil.js"></script>
    <script src="script/reply.js"></script>
    <!-- 代码高亮 -->
    <link type="text/css" rel="stylesheet" href="syntaxhighlighter/styles/shCore.css"/>
	<link type="text/css" rel="stylesheet" href="syntaxhighlighter/styles/shThemeDefault.css"/>
	<script type="text/javascript" src="syntaxhighlighter/scripts/shCore.js"></script>
	<script type="text/javascript" src="syntaxhighlighter/scripts/shBrushes.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href = "<%=basePath%>">
	<title>博文浏览</title>
	<script type="text/javascript">
		//添加事件处理
		ListenerUtil.addListener(window, "load", startUp, false);
		
		//启动函数
		function startUp() {
			//加载评论列表
			var aid = document.getElementById("aid").value;
			getResponseData("reply/list.html?aid=" + aid, false, true, showComments);
		}
		SyntaxHighlighter.config.clipboardSwf = 'syntaxhighlighter/scripts/clipboard.swf';
	    SyntaxHighlighter.all();
	</script>
</head>
<body>
		<jsp:include page="share/head.jsp"></jsp:include>
        <!--以上是头部-->
        
        <!--主体div-->
        <div class="main">
            <!--左侧-->
            <jsp:include page="share/left.jsp"></jsp:include>
            <!--右侧-->
            <div class="article-right">
                <!--标题-->
                <div class="article-title">
					${article.title}
                </div>
                <!--日期和作者-->
                <div class="article-author-date">
                   <span class="article-date"><fmt:formatDate value="${article.createTime}" pattern="yyyy-MM-dd"/></span>
                   &nbsp;&nbsp;
                   <span class="article-author">skywalker</span>
                </div>
                <div class="article-content">
                    ${article.content}
                </div>
                <!--标签-->
                <div class="article-tag">
                   	 标签：
                    <!--没有链接的标签 -->
                    ${article.tagsStr}
                </div>
                <!--上下篇博文-->
                <div class="article-prenext">
                	<c:if test="${pre == null}">
	                    &lt;上一篇
                	</c:if>
                	<c:if test="${pre != null}">
	                    <a href = "article.html?id=${pre.id}">&lt;上一篇</a>
                	</c:if>
                    &nbsp;|&nbsp;
                    <c:if test="${next == null}">
	                   	 下一篇&gt;
                	</c:if>
                	<c:if test="${next != null}">
	                    <a href = "article.html?id=${next.id}">下一篇&gt;</a>
                	</c:if>
                </div>
                <!--评论区-->
                <div class="article-comments">
                    <!--标题-->
                    <div class="comment-title">
                      	  评论：
                    </div>
                    <!--评论迭代开始-->
                    <div class="comment" id="comments">
                    </div>
                    <div class="reply-title">
                    	雁过留名：
                    </div>
                    <!--回复表单-->
                    <div class="reply-form">
                        <form action="" method="post" id="reply_form">
                        	<!-- 提交博文id -->
                        	<input type="hidden" name="aid" value="${article.id}" id="aid">
                        	<!-- 防止js代码出错，无实际意义 -->
                        	<input type="hidden" name="mid" value="" id="mid">
                        	<!-- 提交父评论id，有js动态指定 -->
                        	<input type="hidden" name="pid" id="pid">
                            <table>
                                <tr>
                                    <td>您的昵称 : </td>
                                    <td>
                                        <input type="text" name="nickname">
                                        &nbsp;&nbsp;<span class="reply-error"></span>
                                    </td>
                                </tr>
                                <tr>
                                    <td>您的邮箱 : </td>
                                    <td>
                                        <input type="text" name="email">
                                        &nbsp;&nbsp;<span class="reply-error"></span>
                                    </td>
                                </tr>
                                <tr>
                                    <td>个人主页 : </td>
                                    <td>
                                        <input type="text" name="homepage">(可选)
                                    </td>
                                </tr>
                                <tr>
                                    <td valign="top">contents : </td>
                                    <td>
                                        <textarea name="content" cols="60" rows="5" id="content"></textarea>
                                        &nbsp;&nbsp;<span class="reply-error"></span>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="2" style="text-align:center;padding-top:5px;">
                                        <input type="button" value="发表评论" onclick="submitForm();">                                
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
            <!-- 使父div有高度 -->
            <div style="clear: both;"></div>
        </div>
        <!--主体结束-->
        
       <!-- 引入尾巴 -->
	   <jsp:include page="share/foot.jsp"></jsp:include>
       
</body>
</html>
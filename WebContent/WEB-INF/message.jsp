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
    <link rel="stylesheet" href="css/article.css">
    <link rel="shortcut icon" href="ico/icon.ico">
    <!--引入日历控件-->
    <link rel="stylesheet" href="css/calendar.css">
    <script src="script/calendar.js"></script>
    <script src="script/ajax.js"></script>
    <script src="script/ListenerUtil.js"></script>
    <script src="script/reply.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<base href = "<%=basePath%>">
	<title>留言板</title>
	<script type="text/javascript">
		//添加事件处理
		ListenerUtil.addListener(window, "load", startUp, false);
		
		//启动函数
		function startUp() {
			//加载评论列表
			var mid = document.getElementById("mid").value;
			getResponseData("reply/list.html?mid=" + mid, false, true, showComments);
		}
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
					留言板：
                </div>
                <div class="article-content">
                	${message.content}
                </div>
                <!--评论区-->
                <div class="article-comments" style="margin-top:20px;">
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
                        <form action="" method="post">
                        	<!-- 提交留言id -->
                        	<input type="hidden" name="mid" value="${message.id}" id="mid">
                        	<input type="hidden" name="aid" value="" id="aid">
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
            <div style="clear: both;"></div>
        </div>
        <!--主体结束-->
        
        <!-- 引入尾巴 -->
        <jsp:include page="share/foot.jsp"></jsp:include>
</body>
</html>
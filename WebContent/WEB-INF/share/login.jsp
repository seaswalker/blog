<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<script src="${pageContext.request.contextPath}/script/login.js"></script>
</head>
<body>
	<!-- 模态窗口 -->
    <div class="model-content" id="logincontent">
    	<div class="content-title">
    		欢迎回来：
            <img src="images/close.png" style="float:right;margin-right:5px;margin-top:3px;cursor:pointer;" onclick="closeLogin();">
    	</div>
    	<div class="table">
            <div>
                	用户名：
                <input type="text" id="username" class="table-input">
            </div>
            <div>
             	   密　码：
                <input type="password" id="password" class="table-input">
            </div>
            <div style="height:22px;">
                <div style="float:left;margin-top:0px;margin-left:85px;">
                  	  验证码：
                    <input type="text" id="verifycode" style="margin:0;padding:0;width:90px;">
                </div>
                <div style="float:left;margin:0;height:22px;">
                    <img src="image.jsp" height="22px;" onclick="changeVerifyCode(this);">
                </div>
            </div>
            <div style="margin-left:150px;float:left">
                <!--显示错误信息-->
                <span class="login-error" id="loginerror">${error}</span>
            </div>
            <div style="height:16px;margin-left:-40px;clear:both;">
                <input type="checkbox" name="rememberme" id="checkbox" style="margin:0;padding:0;margin-top:1.5px;">
                <label for="checkbox" style="font-size:12px;line-height:16px;">&nbsp;记住我</label>
                
            </div>
            <div style="margin-top:20px;">
                <input type="button" value="登&nbsp;录" onclick="login();">
            </div>
	    </div>
    </div>
    <!-- 黑色遮盖div -->
    <div class="model-shield" id="loginshield"></div>
</body>
</html>
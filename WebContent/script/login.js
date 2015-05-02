//显示出登录元素
function showLogin() {
    //登录内容元素
    var content = document.getElementById("logincontent");
    //登录遮罩
    var shield = document.getElementById("loginshield");
    content.style.display = "block";
    shield.style.display = "block";
}
//关闭登录元素
function closeLogin() {
    //登录内容元素
    var content = document.getElementById("logincontent");
    //登录遮罩
    var shield = document.getElementById("loginshield");
    content.style.display = "none";
    shield.style.display = "none";
}
//点击验证码改变数字
function changeVerifyCode(img) {
    img.src = "image.jsp?" + Math.random();
}
//登录
function login() {
	var username = document.getElementById("username");
    //错误显示
    var error = document.getElementById("loginerror");
    if(username.value == "") {
        error.innerHTML = "请输入用户名";
        username.focus();
        return;
    }
    var passowrd = document.getElementById("password");
    if(passowrd.value == "") {
        error.innerHTML = "请输入密码";
        passowrd.focus();
        return;
    }
    var verifyCode = document.getElementById("verifycode");
    if(verifyCode.value == "") {
        error.innerHTML = "请输入验证码";
        verifyCode.focus();
        return;
    }
    //获得是否记录
    var checkbox = document.getElementById("checkbox");
    var cb = checkbox.checked ? 1 : 0;
    //向服务器发送请求验证登录
    var xmlHq = createXMLHttpRequest();
    xmlHq.open("post", "login.html", true);
    xmlHq.onreadystatechange = function() {
        if(xmlHq.readyState == 4 && xmlHq.status == 200) {
            //转化成json，必须加上括号
            var json = eval("(" + xmlHq.responseText + ")")
            var result = json.result;
            var errorMsg = json.error;
            if(result == 0) {
                error.innerHTML = errorMsg;
            }else {
                //成功
                window.location.reload();
            }
        }
    }
    //post方式必须设置头信息
    xmlHq.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
    xmlHq.send("username=" + username.value + "&password=" + passowrd.value + "&verifyCode=" + verifyCode.value + "&cb=" + cb);
}

//创建异步请求对象
function createXMLHttpRequest() {
    var xmlHq;
    if(window.XMLHttpRequest) {
        xmlHq = new XMLHttpRequest();
    }else {
        //IE7以下
        xmlHq = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlHq;
}
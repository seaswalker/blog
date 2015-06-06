	//回复相关函数

    //显示评论
	function showComments(data) {
		//评论区容器
		var container = document.getElementById("comments");
        //首先清空容器
        container.innerHTML = "";
		var json = eval("(" + data + ")");
        //判断回复的数量
        if(json.length == 0) {
            container.innerHTML = "<span style='font-size:12px;'>还没有评论呦，赶紧抢沙发～</span>";
            return;
        }
        var pattern = new RegExp("\\[reply\](\\S+)\\[/reply\\](<br>)*");
		//开始递归处理
        handler(json, 0, 1, container, pattern);
	}

    //拿到指定父回复的子回复
    function getReplies(array, parent) {
        var children = new Array();
        var reply;
        for(var i = 0;i < array.length;i ++) {
            reply = array[i];
            if(reply.parent == parent) {
                children.push(reply);
            }
        }
        return children;
    }

    function handler(array, parent, level, container, pattern) {
        var replies = getReplies(array, parent);
        var reply;
        var newLevel = 0;
        for(var i = 0;i < replies.length;i ++) {
            reply = replies[i];
            //处理逻辑
            logic(reply, container, pattern, level);
            //递归
            newLevel = level + 1;
            handler(array, reply.id, newLevel, container, pattern);
        }
    }

    //获取margin-left的数值，产生缩进的效果，每一个level，数值加20
    function getPreCount(level) {
        var preStr = 0;
        for(var i = 1;i < level;i ++) {
            preStr += 20; 
        }
        return preStr;
    }

    //处理逻辑
    function logic(reply, container, pattern, level) {
        var reply, comment, div, commentAvatar, img, commentId, commentTime, commentContent, commentReply, span;
        //包含一个回复的div
        var replyDiv;
        //回复内容
        var content;
        //保存解析出来的nickname
        var name;
        //正则表达式匹配结果数组
        var matches;
        content = reply.content;
        replyDiv = document.createElement("div");
        //设置缩进效果
        replyDiv.style.marginLeft = getPreCount(level) + "px";
        div = document.createElement("div");
        div.style.height = "24px";
        div.style.marginTop = "10px";
        div.style.backgroundColor = "#DADADA";
        commentAvatar = document.createElement("div");
        commentAvatar.className = "comment-avatar";
        img = document.createElement("img");
        img.src = reply.headpath;
        commentId = document.createElement("div");
        commentId.className = "comment-id";
        //设置昵称以及回复信息
        if(content.substring(0, 7) == "[reply]") {
            matches = pattern.exec(content);
            name = matches[1];
            //去掉回复信息
            content = content.replace(pattern, "");
            commentId.innerHTML = "<font color='#8d8d8d'>Re " + name + "</font>&nbsp;&nbsp;&nbsp;" + reply.nickname;
        }else {
            commentId.innerHTML = reply.nickname;
        }
        commentAvatar.appendChild(img);
        div.appendChild(commentAvatar);
        div.appendChild(commentId);
        commentTime = document.createElement("div");
        commentTime.className = "comment-time";
        commentTime.innerHTML = reply.replytime.substring(0, 16);
        commentContent = document.createElement("div");
        commentContent.className = "comment-content";
        commentContent.innerHTML = content;
        commentReply = document.createElement("div");
        commentReply.className = "comment-reply";
        span = document.createElement("span");
        span.innerHTML = "回复";
        span.id = reply.id;
        span.name = reply.nickname;
        span.style.cursor = "pointer";
        span.onclick = function() {
            var content = document.getElementById("content");
            content.value = "[reply]" + this.name + "[/reply]\n";
            //设置父回复id隐藏域
            document.getElementById("pid").value = this.id;
            content.scrollIntoView();
            content.focus();
        };
        commentReply.appendChild(span);

        //加入评论div
        replyDiv.appendChild(div);
        replyDiv.appendChild(commentTime);
        replyDiv.appendChild(commentContent);
        replyDiv.appendChild(commentReply);
        
        //加入容器
        container.appendChild(replyDiv);
    }

    //表单提交
    function submitForm() {
        //获取第二个表单，第一个是搜索
        var form = document.getElementById("reply_form");
        if(check(form)) {
            //ajax提交评论
            var url = "reply/save.html";
            var aid = form.aid.value;
            var mid = form.mid.value;
            var pid = form.pid.value;
            var nickname = form.nickname.value;
            var email = form.email.value;
            var homepage = form.homepage.value;
            var content = form.content.value;
            var params = "aid=" + aid + "&mid=" + mid +  "&pid=" + pid + "&nickname=" + nickname + "&email=" + email + "&homepage=" + homepage + "&content=" + content;
            
            //必须是同步提交，否则很可能会导致再次查询在插入之前到达服务器
            ajax.post(url, false, params);
            
            //重新加载评论列表
            ajax.getResponseData("reply/list.html?aid=" + aid + "&mid=" + mid, false, true, showComments);
            
            //重置表单
            form.reset();
            //清除设置的pid
            form.pid.value = "";
        }
    }

	//检查评论表单函数
	function check(form) {
		var nickname = form.nickname;
		var email = form.email;
		var content = form.content;
		var span;
		if(nickname.value == "") {
			nickname.focus();
			span = nickname.nextElementSibling;
			span.innerHTML = "请输入您的昵称";
			return false;
		}
        if(email.value == "") {
            email.focus();
            span = email.nextElementSibling;
            span.innerHTML = "请输入您的电子邮箱";
            return false;
        }
        var pattern = new RegExp("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.[a-zA-Z0-9_-])+");
        if(!email.value.match(pattern)) {
        	email.focus();
            span = email.nextElementSibling;
            span.innerHTML = "电子邮箱格式非法";
            return false;
        }
        if(content.value == "") {
            content.focus();
            span = content.nextElementSibling;
            span.innerHTML = "请输入您的评论";
            return false;
        }
        //替换为html的换行(因为需要在div中显示)
        content.value = content.value.replace("\n", "<br>");
		return true;
	}
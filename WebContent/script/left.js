	//ajax加载主页面左侧内容
	//处理返回的日期json数组
	function dates(data) {
		var jsonArray = eval("(" + data + ")");
		var ul = document.getElementById("dates");
		ul.innerHTML = "";
		//console.log(jsonArray.length);
		var li;
		var a;
		var year, month;
		for(var i = 0;i < jsonArray.length;i ++) {
			year = jsonArray[i].year;
			month = jsonArray[i].month;
			li = document.createElement("li");
			a = document.createElement("a");
			a.href = "index.html?date=" + year + month;
			a.innerHTML = year + "年" + month + "月";
			li.appendChild(a);
			ul.appendChild(li);
		}
	}
	//处理返回的类别信息
	function category(data) {
		var jsonArray = eval("(" + data + ")");
		var ul = document.getElementById("category");
		ul.innerHTML = "";
		var li;
		var a;
		var id, name, count;
		for(var i = 0;i < jsonArray.length;i ++) {
			id = jsonArray[i].id;
			name = jsonArray[i].category;
			count = jsonArray[i].count;
			li = document.createElement("li");
			a = document.createElement("a");
			a.href = "index.html?cy=" + id;
			a.innerHTML = name + "(" + count + ")";
			li.appendChild(a);
			ul.appendChild(li);
		}
	}
	//处理标签json数组
	function tag(data) {
		var jsonArray = eval("(" + data + ")");
		var div = document.getElementById("tags");
		div.innerHTML = "";
		var a, id, name, txt;
		for(var i = 0;i < jsonArray.length;i ++) {
			id = jsonArray[i].id;
			name = jsonArray[i].name;
			a = document.createElement("a");
			a.href = "index.html?tag=" + id;
			a.innerHTML = name;
			div.appendChild(a);
			txt = document.createTextNode(" ");
			div.appendChild(txt);
		}
	}
	//处理链接
	function links(data) {
		var jsonArray = eval("(" + data + ")");
		var ul = document.getElementById("links");
		ul.innerHTML = "";
		var li;
		var a;
		var name, url;
		for(var i = 0;i < jsonArray.length;i ++) {
			name = jsonArray[i].name;
			url = jsonArray[i].url;
			li = document.createElement("li");
			a = document.createElement("a");
			a.href = url;
			a.target = "_blank";
			a.innerHTML = name;
			li.appendChild(a);
			ul.appendChild(li);
		}
	}
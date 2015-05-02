	//事件监听工具类
	//object是添加事件的对象,handler是指处理函数
	var ListenerUtil = {
		addListener : function(object, listenerName, handler,  useCapture) {
			if(object.addEventListener) {
				object.addEventListener(listenerName, handler, useCapture);
			}else {
				object.attachEvent("on" + listenerName, handler);
			}	
		}
	}
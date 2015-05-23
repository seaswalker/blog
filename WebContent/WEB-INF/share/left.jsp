<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%    
	String path = request.getContextPath();    
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
%>
<html>
<head>
	<base href = "<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
	<title>left area</title>
	<script src = "script/ajax.js"></script>
	<script src = "script/left.js"></script>
	<script type="text/javascript">
		window.onload = function() {
			getResponseData("ajax/dates.html", true, false, dates);
			getResponseData("ajax/cy.html", true, false, category);
			getResponseData("ajax/tag.html", true, false, tag);
			getResponseData("ajax/links.html", true, false, links);
			getResponseData("weather.html", true, false, weather);
		}
		//搜索
		function search() {
			var content = document.getElementById("search").value;
			if(content.trim() == "") {
				alert("请输入搜索内容");
				return false;
			}
			window.open("search.html?search=" + content, "搜索");
			return true;
		}
	</script>
</head>
<body>
	<div class="main-left">
       <!--搜索框-->
       <div class="left-search left-border">
           <div class="search">
               <input type="text" id="search" style="width:150px;height:30px;">
               &nbsp;
               <input type="button" value="搜索" onclick="search();" style="width:50px;height:30px;">
           </div>
       </div>
       <!--日历-->
       <div class="left-calendar left-border">
           <div class="Calendar" style="margin:0 auto;width:230px;"> 
               <div id="idCalendarPre">&lt;&lt;</div> 
               <div id="idCalendarNext">&gt;&gt;</div> 
               <span id="idCalendarYear">2008</span>年         
               <span id="idCalendarMonth">8</span>月 
               <table cellspacing="0"> 
               <thead> 
               <tr> 
               <td>日</td> 
               <td>一</td> 
               <td>二</td> 
               <td>三</td> 
               <td>四</td> 
               <td>五</td> 
               <td>六</td> 
               </tr> 
               </thead> 
               <tbody id="idCalendar"> 
               </tbody> 
               </table> 
               </div> 
               <script type="text/javascript"> 
                   var cale = new Calendar("idCalendar", { 
                       SelectDay: new Date().setDate(10), 
                       onSelectDay: function(o){ o.className = "onSelect"; }, 
                       onToday: function(o){ o.className = "onToday"; }, 
                       onFinish: function(){ 
                           $("idCalendarYear").innerHTML = this.Year;                                                 $("idCalendarMonth").innerHTML = this.Month; 
                       } 
                   }); 
                   $("idCalendarPre").onclick = function(){ cale.PreMonth(); } 
                   $("idCalendarNext").onclick = function(){ cale.NextMonth(); } 
                   </script>
       </div>
       <!-- 天气预报 -->
       <div class="left-weather left-border">
       		今日天气<br>
       		<span class="weather_data" id="weather_report">正在加载...</span>
       </div>
       <!--按日期分类-->
       <div class="left-date left-border">
           <!--标题-->
             <div class="left-title">
				档案:
             </div>
             <ul class="left-ul" id = "dates">
                	<font style="font-size: 14px;">正在加载...</font>
             </ul>
         </div>
         <!--按类别分类-->
       <div class="left-border">
           <!--标题-->
             <div class="left-title">
				类别:
             </div>
             <ul class="left-ul" id = "category">
                 <font style="font-size: 14px;">正在加载...</font>
             </ul>
         </div>
         <!--标签-->
        <div class="left-border">
            <div class="left-title">
				标签:
            </div>
            <div class="left-tags" id = "tags">
                <font style="font-size: 14px;">正在加载...</font>
            </div>
        </div>
        <!--常用连接-->
       <div style="clear:both;margin-bottom: 30px;">
             <div class="left-title">
				常用链接:
             </div>
             <ul class="left-ul" id = "links">
                 <font style="font-size: 14px;">正在加载...</font>
           </ul>
       </div>
   </div>
</body>
</html>
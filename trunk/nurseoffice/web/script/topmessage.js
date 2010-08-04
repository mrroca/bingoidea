function getMessage() {
	$.ajax( {
		url : "requestTodayYYVisitService.action",
		type : "post",
		dataType : "json",
		success : update_page
	});
}
function update_page(data) {
	var jsonArray = eval("(" + data + ")");
	var htm = "";
	var num = jsonArray.length;
	for ( var one in jsonArray) {
		var s = "";
		if(jsonArray[one].brxb=="1"){
			s="男";
		}else if(jsonArray[one].brxb=="2"){
			s="女";
		}else {
			s="不详";
		}
		htm +="<li><img src='image/new.gif' border='0' with=25 height=12>上门服务提示：今日共("+num+")人&nbsp;&nbsp;&nbsp;&nbsp;<a href='visitserviceyuyuelist.html' target='mainFrame'>"+jsonArray[one].brxm+"</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='visitserviceyuyuelist.html' target='mainFrame'>"+s+"</a></li>";
	}
	$("#liyy").html(htm);
}
setTimeout(getMessage,1000);
setInterval(getMessage, 60000);

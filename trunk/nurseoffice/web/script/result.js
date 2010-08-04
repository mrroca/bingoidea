$(document).ready(function() {

	// 直接把onclick事件写在了JS中
		// 序列化表单的值
		// alert(params);
		$.ajax( {

			// 后台处理程序
			url : "getAllUser.action",

			// 数据发送方式
			type : "post",

			// 接受数据格式
			dataType : "json",

			// 要传递的数据

			// 回传函数
			success : update_page

		});

	});
function update_page(result) {
	alert(result);
	var json = eval("(" + result + ")");
	var s = "";
	alert(json.root.length);
	$.each(json.root, function(i, temp) {
		if (i == 0)
			return;
		s = s + "<li>用户名:" + temp.userName + "<br/> 密码:" + temp.password
				+ "</li>";
	});
	// var str = "用户名:" + json.userName + "<br />";
	// str += "密码:" + json.password + "<br />";
	// alert(str);
	$("#result").html(s);

}
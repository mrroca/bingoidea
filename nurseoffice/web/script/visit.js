$(document)
		.ready(
				function() {
					var htm = "";
					htm += "<form>";
					htm += "<table id='visitservicetable'>";
					htm += "<tr>";
					htm += "<td>姓名：<input name='brxm' type='text' size='40' value='' style='width:150px;border:#d6d6d6 1px solid;background-color: transparent;'><input type='button' name='query' value='查询' onClick='update_result()'></td>";
					htm += "</tr>";
					htm += "</table>";
					htm += "</form>";
					$("#result").html(htm);
					var str = "";
					str += "<table id='patienttable'>";
					str +="<tr><td>姓名</td><td>性别</td><td>出生日期</td><td>家庭电话</td><td>家庭地址</td><td>操作</td></tr>";
					str +="<tr><td colspan=6 align=center><a href='visitserviceluru.html'>新增</a></td></tr>";
					str +="</table>";
					$("#result1").html(str);
					$("input[name='brxm']").attr("value", "请输入患者姓名");

					$("input[name='brxm']").focus(function() {
						if ($(this).attr("value") == "请输入患者姓名") {
							$("input[name='brxm']").attr("value", "");
						}
						;

					});
					$("input[name='brxm']").blur(function() {
						if ($(this).attr("value") == "") {
							$("input[name='brxm']").attr("value", "请输入患者姓名");
						}
						;
					});
				});
function update_page(result) {
	var str = "<table id='patienttable'><tr><td>姓名</td><td>性别</td><td>出生日期</td><td>家庭电话</td><td>家庭地址</td><td>操作</td></tr>";
	var jsonArray = eval("(" + result + ")");
	if(jsonArray.length==0){
		str +="<tr><td colspan=6 align=center><a href='visitserviceluru.html'>新增</a></td></tr>";
	}
	for ( var one in jsonArray) {
		var s = "";
		if (jsonArray[one].brxb == "1") {
			s = "男";
		} else if (jsonArray[one].brxb == "2") {
			s = "女";
		} else {
			s = "不详";
		}
		str += "<tr id='cfrow'>";
		str += "<td>" + jsonArray[one].brxm + "</td>";
		str += "<td>" + s + "</td>";
		str += "<td>" + jsonArray[one].csny + "</td>"
		str += "<td>" + jsonArray[one].jtdh + "</td>";
		str += "<td>" + jsonArray[one].hkdz + "</td>";
		str += "<td><a href='#' onClick=\"luru('" + jsonArray[one].ybkh
				+ "')\">录入</a></td>";
		str += "</tr>";
	}
	if(jsonArray.length>0){
		str +="<tr><td colspan=6 align=center><input type='button' name='query' value='首页' onClick=''><input type='button' name='query' value='上一页' onClick=''><input type='button' name='query' value='下一页' onClick=''><input type='button' name='query' value='尾页' onClick=''></td></tr>";
	}
	str += "</table>";
	$("#result1").html(str);
}
function print() {
}
function luru(data){
	alert(data);
	var select = "";
	select += "<div id='outerdiv' style='border:1px solid #C0C0C0;width:82px;height:19px;clip:rect(0px,181px,18px,0px);overflow:hidden;'>";
	select += "<div id='innerdiv' style='border:1px solid #F4F4F4;width:80px;height:17px;clip:rect(0px,179px,16px,0px);overflow:hidden;'>";
	select += "<select name='brxb' id='sex' style='position:relative;float:left;clear:right;left:-2px;top:-2px;font-size:11px;width:83px;line-height:14px;border:0px;color:#00000;'>";
	select += "<option value='0'>请选择</option>";
	select += "<option value='1'>男</option>";
	select += "<option value='2'>女</option>";
	select += "<option value='3'>不详</option>";
	select += "</select>";
	select += "</div>";
	select += "</div>";
	var htm = "";
	htm += "<form>";
	htm += "<table id='visitservicetable'>";
	htm += "<tr>";
	htm += "<td>姓名：<input name='brxm' type='text' size='20' value='' style='width:60%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>";
	htm += "<td style='magin:0px;padding:0px;width:20%;'><div id='selectbox'><div id='sexlabel'>性别：</div>"
			+ select + "</div></td>";
	htm += "<td>联系电话：<input name='lxdh' type='text' size='20' value='' style='width:60%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>";
	htm += "<td>医保卡号：<input name='ybkh' type='text' size='20' value='' style='width:60%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>";
	htm += "</tr>";
	htm += "<tr>";
	htm += "<td colspan=4>家庭地址：<input name='jtdz' type='text' size='80' value='' style='width:80%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>";
	htm += "</tr>";
	htm += "<tr>";
	htm += "<td colspan=4><textarea name='content' cols ='100%' rows='5' style='border:none;overflow:hidden'></textarea></td>";
	htm += "</tr>";
	htm += "<tr>";
	htm += "<td colspan=3>处理人：<span id='opt'><span></td><td><input type='button' name='submit' value='保存' onClick='save()'></td>";
	htm += "</tr>";
	htm += "</table>";
	htm += "</form>";
	$("#result2").html(htm);
	// $('#sex').sSelect();

	$("textarea[name='content']").attr("value", "请输入服务内容");
	$("textarea[name='content']").focus(function() {
		if ($(this).attr("value") == "请输入服务内容") {
			$("textarea[name='content']").attr("value", "");
		}
		;

	});
	$("textarea[name='content']").blur(
			function() {
				if ($(this).attr("value") == "") {
					$("textarea[name='content']").attr("value",
							"请输入服务内容");
				}
				;
			});

	if (data != null) {
		var param = data;

		param = "patient.ybkh=" + param.Trim();
		$.ajax( {
			url : "requestPatientBasic.action",
			type : "post",
			dataType : "json",
			data : param,
			success : update_page2
		});

	}
	;
	$.ajax( {
		url : "requestPersonInfo.action",
		type : "post",
		dataType : "json",
		success : update_opt
	});
}
function update_result() {
	if ($("input[name='brxm']").attr("value") == '请输入患者姓名') {
		alert($("input[name='brxm']").attr("value"));
		return false;
	} else {
		var param = $("input[name='brxm']").attr("value");
		param = "patient.patientname=" + param.Trim();
		$.ajax( {
			url : "requestPatientList.action",
			type : "post",
			dataType : "json",
			data : param,
			success : update_page
		});
	}
}
function update_page2(result) {
	var jsonArray = eval("(" + result + ")");

	for ( var one in jsonArray) {
		var s = "";
		if (jsonArray[one].brxb == "1") {
			s = "男";
		} else if (jsonArray[one].brxb == "2") {
			s = "女";
		} else {
			s = "不详";
		}
		$("input[name='brxm']").attr("value", jsonArray[one].brxm);
		$("select[name='brxb']").attr("value", jsonArray[one].brxb);
		$("input[name='lxdh']").attr("value", jsonArray[one].jtdh);
		$("input[name='jtdz']").attr("value", jsonArray[one].hkdz);
		$("input[name='ybkh']").attr("value", jsonArray[one].ybkh);
	}
}
function update_opt(result) {
	var jsonArray = eval("(" + result + ")");
	var nursestr = "";
	var m = 0;
	nursestr +="<select id='nurseselect' name='username'>";
	for( var one = 0; one < jsonArray.length; one++) {
		nursestr +="<option value='"+jsonArray[one].username+"'>"+jsonArray[one].name+"</option>";
	}
	nursestr +="</select>";
	$("#opt").html(nursestr);
}
function save() {
	// $("input[name='content']").attr("value");
	
	var brxm = $("input[name='brxm']").attr("value");
	var brxb = $("select[name='brxb']").attr("value");
	var lxdh = $("input[name='lxdh']").attr("value");
	var jtdz = $("input[name='jtdz']").attr("value");
	var ybkh = $("input[name='ybkh']").attr("value");
	var content = $("textarea[name='content']").attr("value");
	var user = $("select[name='username']").attr("value");
	
	if (brxm.length == 0 || brxm.Trim().length == 0) {
		alert("姓名为必填信息");
		return false;
	}
	if (brxb == "0" || brxb == 0) {
		alert("请选择性别");
		return false;
	}
	if (jtdz.length == 0 || jtdz.Trim().length == 0) {
		alert("家庭地址为必填信息");
		return false;
	}
	if (content == "请输入服务内容"||content.Trim().length == 0) {
		alert("请输入服务内容!");
		return false;
	}
	if (null == user || user.length == 0) {
		alert("请选择处理人");
		return false;
	}
	
	var param = "";
	param += "patient.patientname=" + brxm;
	param += "&patient.sex=" + brxb;
	param += "&patient.phone=" + lxdh;
	param += "&patient.address=" + jtdz;
	param += "&patient.remark=" + content;
	param += "&patient.ybkh=" + ybkh;
	param += "&patient.optid=" + user;
	//alert(param);
	$.ajax( {
		url : "requestSaveVisitService.action",
		type : "post",
		dataType : "json",
		data : param,
		success : update_result2
	});
}
function update_result2(data) {
	var results = data;
	results = eval("(" + results + ")");

	if (results[0].results == "success") {
		alert("保存成功");
		$("input").attr("readonly", "readonly");
		$("input[name='submit']").attr("disabled", true);
	} else {
		alert("保存失败");
	}
}
function isInteger(num) {
	var patrn = /^[1-9]*[1-9][0-9]*$/;

	if (!patrn.exec(num)) {
		return false;
	} else {
		return true;
	}
}
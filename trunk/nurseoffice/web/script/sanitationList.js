//var sanitationcontent = $.url.param("sanitationcontent");
//var sanitationid = $.url.param("sanitationid");
//var markdate = $.url.param("markdate");
//var sanitationcomplete = $.url.param("sanitationcomplete");
//var username = $.url.param("username");
//alert("sssssssss "+sanitationcontent);
//alert("uuuuuu "+username);
$(document)
		.ready(
				function() {
					var nowDate = new Date();
//					var select = "";
//					select += "<div id='outerdiv' style='border:1px solid #C0C0C0;width:82px;height:19px;clip:rect(0px,181px,18px,0px);overflow:hidden;'>";
//					select += "<div id='innerdiv' style='border:1px solid #F4F4F4;width:80px;height:17px;clip:rect(0px,179px,16px,0px);overflow:hidden;'>";
//					select += "<select name='brxb' id='sex' style='position:relative;float:left;clear:right;left:-2px;top:-2px;font-size:11px;width:83px;line-height:14px;border:0px;color:#00000;'>";
//					select += "<option value='99'>请选择</option>";
//					select += "<option value='0'>未知性别</option>";
//					select += "<option value='1'>男</option>";
//					select += "<option value='2'>女</option>";
//					select += "<option value='9'>未说明的性别</option>";
//					select += "</select>";
//					select += "</div>";
//					select += "</div>";
					var contentselect = "";
					contentselect += "<div id='outerdiv' style='border:1px solid #C0C0C0;width:82px;height:19px;clip:rect(0px,181px,18px,0px);overflow:hidden;'>";
					contentselect += "<div id='innerdiv' style='border:1px solid #F4F4F4;width:80px;height:17px;clip:rect(0px,179px,16px,0px);overflow:hidden;'>";
					contentselect += "<select name='sanitationcontent' id='sanitationcontent' style='position:relative;float:left;clear:right;left:-2px;top:-2px;font-size:11px;width:83px;line-height:14px;border:0px;color:#00000;'>";
					contentselect += "<option value='99'>请选择</option>";
					contentselect += "<option value='0'>卫生</option>";
					contentselect += "<option value='1'>消毒</option>";
					contentselect += "<option value='2'>口腔科消毒</option>";
					contentselect += "</select>";
					contentselect += "</div>";
					contentselect += "</div>";
					var htm = "";
					htm += "<form>";
					htm += "<table id='listservicetable'>";
					htm += "<tr bgcolor=#C5E1EF><td colspan=6 style='height:30px;font-weight:bold;font-size:14px' align='center'>操作信息录入</td></tr>";
//					htm += "<tr>";
//					htm += "<td>姓名：<input name='brxm' type='text' size='20' value='' style='width:60%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>";
//					htm += "<td style='magin:0px;padding:0px;width:20%;'><div id='selectbox'><div id='sexlabel'>性别：</div>"
//							+ select + "</div></td>";
//					htm += "<td>联系电话：<input name='lxdh' type='text' size='20' value='' style='width:60%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>";
//					htm += "<td>医保卡号：<input name='ybkh' type='text' size='20' value='' style='width:60%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>";
//					htm += "</tr>";
//					htm += "<tr>";
//					htm += "<td colspan=4>家庭地址：<input name='jtdz' type='text' size='80' value='' style='width:80%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>";
//					htm += "</tr>";
					htm += "<tr>";
					htm += "<td bgcolor='#cccccc'><b>*操作事项</b></td><td><div id='selectbox'><div id='sexlabel'></div>"
							+ contentselect + "</div></td>";
					htm += "<td bgcolor='#cccccc'><b>完成情况</b></td><td colspan=2 align=left ><input name='sanitationcomplete' type='text' size='20' style='width:60%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>";
					htm += "</tr>";
//					htm += "<tr>";
//					htm += "<td colspan=4>完成情况：<textarea name='sanitationcomplete' cols ='100%' rows='5' style='border:none;overflow:auto'></textarea></td>";
//					htm += "</tr>";
					htm += "<tr>";
					htm += "<td bgcolor='#cccccc'><b>操作时间</b></td><td align='left'><input name='time' type='text' size='20' value='"+nowDate.toLocaleDateString().split(" ", 1)+"' style='width:80%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;overflow:hidden'></td>";
					htm += "<td bgcolor='#cccccc'><b>操作人</b></td><td align='left' ><span id='opt'><span></td><td align=left><input type='button' name='submit' value='保存' style='width:80px' onClick='save()'></td>";
					htm += "</tr>";
					htm += "</table>";
//					var wholehtm = "";
//					wholehtm += "<form>";
//					wholehtm += "<table border='0' cellpadding='0' cellspacing='0' width='1143' height='557'>";
//					wholehtm += "<tr>";
//					wholehtm += "<td valign='top' colspan='3' height='61'>";
//					wholehtm += "<img border='0' src='image/ja_b_01.gif' width='256' height='61'><img border='0' src='image/ja_b_02.gif' width='619' height='61'><img border='0' src='image/ja_b_04.gif' width='269' height='61'></td>";
//					wholehtm += "</tr>";
//					wholehtm += "<tr>";
//					wholehtm += "<td valign='top' width='21'>";
//					wholehtm += "<img border='0' src='image/ja_b_07.gif' width='19' height='453'></td>";
//					wholehtm += "<td valign='top' width='1106'>";
//		　			wholehtm += "<div>"+htm+"</div>";
//					wholehtm += "</td>";
//					wholehtm += "<td valign='top' height='446' width='16'>";
//					wholehtm += "<img border='0' src='image/ja_b_10.gif' width='25' height='453'></td>";
//					wholehtm += "</tr>";
//					wholehtm += "<tr>";
//					wholehtm += "<td valign='top' colspan='3' height='50'>";
//					wholehtm += "<img border='0' src='image/ja_b_11.gif' width='19' height='19'><img border='0' src='image/ja_b_12.gif' width='1100' height='19'><img border='0' src='image/ja_b_14.gif' width='25' height='19'></td>";
//					wholehtm += "</tr>";
//					wholehtm += "</table>";
					htm += "</form>";
					$("#result").html(htm);
					// $('#sex').sSelect();

//					$("textarea[name='sanitationcontent']").attr("value", "请输入服务内容");
//					$("textarea[name='sanitationcontent']").focus(function() {
//						if ($(this).attr("value") == "请输入服务内容") {
//							$("textarea[name='sanitationcontent']").attr("value", "");
//						}
//						;
//
//					});
//					$("textarea[name='sanitationcontent']").blur(
//							function() {
//								if ($(this).attr("value") == "") {
//									$("textarea[name='sanitationcontent']").attr("value",
//											"请输入服务内容");
//								}
//								;
//							});

//					if ($.url.param("ybkh") != null) {
//						var param = $.url.param("ybkh");
//
//						param = "patient.ybkh=" + param.Trim();
//						$.ajax( {
//							url : "sanitationBasic.action",
//							type : "post",
//							dataType : "json",
//							data : param,
							
//						});

//					}
					;
					$.ajax( {
						url : "requestPersonInfo.action",
						type : "post",
						dataType : "json",
						success : update_opt
					});
					
				});
function update_page(result) {
//	alert("sssssssss "+$.url.param("sanitationcomplete"));
//	alert("mmmmmmmmm "+$.url.param("markdate"));
//	alert("uuuuuuuuuuuu  "+$.url.param("username"));
//	var jsonArray = eval("(" + result + ")");
//	var nowDate = new Date();

//		var s = "";
//		if ($.url.param("brxb") == "1") {
//			s = "男";
//		} else if ($.url.param("brxb") == "2") {
//			s = "女";
//		} else if ($.url.param("brxb") == "0"){
//			s = "未知性别";
//		}else if ($.url.param("brxb") == "9"){
//			s = "未说明的性别";
//		}
//		var c = "";
//		if($.url.param("czmc") == "1" || $.url.param("czmc") == 1){
//			c = "伤口处理/换药"
//		} else if($.url.param("czmc") == "2" || $.url.param("czmc") == 2){
//			c = "抽血/取样"
//		}
		$("select[name='username']").attr("value", $.url.param("username"));
		$("input[name='sanitationcomplete']").attr("value", $.url.param("sanitationcomplete"));
//		$("input[name='time']").attr("value", $.url.param("markdate"));
		$("select[name='sanitationcontent']").attr("value", $.url.param("sanitationcontent"));
//		alert("uuuuuuuuuuuu--------  "+$.url.param("username"));
		

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
	update_page();
}
function save() {
	
//	var brxm = $("input[name='brxm']").attr("value");
//	var brxb = $("select[name='brxb']").attr("value");
//	var lxdh = $("input[name='lxdh']").attr("value");
//	var jtdz = $("input[name='jtdz']").attr("value");
//	var ybkh = $("input[name='ybkh']").attr("value");
//	var sanitationcontent = $("textarea[name='sanitationcontent']").attr("value");
//	var user = $("input[name='username']:checked").val();
//	var time = $("input[name='time']").attr("value");
	var sanitationcontent = $("select[name='sanitationcontent']").attr("value");
	var sanitationcomplete = $("input[name='sanitationcomplete']").attr("value");
	var user = $("select[name='username']").attr("value");
	var sanitationid = $.url.param("sanitationid");
	
//	if (brxm.length == 0 || brxm.Trim().length == 0) {
//		alert("姓名为必填信息");
//		return false;
//	}
//	if (!isInteger(ybkh.Trim())) {
//		alert("请输入正确的医保卡号");
//		return false;
//	}
//	if (jtdz.length == 0 || jtdz.Trim().length == 0) {
//		alert("家庭地址为必填信息");
//		return false;
//	}
//	if (sanitationcontent == "请输入服务内容"||sanitationcontent.Trim().length == 0) {
//		alert("请输入服务内容!");
//		return false;
//	}
//	if (null == user || user.length == 0) {
//		alert("请选择完成人");
//		return false;
//	}
	if (sanitationcontent == "请选择操作事项"||sanitationcontent.Trim().length == 0 || sanitationcontent == "99" || sanitationcontent == 99) {
		alert("请选择操作事项");
		return false;
	}
	if (null == user || user.length == 0) {
		alert("请选择操作人");
		return false;
	}
	
	var param = "";
//	param += "patient.patientname=" + brxm;
//	param += "&patient.sex=" + brxb;
//	param += "&patient.phone=" + lxdh;
//	param += "&patient.address=" + jtdz;
//	param += "&patient.sanitationcontent=" + sanitationcontent;
//	param += "&patient.ybkh=" + ybkh;
	param += "patient.sanitationcontent=" + sanitationcontent;
	param += "&patient.sanitationcomplete=" + sanitationcomplete;
	param += "&patient.optid=" + user;
	param += "&patient.sanitationid=" + sanitationid;
	$.ajax( {
		url : "requestUpdateSanitationService.action",
		type : "post",
		dataType : "json",
		data : param,
		success : update_result
	});
}
function update_result(data) {
	var results = data;
	results = eval("(" + results + ")");
	if (results[0].results == "success") {
		alert("保存成功");
		$("input").attr("readonly", "readonly");
		$("input[name='submit']").attr("disabled", true);
		location.href='sanitation.html';
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

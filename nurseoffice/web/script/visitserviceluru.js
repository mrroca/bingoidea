$(document)
		.ready(function() {
					
					var select = "";
//					select += "<div id='outerdiv' style='border:1px solid #C0C0C0;width:82px;height:19px;clip:rect(0px,181px,18px,0px);overflow:hidden;'>";
//					select += "<div id='innerdiv' style='border:1px solid #F4F4F4;width:80px;height:17px;clip:rect(0px,179px,16px,0px);overflow:hidden;'>";
					select += "<select name='brxb' id='sex'>";
					select += "<option value='0'>请选择</option>";
					select += "<option value='1'>男</option>";
					select += "<option value='2'>女</option>";
					select += "<option value='3'>不详</option>";
					select += "</select>";
//					select += "</div>";
//					select += "</div>";
					var fl = "";
					fl += "<select name='fenlei' id='fenlei'>";
					fl += "<option value='0'>请选择</option>";
					fl += "<option value='1'>输液</option>";
					fl += "<option value='2'>注射</option>";
					fl += "<option value='3'>换药</option>";
					fl += "<option value='4'>拆线</option>";
					fl += "<option value='5'>导尿</option>";
					fl += "<option value='6'>其他</option>";
					fl += "</select>";
					var htm = "";
					htm += "<form>";
					htm += "<table id='visitservicetable'>";
					htm +="<tr bgcolor=#C5E1EF><td colspan=8 align=center style='height:30px;font-weight:bold;font-size:14px'>居民基本信息</td></tr>";
					htm += "<tr>";
					htm += "<td style='width:8%' align=center  bgcolor='#cccccc'>姓名</td><td style='width:10%'><input name='brxm' type='text' size='20' value='' style='width:60%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'><a href='#' onClick='read()'>读卡</a></td>";
					htm += "<td style='width:8%' align=center  bgcolor='#cccccc'>性别</td><td style='width:10%;'>"+ select + "</td>";
					htm += "<td style='width:8%' align=center  bgcolor='#cccccc'>联系电话</td><td style='width:10%'><input name='lxdh' type='text' size='20' value='' style='width:80%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'>&nbsp;</td>";
					htm += "<td style='width:8%' align=center  bgcolor='#cccccc'>医保卡号</td><td style='width:20%'><input name='ybkh' type='text' size='20' value='' style='width:80%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'>&nbsp;</td>";
					htm += "</tr>";
					htm += "<tr>";
					htm += "<td style='width:8%' align=center  bgcolor='#cccccc'>家庭地址</td><td colspan=5><input name='jtdz' type='text' size='80' value='' style='width:80%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'>&nbsp;</td><td style='width:8%' align=center  bgcolor='#cccccc'>分类</td><td>"+fl+"</td>";
					htm += "</tr>";
					htm +="</table>";
					htm +="<br/>";
					htm +="<br/>";
					htm +="<table id='visitservicetable2'>";
					htm +="<tr bgcolor=#C5E1EF><td colspan=4 align=center style='height:30px;font-weight:bold;font-size:14px'>医生上门服务信息录入</td></tr>";
					htm += "<tr>";
					htm += "<td colspan=4><textarea name='content' cols ='80%' rows='5' style='border:none;overflow:hidden'></textarea></td>";
					htm += "</tr>";
					htm += "<tr>";
					htm += "<td colspan=2 style='width:10%'  bgcolor='#cccccc' align=center>处理人</td><td><span id='opt'><span></td><td align=center><input type='button' name='submit' value='保存' onClick='save()'></td>";
					htm += "</tr>";
					htm += "</table>";
					htm += "</form>";
					$("#result").html(htm);
					// $('#sex').sSelect();
					$.ajax( {
						url : "requestPersonInfo.action",
						type : "post",
						dataType : "json",
						success : update_opt
					});
					
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

					if ($.url.param("id") != null) {
						var param = $.url.param("id");

						param = "patient.id=" + param.Trim();
						$.ajax( {
							url : "requestVisitServiceByCond.action",
							type : "post",
							dataType : "json",
							data : param,
							success : update_page2
						});

					}
					;
					//read();
				});
function update_page2(result) {
	var jsonArray = eval("(" + result + ")");


		var s = "";
		if (jsonArray[0].sex == "1") {
			s = "男";
		} else if (jsonArray[0].sex == "2") {
			s = "女";
		} else {
			s = "不详";
		}
		$("input[name='brxm']").attr("value", jsonArray[0].name);
		$("select[name='brxb']").attr("value", jsonArray[0].sex);
		$("select[name='username']").attr("value", jsonArray[0].userid);
		$("input[name='lxdh']").attr("value", jsonArray[0].phone);
		$("input[name='jtdz']").attr("value", jsonArray[0].address);
		$("input[name='ybkh']").attr("value", jsonArray[0].ybkh);
		//alert("123123213");
		if(jsonArray[0].kind.length==0||jsonArray[0].kind==""){
			$("select[name='fenlei']").attr("value", "0");
		}else{
			$("select[name='fenlei']").attr("value", jsonArray[0].kind);
		}
		
		$("textarea[name='content']").attr("value",jsonArray[0].content);
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
	var fenlei = $("select[name='fenlei']").attr("value");
	var content = $("textarea[name='content']").attr("value");
	var user = $("select[name='username']").attr("value");
	var flag = $.url.param("flag");
	var id = $.url.param("id");
	
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
	if (fenlei == "0" || fenlei == 0) {
		alert("请选择分类");
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
	param += "&patient.kind=" + fenlei;
	if(flag!=null&&(flag=='1'||flag==1)){
		param +="&patient.flag=1";
		param +="&patient.id="+id;
	}else{
		param +="&patient.flag=0";
		param +="&patient.id=null";
	}
	//alert(param);
	$.ajax( {
		url : "requestSaveVisitService.action",
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
		location.href='visitservicelist.html';
	} else {
		alert("保存失败");
	}
}
function read(){
//	document.onkeydown = function(event) {
//		var e = event || window.event;
//		var keyCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
//		if (keyCode == 0x0D) {
			readcard();
//		}
//	}
}
function update_basicinfo(data){
	//alert(data);
	var jsonArray = eval("(" + data + ")");

		$("input[name='brxm']").attr("value", jsonArray[0].brxm);
		$("select[name='brxb']").attr("value", jsonArray[0].brxb);
		$("input[name='lxdh']").attr("value", jsonArray[0].lxdh);
		$("input[name='jtdz']").attr("value", jsonArray[0].hkdz);
		$("input[name='ybkh']").attr("value", jsonArray[0].ybkh);

}
function isInteger(num) {
	var patrn = /^[1-9]*[1-9][0-9]*$/;

	if (!patrn.exec(num)) {
		return false;
	} else {
		return true;
	}
}
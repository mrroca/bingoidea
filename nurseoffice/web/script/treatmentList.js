var jc = 1;
$(document)
		.ready(
				function() {
					var nowDate = new Date();
					var select = "";
					select += "<div id='outerdiv' style='border:1px solid #C0C0C0;width:102px;height:19px;clip:rect(0px,181px,18px,0px);overflow:hidden;'>";
					select += "<div id='innerdiv' style='border:1px solid #F4F4F4;width:100px;height:17px;clip:rect(0px,179px,16px,0px);overflow:hidden;'>";
					select += "<select name='brxb' id='sex' style='position:relative;float:left;clear:right;left:-2px;top:-2px;font-size:11px;width:103px;line-height:14px;border:0px;color:#00000;'>";
					select += "<option value='99'>请选择</option>";
					select += "<option value='0'>未知性别</option>";
					select += "<option value='1'>男</option>";
					select += "<option value='2'>女</option>";
					select += "<option value='9'>未说明的性别</option>";
					select += "</select>";
					select += "</div>";
					select += "</div>";
					var hyselect = "";
					hyselect += "<div id='outerdiv' style='border:1px solid #C0C0C0;width:102px;height:19px;clip:rect(0px,181px,18px,0px);overflow:hidden;'>";
					hyselect += "<div id='innerdiv' style='border:1px solid #F4F4F4;width:100px;height:17px;clip:rect(0px,179px,16px,0px);overflow:hidden;'>";
					hyselect += "<select name='brhy' id='sex' style='position:relative;float:left;clear:right;left:-2px;top:-2px;font-size:11px;width:103px;line-height:14px;border:0px;color:#00000;'>";
					hyselect += "<option value='99'>请选择</option>";
					hyselect += "<option value='10'>未婚</option>";
					hyselect += "<option value='20'>已婚</option>";
					hyselect += "<option value='21'>初婚</option>";
					hyselect += "<option value='22'>再婚</option>";
					hyselect += "<option value='23'>复婚</option>";
					hyselect += "<option value='30'>丧婚</option>";
					hyselect += "<option value='40'>离婚</option>";
					hyselect += "<option value='90'>未说明的婚姻情况</option>";
					hyselect += "</select>";
					hyselect += "</div>";
					hyselect += "</div>";
					var treatmentSelect = "";
					treatmentSelect += "<div id='outerdiv' style='border:1px solid #C0C0C0;width:102px;height:19px;clip:rect(0px,181px,18px,0px);overflow:hidden;'>";
					treatmentSelect += "<div id='innerdiv' style='border:1px solid #F4F4F4;width:100px;height:17px;clip:rect(0px,179px,16px,0px);overflow:hidden;'>";
					treatmentSelect += "<select name='czmc' id='sex' style='position:relative;float:left;clear:right;left:-2px;top:-2px;font-size:11px;width:103px;line-height:14px;border:0px;color:#00000;'>";
					treatmentSelect += "<option value='0'>请选择</option>";
					treatmentSelect += "<option value='1'>换药</option>";
					treatmentSelect += "<option value='2'>拆线</option>";
					treatmentSelect += "<option value='3'>缝合</option>";
					treatmentSelect += "<option value='9'>其他</option>";
					treatmentSelect += "</select>";
					treatmentSelect += "</div>";
					treatmentSelect += "</div>";
					var basic="<table id='basic'>";
					basic += "<tr bgcolor=#C5E1EF><td colspan=6 style='height:30px;font-weight:bold;font-size:14px' align='center'>居民基本信息</td></tr>";
					basic += "<tr><td bgcolor='#cccccc'><b> *姓     名 </b></td><td width=24%><input name='brxm' type='text' size='20' value='' style='width:100%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>";
					basic += "<td bgcolor='#cccccc'><b> *性     别 </b></td><td width=24%><div id='selectbox'><div id='sexlabel'></div>"+ select + "</div></td>"
					basic += "<td bgcolor='#cccccc'><b> 年     龄 </b></td><td width=24% align=left><input name='brnl' type='text' size='20' value='' style='width:80%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td></tr>";
					basic += "<tr><td bgcolor='#cccccc'><b>家庭地址</b></td><td width=24%><input name='jtdz' type='text' size='20' value='' style='width:100%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>"
					basic += "<td bgcolor='#cccccc'><b>联系电话</b></td><td width=24%><input name='lxdh' type='text' size='20' value='' style='width:100%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>"
					basic += "<td bgcolor='#cccccc'><b> 婚     姻 </b></td><td width=24%><div id='hybox'><div id='hylabel'></div>"+ hyselect + "</div></td></tr>"
					basic += "<tr><td bgcolor='#cccccc'><b>过敏史</b></td><td width=24%><input name='gms' type='text' size='20' value='' style='width:100%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>"
					basic += "<td bgcolor='#cccccc'><b> 血     型 </b></td><td width=24%><input name='xx' type='text' size='20' value='' style='width:100%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>"
					basic += "<td bgcolor='#cccccc'><b>门诊诊断</b></td><td width=24% align=left><input name='mzzd' type='text' size='20' value='' style='width:80%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td></tr>"
					basic +="</table>";
					var htm = "";
//					htm+="<div><input id='ybkh' name='ybkh' type='hidden' value='' size='5' class='xhx'>&nbsp;&nbsp;&nbsp;&nbsp;";
//					htm+="<div><input id='ybkh' name='ybkh' type='hidden' value='' size='5' class='xhx'>&nbsp;&nbsp;&nbsp;&nbsp;";
//					htm+="<font size='3'><b>姓名</b>：</font><input id='brxm' name='brxm' value='' size='15' class='xhx'>&nbsp;&nbsp;&nbsp;&nbsp;";
//					htm+="<font size='3'><b>性别</b>：</font><input id='brxb' name='brxb' value='' size='15' class='xhx'>&nbsp;&nbsp;&nbsp;&nbsp;";
//					htm+="<font size='3'><b>年龄</b>：</font><input id='brnl' name='brnl' value='' size='10' class='xhx'>&nbsp;&nbsp;&nbsp;&nbsp;";
//					htm+="<font size='3'><b>婚姻</b>：</font><input id='brhy' name='brhy' value='' size='14' class='xhx'>&nbsp;&nbsp;&nbsp;&nbsp;</div>";
//					
//					htm+="<div><font size='3'><b>家庭地址</b>：</font><input id='jtdz' name='jtdz' value='' size='55' class='xhx'>&nbsp;&nbsp;&nbsp;&nbsp;";
//					htm+="<font size='3'><b>联系电话</b>：</font><input id='lxdh' name='lxdh' value='' size='20' class='xhx'>&nbsp;&nbsp;&nbsp;&nbsp;</div>";
//					
//					htm+="<div><font size='3'><b>过敏史</b>：</font><input id='gms' name='gms' value='' size='27' class='xhx'>&nbsp;&nbsp;&nbsp;&nbsp;";
//					htm+="<font size='3'><b>血型</b>：</font><input id='xx' name='xx' value='' size='5' class='xhx'>&nbsp;&nbsp;&nbsp;&nbsp;";
//					htm+="<font size='3'><b>门诊诊断</b>：</font><input id='mzzd' name='mzzd' value='' size='30' class='xhx'>&nbsp;&nbsp;&nbsp;&nbsp;</div><br><hr align='center' width='80%'><br>";
					htm += "<form>";
					htm += basic;
					htm += "<br/>";
					htm += "<br/>";
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
					htm += "<td bgcolor='#cccccc'><b> 时     间 </b></td><td width=24%><input name='time' type='text' size='20' value='"+nowDate.toLocaleDateString().split(" ", 1)+"' style='width:100%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>";
					htm += "<td bgcolor='#cccccc'><b>*操作名称</b></td><td width=24%><div id='selectbox'><div id='treatmentName'></div>"
						+ treatmentSelect + "</div></td>";
					htm += "<td bgcolor='#cccccc'><b> *计     次 </b></td><td width=24% align=left><a href=# onClick='add()' ondblclick='add()'><img src='image/036.png' style='width:30px;height:30px' align='middle'></img></a><a href=# onClick='decrease()' ondblclick='decrease()'><img src='image/038.png' style='width:30px;height:30px' align='middle'></img></a><input name='jc' type='text' size='20' value='"+jc+"' style='width:20%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'>次</td>";
//					htm += "<td>完成人：<input name='ybkh' type='text' size='20' value='' style='width:60%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>";
					htm += "</tr>";
					htm += "<tr>";
					htm += "<td bgcolor='#cccccc'><b> 描     述 </b></td><td colspan=5 align=left><input name='description' type='text' size='20' value='' style='width:80%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>";
					htm += "</tr>";
					htm += "<tr>";
					htm += "<td bgcolor='#cccccc'><b>*完成人</b></td><td align=left><span id='opt'><span></td><td colspan=4 align=left><input type='button' name='submit' value='新增' style='width:102px;height:32px' onClick='addtreatment()'><input type='button' name='submit' value='保存' style='width:102px;height:32px' onClick='save()'></td>";
					htm += "</tr>";
					htm += "</table>";
//					var wholehtm = "";
//					wholehtm+="<div><input id='ybkh' name='ybkh' type='hidden' value='' size='5' class='xhx'>&nbsp;&nbsp;&nbsp;&nbsp;";
//					wholehtm += "<form>";
//					wholehtm += "<table border='0' cellpadding='0' cellspacing='0' width='100%' height='566'>";
//					wholehtm += "<tr>";
//					wholehtm += "<td valign='top' colspan='3'>";
//					wholehtm += "<img border='0' src='image/ja_b_01.gif' width='256' height='61'><img border='0' src='image/ja_b_02.gif' width='428' height='61'><img border='0' src='image/ja_b_04.gif' width='269' height='61'></td>";
//					wholehtm += "<td height='61'>　</td>";
//					wholehtm += "</tr>";
//					wholehtm += "<tr>";
//					wholehtm += "<td valign='top' rowspan='2'>";
//					wholehtm += "<img border='0' src='image/ja_b_07.gif' width='19' height='310'></td>";
//					wholehtm += "<td valign='top'>";
//		　			wholehtm += "<div>"+htm+"</div>";
//					wholehtm += "</td>";
//					wholehtm += "<td valign='top' rowspan='2'>";
//					wholehtm += "<img border='0' src='image/ja_b_10.gif' width='25' height='310'></td>";
//					wholehtm += "<td height='309'>　</td>";
//					wholehtm += "</tr>";
//					wholehtm += "<tr>";
//					wholehtm += "<td></td>";
//					wholehtm += "<td height='1'></td>";
//					wholehtm += "</tr>";
//					wholehtm += "<tr>";
//					wholehtm += "<td valign='top' colspan='3'>";
//					wholehtm += "<img border='0' src='image/ja_b_11.gif' width='19' height='19'><img border='0' src='image/ja_b_12.gif' width='909' height='19'><img border='0' src='image/ja_b_14.gif' width='25' height='19'></td>";
//					wholehtm += "<td height='19'></td>";
//					wholehtm += "</tr>";
//					wholehtm += "<tr>";
//					wholehtm += "<td width='20'>　</td>";
//					wholehtm += "<td width='908'>　</td>";
//					wholehtm += "<td width='30'>　</td>";
//					wholehtm += "<td height='176' width='186'>　</td>";
//					wholehtm += "</tr>";
//					wholehtm += "</table>";
					htm += "</form>";
					htm+="<div><input id='ybkh' name='ybkh' type='hidden' value='' size='5' class='xhx'>&nbsp;&nbsp;&nbsp;&nbsp;";
					$("#result").html(htm);
					// $('#sex').sSelect();


//					if ($.url.param("ybkh") != null) {
//						var param = $.url.param("ybkh");

//						param = "patient.ybkh=" + param.Trim();
//						$.ajax( {
//							url : "requestPatientBasic.action",
//							type : "post",
//							dataType : "json",
//							data : param,
//							success : update_page
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
function update_page() {
//	var jsonArray = eval("(" + result + ")");
	var nowDate = new Date();
//	for ( var one in jsonArray) {
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
		var c = "";
		if($.url.param("czmc") == "1" || $.url.param("czmc") == 1){
			c = "换药"
		} else if($.url.param("czmc") == "2" || $.url.param("czmc") == 2){
			c = "拆线"
		}else if($.url.param("czmc") == "3" || $.url.param("czmc") == 3){
			c = "缝合"
		}else if($.url.param("czmc") == "9" || $.url.param("czmc") == 9){
			c = "其他"
		}
//		alert("brxbbrxb "+$.url.param("brxb"));
//		alert("llllllll "+$.url.param("lxdh"));
//		alert("dddddddd "+$.url.param("jtdz"));
//		alert("uuuuuuuuu "+$.url.param("username"));
		$("input[name='brxm']").attr("value", $.url.param("brxm"));
		$("input[name='brnl']").attr("value", $.url.param("brnl"));
		$("select[name='brhy']").attr("value", $.url.param("brhy"));
		$("input[name='gms']").attr("value", $.url.param("gms"));
		$("input[name='xx']").attr("value", $.url.param("xx"));
		$("input[name='mzzd']").attr("value", $.url.param("mzzd"));
		$("input[name='description']").attr("value", $.url.param("description"));
		$("select[name='brxb']").attr("value", $.url.param("brxb"));
		$("input[name='lxdh']").attr("value", $.url.param("lxdh"));
		$("input[name='jtdz']").attr("value", $.url.param("jtdz"));
		$("input[name='ybkh']").attr("value", $.url.param("ybkh"));
		$("input[name='time']").attr("value", $.url.param("markdate"));
		$("input[name='jc']").attr("value", $.url.param("jc"));
		$("select[name='czmc']").attr("value", $.url.param("czmc"));
		$("select[name='username']").attr("value", $.url.param("username"));
//	}
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
	// $("input[name='content']").attr("value");
//	alert(11111111);
	var brxm = $("input[name='brxm']").attr("value");
	var brnl = $("input[name='brnl']").attr("value");
	var brhy = $("select[name='brhy']").attr("value");
	var gms = $("input[name='gms']").attr("value");
	var xx = $("input[name='xx']").attr("value");
	var mzzd = $("input[name='mzzd']").attr("value");
	var description = $("input[name='description']").attr("value");
	var brxb = $("select[name='brxb']").attr("value");
	var lxdh = $("input[name='lxdh']").attr("value");
	var jtdz = $("input[name='jtdz']").attr("value");
	var ybkh = $("input[name='ybkh']").attr("value");
	var jc = $("input[name='jc']").attr("value");
	var czmc = $("select[name='czmc']").attr("value");
	var user = $("select[name='username']").attr("value");
//	alert("xxxxxxxx "+brxb);
//	alert("hhhhhhhhhhh "+brhy);
//	alert("ccccccccccc "+czmc);
//	alert("uuuuuuuuuu "+user);
	var jcvalue = jc.toString().Trim();
		
//	var s = "";
//	if($("input[name='brxb']").attr("value") == "男"){
//		s = "1";
//	}else if($("input[name='brxb']").attr("value") == "男"){
//		s = "2";
//	}
	if (brxm.length == 0 || brxm.Trim().length == 0) {
		alert("姓名为必填信息");
		return false;
	}
	if (brxb == "99" || brxb == 99) {
		alert("请选择性别");
		return false;
	}
//	if (ybkh.length == 0 || ybkh.Trim().length == 0 || !isInteger(ybkh)) {
//		alert("请输入正确的医保卡号");
//		return false;
//	}
//	if (jtdz.length == 0 || jtdz.Trim().length == 0) {
//		alert("家庭地址为必填信息");
//		return false;
//	}
	if(jcvalue.length == 0 || jcvalue.Trim().length == 0||!isInteger(jcvalue) || jc == 0 || jc == "0"){
		alert("请输入正确的计次");
		return false;
	}
	if(czmc.length == 0 || czmc.Trim().length == 0 || czmc == "0" || czmc == 0){
		alert("请选择操作名称");
		return false;
	}
	if (null == user || user.length == 0) {
		alert("请选择完成人");
		return false;
	}
	
	var param = "";
//	alert("xxxxxxxx "+brxm);
	param += "patient.patientname=" + brxm;
	param += "&patient.brnl=" + brnl;
	param += "&patient.brhy=" + brhy;
	param += "&patient.gms=" + gms;
	param += "&patient.xx=" + xx;
	param += "&patient.mzzd=" + mzzd;
	param += "&patient.description=" + description;
	param += "&patient.sex=" + brxb;
	param += "&patient.phone=" + lxdh;
	param += "&patient.address=" + jtdz;
	param += "&patient.ybkh=" + ybkh;
	param += "&patient.optid=" + user;
	param += "&patient.czmc=" + czmc;
	param += "&patient.jc=" + jcvalue;
	param += "&patient.treatmentid=" + $.url.param("treatmentid");
//	alert("ididid "+$.url.param("treatmentid"));
	//alert(param);
	$.ajax( {
		url : "requestUpdateTreatmentService.action",
		type : "post",
		dataType : "json",
		data : param,
		success : update_result
	});
//	location.href='treatment.html';
}
function addtreatment(){
	// $("input[name='content']").attr("value");
//	alert(11111111);
	var brxm = $("input[name='brxm']").attr("value");
	var brnl = $("input[name='brnl']").attr("value");
	var brhy = $("select[name='brhy']").attr("value");
	var gms = $("input[name='gms']").attr("value");
	var xx = $("input[name='xx']").attr("value");
	var mzzd = $("input[name='mzzd']").attr("value");
	var description = $("input[name='description']").attr("value");
	var brxb = $("select[name='brxb']").attr("value");
	var lxdh = $("input[name='lxdh']").attr("value");
	var jtdz = $("input[name='jtdz']").attr("value");
	var ybkh = $("input[name='ybkh']").attr("value");
	var jc = $("input[name='jc']").attr("value");
	var czmc = $("select[name='czmc']").attr("value");
	var user = $("select[name='username']").attr("value");
//	alert("xxxxxxxx "+brxb);
//	alert("hhhhhhhhhhh "+brhy);
//	alert("ccccccccccc "+czmc);
//	alert("uuuuuuuuuu "+user);
	var jcvalue = jc.toString().Trim();
		
//	var s = "";
//	if($("input[name='brxb']").attr("value") == "男"){
//		s = "1";
//	}else if($("input[name='brxb']").attr("value") == "男"){
//		s = "2";
//	}
	if (brxm.length == 0 || brxm.Trim().length == 0) {
		alert("姓名为必填信息");
		return false;
	}
	if (brxb == "99" || brxb == 99) {
		alert("请选择性别");
		return false;
	}
//	if (ybkh.length == 0 || ybkh.Trim().length == 0 || !isInteger(ybkh)) {
//		alert("请输入正确的医保卡号");
//		return false;
//	}
//	if (jtdz.length == 0 || jtdz.Trim().length == 0) {
//		alert("家庭地址为必填信息");
//		return false;
//	}
	if(jcvalue.length == 0 || jcvalue.Trim().length == 0||!isInteger(jcvalue) || jc == 0 || jc == "0"){
		alert("请输入正确的计次");
		return false;
	}
	if(czmc.length == 0 || czmc.Trim().length == 0 || czmc == "0" || czmc == 0){
		alert("请选择操作名称");
		return false;
	}
	if (null == user || user.length == 0) {
		alert("请选择完成人");
		return false;
	}
	
	var param = "";
	param += "patient.patientname=" + brxm;
	param += "&patient.brnl=" + brnl;
	param += "&patient.brhy=" + brhy;
	param += "&patient.gms=" + gms;
	param += "&patient.xx=" + xx;
	param += "&patient.mzzd=" + mzzd;
	param += "&patient.description=" + description;
	param += "&patient.sex=" + brxb;
	param += "&patient.phone=" + lxdh;
	param += "&patient.address=" + jtdz;
	param += "&patient.ybkh=" + ybkh;
	param += "&patient.optid=" + user;
	param += "&patient.czmc=" + czmc;
	param += "&patient.jc=" + jcvalue;
	param += "&patient.treatmentid=" + $.url.param("treatmentid");
//	alert("ididid "+$.url.param("treatmentid"));
	//alert(param);
	$.ajax( {
		url : "requestUpdateTreatmentService.action",
		type : "post",
		dataType : "json",
		data : param,
		success : update_addresult
	});
//	location.href='treatment.html';
}
function add(){
	var jcvalue = $("input[name='jc']").attr("value");
	if(!isJcInteger(parseInt(jcvalue.toString().Trim()))){
		alert("请输入数字");
	}
	else if(jcvalue == null || jcvalue == ""){
		jcvalue = 1;
		return $("input[name='jc']").attr("value", jcvalue);
	}else{
		
		jcvalue ++;
		return $("input[name='jc']").attr("value", jcvalue);
	}
}
function decrease(){
	var jcvalue = $("input[name='jc']").attr("value");
	if(!isJcInteger(parseInt(jcvalue.toString().Trim()))){
		alert("请输入数字");
	}
	else if(jcvalue>0){
		jcvalue--;
		return $("input[name='jc']").attr("value", jcvalue);
	}
	else{
		alert("计次不能为负数");
		return false;
	}
}
function update_result(data) {
	var results = data;
	results = eval("(" + results + ")");
	if (results[0].results == "success") {
		alert("保存成功");
		$("input").attr("readonly", "readonly");
		$("input[name='submit']").attr("disabled", true);
		location.href='treatment.html';
	} else {
		alert("保存失败");
	}
}
function update_addresult(data){
	var results = data;
	var nowDate = new Date();
	results = eval("(" + results + ")");
	if (results[0].results == "success") {
		alert("保存成功");
		$("input").attr("readonly", "readonly");
		$("input[name='submit']").attr("disabled", true);
		location.href='treatmentAppendList.jsp';
//		$("input[name='brxm']").attr("value", "");
//		$("input[name='brnl']").attr("value", "");
//		$("select[name='brhy']").attr("value", "99");
//		$("input[name='gms']").attr("value", "");
//		$("input[name='xx']").attr("value", "");
//		$("input[name='mzzd']").attr("value", "");
//		$("input[name='description']").attr("value", "");
//		$("select[name='brxb']").attr("value", "99");
//		$("input[name='lxdh']").attr("value", "");
//		$("input[name='jtdz']").attr("value", "");
//		$("input[name='ybkh']").attr("value", "");
//		$("input[name='time']").attr("value", nowDate.toLocaleDateString().split(" ", 1));
//		$("input[name='jc']").attr("value", "1");
//		$("select[name='czmc']").attr("value", "0");
//		$("select[name='username']").attr("value", "XJ05");
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
function isJcInteger(num) {
	var patrn = /^[0-9]*[0-9][0-9]*$/;

	if (!patrn.exec(num)) {
		return false;
	} else {
		return true;
	}
}
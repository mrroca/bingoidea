var id = "";
$(document).ready(function() {
	$.ajax( {
		url : "requestClinicPatientWpyList.action",
		type : "post",
		dataType : "json",
		success : update_page
	});
});
function update_page(result) {
//	 alert(result);
	var str = "<table id='clinicwpytable'>";
	str += "<tr bgcolor=#C5E1EF><td colspan=2 style='height:30px;font-weight:bold;font-size:14px'>门诊观察列表</td></tr>";
	str += "<tr bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px'>姓名</td><td style='height:30px;font-weight:bold;font-size:14px'>性别</td></tr>";
	var jsonArray = eval("(" + result + ")");
	var row = 0;
	var firstPage = 11;
	if (jsonArray.length <= firstPage) {
		firstPage = jsonArray.length;
	}
	for ( var one = 0; one < firstPage; one++) {
		var s = "";
		if (jsonArray[one].brxb == "1") {
			s = "男";
		} else if (jsonArray[one].brxb == "2") {
			s = "女";
		} else {
			s = "不详";
		}
		str += "<tr id='cfrow'>";
		str += "<td><a href='#' onClick=\"aclick('" + jsonArray[one].cfid
				+ "'," + "'" + jsonArray[one].brxm + "'" + ",'"
				+ jsonArray[one].id + "')\">" + jsonArray[one].brxm
				+ "</a></td>";
		str += "<td>" + s + "</td>";
		str += "</tr>";
		row++;
	}
	if (row < 11) {
		for ( var i = 0; i < (11 - row); i++) {
			str += "<tr><td>&nbsp;</td><td>&nbsp;</td></tr>";
		}
	}
	if (jsonArray.length > 0) {
		str += "";
	}
	str += "<tr><td td style='height:30px;font-weight:bold;font-size:14px'><input type='button' value='上一页' onclick='previousPage()'></td><td style='height:30px;font-weight:bold;font-size:14px'><input type='button' value='下一页' onclick='nextPage()'></td></tr>";
	str += "</table>";
	$("#clinicresult").html(str);
	var blfySelect = "";
	blfySelect += "<select name='blfy' onchange='changeblfy()'>";
	blfySelect += "<option value='99'>请选择</option>";
	blfySelect += "<option value='1'>有</option>";
	blfySelect += "<option value='0'>无</option>";
	blfySelect += "</select>";
	var basic = "";
	basic += "<table id='clinicwpylurubasic'>";
	basic += "<tr bgcolor=#C5E1EF><td colspan=6 style='height:30px;font-weight:bold;font-size:14px'>居民基本信息</td></tr>"
			+ "<tr><td bgcolor='#cccccc' style='width:16%'>姓名</td><td id='xm' style='width:16%'>"
			+ "&nbsp;"
			+ "</td><td bgcolor='#cccccc' style='width:16%'>性别</td><td id='xb' style='width:16%'>"
			+ "&nbsp;"
			+ "</td><td bgcolor='#cccccc' style='width:16%'>联系电话</td><td id='dh' style='width:16%'>"
			+ "&nbsp;"
			+ "</td></tr><tr><td bgcolor='#cccccc' style='width:16%'>家庭地址</td><td align=left id='dz' style='width:48%' colspan=3>"
			+ "&nbsp;"
			+ "</td><td bgcolor='#cccccc' style='width:16%'>临床诊断</td><td id='zd' style='width:16%'>"
			+ "&nbsp;" + "</td></tr>";
	basic += "</table>";
	basic += "<br/>";
	basic += "<br/>";
	basic += "<br/>";
	basic += "<br/>";
	var lurudiv = "<table id='clinicwpyluru'>";
	lurudiv += "<tr bgcolor=#C5E1EF><td colspan=4 style='height:30px;font-weight:bold;font-size:14px'>观察记录信息</td></tr>";
	lurudiv += "<tr bgcolor='#cccccc'>";
	lurudiv += "<td style='width:23%;height:30px;font-weight:bold;font-size:14px'>有无不良反应</td><td style='width:54%;height:30px;font-weight:bold;font-size:14px' colspan=2>不良反应</td><td style='width:23%;height:30px;font-weight:bold;font-size:14px'>主要症状和处理</td>";
	lurudiv += "</tr>";
	lurudiv += "<tr style='height:50px'>";
	lurudiv += "<td style='magin:0px;padding:0px;width:20%;'>"
			+ blfySelect
			+ "</td><td colspan=2><form id='blfynr' valign='left'><input type='checkbox' value='' name='blfycheck' id='fr'><span style='font-size:14px'> 1、发热</span></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='checkbox' value='' name='blfycheck' id='pz'><span style='font-size:14px'>2、皮疹</span></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='checkbox' value='' name='blfycheck' id='dxy'><span style='font-size:14px'>3、低血压</span></input><br/><input type='checkbox' value='' name='blfycheck' id='mbch'><span style='font-size:14px'>4、面部潮红</span></input><input type='checkbox' value='' name='blfycheck' id='pfsy'><span style='font-size:14px'>5、皮肤瘙痒</span></input><input type='checkbox' value='' name='blfycheck' id='mgy'><span style='font-size:14px'>6、脉管炎</span></input><br/><input type='checkbox' value='' name='blfycheck' id='hxkn'><span style='font-size:14px'>7、呼吸困难</span></input><input type='checkbox' value='' name='blfycheck' id='xj'><span style='font-size:14px'>8、心悸</span></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type='checkbox' value='' name='blfycheck' id='xom'><span style='font-size:14px'>9、胸闷</span></input>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><input type='checkbox' value='' name='blfycheck' id='qt' onchange='changeqt()'><span style='font-size:14px'>10、其它</span></input><input name='qtnr' id='qtnr' type='text' value='' style='width:60%;border-bottom-style: solid;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></form></td><td><textarea name='chuli' cols ='17%' rows='5' style='overflow:auto'></textarea></td>";
	lurudiv += "</tr>";
	lurudiv += "<tr style='height:30px'>";
	lurudiv += "<td colspan=2>处理人：</td><td colspan=2><span id='opt'><span></td>";
	lurudiv += "</tr>";
	lurudiv += "<tr style='height:30px'>";
	lurudiv += "<td id='currentpatient' colspan=2></td><td colspan=2><input type='button' name='submit' style='width:92px;height:22px' value='保存' onClick='save()'><input type='hidden' name='cfid' value=''></td>";
	lurudiv += "</tr>";
	lurudiv += "</table>";
	basic += lurudiv;
	$("#clinicresultluru").html(basic);
	$("input[name='submit']").hide();
	$("#blfynr").hide();
	$("select[name='blfy']").hide();
	$("textarea[name='chuli']").hide();
}
function prePage(result) {
	var str = "<table id='clinicwpytable'>";
	str += "<tr bgcolor=#C5E1EF><td colspan=2 style='height:30px;font-weight:bold;font-size:14px'>门诊观察列表</td></tr>";
	str += "<tr bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px'>姓名</td><td style='height:30px;font-weight:bold;font-size:14px'>性别</td></tr>";
	var jsonArray = eval("(" + result + ")");
	var row = 0;
	var from;
	var to;
	var clinictotalPages = 1;
	var cliniccurrentpage = document.getElementById("cliniccurrentpage").value;
	cliniccurrentpage = parseInt(cliniccurrentpage);
	if (cliniccurrentpage == 1) {
		return;
	}
	from = (cliniccurrentpage - 2) * 11;
	to = (cliniccurrentpage - 1) * 11;
	if (jsonArray.length > 11) {
		clinictotalPages = jsonArray.length / 11 + "";
		if (clinictotalPages.indexOf(".") >= 0) {
			clinictotalPages = parseInt(clinictotalPages, 10) + 1;
		}
	}
	for ( var one = from; one < to; one++) {
		var s = "";
		if (jsonArray[one].brxb == "1") {
			s = "男";
		} else if (jsonArray[one].brxb == "2") {
			s = "女";
		} else {
			s = "不详";
		}
		str += "<tr id='cfrow'>";
		str += "<td><a href='#' onClick=\"aclick('" + jsonArray[one].cfid
				+ "'," + "'" + jsonArray[one].brxm + "'" + ",'"
				+ jsonArray[one].id + "')\">" + jsonArray[one].brxm
				+ "</a></td>";
		str += "<td>" + s + "</td>";
		str += "</tr>";
		row++;
	}
	if (row < 10) {
		for ( var i = 0; i < (10 - row); i++) {
			str += "<tr><td>&nbsp;</td><td>&nbsp;</td></tr>";
		}
	}
	if (jsonArray.length > 0) {
		str += "";
	}
	str += "<tr><td td style='height:30px;font-weight:bold;font-size:14px'><input type='button' value='上一页' onclick='previousPage()'></td><td style='height:30px;font-weight:bold;font-size:14px'><input type='button' value='下一页' onclick='nextPage()'></td></tr>";
	str += "</table>";
	$("#clinicresult").html(str);
	var blfySelect = "";
	blfySelect += "<select name='blfy' onchange='changeblfy()'>";
	blfySelect += "<option value='99'>请选择</option>";
	blfySelect += "<option value='1'>有</option>";
	blfySelect += "<option value='0'>无</option>";
	blfySelect += "</select>";
	var basic = "";
	basic += "<table id='clinicwpylurubasic'>";
	basic += "<tr bgcolor=#C5E1EF><td colspan=6 style='height:30px;font-weight:bold;font-size:14px'>居民基本信息</td></tr>"
			+ "<tr><td bgcolor='#cccccc' style='width:16%'>姓名</td><td id='xm' style='width:16%'>"
			+ "&nbsp;"
			+ "</td><td bgcolor='#cccccc' style='width:16%'>性别</td><td id='xb' style='width:16%'>"
			+ "&nbsp;"
			+ "</td><td bgcolor='#cccccc' style='width:16%'>联系电话</td><td id='dh' style='width:16%'>"
			+ "&nbsp;"
			+ "</td></tr><tr><td bgcolor='#cccccc' style='width:16%'>家庭地址</td><td align=left id='dz' style='width:48%' colspan=3>"
			+ "&nbsp;"
			+ "</td><td bgcolor='#cccccc' style='width:16%'>临床诊断</td><td id='zd' style='width:16%'>"
			+ "&nbsp;" + "</td></tr>";
	basic += "</table>";
	basic += "<br/>";
	basic += "<br/>";
	basic += "<br/>";
	basic += "<br/>";
	var lurudiv = "<table id='clinicwpyluru'>";
	lurudiv += "<tr bgcolor=#C5E1EF><td colspan=4 style='height:30px;font-weight:bold;font-size:14px'>观察记录信息</td></tr>";
	lurudiv += "<tr bgcolor='#cccccc'>";
	lurudiv += "<td style='width:23%;height:30px;font-weight:bold;font-size:14px'>有无不良反应</td><td style='width:54%;height:30px;font-weight:bold;font-size:14px' colspan=2>不良反应</td><td style='width:23%;height:30px;font-weight:bold;font-size:14px'>主要症状和处理</td>";
	lurudiv += "</tr>";
	lurudiv += "<tr style='height:50px'>";
	lurudiv += "<td style='magin:0px;padding:0px;width:20%;'>"
			+ blfySelect
			+ "</td><td colspan=2><form id='blfynr'><input type='checkbox' value='' name='blfycheck' id='fr'><span style='font-size:14px'> 1、发热</span></input><input type='checkbox' value='' name='blfycheck' id='pz'><span style='font-size:14px'>2、皮疹</span></input><input type='checkbox' value='' name='blfycheck' id='dxy'><span style='font-size:14px'>3、低血压</span></input><br/><input type='checkbox' value='' name='blfycheck' id='mbch'><span style='font-size:14px'>4、面部潮红</span></input><input type='checkbox' value='' name='blfycheck' id='pfsy'><span style='font-size:14px'>5、皮肤瘙痒</span></input><input type='checkbox' value='' name='blfycheck' id='mgy'><span style='font-size:14px'>6、脉管炎</span></input><br/><input type='checkbox' value='' name='blfycheck' id='hxkn'><span style='font-size:14px'>7、呼吸困难</span></input><input type='checkbox' value='' name='blfycheck' id='xj'><span style='font-size:14px'>8、心悸</span></input><input type='checkbox' value='' name='blfycheck' id='xom'><span style='font-size:14px'>9、胸闷</span></input><br/><input type='checkbox' value='' name='blfycheck' id='qt' onchange='changeqt()'><span style='font-size:14px'>10、其它</span></input><input name='qtnr' id='qtnr' type='text' value='' style='border-bottom-style: solid;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></form></td><td><textarea name='chuli' cols ='17%' rows='5' style='overflow:auto'></textarea></td>";
	lurudiv += "</tr>";
	lurudiv += "<tr style='height:30px'>";
	lurudiv += "<td colspan=2>处理人：</td><td colspan=2><span id='opt'><span></td>";
	lurudiv += "</tr>";
	lurudiv += "<tr style='height:30px'>";
	lurudiv += "<td id='currentpatient' colspan=2></td><td colspan=2><input type='button' name='submit' style='width:92px;height:22px' value='保存' onClick='save()'><input type='hidden' name='cfid' value=''></td>";
	lurudiv += "</tr>";
	lurudiv += "</table>";
	basic += lurudiv;
	$("#clinicresultluru").html(basic);
	$("input[name='submit']").hide();
	$("#blfynr").hide();
	$("select[name='blfy']").hide();
	$("textarea[name='chuli']").hide();
	document.getElementById("cliniccurrentpage").value = cliniccurrentpage - 1
}
function nexPage(result) {
	var str = "<table id='clinicwpytable'>";
	str += "<tr bgcolor=#C5E1EF><td colspan=2 style='height:30px;font-weight:bold;font-size:14px'>门诊观察列表</td></tr>";
	str += "<tr bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px'>姓名</td><td style='height:30px;font-weight:bold;font-size:14px'>性别</td></tr>";
	var jsonArray = eval("(" + result + ")");
	var row = 0;
	var from;
	var to;
	var clinictotalPages = 1;
	var cliniccurrentpage = document.getElementById("cliniccurrentpage").value;
	cliniccurrentpage = parseInt(cliniccurrentpage);
	if (jsonArray.length > 11) {
		clinictotalPages = jsonArray.length / 11 + "";
		if (clinictotalPages.indexOf(".") >= 0) {
			clinictotalPages = parseInt(clinictotalPages, 10) + 1;
		}
	}
	if (cliniccurrentpage == clinictotalPages) {
		return;
	}
	from = cliniccurrentpage * 11;
	to = (cliniccurrentpage + 1) * 11;
	if (cliniccurrentpage == (clinictotalPages - 1)) {
		to = jsonArray.length;
	}
	for ( var one = from; one < to; one++) {
		var s = "";
		if (jsonArray[one].brxb == "1") {
			s = "男";
		} else if (jsonArray[one].brxb == "2") {
			s = "女";
		} else {
			s = "不详";
		}
		str += "<tr id='cfrow'>";
		str += "<td><a href='#' onClick=\"aclick('" + jsonArray[one].cfid
				+ "'," + "'" + jsonArray[one].brxm + "'" + ",'"
				+ jsonArray[one].id + "')\">" + jsonArray[one].brxm
				+ "</a></td>";
		str += "<td>" + s + "</td>";
		str += "</tr>";
		row++;
	}
	if (row < 11) {
		for ( var i = 0; i < (11 - row); i++) {
			str += "<tr><td>&nbsp;</td><td>&nbsp;</td></tr>";
		}
	}
	if (jsonArray.length > 0) {
		str += "";
	}
	str += "<tr><td td style='height:30px;font-weight:bold;font-size:14px'><input type='button' value='上一页' onclick='previousPage()'></td><td style='height:30px;font-weight:bold;font-size:14px'><input type='button' value='下一页' onclick='nextPage()'></td></tr>";
	str += "</table>";
	$("#clinicresult").html(str);
	var blfySelect = "";
	blfySelect += "<select name='blfy' onchange='changeblfy()'>";
	blfySelect += "<option value='99'>请选择</option>";
	blfySelect += "<option value='1'>有</option>";
	blfySelect += "<option value='0'>无</option>";
	blfySelect += "</select>";
	var basic = "";
	basic += "<table id='clinicwpylurubasic'>";
	basic += "<tr bgcolor=#C5E1EF><td colspan=6 style='height:30px;font-weight:bold;font-size:14px'>居民基本信息</td></tr>"
			+ "<tr><td bgcolor='#cccccc' style='width:16%'>姓名</td><td id='xm' style='width:16%'>"
			+ "&nbsp;"
			+ "</td><td bgcolor='#cccccc' style='width:16%'>性别</td><td id='xb' style='width:16%'>"
			+ "&nbsp;"
			+ "</td><td bgcolor='#cccccc' style='width:16%'>联系电话</td><td id='dh' style='width:16%'>"
			+ "&nbsp;"
			+ "</td></tr><tr><td bgcolor='#cccccc' style='width:16%'>家庭地址</td><td align=left id='dz' style='width:48%' colspan=3>"
			+ "&nbsp;"
			+ "</td><td bgcolor='#cccccc' style='width:16%'>临床诊断</td><td id='zd' style='width:16%'>"
			+ "&nbsp;" + "</td></tr>";
	basic += "</table>";
	basic += "<br/>";
	basic += "<br/>";
	basic += "<br/>";
	basic += "<br/>";
	var lurudiv = "<table id='clinicwpyluru'>";
	lurudiv += "<tr bgcolor=#C5E1EF><td colspan=4 style='height:30px;font-weight:bold;font-size:14px'>观察记录信息</td></tr>";
	lurudiv += "<tr bgcolor='#cccccc'>";
	lurudiv += "<td style='width:23%;height:30px;font-weight:bold;font-size:14px'>有无不良反应</td><td style='width:54%;height:30px;font-weight:bold;font-size:14px' colspan=2>不良反应</td><td style='width:23%;height:30px;font-weight:bold;font-size:14px'>主要症状和处理</td>";
	lurudiv += "</tr>";
	lurudiv += "<tr style='height:50px'>";
	lurudiv += "<td style='magin:0px;padding:0px;width:20%;'>"
			+ blfySelect
			+ "</td><td colspan=2><form id='blfynr'><input type='checkbox' value='' name='blfycheck' id='fr'><span style='font-size:14px'> 1、发热</span></input><input type='checkbox' value='' name='blfycheck' id='pz'><span style='font-size:14px'>2、皮疹</span></input><input type='checkbox' value='' name='blfycheck' id='dxy'><span style='font-size:14px'>3、低血压</span></input><br/><input type='checkbox' value='' name='blfycheck' id='mbch'><span style='font-size:14px'>4、面部潮红</span></input><input type='checkbox' value='' name='blfycheck' id='pfsy'><span style='font-size:14px'>5、皮肤瘙痒</span></input><input type='checkbox' value='' name='blfycheck' id='mgy'><span style='font-size:14px'>6、脉管炎</span></input><br/><input type='checkbox' value='' name='blfycheck' id='hxkn'><span style='font-size:14px'>7、呼吸困难</span></input><input type='checkbox' value='' name='blfycheck' id='xj'><span style='font-size:14px'>8、心悸</span></input><input type='checkbox' value='' name='blfycheck' id='xom'><span style='font-size:14px'>9、胸闷</span></input><br/><input type='checkbox' value='' name='blfycheck' id='qt' onchange='changeqt()'><span style='font-size:14px'>10、其它</span></input><input name='qtnr' id='qtnr' type='text' value='' style='border-bottom-style: solid;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></form></td><td><textarea name='chuli' cols ='17%' rows='5' style='overflow:auto'></textarea></td>";
	lurudiv += "</tr>";
	lurudiv += "<tr style='height:30px'>";
	lurudiv += "<td colspan=2>处理人：</td><td colspan=2><span id='opt'><span></td>";
	lurudiv += "</tr>";
	lurudiv += "<tr style='height:30px'>";
	lurudiv += "<td id='currentpatient' colspan=2></td><td colspan=2><input type='button' name='submit' style='width:92px;height:22px' value='保存' onClick='save()'><input type='hidden' name='cfid' value=''></td>";
	lurudiv += "</tr>";
	lurudiv += "</table>";
	basic += lurudiv;
	$("#clinicresultluru").html(basic);
	$("input[name='submit']").hide();
	$("#blfynr").hide();
	$("select[name='blfy']").hide();
	$("textarea[name='chuli']").hide();
	document.getElementById("cliniccurrentpage").value = cliniccurrentpage + 1
}
function nextPage(){
	$.ajax( {
		url : "requestClinicPatientWpyList.action",
		type : "post",
		dataType : "json",
		success : nexPage
	});
}
function previousPage(){
	$.ajax( {
		url : "requestClinicPatientWpyList.action",
		type : "post",
		dataType : "json",
		success : prePage
	});
}
function print() {
}
function aclick(cfid, patientname, pid) {
	var cfid = cfid;
	id = pid;
	var wpygcjlid = pid;
//	 alert(wpygcjlid);
	var patientname = patientname;
	$.ajax( {
		url : "requestPersonInfo.action",
		type : "post",
		dataType : "json",
		success : update_opt
	});
	param = "patient.cfid=" + cfid+"&patient.wpygcjlid="+wpygcjlid;
	$.ajax( {

		url : "requestPatientWpyInfomx.action",

		type : "post",

		dataType : "json",

		data : param,

		success : update_basic
	});
	var blfy = $("select[name='blfy']").attr("value", "");
	var chuli = $("textarea[name='chuli']").attr("value", "");
	$("input[name='submit']").show();
	
	$("select[name='blfy']").show();
	$("textarea[name='chuli']").show();
	$("input[name='submit']").attr("disabled", false);
	$("#blfynr").hide();
	var patient = "患者：" + patientname;
	$("#currentpatient").html(patient);
	$("input[name='cfid']").attr("value", cfid);
	
}
function changeblfy(){
	var blfy = $("select[name='blfy']").attr("value");
	if(blfy == 1 || blfy == '1'){
		$("#blfynr").show();
		$("input[name='blfycheck']").each(function() {
			$(this).attr("checked", false);
		});
		$("input[name='qtnr']").attr("value", "");
	}else if(blfy == 0 || blfy == '0'){
		$("#blfynr").hide();
	}else {
		return;
	}
	if($("#qt").attr("checked") == true){
		$("#qtnr").attr("disabled", false);
	}else if($("#qt").attr("checked") == false){
		$("#qtnr").attr("disabled", true);
		$("input[name='qtnr']").attr("value", "");
	}
}
function changeqt(){
	if ($("#qt").attr("checked") == true) {
		$("#qtnr").attr("disabled", false);
	} else if ($("#qt").attr("checked") == false) {
		$("#qtnr").attr("disabled", true);
		$("input[name='qtnr']").attr("value", "");
	}
}
function clinicwpy (){
	location.href='clinicwpyservice.html';
}
function update_opt(result) {
	var jsonArray = eval("(" + result + ")");
	var nursestr = "";
	nursestr += "<select id='nurseselect' name='username'>";
	// for ( var one = 0; one < jsonArray.length; one++) {
	// nursestr += "<input type='radio' name='username' value='"
	// + jsonArray[one].username + "'>" + jsonArray[one].name + "";
	// if ((m + 1) % 4 == 0) {
	// nursestr += "<br/>";
	// }
	// m++;
	// }
	for ( var one = 0; one < jsonArray.length; one++) {
		nursestr += "<option value='" + jsonArray[one].username + "'>"
				+ jsonArray[one].name + "</option>";
	}
	nursestr += "</select>";
	$("#opt").html(nursestr);
}
function update_basic(data) {
	var temp = data;
	var tempstr = temp.toString().split("|-|-|");
	var jsonArraybasicinfo = eval("(" + tempstr[0] + ")");
	var jsonArraygcjlinfo = eval("(" + tempstr[1] + ")");
	var lczd = "";

	if (lczd.length > 0) {
		lczd = lczd.substring(0, lczd.length - 1);
		$("#zd").html(lczd);
	}
	
	$("select[name='blfy']").attr("value", jsonArraygcjlinfo[0].blfy);
	var blfynr = jsonArraygcjlinfo[0].blfynr.split(',');
	var fr = blfynr[0];
	var pz = blfynr[1];
	var dxy = blfynr[2];
	var mbch = blfynr[3];
	var pfsy = blfynr[4];
	var mgy = blfynr[5];
	var hxkn = blfynr[6];
	var xj = blfynr[7];
	var xom = blfynr[8];
	var qt = blfynr[9];
	var qtnr = blfynr[10];
	if(fr == 1 || fr == '1'){
		$("#fr").attr("checked",true);
	}else{
		$("#fr").attr("checked",false);
	}
	if(pz == 1 || pz == '1'){
		$("#pz").attr("checked",true);
	}else{
		$("#pz").attr("checked",false);
	}
	if(dxy == 1 || dxy == '1'){
		$("#dxy").attr("checked",true);
	}else{
		$("#dxy").attr("checked",false);
	}
	if(mbch == 1 || mbch == '1'){
		$("#mbch").attr("checked",true);
	}else{
		$("#mbch").attr("checked",false);
	}
	if(pfsy == 1 || pfsy == '1'){
		$("#pfsy").attr("checked",true);
	}else{
		$("#pfsy").attr("checked",false);
	}
	if(mgy == 1 || mgy == '1'){
		$("#mgy").attr("checked",true);
	}else{
		$("#mgy").attr("checked",false);
	}
	if(hxkn == 1 || hxkn == '1'){
		$("#hxkn").attr("checked",true);
	}else{
		$("#hxkn").attr("checked",false);
	}
	if(xj == 1 || xj == '1'){
		$("#xj").attr("checked",true);
	}else{
		$("#xj").attr("checked",false);
	}
	if(xom == 1 || xom == '1'){
		$("#xom").attr("checked",true);
	}else{
		$("#xom").attr("checked",false);
	}
	if(qt == 1 || qt == '1'){
		$("#qt").attr("checked",true);
	}else{
		$("#qt").attr("checked",false);
	}
	$("input[name='qtnr']").attr("value", qtnr);
	$("textarea[name='chuli']").attr("value", jsonArraygcjlinfo[0].chuli);
	var blfy = $("select[name='blfy']").attr("value");
	if(blfy == 1 || blfy == '1'){
		$("#blfynr").show();
	}else if(blfy == 0 || blfy == '0'){
		$("#blfynr").hide();
	}else {
		return;
	}
	$("select[name='username']").attr("value",jsonArraygcjlinfo[0].username);
	
	for ( var one in jsonArraybasicinfo) {
		var s = "";
		if (jsonArraybasicinfo[one].brxb == "1") {
			s = "男";
		} else if (jsonArraybasicinfo[one].brxb == "2") {
			s = "女";
		} else {
			s = "不详";
		}

		$("#xm").html(jsonArraybasicinfo[one].brxm);
		$("#xb").html(s);
		$("#dz").html(jsonArraybasicinfo[one].jtdz);
		$("#dh").html(jsonArraybasicinfo[one].lxdh);
		$("#zd").html(jsonArraybasicinfo[one].lczd);
	}
	if ($("#qt").attr("checked") == true) {
		$("#qtnr").attr("disabled", false);
	} else if ($("#qt").attr("checked") == false) {
		$("#qtnr").attr("disabled", true);
		$("input[name='qtnr']").attr("value", "");
	}
}
function save() {
//	alert("kiiii"+id);
	var cfid = $("input[name='cfid']").attr("value");
	var blfy = $("select[name='blfy']").attr("value");
	var fr = $("input[name='fr']").attr("value");
	var pz = $("input[name='pz']").attr("value");
	var dxy = $("input[name='dxy']").attr("value");
	var xom = $("input[name='xom']").attr("value");
	var mbch = $("input[name='mbch']").attr("value");
	var pfsy = $("input[name='pfsy']").attr("value");
	var mgy = $("input[name='mgy']").attr("value");
	var hxkn = $("input[name='hxkn']").attr("value");
	var xj = $("input[name='xj']").attr("value");
	var qt = $("input[name='qt']").attr("value");
	var qtnr = $("input[name='qtnr']").attr("value");
	var chuli = $("textarea[name='chuli']").attr("value");
	var userid = $("select[name='username']").attr("value");
	if($("#fr").attr("checked")==true){
		fr = '1';
	}else if($("#fr").attr("checked") == false){
		fr = '0';
	}
	if($("#pz").attr("checked") == true){
		pz = '1';
	}else if($("#pz").attr("checked") == false){
		pz = '0';
	}
	if($("#dxy").attr("checked") == true){
		dxy = '1';
	}else if($("#dxy").attr("checked") == false){
		dxy = '0';
	}
	if($("#xom").attr("checked") == true){
		xom = '1';
	}else if($("#xom").attr("checked") == false){
		xom = '0';
	}
	if($("#mbch").attr("checked") == true){
		mbch = '1';
	}else if($("#mbch").attr("checked") == false){
		mbch = '0';
	}
	if($("#pfsy").attr("checked") == true){
		pfsy = '1';
	}else if($("#pfsy").attr("checked") == false){
		pfsy = '0';
	}
	if($("#mgy").attr("checked") == true){
		mgy = '1';
	}else if($("#mgy").attr("checked") == false){
		mgy = '0';
	}
	if($("#hxkn").attr("checked") == true){
		hxkn = '1';
	}else if($("#hxkn").attr("checked") == false){
		hxkn = '0';
	}
	if($("#xj").attr("checked") == true){
		xj = '1';
	}else if($("#xj").attr("checked") == false){
		xj = '0';
	}
	if($("#qt").attr("checked") == true){
		qt = '1';
	}else if($("#qt").attr("checked") == false){
		qt = '0';
	}
	if (blfy.Trim().length == 0 || blfy == "99") {
		alert("请选择有无不良反应");
		return false;
	}
	if (chuli.Trim().length == 0) {
		alert("请输入处理内容");
		return false;
	}
	if (userid == null) {
		alert("请选择处理人");
		return false;
	}
	var param = "patient.cfid=" + cfid + "&patient.blfy=" + blfy
			+ "&patient.blfynr=" + fr + "," + pz + "," + dxy + "," + mbch + ","
			+ pfsy + "," + mgy + "," + hxkn + "," + xj + "," + xom + "," + qt
			+ "," + qtnr + "&patient.chuli=" + chuli + "&patient.optid="
			+ userid + "&patient.id=" + id;
	$.ajax( {
		url : "saveClinicWpyService.action",
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
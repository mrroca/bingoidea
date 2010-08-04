$(document).ready(function() {
//	$.ajax( {
//		url : "requestPersonInfo.action",
//		type : "post",
//		dataType : "json",
//		success : update_opt
//	});
	
	$.ajax( {
			
			// 处理程序
			url : "requestPersonInfo.action",

			// 发送方式
			type : "post",

			// 数据格式
			dataType : "json",

			// 要传递的数据
			
			// 回传函数
			success : update_opt
	});

		});
	
	function saveTprbp() {
		if(brxm.value==""){
			alert("请输入姓名！");
			return false;
		}else if(brxb.value==""){
			alert("请选择性别！");
			return false;
		}else if(brnl.value==""){
			alert("请填写病人年龄！");
			return false;
		}else if(brxz.value==""){
			alert("请选择病人性质！");
			return false;
		}
		
		if(tw.value!=""){
			var temptz=parseFloat(tw.value);
			if(temptz<33 || temptz>42){
				alert("体温必须在33--44℃之间！");
				return false;
			}
		}
		if(mb.value!=""){
			var temppz=parseInt(mb.value);
			if(temppz<0 || temppz>220){
				alert("脉搏必须在0--220之间！");
				return false;
			}
		}
		if(brnl.value!=""){
			if(!isInteger(brnl.value)){
				alert("病人年龄必须为整数！");
				return false;
			}
		}
		if(tw.value=="" && mb.value=="" && hx.value=="" && (ssy.value=="" || szy.value=="") ){
			alert("体温，脉搏，呼吸，血压必须选填一项！");
			return false;
		}
		var user = "";
		user = clr.value;
		if(user){
			var tprbpInfo="patient.nl="+brnl.value+"&patient.patientname="+brxm.value+"&patient.gmwz="+gmwz.value+"&patient.mzzd="+mzzd.value+"&patient.sbxh="+sbxh.value;
			tprbpInfo+="&patient.sex="+brxb.value+"&patient.address="+jtdz.value+"&patient.phone="+lxdh.value+"&patient.gms="+gms.value+"&patient.appid="+appid.value;
			tprbpInfo+="&patient.xx="+xx.value+"&patient.tw="+tw.value+"&patient.mb="+mb.value+"&patient.hy="+hy.value+"&patient.brxz="+brxz.value+"&patient.sflsry="+sflsry.value;
			tprbpInfo+="&patient.hx="+hx.value+"&patient.ssy="+ssy.value+"&patient.szy="+szy.value+"&patient.optname="+user+"&patient.ybkh="+mzhm.value+"&patient.sfyqry="+sfyqry.value;
			$.ajax( {
				url : "saveTPRBP.action",
				type : "post",
				dataType : "json",
				data: tprbpInfo,
				success : messsage
			});
		}else{
			alert("请选择处理人！");
			return false;
		}
		
		
	}

function saveTprbp2() {
		if(brxm.value==""){
			alert("请输入姓名！");
			return false;
		}else if(brxb.value==""){
			alert("请选择性别！");
			return false;
		}else if(brnl.value==""){
			alert("请填写病人年龄！");
			return false;
		}else if(brxz.value==""){
			alert("请选择病人性质！");
			return false;
		}
		
		if(tw.value!=""){
			var temptz=parseFloat(tw.value);
			if(temptz<33 || temptz>42){
				alert("体温必须在33--44℃之间！");
				return false;
			}
		}
		if(mb.value!=""){
			var temppz=parseInt(mb.value);
			if(temppz<0 || temppz>220){
				alert("脉搏必须在0--220之间！");
				return false;
			}
		}
		if(brnl.value!=""){
			if(!isInteger(brnl.value)){
				alert("病人年龄必须为整数！");
				return false;
			}
		}
		if(tw.value=="" && mb.value=="" && hx.value=="" && (ssy.value=="" || szy.value=="") ){
			alert("体温，脉搏，呼吸，血压必须选填一项！");
			return false;
		}
		var user = "";
		user = clr.value;
		if(user){
			var tprbpInfo="patient.nl="+brnl.value+"&patient.patientname="+brxm.value+"&patient.gmwz="+gmwz.value+"&patient.mzzd="+mzzd.value+"&patient.sbxh="+sbxh.value;
			tprbpInfo+="&patient.sex="+brxb.value+"&patient.address="+jtdz.value+"&patient.phone="+lxdh.value+"&patient.gms="+gms.value+"&patient.appid="+appid.value;
			tprbpInfo+="&patient.xx="+xx.value+"&patient.tw="+tw.value+"&patient.mb="+mb.value+"&patient.hy="+hy.value+"&patient.brxz="+brxz.value+"&patient.sflsry="+sflsry.value;
			tprbpInfo+="&patient.hx="+hx.value+"&patient.ssy="+ssy.value+"&patient.szy="+szy.value+"&patient.optname="+user+"&patient.ybkh="+mzhm.value+"&patient.sfyqry="+sfyqry.value;
			$.ajax( {
				url : "saveTPRBP.action",
				type : "post",
				dataType : "json",
				data: tprbpInfo,
				success : messsage2
			});
		}else{
			alert("请选择处理人！");
			return false;
		}
	}
function  clear() {
		appid.value="";
		sbxh.value="";
		mzhm.value="";
		brxm.value="";
		brxb.value="";
		brnl.value="";
		hy.value="";
		jtdz.value="";
		lxdh.value="";
		gms.value="";
		gmwz.value="";
		xx.value="";
		mzzd.value="";
		tw.value="";
		mb.value="";
		hx.value="";
		ssy.value="";
		szy.value="";
		clr.value="";
		brxz.value="";
		sflsry.value="";
		sfyqry.value="";
	}

function messsage(result) {
	var jsonObj= eval("(" + result + ")");
	mzhm.value=jsonObj.mzhm;
	appid.value=jsonObj.appid;
	sbxh.value=jsonObj.sbxh;
	alert(jsonObj.message);
	location.href="tprbplist.html";
}
function messsage2(result) {
	var jsonObj= eval("(" + result + ")");
	mzhm.value=jsonObj.mzhm;
	appid.value=jsonObj.appid;
	sbxh.value=jsonObj.sbxh;
	alert(jsonObj.message);
	clear();
}
function update_opt(result) {
	var jsonArray = eval("(" + result + ")");
	var nursestr = "<SELECT name='clr' id='clr' style='background: #ffffff'><OPTION value=''></OPTION>";
	for ( var one = 0; one < jsonArray.length; one++) {
		nursestr += "<OPTION value='"
				+ jsonArray[one].username + "'>" + jsonArray[one].name + "</OPTION>";
	}
	nursestr+="</SELECT>";
	$("#clr2").html(nursestr);
	var urlparam = $.url.param("patient.appid");
	var urlparam1 = $.url.param("patient.sbxh");
	var	urlparam2 = "patient.appid=" + urlparam+"&patient.sbxh=" + urlparam1;
		$.ajax( {
			
			// 处理程序
			url : "getTprbpInfo.action",

			// 发送方式
			type : "post",

			// 数据格式
			dataType : "json",

			// 要传递的数据
			data : urlparam2,
			// 回传函数
			success : update_tprbpInfo

		});
	
	
}

function update_tprbpInfo(result) {
	
	var jsonArray = eval("(" + result + ")");
//	alert(result.length.toString());
	if(jsonArray.length==0){
		return false;
	}
		appid.value=jsonArray[0].appid;
		sbxh.value=jsonArray[0].sbxh;
		mzhm.value=jsonArray[0].mzhm;
		brxm.value=jsonArray[0].brxm;
		brxb.value=jsonArray[0].brxb;
		brnl.value=new Date().getYear()-jsonArray[0].csny;
		jtdz.value=jsonArray[0].jtdz;
		lxdh.value=jsonArray[0].lxdh;
		gms.value=jsonArray[0].gms;
		gmwz.value=jsonArray[0].gmwz;
		hy.value=jsonArray[0].hy;
		xx.value=jsonArray[0].xx;
		mzzd.value=jsonArray[0].mzzd;
		brxz.value=jsonArray[0].brxz
		tw.value=jsonArray[0].tw;
		mb.value=jsonArray[0].mb;
		hx.value=jsonArray[0].hx;
		ssy.value=jsonArray[0].ssy;
		szy.value=jsonArray[0].szy;
		clr.value=jsonArray[0].clr;
		sflsry.value=jsonArray[0].sflsry;
		sfyqry.value=jsonArray[0].sfyqry;
}
function doBefore() {
	if(xm.value==""){
		alert("请输入姓名！");
		return;
	}else if(brxb.value==""){
		alert("请选择性别！");
		return;
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
function update_basicinfo(result){
	// alert(result);
	var age = 0;
	var jsonArray = eval("(" + result + ")");

		$("input[name='brxm']").attr("value", jsonArray[0].brxm);
		$("input[name='mzhm']").attr("value", jsonArray[0].ybkh);
		$("input[name='jtdz']").attr("value", jsonArray[0].hkdz);
		$("input[name='lxdh']").attr("value", jsonArray[0].lxdh);
		$("select[name='brxb']").attr("value", jsonArray[0].brxb);
		age = calculate_age(jsonArray[0].sfzh.Trim());
		$("input[name='brnl']").attr("value", age);
		$("input[name='appid']").attr("value", jsonArray[0].appid);
}
//function update_opt(result) {
//	var jsonArray = eval("(" + result + ")");
//	var nursestr = "";
//	var m = 0;
//	for ( var one = 0; one < jsonArray.length; one++) {
//		nursestr += "<input type='radio' name='username' value='"
//				+ jsonArray[one].username + "'>" + jsonArray[one].name + "";
//		if ((m + 1) % 4 == 0) {
//			nursestr += "<br/>";
//		}
//		m++;
//	}
//	$("#opt").html(nursestr);
//}

var maxdays=0;
$(document).ready(function() {

	var urlparam = $.url.param("patient.cfsbnumber");
		urlparam = "patient.cfsbnumber=" + urlparam;
		$.ajax( {

			url : "requestPatientInfomx.action",

			type : "post",

			dataType : "json",

			data : urlparam,

			success : update_page
		});
	});
function update_page(data) {
	var temp = data;
	var tempstr = temp.toString().split("|-|-|");
	var jsonArraybasicinfo = eval("(" + tempstr[0] + ")");
	var jsonArraycfinfo = eval("(" + tempstr[1] + ")");
	var jsonArraylczdinfo = eval("(" + tempstr[2] + ")");
	var jsonArraynurse = eval("(" + tempstr[3] + ")");
	var cfsbnumber = $.url.param("patient.cfsbnumber");
	var basic="<table id='basic'>";
	
	var cflist = "<tr bgcolor='#cccccc'><td>组套标识</td><td>药品名称</td><td>规格</td><td>单位</td><td>剂量</td><td>剂量单位</td><td>频次</td><td>天数</td><td>用法</td><td>日期</td></tr>";
	var lczd = "";
	var nursestr = "";
	for ( var one in jsonArraylczdinfo) {
		lczd += jsonArraylczdinfo[one].lczdmc;
		lczd += "、";
	}
	if (lczd.length > 0) {
		lczd = lczd.substring(0, lczd.length - 1);
	}
	//alert(jsonArraycfinfo[0].yyts);
	var largedays=0;
	for ( var one in jsonArraycfinfo) {
		largedays=jsonArraycfinfo[one].yyts;
		if(parseInt(largedays)>maxdays){
			maxdays=parseInt(largedays);
		}
		cflist += "<tr><td>"+jsonArraycfinfo[one].ypzh+"</td><td align=left>" + jsonArraycfinfo[one].ypmc + "</td><td>"
				+ jsonArraycfinfo[one].ypgg + "</td><td>"
				+ jsonArraycfinfo[one].ypdw + "</td><td>"
				+ jsonArraycfinfo[one].ypjl + "</td><td>"
				+ jsonArraycfinfo[one].jldw + "</td><td>"
				+ jsonArraycfinfo[one].pc + "</td><td>"
				+ jsonArraycfinfo[one].yyts +"</td><td>"
				+ jsonArraycfinfo[one].ypyf + "</td><td>"
				+ jsonArraycfinfo[one].kfrq + "</td></tr>";
	}
	if (jsonArraycfinfo.toString().length <= 0) {
		cflist = "";
	}

	for ( var one in jsonArraybasicinfo) {
		var s = "";
		if (jsonArraybasicinfo[one].brxb == "1") {
			s = "男";
		} else if (jsonArraybasicinfo[one].brxb == "2") {
			s = "女";
		} else {
			s = "不详";
		}

		basic += "<tr bgcolor=#C5E1EF><td colspan=6 style='height:30px;font-weight:bold;font-size:14px'>居民基本信息</td></tr><tr>"
				+"<td bgcolor='#cccccc'>姓名</td><td align=left>" + jsonArraybasicinfo[one].brxm
				+ "</td><td bgcolor='#cccccc'>性别</td><td align=center>" + s
				+ "</td><td bgcolor='#cccccc'>联系电话</td><td align=left>"
				+ jsonArraybasicinfo[one].lxdh + "</td></tr><tr><td bgcolor='#cccccc' >家庭地址</td><td colspan=3 align=left>"
				+ jsonArraybasicinfo[one].jtdz + "</td><td bgcolor='#cccccc' >临床诊断</td><td align=left>"
				+ lczd + "</td></tr>";
	}
	basic +="</table>";
	var htm="";
	htm += basic;
	htm +="<br/><br/>";
	htm += "<table id='cfinfomxbable'><tr bgcolor=#C5E1EF><td colspan=10 style='height:30px;font-weight:bold;font-size:14px'>处方明细信息</td></tr>";
	htm += cflist;
	var m = 0;
	nursestr +="<select id='nurseselect' name='username'>";
	for( var one = 0; one < jsonArraynurse.length; one++) {
		nursestr +="<option value='"+jsonArraynurse[one].username+"'>"+jsonArraynurse[one].name+"</option>";
	}
	nursestr +="</select>";
	htm += "<tr><td bgcolor=#cccccc>处理人</td><td>"
			+ nursestr
			+ "</td><td bgcolor=#cccccc colspan=2>本次就诊护理天数：<input name='inputtotal' type='text' size='3' maxlength='3' style='width:25px'>天<input name='current' type='hidden'></td><td colspan=2><span style='color:blue'>已护理</span><span id='yhl' style='color:blue'>0</span><span style='color:blue'>天</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input name='submit' type='button' value='处理' onClick='save()'></td><td></td><td colspan=3><a href='printpage.html?patient.cfsbnumber="+cfsbnumber+"'>打印输液单</a></td></tr>";
	htm += "</table>";
	$("#result").html(htm);
	//var other = "<table id='printsyd'><tr><td><a href='printpage.html?patient.cfsbnumber="+cfsbnumber+"'>打印输液单</a></td></tr></table>";
	//$("#other").html(other);
	var total = $.url.param("patient.total");
	var currents = $.url.param("patient.currents");
	var paramurl = $.url.param("patient.cfsbnumber");
	// urlparam = urlparam.serialize();
	paramurl = "patient.cfsbnumber=" + paramurl;
	//alert(paramurl);
	$.ajax( {

		// 处理程序
		url : "requestPatientcurrents.action",

		// 发送方式
		type : "post",

		// 数据格式
		dataType : "json",

		// 要传递的数据
		data : paramurl,
		// 回传函数
		success : function(data){
				var jsonArraycurrents = eval("(" + data + ")");
				//alert(jsonArraycurrents[0]);
				if(null!=jsonArraycurrents[0]){
					if (jsonArraycurrents[0].total.length != 0 && jsonArraycurrents[0].currents.length != 0) {
						$("input[name='inputtotal']").attr("disabled", true);
						$("input[name='inputtotal']").attr("value", jsonArraycurrents[0].total.Trim());
						$("input[name='current']").attr("value", jsonArraycurrents[0].currents.Trim());
						$("#yhl").html(jsonArraycurrents[0].currents.Trim());
						if(jsonArraycurrents[0].markdate.Trim() == jsonArraycurrents[0].today.Trim()){
							$("input[name='submit']").attr("disabled", true);
						}
						if (jsonArraycurrents[0].currents.Trim() == jsonArraycurrents[0].total.Trim()) {
							$("input[name='submit']").attr("disabled", true);
						}
					}
				}
			}

	});
	if (total.length != 0 && currents.length != 0) {
		$("input[name='inputtotal']").attr("disabled", true);
		$("input[name='inputtotal']").attr("value", total);
		$("#yhl").html(currents);
		if (currents == total) {
			$("input[name='submit']").attr("disabled", true);
		}
	}
	if(total.length==0){
		$("input[name='inputtotal']").attr("value", maxdays);
	}
	//alert("maxdays="+maxdays);
}
function save() {
	var cfsbnumber = $.url.param("patient.cfsbnumber");
	var currents = $.url.param("patient.currents");
	var current = $("input[name='current']").attr("value");
	//alert("current===="+current);
	if(currents.length=0||currents!=current){
		currents=current;
	}
	var total = $.url.param("patient.total");
	//var user = $("input[name='username']:checked").val();
	var user = $("select[name='username']").attr("value");
	var inputtotal = $("input[name='inputtotal']").attr("value");
	if (total.length != 0) {
		inputtotal = total;
	}
	if (cfsbnumber.length == 0) {
		alert("参数错误！");
		return false;
	}
	if (null == user || user.length == 0) {
		alert("请选择处理人");
		return false;
	}
	if (!$("input[name='inputtotal']").attr("disabled")
			&& 0 == inputtotal.length) {
		alert("请输入应护理次数");
		return false;
	} else if (!isInteger(inputtotal)) {
		alert("应护理次数应为正整数且不能以0开始");
		return false;
	} else {
		var param = "patient.optid=" + user + "&patient.cfsbnumber="
				+ cfsbnumber + "&patient.total=" + inputtotal
				+ "&patient.currents=" + currents + "";
		$.ajax( {

			url : "savePatientInfo.action",

			type : "post",

			dataType : "json",

			data : param,
			
			success : update_result

		});
	}
	
	$("input[name='submit']").attr("disabled", true);
	$("input[name='inputtotal']").attr("disabled", true);
}
function update_result(data) {
	var results = data;
	results = eval("(" + results + ")");

	if (results[0].results == "success") {
		alert("保存成功");
	} else {
		alert("保存失败");
	}
	var currents = $.url.param("patient.currents");
	var current = $("input[name='current']").attr("value");
	//alert("current===="+current);
	if(currents.length=0||currents!=current){
		currents=current;
	}
	//alert(currents);
	if(currents==null||currents.length==0){
		currents=0;
	}
	//alert(currents);
	
	cur = eval(parseInt(currents,10)+1);
	$("#yhl").html(cur);
}
// 判断是否为正整数
function isInteger(num) {
	var patrn = /^[1-9]*[1-9][0-9]*$/;

	if (!patrn.exec(num)) {
		return false;
	} else {
		return true;
	}
}
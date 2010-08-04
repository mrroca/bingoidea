var drugrows = 0;
var maxdays = 0;
$(document)
		.ready(
				function() {
					var selectsex = "";
					selectsex += "<select name='brxb' id='sex'>";
					selectsex += "<option value='0'>请选择</option>";
					selectsex += "<option value='1'>男</option>";
					selectsex += "<option value='2'>女</option>";
					selectsex += "<option value='3'>不详</option>";
					selectsex += "</select>";
					var selectpc = "";
					selectpc += "<select name='pc0' id='pc0'>";
					selectpc += "<option value='0'>&nbsp;</option>";
					selectpc += "<option value='bid'>bid</option>";
					selectpc += "<option value='tid'>tid</option>";
					selectpc += "<option value='qd'>qd</option>";
					selectpc += "<option value='st'>st</option>";
					selectpc += "<option value='qid'>qid</option>";
					selectpc += "<option value='biw'>biw</option>";
					selectpc += "<option value='q2h'>q2h</option>";
					selectpc += "<option value='qn'>qn</option>";
					selectpc += "<option value='qod'>qod</option>";
					selectpc += "<option value='qw'>qw</option>";
					selectpc += "<option value='w3d'>w3d</option>";
					selectpc += "</select>";
					var selectypyf = "";
					selectypyf += "<select name='ypyf0' id='ypyf0'>";
					selectypyf += "<option value='0'>&nbsp;</option>";
					selectypyf += "<option value='4'>注射给药</option>";
					selectypyf += "<option value='5'>吸入给药</option>";
					selectypyf += "<option value='401'>皮下注射</option>";
					selectypyf += "<option value='403'>肌肉注射</option>";
					selectypyf += "<option value='404'>静脉注射</option>";
					selectypyf += "<option value='405'>静脉滴注</option>";
					selectypyf += "<option value='406'>宫颈注射</option>";
					selectypyf += "<option value='407'>腹腔注射</option>";
					selectypyf += "<option value='601'>椎管内给药</option>";
					selectypyf += "<option value='602'>关节腔内给药</option>";
					selectypyf += "<option value='603'>胸膜腔给药</option>";
					selectypyf += "<option value='604'>腹腔给药</option>";
					selectypyf += "<option value='612'>气管内注入</option>";
					selectypyf += "<option value='902'>皮试</option>";
					selectypyf += "</select>";
					var htm = "";
					var basic = "";
					basic += "<table id='basic'>";
					basic += "<tr bgcolor=#C5E1EF>";
					basic += "<td colspan=6 style='height:30px;font-weight:bold;font-size:14px'>居民基本信息</td>";
					basic += "</tr>";
					basic += "<tr>";
					basic += "<td bgcolor='#cccccc'><font color=red size=3>*&nbsp;&nbsp;</font>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</td>";
					basic += "<td align=left>"
							+ "<input type='text' name='brxm' style=''>"
							+ "</td><a href='#' onClick='read()'>读卡</a>";
					basic += "<td bgcolor='#cccccc'><font color=red size=3>*&nbsp;&nbsp;</font>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</td>";
					basic += "<td align=center>" + selectsex + "</td>";
					basic += "<td bgcolor='#cccccc'>联系电话</td>";
					basic += "<td align=left>"
							+ "<input type='text' name='lxdh' style=''>"
							+ "</td>";
					basic += "</tr>";
					basic += "<tr>";
					basic += "<td bgcolor='#cccccc' ><font color=red size=3>*&nbsp;&nbsp;</font>家庭地址</td>";
					basic += "<td colspan=3 align=left>"
							+ "<input type='text' name='jtdz' style='width:100%'>"
							+ "</td>";
					basic += "<td bgcolor='#cccccc' ><font color=red size=3>*&nbsp;&nbsp;</font>临床诊断</td>";
					basic += "<td align=left>"
							+ "<input type='text' name='lczd' style=''>"
							+ "</td>";
					basic += "</tr>";
					basic += "</table>";
					var cfmx = "";
					cfmx += "<table id='cfinfomxbable'>";
					cfmx += "<tr bgcolor=#C5E1EF>";
					cfmx += "<td colspan=11 style='height:30px;font-weight:bold;font-size:14px'>处方明细信息</td>";
					cfmx += "</tr>";
					cfmx += "<tr bgcolor='#cccccc'>";
					cfmx += "<td>组套</td>";
					cfmx += "<td width='145px'>药品名称</td>";
					cfmx += "<td width='70px'>规格</td>";
					cfmx += "<td>单位</td>";
					cfmx += "<td>剂量</td>";
					cfmx += "<td>剂量单位</td>";
					cfmx += "<td>频次</td>";
					cfmx += "<td>天数</td>";
					cfmx += "<td>用法</td>";
					cfmx += "<td width='100px'>日期</td>";
					cfmx += "<td>操作</td>";
					cfmx += "</tr>";
					cfmx += "<tr id='drug0'>";
					cfmx += "<td><input type='text' name='ztbs0' style='width:100%'></td>";
					cfmx += "<td><input type='text' name='ypmc0' style='width:140px;overflow:hidden'><input type='hidden' name='ypxh0' style=''></td>";
					cfmx += "<td><input type='text' name='ypgg0' style='width:100%'></td>";
					cfmx += "<td><input type='text' name='ypdw0' style='width:100%'></td>";
					cfmx += "<td><input type='text' name='ypjl0' style='width:100%'></td>";
					cfmx += "<td><input type='text' name='jldw0' style='width:100%'></td>";
					cfmx += "<td>" + selectpc + "</td>";
					cfmx += "<td><input type='text' name='days0' style='width:100%'></td>";
					cfmx += "<td>" + selectypyf + "</td>";
					cfmx += "<td><input name='kfrq0' type='text' size='20' value='' class=\"Wdate\" onClick=\"WdatePicker({skin:'whyGreen'})\" style='width:100%;background-color: transparent;'></td>";
					cfmx += "<td></td>";
					cfmx += "</tr>";
					cfmx += "<tr>";
					cfmx += "<td colspan=6 style='text-align:right;border-right:none'><input type='button' name='submit' value='保存' onClick='save()'></td>";
					cfmx += "<td colspan=5 style='text-align:right'><a href='#' onClick='adddrug()'>新增药品</a></td>";
					cfmx += "</tr>";
					cfmx += "</table>";
					htm += basic;
					htm += cfmx;
					$("#result").html(htm);
					$("input[name='ypmc0']")
							.blur(
									function() {
										if ($("input[name='ypmc0']").attr(
												"value").length == 0) {
											alert("药品名称为必填信息！");
										}
									});
					$("input[name='ztbs0']").blur(
							function() {
								if (!isInteger($("input[name='ztbs0']").attr(
										"value"))) {
									alert("组套标识应为正整数！");
								}
							});
					$("input[name='days0']").blur(
							function() {
								if (!isInteger($("input[name='days0']").attr(
										"value"))) {
									alert("天数应为正整数！");
								}
							});
					$("input[name^='ypmc']")
							.autocomplete(
									"requestDrugInfoByPYDM.action",
									{
										multiple : false,
										max : 5000,
										scroll : true,
										scrollHeight : 300,
										dataType : 'json',
										parse : function(data) {
											return $.map(eval(data), function(
													row) {
												return {
													data : row,
													value : row.ypmc + " <"
															+ row.ypmc + ">",
													result : row.ypmc
												};
											});
										},
										formatItem : function(item) {
											// alert(item.ypmc);
											return item.ypmc
													+ "<span align=left><font color='green'>"
													+ item.ypgg
													+ "</font></span>";
										}
									});
					$("input[name^='ypmc']").result(adddruginfo);
				});
function adddruginfo(event, item, formatted) {
	var eltname = "";
	if (event.srcElement) {
		eltname = event.srcElement.name;
	} else if (event.target) {
		eltname = event.target.name;
	}
	var index = 0;
	// index = eltname.substring(4,eltname.length-1);
	// alert(eltname);
	var temp = "";
	temp = eltname.substring(4, eltname.length);
	index = parseInt(temp, 10);
	$("input[name='ypgg" + index + "']").attr("value", item.ypgg);
	$("input[name='ypxh" + index + "']").attr("value", item.ypxh);
	$("input[name='ypdw" + index + "']").attr("value", item.ypdw);
	$("input[name='ypjl" + index + "']").attr("value", item.ypjl);
	$("input[name='jldw" + index + "']").attr("value", item.jldw);
}
function deletedrug(elementid){
	//alert(elementid);
	//alert($("#"+elementid).html());
	//alert($("#"+elementid+1).html());
	if($("#"+elementid+1).html()==null){
		
	}
	$("#"+elementid).remove();
}
function adddrug() {
	var cfmx = "";
	var rows = drugrows;
	drugrows += 1;
	var selectpc = "";
	selectpc += "<select name='pc" + drugrows + "' id='pc" + drugrows + "'>";
	selectpc += "<option value='0'>&nbsp;</option>";
	selectpc += "<option value='bid'>bid</option>";
	selectpc += "<option value='tid'>tid</option>";
	selectpc += "<option value='qd'>qd</option>";
	selectpc += "<option value='st'>st</option>";
	selectpc += "<option value='qid'>qid</option>";
	selectpc += "<option value='biw'>biw</option>";
	selectpc += "<option value='q2h'>q2h</option>";
	selectpc += "<option value='qn'>qn</option>";
	selectpc += "<option value='qod'>qod</option>";
	selectpc += "<option value='qw'>qw</option>";
	selectpc += "<option value='w3d'>w3d</option>";
	selectpc += "</select>";
	var selectypyf = "";
	selectypyf += "<select name='ypyf" + drugrows + "' id='ypyf" + drugrows
			+ "'>";
	selectypyf += "<option value='0'>&nbsp;</option>";
	selectypyf += "<option value='4'>注射给药</option>";
	selectypyf += "<option value='5'>吸入给药</option>";
	selectypyf += "<option value='401'>皮下注射</option>";
	selectypyf += "<option value='403'>肌肉注射</option>";
	selectypyf += "<option value='404'>静脉注射</option>";
	selectypyf += "<option value='405'>静脉滴注</option>";
	selectypyf += "<option value='406'>宫颈注射</option>";
	selectypyf += "<option value='407'>腹腔注射</option>";
	selectypyf += "<option value='601'>椎管内给药</option>";
	selectypyf += "<option value='602'>关节腔内给药</option>";
	selectypyf += "<option value='603'>胸膜腔给药</option>";
	selectypyf += "<option value='604'>腹腔给药</option>";
	selectypyf += "<option value='612'>气管内注入</option>";
	selectypyf += "<option value='902'>皮试</option>";
	selectypyf += "</select>";
	cfmx += "<tr id='drug" + drugrows + "'>";
	cfmx += "<td><input type='text' name='ztbs" + drugrows
			+ "' style='width:100%'></td>";
	cfmx += "<td><input type='text' name='ypmc" + drugrows
			+ "' style='width:100%'><input type='hidden' name='ypxh" + drugrows
			+ "' style=''</td>";
	cfmx += "<td><input type='text' name='ypgg" + drugrows
			+ "' style='width:100%'></td>";
	cfmx += "<td><input type='text' name='ypdw" + drugrows
			+ "' style='width:100%'></td>";
	cfmx += "<td><input type='text' name='ypjl" + drugrows
			+ "' style='width:100%'></td>";
	cfmx += "<td><input type='text' name='jldw" + drugrows
			+ "' style='width:100%'></td>";
	cfmx += "<td>" + selectpc + "</td>";
	cfmx += "<td><input type='text' name='days" + drugrows
			+ "' style='width:100%'></td>";
	cfmx += "<td>" + selectypyf + "</td>";
	cfmx += "<td><input name='kfrq"
			+ drugrows
			+ "' type='text' size='20' value='' class=\"Wdate\" onClick=\"WdatePicker({skin:'whyGreen'})\" style='width:100%;background-color: transparent;'></td>";
	cfmx += "<td><a href='#' onClick=\"deletedrug('drug"+drugrows+"')\">删除</a></td>";
	cfmx += "</tr>";
	//alert($("tr[id^=drug]").length);
	var addrow=0;
	$("tr[id^=drug]").each(function(){
		var temprow=$(this).attr("id").substring(4,$(this).attr("id").length);
		if(parseInt(temprow,10)>addrow){
			addrow = parseInt(temprow,10);
		}
	});
	$("#drug" + addrow).after(cfmx);
	if ($("input[name='kfrq0']").attr("value").length != 0) {
		$("input[name='kfrq" + drugrows + "']").attr("value",
				$("input[name='kfrq0']").attr("value"));
	}
	if ($("input[name='days0']").attr("value").length != 0) {
		$("input[name='days" + drugrows + "']").attr("value",
				$("input[name='days0']").attr("value"));
	}
	if ($("input[name='ztbs0']").attr("value").length != 0) {
		$("input[name='ztbs" + drugrows + "']").attr("value",
				$("input[name='ztbs0']").attr("value"));
	}
	if ($("select[name='pc0']").attr("value").length != 0) {
		$("select[name='pc" + drugrows + "']").attr("value",
				$("select[name='pc0']").attr("value"));
	}
	if ($("select[name='ypyf0']").attr("value").length != 0) {
		$("select[name='ypyf" + drugrows + "']").attr("value",
				$("select[name='ypyf0']").attr("value"));
	}
	$("input[name^='ypmc']").autocomplete(
			"requestDrugInfoByPYDM.action",
			{
				multiple : false,
				max : 5000,
				scroll : true,
				scrollHeight : 300,
				dataType : 'json',
				parse : function(data) {
					return $.map(eval(data), function(row) {
						return {
							data : row,
							value : row.ypmc + " <" + row.ypmc + ">",
							result : row.ypmc
						};
					});
				},
				formatItem : function(item) {
					// alert(item.ypmc);
					return item.ypmc + "<span align=left><font color='green'>"
							+ item.ypgg + "</font></span>";
				}
			}).result(adddruginfo);
	$("input[name='ypmc" + drugrows + "']").blur(function() {
		if ($("input[name='ypmc" + drugrows + "']").attr("value").length == 0) {
			alert("药品名称为必填信息！");
		}
	});
	$("input[name='ztbs" + drugrows + "']").blur(function() {
		if (!isInteger($("input[name='ztbs" + drugrows + "']").attr("value"))) {
			alert("组套标识应为正整数！");
		}
	});
	$("input[name='days"+ drugrows +"']").blur(function() {
		if (!isInteger($("input[name='days" + drugrows + "']").attr("value"))) {
			alert("天数应为正整数！");
		}
	});
}
function save() {
	var ztbs = "";
	var ypmc = "";
	var ypgg = "";
	var ypdw = "";
	var ypjl = "";
	var jldw = "";
	var pc = "";
	var days = "";
	var ypyf = "";
	var kfrq = "";
	var ypxh = "";
	var brxm = $("input[name='brxm']").attr("value");
	var brxb = $("select[name='brxb']").attr("value");
	var lxdh = $("input[name='lxdh']").attr("value");
	var jtdz = $("input[name='jtdz']").attr("value");
	var lczd = $("input[name='lczd']").attr("value");
	
	$("input[name^=ypxh]").each(function(){
		if($(this).attr("value").length==0){
			ypxh += "@|-|-|";
		}else{
			ypxh += $(this).attr("value") + "|-|-|";
		}
	});
	$("input[name^=ztbs]").each(function(){
		if($(this).attr("value").length==0){
			ztbs += "@|-|-|";
		}else{
			ztbs += $(this).attr("value") + "|-|-|";
		}
	});
	$("input[name^=ypmc]").each(function(){
		if($(this).attr("value").length==0){
			ypmc += "@|-|-|";
		}else{
			ypmc += $(this).attr("value") + "|-|-|";
		}
	});
	$("input[name^=ypgg]").each(function(){
		if($(this).attr("value").length==0){
			ypgg += "@|-|-|";
		}else{
			ypgg += $(this).attr("value") + "|-|-|";
		}
	});
	$("input[name^=ypdw]").each(function(){
		if($(this).attr("value").length==0){
			ypdw += "@|-|-|";
		}else{
			ypdw += $(this).attr("value") + "|-|-|";
		}
	});
	$("input[name^=ypjl]").each(function(){
		if($(this).attr("value").length==0){
			ypjl += "@|-|-|";
		}else{
			ypjl += $(this).attr("value") + "|-|-|";
		}
	});
	$("input[name^=jldw]").each(function(){
		if($(this).attr("value").length==0){
			jldw += "@|-|-|";
		}else{
			jldw += $(this).attr("value") + "|-|-|";
		}
	});
	$("select[name^=pc]").each(function(){
		if($(this).attr("value").length==0){
			pc += "@|-|-|";
		}else{
			pc += $(this).attr("value") + "|-|-|";
		}
	});
	$("input[name^=days]").each(function(){
		if($(this).attr("value").length==0){
			days += "@|-|-|";
		}else{
			days += $(this).attr("value") + "|-|-|";
		}
	});
	$("select[name^=ypyf]").each(function(){
		if($(this).attr("value").length==0){
			ypyf += "@|-|-|";
		}else{
			ypyf += $(this).attr("value") + "|-|-|";
		}
	});
	$("input[name^=kfrq]").each(function(){
		if($(this).attr("value").length==0){
			kfrq += "@|-|-|";
		}else{
			kfrq += $(this).attr("value") + "|-|-|";
		}
	});

	if (brxm.length == 0) {
		alert("病人姓名不能为空！");
		return false;
	}
	if (brxb.length == 0) {
		alert("病人性别不能为空！");
		return false;
	}
	if (lczd.length == 0) {
		alert("临床诊断不能为空！");
		return false;
	}
	if (jtdz.length == 0) {
		alert("家庭地址不能为空！");
		return false;
	}
	
	ypxh = ypxh.substring(0, ypxh.length-5);
	ztbs = ztbs.substring(0, ztbs.length-5);
	ypmc = ypmc.substring(0, ypmc.length-5);
	ypgg = ypgg.substring(0, ypgg.length-5);
	ypdw = ypdw.substring(0, ypdw.length-5);
	ypjl = ypjl.substring(0, ypjl.length-5);
	jldw = jldw.substring(0, jldw.length-5);
	pc   = pc.substring(0, pc.length-5);
	days = days.substring(0, days.length-5);
	ypyf = ypyf.substring(0, ypyf.length-5);
	kfrq = kfrq.substring(0, kfrq.length-5);
	
	var param = "";
		param +="patient.ypxh="+ypxh
		+"&patient.ztbs="+ztbs
		+"&patient.ypmc="+ypmc
		+"&patient.ypgg="+ypgg
		+"&patient.ypdw="+ypdw
		+"&patient.ypjl="+ypjl
		+"&patient.jldw="+jldw
		+"&patient.pc="+pc
		+"&patient.days="+days
		+"&patient.ypyf="+ypyf
		+"&patient.date="+kfrq
		+"&patient.patientname="+brxm
		+"&patient.sex="+brxb
		+"&patient.phone="+lxdh
		+"&patient.address="+jtdz
		+"&patient.lczd="+lczd;
		
	$.ajax( {

		url : "requestsaveWpPatientInfo.action",

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
		location.href="patientlist.html";
	} else {
		alert("保存失败");
	}
	
}
function read() {
	// document.onkeydown = function(event) {
	// var e = event || window.event;
	// var keyCode = e.keyCode ? e.keyCode : e.which ? e.which : e.charCode;
	// if (keyCode == 0x0D) {
	readcard();
	// }
	// }
}
function update_basicinfo(data) {
	// alert(data);
	var jsonArray = eval("(" + data + ")");

	$("input[name='brxm']").attr("value", jsonArray[0].brxm);
	$("select[name='brxb']").attr("value", jsonArray[0].brxb);
	$("input[name='lxdh']").attr("value", jsonArray[0].lxdh);
	$("input[name='jtdz']").attr("value", jsonArray[0].hkdz);


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
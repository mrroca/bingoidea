//var ybkh = "";
//var brxm = "";
//var brxb = "";
//var phone = "";
//var hkdz = "";
//var jc = "";
//var czmc = "";
//var brnl = "";
//var brhy = "";
//var gms = "";
//var xx = "";
//var mzzd = "";
//var description = "";
//var treatmentid = "";
var globalElement = "";
var globalColor = "";
// var globalAppid = "";
$(document).ready(function() {
	$("#treatmentTable").hide();
	$.ajax( {
		url : "getAllTreatment.action",
		type : "post",
		dataType : "json",
		success : update_page
	});
	$("#treatmentfirstpage").click(function() {
		$.ajax( {
			url : "getAllTreatment.action",
			type : "post",
			dataType : "json",
			success : update_page
		});
	});
	$("#treatmentprevpage").click(function() {
		$.ajax( {
			url : "getAllTreatment.action",
			type : "post",
			dataType : "json",
			success : treatmentprevpage
		});
	});
	$("#treatmentnextpage").click(function() {
		$.ajax( {
			url : "getAllTreatment.action",
			type : "post",
			dataType : "json",
			success : treatmentnextpage
		});
	});
	$("#treatmentlastpage").click(function() {
		$.ajax( {
			url : "getAllTreatment.action",
			type : "post",
			dataType : "json",
			success : treatmentlastpage
		});
	});
});
function update_page(result) {
	var str = "<table id='patienttable'>";
	str += "<tr bgcolor=#C5E1EF><td colspan=7 style='height:30px;font-weight:bold;font-size:14px'>居民就诊信息</td></tr>";
	str += "<tr bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px'><b>姓名</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>性别</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>家庭住址</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>计次</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>操作名称</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>日期</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>医生</b></td></tr>";
	var jsonArray = eval("(" + result + ")");
	var firstpage = 11;
	if (jsonArray.length <= firstpage) {
		firstpage = jsonArray.length;
	}
	var row = 0;
	for ( var one = 0; one < firstpage; one++) {
		var s = "";
		if (jsonArray[one].brxb == "1") {
			s = "男";
		} else if (jsonArray[one].brxb == "2") {
			s = "女";
		} else if (jsonArray[one].brxb == "0") {
			s = "未知性别";
		} else if (jsonArray[one].brxb == "9") {
			s = "未说明的性别";
		}
		var c = "";
		if (jsonArray[one].czmc == "1") {
			c = "换药";
		} else if (jsonArray[one].czmc == "2") {
			c = "拆线";
		} else if (jsonArray[one].czmc == "3") {
			c = "缝合";
		} else if (jsonArray[one].czmc == "4") {
			c = "抽血";
		} else if (jsonArray[one].czmc == "5") {
			c = "取样";
		}else if (jsonArray[one].czmc == "6") {
			c = "测血糖";
		}else if (jsonArray[one].czmc == "9") {
			c = "其他";
		}else if (jsonArray[one].czmc == "99") {
			c = "其他";
		}
		var color = "";
		if (one % 2 == 0) {
			color = '#ffffff';
		} else {
			color = '#E8F2FE';
		}
		// ybkh = jsonArray[one].ybkh;
		// alert("11111111111 "+jsonArray[one].ybkh)
		// alert("dhdhdh "+jsonArray[one].phone);
		// alert("hkdzhkdz "+jsonArray[one].hkdz);
		str += "<tr bgcolor=" + color + " onClick=\"mclick(this,'" + color
				+ "','" + jsonArray[one].ybkh + "','" + jsonArray[one].brxm
				+ "','" + jsonArray[one].brxb + "','" + jsonArray[one].phone
				+ "','" + jsonArray[one].hkdz + "','" + jsonArray[one].jc
				+ "','" + jsonArray[one].czmc + "','" + jsonArray[one].brnl
				+ "','" + jsonArray[one].brhy + "','" + jsonArray[one].gms
				+ "','" + jsonArray[one].xx + "','" + jsonArray[one].mzzd
				+ "','" + jsonArray[one].description + "','"
				+ jsonArray[one].treatmentid + "','" + jsonArray[one].username
				+ "','" + jsonArray[one].markdate
				+ "')\" ondblclick=\"mdbclick('" + jsonArray[one].ybkh + "','"
				+ jsonArray[one].brxm + "','" + jsonArray[one].brxb + "','"
				+ jsonArray[one].phone + "','" + jsonArray[one].hkdz + "','"
				+ jsonArray[one].jc + "','" + jsonArray[one].czmc + "','"
				+ jsonArray[one].brnl + "','" + jsonArray[one].brhy + "','"
				+ jsonArray[one].gms + "','" + jsonArray[one].xx + "','"
				+ jsonArray[one].mzzd + "','" + jsonArray[one].description
				+ "','" + jsonArray[one].treatmentid + "','"
				+ jsonArray[one].username + "','" + jsonArray[one].markdate
				+ "')\"><td>" + jsonArray[one].brxm + "</td><td>" + s
				+ "</td><td>";
		str += jsonArray[one].hkdz + "</td><td>" + jsonArray[one].jc
				+ "</td><td>" + c + "</td><td>" + jsonArray[one].markdate
				+ "</td><td>";
		str += jsonArray[one].name + "</td></tr>";
		row++;
	}
	var rowstr = "";
	if (row < 11) {
		for ( var i = 0; i < (11 - row); i++) {
			rowstr += "<tr><td >&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td></tr>";
		}
	}
	str += rowstr;
	str += "</table>";
	document.getElementById("treatmentpageNo").innerText = "1";
	var treatmenttotalPages = jsonArray.length / 11 + "";
	if (treatmenttotalPages.indexOf(".") >= 0) {
		treatmenttotalPages = parseInt(treatmenttotalPages, 10) + 1;
	}
	document.getElementById("treatmentcurrentpage").value = 1;
	document.getElementById("treatmenttotalPages").innerText = treatmenttotalPages
			+ "";
	document.getElementById("treatmenttotalRecordsUI").innerText = jsonArray.length
			+ "";
//	var wholehtm = "";
//	wholehtm += "<table border='0' cellpadding='0' cellspacing='0' width='100%' height='557'>";
//	wholehtm += "<tr>";
//	wholehtm += "<td valign='top' colspan='3' height='61'>";
//	wholehtm += "<img border='0' src='image/ja_b_01.gif' width='256' height='61'><img border='0' src='image/ja_b_02.gif' width='619' height='61'><img border='0' src='image/ja_b_04.gif' width='269' height='61'></td>";
//	wholehtm += "</tr>";
//	wholehtm += "<tr>";
//	wholehtm += "<td valign='top' width='21'>";
//	wholehtm += "<img border='0' src='image/ja_b_07.gif' width='19' height='453'></td>";
//	wholehtm += "<td valign='top' width='1106'>";
//	wholehtm += "<div>" + str + "</div>";
//	wholehtm += "</td>";
//	wholehtm += "<td valign='top' height='446' width='16'>";
//	wholehtm += "<img border='0' src='image/ja_b_10.gif' width='25' height='453'></td>";
//	wholehtm += "</tr>";
//	wholehtm += "<tr>";
//	wholehtm += "<td valign='top' colspan='3' height='50'>";
//	wholehtm += "<img border='0' src='image/ja_b_11.gif' width='19' height='19'><img border='0' src='image/ja_b_11.gif' width='1100' height='19'><img border='0' src='image/ja_b_14.gif' width='25' height='19'></td>";
//	wholehtm += "</tr>";
//	wholehtm += "</table>";
//	wholehtm += "</form>";
	$("#result").html(str);
	$("#treatmentTable").show();
}
function treatmentlastpage(result) {
	var str = "<table id='patienttable'>";
	str += "<tr bgcolor=#C5E1EF><td colspan=7 style='height:30px;font-weight:bold;font-size:14px'>居民就诊信息</td></tr>";
	str += "<tr bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px'><b>姓名</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>性别</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>家庭住址</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>计次</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>操作名称</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>日期</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>医生</b></td></tr>";
	var jsonArray = eval("(" + result + ")");
	var to = jsonArray.length;
	var from;
	var treatmenttotalPages = 1;
	if (jsonArray.length <= 11) {
		from = 0;
	} else {
		var temp = jsonArray.length / 11 + "";
		if (temp.indexOf(".") < 0) {
			from = parseInt(temp - 1, 10) * 11;
		} else {
			from = parseInt(temp, 10) * 11;
		}
		treatmenttotalPages = jsonArray.length / 11 + "";
		if (treatmenttotalPages.indexOf(".") >= 0) {
			treatmenttotalPages = parseInt(treatmenttotalPages, 10) + 1;
		}
	}
	var row = 0;
	for ( var one = from; one < to; one++) {
		var s = "";
		if (jsonArray[one].brxb == "1") {
			s = "男";
		} else if (jsonArray[one].brxb == "2") {
			s = "女";
		} else if (jsonArray[one].brxb == "0") {
			s = "未知性别";
		} else if (jsonArray[one].brxb == "9") {
			s = "未说明的性别";
		}
		var c = "";
		if (jsonArray[one].czmc == "1") {
			c = "换药";
		} else if (jsonArray[one].czmc == "2") {
			c = "拆线";
		} else if (jsonArray[one].czmc == "3") {
			c = "缝合";
		} else if (jsonArray[one].czmc == "4") {
			c = "抽血";
		} else if (jsonArray[one].czmc == "5") {
			c = "取样";
		}else if (jsonArray[one].czmc == "6") {
			c = "测血糖";
		}else if (jsonArray[one].czmc == "9") {
			c = "其他";
		}else if (jsonArray[one].czmc == "99") {
			c = "其他";
		}
		var color = "";

		if (one % 2 == 0) {
			color = '#ffffff';
		} else {
			color = '#E8F2FE';
		}
		str += "<tr bgcolor=" + color + " onClick=\"mclick(this,'" + color
				+ "','" + jsonArray[one].ybkh + "','" + jsonArray[one].brxm
				+ "','" + jsonArray[one].brxb + "','" + jsonArray[one].phone
				+ "','" + jsonArray[one].hkdz + "','" + jsonArray[one].jc
				+ "','" + jsonArray[one].czmc + "','" + jsonArray[one].brnl
				+ "','" + jsonArray[one].brhy + "','" + jsonArray[one].gms
				+ "','" + jsonArray[one].xx + "','" + jsonArray[one].mzzd
				+ "','" + jsonArray[one].description + "','"
				+ jsonArray[one].treatmentid + "','" + jsonArray[one].username
				+ "','" + jsonArray[one].markdate
				+ "')\" ondblclick=\"mdbclick('" + jsonArray[one].ybkh + "','"
				+ jsonArray[one].brxm + "','" + jsonArray[one].brxb + "','"
				+ jsonArray[one].phone + "','" + jsonArray[one].hkdz + "','"
				+ jsonArray[one].jc + "','" + jsonArray[one].czmc + "','"
				+ jsonArray[one].brnl + "','" + jsonArray[one].brhy + "','"
				+ jsonArray[one].gms + "','" + jsonArray[one].xx + "','"
				+ jsonArray[one].mzzd + "','" + jsonArray[one].description
				+ "','" + jsonArray[one].treatmentid + "','"
				+ jsonArray[one].username + "','" + jsonArray[one].markdate
				+ "')\"><td>" + jsonArray[one].brxm + "</td><td>" + s
				+ "</td><td>";
		str += jsonArray[one].hkdz + "</td><td>" + jsonArray[one].jc
				+ "</td><td>" + c + "</td><td>" + jsonArray[one].markdate
				+ "</td><td>";
		str += jsonArray[one].name + "</td></tr>";
		row++;
	}
	var rowstr = "";
	if (row < 11) {
		for ( var i = 0; i < (11 - row); i++) {
			rowstr += "<tr><td >&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td></tr>";
		}
	}
	str += rowstr;
	str += "</table>";
	document.getElementById("treatmentpageNo").innerText = treatmenttotalPages;
	document.getElementById("treatmentcurrentpage").value = treatmenttotalPages;
	document.getElementById("treatmenttotalPages").innerText = treatmenttotalPages;
	document.getElementById("treatmenttotalRecordsUI").innerText = jsonArray.length;
	$("#result").html("");
//	var wholehtm = "";
//	wholehtm += "<table border='0' cellpadding='0' cellspacing='0' width='1143' height='557'>";
//	wholehtm += "<tr>";
//	wholehtm += "<td valign='top' colspan='3' height='61'>";
//	wholehtm += "<img border='0' src='image/ja_b_01.gif' width='256' height='61'><img border='0' src='image/ja_b_02.gif' width='619' height='61'><img border='0' src='image/ja_b_04.gif' width='269' height='61'></td>";
//	wholehtm += "</tr>";
//	wholehtm += "<tr>";
//	wholehtm += "<td valign='top' width='21'>";
//	wholehtm += "<img border='0' src='image/ja_b_07.gif' width='19' height='453'></td>";
//	wholehtm += "<td valign='top' width='1106'>";
//	wholehtm += "<div>" + str + "</div>";
//	wholehtm += "</td>";
//	wholehtm += "<td valign='top' height='446' width='16'>";
//	wholehtm += "<img border='0' src='image/ja_b_10.gif' width='25' height='453'></td>";
//	wholehtm += "</tr>";
//	wholehtm += "<tr>";
//	wholehtm += "<td valign='top' colspan='3' height='50'>";
//	wholehtm += "<img border='0' src='image/ja_b_11.gif' width='19' height='19'><img border='0' src='image/ja_b_11.gif' width='1100' height='19'><img border='0' src='image/ja_b_14.gif' width='25' height='19'></td>";
//	wholehtm += "</tr>";
//	wholehtm += "</table>";
//	wholehtm += "</form>";
	$("#result").html(str);
	$("#treatmentTable").show();
}
function treatmentprevpage(result) {
	var str = "<table id='patienttable'>";
	str += "<tr bgcolor=#C5E1EF><td colspan=7 style='height:30px;font-weight:bold;font-size:14px'>居民就诊信息</td></tr>";
	str += "<tr bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px'><b>姓名</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>性别</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>家庭住址</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>计次</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>操作名称</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>日期</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>医生</b></td></tr>";
	var jsonArray = eval("(" + result + ")");
	var from;
	var to;
	var treatmenttotalPages = 1;
	var treatmentcurrentpage = document.getElementById("treatmentcurrentpage").value;
	treatmentcurrentpage = parseInt(treatmentcurrentpage);
	// alert("cccccccc "+treatmentcurrentpage);
	if (treatmentcurrentpage == 1) {
		return;
	}
	from = (treatmentcurrentpage - 2) * 11;
	to = (treatmentcurrentpage - 1) * 11;
	// alert("ssssssss"+from);
	// alert("qqqqqqqq "+to);
	if (jsonArray.length > 11) {
		treatmenttotalPages = jsonArray.length / 11 + "";
		if (treatmenttotalPages.indexOf(".") >= 0) {
			treatmenttotalPages = parseInt(treatmenttotalPages, 10) + 1;
		}
	}
	var row = 0;
	for ( var one = from; one < to; one++) {
		var s = "";
		if (jsonArray[one].brxb == "1") {
			s = "男";
		} else if (jsonArray[one].brxb == "2") {
			s = "女";
		} else if (jsonArray[one].brxb == "0") {
			s = "未知性别";
		} else if (jsonArray[one].brxb == "9") {
			s = "未说明的性别";
		}
		var c = "";
		if (jsonArray[one].czmc == "1") {
			c = "换药";
		} else if (jsonArray[one].czmc == "2") {
			c = "拆线";
		} else if (jsonArray[one].czmc == "3") {
			c = "缝合";
		} else if (jsonArray[one].czmc == "4") {
			c = "抽血";
		} else if (jsonArray[one].czmc == "5") {
			c = "取样";
		}else if (jsonArray[one].czmc == "6") {
			c = "测血糖";
		}else if (jsonArray[one].czmc == "9") {
			c = "其他";
		}else if (jsonArray[one].czmc == "99") {
			c = "其他";
		}
		var color = "";
		if (one % 2 == 0) {
			color = '#ffffff';
		} else {
			color = '#E8F2FE';
		}
		str += "<tr bgcolor=" + color + " onClick=\"mclick(this,'" + color
				+ "','" + jsonArray[one].ybkh + "','" + jsonArray[one].brxm
				+ "','" + jsonArray[one].brxb + "','" + jsonArray[one].phone
				+ "','" + jsonArray[one].hkdz + "','" + jsonArray[one].jc
				+ "','" + jsonArray[one].czmc + "','" + jsonArray[one].brnl
				+ "','" + jsonArray[one].brhy + "','" + jsonArray[one].gms
				+ "','" + jsonArray[one].xx + "','" + jsonArray[one].mzzd
				+ "','" + jsonArray[one].description + "','"
				+ jsonArray[one].treatmentid + "','" + jsonArray[one].username
				+ "','" + jsonArray[one].markdate
				+ "')\" ondblclick=\"mdbclick('" + jsonArray[one].ybkh + "','"
				+ jsonArray[one].brxm + "','" + jsonArray[one].brxb + "','"
				+ jsonArray[one].phone + "','" + jsonArray[one].hkdz + "','"
				+ jsonArray[one].jc + "','" + jsonArray[one].czmc + "','"
				+ jsonArray[one].brnl + "','" + jsonArray[one].brhy + "','"
				+ jsonArray[one].gms + "','" + jsonArray[one].xx + "','"
				+ jsonArray[one].mzzd + "','" + jsonArray[one].description
				+ "','" + jsonArray[one].treatmentid + "','"
				+ jsonArray[one].username + "','" + jsonArray[one].markdate
				+ "')\"><td>" + jsonArray[one].brxm + "</td><td>" + s
				+ "</td><td>";
		str += jsonArray[one].hkdz + "</td><td>" + jsonArray[one].jc
				+ "</td><td>" + c + "</td><td>" + jsonArray[one].markdate
				+ "</td><td>";
		str += jsonArray[one].name + "</td></tr>";
		row++;
	}
	var rowstr = "";
	if (row < 11) {
		for ( var i = 0; i < (11 - row); i++) {
			rowstr += "<tr><td >&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td></tr>";
		}
	}
	str += rowstr;
	str += "</table>";
	document.getElementById("treatmentpageNo").innerText = treatmentcurrentpage - 1;
	document.getElementById("treatmentcurrentpage").value = treatmentcurrentpage - 1;
	document.getElementById("treatmenttotalPages").innerText = treatmenttotalPages;
	document.getElementById("treatmenttotalRecordsUI").innerText = jsonArray.length;
	$("#result").html("");
//	var wholehtm = "";
//	wholehtm += "<table border='0' cellpadding='0' cellspacing='0' width='1143' height='557'>";
//	wholehtm += "<tr>";
//	wholehtm += "<td valign='top' colspan='3' height='61'>";
//	wholehtm += "<img border='0' src='image/ja_b_01.gif' width='256' height='61'><img border='0' src='image/ja_b_02.gif' width='619' height='61'><img border='0' src='image/ja_b_04.gif' width='269' height='61'></td>";
//	wholehtm += "</tr>";
//	wholehtm += "<tr>";
//	wholehtm += "<td valign='top' width='21'>";
//	wholehtm += "<img border='0' src='image/ja_b_07.gif' width='19' height='453'></td>";
//	wholehtm += "<td valign='top' width='1106'>";
//	wholehtm += "<div>" + str + "</div>";
//	wholehtm += "</td>";
//	wholehtm += "<td valign='top' height='446' width='16'>";
//	wholehtm += "<img border='0' src='image/ja_b_10.gif' width='25' height='453'></td>";
//	wholehtm += "</tr>";
//	wholehtm += "<tr>";
//	wholehtm += "<td valign='top' colspan='3' height='50'>";
//	wholehtm += "<img border='0' src='image/ja_b_11.gif' width='19' height='19'><img border='0' src='image/ja_b_11.gif' width='1100' height='19'><img border='0' src='image/ja_b_14.gif' width='25' height='19'></td>";
//	wholehtm += "</tr>";
//	wholehtm += "</table>";
//	wholehtm += "</form>";
	$("#result").html(str);
	$("#treatmentTable").show();
}
function treatmentnextpage(result) {
	var str = "<table id='patienttable'>";
	str += "<tr bgcolor=#C5E1EF><td colspan=7 style='height:30px;font-weight:bold;font-size:14px'>居民就诊信息</td></tr>";
	str += "<tr bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px'><b>姓名</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>性别</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>家庭住址</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>计次</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>操作名称</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>日期</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>医生</b></td></tr>";
	var jsonArray = eval("(" + result + ")");
	var from;
	var to;
	var treatmenttotalPages = 1;
	var treatmentcurrentpage = document.getElementById("treatmentcurrentpage").value;
	// alert("11111 "+treatmentcurrentpage)
	treatmentcurrentpage = parseInt(treatmentcurrentpage);
	// alert(treatmentcurrentpage);
	if (jsonArray.length > 11) {
		treatmenttotalPages = jsonArray.length / 11 + "";
		if (treatmenttotalPages.indexOf(".") >= 0) {
			treatmenttotalPages = parseInt(treatmenttotalPages, 10) + 1;
		}
	}
	if (treatmentcurrentpage == treatmenttotalPages) {
		return;
	}
	from = treatmentcurrentpage * 11;
	to = (treatmentcurrentpage + 1) * 11;
	if (treatmentcurrentpage == (treatmenttotalPages - 1)) {
		to = jsonArray.length;
	}
	var row = 0;
	for ( var one = from; one < to; one++) {
		var s = "";
		if (jsonArray[one].brxb == "1") {
			s = "男";
		} else if (jsonArray[one].brxb == "2") {
			s = "女";
		} else if (jsonArray[one].brxb == "0") {
			s = "未知性别";
		} else if (jsonArray[one].brxb == "9") {
			s = "未说明的性别";
		}
		var c = "";
		if (jsonArray[one].czmc == "1") {
			c = "换药";
		} else if (jsonArray[one].czmc == "2") {
			c = "拆线";
		} else if (jsonArray[one].czmc == "3") {
			c = "缝合";
		} else if (jsonArray[one].czmc == "4") {
			c = "抽血";
		} else if (jsonArray[one].czmc == "5") {
			c = "取样";
		}else if (jsonArray[one].czmc == "6") {
			c = "测血糖";
		}else if (jsonArray[one].czmc == "9") {
			c = "其他";
		}else if (jsonArray[one].czmc == "99") {
			c = "其他";
		}
		var color = "";
		if (one % 2 == 0) {
			color = '#ffffff';
		} else {
			color = '#E8F2FE';
		}
		str += "<tr bgcolor=" + color + " onClick=\"mclick(this,'" + color
				+ "','" + jsonArray[one].ybkh + "','" + jsonArray[one].brxm
				+ "','" + jsonArray[one].brxb + "','" + jsonArray[one].phone
				+ "','" + jsonArray[one].hkdz + "','" + jsonArray[one].jc
				+ "','" + jsonArray[one].czmc + "','" + jsonArray[one].brnl
				+ "','" + jsonArray[one].brhy + "','" + jsonArray[one].gms
				+ "','" + jsonArray[one].xx + "','" + jsonArray[one].mzzd
				+ "','" + jsonArray[one].description + "','"
				+ jsonArray[one].treatmentid + "','" + jsonArray[one].username
				+ "','" + jsonArray[one].markdate
				+ "')\" ondblclick=\"mdbclick('" + jsonArray[one].ybkh + "','"
				+ jsonArray[one].brxm + "','" + jsonArray[one].brxb + "','"
				+ jsonArray[one].phone + "','" + jsonArray[one].hkdz + "','"
				+ jsonArray[one].jc + "','" + jsonArray[one].czmc + "','"
				+ jsonArray[one].brnl + "','" + jsonArray[one].brhy + "','"
				+ jsonArray[one].gms + "','" + jsonArray[one].xx + "','"
				+ jsonArray[one].mzzd + "','" + jsonArray[one].description
				+ "','" + jsonArray[one].treatmentid + "','"
				+ jsonArray[one].username + "','" + jsonArray[one].markdate
				+ "')\"><td>" + jsonArray[one].brxm + "</td><td>" + s
				+ "</td><td>";
		str += jsonArray[one].hkdz + "</td><td>" + jsonArray[one].jc
				+ "</td><td>" + c + "</td><td>" + jsonArray[one].markdate
				+ "</td><td>";
		str += jsonArray[one].name + "</td></tr>";
		row++;
	}
	var rowstr = "";
	if (row < 11) {
		for ( var i = 0; i < (11 - row); i++) {
			rowstr += "<tr><td >&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td></tr>";
		}
	}
	str += rowstr;
	str += "</table>";
	document.getElementById("treatmentpageNo").innerText = treatmentcurrentpage + 1;
	document.getElementById("treatmentcurrentpage").value = treatmentcurrentpage + 1;
	document.getElementById("treatmenttotalPages").innerText = treatmenttotalPages;
	document.getElementById("treatmenttotalRecordsUI").innerText = jsonArray.length;
	$("#result").html("");
//	var wholehtm = "";
//	wholehtm += "<table border='0' cellpadding='0' cellspacing='0' width='1143' height='557'>";
//	wholehtm += "<tr>";
//	wholehtm += "<td valign='top' colspan='3' height='61'>";
//	wholehtm += "<img border='0' src='image/ja_b_01.gif' width='256' height='61'><img border='0' src='image/ja_b_02.gif' width='619' height='61'><img border='0' src='image/ja_b_04.gif' width='269' height='61'></td>";
//	wholehtm += "</tr>";
//	wholehtm += "<tr>";
//	wholehtm += "<td valign='top' width='21'>";
//	wholehtm += "<img border='0' src='image/ja_b_07.gif' width='19' height='453'></td>";
//	wholehtm += "<td valign='top' width='1106'>";
//	wholehtm += "<div>" + str + "</div>";
//	wholehtm += "</td>";
//	wholehtm += "<td valign='top' height='446' width='16'>";
//	wholehtm += "<img border='0' src='image/ja_b_10.gif' width='25' height='453'></td>";
//	wholehtm += "</tr>";
//	wholehtm += "<tr>";
//	wholehtm += "<td valign='top' colspan='3' height='50'>";
//	wholehtm += "<img border='0' src='image/ja_b_11.gif' width='19' height='19'><img border='0' src='image/ja_b_11.gif' width='1100' height='19'><img border='0' src='image/ja_b_14.gif' width='25' height='19'></td>";
//	wholehtm += "</tr>";
//	wholehtm += "</table>";
//	wholehtm += "</form>";
	$("#result").html(str);
	$("#treatmentTable").show();
}
function changeColor1(element, color) {
	element.style.backgroundColor = "#FFCCFF";

}
function changeColor2(element, color) {
	element.style.backgroundColor = color;
}
function mclick(element, color, ybkh, brxm, brxb, lxdh, jtdz, jc, czmc, brnl,
		brhy, gms, xx, mzzd, description, treatmentid, username, markdate) {
	if (globalElement != "" && globalColor != "") {
		globalElement.style.backgroundColor = globalColor;
	}
	element.style.backgroundColor = "#99ffcc";
	globalElement = element;
	globalColor = color;
	// globalAppid = appid;
	$("#treatmentList").click(
			function() {
				location.href = "treatmentList.html?ybkh=" + ybkh + "&brxm="
						+ brxm + "&brxb=" + brxb + "&lxdh=" + lxdh + "&jtdz="
						+ jtdz + "&jc=" + jc + "&czmc=" + czmc + "&brnl="
						+ brnl + "&brhy=" + brhy + "&gms=" + gms + "&xx=" + xx
						+ "&mzzd=" + mzzd + "&description=" + description
						+ "&treatmentid=" + treatmentid + "&username="
						+ username + "&markdate=" + markdate;
			});
}
function mdbclick(ybkh, brxm, brxb, lxdh, jtdz, jc, czmc, brnl, brhy, gms, xx,
		mzzd, description, treatmentid, username, markdate) {
	location.href = "treatmentList.html?ybkh=" + ybkh + "&brxm=" + brxm
			+ "&brxb=" + brxb + "&lxdh=" + lxdh + "&jtdz=" + jtdz + "&jc=" + jc
			+ "&czmc=" + czmc + "&brnl=" + brnl + "&brhy=" + brhy + "&gms="
			+ gms + "&xx=" + xx + "&mzzd=" + mzzd + "&description="
			+ description + "&treatmentid=" + treatmentid + "&username="
			+ username + "&markdate=" + markdate;
}
// function isInteger(num) {
// var patrn = /^[1-9]*[1-9][0-9]*$/;
//
// if (!patrn.exec(num)) {
// return false;
// } else {
// return true;
// }
// }

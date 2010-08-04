var globalElement = "";
var globalColor = "";
$(document).ready(function() {
	$("#sanitationTable").hide();
	$.ajax( {
		url : "getAllSanitation.action",
		type : "post",
		dataType : "json",
		success : update_page
	});
	$("#sanitationfirstpage").click(function() {
		$.ajax( {
			url : "getAllSanitation.action",
			type : "post",
			dataType : "json",
			success : update_page
		});
	});
	$("#sanitationprevpage").click(function() {
		$.ajax( {
			url : "getAllSanitation.action",
			type : "post",
			dataType : "json",
			success : sanitationprevpage
		});
	});
	$("#sanitationnextpage").click(function() {
		$.ajax( {
			url : "getAllSanitation.action",
			type : "post",
			dataType : "json",
			success : sanitationnextpage
		});
	});
	$("#sanitationlastpage").click(function() {
		$.ajax( {
			url : "getAllSanitation.action",
			type : "post",
			dataType : "json",
			success : sanitationlastpage
		});
	});
});
function update_page(result) {
	var str = "<table id='patienttable'>";
		str +="<tr bgcolor=#C5E1EF><td colspan=4 style='height:30px;font-weight:bold;font-size:14px'>卫生消毒信息</td></tr>";
		str += "<tr bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px'><b>操作时间</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>操作人</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>操作事项</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>完成情况</b></td></tr>";
	var jsonArray = eval("(" + result + ")");
	var firstpage = 11;
	if (jsonArray.length <= firstpage) {
		firstpage = jsonArray.length;
	}
	var row = 0;
	for ( var one = 0; one < firstpage; one++) {
		var s = "";
		if (jsonArray[one].sanitationcontent == "0") {
			s = "卫生";
		} else if (jsonArray[one].sanitationcontent == "1") {
			s = "消毒";
		}else if (jsonArray[one].sanitationcontent == "2") {
			s = "口腔科消毒";
		}
		var color = "";
		if (one % 2 == 0) {
			color = '#ffffff';
		} else {
			color = '#E8F2FE';
		}
		// var c = "";
		// if (jsonArray[one].czmc == "1") {
		// c = "伤口处理/换药";
		// } else if (jsonArray[one].czmc == "2") {
		// c = "抽血/取样";
		// }
		str += "<tr tr bgcolor=" + color + " onClick=\"mclick(this,'" + color
				+ "','" + jsonArray[one].sanitationid + "','"
				+ jsonArray[one].markdate + "','"
				+ jsonArray[one].sanitationcontent + "','"
				+ jsonArray[one].sanitationcomplete + "','"
				+ jsonArray[one].username + "')\" ondblclick=\"mdbclick('"
				+ jsonArray[one].sanitationid + "','" + jsonArray[one].markdate
				+ "','" + jsonArray[one].sanitationcontent + "','"
				+ jsonArray[one].sanitationcomplete + "','"
				+ jsonArray[one].username + "')\"><td>"
				+ jsonArray[one].markdate + "</td><td>" + jsonArray[one].name
				+ "</td><td>";
		str += s + "</td><td>" + jsonArray[one].sanitationcomplete
				+ "</td></tr>";
		// str += jsonArray[one].name + "</td></tr>";
		row++;
	}
	var rowstr = "";
	if (row < 11) {
		for ( var i = 0; i < (11 - row); i++) {
			rowstr += "<tr><td >&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
		}
	}
	str += rowstr;
	str += "</table>";
	document.getElementById("sanitationpageNo").innerText = "1";
	var treatmenttotalPages = jsonArray.length / 11 + "";
	if (treatmenttotalPages.indexOf(".") >= 0) {
		treatmenttotalPages = parseInt(treatmenttotalPages, 10) + 1;
	}
	document.getElementById("sanitationcurrentpage").value = 1;
	document.getElementById("sanitationtotalPages").innerText = treatmenttotalPages
			+ "";
	document.getElementById("sanitationtotalRecordsUI").innerText = jsonArray.length
			+ "";
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
//	wholehtm += "<img border='0' src='image/ja_b_11.gif' width='19' height='19'><img border='0' src='image/ja_b_12.gif' width='1100' height='19'><img border='0' src='image/ja_b_14.gif' width='25' height='19'></td>";
//	wholehtm += "</tr>";
//	wholehtm += "</table>";
//	wholehtm += "</form>";
	$("#result").html(str);
	$("#sanitationTable").show();
}
function sanitationlastpage(result) {
	var str = "<table id='patienttable'>";
	str +="<tr bgcolor=#C5E1EF><td colspan=4 style='height:30px;font-weight:bold;font-size:14px'>卫生消毒信息</td></tr>";
	str += "<tr bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px'><b>操作时间</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>操作人</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>操作事项</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>完成情况</b></td></tr>";
	var jsonArray = eval("(" + result + ")");
	var to = jsonArray.length;
	var from;
	var sanitationtotalPages = 1;
	if (jsonArray.length <= 11) {
		from = 0;
	} else {
		var temp = jsonArray.length / 11 + "";
		if (temp.indexOf(".") < 0) {
			from = parseInt(temp - 1, 10) * 11;
		} else {
			from = parseInt(temp, 10) * 11;
		}
		sanitationtotalPages = jsonArray.length / 11 + "";
		if (sanitationtotalPages.indexOf(".") >= 0) {
			sanitationtotalPages = parseInt(sanitationtotalPages, 10) + 1;
		}
	}
	var row = 0;
	for ( var one = from; one < to; one++) {
		var s = "";
		if (jsonArray[one].sanitationcontent == "0") {
			s = "卫生";
		} else if (jsonArray[one].sanitationcontent == "1") {
			s = "消毒";
		}else if (jsonArray[one].sanitationcontent == "2") {
			s = "口腔科消毒";
		}
		var color = "";
		if (one % 2 == 0) {
			color = '#ffffff';
		} else {
			color = '#E8F2FE';
		}
		// var s = "";
		// if (jsonArray[one].brxb == "1") {
		// s = "男";
		// } else if (jsonArray[one].brxb == "2") {
		// s = "女";
		// } else if (jsonArray[one].brxb == "0") {
		// s = "未知性别";
		// } else if (jsonArray[one].brxb == "9") {
		// s = "未说明的性别";
		// }
		// var c = "";
		// if (jsonArray[one].czmc == "1") {
		// c = "伤口处理/换药";
		// } else if (jsonArray[one].czmc == "2") {
		// c = "抽血/取样";
		// }
		str += "<tr tr bgcolor=" + color + " onClick=\"mclick(this,'" + color
				+ "','" + jsonArray[one].sanitationid + "','"
				+ jsonArray[one].markdate + "','"
				+ jsonArray[one].sanitationcontent + "','"
				+ jsonArray[one].sanitationcomplete + "','"
				+ jsonArray[one].username + "')\" ondblclick=\"mdbclick('"
				+ jsonArray[one].sanitationid + "','" + jsonArray[one].markdate
				+ "','" + jsonArray[one].sanitationcontent + "','"
				+ jsonArray[one].sanitationcomplete + "','"
				+ jsonArray[one].username + "')\"><td>"
				+ jsonArray[one].markdate + "</td><td>" + jsonArray[one].name
				+ "</td><td>";
		str += s + "</td><td>" + jsonArray[one].sanitationcomplete
				+ "</td></tr>";
		row++;
	}
	var rowstr = "";
	if (row < 11) {
		for ( var i = 0; i < (11 - row); i++) {
			rowstr += "<tr><td >&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
		}
	}
	str += rowstr;
	str += "</table>";
	document.getElementById("sanitationpageNo").innerText = sanitationtotalPages;
	document.getElementById("sanitationcurrentpage").value = sanitationtotalPages;
	document.getElementById("sanitationtotalPages").innerText = sanitationtotalPages;
	document.getElementById("sanitationtotalRecordsUI").innerText = jsonArray.length;
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
//	wholehtm += "<img border='0' src='image/ja_b_11.gif' width='19' height='19'><img border='0' src='image/ja_b_12.gif' width='1100' height='19'><img border='0' src='image/ja_b_14.gif' width='25' height='19'></td>";
//	wholehtm += "</tr>";
//	wholehtm += "</table>";
//	wholehtm += "</form>";
	$("#result").html(str);
	$("#sanitationTable").show();
}
function sanitationprevpage(result) {
	var str = "<table id='patienttable'>";
	str +="<tr bgcolor=#C5E1EF><td colspan=4 style='height:30px;font-weight:bold;font-size:14px'>卫生消毒信息</td></tr>";
	str += "<tr bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px'><b>操作时间</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>操作人</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>操作事项</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>完成情况</b></td></tr>";
	var jsonArray = eval("(" + result + ")");
	var from;
	var to;
	var sanitationtotalPages = 1;
	var sanitationcurrentpage = document
			.getElementById("sanitationcurrentpage").value;
	sanitationcurrentpage = parseInt(sanitationcurrentpage);
	// alert("cccccccc "+sanitationcurrentpage);
	if (sanitationcurrentpage == 1) {
		return;
	}
	from = (sanitationcurrentpage - 2) * 11;
	to = (sanitationcurrentpage - 1) * 11;
	// alert("ssssssss"+from);
	// alert("qqqqqqqq "+to);
	if (jsonArray.length > 11) {
		sanitationtotalPages = jsonArray.length / 11 + "";
		if (sanitationtotalPages.indexOf(".") >= 0) {
			sanitationtotalPages = parseInt(sanitationtotalPages, 10) + 1;
		}
	}
	var row = 0;
	for ( var one = from; one < to; one++) {
		var s = "";
		if (jsonArray[one].sanitationcontent == "0") {
			s = "卫生";
		} else if (jsonArray[one].sanitationcontent == "1") {
			s = "消毒";
		}else if (jsonArray[one].sanitationcontent == "2") {
			s = "口腔科消毒";
		}
		var color = "";
		if (one % 2 == 0) {
			color = '#ffffff';
		} else {
			color = '#E8F2FE';
		}
		// var s = "";
		// if (jsonArray[one].brxb == "1") {
		// s = "男";
		// } else if (jsonArray[one].brxb == "2") {
		// s = "女";
		// } else if (jsonArray[one].brxb == "0") {
		// s = "未知性别";
		// } else if (jsonArray[one].brxb == "9") {
		// s = "未说明的性别";
		// }
		// var c = "";
		// if (jsonArray[one].czmc == "1") {
		// c = "伤口处理/换药";
		// } else if (jsonArray[one].czmc == "2") {
		// c = "抽血/取样";
		str += "<tr tr bgcolor=" + color + " onClick=\"mclick(this,'" + color
				+ "','" + jsonArray[one].sanitationid + "','"
				+ jsonArray[one].markdate + "','"
				+ jsonArray[one].sanitationcontent + "','"
				+ jsonArray[one].sanitationcomplete + "','"
				+ jsonArray[one].username + "')\" ondblclick=\"mdbclick('"
				+ jsonArray[one].sanitationid + "','" + jsonArray[one].markdate
				+ "','" + jsonArray[one].sanitationcontent + "','"
				+ jsonArray[one].sanitationcomplete + "','"
				+ jsonArray[one].username + "')\"><td>"
				+ jsonArray[one].markdate + "</td><td>" + jsonArray[one].name
				+ "</td><td>";
		str += s + "</td><td>" + jsonArray[one].sanitationcomplete
				+ "</td></tr>";
		row++;
	}
	// }
	var rowstr = "";
	if (row < 11) {
		for ( var i = 0; i < (11 - row); i++) {
			rowstr += "<tr><td >&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
		}
	}
	str += rowstr;
	// str += "</table>";
	// document.getElementById("sanitationpageNo").innerText =
	// sanitationcurrentpage - 1;
	// document.getElementById("sanitationcurrentpage").value =
	// sanitationcurrentpage - 1;
	// document.getElementById("sanitationtotalPages").innerText =
	// sanitationtotalPages;
	// document.getElementById("sanitationtotalRecordsUI").innerText =
	// jsonArray.length;
	str += "</table>";
	document.getElementById("sanitationpageNo").innerText = sanitationcurrentpage - 1;
	document.getElementById("sanitationcurrentpage").value = sanitationcurrentpage - 1;
	document.getElementById("sanitationtotalPages").innerText = sanitationtotalPages;
	document.getElementById("sanitationtotalRecordsUI").innerText = jsonArray.length;
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
//	wholehtm += "<img border='0' src='image/ja_b_11.gif' width='19' height='19'><img border='0' src='image/ja_b_12.gif' width='1100' height='19'><img border='0' src='image/ja_b_14.gif' width='25' height='19'></td>";
//	wholehtm += "</tr>";
//	wholehtm += "</table>";
//	wholehtm += "</form>";
	$("#result").html(str);
	$("#sanitationTable").show();
}
function sanitationnextpage(result) {
	var str = "<table id='patienttable'>";
	str +="<tr bgcolor=#C5E1EF><td colspan=4 style='height:30px;font-weight:bold;font-size:14px'>卫生消毒信息</td></tr>";
	str += "<tr bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px'><b>操作时间</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>操作人</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>操作事项</b></td><td style='height:30px;font-weight:bold;font-size:14px'><b>完成情况</b></td></tr>";
	var jsonArray = eval("(" + result + ")");
	var from;
	var to;
	var sanitationtotalPages = 1;
	var sanitationcurrentpage = document
			.getElementById("sanitationcurrentpage").value;
	// alert("11111 "+sanitationcurrentpage)
	sanitationcurrentpage = parseInt(sanitationcurrentpage);
	// alert(sanitationcurrentpage);
	if (jsonArray.length > 11) {
		sanitationtotalPages = jsonArray.length / 11 + "";
		if (sanitationtotalPages.indexOf(".") >= 0) {
			sanitationtotalPages = parseInt(sanitationtotalPages, 10) + 1;
		}
	}
	if (sanitationcurrentpage == sanitationtotalPages) {
		return;
	}
	from = sanitationcurrentpage * 11;
	to = (sanitationcurrentpage + 1) * 11;
	if (sanitationcurrentpage == (sanitationtotalPages - 1)) {
		to = jsonArray.length;
	}
	var row = 0;
	for ( var one = from; one < to; one++) {
		var s = "";
		if (jsonArray[one].sanitationcontent == "0") {
			s = "卫生";
		} else if (jsonArray[one].sanitationcontent == "1") {
			s = "消毒";
		}else if (jsonArray[one].sanitationcontent == "2") {
			s = "口腔科消毒";
		}
		var color = "";
		if (one % 2 == 0) {
			color = '#ffffff';
		} else {
			color = '#E8F2FE';
		}
		// var s = "";
		// if (jsonArray[one].brxb == "1") {
		// s = "男";
		// } else if (jsonArray[one].brxb == "2") {
		// s = "女";
		// } else if (jsonArray[one].brxb == "0") {
		// s = "未知性别";
		// } else if (jsonArray[one].brxb == "9") {
		// s = "未说明的性别";
		// }
		// var c = "";
		// if (jsonArray[one].czmc == "1") {
		// c = "伤口处理/换药";
		// } else if (jsonArray[one].czmc == "2") {
		// c = "抽血/取样";
		// }
		str += "<tr tr bgcolor=" + color + " onClick=\"mclick(this,'" + color
				+ "','" + jsonArray[one].sanitationid + "','"
				+ jsonArray[one].markdate + "','"
				+ jsonArray[one].sanitationcontent + "','"
				+ jsonArray[one].sanitationcomplete + "','"
				+ jsonArray[one].username + "')\" ondblclick=\"mdbclick('"
				+ jsonArray[one].sanitationid + "','" + jsonArray[one].markdate
				+ "','" + jsonArray[one].sanitationcontent + "','"
				+ jsonArray[one].sanitationcomplete + "','"
				+ jsonArray[one].username + "')\"><td>"
				+ jsonArray[one].markdate + "</td><td>" + jsonArray[one].name
				+ "</td><td>";
		str += s + "</td><td>" + jsonArray[one].sanitationcomplete
				+ "</td></tr>";
		row++;
	}
	var rowstr = "";
	if (row < 11) {
		for ( var i = 0; i < (11 - row); i++) {
			rowstr += "<tr><td >&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
		}
	}
	str += rowstr;
	str += "</table>";
	document.getElementById("sanitationpageNo").innerText = sanitationcurrentpage + 1;
	document.getElementById("sanitationcurrentpage").value = sanitationcurrentpage + 1;
	document.getElementById("sanitationtotalPages").innerText = sanitationtotalPages;
	document.getElementById("sanitationtotalRecordsUI").innerText = jsonArray.length;
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
//	wholehtm += "<img border='0' src='image/ja_b_11.gif' width='19' height='19'><img border='0' src='image/ja_b_12.gif' width='1100' height='19'><img border='0' src='image/ja_b_14.gif' width='25' height='19'></td>";
//	wholehtm += "</tr>";
//	wholehtm += "</table>";
//	wholehtm += "</form>";
	$("#result").html(str);
	$("#sanitationTable").show();
}
function changeColor1(element, color) {
	element.style.backgroundColor = "#FFCCFF";

}
function changeColor2(element, color) {
	element.style.backgroundColor = color;
}
function mclick(element, color, sanitationid, markdate, sanitationcontent,
		sanitationcomplete, username) {
	if (globalElement != "" && globalColor != "") {
		globalElement.style.backgroundColor = globalColor;
	}
	element.style.backgroundColor = "#99ffcc";
	globalElement = element;
	globalColor = color;
	$("#sanitationList").click(
			function() {
				location.href = "sanitationList.html?sanitationid="
						+ sanitationid + "&markdate=" + markdate
						+ "&sanitationcontent=" + sanitationcontent
						+ "&sanitationcomplete=" + sanitationcomplete
						+ "&username=" + username;
			});
}
function mdbclick(sanitationid, markdate, sanitationcontent,
		sanitationcomplete, username) {
	location.href = "sanitationList.html?sanitationid=" + sanitationid
			+ "&markdate=" + markdate + "&sanitationcontent="
			+ sanitationcontent + "&sanitationcomplete=" + sanitationcomplete
			+ "&username=" + username;
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

var globalElement = "";
var globalColor = "";
var globalAppid = "";
var globalyysj = "";
$(document).ready(function() {

	$("#clicktable").hide();
	//var param = "patient.date=" + globalyysj;
	$.ajax( {
		url : "requestVisitServiceYYByCond.action",
		type : "post",
		dataType : "json",
		data : "patient.date=" + globalyysj,
		success : update_page
	});
	$("#lastpage").click(function() {
		$.ajax( {
			url : "requestVisitServiceYYByCond.action",
			type : "post",
			dataType : "json",
			data : "patient.date=" + globalyysj,
			success : lastpage
		});
	});
	$("#firstpage").click(function() {
		$.ajax( {
			url : "requestVisitServiceYYByCond.action",
			type : "post",
			dataType : "json",
			data : "patient.date=" + globalyysj,
			success : update_page
		});
	});

	$("#prevpage").click(function() {
		$.ajax( {
			url : "requestVisitServiceYYByCond.action",
			type : "post",
			dataType : "json",
			data : "patient.date=" + globalyysj,
			success : prevpage
		});
	});
	$("#nextpage").click(function() {
		$.ajax( {
			url : "requestVisitServiceYYByCond.action",
			type : "post",
			dataType : "json",
			data : "patient.date=" + globalyysj,
			success : nextpage
		});
	});
	$("#update").click(function() {
		location.href = "visitserviceluru.html?id=" + globalAppid + "&flag=1";
	});
});

function update_page(result) {
	// alert(result);
	var str = "<table id='patienttable'>";
	str += "<tr bgcolor=#C5E1EF><td colspan=8  style='height:30px;font-weight:bold;font-size:14px'>上门服务预约列表</td></tr>";
	str += "<tr bgcolor=#99ccff><td colspan=8  style='' align=left>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='visitserviceyuyue.jsp'>预约登记</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id='yysj' name='yysj' type='text' size='20' class=\"Wdate\" onClick=\"WdatePicker({skin:'whyGreen'})\">&nbsp;&nbsp;&nbsp;&nbsp;<input name='query' id='query' type='button' value='查询' onClick='query()'> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='yuyueprintpage.html'>打印今日预约单</a></td></tr>";
	str += "<tr bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px'>姓名</td><td style='height:30px;font-weight:bold;font-size:14px'>性别</td><td style='height:30px;font-weight:bold;font-size:14px'>家庭住址</td><td style='height:30px;font-weight:bold;font-size:14px'>联系电话</td><td style='height:30px;font-weight:bold;font-size:14px'> 预约日期</td><td style='height:30px;font-weight:bold;font-size:14px'>分类</td><td style='height:30px;font-weight:bold;font-size:14px'>备注</td><td style='height:30px;font-weight:bold;font-size:14px'>详细</td></tr>";
	var jsonArray = eval("(" + result + ")");
	var firstpage = 11;
	if (jsonArray.length <= firstpage) {
		firstpage = jsonArray.length;
	}
	// alert("update_page");
	var row = 0;
	for ( var one = 0; one < firstpage; one++) {
		var s = "";
		if (jsonArray[one].brxb == "1") {
			s = "男";
		} else if (jsonArray[one].brxb == "2") {
			s = "女";
		} else {
			s = "不详";
		}
		var kind="";
		if (jsonArray[one].kind == "1") {
			kind = "输液";
		} else if (jsonArray[one].kind == "2") {
			kind = "注射";
		} else if(jsonArray[one].kind == "3"){
			kind = "换药";
		}
		else if(jsonArray[one].kind == "4"){
			kind = "拆线";
		}
		else if(jsonArray[one].kind == "5"){
			kind = "导尿";
		}
		else if(jsonArray[one].kind == "6"){
			kind = "其他";
		}
		else if(jsonArray[one].kind == "0"){
			kind = "不详";
		}
		var color = "";
		if (one % 2 == 0) {
			color = '#ffffff';
		} else {
			color = '#E8F2FE';
		}
		str += "<tr bgcolor=" + color + "  onClick=\"mclick(this,'" + color
				+ "','" + jsonArray[one].id + "')\" ondblclick=\"mdbclick('"
				+ jsonArray[one].id + "')\"><td>" + jsonArray[one].brxm
				+ "</td><td>" + s + "</td>";
		str += "<td>" + jsonArray[one].jtdz + "</td><td>" + jsonArray[one].lxdh
				+ "</td><td>" + jsonArray[one].yysj + "</td>";
		str += "<td>" + kind + "</td>";
		str += "<td>" + jsonArray[one].remark + "</td>";
		str += "<td><a href='visitserviceyuyueluru.html?id=" + jsonArray[one].id
				+ "&flag=1'>查看</a></td></tr>";
		row++;
	}
	if (row < 11) {
		for ( var i = 0; i < (11 - row); i++) {
			str += "<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
		}
	}
	str += "</table>";
	document.getElementById("pageNo").innerText = "1";
	var totalPages = jsonArray.length / 11 + "";
	if (totalPages.indexOf(".") >= 0) {
		totalPages = parseInt(totalPages, 10) + 1;
	}
	document.getElementById("currentpage").value = 1;
	document.getElementById("totalPages").innerText = totalPages + "";
	document.getElementById("totalRecordsUI").innerText = jsonArray.length + "";
	$("#result").html(str);
	$("input[name='yysj']").attr("readonly", "readonly");
	$("#clicktable").show();
}
function changeColor1(element, color) {
	element.style.backgroundColor = "#FFCCFF";

}
function changeColor2(element, color) {
	element.style.backgroundColor = color;
}
function mclick(element, color, id) {
	if (globalElement != "" && globalColor != "") {
		globalElement.style.backgroundColor = globalColor;
	}
	element.style.backgroundColor = "#cc99ff";
	globalElement = element;
	globalColor = color;
	globalAppid = id;
	element.style.backgroundColor = "#99ffcc";
}
function mdbclick(id) {
	location.href = "visitserviceyuyueluru.html?id=" + id + "&flag=1";
}
function lastpage(result) {
	// alert("lastpage");
	var str = "<table id='patienttable'>";
	str += "<tr bgcolor=#C5E1EF><td colspan=8  style='height:30px;font-weight:bold;font-size:14px'>上门服务预约列表</td></tr>";
	str += "<tr bgcolor=#99ccff><td colspan=8  style='' align=left>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='visitserviceyuyue.jsp'>预约登记</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id='yysj' name='yysj' type='text' size='20' class=\"Wdate\" onClick=\"WdatePicker({skin:'whyGreen'})\">&nbsp;&nbsp;&nbsp;&nbsp;<input name='query' id='query' type='button' value='查询' onClick='query()'> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='yuyueprintpage.html'>打印今日预约单</a></td></tr>";
	str += "<tr bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px'>姓名</td><td style='height:30px;font-weight:bold;font-size:14px'>性别</td><td style='height:30px;font-weight:bold;font-size:14px'>家庭住址</td><td style='height:30px;font-weight:bold;font-size:14px'>联系电话</td><td style='height:30px;font-weight:bold;font-size:14px'> 预约日期</td><td style='height:30px;font-weight:bold;font-size:14px'>分类</td><td style='height:30px;font-weight:bold;font-size:14px'>备注</td><td style='height:30px;font-weight:bold;font-size:14px'>详细</td></tr>";
	var jsonArray = eval("(" + result + ")");
	var to = jsonArray.length;
	var from;
	var totalPages = 1;
	if (jsonArray.length <= 11) {
		from = 0;
	} else {
		var temp = jsonArray.length / 11 + "";
		if (temp.indexOf(".") < 0) {
			from = parseInt(temp - 1, 10) * 11;
		} else {
			from = parseInt(temp, 10) * 11;
		}
		totalPages = jsonArray.length / 11 + "";
		if (totalPages.indexOf(".") >= 0) {
			totalPages = parseInt(totalPages, 10) + 1;
		}
	}
	var row = 0;
	// alert("from " + from + " to " + to);
	for ( var one = from; one < to; one++) {
		var s = "";
		if (jsonArray[one].brxb == "1") {
			s = "男";
		} else if (jsonArray[one].brxb == "2") {
			s = "女";
		} else {
			s = "不详";
		}
		var kind="";
		if (jsonArray[one].kind == "1") {
			kind = "输液";
		} else if (jsonArray[one].kind == "2") {
			kind = "注射";
		} else if(jsonArray[one].kind == "3"){
			kind = "换药";
		}
		else if(jsonArray[one].kind == "4"){
			kind = "拆线";
		}
		else if(jsonArray[one].kind == "5"){
			kind = "导尿";
		}
		else if(jsonArray[one].kind == "6"){
			kind = "其他";
		}
		else if(jsonArray[one].kind == "0"){
			kind = "不详";
		}
		var color = "";
		if (one % 2 == 0) {
			color = '#ffffff';
		} else {
			color = '#E8F2FE';
		}

		str += "<tr bgcolor=" + color + "  onClick=\"mclick(this,'" + color
				+ "','" + jsonArray[one].id + "')\" ondblclick=\"mdbclick('"
				+ jsonArray[one].id + "')\"><td>" + jsonArray[one].brxm
				+ "</td><td>" + s + "</td>";
		str += "<td>" + jsonArray[one].jtdz + "</td><td>" + jsonArray[one].lxdh
				+ "</td><td>" + jsonArray[one].yysj + "</td>";
		str += "<td>" + kind + "</td>";
		str += "<td>" + jsonArray[one].remark + "</td>";
		str += "<td><a href='visitserviceluru.jsp?id=" + jsonArray[one].id
				+ "&flag=1'>查看</a></td></tr>";
		row++;
	}
	if (row < 11) {
		for ( var i = 0; i < (11 - row); i++) {
			str += "<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
		}
	}
	str += "</table>";
	document.getElementById("pageNo").innerText = totalPages;
	document.getElementById("currentpage").value = totalPages;
	document.getElementById("totalPages").innerText = totalPages;
	document.getElementById("totalRecordsUI").innerText = jsonArray.length;
	$("#result").html("");
	$("#result").html(str);
	$("#clicktable").show();
}

function prevpage(result) {
	// alert("lastpage");
	var str = "<table id='patienttable'>";
	str += "<tr bgcolor=#C5E1EF><td colspan=8  style='height:30px;font-weight:bold;font-size:14px'>上门服务预约列表</td></tr>";
	str += "<tr bgcolor=#99ccff><td colspan=8  style='' align=left>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='visitserviceyuyue.jsp'>预约登记</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id='yysj' name='yysj' type='text' size='20' class=\"Wdate\" onClick=\"WdatePicker({skin:'whyGreen'})\">&nbsp;&nbsp;&nbsp;&nbsp;<input name='query' id='query' type='button' value='查询' onClick='query()'> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='yuyueprintpage.html'>打印今日预约单</a></td></tr>";
	str += "<tr bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px'>姓名</td><td style='height:30px;font-weight:bold;font-size:14px'>性别</td><td style='height:30px;font-weight:bold;font-size:14px'>家庭住址</td><td style='height:30px;font-weight:bold;font-size:14px'>联系电话</td><td style='height:30px;font-weight:bold;font-size:14px'> 预约日期</td><td style='height:30px;font-weight:bold;font-size:14px'>分类</td><td style='height:30px;font-weight:bold;font-size:14px'>备注</td><td style='height:30px;font-weight:bold;font-size:14px'>详细</td></tr>";
	var jsonArray = eval("(" + result + ")");
	var from;
	var to;
	var totalPages = 1;
	var currentpage = document.getElementById("currentpage").value;
	currentpage = parseInt(currentpage);
	// alert(currentpage);
	if (currentpage == 1) {
		return;
	}
	from = (currentpage - 2) * 11;
	to = (currentpage - 1) * 11;
	if (jsonArray.length > 11) {
		totalPages = jsonArray.length / 11 + "";
		if (totalPages.indexOf(".") >= 0) {
			totalPages = parseInt(totalPages, 10) + 1;
		}
	}
	var row = 0;
	// alert("from " + from + " to " + to);
	for ( var one = from; one < to; one++) {
		var s = "";
		if (jsonArray[one].brxb == "1") {
			s = "男";
		} else if (jsonArray[one].brxb == "2") {
			s = "女";
		} else {
			s = "不详";
		}
		var kind="";
		if (jsonArray[one].kind == "1") {
			kind = "输液";
		} else if (jsonArray[one].kind == "2") {
			kind = "注射";
		} else if(jsonArray[one].kind == "3"){
			kind = "换药";
		}
		else if(jsonArray[one].kind == "4"){
			kind = "拆线";
		}
		else if(jsonArray[one].kind == "5"){
			kind = "导尿";
		}
		else if(jsonArray[one].kind == "6"){
			kind = "其他";
		}
		else if(jsonArray[one].kind == "0"){
			kind = "不详";
		}
		var color = "";
		if (one % 2 == 0) {
			color = '#ffffff';
		} else {
			color = '#E8F2FE';
		}
		str += "<tr bgcolor=" + color + "  onClick=\"mclick(this,'" + color
				+ "','" + jsonArray[one].id + "')\" ondblclick=\"mdbclick('"
				+ jsonArray[one].id + "')\"><td>" + jsonArray[one].brxm
				+ "</td><td>" + s + "</td>";
		str += "<td>" + jsonArray[one].jtdz + "</td><td>" + jsonArray[one].lxdh
				+ "</td><td>" + jsonArray[one].yysj + "</td>";
		str += "<td>" + kind + "</td>";
		str += "<td>" + jsonArray[one].remark + "</td>";
		str += "<td><a href='visitserviceluru.jsp?id=" + jsonArray[one].id
				+ "&flag=1'>查看</a></td></tr>";
		row++;
	}
	if (row < 11) {
		for ( var i = 0; i < (11 - row); i++) {
			str += "<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
		}
	}
	str += "</table>";
	document.getElementById("pageNo").innerText = currentpage - 1;
	document.getElementById("currentpage").value = currentpage - 1;
	document.getElementById("totalPages").innerText = totalPages;
	document.getElementById("totalRecordsUI").innerText = jsonArray.length;
	$("#result").html("");
	$("#result").html(str);
	$("#clicktable").show();
}

function nextpage(result) {
	// alert("lastpage");
	var str = "<table id='patienttable'>";
	str += "<tr bgcolor=#C5E1EF><td colspan=8  style='height:30px;font-weight:bold;font-size:14px'>上门服务预约列表</td></tr>";
	str += "<tr bgcolor=#99ccff><td colspan=8  style='' align=left>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='visitserviceyuyue.jsp'>预约登记</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id='yysj' name='yysj' type='text' size='20' class=\"Wdate\" onClick=\"WdatePicker({skin:'whyGreen'})\">&nbsp;&nbsp;&nbsp;&nbsp;<input name='query' id='query' type='button' value='查询' onClick='query()'> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='yuyueprintpage.html'>打印今日预约单</a></td></tr>";
	str += "<tr bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px'>姓名</td><td style='height:30px;font-weight:bold;font-size:14px'>性别</td><td style='height:30px;font-weight:bold;font-size:14px'>家庭住址</td><td style='height:30px;font-weight:bold;font-size:14px'>联系电话</td><td style='height:30px;font-weight:bold;font-size:14px'> 预约日期</td><td style='height:30px;font-weight:bold;font-size:14px'>分类</td><td style='height:30px;font-weight:bold;font-size:14px'>备注</td><td style='height:30px;font-weight:bold;font-size:14px'>详细</td></tr>";
	var jsonArray = eval("(" + result + ")");
	var from;
	var to;
	var totalPages = 1;
	var currentpage = document.getElementById("currentpage").value;
	currentpage = parseInt(currentpage);
	// alert(currentpage);
	if (jsonArray.length > 11) {
		totalPages = jsonArray.length / 11 + "";
		if (totalPages.indexOf(".") >= 0) {
			totalPages = parseInt(totalPages, 10) + 1;
		}
	}
	if (currentpage == totalPages) {
		return;
	}
	from = currentpage * 11;
	to = (currentpage + 1) * 11;
	if (currentpage == (totalPages - 1)) {
		to = jsonArray.length;
	}
	var row = 0;
	// alert("from " + from + " to " + to);
	for ( var one = from; one < to; one++) {
		var s = "";
		if (jsonArray[one].brxb == "1") {
			s = "男";
		} else if (jsonArray[one].brxb == "2") {
			s = "女";
		} else {
			s = "不详";
		}
		var kind="";
		if (jsonArray[one].kind == "1") {
			kind = "输液";
		} else if (jsonArray[one].kind == "2") {
			kind = "注射";
		} else if(jsonArray[one].kind == "3"){
			kind = "换药";
		}
		else if(jsonArray[one].kind == "4"){
			kind = "拆线";
		}
		else if(jsonArray[one].kind == "5"){
			kind = "导尿";
		}
		else if(jsonArray[one].kind == "6"){
			kind = "其他";
		}
		else if(jsonArray[one].kind == "0"){
			kind = "不详";
		}
		var color = "";
		if (one % 2 == 0) {
			color = '#ffffff';
		} else {
			color = '#E8F2FE';
		}
		str += "<tr bgcolor=" + color + "  onClick=\"mclick(this,'" + color
				+ "','" + jsonArray[one].id + "')\" ondblclick=\"mdbclick('"
				+ jsonArray[one].id + "')\"><td>" + jsonArray[one].brxm
				+ "</td><td>" + s + "</td>";
		str += "<td>" + jsonArray[one].jtdz + "</td><td>" + jsonArray[one].lxdh
				+ "</td><td>" + jsonArray[one].yysj + "</td>";
		str += "<td>" + kind + "</td>";
		str += "<td>" + jsonArray[one].remark + "</td>";
		str += "<td><a href='visitserviceluru.jsp?id=" + jsonArray[one].id
				+ "&flag=1'>查看</a></td></tr>";
		row++;
	}
	if (row < 11) {
		for ( var i = 0; i < (11 - row); i++) {
			str += "<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
		}
	}
	str += "</table>";
	document.getElementById("pageNo").innerText = currentpage + 1;
	document.getElementById("currentpage").value = currentpage + 1;
	document.getElementById("totalPages").innerText = totalPages;
	document.getElementById("totalRecordsUI").innerText = jsonArray.length;
	$("#result").html("");
	$("#result").html(str);
	$("#clicktable").show();
}
function query() {

	var yysj = $("input[name='yysj']").attr("value");
	globalyysj = yysj;
	var param = "patient.date=" + yysj.Trim();
	$.ajax( {
		url : "requestVisitServiceYYByCond.action",
		type : "post",
		dataType : "json",
		data : param,
		success : update_page
	});
}

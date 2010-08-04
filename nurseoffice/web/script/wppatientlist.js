$(document).ready(function() {
	$("#clicktable").hide();
	$.ajax( {
		url : "requestWpPatientAll.action",
		type : "post",
		dataType : "json",
		success : update_page
	});
	$("#lastpage").click(function() {
		$.ajax( {
			url : "requestWpPatientAll.action",
			type : "post",
			dataType : "json",
			success : lastpage
		});
	});
	$("#firstpage").click(function() {
		$.ajax( {
			url : "requestWpPatientAll.action",
			type : "post",
			dataType : "json",
			success : update_page
		});
	});
	$("#prevpage").click(function() {
		$.ajax( {
			url : "requestWpPatientAll.action",
			type : "post",
			dataType : "json",
			success : prevpage
		});
	});
	$("#nextpage").click(function() {
		$.ajax( {
			url : "requestWpPatientAll.action",
			type : "post",
			dataType : "json",
			success : nextpage
		});
	});
});

function update_page(result) {
	//alert(result);
	var str = "<table id='patienttable'><tr bgcolor=#C5E1EF><td colspan=6  style='height:30px;font-weight:bold;font-size:14px'>居民就诊信息(外配药)</td></tr>";
	str +="<tr bgcolor=#99ccff><td colspan=2 align=left style='border-right:none'><a href='wpcfinfomxluru.jsp'>外配录入</a></td><td colspan=4 align=right style='border-left:none'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>";
	str +="<tr bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px'>姓名</td><td style='height:30px;font-weight:bold;font-size:14px'>性别</td><td style='height:30px;font-weight:bold;font-size:14px'>家庭地址</td><td style='height:30px;font-weight:bold;font-size:14px'>联系电话</td><td style='height:30px;font-weight:bold;font-size:14px'> 录入日期</td><td style='height:30px;font-weight:bold;font-size:14px'>详细</td></tr>";
	var jsonArray = eval("(" + result + ")");
	var firstpage = 10;
	if (jsonArray.length <= firstpage) {
		firstpage = jsonArray.length;
	}
	// alert("update_page");
	var row = 0 ;
	for ( var one = 0; one < firstpage; one++) {
		var s = "";
		if(jsonArray[one].brxb=="1"){
			s="男";
		}else if(jsonArray[one].brxb=="2"){
			s="女";
		}else {
			s="不详";
		}
		var color="";
		if(one%2==0){
			color='#ffffff';
		}else{
			color='#E8F2FE';
		}
		str += "<tr bgcolor="+color+" onmouseover=changeColor1(this) onmouseout=changeColor2(this,\""+color+"\") onClick=mclick(this) ondblclick=\"mdbclick('"+jsonArray[one].id+"')\"><td>"
				+ jsonArray[one].brxm + "</td><td>";
		str += s + "</td><td>" + jsonArray[one].jtdz
				+ "</td><td>" + jsonArray[one].lxdh + "</td><td>"
				+ jsonArray[one].lrrq + "</td><td>";
		str += "<a href='wpcfinfomx.html?patient.id="
				+ jsonArray[one].id+ "'>查看</a></td></tr>";
		row++;
	}
	if(row<10){
		for(var i=0 ; i<(10-row);i++){
			str+="<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
		}
	}
	str += "</table>";
	document.getElementById("pageNo").innerText = "1";
	var totalPages = jsonArray.length / 10 + "";
	if (totalPages.indexOf(".") >= 0) {
		totalPages = parseInt(totalPages, 10) + 1;
	}
	document.getElementById("currentpage").value = 1;
	document.getElementById("totalPages").innerText = totalPages + "";
	document.getElementById("totalRecordsUI").innerText = jsonArray.length + "";
	$("#result").html(str);
	$("#clicktable").show();
}
function changeColor1(element,color){
	element.style.backgroundColor="#FFCCFF";

}
function changeColor2(element,color){
	element.style.backgroundColor=color;
}
function mclick(element){
	element.style.backgroundColor="#99ffcc";
}
function mdbclick(cfsb){
	location.href="wpcfinfomx.html?patient.id="+cfsb+"";
}
function lastpage(result) {
	// alert("lastpage");
	var str = "<table id='patienttable'><tr bgcolor=#C5E1EF><td colspan=6  style='height:30px;font-weight:bold;font-size:14px'>居民就诊信息</td></tr>";
	str +="<tr bgcolor=#99ccff><td colspan=2 align=left style='border-right:none'><a href='wpcfinfomxluru.jsp'>外配录入</a></td><td colspan=4 align=right style='border-left:none'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>";
	str +="<tr bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px'>姓名</td><td style='height:30px;font-weight:bold;font-size:14px'>性别</td><td style='height:30px;font-weight:bold;font-size:14px'>科室</td><td style='height:30px;font-weight:bold;font-size:14px'>医生</td><td style='height:30px;font-weight:bold;font-size:14px'> 开方日期</td><td style='height:30px;font-weight:bold;font-size:14px'>详细</td></tr>";
	var jsonArray = eval("(" + result + ")");
	var to = jsonArray.length;
	var from;
	var totalPages = 1;
	if (jsonArray.length <= 10) {
		from = 0;
	} else {
		var temp = jsonArray.length / 10 + "";
		if (temp.indexOf(".") < 0) {
			from = parseInt(temp - 1, 10) * 10;
		} else {
			from = parseInt(temp, 10) * 10;
		}
		totalPages = jsonArray.length / 10 + "";
		if (totalPages.indexOf(".") >= 0) {
			totalPages = parseInt(totalPages, 10) + 1;
		}
	}
	//alert("from " + from + " to " + to);
	var row=0;
	for ( var one = from; one < to; one++) {
		var s = "";
		if(jsonArray[one].brxb=="1"){
			s="男";
		}else if(jsonArray[one].brxb=="2"){
			s="女";
		}else {
			s="不详";
		}
		var color="";
		if(one%2==0){
			color='#ffffff';
		}else{
			color='#E8F2FE';
		}
		if(jsonArray[one].fphm.length==0||jsonArray[one].fphm==""){
			color='red';
		}
		str += "<tr bgcolor="+color+" onmouseover=changeColor1(this) onmouseout=changeColor2(this,\""+color+"\") onClick=mclick(this) ondblclick=\"mdbclick('"+jsonArray[one].cfsb+"','"+jsonArray[one].total+"','"+jsonArray[one].currents+"')\"><td>"
				+ jsonArray[one].brxm + "</td><td>";
		str += s + "</td><td>" + jsonArray[one].ksmc
				+ "</td><td>" + jsonArray[one].ysxm + "</td><td>"
				+ jsonArray[one].kfrq + "</td><td>";
		str += "<a href='cfinfomx.html?patient.cfsbnumber="
				+ jsonArray[one].cfsb + "&patient.total="
				+ jsonArray[one].total + "&patient.currents="
				+ jsonArray[one].currents + "'>查看</a></td></tr>";
		row++;
	}
	if(row<10){
		for(var i=0 ; i<(10-row);i++){
			str+="<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
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
	var str = "<table id='patienttable'><tr bgcolor=#C5E1EF><td colspan=6  style='height:30px;font-weight:bold;font-size:14px'>居民就诊信息</td></tr>";
	str +="<tr bgcolor=#99ccff><td colspan=2 align=left style='border-right:none'><a href='wpcfinfomxluru.jsp'>外配录入</a></td><td colspan=4 align=right style='border-left:none'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>";
	str +="<tr bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px'>姓名</td><td style='height:30px;font-weight:bold;font-size:14px'>性别</td><td style='height:30px;font-weight:bold;font-size:14px'>科室</td><td style='height:30px;font-weight:bold;font-size:14px'>医生</td><td style='height:30px;font-weight:bold;font-size:14px'> 开方日期</td><td style='height:30px;font-weight:bold;font-size:14px'>详细</td></tr>";
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
	from = (currentpage - 2) * 10;
	to = (currentpage - 1) * 10;
	if (jsonArray.length > 10) {
		totalPages = jsonArray.length / 10 + "";
		if (totalPages.indexOf(".") >= 0) {
			totalPages = parseInt(totalPages, 10) + 1;
		}
	}

	//alert("from " + from + " to " + to);
	var row=0;
	for ( var one = from; one < to; one++) {
		var s = "";
		if(jsonArray[one].brxb=="1"){
			s="男";
		}else if(jsonArray[one].brxb=="2"){
			s="女";
		}else {
			s="不详";
		}
		var color="";
		if(one%2==0){
			color='#ffffff';
		}else{
			color='#E8F2FE';
		}
		if(jsonArray[one].fphm.length==0||jsonArray[one].fphm==""){
			color='red';
		}
		str += "<tr bgcolor="+color+" onmouseover=changeColor1(this) onmouseout=changeColor2(this,\""+color+"\") onClick=mclick(this) ondblclick=\"mdbclick('"+jsonArray[one].cfsb+"','"+jsonArray[one].total+"','"+jsonArray[one].currents+"')\"><td>"
				+ jsonArray[one].brxm + "</td><td>";
		str += s + "</td><td>" + jsonArray[one].ksmc
				+ "</td><td>" + jsonArray[one].ysxm + "</td><td>"
				+ jsonArray[one].kfrq + "</td><td>";
		str += "<a href='cfinfomx.html?patient.cfsbnumber="
				+ jsonArray[one].cfsb + "&patient.total="
				+ jsonArray[one].total + "&patient.currents="
				+ jsonArray[one].currents + "'>查看</a></td></tr>";
		row++;
	}
	if(row<10){
		for(var i=0 ; i<(10-row);i++){
			str+="<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
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
	var str = "<table id='patienttable'><tr bgcolor=#C5E1EF><td colspan=6  style='height:30px;font-weight:bold;font-size:14px'>居民就诊信息</td></tr>";
	str +="<tr bgcolor=#99ccff><td colspan=2 align=left style='border-right:none'><a href='wpcfinfomxluru.jsp'>外配录入</a></td><td colspan=4 align=right style='border-left:none'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td></tr>";
	str +="<tr bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px'>姓名</td><td style='height:30px;font-weight:bold;font-size:14px'>性别</td><td style='height:30px;font-weight:bold;font-size:14px'>科室</td><td style='height:30px;font-weight:bold;font-size:14px'>医生</td><td style='height:30px;font-weight:bold;font-size:14px'> 开方日期</td><td style='height:30px;font-weight:bold;font-size:14px'>详细</td></tr>";
	var jsonArray = eval("(" + result + ")");
	var from;
	var to;
	var totalPages = 1;
	var currentpage = document.getElementById("currentpage").value;
	currentpage = parseInt(currentpage);
	// alert(currentpage);
	if (jsonArray.length > 10) {
		totalPages = jsonArray.length / 10 + "";
		if (totalPages.indexOf(".") >= 0) {
			totalPages = parseInt(totalPages, 10) + 1;
		}
	}
	if (currentpage == totalPages) {
		return;
	}
	from = currentpage * 10;
	to = (currentpage + 1) * 10;
	if (currentpage == (totalPages - 1)) {
		to = jsonArray.length;
	}
	//alert("from " + from + " to " + to);
	var row=0;
	for ( var one = from; one < to; one++) {
		var s = "";
		if(jsonArray[one].brxb=="1"){
			s="男";
		}else if(jsonArray[one].brxb=="2"){
			s="女";
		}else {
			s="不详";
		}
		var color="";
		if(one%2==0){
			color='#ffffff';
		}else{
			color='#E8F2FE';
		}
		if(jsonArray[one].fphm.length==0||jsonArray[one].fphm==""){
			color='red';
		}
		str += "<tr bgcolor="+color+" onmouseover=changeColor1(this) onmouseout=changeColor2(this,\""+color+"\") ondblclick=\"mdbclick('"+jsonArray[one].cfsb+"','"+jsonArray[one].total+"','"+jsonArray[one].currents+"')\"><td>"
				+ jsonArray[one].brxm + "</td><td>";
		str += s + "</td><td>" + jsonArray[one].ksmc
				+ "</td><td>" + jsonArray[one].ysxm + "</td><td>"
				+ jsonArray[one].kfrq + "</td><td>";
		str += "<a href='cfinfomx.html?patient.cfsbnumber="
				+ jsonArray[one].cfsb + "&patient.total="
				+ jsonArray[one].total + "&patient.currents="
				+ jsonArray[one].currents + "'>查看</a></td></tr>";
		row++;
	}
	if(row<10){
		for(var i=0 ; i<(10-row);i++){
			str+="<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
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

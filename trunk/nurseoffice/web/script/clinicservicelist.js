$(document).ready(function() {
	$("#Table").hide();
	$.ajax( {
		url : "getAllObserved.action",
		type : "post",
		dataType : "json",
		success : update_page
	});
	$("#firstpage").click(function() {
		if(qssj.value!="" && jssj.value!=""){
			$.ajax( {
				url : "getAllObservedBetween.action",
				type : "post",
				dataType : "json",
				data: "qssj="+qssj.value+"&jssj="+jssj.value,
				success : update_page
			});
		}else{
			$.ajax( {
				url : "getAllObserved.action",
				type : "post",
				dataType : "json",
				success : update_page
			});
		}
		
	});
	$("#prevpage").click(function() {
		if(qssj.value!="" && jssj.value!=""){
			$.ajax( {
				url : "getAllObservedBetween.action",
				type : "post",
				dataType : "json",
				data: "qssj="+qssj.value+"&jssj="+jssj.value,
				success : prevpage
			});
		}else{
			$.ajax( {
				url : "getAllObserved.action",
				type : "post",
				dataType : "json",
				success : prevpage
			});
		}
	});
	$("#nextpage").click(function() {
		
		if(qssj.value!="" && jssj.value!=""){
			$.ajax( {
				url : "getAllObservedBetween.action",
				type : "post",
				dataType : "json",
				data: "qssj="+qssj.value+"&jssj="+jssj.value,
				success : nextpage
			});
		}else{
			$.ajax( {
				url : "getAllObserved.action",
				type : "post",
				dataType : "json",
				success : nextpage
			});
		}
	});
	$("#lastpage").click(function() {
		if(qssj.value!="" && jssj.value!=""){
			$.ajax( {
				url : "getAllObservedBetween.action",
				type : "post",
				dataType : "json",
				data: "qssj="+qssj.value+"&jssj="+jssj.value,
				success : lastpage
			});
		}else{
			$.ajax( {
				url : "getAllObserved.action",
				type : "post",
				dataType : "json",
				success : lastpage
			});
		}
	});
});
var globalElement="";
var globalColor="";
var globalAppid="";
var globalSbxh="";
function update_page(result) {
	
	var str = "<table id='patienttable'>" ;
	str +="<tr bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px' >时间</td><td style='height:30px;font-weight:bold;font-size:14px'>姓名</td><td style='height:30px;font-weight:bold;font-size:14px'>性别</td><td style='height:30px;font-weight:bold;font-size:14px' >年龄</td><td style='height:30px;font-weight:bold;font-size:14px'>地 址</td><td style='height:30px;font-weight:bold;font-size:14px'>主要症状和处理</td><td style='height:30px;font-weight:bold;font-size:14px'>补药药品</td><td style='height:30px;font-weight:bold;font-size:14px'>有无不良记录</td><td style='height:30px;font-weight:bold;font-size:14px'>处置医生护士</td><td style='height:30px;font-weight:bold;font-size:14px'>结束留观时间</td></tr>";
	var jsonArray = eval("(" + result + ")");
	var firstpage = 5;
	if (jsonArray.length <= firstpage) {
		firstpage = jsonArray.length;
	}
	var color="";
	var row=0;
	//alert(jsonArray[0].jssj);
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
		var bufy="";
		if(jsonArray[one].blfy==0){
			blfy="无";
		}else if(jsonArray[one].blfy==0){
			blfy="有";
		}
		if(one%2==0){
			color='#ffffff';
		}else{
			color='#E8F2FE';
		}
		var brnl=new Date().getYear()-jsonArray[one].csny;
		str += "<tr bgcolor='"+color+"'><td>" + jsonArray[one].kssj.toString() + "</td><td>"+ jsonArray[one].brxm + "</td><td>" + s + "</td><td>";
		str += brnl+ "</td><td>" + jsonArray[one].hkdz
				+ "</td><td>" +jsonArray[one].chuli + "</td><td>" + jsonArray[one].ypmc
				+ "</td><td>"+ blfy + "</td><td>"+jsonArray[one].ygxm + "</td><td>";
		str += jsonArray[one].jssj.toString()+ "</td></tr>";
		row++;
	}
	var rowstr="";
	if(row<5){
		for(var i=0 ; i<(5-row);i++){
			rowstr+="<tr><td >&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
		}
	}
	str+=rowstr;
	str += "</table>";
	document.getElementById("pageNo").innerText = "1";
	var totalPages = jsonArray.length / 5 + "";
	if (totalPages.indexOf(".") >= 0) {
		totalPages = parseInt(totalPages, 10) + 1;
	}
	document.getElementById("currentpage").value = 1;
	document.getElementById("totalPages").innerText = totalPages
			+ "";
	document.getElementById("totalRecordsUI").innerText = jsonArray.length
			+ "";
	$("#result").html(str);
	$("#Table").show();
}
function lastpage(result) {
	var str = "<table id='patienttable'>" ;
    str +="<tr bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px' >时间</td><td style='height:30px;font-weight:bold;font-size:14px'>姓名</td><td style='height:30px;font-weight:bold;font-size:14px'>性别</td><td style='height:30px;font-weight:bold;font-size:14px' >年龄</td><td style='height:30px;font-weight:bold;font-size:14px'>地 址</td><td style='height:30px;font-weight:bold;font-size:14px'>主要症状和处理</td><td style='height:30px;font-weight:bold;font-size:14px'>补药药品</td><td style='height:30px;font-weight:bold;font-size:14px'>有无不良记录</td><td style='height:30px;font-weight:bold;font-size:14px'>处置医生护士</td><td style='height:30px;font-weight:bold;font-size:14px'>结束留观时间</td></tr>";var jsonArray = eval("(" + result + ")");
	var to = jsonArray.length;
	var from;
	var totalPages = 1;
	if (jsonArray.length <= 5) {
		from = 0;
	} else {
		var temp = jsonArray.length / 5 + "";
		if (temp.indexOf(".") < 0) {
			from = parseInt(temp - 1, 10) * 5;
		} else {
			from = parseInt(temp, 10) * 5;
		}
		totalPages = jsonArray.length / 5 + "";
		if (totalPages.indexOf(".") >= 0) {
			totalPages = parseInt(totalPages, 10) + 1;
		}
	}
	var row=0;
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
			var bufy="";
		if(jsonArray[one].blfy==0){
			blfy="无";
		}else if(jsonArray[one].blfy==0){
			blfy="有";
		}
		if(one%2==0){
			color='#ffffff';
		}else{
			color='#E8F2FE';
		}
			var brnl=new Date().getYear()-jsonArray[one].csny;
		str += "<tr bgcolor='"+color+"'><td>" + jsonArray[one].kssj.toString() + "</td><td>"+ jsonArray[one].brxm + "</td><td>" + s + "</td><td>";
		str += brnl+ "</td><td>" + jsonArray[one].hkdz
				+ "</td><td>" +jsonArray[one].chuli + "</td><td>" + jsonArray[one].ypmc
				+ "</td><td>"+ blfy + "</td><td>"+jsonArray[one].ygxm + "</td><td>";
		str += jsonArray[one].jssj.toString()+ "</td></tr>";
		row++;
	}
	var rowstr="";
	if(row<5){
		for(var i=0 ; i<(5-row);i++){
			rowstr+="<tr><td >&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
		}
	}
	str+=rowstr;
	str += "</table>";
	document.getElementById("pageNo").innerText = totalPages;
	document.getElementById("currentpage").value = totalPages;
	document.getElementById("totalPages").innerText = totalPages;
	document.getElementById("totalRecordsUI").innerText = jsonArray.length;
	$("#result").html("");
	$("#result").html(str);
	$("#Table").show();
}
function prevpage(result) {
	var str = "<table id='patienttable'>" ;
	str +="<tr bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px' >时间</td><td style='height:30px;font-weight:bold;font-size:14px'>姓名</td><td style='height:30px;font-weight:bold;font-size:14px'>性别</td><td style='height:30px;font-weight:bold;font-size:14px' >年龄</td><td style='height:30px;font-weight:bold;font-size:14px'>地 址</td><td style='height:30px;font-weight:bold;font-size:14px'>主要症状和处理</td><td style='height:30px;font-weight:bold;font-size:14px'>补药药品</td><td style='height:30px;font-weight:bold;font-size:14px'>有无不良记录</td><td style='height:30px;font-weight:bold;font-size:14px'>处置医生护士</td><td style='height:30px;font-weight:bold;font-size:14px'>结束留观时间</td></tr>";var jsonArray = eval("(" + result + ")");
	var from;
	var to;
	var totalPages = 1;
	var currentpage = document.getElementById("currentpage").value;
	currentpage = parseInt(currentpage);
	// alert("cccccccc "+currentpage);
	if (currentpage == 1) {
		return;
	}
	from = (currentpage - 2) * 5;
	to = (currentpage - 1) * 5;
	// alert("ssssssss"+from);
	// alert("qqqqqqqq "+to);
	if (jsonArray.length > 5) {
		totalPages = jsonArray.length / 5 + "";
		if (totalPages.indexOf(".") >= 0) {
			totalPages = parseInt(totalPages, 10) + 1;
		}
	}
	var row=0;
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
			var bufy="";
		if(jsonArray[one].blfy==0){
			blfy="无";
		}else if(jsonArray[one].blfy==0){
			blfy="有";
		}
		if(one%2==0){
			color='#ffffff';
		}else{
			color='#E8F2FE';
		}
			var brnl=new Date().getYear()-jsonArray[one].csny;
		str += "<tr bgcolor='"+color+"'><td>" + jsonArray[one].kssj.toString() + "</td><td>"+ jsonArray[one].brxm + "</td><td>" + s + "</td><td>";
		str += brnl+ "</td><td>" + jsonArray[one].hkdz
				+ "</td><td>" +jsonArray[one].chuli + "</td><td>" + jsonArray[one].ypmc
				+ "</td><td>"+ blfy + "</td><td>"+jsonArray[one].ygxm + "</td><td>";
		str += jsonArray[one].jssj.toString()+ "</td></tr>";
		row++;
	}
	var rowstr="";
	if(row<5){
		for(var i=0 ; i<(5-row);i++){
			rowstr+="<tr><td >&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
		}
	}
	str+=rowstr;
	str += "</table>";
	document.getElementById("pageNo").innerText = currentpage - 1;
	document.getElementById("currentpage").value = currentpage - 1;
	document.getElementById("totalPages").innerText = totalPages;
	document.getElementById("totalRecordsUI").innerText = jsonArray.length;
	$("#result").html("");
	$("#result").html(str);
	$("#Table").show();
}
function nextpage(result) {
	var str = "<table id='patienttable'>" ;
     str +="<tr bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px' >时间</td><td style='height:30px;font-weight:bold;font-size:14px'>姓名</td><td style='height:30px;font-weight:bold;font-size:14px'>性别</td><td style='height:30px;font-weight:bold;font-size:14px' >年龄</td><td style='height:30px;font-weight:bold;font-size:14px'>地 址</td><td style='height:30px;font-weight:bold;font-size:14px'>主要症状和处理</td><td style='height:30px;font-weight:bold;font-size:14px'>补药药品</td><td style='height:30px;font-weight:bold;font-size:14px'>有无不良记录</td><td style='height:30px;font-weight:bold;font-size:14px'>处置医生护士</td><td style='height:30px;font-weight:bold;font-size:14px'>结束留观时间</td></tr>";var jsonArray = eval("(" + result + ")");
	var from;
	var to;
	var totalPages = 1;
	var currentpage = document.getElementById("currentpage").value;
	// alert("11111 "+currentpage)
	currentpage = parseInt(currentpage);
	// alert(currentpage);
	if (jsonArray.length > 5) {
		totalPages = jsonArray.length / 5+ "";
		if (totalPages.indexOf(".") >= 0) {
			totalPages = parseInt(totalPages, 10) + 1;
		}
	}
	if (currentpage == totalPages) {
		return;
	}
	from = currentpage * 5;
	to = (currentpage + 1) * 5;
	if (currentpage == (totalPages - 1)) {
		to = jsonArray.length;
	}
	var row=0;
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
			var bufy="";
		if(jsonArray[one].blfy==0){
			blfy="无";
		}else if(jsonArray[one].blfy==0){
			blfy="有";
		}
		if(one%2==0){
			color='#ffffff';
		}else{
			color='#E8F2FE';
		}
			var brnl=new Date().getYear()-jsonArray[one].csny;
		str += "<tr bgcolor='"+color+"'><td>" + jsonArray[one].kssj.toString() + "</td><td>"+ jsonArray[one].brxm + "</td><td>" + s + "</td><td>";
		str += brnl+ "</td><td>" + jsonArray[one].hkdz
				+ "</td><td>" +jsonArray[one].chuli + "</td><td>" + jsonArray[one].ypmc
				+ "</td><td>"+ blfy + "</td><td>"+jsonArray[one].ygxm + "</td><td>";
		str += jsonArray[one].jssj.toString()+ "</td></tr>";
		row++;
	}
	var rowstr="";
	if(row<5){
		for(var i=0 ; i<(5-row);i++){
			rowstr+="<tr><td >&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td >&nbsp;</td><td >&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
		}
	}
	str+=rowstr;
	str += "</table>";
	document.getElementById("pageNo").innerText = currentpage + 1;
	document.getElementById("currentpage").value = currentpage + 1;
	document.getElementById("totalPages").innerText = totalPages;
	document.getElementById("totalRecordsUI").innerText = jsonArray.length;
	$("#result").html("");
	$("#result").html(str);
	$("#Table").show();
}
//function changeColor1(element,color){
//	element.style.backgroundColor="#FFCCFF";
//
//}
//function changeColor2(element,color){
//	element.style.backgroundColor=color;
//}
function mclick(element,color,appid,sbxh){
	if(globalElement!="" && globalColor!=""){
		globalElement.style.backgroundColor=globalColor;
	}
	element.style.backgroundColor="#99ffcc";
	globalElement=element;
	globalColor=color;
 	globalAppid=appid;
 	globalSbxh=sbxh;
}
function modify() {
	if(globalAppid==""){
		alert("请您单击选中某一行！");
		return false;
	}else{
		location.href="T-P-R-BPtest.jsp?patient.appid="+globalAppid+"&patient.sbxh="+globalSbxh;
	}
}

function mdbclick(appid,sbxh){
  location.href="T-P-R-BPtest.jsp?patient.appid="+appid+"&patient.sbxh="+sbxh;
}
 function isInteger(num) {
 var patrn = /^[1-9]*[1-9][0-9]*$/;

 if (!patrn.exec(num)) {
 return false;
 } else {
 return true;
 }
 }
function query() {
	if(qssj.value!="" && jssj.value!=""){
			$.ajax( {
				url : "getAllObservedBetween.action",
				type : "post",
				dataType : "json",
				data: "qssj="+qssj.value+"&jssj="+jssj.value,
				success : update_page
			});
		}else{
			$.ajax( {
				url : "getAllObserved.action",
				type : "post",
				dataType : "json",
				success : update_page
			});
		}
}



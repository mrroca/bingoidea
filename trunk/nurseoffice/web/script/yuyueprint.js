$(document).ready(function() {

	$.ajax( {
		url : "requestTodayYYVisitService.action",
		type : "post",
		dataType : "json",
		success : update_page
	});

	});
function update_page(data) {
	var jsonArray = eval("(" + data + ")");
	
	var htm = "<div id='content'><table id='print'>";
	htm +="<tr><td colspan=5><font size='2'><b>今日上门服务信息</b></font></td></tr>";
	htm +="<tr><td colspan=3 width='380px' height=20px>&nbsp;</td><td align=right height=20px></td><td align=right height=20px> 日期&nbsp;&nbsp;"+(new Date()).pattern("yyyy-MM-dd")+"</td></tr>";
	htm +="</table>";
	htm +="<table id='printarea'>";
	var yylist = "<tr><td height=20px width=50px>姓名</td><td height=20px width='40px'>性别</td><td width='150px' height=20px>家庭地址</td><td width=50px height=20px>联系电话</td><td height=20px width='40px'>分类</td><td width=100px height=20px>备注</td></tr>";
	var row=0;
	for ( var one in jsonArray) {
		var s = "";
		if(jsonArray[one].brxb=="1"){
			s="男";
		}else if(jsonArray[one].brxb=="2"){
			s="女";
		}else {
			s="不详";
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
		yylist += "<tr><td>"+jsonArray[one].brxm+"</td><td>" + s + "</td><td align='left'>"
				+ jsonArray[one].jtdz + "</td><td align='left'>"
				+ jsonArray[one].lxdh + "</td><td>"
				+ kind + "</td><td align='left'>"
				+ jsonArray[one].remark + "</td></tr>";
		row++;
	}
	var rowstr="";
	if (jsonArray.toString().length <= 0) {
		yylist = "";
	}else{
		if(row<12){
			for(var i=0 ; i<(12-row);i++){
				rowstr+="<tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>";
			}
		}
	}
	yylist+=rowstr;
	htm += yylist;
	htm += "</table></div>";
	
	$("#result").html(htm);
	var ht = "<p><input name='print' type='button' value='打印' onClick='print2();return false;'></p>";
	$("#result").append(ht);
	//alert(htm);
}
function print2() {

//alert("----------");
	$("input[name='print']").remove();
	var body = window.document.body.innerHTML;
	var printArea = window.document.getElementById("content").innerHTML;
	window.document.body.innerHTML = printArea;
	window.print();
	window.document.body.innerHTML = body;

//alert($("#content").html());
	//window.document.body.innerHTML=window.document.getElementById('content').innerHTML;
	//$("input[name='print']").remove();
	//window.print();
	
	//$("#content").printArea(); 
	
}
function update_result(data) {
	
}
function isInteger(num) {
	var patrn = /^[1-9]*[1-9][0-9]*$/;

	if (!patrn.exec(num)) {
		return false;
	} else {
		return true;
	}
}
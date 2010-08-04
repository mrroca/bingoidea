$(document).ready(function() {

	var urlparam = $.url.param("patient.cfsbnumber");
	// urlparam = urlparam.serialize();
		urlparam = "patient.cfsbnumber=" + urlparam;
		$.ajax( {

			// 处理程序
			url : "getPrintInfo.action",

			// 发送方式
			type : "post",

			// 数据格式
			dataType : "json",

			// 要传递的数据
			data : urlparam,
			// 回传函数
			success : update_page

		});

	});
function update_page(data) {
	var temp = data;
	var tempstr = temp.toString().split("|-|-|");
	var jsonArraybasicinfo = eval("(" + tempstr[0] + ")");
	var jsonArraycfinfo = eval("(" + tempstr[1] + ")");
	
	var htm = "<div id='content'><table id='print'>";
	htm +="<tr><td colspan=8><font size='2'><b>苏州市胥江社区卫生服务中心静脉输液治疗单</b></font></td></tr>";
	htm +="<tr><td colspan=6 width='380px' height=20px>&nbsp;</td><td align=right height=20px></td><td align=right height=20px> 日期&nbsp;&nbsp;"+(new Date()).pattern("yyyy-MM-dd")+"</td></tr>";
	htm +="</table>";
	htm +="<table id='printarea'>";
	var cflist = "<tr><td height=20px width=25px>组套</td><td height=20px width='200px' colspan=2>药品名称</td><td width='50px' height=20px>规格</td><td width=25px height=20px>用量</td><td width=25px height=20px>用法</td><td width=25px height=20px>滴速</td><td width='40px' height=20px>时间</td><td width='40px' height=20px>签名</td></tr>";
	var row=0;
	for ( var one in jsonArraycfinfo) {
		cflist += "<tr><td>"+jsonArraycfinfo[one].ypzh+"</td><td width='180px' align='left' colspan=2>" + jsonArraycfinfo[one].ypmc + "</td><td width=50px>"
				+ jsonArraycfinfo[one].ypgg + "</td><td>"
				+ jsonArraycfinfo[one].ycjl + "</td><td>"+jsonArraycfinfo[one].pc+"</td><td>"
				+ "&nbsp;" + "</td><td width=''>"
				+ "&nbsp;" + "</td><td width=''>"
				+ "&nbsp;" + "</td></tr>";
		row++;
	}
	var rowstr="";
	if (jsonArraycfinfo.toString().length <= 0) {
		cflist = "";
	}else{
		if(row<12){
			for(var i=0 ; i<(12-row);i++){
				rowstr+="<tr><td>&nbsp;</td><td width='180px' align='left' colspan=2>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td width=''>&nbsp;</td><td width=''>&nbsp;</td></tr>";
			}
		}
	}

	for ( var one in jsonArraybasicinfo) {
		htm += "<tr><td>床号</td><td>" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
				+ "</td><td>姓名</td><td>"+jsonArraybasicinfo[one].brxm+"</td><td>" + "总量"
				+ "</td><td></td><td colspan=2>结束时间" + "</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + "</td></tr>";
	}
	cflist+=rowstr;
	htm += cflist;
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
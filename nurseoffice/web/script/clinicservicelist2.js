$(document).ready(function() {
	var urlparam = $.url.param("patient.cfsbnumber");
	// urlparam = urlparam.serialize();
		urlparam = "patient.cfsbnumber=" + urlparam;
		$.ajax( {

			// 处理程序
			url : "requestPatientInfomx.action",

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
function update_page(result) {
	var temp = result;
	var tempstr = temp.toString().split("|-|-|");
	var jsonArraybasicinfo = eval("(" + tempstr[0] + ")");
	var jsonArraycfinfo = eval("(" + tempstr[1] + ")");
	var jsonArraylczdinfo = eval("(" + tempstr[2] + ")");
	var jsonArraynurse = eval("(" + tempstr[3] + ")");
	var cfsbnumber = $.url.param("patient.cfsbnumber");
	var htm = "<table id='cfinfomxbable'>";
	var cflist = "<tr><td>药品名称</td><td>剂量</td><td>剂量单位</td><td>给药途径</td><td>滴速/观察/处理/记录人</td><td>组别</td><td>日期</td></tr>";
	var lczd = "";
	var nursestr = "";
	for ( var one in jsonArraylczdinfo) {
		lczd += jsonArraylczdinfo[one].lczdmc;
		lczd += "、";
	}
	if (lczd.length > 0) {
		lczd = lczd.substring(0, lczd.length - 1);
	}
	var zh = "";
	if (jsonArraycfinfo[0] != null) {
		zh = jsonArraycfinfo[0].ypzh;
	}
	var rowcount = 1;
	for ( var one in jsonArraycfinfo) {
// if (one > 0) {
// if (zh == jsonArraycfinfo[one].ypzh) {
// ++rowcount;
// } else {
// zh = jsonArraycfinfo[one].ypzh;
// rowcount = 1;
// }
// }
		cflist += "<tr>" + "<td>"
				+ jsonArraycfinfo[one].ypmc
				+ "</td>"
				+ "<td>"
				+ jsonArraycfinfo[one].ypjl
				+ "</td>"
				+ "<td>"
				+ jsonArraycfinfo[one].jldw
				+ "</td>"
				+ "<td>"
				+ jsonArraycfinfo[one].ypyf
				+ "</td>"
				+ "<td>"
				+ "<input type='text' name='input3' value='' style='width:50px'><input type='text' name='input3' value='' style='width:50px'><input type='text' name='input3' value='' style='width:50px'><input type='text' name='input3' value='' style='width:50px'>"
				+ "</td>"
				+ "<td>"
				+ jsonArraycfinfo[one].ypzh
				+ "</td>"
				+ "<td>" + jsonArraycfinfo[one].kfrq + "</td>" + "</tr>";
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

		htm += "<tr><td>处方号码</td><td>" + jsonArraybasicinfo[one].cfhm
				+ "</td><td>姓名</td><td>" + jsonArraybasicinfo[one].brxm
				+ "</td><td>性别</td><td>" + s
				+ "</td><td></td></tr><tr><td>家庭地址</td><td>"
				+ jsonArraybasicinfo[one].jtdz + "</td><td>联系电话</td><td>"
				+ jsonArraybasicinfo[one].lxdh + "</td><td>临床诊断</td><td>"
				+ lczd + "</td><td></td></tr>";
	}
	htm += cflist;
	var m = 0;
	for ( var one in jsonArraynurse) {
		nursestr += "<input type='radio' name='username' value='"
				+ jsonArraynurse[one].username + "'>"
				+ jsonArraynurse[one].name + "";
		if ((m + 1) % 4 == 0) {
			nursestr += "<br/>";
		}
		m++;
	}
	htm += "<tr><td>处理人</td><td colspan=5>"
			+ nursestr
			+ "</td><td><input name='submit' type='button' value='处理' onClick='save()'></td></tr>";
	htm += "</table>";
	$("#result").html(htm);
	MergeCellsVertical(document.getElementById("cfinfomxbable"), 5);
	//MergeCellsVertical2(document.getElementById("cfinfomxbable"), 5);
}
function print() {
}
function update_result() {
	if ($("input[name='brxm']").attr("value") == '请输入患者姓名') {
		alert($("input[name='brxm']").attr("value"));
		return false;
	} else {
		var param = $("input[name='brxm']").attr("value");
		param = "patient.patientname=" + param.Trim();
		$.ajax( {
			url : "requestPatientList.action",
			type : "post",
			dataType : "json",
			data : param,
			success : update_page
		});
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
function MergeCellsVertical(tab, cellIndex) // 相同数据上下合并
{    
	for(var i=0,j=0;i<tab.rows.length-1;i++,j++) {
		if(name==tab.rows[i].cells[cellIndex].innerHTML) {
	             tab.rows[i].deleteCell(cellIndex-1);
	             //tab.rows[i].deleteCell(cellIndex+1);
	             //tab.rows[i].deleteCell(cellIndex+2);
	    }else  { 
			name=tab.rows[i].cells[cellIndex].innerHTML;
			if(i>0){
				tab.rows[i-j].cells[cellIndex-1].rowSpan=j;
				//tab.rows[i-j].cells[cellIndex+1].rowSpan=j;
				//tab.rows[i-j].cells[cellIndex+2].rowSpan=j;
			}
			j=0;
		}
	    if(i ==tab.rows.length-2){
			tab.rows[i-j].cells[cellIndex-1].rowSpan=j+1;
			//tab.rows[i-j].cells[cellIndex+1].rowSpan=j+1;
			//tab.rows[i-j].cells[cellIndex+2].rowSpan=j+1;
		}
	}
}

//function MergeCellsVertical2(tab, cellIndex) // 相同数据上下合并
//{    
//	for(var i=0,j=0;i<tab.rows.length-1;i++,j++) {
//		if(name==tab.rows[i].cells[cellIndex].innerHTML) {
//			tab.rows[i].deleteCell(cellIndex+2);
//	    }else  { 
//			name=tab.rows[i].cells[cellIndex].innerHTML;
//			if(i>0){
//				alert("i==="+i);
//				alert("i-j==="+(i-j));
//				alert("cells==="+(cellIndex+2));
//				alert(tab.rows[i].cells[cellIndex].innerHTML);
//				tab.rows[i-j].cells[cellIndex+2].rowSpan=j;
//			}
//			j=0;
//		}
//	    if(i ==tab.rows.length-2){
//			tab.rows[i-j].cells[cellIndex+2].rowSpan=j+1;
//		}
//	}
//}
$(document).ready(function() {
		
		var cardnum = 0;
		try {
			cardnum = readCard.getcardno();
		} catch (err) {
			alert("请插入卡片");
			return;
		}
		var param = "patient.ybkh="+cardnum.Trim();
		
		$.ajax( {

			// 后台处理程序
			url : "requestPatientInfo.action",

			// 数据发送方式
			type : "post",

			// 接受数据格式
			dataType : "json",

			data : param,

			// 回传函数
			success : update_page

		});

	});
function update_page(result) {
	var str = "<table id='patienttable'><tr bgcolor=#C5E1EF><td colspan=7  style='height:30px;font-weight:bold;font-size:14px'>居民就诊信息</td></tr><tr  bgcolor='#cccccc'><td style='height:30px;font-weight:bold;font-size:14px'>姓名</td><td style='height:30px;font-weight:bold;font-size:14px'>性别</td><td style='height:30px;font-weight:bold;font-size:14px'>科室</td><td style='height:30px;font-weight:bold;font-size:14px'>医生</td><td style='height:30px;font-weight:bold;font-size:14px'>日期</td><td style='height:30px;font-weight:bold;font-size:14px'>详细</td></tr>";
	var jsonArray = eval("(" + result + ")");
	//$("#datatable").addClass("rowctrl");
	if(null!=jsonArray[0].info){
		alert("无持卡人信息,请插入卡片");
		location.href="patientlist.html";
	}
	for ( var one in jsonArray) {
		var s = "";
		if(jsonArray[one].brxb=="1"){
			s="男";
		}else if(jsonArray[one].brxb=="2"){
			s="女";
		}else {
			s="不详";
		}
		//if(jsonArray[one].total.length!=0){
			//if(jsonArray[one].total==jsonArray[one].currents){
				
			//}
		//}
		var color="";
		if(one%2==0){
			color='#ffffff';
		}else{
			color='#E8F2FE';
		}
		if(jsonArray[one].fphm.length==0||jsonArray[one].fphm==""){
			color='red';
		}
		str += "<tr bgcolor="+color+" onmouseover=changeColor1(this) onmouseout=changeColor2(this,\""+color+"\") onClick=mclick(this) ondblclick=\"mdbclick('"+jsonArray[one].cfsb+"','"+jsonArray[one].total+"','"+jsonArray[one].currents+"')\">"+"<td>"+jsonArray[one].brxm+"</td>"+"<td>"+s+"</td>"+"<td>"+jsonArray[one].ksmc+"</td>"+"<td>"+jsonArray[one].ysxm+"</td>"+"<td>"+jsonArray[one].kfrq+"</td>"+"<td><a href='"+"cfinfomx.html?patient.cfsbnumber="+jsonArray[one].cfsb+"&patient.total="+jsonArray[one].total+"&patient.currents="+jsonArray[one].currents+"'>查看</a></td>"+"</tr>";
	}
	str += "</table>";
	$("#result").html(str);
	//$("#cfrow").css({ color: "#cccccc"});
	//$("#datatable").css({ color: "#cccccc"});
	//$("#datatable").css({ color: "red", background: "#cccccc" });
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
function mdbclick(cfsb,total,currents){
	location.href="cfinfomx.html?patient.cfsbnumber="+cfsb+"&patient.total="+total+"&patient.currents="+currents+"";
}
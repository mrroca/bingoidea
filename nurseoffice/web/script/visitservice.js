$(document)
		.ready(function() {
			// function window.confirm(str)
				// {
				// execScript("n = (msgbox('"+ str
				// +"',vbYesNo,'提示信息')=vbYes)","vbscript");
				// return(n);
				// }
				// var bln = window.confirm("医保读卡?");

				var htm = "";
				htm += "<form>";
				htm += "<table id='visitservicetable'>";
				htm += "<tr>";
				htm += "<td>姓名：<input name='brxm' type='text' size='40' value='' style='width:80%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>";
				htm += "<td>性别：<input name='mzhm' type='text' size='40' value='' style='width:80%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>";
				htm += "<td>联系电话：<input name='mzhm' type='text' size='40' value='' style='width:80%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>";
				htm += "</tr>";
				htm += "<tr>";
				htm += "<td colspan=3>家庭住址：<input name='mzhm' type='text' size='80' value='' style='width:80%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>";
				htm += "</tr>";
				htm += "<tr>";
				htm += "<td colspan=3><textarea name='yoursuggest' cols ='100%' rows='5' style='border:none;overflow:hidden'></textarea></td>";
				htm += "</tr>";
				htm += "</table>";
				htm += "</form>";
				$("#result").html(htm);
				$("input[name='brxm']").autocomplete(
						"requestPatientName.action",
						{
							multiple : true,
							dataType : 'json',
							parse : function(data) {
								return $.map(eval(data), function(row) {
									return {
										data : row,
										value : row.brxm + " <" + row.brxm
												+ ">",
										result : row.brxm
									}
								});
							},
							formatItem : function(item) {
								return "<font color=green>" + item.xmpy
										+ "</font>&nbsp;(" + item.brxm + ")";
							}
						});
				$("textarea[name='yoursuggest']").attr("value", "请输入服务内容");
				$("textarea[name='yoursuggest']").focus(function() {
					if ($(this).attr("value") == "请输入服务内容") {
						$("textarea[name='yoursuggest']").attr("value", "");
					}
					;

				});
				// if(bln){
				// $.ajax( {
				//
				// // 后台处理程序
				// url : "requestPatientInfo.action",
				//
				// // 数据发送方式
				// type : "post",
				//
				// // 接受数据格式
				// dataType : "json",
				//
				// // 要传递的数据
				//
				// // 回传函数
				// success : update_page
				//
				// });
				// }
			});
function update_page(result) {
}
function print() {
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
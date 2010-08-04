$(document)
		.ready(
				function() {

					htm = "";
					htm += "<table id='managetable'>";
					htm += "<tr>";
					htm += "<td id='leftuser'>";
					htm += "</td>";
					htm += "<td>";
					htm += "<input type='button' value=' 添加>> ' name='btnRight' onclick='moveItem(right,left)' />";
					htm += "<br/><br/>";
					htm += "<input type='button' value=' <<移除 ' onclick='moveItem(left,right)' id='btn' name='btnLeft' />";
					htm += "</td>";
					htm += "<td id='rightuser'>";
					htm += "</td>";
					htm += "</tr>";
					htm += "<tr>";
					htm += "<td colspan=3 algin='center'>";
					htm += "<input type='button' name='submit' value='确定' onClick='save()'>";
					htm += "</td>";
					htm += "</tr>";
					htm += "</table>";
					$("#manageuser").html(htm);
					$.ajax( {
						url : "requestAllPerson.action",
						type : "post",
						dataType : "json",
						success : update_left
					});
					$.ajax( {
						url : "requestPersonInfo.action",
						type : "post",
						dataType : "json",
						success : update_right
					});
				});
function update_left(result) {
	var jsonArray = eval("(" + result + ")");
	var htmstr = "<select style='width:80px;align:center' size='15' name='leftuser' multiple='multiple' id='left' ondblclick='moveItem(right,this)'>";
	var m = 0;
	for ( var one = 0; one < jsonArray.length; one++) {
		htmstr += "<option value='" + jsonArray[one].ygdm + "'>"
				+ jsonArray[one].ygxm + "</option>";
	}
	htmstr += "</select>";
	$("#leftuser").html(htmstr);
}
function update_right(result) {
	var jsonArray = eval("(" + result + ")");
	var htmstr = "<select style='width:80px;align:center' size='15' name='rightuser' multiple='multiple' id='right' ondblclick='moveItem(left,this)'>";
	var m = 0;
	for ( var one = 0; one < jsonArray.length; one++) {
		htmstr += "<option value='" + jsonArray[one].username + "'>"
				+ jsonArray[one].name + "</option>";
	}
	htmstr += "</select>";
	$("#rightuser").html(htmstr);
}
function save() {
	// $("select[name='leftuser']").children().each(function(){$(this).attr("selected","selected")});
	// for(var i=0;i<$("select[name='leftuser'] option:selected").length;i++){
	// $("select[name='leftuser']
	// option:selected").each(alert($(this).val()));
	// alert($("select[name='leftuser'] option:selected").attr("value"));
	// }

	var newnurse = new Array(document.getElementById('right').options.length);
	var oldnurse = new Array(document.getElementById('left').options.length);
	for ( var i = 0; i < document.getElementById('right').options.length; i++) {
		newnurse[i] = document.getElementById('right').options[i].value;
	}
	for ( var i = 0; i < document.getElementById('left').options.length; i++) {
		oldnurse[i] = document.getElementById('left').options[i].value;
	}
	var params = "nurse.newnurse=" + newnurse.toString() + "&nurse.oldnurse="
			+ oldnurse.toString();
	$.ajax( {
		url : "updateNurseInfo.action",
		type : "post",
		dataType : "json",
		data : params,
		success : update_result
	});
}
function update_result(data) {
	var results = data;
	results = eval("(" + results + ")");

	if (results[0].info == "success") {
		alert("保存成功");
	} else {
		alert("保存失败");
	}
}
function moveItem(dest, source) {
	var dest = (typeof dest == "string" ? document.getElementById(dest) : dest);
	var source = (typeof source == "string" ? document.getElementById(source)
			: source);

	if (source.tagName.toLowerCase() != "select"
			|| dest.tagName.toLowerCase() != "select") {
		return;
	}

	for (index = source.length - 1; index >= 0; index--) {
		if (source[index].selected) {
			dest.length++;
			dest[dest.length - 1].id = source[index].id;
			dest[dest.length - 1].value = source[index].value;
			dest[dest.length - 1].text = source[index].text;
			source[index] = null;
		}
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
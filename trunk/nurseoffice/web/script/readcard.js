function readcard() {
	var cardnum = 0;
	try {
		cardnum = readCard.getcardno();
	} catch (err) {
		alert("请插入卡片");
		return;
	}
	if(cardnum.length<10){
		return;
	}
	var param = "patient.ybkh="+cardnum.Trim();
	$.ajax( {

		url : "requestPatientBasicByCard.action",

		type : "post",

		dataType : "json",

		data : param,

		success : update_basicinfo

	});
}
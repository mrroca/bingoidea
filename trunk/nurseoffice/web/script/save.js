$(document).ready(function() {
	var urlparam = $.url.param("patient.cfsbnumber");
	//urlparam = urlparam.serialize();
	urlparam = "patient.cfsbnumber="+urlparam;
	//alert(test);
		$.ajax( {
			url : "requestPatientInfomx.action",
			type : "post",
			dataType : "json",
			data: urlparam,
			success : update_page
		});
	});
function update_page(data) {
	$("#result").html(htm);
}
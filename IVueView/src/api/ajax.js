import $ from "jquery"
export const baseUrl = "http://localhost:8090"
export const jsonpGet = (url,data,result,error) => {
	data.jsonp = true;
	data.token = localStorage.getItem("token")
	$.ajax({
		data: data,
		url: baseUrl + url,
		type: 'get',
		dataType: 'jsonp',
		timeout: 1000,
		cache: false,
		jsonpCallback: "callback",
		error: function(neterror) {
			if (error != null) {
				error(neterror);
			} else {
				result(neterror);
			}
		}, //错误执行方法
		success: function(netresult) {
			result(netresult);
		} //成功执行方法
	});
}



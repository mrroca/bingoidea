function calculate_age(sfzh){

	var str=sfzh;// 身份证号
	var age=0;
	var len=str.length;// 身份证长度
	if (len<15){ 
		alert("不是有效身份证"); 
　　} 
	else// 大于15位
　　{ 
		if ((len!=15) && (len!=18))// 身份证长度等于15或18
	　　{ 
	　　	alert("不是有效身份证"); 
	　　} 
		else 
	　　{ 
			if (len==15)// 15位身份证
		　　{
				var date1=new Date();// 当前日期
				var year1=date1.getFullYear();// 当前年份
				var month1=date1.getMonth();// 当前月份
				if (month1>parseInt(str.substr(8,2)))
				{// 判断当前月份与身份证中的月份大小
					age	= year1-("19"+str.substr(6,2));
			　　		return age;
			　　}else
				{ 
					age = year1-("19"+str.substr(6,2))-1;
			　　		return age; 
				}
		　　} 
			if (len==18)// 18位身份证
		　　{ 
				var date1=new Date();// 当前日期
				var year1=date1.getFullYear();// 当前年份
				var month1=date1.getMonth();// 当前月份
				if (month1>parseInt(str.substr(10,2)))
				{// //判断当前月份与身份证中的月份大小
					age = year1-str.substr(6,4);
					return age; 
				}else
				{ 
					age = year1-str.substr(6,4)-1;
					return age;
				}
		　　} 
	　　} 
　　}
}
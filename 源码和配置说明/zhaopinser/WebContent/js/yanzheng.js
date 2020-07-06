
/**
 * 验证密码复杂度（必须包含数字字母）
 * @param str
 * @returns true:满足规则，false:不满足
 */
function validateStr(str){
	var reg1 = /^(([0-9]{1,})([a-z]{1,}))|(([a-z]{1,})([0-9]{1,}))$/;
	var reg2 = /^(([0-9]{1,})([A-Z]{1,}))|(([A-Z]{1,})([0-9]{1,}))$/;
	//var reg3 = /^([a-zA-Z]{0,})[0-9a-z-A-z]{0,}[~`!@#$%^&*.]{0,}$/;
	str = valueTrim(str);
	//if(reg3.test(str)){
	//	return true;
	//}
	if(reg1.test(str)){
		return true;
	}
	if(reg2.test(str)){
		return true;
	}
	return false;
}




/**
 * 判断字符串长度 必须大于8位小于20位，一般用于密码
 * @param str 字符串
 * @returns 满足返回true
 */
function valiDateLength(str){
	if(str==null || str==''){
		return false;
	}
	str = valueTrim(str);
	if(parseFloat(str.length)<8 ){
		return false;
	}
	if(parseFloat(str.length)>20){
		return false;
	}
	return true;
}
/**
 * 验证时间
 * @param dataValue 格式为：YYYY-MM-DD
 * @returns 匹配返回true 不匹配返回false
 */
function valiDate(dateValue){
	var result = dateValue.match(/((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/);
	if(result==null){
		return false;
	}
	return true;
}


/**
 * 验证电话号码
 * @param phoneValue 要验证的电话号码
 * @returns 匹配返回true 不匹配返回false
 */
function validatePhone(phoneValue) {
	phoneValue = valueTrim(phoneValue);
	var reg = /^[1][0-9]{10}$/;
	return reg.test(phoneValue);
}
/**
 * 验证邮箱
 * @param emailValue 要验证的邮箱
 * @returns 匹配返回true 不匹配返回false
 */
function validateEmail(emailValue){
	var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	return reg.test(emailValue);
}
/**
 * 判断是否是数字
 * @param numberValue 要验证的数据
 * @returns 匹配返回true 不匹配返回false
 */
function isNumber(numberValue){
	//定义正则表达式部分    
	var reg1 = /^[0-9]{0,}$/;
	var reg2 = /^[1-9]{1}[0-9]{0,}$/;
	//alert(numberValue);
	if(numberValue ==null || numberValue.length==0){
		return false;
	}
	numberValue = valueTrim(numberValue);
	//判断当数字只有1位时
	if(numberValue.length<2){
		return reg1.test(numberValue);
	}
	return reg2.test(numberValue);;
}
/***
 * 金额
 * @param value
 * @returns
 */
function isMoney(value) {
	if(value==''){
		return false;
	}
	value = valueTrim(value);
	value = value.replace(/(^\s*)|(\s*$)/g, "");
	var reg = /^[0-9]*\.?[0-9]{0,2}$/;
	if(isNumber(value)){
		return true;
	}
	if(value.length>3){
		if(value.substr(0, 1)=="0"){
			if(value.substr(3,value.length).length>2){
				return false;
			} 
		}
	}
	return reg.test(value);
}
/***
 * 判断是否是0到100之间的数
 * @param value
 * @returns
 */
function isZeroToOne(value) {
	if(value==''){
		return false;
	}
	value = valueTrim(value);
	if(isMyFloat(value)){
		if(parseFloat(value)<100 && parseFloat(value)>0){
			return true;
		}
	}
	return false;
}


/**
 * 验证是否是浮点数
 * @param floatValue 要验证的数据
 * @returns 匹配返回true 不匹配返回false
 */
function isMyFloat(floatValue){
	if(floatValue==''){
		return false;
	}
	floatValue = valueTrim(floatValue);
	var reg = /^(\d+)(\.\d+)$/;
	if(isNumber(floatValue)){
		return true;
	}
	if(floatValue.length>3){
		if(floatValue.substr(0, 1)=="0"){
			if(floatValue.substr(0, 2)!="0."){
				return false;
			} 
		}
	}
	return reg.test(floatValue);
}
/**
 * 判断是否是汉字
 * @param charValue 要验证的数据
 * @returns 匹配返回true 不匹配返回false
 */
function isCharacter(charValue){
	var reg = /^[\u4e00-\u9fa5]{0,}$/;
	return reg.test(charValue);
}
/**
 * 验证座机号
 * @param telValue 要验证的座机号
 * @returns 匹配返回true 不匹配返回false
 */
function valiDateTel(telValue){
	var reg = /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/;
	telValue = valueTrim(telValue);
	if(!reg.test(telValue)){
		return false;
    }
	return true;
}


var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];    // 加权因子   
var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];            // 身份证验证位值.10代表X
/**
 * 验证身份证
 * @param idCard 需要验证的身份证号
 * @returns 匹配返回true 不匹配返回false
 */
function IdCardValidate(idCardValue) {
	//去掉字符串头尾空格
	idCardValue = valueTrim(idCardValue.replace(/ /g, ""));                     
    if (idCardValue.length == 15) {
    	//进行15位身份证的验证 
        return isValidityBrithBy15IdCard(idCardValue);   
    } else if (idCardValue.length == 18) {
    	// 得到身份证数组  
        var a_idCard = idCardValue.split(""); 
        //进行18位身份证的基本验证和第18位的验证
        if(isValidityBrithBy18IdCard(idCardValue)&&isTrueValidateCodeBy18IdCard(a_idCard)){
            return true;   
        }else {   
            return false;
        }   
    } else {
        return false;   
    }   
}   
/**  
 * 判断身份证号码为18位时最后的验证位是否正确  
 * @param a_idCard 身份证号码数组  
 * @return  
 */  
function isTrueValidateCodeBy18IdCard(a_idCard) {   
    var sum = 0; // 声明加权求和变量   
    if (a_idCard[17].toLowerCase() == 'x') {   
        a_idCard[17] = 10;// 将最后位为x的验证码替换为10方便后续操作   
    }   
    for ( var i = 0; i < 17; i++) {   
        sum += Wi[i] * a_idCard[i];// 加权求和   
    }   
    valCodePosition = sum % 11; // 得到验证码所位置   
    if (a_idCard[17] == ValideCode[valCodePosition]) {   
        return true;   
    } else {   
        return false;   
    }   
}   
/**  
  * 验证18位数身份证号码中的生日是否是有效生日  
  * @param idCard 18位书身份证字符串  
  * @return  
  */  
function isValidityBrithBy18IdCard(idCard18){   
    var year =  idCard18.substring(6,10);   
    var month = idCard18.substring(10,12);   
    var day = idCard18.substring(12,14);   
    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
    // 这里用getFullYear()获取年份，避免千年虫问题   
    if(temp_date.getFullYear()!=parseFloat(year)   
          ||temp_date.getMonth()!=parseFloat(month)-1   
          ||temp_date.getDate()!=parseFloat(day)){   
            return false;   
    }else{   
        return true;   
    }   
}   
/**  
  * 验证15位数身份证号码中的生日是否是有效生日  
  * @param idCard15 15位书身份证字符串  
  * @return  
  */  
function isValidityBrithBy15IdCard(idCard15){   
   var year =  idCard15.substring(6,8);   
   var month = idCard15.substring(8,10);   
   var day = idCard15.substring(10,12);   
   var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
   // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法   
   if(temp_date.getYear()!=parseFloat(year)   
           ||temp_date.getMonth()!=parseFloat(month)-1   
           ||temp_date.getDate()!=parseFloat(day)){   
       return false;   
   }else{   
       return true;   
   }   
}   
//去掉字符串头尾空格   
function valueTrim(str) {   
    return str.replace(/(^\s*)|(\s*$)/g, "");   
}


/**
 * 检验18位身份证号码（15位号码可以只检测生日是否正确即可，自行解决）
 * @param idCardValue 18位身份证号
 * @returns 匹配返回true 不匹配返回false
 */
function idCardVildate(cid){
    var arrExp = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];//加权因子
    var arrValid = [1, 0, "X", 9, 8, 7, 6, 5, 4, 3, 2];//校验码
    var reg = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
    if(reg.test(cid)){
        var sum = 0, idx;
        for(var i = 0; i < cid.length - 1; i++){
            // 对前17位数字与权值乘积求和
            sum += parseInt(cid.substr(i, 1), 10) * arrExp[i];
        }
        // 计算模（固定算法）
        idx = sum % 11;
        // 检验第18为是否与校验码相等
        return arrValid[idx] == cid.substr(17, 1).toUpperCase();
    }else{
        return false;
    }
}


/**
 * 获取指定日期之前或之后的第几天
 * 
 * @param dayCount
 *            正数为以后时间，负数为以前时间 如：1表示为明天，-1为昨天
 * 
 */
function getDateStr(dates, dayCount) {
	var dateTime = dayCount * 24 * 60 * 60 * 1000;
	var dd = new Date();
	if (dates == "") {
		dd = new Date();
	} else {
		dd = new Date(dates);
	}
	var dateNumber = dd.getTime() + dateTime;
	var newDate = new Date(dateNumber);
	var y = newDate.getFullYear();
	var m = newDate.getMonth() + 1;// 获取当前月份的日期
	var d = newDate.getDate();
	if (m < 10) {
		m = "0" + m;
	}
	if (d < 10) {
		d = "0" + d;
	}
	return y + "-" + m + "-" + d;
}
/**
 * 获取指定月份的之前或之后的第几个月
 * 
 * @param dayCount
 *            正数为以后月份，负数为以前月份 如：1表示为下月，-1为上月
 * 
 */
function getMonthStr(dates, monthCount) {
	var dd = new Date();
	if (dates == "") {
		dd = new Date();
	} else {
		dd = new Date(dates);
	}
	var y = dd.getFullYear();
	var m = dd.getMonth() + 1;// 获取当前月份的日期
	m = m + monthCount;
	if (m == 0) {
		m = "12";
		y = y - 1;
	} else if (m < 10) {
		m = "0" + m;
	} else if (m > 12) {
		m = m - 12;
		m = "0" + m;
		y = y + 1;
	}
	return y + "-" + m;
}
/**
*
*对val值为undefined返回“”，否则返回原值
*/
function dealNull(val) {
	if (typeof (val) == "undefined") {
		return "";
	} else {
		return val;
	}
}
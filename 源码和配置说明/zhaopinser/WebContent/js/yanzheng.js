
/**
 * ��֤���븴�Ӷȣ��������������ĸ��
 * @param str
 * @returns true:�������false:������
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
 * �ж��ַ������� �������8λС��20λ��һ����������
 * @param str �ַ���
 * @returns ���㷵��true
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
 * ��֤ʱ��
 * @param dataValue ��ʽΪ��YYYY-MM-DD
 * @returns ƥ�䷵��true ��ƥ�䷵��false
 */
function valiDate(dateValue){
	var result = dateValue.match(/((^((1[8-9]\d{2})|([2-9]\d{3}))(-)(10|12|0?[13578])(-)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(11|0?[469])(-)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))(-)(0?2)(-)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)(-)(0?2)(-)(29)$)|(^([3579][26]00)(-)(0?2)(-)(29)$)|(^([1][89][0][48])(-)(0?2)(-)(29)$)|(^([2-9][0-9][0][48])(-)(0?2)(-)(29)$)|(^([1][89][2468][048])(-)(0?2)(-)(29)$)|(^([2-9][0-9][2468][048])(-)(0?2)(-)(29)$)|(^([1][89][13579][26])(-)(0?2)(-)(29)$)|(^([2-9][0-9][13579][26])(-)(0?2)(-)(29)$))/);
	if(result==null){
		return false;
	}
	return true;
}


/**
 * ��֤�绰����
 * @param phoneValue Ҫ��֤�ĵ绰����
 * @returns ƥ�䷵��true ��ƥ�䷵��false
 */
function validatePhone(phoneValue) {
	phoneValue = valueTrim(phoneValue);
	var reg = /^[1][0-9]{10}$/;
	return reg.test(phoneValue);
}
/**
 * ��֤����
 * @param emailValue Ҫ��֤������
 * @returns ƥ�䷵��true ��ƥ�䷵��false
 */
function validateEmail(emailValue){
	var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	return reg.test(emailValue);
}
/**
 * �ж��Ƿ�������
 * @param numberValue Ҫ��֤������
 * @returns ƥ�䷵��true ��ƥ�䷵��false
 */
function isNumber(numberValue){
	//����������ʽ����    
	var reg1 = /^[0-9]{0,}$/;
	var reg2 = /^[1-9]{1}[0-9]{0,}$/;
	//alert(numberValue);
	if(numberValue ==null || numberValue.length==0){
		return false;
	}
	numberValue = valueTrim(numberValue);
	//�жϵ�����ֻ��1λʱ
	if(numberValue.length<2){
		return reg1.test(numberValue);
	}
	return reg2.test(numberValue);;
}
/***
 * ���
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
 * �ж��Ƿ���0��100֮�����
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
 * ��֤�Ƿ��Ǹ�����
 * @param floatValue Ҫ��֤������
 * @returns ƥ�䷵��true ��ƥ�䷵��false
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
 * �ж��Ƿ��Ǻ���
 * @param charValue Ҫ��֤������
 * @returns ƥ�䷵��true ��ƥ�䷵��false
 */
function isCharacter(charValue){
	var reg = /^[\u4e00-\u9fa5]{0,}$/;
	return reg.test(charValue);
}
/**
 * ��֤������
 * @param telValue Ҫ��֤��������
 * @returns ƥ�䷵��true ��ƥ�䷵��false
 */
function valiDateTel(telValue){
	var reg = /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/;
	telValue = valueTrim(telValue);
	if(!reg.test(telValue)){
		return false;
    }
	return true;
}


var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];    // ��Ȩ����   
var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];            // ���֤��֤λֵ.10����X
/**
 * ��֤���֤
 * @param idCard ��Ҫ��֤�����֤��
 * @returns ƥ�䷵��true ��ƥ�䷵��false
 */
function IdCardValidate(idCardValue) {
	//ȥ���ַ���ͷβ�ո�
	idCardValue = valueTrim(idCardValue.replace(/ /g, ""));                     
    if (idCardValue.length == 15) {
    	//����15λ���֤����֤ 
        return isValidityBrithBy15IdCard(idCardValue);   
    } else if (idCardValue.length == 18) {
    	// �õ����֤����  
        var a_idCard = idCardValue.split(""); 
        //����18λ���֤�Ļ�����֤�͵�18λ����֤
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
 * �ж����֤����Ϊ18λʱ������֤λ�Ƿ���ȷ  
 * @param a_idCard ���֤��������  
 * @return  
 */  
function isTrueValidateCodeBy18IdCard(a_idCard) {   
    var sum = 0; // ������Ȩ��ͱ���   
    if (a_idCard[17].toLowerCase() == 'x') {   
        a_idCard[17] = 10;// �����λΪx����֤���滻Ϊ10�����������   
    }   
    for ( var i = 0; i < 17; i++) {   
        sum += Wi[i] * a_idCard[i];// ��Ȩ���   
    }   
    valCodePosition = sum % 11; // �õ���֤����λ��   
    if (a_idCard[17] == ValideCode[valCodePosition]) {   
        return true;   
    } else {   
        return false;   
    }   
}   
/**  
  * ��֤18λ�����֤�����е������Ƿ�����Ч����  
  * @param idCard 18λ�����֤�ַ���  
  * @return  
  */  
function isValidityBrithBy18IdCard(idCard18){   
    var year =  idCard18.substring(6,10);   
    var month = idCard18.substring(10,12);   
    var day = idCard18.substring(12,14);   
    var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
    // ������getFullYear()��ȡ��ݣ�����ǧ�������   
    if(temp_date.getFullYear()!=parseFloat(year)   
          ||temp_date.getMonth()!=parseFloat(month)-1   
          ||temp_date.getDate()!=parseFloat(day)){   
            return false;   
    }else{   
        return true;   
    }   
}   
/**  
  * ��֤15λ�����֤�����е������Ƿ�����Ч����  
  * @param idCard15 15λ�����֤�ַ���  
  * @return  
  */  
function isValidityBrithBy15IdCard(idCard15){   
   var year =  idCard15.substring(6,8);   
   var month = idCard15.substring(8,10);   
   var day = idCard15.substring(10,12);   
   var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
   // ���������֤�е����������迼��ǧ��������ʹ��getYear()����   
   if(temp_date.getYear()!=parseFloat(year)   
           ||temp_date.getMonth()!=parseFloat(month)-1   
           ||temp_date.getDate()!=parseFloat(day)){   
       return false;   
   }else{   
       return true;   
   }   
}   
//ȥ���ַ���ͷβ�ո�   
function valueTrim(str) {   
    return str.replace(/(^\s*)|(\s*$)/g, "");   
}


/**
 * ����18λ���֤���루15λ�������ֻ��������Ƿ���ȷ���ɣ����н����
 * @param idCardValue 18λ���֤��
 * @returns ƥ�䷵��true ��ƥ�䷵��false
 */
function idCardVildate(cid){
    var arrExp = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];//��Ȩ����
    var arrValid = [1, 0, "X", 9, 8, 7, 6, 5, 4, 3, 2];//У����
    var reg = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
    if(reg.test(cid)){
        var sum = 0, idx;
        for(var i = 0; i < cid.length - 1; i++){
            // ��ǰ17λ������Ȩֵ�˻����
            sum += parseInt(cid.substr(i, 1), 10) * arrExp[i];
        }
        // ����ģ���̶��㷨��
        idx = sum % 11;
        // �����18Ϊ�Ƿ���У�������
        return arrValid[idx] == cid.substr(17, 1).toUpperCase();
    }else{
        return false;
    }
}


/**
 * ��ȡָ������֮ǰ��֮��ĵڼ���
 * 
 * @param dayCount
 *            ����Ϊ�Ժ�ʱ�䣬����Ϊ��ǰʱ�� �磺1��ʾΪ���죬-1Ϊ����
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
	var m = newDate.getMonth() + 1;// ��ȡ��ǰ�·ݵ�����
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
 * ��ȡָ���·ݵ�֮ǰ��֮��ĵڼ�����
 * 
 * @param dayCount
 *            ����Ϊ�Ժ��·ݣ�����Ϊ��ǰ�·� �磺1��ʾΪ���£�-1Ϊ����
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
	var m = dd.getMonth() + 1;// ��ȡ��ǰ�·ݵ�����
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
*��valֵΪundefined���ء��������򷵻�ԭֵ
*/
function dealNull(val) {
	if (typeof (val) == "undefined") {
		return "";
	} else {
		return val;
	}
}
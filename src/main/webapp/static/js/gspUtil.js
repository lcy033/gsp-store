/**
* 这是一个JS工具类
* isEmptyString ： 判断是否空字符
* loadCSS ：加载CSS文件
* loadJS ： 加载JavaScript文件
* Date.formate：日期格式化
* parseDate: 字符转换日期
* puhui.CSS_URL ：CSS文件路径
* puhui.JAVASCRIPT_URL ：JS文件路径
* puhui.IMAGE_URL ：图片文件路径
* puhui.getScrollXY : 获取浏览器滚动条位移
* puhui.toString : 将对象格式化成字符串
* puhui.toggleAImg : 用于鼠标事件切换图片显示效果
* puhui.maxScrollX : 用于获取页面滚动条长度（X轴）
*/
var puhui = {};
//CSS文件路径
puhui.CSS_URL = "http://127.0.0.1/css";
//JS文件路径
puhui.JAVASCRIPT_URL = "http://127.0.0.1/js";
//图片文件路径
puhui.IMAGE_URL = "http://127.0.0.1/images";
//判断是否空字符串
puhui.isEmptyString = function(str){
	return null == str || $.trim(str).length == 0;
};
//动态加载CSS
puhui.loadCSS = function(link){
	var node = document.createElement("link");
	node.href = link;
	node.type = "text/css";
	node.rel = "stylesheet";
	document.getElementsByTagName('head')[0].appendChild(node); 
};
//动态加载JavaScript并执行回调函数
puhui.loadJS = function(link,callback){
	var node = document.createElement("script");
	node.src = link;  
	document.getElementsByTagName('head')[0].appendChild(node); 
	if(!callback){return false;}
	if (navigator.userAgent.toLowerCase().indexOf('msie')>-1){	
		node.onreadystatechange = function(){
			if(this.readyState == "complete" || this.readyState == "loaded"){
				callback();
			}
		};
	} else {
		node.onload = function(){callback();};
	}
};
/**
*  字符转换日期
*  parseDate('2006-1-1') return new Date(2006,0,1)  
*  parseDate(' 2006-1-1 ') return new Date(2006,0,1)  
*  parseDate('2006-1-1 15:14:16') return new Date(2006,0,1,15,14,16)  
*  parseDate(' 2006-1-1 15:14:16 ') return new Date(2006,0,1,15,14,16);  
*  parseDate('2006-1-1 15:14:16.254') return new Date(2006,0,1,15,14,16,254)  
*  parseDate(' 2006-1-1 15:14:16.254 ') return new Date(2006,0,1,15,14,16,254)  
*  parseDate('不正确的格式') retrun null  
*
*/
puhui.parseDate = function(str){   
  if(typeof str == 'string'){   
    var results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) *$/);   
    if(results && results.length>3)   
      return new Date(parseInt(results[1]),parseInt(results[2]) -1,parseInt(results[3]));    
    results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2}) *$/);   
    if(results && results.length>6)   
      return new Date(parseInt(results[1]),parseInt(results[2]) -1,parseInt(results[3]),parseInt(results[4]),parseInt(results[5]),parseInt(results[6]));    
    results = str.match(/^ *(\d{4})-(\d{1,2})-(\d{1,2}) +(\d{1,2}):(\d{1,2}):(\d{1,2})\.(\d{1,9}) *$/);   
    if(results && results.length>7)   
      return new Date(parseInt(results[1]),parseInt(results[2]) -1,parseInt(results[3]),parseInt(results[4]),parseInt(results[5]),parseInt(results[6]),parseInt(results[7]));    
  }   
  return null;   
};
/**       
* 对Date的扩展，将 Date 转化为指定格式的String       
* 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符       
* 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)       
* eg:       
* (new Date()).formate("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423       
* (new Date()).formate("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04       
* (new Date()).formate("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04       
* (new Date()).formate("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04       
* (new Date()).formate("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18       
*/          
Date.prototype.formate=function(fmt) {
    var o = {
    "M+" : this.getMonth()+1, //月份
    "d+" : this.getDate(), //日
    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时
    "H+" : this.getHours(), //小时
    "m+" : this.getMinutes(), //分
    "s+" : this.getSeconds(), //秒
    "q+" : Math.floor((this.getMonth()+3)/3), //季度
    "S" : this.getMilliseconds() //毫秒
    };
    var week = {
    "0" : "\u65e5",
    "1" : "\u4e00",
    "2" : "\u4e8c",
    "3" : "\u4e09",
    "4" : "\u56db",
    "5" : "\u4e94",
    "6" : "\u516d"          
    };
    if(/(y+)/.test(fmt)){
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }
    if(/(E+)/.test(fmt)){
        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);
    }
    for(var k in o){
        if(new RegExp("("+ k +")").test(fmt)){
 fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
    return fmt;
};
/**
* 将对象格式化成字符串
*/
puhui.toString = function(obj){var str = "";if(obj){for(var i in obj){str+=i+":"+obj[i]+",";}if(str.length==0){str+=obj}}return str;};
/**
* 用于鼠标事件切换图片显示效果
* puhui.toggleAImg("a/a list/img/img list/other has img dom list");
*/
puhui.toggleAImg = function(select){
	var $l = $(select);
	if ($l.length == 0){return;}
	$l.each(function(){
		$t = $(this);
		$t.mouseenter(function(){
			_z_toggleAImg($(this));
		}).mouseleave(function(){
			_z_toggleAImg($(this));
		});
	});
	function _z_toggleAImg($t){
		var $img = $t.is("img")?$t:$t.find("img"),
		img = $img.attr("name");
		if (!puhui.isEmptyString(img)){
			if (img.indexOf(puhui.IMAGE_URL) == -1)img = puhui.IMAGE_URL + "/version2/" + img;
			$img.attr("name",$img.attr("src"));
			$img.attr("src",img);	
		}
	}
};
/**
* 获取页面滚动条长度
*/
puhui.maxScrollX = function(){
	return $(document).width()-$(window).width();
};
/**
 * 添加cookie
 */
puhui.addCookie = function(key,value){
	document.cookie = key + '=' + value;
};
/**
* 查找cookie
*/
puhui.getCookie = function(key){
	var cookies = document.cookie.split(";");
	var value = '';
	$.each(cookies,function(i,v){
		var temp = v.split("=");
		if(temp.length == 2){
			if(temp[0].trim() == key){
				value = temp[1];
				return;
			}
		}
	});
	return value;
};
/**
 * 千分位格式化整数
 */
puhui.formatAmount = function(amount){
	if (amount == 0)
		return "0";
	amount = amount.toString().replace(/^(\d*)$/, "$1.");
	amount = amount.replace(".", ",");
	var re = /(\d)(\d{3},)/;
	while (re.test(amount))
		amount = amount.replace(re, "$1,$2");
	amount = amount.replace(/,(\d\d)$/, ".$1");
	//把最后一个逗号截取掉
	amount=amount.substring(0,amount.lastIndexOf(","));
	return amount;
};
/**
 *格式化金额
 */
puhui.formatMoney=function(value) {
	 var s = value; //获取小数型数据
     s += "";
     if (s.indexOf(".") == -1) s += ".0"; //如果没有小数点，在后面补个小数点和0
     if (/\.\d$/.test(s)) s += "0"; //正则判断
     while (/\d{4}(\.|,)/.test(s)) //符合条件则进行替换
         s = s.replace(/(\d)(\d{3}(\.|,))/, "$1,$2"); //每隔3位添加一个
     return s;
};
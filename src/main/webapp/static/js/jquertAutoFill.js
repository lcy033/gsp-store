//jquery自动补全

//定义全局变量
var timeoutId;				//表示延時向服务器发送请的时间

/*Ajax 自动补全*/

$(document).ready(function () {
	var _height=200;
	var _class="autofill";
	var createPop=function(){
		var _arr=$(".jext-autoFill");
		var _div,_item,_offsetLeft,_offsetBottom,_event_change;
		$.each(_arr,function(k,v){
			_item=$(v);
			//console.log(_item);
			_offsetLeft=_item.offset().left;
			_offsetBottom=_item.offset().top+_item.height()+6;
			_event_change=_item.attr("event-change");
			//新建弹出列表
			_div=$("<div></div>");
			_div.attr("id","jext-autoFill-"+k);
			_item.attr("data-id","jext-autoFill-"+k);
			_div.width(_item.width()+4);
			_div.height(_height);
			//console.log(_offsetBottom);
			_div.css({"position":"absolute","left":_offsetLeft,"top":_offsetBottom,"display":"none","z-index":"10000"});
			_div.addClass(_class);
			_div.appendTo($("body"));
			//绑定change事件
			_item.keyup(findcon);
			_item.click(function(e){
				e && e.stopPropagation ? e.stopPropagation() : window.event.cancelBubble=true;
			});
			_div.click(function(e){
				e && e.stopPropagation ? e.stopPropagation() : window.event.cancelBubble=true;
			});
			/*_item.focusout(function(){
				var _v=$("#"+$(this).attr("data-id"));
				//_v.empty();
				_v.hide();
			});
			_div.focusout(function(){
				var _v=$(this);
				_v.empty();
				_v.hide();
			});*/
			_item.focus(whenfocus);
			if(_event_change){
				_item.on("change",eventChange);
				function eventChange(){
					eval(_event_change+"()");
				}
			}
		});
	};
	function whenfocus(event){
		var _me=$(this),
		_val=_me.val(),
		_url=_me.attr("data-url"),
		_arg=_me.attr("data-arg"),
		_pop=$("#"+_me.attr("data-id")),
		_obj={},
		_arr=new Array();
	_arr=_arg.split(",");
	
	$.each(_arr,function(k,v){
		_obj[v]=$("#"+v).val() || $("#"+v).combobox("getValue");
	});
	_obj.keyword=_val;

	//console.log("2222222222222")
	//console.log(_obj);
	//得到当前按键的code值
    if($.trim(_val).length > 0){
    	clearTimeout(timeoutId);
		//对服务器端进行交互延迟500ms,避免快速打字造成的频繁请求
		timeoutId = setTimeout(function(){
	        //获取异步数据
	        var _val=_me.val();
	        if($.trim(_val).length < 1){
	        	clearcon(_pop);
	        	return;
	        }
	        $.post(_url,_obj,function(data){
	        	 //console.log(data);
	                if(data!=null){
	                // console.log(_me); 
	                    setcon(_me,data);    //显示服务器结果  
	                }  
	                else  
	                    clearcon(_pop);  
	        },"json");  
    	},500);
	}
	}
	function findcon(event){
		var _me=$(this),
			_val=_me.val(),
			_url=_me.attr("data-url"),
			_arg=_me.attr("data-arg"),
			_pop=$("#"+_me.attr("data-id")),
			_obj={},
			_arr=new Array();
		_arr=_arg.split(",");
		$.each(_arr,function(k,v){
			_obj[v]=$("#"+v).val() || $("#"+v).combobox("getValue");
		});
		_obj.keyword=_val;
		//console.log(_obj);
		//得到当前按键的code值
		var myEvent = event || window.event;
		var keyCode = myEvent.keyCode;	
		// || 
		if (keyCode >= 65 || keyCode <= 90 || keyCode == 8 || keyCode == 46){
		    if($.trim(_val).length > 0){  
		    	if(keyCode == 38){
			    	//console.log("上");
			    	var _lieq=parseInt($("#licolor").index()-1);
			    	if($("#licolor").length!=0 || _lieq>=0){			    		
			    		var _liindex=parseInt($("#licolor").index()-1);			    		
			    		if($(_pop.selector).find("li:first").attr("id")!="licolor"){			    			
			    			var _li=$(_pop.selector).find("li").eq(_liindex);				    	
						    _me.attr("data-value",_li.attr("data-value"));
					    	_me.val(_li.text());
							getBranchNo(_li.attr("data-no"));
							$("#licolor").attr("id","");
							_li.attr("id","licolor");	
			    		}
			    	}			    	
			    }else if(keyCode == 40){
			    	//console.log("下");			    
			    	var _lieq=parseInt($("#licolor").index()+1);
					if($("#licolor").length==0){				
						var _lifirst=$(_pop.selector).find("li:first");				    	
				    	_me.attr("data-value",_lifirst.attr("data-value"));
				    	_me.val(_lifirst.text());
						getBranchNo(_lifirst.attr("data-no"));
						_lifirst.attr("id","licolor");						
					}else if(_lieq==$(_pop.selector).find("li").length){						
						$(_pop.selector).find("li:last").attr("id","licolor");
					}else{												
							var _liindex=parseInt($("#licolor").index()+1);
							//console.log(_liindex)
							if(_liindex<8){
								var _li=$(_pop.selector).find("li").eq(_liindex);				    	
								_me.attr("data-value",_li.attr("data-value"));
								_me.val(_li.text());
								getBranchNo(_li.attr("data-no"));
								$("#licolor").attr("id","");
								_li.attr("id","licolor");									
							}											
					}			
					
			    }else{
			    	clearTimeout(timeoutId);
					//对服务器端进行交互延迟500ms,避免快速打字造成的频繁请求
					timeoutId = setTimeout(function(){
				        //获取异步数据
				        var _val=_me.val();
				        if($.trim(_val).length < 1){
				        	clearcon(_pop);
				        	return;
				        }
				        $.post(_url,_obj,function(data){
				        	 //console.log(data);
				                if(data!=null){
				                // console.log(_me); 
				                    setcon(_me,data);    //显示服务器结果  
				                }  
				                else  
				                    clearcon(_pop);  
				        },"json");  
			    	},500);			    	
			    }
		    	
		    };
	    }
	    else{
	        clearcon(_pop); //无输入时清除提示框（例如用户按del键）  
		}
	
	}
	function clearcon(_pop){
    	//清除提示内容  
    	_pop.empty();
		_pop.hide();
	} 
	//选择子元素触发事件
	function liClick(){		
		var _val=$(this).attr("data-value"),
		_pop=$("#"+$(this).attr("data-id")),		
		_item=$("[data-id='"+$(this).attr("data-id")+"']");		
		var branchNo = $(this).attr("data-no");
		// console.log(_item);
		_item.attr("data-value",_val);
		//console.log("title:"+branchNo);
		_item.val($(this).text());
		//clearcon(_pop);
		getBranchNo(branchNo,$(this).attr("data-id"));
		$("#licolor").attr("id","");
		$(this).attr("id","licolor");
	}
	function setcon(_item,the_con){
	    //显示提示框，传入的参数即为匹配出来的结果组成的数组  
	    var _pop=$("#"+_item.attr("data-id"));
		clearcon(_pop); //每输入一个字母就先清除原先的提示，再继续
		var _ul=$("<ul></ul>"),_li;
		_ul.height(_height);
		$.each(the_con,function(k,v){		 	 
			_li=$("<li data-value='"+v.bankBranchName+"' title='"+v.bankBranchName+"' data-no='"+v.bankNo+"' data-id='"+_item.attr("data-id")+"'>"+v.bankBranchName+"</li>");
			_li.click(liClick);				
			_li.appendTo(_ul);
		});
		_ul.appendTo(_pop);
		_pop.show();
		
	}	
	createPop();
});


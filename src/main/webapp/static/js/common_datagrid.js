/*
 * 实现datagridext
 * 使用方法同datagrid
 * */
(function($){
	function initComponent(target){
		 var _handler=$(target),
	   	   _obj={},
	   	   _defaults=$.data(target, 'datagridext').options,
	   	   _settings={},
	   	   _options,
	   	   _columns=new Array(),
	   	   _datagrid,
	   	   _mask=$('<div class="datagrid-mask" style="display:none;opacity: 0.5;z-index:10000"><div class="datagrid-mask-msg" style="display:block;left:50%;z-index:90000">正在努力加载，请稍等...</div></div>').appendTo(_handler.closest("body"));
		 _mask.show();
		 $.ajax({type:"post",url:_defaults.url,data:_defaults.queryParams,dataType:"json",success:function(data){
	   		  _obj=data;
	          _columns.push(catColumns(target,_obj.columnShows));
	          _settings.columns=_columns;
	          _settings.data=_obj;
	          _settings.pageSize=_obj.pageModel.size ? _obj.pageModel.size : 20;
	          _settings.showFooter=_obj.conf.showFooter ? true : false;
	          _options=$.extend(false,{},_settings,_defaults);
	          _mask.hide();
	          _datagrid=_handler.datagrid(_options);
			  //执行添加排序方法
	          _datagrid.datagrid("bondHeadMenu");
	          _datagrid.datagrid("resize");
	          return _datagrid;
		   },error:function(){
			   _mask.hide();
			   alert("数据渲染出错,请刷新！");
		   }});
	}
	function catColumns(target,_columns){
		var _options=$.data(target, 'datagridext').options;
		$.each(_columns,function(_i,_v){
			switch(_v.type){
				case 1:
					_v.align="right";
					_v.formatter=_options.formatter.float;
				break;
				case 2:
					_v.align="right";
				break;
				case 3:
					_v.align="right";
					_v.formatter=_options.formatter.float;
				break;
				case 4:
					_v.align="center";
					_v.formatter=_options.formatter.datetime;
				break;
				case 5:
					_v.align="center";
					_v.formatter=_options.formatter.date;
				break;
				case 6:
					_v.align="center";
					_v.formatter=_options.formatter.boolean;
				break;
			}
		});
		return  _columns;
	}
	$.fn.datagridext=function($defaults){
		return this.each(function(){
			var state = $.data(this, 'datagridext');
			//初始化配置
			var $opts={
					autoLoad:false,//首次数据加载不使用ajax
					addHeadMenu:false,//初始化headMenu标识
					formatter:{
						float:function(v){
							if(!v && v!=0){
								return;
							}
							 var s = v; //获取小数型数据
						     s += "";
						     if (s.indexOf(".") == -1) s += ".0"; //如果没有小数点，在后面补个小数点和0
						     if (/\.\d$/.test(s)) s += "0"; //正则判断
						     while (/\d{4}(\.|,)/.test(s)) //符合条件则进行替换
						         s = s.replace(/(\d)(\d{3}(\.|,))/, "$1,$2"); //每隔3位添加一个
						     return s;
						},
						date:function(v){
							var _date=v ? new Date(v).formate("yyyy-MM-dd") : '';
							return _date;
						},
						datetime:function(v){
							var _date=v ? new Date(v).formate("yyyy-MM-dd HH:mm") : '';
							return _date;
						},
						boolean:function(v){
							return typeof(v)=="boolean" ? (v?"是":"否") :"";
						}
					},
					loadFilter: function(data){
						var opts=$(this).datagrid("options");
						/*if(!opts.addHeadMenu){
							//执行添加排序方法
							$(this).datagrid("bondHeadMenu");
							opts.addHeadMenu=true;
						}*/
						if(opts.showFooter){
							var _arr= new Array(),
								_o={};
							if(data.footers){
								$.each(data.footers,function(_i,_v){
									_o={};
									$.each(_v.configs,function(_j,_value){				
										_o[_value.key]=_value.value;
									});
									_arr.push(_o);	
								});
							}
							return {total:data.pageModel.total,rows:data.data,footer:_arr};
						}
						
						return {total:data.pageModel.total,rows:data.data};
					},
					loader:function(_218,_219,_21a){
						var opts=$(this).datagrid("options");
						_218.size=_218.rows;
						if(!opts.url || !opts.autoLoad){
							opts.autoLoad=true;
							return false;
						}
//						console.log(_218);
						$.ajax({type:opts.method,url:opts.url,data:_218,dataType:"json",success:function(data){
						_219(data);
						},error:function(){
						_21a.apply(this,arguments);
						}});
					}
				};
			if (state){
				state.options=$.extend(state.options, $defaults,$opts);
			} else {
				state = $.data(this, 'datagridext', {
					options: $.extend({}, $defaults,$opts)
				});
				initComponent(this);
			}
		});
		  
	   	};
})(jQuery);
/*
 * datagrid扩展bondHeadMenu方法
 * */
$.extend($.fn.datagrid.methods, {
	bondHeadMenu:function(jq){
		function addMenu(_h,_opts,_options){
			var target=_h.datagrid("getPanel");
			var _headerTds=$(".datagrid-view2 .datagrid-header-inner table tr:first-child",target),
				_div;
			$.each(_opts,function(_i,_v){
				_div=_headerTds.find("[field='"+_v.field+"'] .datagrid-cell");
				// _div.css("width",_div.width()+22);
				// _div.css("width",_div.width()+40);
				// var _tds=$("."+_v.cellClass);
				// _tds.css("width",_div.width());
				// console.log(_v);
				createMeun(_h,_div,_v,_options,_opts);
			});

		};
		function createMeun(_h,_div,_opt,_options,_opts){
			var _mmId="mm-"+_opt.field;
			var _mm=$('<ul id="'+_mmId+'" class="dMenu" ></ul>');
			_div.css("position","relative");
			var _tmp=$("<span class='datagrid-arrow-icon m-btn-downarrow'></span>");
			_tmp.css({"display":"inline-block","width":"15px","height":"100%","right":"0px","bottom":"0","padding":"0px 5px 0 0","border-left":"dotted 1px #ccc","float":"right","cursor":"pointer"});
			_tmp.appendTo(_div);
			_mm.appendTo($("body"));
			_tmp.bind("click",function(e){
				var _offset=$(this).offset();
				_mm.css({
					display:'block',
					position:'absolute',
					top:_offset.top+30,
					left:_offset.left
				});
			});
			//添加排序菜单
			if(_opt.order){
				var _orderAsc=$('<li class="sort-asc"  field="'+_opt.field+'"><span class="menu-icon icon-asc"></span>升序排序</li>'),
					_orderDesc=$('<li  class="sort-desc" field="'+_opt.field+'"><span class="menu-icon icon-desc"></span>降序排列</li>');
				//绑定菜单
				_orderAsc.click(function(){
					_field=$(this).attr("field");
					//修改排序的表头样式
					$(".sort-col").removeClass("sort-col");
					_div.addClass("sort-col");
					var _op=$.extend(_options.queryParams,{
						sort: _field,
						order: 'asc'
					});
					_h.datagrid('load',_op);
				});
				_orderDesc.click(function(){
					_field=$(this).attr("field");
					//修改排序的表头样式
					$(".sort-col").removeClass("sort-col");
					_div.addClass("sort-col");
					var _op=$.extend(_options.queryParams,{
						sort: _field,
						order: 'desc'
					});
					_h.datagrid('load',_op);
				});
				_orderAsc.appendTo(_mm);
				_orderDesc.appendTo(_mm);
			}
			//添加过滤菜单
			if(_opt.filterType){
				var _mmFilterId="mm-filter-"+_opt.field;
				var _filterItem=$('<li><span class="menu-icon icon-filter-uncheck"></span><span class="filter"    field="'+_opt.field+'">过滤</span></li>');
				_filterItem.appendTo(_mm);
				_filterItem.bind("mouseover",function(){
					var _ul=$(this).children("ul");
					_ul.show();
				}).bind("mouseleave",function(){
					var _ul=$(this).children("ul");
					_ul.hide();
				});
				var _tpl=createFilterMenu(_h,_mmFilterId,_opt,_options,_opts);
				_tpl.appendTo(_filterItem);
				// _filterItem.menubutton({
			 //    	menu: '#'+_mmFilterId
				// });
			}
			// _tmp.menubutton({
			//     menu: '#'+_mmId
			// });
			//绑定过滤事件
			// console.log(_mm);
			// _mm.find(".for-string").on({
			// 	click:function(){
			// 		e.stopPropagation();
			// 		console.log("---");
			// 		return false;
			// 	}
			// });
			// _div.mouseover(function(){
			// 	_tmp.show();
			// });
			// _div.mouseout(function(){
			// 	_tmp.hide();
			// });
		}
		function createFilterMenu(_h,_id,_opt,_options,_opts){
			var _timeOut,
				_sec=1300,
				_me=_h,
				_type=_opt.filterType,
				_data=_opt.listValue,
				_tpl=$('<ul id="'+_id+'" field="'+_opt.field+'"></ul>'),
				// _string_input=$('<input type="text" class="filter-input" />'),
				// _greater_than_input=$('<input type="text" class="filter-input" />'),
				// _less_than_input=$('<input type="text" class="filter-input" />'),
				_stringTpl=$('<li  class="filter-item"  ><span class="menu-icon icon-filter-string"></span><input type="text" class="filter-input for-string" field="'+_opt.field+'" /></li>'),
				_greater_than_tpl=$('<li  class="filter-item" ><span class="menu-icon icon-greater-than"></span><input type="text" class="filter-input filter-greater-than" field="'+_opt.field+'" /></li>'),
				_less_than_tpl=$('<li  class="filter-item"><span class="menu-icon icon-less-than"></span><input type="text" class="filter-input filter-less-than" field="'+_opt.field+'" /></li>'),
				_start_date_tpl=$('<li  class="filter-item" ><span class="menu-icon icon-greater-than"></span><input type="text" class="filter-input filter-start-date" field="'+_opt.field+'" /></li>'),
				_end_date_tpl=$('<li  class="filter-item" ><span class="menu-icon icon-less-than"></span><input type="text" class="filter-input filter-end-date" field="'+_opt.field+'" /></li>');
				
				switch(_type){
					case 'string':
						_stringTpl.appendTo(_tpl);
						_stringTpl.find("input").on({
							click:function(e){
								e.stopPropagation();
								return false;
							},
							keyup:function(){
								var _t=$(this);	
								_timeOut=setTimeout(function(){
									var _val=_t.val(),
										_field=_t.attr("field"),
										_obj={};
									filterConditions(_options,_opts,_field);
									if(!$.trim(_val)){
									if(_options.queryParams[_field]){
											delete _options.queryParams[_field];
											_me.datagrid('load');
										}
										return false;
									}
									_obj[_field]=_val;
									var _op=$.extend(_options.queryParams,_obj);
									_me.datagrid('load',_op);
								},_sec);
							},
							keydown:function(){
								clearTimeout(_timeOut);
							}
						});
					// console.log(_stringTpl.find(".filter-input"));
					// _stringTpl.find(".filter-input").on({
					// click:function(e){
					// 	e.stopPropagation();
					// 	console.log("---");
					// 	//return false;
					// 	// var _me=this;
					// 	// _mm.menu('setIcon', {
					// 	// 	target: _me,
					// 	// 	iconCls: 'icon-closed'
					// 	// });
					// 	}
					// });
					break;
					case 'number':
						_less_than_tpl.appendTo(_tpl);
						_greater_than_tpl.appendTo(_tpl);
						_greater_than_tpl.find("input").on({
							click:function(e){
								e.stopPropagation();
								return false;
							},
							keyup:function(){
								var _ts=$(this);
								_timeOut=setTimeout(function(){
									var _val=Number(_ts.val()),
									_less_than=_ts.closest("ul").find(".filter-less-than"),
									_end_val=Number(_less_than.val()),
									_field=_ts.attr("field"),
									_obj={};
									filterConditions(_options,_opts,_field);
									// console.log("start:"+_val+",end:"+_end_val);
									if(!$.trim(_val)){
										if(_options.queryParams[_field+"_start"]){
											delete _options.queryParams[_field+"_start"];
											_me.datagrid('load');
										}
										return false;
									}
									if(_end_val && _val<_end_val){
										_obj[_field+"_start"]=_val;
										_obj[_field+"_end"]=_end_val;
									}else{
										_less_than.val("");
										delete _options.queryParams[_field+"_end"];
										_obj[_field+"_start"]=_val;
									}
									var _op=$.extend(_options.queryParams,_obj);
									_me.datagrid('load',_op);
								},_sec);
							},
							keydown:function(){
								clearTimeout(_timeOut);
							}
						});
						_less_than_tpl.find("input").on({
							click:function(e){
								e.stopPropagation();
								return false;
							},
							keyup:function(){
								var _ts=$(this);
								_timeOut=setTimeout(function(){
									var _val=Number(_ts.val()),
									_greater_than=_ts.closest("ul").find(".filter-greater-than"),
									_start_val=Number(_greater_than.val()),
									_field=_ts.attr("field"),
									_obj={};
									filterConditions(_options,_opts,_field);
									// console.log("start:"+_start_val+",end:"+_val);
									if(!$.trim(_val)){
										if(_options.queryParams[_field+"_end"]){
											delete _options.queryParams[_field+"_end"];
											_me.datagrid('load');
										}
										return false;
									}

									if(_start_val && _val>_start_val){
										_obj[_field+"_end"]=_val;
										_obj[_field+"_start"]=_start_val;
									}else{
										_greater_than.val("");
										delete _options.queryParams[_field+"_start"];
										_obj[_field+"_end"]=_val;
									}
									var _op=$.extend(_options.queryParams,_obj);
									_me.datagrid('load',_op);
								},_sec);
							},
							keydown:function(){
								clearTimeout(_timeOut);
							}
						});
					break;
					case 'date':
						_start_date_tpl.appendTo(_tpl);
						_end_date_tpl.appendTo(_tpl);
					break;
					case 'multiselect':
						if(_data.length>0){
							$.each(_data,function(_i,_v){
								var _item=$('<li class="mult-item"><span class="menu-icon icon-filter-uncheck"></span><span class="filter" field="'+_opt.field+'" data="'+_v[0]+'">'+_v[1]+'</span></li>');
								_item.appendTo(_tpl);
								_item.on({
									click:function(e){
										var _m=$(this),
											_t=_m.closest('ul');
										if(_m.hasClass('item-selected')){
											_m.removeClass('item-selected');
											_m.find('.menu-icon').removeClass('icon-filter-checked').addClass('icon-filter-uncheck');
										}else{
											_m.addClass('item-selected');
											_m.find('.menu-icon').removeClass('icon-filter-uncheck').addClass('icon-filter-checked');
										}
										clearTimeout(_timeOut);
										_timeOut=setTimeout(function(){
											var _items=_t.find('.item-selected'),_field=_t.attr('field'),_obj={};
											var _arr=[];
											filterConditions(_options,_opts,_field);
											// console.log(_items.length);
											if(_items.length<1){
												if(_options.queryParams[_field]){
													delete _options.queryParams[_field];
													_me.datagrid('load');
												}
												return false;
											}
											$.each(_items,function(_i,_v){
												var _this=$(_v),
													_span=_this.find('.filter'),
													_data=_span.attr('data');
												_arr.push(_data);
												
											});
											_obj[_field]=_arr.join(',');
											var _op=$.extend(_options.queryParams,_obj);
											// console.log(_op);
											_me.datagrid('load',_op);
										},_sec);
									}
								});
							});
						}
						
					break;
				}
				return _tpl;
		}
		function  filterConditions(_options,_opts,_field){
			var _querys=_options.queryParams;
			$.each(_opts,function(_i,_v){
				if(_querys[_field] || _querys[_field+'_start'] || _querys[_field+'_end']) return;
				if(_querys[_v.field] || _querys[_v.field+'_start'] || _querys[_v.field+'_end']){
				switch(_v.filterType){
					case 'string':
						delete _querys[_v.field];
						$('#mm-filter-'+_v.field).find('input').val('');
					break;
					case 'multiselect':
						delete _querys[_v.field];
						var _items=$('#mm-filter-'+_v.field).find('.item-selected');
						$.each(_items,function(_i,_v){
							var _m=$(_v);
							_m.removeClass('item-selected');
							_m.find('.menu-icon').removeClass('icon-filter-checked').addClass('icon-filter-uncheck');
						});
						
					break;
					case 'number':
					case 'date':
						delete _querys[_v.field+'_start'];
						delete _querys[_v.field+'_end'];
						$('#mm-filter-'+_v.field).find('input').val('');
					break;
				}

				}
			});
		}
		return jq.each(function(){
			var _me=$(this);
			var _options=$.data(this,"datagrid").options;
			var _fields =_me.datagrid('getColumnFields');
			var _opts=new Array();
			var ss=["<style type=\"text/css\">"];
			var _headerTds=$(".datagrid-view2 .datagrid-header-inner table tr:first-child",_me.datagrid("getPanel")),
				_div;
			$.each(_fields,function (i, value) {
			    	_opt=_me.datagrid('getColumnOption',value);
			    	if(_opt.order || _opt.filterType){
			    		_opts.push(_opt);
			    		// console.log(_opt);
			    		_div=_headerTds.find("[field='"+_opt.field+"'] .datagrid-cell");
			    		var _s=_div.find("span:first").width();
			    		// console.log(_div.width());
			    		if((_s+20+4)>_div.width()){
							var _m=_s+20+4-_div.width();
				    		_opt.boxWidth=_opt.boxWidth+_m;
							_opt.width=_opt.width+_m;
							_div.css("width",_s+20+4);
			    		}
			    		// console.log(_opt);
			    	}
			    	ss.push("."+_opt.cellClass+"{width:"+_opt.boxWidth+"px}");
			    });
			// console.log(_opts);
			ss.push("</style>");
			$(ss.join("\n")).appendTo($("div.datagrid-view",_me.datagrid("getPanel")));
			addMenu(_me,_opts,_options);
			$(document).unbind('.dmenu').bind('mousedown.dmenu', function(e){
				var allMenu = $('body>ul.dMenu:visible');
				var m = $(e.target).closest('ul.dMenu', allMenu);
				if (m.length){return}
				allMenu.hide();
			});
			
			//绑定过滤事件
			
			
			$('.filter-start-date').datebox();
		});
	}
});
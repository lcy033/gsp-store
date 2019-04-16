
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="taglibs.jsp" %>
<div id="topbar" class="title" >
    <div style="float:left;margin-right:80px;">
		<img src="${ctx}/static/images/logo.png"/>
	</div>
	<div id="notice" style="float:left;margin:15px 0 0 0;">
	   <img src="${ctx}/static/images/volume.png" style="display:none;margin-bottom:-8px;">
	   <span style="color:#f00;height:20px;line-height:20px;"></span>
	</div>
	<div style="float:right">
		<span style="color:#BE0000;line-height:26px;"><img src="${ctx}/static/images/user-icon.png" width=16 height=16 style="margin:-2px 1px"/>${user.user}</span>
		<a id="logout"class="easyui-linkbutton"
			plain="true" href="${ctx}/user/logout"><img src="${ctx}/static/images/return.png" style="margin:0 3px -3px -6px;">登出</a>
	</div>
</div>
<script>
	var _systemSelect = $('#systemSelect');
	
	//给easyUI下拉框或日期框添加点击事件
	$("#topbar").delegate('span[class^="combo"] input[class^="combo-text validatebox-text"]','click',function(){
		$(this).next('span').children('span[class="combo-arrow"]').click();
	});
	
	$(function() {
		//notice();
		//每30s循环获取系统通知
		var timer1 = setInterval(notice, 30000);
		
		//添加点击事件
		_systemSelect.combobox({
			panelHeight:40,
			onSelect : function(param) {
				if (param.value) {
					var url = window.location.origin + param.value;
					window.open(url);
				}
			}
		});
	});
	
	//获取系统通知内容
	function notice() {
		$.ajax({
			url : "${ctx}/static/notice.txt",
			cache : false,
			dataType : "text",
			success : function(data) {
				if (!data) {
					$("#notice img").hide();
				} else {
					$("#notice img").show();
					$("#notice span").html(data);
				}
			}
		});
	}
</script>

var s = "";
if(navigator.userAgent.indexOf("MSIE")>0){
	s = "<OBJECT id=HWPostil1 align='middle' style='LEFT: 0px; WIDTH: 100%; TOP: 0px; HEIGHT: 680px'"
		+ "classid=clsid:FF1FE7A0-0578-4FEE-A34E-FB21B277D561 codebase='HWPostil.ocx#version=3,1,0,2'>"
		+ "<PARAM NAME='_Version' VALUE='65536'>"
		+ "<PARAM NAME='_ExtentX' VALUE='17410'>"
		+ "<PARAM NAME='_ExtentY' VALUE='10874'>"
		+ "<PARAM NAME='_StockProps' VALUE='0'>"
		+ "</OBJECT>";
}

if(navigator.userAgent.indexOf("Chrome")>0){
	s = "<object id='HWPostil1' type='application/x-itst-activex' align='baseline' border='0'"
		+ "style='LEFT: 0px; WIDTH: 100%; TOP: 0px; HEIGHT: 680px'"
		+ "progid='HWPostil.ocx#version=3,1,0,2'"
		+ "clsid='{FF1FE7A0-0578-4FEE-A34E-FB21B277D561}'"
		+ "event_NotifyCtrlReady='HWPostil1_NotifyCtrlReady'"
		+"event_JSNotifyBeforeAction='HWPostil1_JSNotifyBeforeAction'"
		+ "</object>";	
	}

if(navigator.userAgent.indexOf("Firefox")>0){
	s = "<object id='HWPostil1' type='application/x-itst-activex' align='baseline' border='0'"
		+ "style='LEFT: 0px; WIDTH: 100%; TOP: 0px; HEIGHT: 680px'" 
		+ "progid='HWPostil.ocx#version=3,1,0,2'"
		+ "clsid='{FF1FE7A0-0578-4FEE-A34E-FB21B277D561}'"
		+ "event_NotifyCtrlReady='HWPostil1_NotifyCtrlReady'"
		+"event_JSNotifyBeforeAction='HWPostil1_JSNotifyBeforeAction'"
		+ "</object>";	
}
document.write(s);

	
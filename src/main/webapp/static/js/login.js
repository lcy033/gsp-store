$(document).ready(function() {
	
//回车登录
$(document).keydown(function(e){
	if(e.keyCode == 13) {
		submit();
	}
});

$('#Kaptcha').click(     
        function() {     
           $(this).hide().attr('src','Kaptcha.jpg?' + Math.floor(Math.random() * 100)).fadeIn();     
});

$("#userName").blur(function(){
	if($(this).val()){
		$(this).removeClass('username');
	}else{
		$(this).addClass('username');
		
	}
});

$("#password").blur(function(){
	if($(this).val()){
		$(this).removeClass('password');
	}else{
		$(this).addClass('password');
		
	}
});
$("#code").blur(function(){
	if($(this).val()){
		$(this).removeClass('code');
	}else{
		$(this).addClass('code');
		
	}
});
});
//表单提交
function submit()
{
	$('#loginform').submit();
}

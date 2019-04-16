<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
.rankLow{
	color: #ff9c3a;
	background: url(${ctx}/lend-app-report/static/images/pwd_sprite.png) 7px -100px;
	padding-left: 82px;
	
}
.rankMiddle{
	color: #61d01c;
	background: url(${ctx}/lend-app-report/static/images/pwd_sprite.png) 9px -142px;
	padding-left: 82px;
}
.rankHigh{
	color: #61d01c;
	background: url(${ctx}/lend-app-report/static/images/pwd_sprite.png) 0px -190px;
	padding-left: 70px;
}
.pwd_info{
	position: relative;
}
.password_info{
	vertical-align: top;
	color: #808080;
}
.password_txt{
	padding: =8px;
	background: transparent;
}
.pwd_tips{
	width: 170px;
	height: 71px;
	padding-top: 13px;
	position: absolute;
	top: -26px;
	background: url(${ctx}/lend-app-report/static/images/pwd_sprite.png) no-repeat scroll 0 0 rgba(255, 255, 255, 1)
}
.default{
	background: url(${ctx}/lend-app-report/static/images/pwd_sprite.png) no-repeat scroll 7px -221px transparent;
	padding: 3px;
	padding-left: 21px;
}
.no{
	background: url(${ctx}/lend-app-report/static/images/pwd_sprite.png) no-repeat scroll 7px -286px transparent;
	padding: 3px;
	padding-left: 21px;
}
.yes{
	background: url(${ctx}/lend-app-report/static/images/pwd_sprite.png) no-repeat scroll 7px -252px transparent;
	padding: 3px;
	padding-left: 21px;
}
.red{
	color: #f66;
}
</style>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="editPwd">
     <form id="editCurrentUserPwdForm" method="post">
        <div  style="padding: 10px;">
         <!-- 隐藏域的属性 -->
         <input type="hidden" id = "id" name="id" />
				<table id ="pwd_table">
					<tr style="padding: 20px;">
						<td width='80px' style="text-align: center;padding: 5px" >原密码:</td>
						<td width='150px'><input name="oldPwd" id="oldPwd" type="password" placeholder="请输入原密码" class="easyui-validatebox" data-options="required:true" ></td>
					</tr>
					<tr style="padding: 20px;">
						<td width='80px' style="text-align: center;padding: 5px">新密码:</td>
						<td width='150px'>
							<input name="pwd" type="password" id = "newPwd" placeholder="请输入新密码" class="password_txt validate-box" data-options="required:true" oncopy='return false' onpaste='return false' >							
						</td>
						<td>
							<div class=pwd_info>
								<div class="pwd_tips" id="pwd_tips">
									<div class="default" id="pwd_tip1">长度为8-12个字符</div>
									<div class="default" id="pwd_tip3">不能包含空格</div>
									<div class="default" id="pwd_tip2">不能是纯数字</div>	
								</div>
								<table id="pwd_result" class="">
									<tbody>
										<tr>
											<td id="password_pic" class="rankLow">弱</td>
										</tr>
										<tr>
											<td id="password_info" class="password_info">试试字母、数字、标点的组合吧</td>
										</tr>
									</tbody>
								</table>
							</div>
						</td>
					</tr>
					<tr style="padding: 20px;">
						<td width='80px' style="text-align: center;padding: 5px">确认新密码:</td>
						<td width='150px'><input name="rePwd" type="password" placeholder="请再次输入新密码" class="easyui-validatebox" data-options="required:true,validType:'eqPwd[\'#editCurrentUserPwdForm input[name=pwd]\']'"  oncopy='return false' onpaste='return false' ></td>
					</tr>
				</table>
		</div>
	 </form>
</div>
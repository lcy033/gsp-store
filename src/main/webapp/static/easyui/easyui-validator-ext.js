$.extend($.fn.validatebox.defaults.rules, {
	// 固话验证
	tel : {
		validator : function(value, param) {
			if (value) {
				return /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/
						.test(value);
			} else {
				return true;
			}
		},
		message : '电话号码格式错误'
	},
	//中文或英文验证
	CHS : {
		validator : function(value, param) {
			return /^[A-Za-z\u4e00-\u9fa5]+$/.test(value);
		},
		message : '请输入汉字或者字母'
	},
	//验证金额值
	equals : {
		validator : function(value, param) {
			return $(param[0]).val() >= 0;
		},
		message : '请输入大于0的值.'
	},
	//验证月还款额小于借款金额
	big : {
		validator : function(value, param) {
			return new Number($(param[0]).val()) >= new Number(value);
		},
		message : '请输入的月接受还款额小于期望贷款金额.'
	},
	//身份证号验证
	idcard : {
		validator : function(value, param) {
			return /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X)$)/.test(value);
		},
		message : '请输入正确的身份证号码'
	},
	//手机号码验证
	mobile : {
		validator : function(value, param) {
			return /^1[3|4|5|8][0-9]\d{4,8}$/.test(value);
		},
		message : '手机号码不正确'
	},
	space:{
		validator :function(value,param){
			return /.*[^ ].*/.test(value);
		},
		message:'输入值不能全部为空'
	}
});
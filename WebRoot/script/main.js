var gRight = '<img src="images/icon_03c.gif" align="absmiddle"> ';
var gWrong = '<img src="images/icon_03b.gif" align="absmiddle"> ';
var gCue = '<img src="images/icon_03.gif" align="absmiddle"> ';
var gTipCodeOn = '请输入手机号码';
var gProStatus = 0;
var gProStatus2 = 0;
var gInputEmail = '';
var gInputUname = '';

$(document).ready(function(){
	$('#email').focus(showEmailTip);
	$('#email').blur(showEmailTipBlur);

	$('#uname').focus(showUnameTip);
	$('#uname').blur(showUnameTipBlur);

	$('#pass').focus(showPassTip);
	$('#pass').blur(showPassTipBlur);
	//$('#pass').keyup(passLevel);

	$('#cpass').blur(showCpassTipBlur);
	$('#cpass').focus(showCpassTip);

	$('#code').focus(showCodeTip);
	$('#code').blur(showCodeTipBlur);

	$('#submit').click(checkForm);
	$('#qtime').val(new Date().getTime());
	
	//新加问题验证
	//$('#question').focus(showQuestionTip);
	//$('#question').blur(showQuestionTipBlur);
	
	//$('#answer').focus(showAnswerTip);
	//$('#answer').blur(showAnswerTipBlur);
	
	//$("#newQuestion").focus(showNewQuestionTip);
	//$("#newQuestion").blur(checkNewQuestion);
	
});
function checkForm(){
	
	//email
	var iEmail = checkEmail(1);
	var iUname = checkUname(1);
	var iPass = checkPass();
	var iCpass = checkCpass();
	var iCode = checkCode();
	//var iQuestion = checkQuestion();
	//var iAnswer = checkAnswer();
	alert(gProStatus);
	alert(gProStatus2);
	if(gProStatus != 1 || gProStatus2 != 1 || iEmail || iUname || iPass || iCpass || iCode ){
		if(gProStatus != 1 && gProStatus != 2) checkUname();
		if(gProStatus2 != 1 && gProStatus2 != 2) checkEmail();
		alert("click");
		return false;
	}
	else{
//		if(checkIagree()) return false;
//		if(checkNewQuestion()) return false;
		//post step1
		var sEmail = $.trim($('#email').val());
		var sUname = $.trim($('#uname').val());
		var sPass = $.trim($('#pass').val());
		var sCode = $.trim($('#code').val());
		var sTrueName = $.trim($('#true_name').val());
		var sIdc = $.trim($('#idc').val());
		var sContact = $.trim($('#contact').val());
		var sAddress = $.trim($('#address').val());
		//var sQuestion = $.trim($('#question').val());
		//var sAnswer = $.trim($('#answer').val());
		var sSec =  $.trim($('#tnumber').val());//hex_sha1(sEmail + sUname + sPass);
		var sPid = $('#pid').val();
		//var newQuestion = $.trim($("#newQuestion").val());
		$('#submit').attr('disabled', true);
		jQuery.post('sys/register',
		{'user.detailInfor.email':sEmail, 'user.username':sUname, 'user.password':sPass, 'user.detailInfor.cellphoneNumber':sCode, 'user.detailInfor.telephoneNumber':sSec,'user.detailInfor.name':sTrueName,'user.detailInfor.comments':sIdc,'user.detailInfor.QQ':sContact,'user.detailInfor.address':sAddress,'user.detailInfor.gender':sPid},
		function(sData){
			var $tipCode = $('#tip_code');
			var aItem = sData.split("\n");
			sValue = aItem[0];
			$('#submit').attr('disabled', false);
			if(sValue == -1){
				$tipCode.html(gWrong + '验证码输入有误，请重输');
				tipWrong('tip_code');
				reCode();
			}
			else if(sValue == 1){
				$tipCode.html('');
				var sAddUrl = '';
				sBkurl = $('#bkurl').val();
				if(sBkurl != '') {
					sAddUrl = '&backurl=' + sBkurl;
					window.location.href = '?_c=register&_a=new-account-next' + sAddUrl;
					return;
				}
				window.location.href = '?_c=register&_a=new-account-next' + sAddUrl;
			}
			else if(sValue == -20){
				tipRight('tip_uname');
				$('#tip_uname').html(gWrong + '此用户名已被占用');
			}
			else if(sValue == -21){
				tipRight('tip_email');
				$('#tip_email').html(gWrong + '此邮箱已被占用');
			}else if(sValue == -111){
				$('#tip_answer').html(gWrong + '答案不能超过50个字');
			}else if(sValue == -101){
				$('#tip_question').html(gWrong + '问题选择有误，请重新选择');
			}else if(sValue == -2){
				//这里是问题没有保存好的错误。
			}else if(sValue== 1201){
				$('#tip_newQuestion').html(gWrong + '自定义问题不能超过50个字符');
			}else{
				alert('连接超时，请您稍后再试');
			}
		});
		return true;
	}

}
function checkIagree(){
	error = 0;
	if($('#iagree').attr('checked') != true){
		
		error = 1;
	}
	return error;
}
function checkCode(){
	var sCode = $.trim($('#code').val());
	var sId = 'tip_code';
	var $tipsCode = $('#tip_code');
	var error = 0;
	var pattern = /^1[34589]\d{9}$/;
	var sTip = '';

	if(sCode == ''){
		error = 1;
		sTip = '请输入手机号码';
	}
	else if(!pattern.test(sCode)){
		error = 2;
		sTip = '手机号码错误，请重新输入';
	}
	else
	{
		sTip = gRight;
		tipRight(sId);
	}
	if(error){
		sTip = gWrong + sTip;
		tipWrong('tip_code');
	}
	
	$('#' + sId).html(sTip);
	return error;
}
function reCode(){
	$('#pcode').attr('src', './captcha/code/index.php?t=' + new Date().getTime());
}
function passLevel(){
	var sP = $('#pass').val();
	var iLength = sP.length;
	var iLevel = 0;
	if(sP.match(/[0-9]+/))
		iLevel++;
	if(sP.match(/[a-z]+/))
		iLevel++;
	if(sP.match(/[A-Z]+/))
		iLevel++;
	if(sP.match(/[^a-z0-9]+/i))
		iLevel++;
	//if(sP && iLength < 4)
	//	iLevel = 1;
	$('.c-visible').css({'visibility':'visible'});
	$('#tip_pass').html('区分大小写。6-20位字符，不能使用空格、用户名');
	$('#pass_level_bar').attr('class', 'bg c-plv-' + iLevel);
}
function checkCpass(){
	var $tipCpass = $('#tip_cpass');
	var sPass = $('#pass').val();
	var error = 0;
	var sTip;
	var sId = 'tip_cpass';
	if(sPass != $('#cpass').val()){
		error = 1;
		if($('#cpass').val() == ''){
			sTip = gWrong + '请输入确认密码';
		}
		else{
			sTip = gWrong + '两次输入的密码不一致，请检查';
		}
		tipWrong(sId);
	}
	else if(sPass == ''){
		sTip = '';
	}
	else{
		sTip = gRight;
		tipRight(sId);
	}
	$tipCpass.html(sTip);
	return error;
}
function checkPass(){
	var $pass = $('#pass');
	var pass = $('#pass').val();
	var $tipPass = $('#tip_pass');
	var error = 0;
	var ok = 1;
	var sTip = '';
	if(pass == '') {
		error = 1;
		$('.c-visible:first').css({'visibility':'visible'});
		sTip = gWrong + '请输入密码';
		tipWrong('tip_pass');
		$tipPass.html(sTip);
		return error;
	}
	if(pass.indexOf(' ') != -1){
		error = 1;
		$('.c-visible:first').css({'visibility':'visible'});
		sTip = gWrong + '密码中不能包含空格';
		tipWrong('tip_pass');
		$tipPass.html(sTip);
		return error;
	}
	//uname != pass
	var sUname = $.trim($('#uname').val());
	if(sUname != '' && sUname == pass){
		error = 3;
		$('.c-visible:first').css({'visibility':'visible'});
		sTip = gWrong + '密码不能和用户名相同';
		tipWrong('tip_pass');
		$tipPass.html(sTip);
		return error;
	}

	var passLength = pass.length;
	for(var i = 0; i < passLength; i++){
		if(pass.charCodeAt(i) < 33 || pass.charCodeAt(i) > 125) ok = 0;
	}
	if(!ok || passLength < 6 || passLength > 20){
		error = 2;
		$('.c-visible').css({'visibility':'visible'});
	}
	if(ok == 0){
		error = 4;
		sTip = gWrong + '请输入6-20位字符，不能使用空格、用户名';
		tipWrong('tip_pass');
		$tipPass.html(sTip);
	}
	if(!error) {
		tipRight('tip_pass');
		$tipPass.html(gRight);
		$('.c-visible:last').css({'visibility':'hidden'});
	}
	return error;
}
function checkUname(){
	var $uname = $('#uname');
	var uname = $.trim($uname.val());
	var $tipUname = $('#tip_uname');
	var pattern = /^[a-z0-9\-_\u4E00-\u9FA5]{2,24}$/i;
	var error = 0;
	var sTip = '';
	if(uname == ''){
		error = 1;
		sTip = '请输入用户名';
	}
	else if(uname.length < 3){
		error = 2;
		sTip = '用户名太短，应为3-20个字符';
	}
	else if(!pattern.test(uname)){
		error = 3;
		sTip = '3-20个中文、英文、数字、-、_组成，不允许以数字为开头，注册后不可修改';
	}
	else if(uname.match(/^\d/)){
		error = 4;
		sTip = '用户名不能以数字开头';
	}
	else{
		if(arguments[0] == 1) return 0;
		if(gInputUname == uname){
			if(gProStatus == 1) return 0;
			return 1;
		}
		jQuery.post('sys/user_checkUserName',
		{'u':uname},
	
		function(sData){
			alert("1"+sData);
//			if(sData == 1){
				sTip = gRight;
				tipRight('tip_uname');
				gProStatus = 1;
//			}
//			else{
//				sTip = gWrong + '此用户名已被占用，请换一个，或 <a href="sys/user_logout">登录</a>';
//				tipWrong('tip_uname');
//				gProStatus = 2;
//			}
			$tipUname.html(sTip);
			gInputUname = uname;
		});
	}
	if(error) {
		sTip = gWrong + sTip;
		tipWrong('tip_uname');
	}

	$tipUname.html(sTip);
	return error;

}
function checkEmail(){
	var $email = $('#email');
	var email = $.trim($email.val());
	var $tipEmail = $('#tip_email');
	var error = 0;
	var pattern = /^[a-z0-9][a-z0-9_\-\.]*[a-z0-9]*\@[a-z0-9_\-]+(\.[a-z0-9]{2,5}){1,}$/i;
	var sTip = '';

	if(email == ''){
		error = 1;
		sTip = '请输入您的常用邮箱地址，它将可以用来找回密码';
	}
	else if(!pattern.test(email)){
		error = 2;
		sTip = '邮箱格式错误，请重新输入';
	}
	else{
		sTip = gRight;
		tipRight('tip_email');
		gProStatus2 = 1;
		$tipEmail.html(sTip);
		gInputEmail = email;
//		if(arguments[0] == 1) return 0;
//		if(gInputEmail == email){
//			if(gProStatus2 == 1) return 0;
//			return 1;
//		}
//		jQuery.get('?_c=register&_a=check-email',{'e':email}, function(sData){
//			if(sData == 1){
//				sTip = gRight;
//				tipRight('tip_email');
//				gProStatus2 = 1;
//			}
//			else{
//				enEmail = encodeURIComponent(email);
//				sTip2 = '<a href="/account/forgot-password?email='+enEmail+'" target="_blank" style="color:#004276">试试找回密码</a>';
//				sTip = gWrong + '该邮箱地址已被注册，请换一个，或<a href="?_c=index&_a=user-login" style="color:#004276">登录</a> ' + sTip2;
//				tipWrong('tip_email');
//				gProStatus2 = 2;
//			}
//			$tipEmail.html(sTip);
//			gInputEmail = email;
//		});
	}
	if(error){
		sTip = gWrong + sTip;
		tipWrong('tip_email');
	}
	$tipEmail.html(sTip);
	//if(error) $email.focus();
	return error;
}


function showCodeTip(){
	if($(this).val() == ''){
		$('#tip_code').html(gCue+gTipCodeOn);
	}
}
function showCodeTipBlur(){
	if($(this).val() == ''){
		$('#tip_code').html(gWrong+'请输入手机号码');
	}
	else checkCode();
}
function showAddressTip(){
	if($(this).val() == ''){
		$('#tip_address').html(gCue+gTipAddressOn);
	}
}
function showAddressTipBlur(){
	if($(this).val() == ''){
		$('#tip_address').html('');
	}
	else checkAddress();
}
function showContactTip(){
	if($(this).val() == ''){
		$('#tip_contact').html(gCue+gTipContactOn);
	}
}
function showContactTipBlur(){
	if($(this).val() == ''){
		$('#tip_contact').html('');
	}
	else checkContact();
}
function showIdcTip(){
	if($(this).val() == ''){
		$('#tip_idc').html(gCue+gTipIdcOn);
	}
}
function showIdcTipBlur(){
	if($(this).val() == ''){
		$('#tip_idc').html('');
	}
	else checkIdc();
}
function showTrueNameTip(){
	if($(this).val() == ''){
		$('#tip_true_name').html(gCue+gTipTrueNameOn);
	}
}
function showTrueNameTipBlur(){
	if($.trim($(this).val()) == ''){
		$('#tip_true_name').html('');
	}
	else checkTrueName();
}
function showCpassTipBlur(){
	if($('#pass').val() != '' && $(this).val() == '') $('#tip_cpass').html(gWrong+'请输入确认密码！');
	else checkCpass();
}
function showCpassTip(){
	if($('#cpass').val() == '' && $('#pass').val() != ''){
		$('#tip_cpass').html(gCue+'请输入确认密码');
	}
}
function showPassTip(){
	if($('#pass').val() == ''){
		$('.c-visible').css({'visibility':'visible'});
		$('#tip_pass').html(gCue+'6-20位字符，不能使用空格、用户名');
	}
}
function showPassTipBlur(){
	if($(this).val() == '') {
		$('.c-visible').css({'visibility':'hidden'});
		$('#tip_pass').html(gWrong+'请输入密码');
	}
	else{
		var tmp =$(this).val();
		if(tmp.length<6){
			$('#tip_pass').html(gWrong+'密码太短，应为6-20个字符');
		}
		if(tmp.length >20){
			$('#tip_pass').html(gWrong+'6-20位字符，区分大小写，不能与用户名相同');
		}
		checkPass();
	}
}
function showEmailTip(){
	if($('#email').val() == ''){
		var sTip = '请输入您的常用邮箱地址，也可用来找回密码';
		$('#tip_email').html(gCue+sTip);
	}
}
function showEmailTipBlur(){
	if($(this).val() == '') $('#tip_email').html(gWrong+'请输入邮箱地址');
	else checkEmail();
}
function showUnameTip(){
	var uname = $.trim($('#uname').val());
	var $tipUname = $('#tip_uname');
	if(uname == ''){
		$tipUname.html(gCue+'3-20个中文、英文、数字、-、_组成，不允许以数字为开头，注册后不可修改');
	}
}
function showUnameTipBlur(){
	if($(this).val() == '') $('#tip_uname').html(gWrong+'请输入用户名');
	else checkUname();
}
function tipRight(id){
	$('#' + id).css({'background-image':'none'});
}
function tipWrong(id){
	$('#' + id).css({'background-image':'none'});
}
//新添加密码保护问题验证
function checkQuestion(){
	var $question = $("#question");
	var $tipQuestion = $('#tip_question');
	if(0 == parseInt($question.val())){
		var sTip = '请选择密码保护问题';
		$tipQuestion.html(gWrong+sTip);
		return 1;
	}
	return 0;
}
function checkNewQuestion(){
	if(1201 == parseInt($("#question").val())){
		var newQuestion = $("#newQuestion").val();
		var $tipsQuestion = $('#tip_newQuestion');
		if(newQuestion != ''){
		if(newQuestion.length >49){
			var sTip = '问题不能超过50个字符';
			$tipsQuestion.html(gCue+sTip);
			return 1;
		}
		if(newQuestion.length <2){
			var sTip = '自定义问题太短，至少2个字符';
			$tipsQuestion.html(gCue+sTip);
			return 1;
		}
		}else{
			$tipsQuestion.html(gCue+'请输入自定义问题');
		}
	}
	return 0;
}
function checkAnswer(){
	var $answer = $("#answer");
	var $tipAnswer = $('#tip_answer');
	var answer = $answer.val();
	if('' == $.trim($answer.val()) || answer.length < 2){
		var sTip = '请输入答案';
		$tipAnswer.html(gWrong+sTip);
		return 1;
	}
	return 0;
}
function showQuestionTip(){
	if(0 ==parseInt($("#question").val())){
		var sTip = '请选择密码保护问题，可用来找回密码';
		var $tipQuestion = $('#tip_question');
		$tipQuestion.html(gCue+sTip);
	}
}
function showNewQuestionTip(){
	
	if($('#newQuestion').val() == ''){
		var sTip = '请输入自定义问题，至少2个字符';
		$('#tip_newQuestion').html(gCue+sTip);
	}else{
		$('#tip_newQuestion').html('');
	}
}
function showQuestionTipBlur(){
	var $tipQuestion = $('#tip_question');
	if(0 != parseInt($("#question").val())){
		$tipQuestion.html('');
	}else{
		if(0 ==parseInt($("#question").val())){
			var sTip = '请选择密码保护问题';
			var $tipQuestion = $('#tip_question');
			$tipQuestion.html(gWrong+sTip);
		}
		
	};
}
function showAnswerTip(){
	if('' == $.trim($("#answer").val())){
		var sTip = '请输入答案，至少2个字符';
		var $tipAnswer = $('#tip_answer');
		$tipAnswer.html(gCue+sTip);
	}
}
function showAnswerTipBlur(){
	var $tipAnswer = $('#tip_answer');
	if('' != $.trim($("#answer").val())){
		if($("#answer").val().length > 50){
			$tipAnswer.html(gWrong+"答案不能超过50个字符");
		}else if($("#answer").val().length<2){
			$tipAnswer.html(gWrong+"答案太短，至少 2个字符");
		}else{
			$tipAnswer.html(gRight);
		}
		
	}else{
		if('' == $.trim($("#answer").val())){
			var sTip = '请输入答案';
			var $tipAnswer = $('#tip_answer');
			$tipAnswer.html(gWrong+sTip);
		}
		
	};
}
/*function tipNormal(id){
	$('#' + id).css({ background: "url('/resource/images/icon_03.gif') no-repeat left 6px"});
}*/
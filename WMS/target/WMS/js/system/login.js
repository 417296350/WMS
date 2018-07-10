// 文档加载完毕后调用
$(function () {

	// 点击登录-把用户信息表单提交到服务器
	$("#login_sub").click(function () {
		$("#submitForm").submit();
    })

	// 点击键盘enter-把用户信息表单提交服务器
	$("#pwd").keypress(enterLogin);

})

// 点击键盘enter登录
function enterLogin(e) {
    var enter = e || window.event;
    if ( e.keyCode == 13 ){
        $("#submitForm").submit();
    }
}
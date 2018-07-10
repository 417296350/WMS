// input表单校验
$(function () {

    if ($("#editForm").length==0) return;

    $("#editForm").validate({
        rules: {
            "employee.name": {
                required:true,
                rangelength:[5,8]
            },
            "employee.password": {
                required:true,
                minlength:4
            },
            "employee.repassword": {
                equalTo:"#password"
            },
        },
        messages: {
            "employee.name": {
                required:"必填想啥呢，赶紧的傻逼",
                rangelength:"字符串长度在{0}到{1}"
            },
            "employee.password": {
                required:"必填想啥呢，赶紧的傻逼",
                minlength:"长度不能少于4位"
            },
            "employee.repassword": {
                equalTo:"密码不一致"
            },
        }
    })
})

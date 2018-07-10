// 加载权限
$(function () {
    $(".reload_permission_btn").click(function () {
        var url = $(this).data("url");

        var dialog = $.dialog({
            title: "提示",
            icon: "warning"
        })

        $.dialog({
            title:"提示",
            icon:"warning",
            content:"是否要加载权限，这个操作很耗时",
            cancel:true,
            ok:function () {
                $.get(url,function () {
                    dialog.content("加载权限完成").button({
                        name:"ok",
                        callback:function () {
                            window.location.reload();
                        }
                    })
                })
            }
        })

    })
})
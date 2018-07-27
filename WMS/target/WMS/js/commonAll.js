$.ajaxSettings.traditional = true;


/** table鼠标悬停换色* */
$(function() {
	// 如果鼠标移到行上时，执行函数
	$(".table tr").mouseover(function() {
		$(this).css({background : "#CDDAEB"});
		$(this).children('td').each(function(index, ele){
			$(ele).css({color: "#1D1E21"});
		});
	}).mouseout(function() {
		$(this).css({background : "#FFF"});
		$(this).children('td').each(function(index, ele){
			$(ele).css({color: "#909090"});
		});
	});
});

/** 点击查询|翻页交表单* */
$(function() {

	// 点击查询|翻页交表单
    $(".page_btn").click(function () {

    	// 获取当前要显示的页数
        var page = $(this).data("page") || $(":input[name='query.currentPage']").val();

        if ($(this).val()=="查询"){
            page = 1;
        }

		// 给表单设置当前页数
        $(":input[name='query.currentPage']").val(page);

		// 提交表单
        $("#searchForm").submit();

    })

	// 点击当前显示页数提交表单
    $(":input[name='query.pageSize']").change(function () {
        // 提交表单
        $("#searchForm").submit();
    })

});

/** 跳转* */
$(function () {

	$(".btn_href").click(function () {

		// 获取跳转的URL
		var url = $(this).data("url");

		// 跳转
		window.location.href = url;

    })
})

/** 全选反选操作* */
$(function () {

    $("#all").click(function () {
        $(".acb").prop("checked",this.checked);
    });

})


/** 删除操作* */
$(function () {
    $(".btn_delete").click(function () {

        var self = this;


        $.dialog({
           title:"提示信息",
            content:"确定要删除?",
            icon:"warning",
            cancel:true,
            ok:function () {

                var dialog = $.dialog({
                    title: "提示信息",
                    icon: "warning"
                })

                // 发送请求，删除数据
                var url = $(self).data("url");
                $.get(url,function (resposne) {
                    if (resposne){
                        dialog.content(resposne).button({
                            name:"朕知道了"
                        });
                    }else {
                        dialog.content(resposne).button({
                            name:"朕知道了"
                        });;
                    }
                })
            }
        });

    });
})

/** 批量删除* */
$(function () {
    $(".batch_delete_btn").click(function () {

        if ($(".acb:checked").length <= 0 ){
            $.dialog({
                title:"提示信息",
                content:"没有选中删除目标",
                icon:"warning",
                ok:true
            });
        }

        var ids =  $.map($(".acb:checked"),function (item) {
            console.debug(item);
            return $(item).data("id");
        });

        var url = $(this).data("url");

        $.dialog({
            title:"提示信息",
            content:"确定要删除?",
            icon:"warning",
            cancel:true,
            ok:function () {

                var dialog = $.dialog({
                    title:"提示信息",
                    icon:"face-smile",
                })

                $.get(url,{"ids":ids},function () {

                    dialog.content("删除成功").button({
                        name:"朕知道了",
                        callback:function () {
                            window.location.reload();
                        }
                    });

                });
            }
        });

    });
})


/** select去重* */
$(function () {

    var soucreOptions = $("#soucre option");
    var targetOptions = $("#target option");
    var optionIds = $.map(targetOptions,function (option) {
        return $(option).attr("value");
    })
    $.each(soucreOptions,function (index,item) {
        var sourceId = $(item).attr("value");
        $.each(optionIds,function (index,id) {
            if (sourceId == id){
                $(item).remove();
            }
        })
    })
})

/** select中所选中的option移动* */
$(function () {
    $(".select_move").click(function () {
        var source = $(this).data("source");
        var target = $(this).data("target");
        console.debug(source);
        console.debug($("#" + source + " option:selected"));
        $("#" + target).append($("#" + source + " option:selected"));
    });
})

/** select中所有option移动* */
$(function () {
    $(".all_move").click(function () {
        var source = $(this).data("source");
        var target = $(this).data("target");
        $("#" + target).append($("#" + source + " option"));
    });
})

/** input编辑|保存提交* */
$(function () {
    $(".input_submit").click(function () {
        var targetOptions = $(".target option");
        $.each(targetOptions,function (index,item) {
            $(item).prop("selected",true);
        })

    });
})


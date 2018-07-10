// select去重
$(function () {

    var soucreOptions = $("#soucre_permssion option");
    var targetOptions = $("#target_permssion option");
    console.debug(soucreOptions)
    var optionIds = $.map(targetOptions,function (option) {
        console.debug(option)
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

$(function () {

    var soucreOptions = $("#soucre_menu option");
    var targetOptions = $("#target_menu option");
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


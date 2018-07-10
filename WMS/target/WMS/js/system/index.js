//加载当前日期
function loadDate(){
	var time = new Date();
	var myYear = time.getFullYear();
	var myMonth = time.getMonth() + 1;
	var myDay = time.getDate();
	if (myMonth < 10) {
		myMonth = "0" + myMonth;
	}
	document.getElementById("day_day").innerHTML = myYear + "." + myMonth + "."	+ myDay;
}

/**
 * 隐藏或者显示侧边栏
 * 
 **/
function switchSysBar(flag){
	var side = $('#side');
    var left_menu_cnt = $('#left_menu_cnt');
	if( flag==true ){	// flag==true
		left_menu_cnt.show(500, 'linear');
		side.css({width:'280px'});
		$('#top_nav').css({width:'77%', left:'304px'});
    	$('#main').css({left:'280px'});
	}else{
        if ( left_menu_cnt.is(":visible") ) {
			left_menu_cnt.hide(10, 'linear');
			side.css({width:'60px'});
        	$('#top_nav').css({width:'100%', left:'60px', 'padding-left':'28px'});
        	$('#main').css({left:'60px'});
        	$("#show_hide_btn").find('img').attr('src', '/images/common/nav_show.png');
        } else {
			left_menu_cnt.show(500, 'linear');
			side.css({width:'280px'});
			$('#top_nav').css({width:'77%', left:'304px', 'padding-left':'0px'});
        	$('#main').css({left:'280px'});
        	$("#show_hide_btn").find('img').attr('src', '/images/common/nav_hide.png');
        }
	}
}

//加载侧边栏的zTree数据
$(function () {

	// 0.默认加载侧边栏第一个zTree数据
    loadLeftTable("business");

	// 1.切换侧边栏
    $("#TabPage2 li").click(function () {
    	// 1.修改li的背景颜色
		// 清空所有li的class属性
		$("#TabPage2 li").prop("class","");
		// 设置当前li的class属性值为CSS对应的selected
		$(this).prop("class","selected");

		// 2.修改li中img的图片样式，配合图片命名的规律设置图片
        // 设置所有li的图片为正常木事
		$.each($("#TabPage2 li"),function (index,item) {
			$(item).children("img").prop("src","images/common/" + (index+1) + ".jpg");
        })
		// 获取当前li在所有li中的index索引
		var index = $(this).index();
		var img = $(this).children();
		$(this).children("img").prop("src","images/common/" + (index+1) + "_hover.jpg");

		// 3.加载左侧table的具体内容
		loadLeftTable($(this).data("rootmenu"));
    })
})

var defaultNodes =  {
    "business":{id:"1",name:"业务管理",isParent:true,sn:"business",parentName:"业务"},
    "system":{id:"2",name:"系统管理",isParent:true,sn:"system",parentName:"系统"},
    "chart":{id:"3",name:"报表管理",isParent:true,sn:"chart",parentName:"报表"}
}

// 加载左侧table的具体内容(树形结构)
function loadLeftTable(rootMenu) {

	var setting = {
		data:{simpleData:{enable:true}},
		async:{
			enable:true,
			url:"systemMenu_getChildMenusByParentSn.action",
			autoParam:["sn=query.parentSn"],
			dataFilter:filter
		},
		callback:{
			onClick:function (event,treeId,treeNode) {
				var parentNode = treeNode.getParentNode();
                var parentPName;
				var parentName;
				if ( parentNode ){
                    parentPName = $(parentNode).prop("parentName")
                    parentNode = $(parentNode).prop("name")
				}
				if (treeNode.action) {
                    $("#rightMain").prop("src",treeNode.action + ".action");
                    $("#here_area").html("当前位置：" + parentPName + "&nbsp;>&nbsp;" +parentNode + "&nbsp;>&nbsp;"+ treeNode.name);
				}
            }
		}
	}

	function filter(treeId,parentNode,responseData) {
		return responseData;
    }

    $.fn.zTree.init($("#dleft_tab1"), setting, defaultNodes[rootMenu]);
}

$(function(){
	loadDate();

	// 显示侧边栏
	switchSysBar(true);

	// 显示隐藏侧边栏
	$("#show_hide_btn").click(function() {
        switchSysBar();
    });

});

// 切换右侧1级菜单
$(function () {
	$("#TabPage2 > li").click(function () {
		var rootmenu = $(this).data("rootmenu");
		console.debug(rootmenu);
        $("#here_area").html("当前位置：" + defaultNodes[rootmenu]["parentName"] + "&nbsp;>&nbsp;" + defaultNodes[rootmenu]["name"]);
    })
})
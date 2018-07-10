<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="ui_tb_h30">
    <div class="ui_flt" style="height: 30px; line-height: 30px;">
        共有
        <span class="ui_txt_bold04"><s:property value="#pageResult.dataTotalCount"/></span>
        条记录，当前第
        <span class="ui_txt_bold04">
            <s:property value="#pageResult.currentPage"/>/<s:property value="#pageResult.pageTotalCount"/>
        </span>
        页
    </div>
    <div class="ui_frt">
        <input type="button" value="首页" class="ui_input_btn01 page_btn" data-page="1"/>
        <input type="button" value="上一页" class="ui_input_btn01 page_btn" data-page="${pageResult.lastPage}"/>
        <input type="button" value="下一页" class="ui_input_btn01 page_btn" data-page="${pageResult.nextPage}"/>
        <input type="button" value="尾页" class="ui_input_btn01 page_btn" data-page="${pageResult.pageTotalCount}"/>

        <s:select class="ui_select02" list="{5,10,50}" name="query.pageSize" />
        转到第<s:textfield class="ui_input_txt01" name="query.currentPage"/>页
        <input type="button" class="ui_input_btn01  page_btn" value="跳转"/>
    </div>
</div>

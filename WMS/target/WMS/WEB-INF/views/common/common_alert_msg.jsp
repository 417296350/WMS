<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=default"></script>

<script type="text/javascript">
    <%--判断是否有错误信息，有则显示错误信息--%>
    <s:if test="hasActionErrors()">
    var msg = '<s:property value="actionErrors[0]"/>';
    $.dialog({
        title:"提示信息",
        content:msg,
        icon:"face-sad",
        ok:true

    });
    </s:if>
    <%--判断是否有消息信息，有则显示错误信息--%>
    <s:if test="hasActionMessages()">
    var msg = '<s:property value="actionMessages[0]"/>';
    $.dialog({
        title:"提示信息",
        content:msg,
        icon:"face-smile",
        ok:true
    });
    </s:if>
</script>

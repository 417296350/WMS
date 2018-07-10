<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%--判断是否有错误信息，有则显示错误信息--%>
<s:if test="hasActionErrors()">
    <s:property value="actionErrors[0]"/>
</s:if>
<%--判断是否有消息信息，有则显示错误信息--%>
<s:if test="hasActionMessages()">
    <s:property value="actionMessages[0]"/>
</s:if>

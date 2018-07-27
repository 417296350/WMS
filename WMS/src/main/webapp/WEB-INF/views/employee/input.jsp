<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<title>信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="/js/plugins/validate/jquery.validate.min.js"></script>
	<script type="text/javascript" src="/js/commonAll.js"></script>
	<script type="text/javascript" src="/js/system/employee.js"></script>
</head>
<body>
<%--<%@ include file="/WEB-INF/views/common/common_show_msg.jsp"%>--%>
<s:form name="editForm" namespace="/" action="employee_saveOrUpdate" method="post" id="editForm">
	<div id="container">
		<div id="nav_links">
			<span style="color: #1A5CC6;">用户编辑</span>
			<div id="page_close">
				<a>
					<img src="images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
				</a>
			</div>
		</div>
		<div class="ui_content">
			<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<s:hidden name="employee.id" />
				<tr>
					<td class="ui_text_rt" width="140">用户名</td>
					<td class="ui_text_lt">
						<s:textfield name="employee.name" class="ui_input_txt02"/>
					</td>
				</tr>
				<s:if test="employee.id == null">
					<tr>
						<td class="ui_text_rt" width="140">密码</td>
						<td class="ui_text_lt">
							<s:textfield name="employee.password" id="password" class="ui_input_txt02"/>
						</td>
					</tr>
					<tr>
						<td class="ui_text_rt" width="140">验证密码</td>
						<td class="ui_text_lt">
							<s:textfield name="employee.repassword" type="password" class="ui_input_txt02" />
						</td>
					</tr>
				</s:if>
				<tr>
					<td class="ui_text_rt" width="140">Email</td>
					<td class="ui_text_lt">
						<s:textfield name="employee.email" class="ui_input_txt02" />
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">年龄</td>
					<td class="ui_text_lt">
						<s:textfield name="employee.age" class="ui_input_txt02" />
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">所属部门</td>
					<td class="ui_text_lt">
						<s:select list="#departments" name="employee.depart.id" listKey="id" listValue="name"></s:select>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">超级管理员</td>
					<td class="ui_text_lt">
						<s:checkbox name="employee.admin" cssClass="ui_checkbox01" />
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">角色</td>
					<td class="ui_text_lt">
						<table>
							<tr>
								<td>
									<s:select id="soucre" list="#roles" listKey="id" listValue="name" multiple="true" class="ui_multiselect01" ></s:select>
								</td>
								<td align="center">
									<input type="button" id="select" value="-->" class="left2right select_move"   data-source="soucre"  data-target="target" /><br/>
									<input type="button" id="selectAll" value="==>" class="left2right all_move"   data-source="soucre"  data-target="target"/><br/>
									<input type="button" id="deselect" value="<--" class="left2right select_move" data-source="target"  data-target="soucre" /><br/>
									<input type="button" id="deselectAll" value="<==" class="left2right all_move" data-source="target"  data-target="soucre"/>
								</td>
								<td>
									<s:select id="target" list="employee.roles" name="employee.roles.id" listKey="id"  listValue="name" multiple="true" class="ui_multiselect01 target"></s:select>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
						&nbsp;<input type="submit" value="确定保存" class="ui_input_btn01 input_submit"/>
						&nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</s:form>
</body>
</html>
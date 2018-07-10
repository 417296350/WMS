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
	<script type="text/javascript" src="/js/system/role.js"></script>
</head>
<body>
<%@ include file="/WEB-INF/views/common/common_show_msg.jsp"%>
<s:form name="editForm" namespace="/" action="role_saveOrUpdate" method="post" id="editForm">
	<div id="container">
		<div id="nav_links">
			<span style="color: #1A5CC6;">角色编辑</span>
			<div id="page_close">
				<a>
					<img src="images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
				</a>
			</div>
		</div>
		<div class="ui_content">
			<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<s:hidden name="role.id"/>
				<tr>
					<td class="ui_text_rt" width="140">名称</td>
					<td class="ui_text_lt">
						<s:textfield name="role.name" class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">编号</td>
					<td class="ui_text_lt">
						<s:textfield name="role.sn"  class="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">权限</td>
					<td class="ui_text_lt">
						<table>
							<tr>
								<td>
									<s:select id="soucre_permssion" list="#permissions" listKey="id" listValue="name" multiple="true" class="ui_multiselect01" ></s:select>
								</td>
								<td align="center">
									<input type="button" id="select" value="-->" class="left2right select_move"   data-source="soucre_permssion"  data-target="target_permssion" /><br/>
									<input type="button" id="selectAll" value="==>" class="left2right all_move"   data-source="soucre_permssion"  data-target="target_permssion"/><br/>
									<input type="button" id="deselect" value="<--" class="left2right select_move" data-source="target_permssion"  data-target="soucre_permssion" /><br/>
									<input type="button" id="deselectAll" value="<==" class="left2right all_move" data-source="target_permssion"  data-target="soucre_permssion"/>
								</td>
								<td>
									<s:select id="target_permssion" list="role.permissions" name="role.permissions.id" listKey="id"  listValue="name" multiple="true" class="ui_multiselect01 target"></s:select>
								</td>
							</tr>
							<tr>
								<td>
									<s:select id="soucre_menu" list="#systemMenus" listKey="id" listValue="name" multiple="true" class="ui_multiselect01" ></s:select>
								</td>
								<td align="center">
									<input type="button"  value="-->" class="left2right select_move"   data-source="soucre_menu"  data-target="target_menu" /><br/>
									<input type="button"  value="==>" class="left2right all_move"   data-source="soucre_menu"  data-target="target_menu"/><br/>
									<input type="button"  value="<--" class="left2right select_move" data-source="target_menu"  data-target="soucre_menu" /><br/>
									<input type="button"  value="<==" class="left2right all_move" data-source="target_menu"  data-target="soucre_menu"/>
								</td>
								<td>
									<s:select id="target_menu" list="role.systemMenus" name="role.systemMenus.id" listKey="id"  listValue="name" multiple="true" class="ui_multiselect01 target"></s:select>
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
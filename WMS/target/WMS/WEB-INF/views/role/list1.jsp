<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
	<link href="/style/common_style.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="/js/jquery/jquery.js"></script>
	<script type="text/javascript" src="/js/commonAll.js"></script>
<title>PSS-部门管理</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/common_alert_msg.jsp"%>
	<s:form id="searchForm" namespace="/" action="role" method="post">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_center">
							姓名
							<s:textfield class="ui_input_txt02" name="query.name"/>
						</div>
						<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 page_btn"/>
							<input type="button" value="新增" class="ui_input_btn01 btn_href" data-url="<s:url namespace="/" action="role_input"/>"/>
							<input type="button" value="删除" class="ui_input_btn01 batch_delete_btn" data-url="<s:url namespace="/" action="role_batchDelete"/>"/>
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>角色</th>
							<th>编号</th>
							<th>权限</th>
							<th></th>
						</tr>
						<tbody>

							<s:iterator value="#pageResult.dataResults">
								<tr>
									<td><input type="checkbox" name="IDCheck" class="acb" data-id="<s:property value="id" />" /></td>
									<td><s:property value="name"/></td>
									<td><s:property value="sn"/></td>
									<td><s:property value="allPermissionName"/></td>
									<td>
										<s:a namespace="/" action="role_input" >
											<s:param name="role.id" value="id"/>编辑
										</s:a>

										<s:url var="deleteUrl" namespace="/" action="role_delete">
											<s:param name="role.id" value="id"/>
										</s:url>
										<a class="btn_delete" href="javascript:;" data-url="<s:property value="#deleteUrl"/>">删除</a>
									</td>
								</tr>
							</s:iterator>

						</tbody>
					</table>
				</div>
				<%--内部有select标签表示当前显示页的数量，需要提交给服务器，所以把翻页放到form中，方便到只要执行submit自然就能把所需内容提交上去--%>
				<%@ include file="/WEB-INF/views/common/common_page.jsp"%>
			</div>
		</div>
	</s:form>
</body>
</html>

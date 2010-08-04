<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><s:text name="Page.UserList.Title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<s:form action="deleteUser" name="deleteUser">


	<tr>
		<td>
		<table border="2px">
			<thead>
				<tr>
					<th></th>
					<th><s:text name="User.Name"/></th>
				</tr>
			</thead>
			<s:iterator value="userList" status="stat" id="tempUser">
				<tr>
					<td><s:checkbox name="userList[%{#stat.index}].userName"
						fieldValue="%{userName}" /></td>
						<td><s:property value="#tempUser.userName" /></td>
				</tr>
			</s:iterator>
		</table>


		</td>
	</tr>

	<tr>
		<td>

		<table>
			<tr><td><s:submit type="button" value="%{getText('User.AddUser')}" onclick="document.forms['createUser'].submit();return false;"/></td>
			<td><s:submit type="button" value="%{getText('User.DeleteUsers')}" /></td>
			</tr>
		</table>
		</td>
	</tr>
</s:form>

<s:form action="createOrEditUser" name="createUser" >
</s:form>

</body>
</html>
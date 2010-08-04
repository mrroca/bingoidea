<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><s:text name="Page.User.Title"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<s:if test="fieldErrors.size>0||actionErrors.size>0">
	<table>
		<tr>
			<td colspan="2"><s:fielderror /></td>

		</tr>
		<tr>
			<td colspan="2"><s:actionerror /></td>
		</tr>
	</table>
</s:if>
<s:form action="submitUser" name="submitUser">

	<table>
		<tr>
			<td><s:text name="User.Name"/></td>
			<td><s:textfield name="user.userName" /></td>
		</tr>
		<tr>
			<td><s:text name="User.Password"/></td>
			<td><s:password name="user.password" /></td>
		</tr>
		<tr>
			<td></td>
			<td align="right"><s:submit type="button" value="%{getText('User.Submit')}" /></td>

		</tr>
	</table>
</s:form>
</body>
</html>
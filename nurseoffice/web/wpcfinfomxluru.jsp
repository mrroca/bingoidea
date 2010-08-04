<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE> New Document </TITLE>
<META NAME="Description" CONTENT="">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
</HEAD>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="Stylesheet" href="css/jquery.autocomplete.css" /> 
<script type="text/javascript" src="script/jquery-1.3.2.js"></script>
<script type="text/javascript" src="script/url.js"></script>
<script type="text/javascript" src="script/jquery.url.js"></script>
<script type="text/javascript" src="script/jquery.jPrintArea.js"></script>
<script type="text/javascript" src="script/wpcfinfomxluru.js"></script>
<script type="text/javascript" src="script/readcard.js"></script>
<script type="text/javascript" src="script/jquery.autocomplete.js"></script>
<script type="text/javascript" src="script/strim.js"></script>
<script language="javascript" type="text/javascript" src="script/dateplugin/WdatePicker.js"></script>
<style>
#result{ 
overflow-y:auto; 
height:350px;
} 
</style>
<BODY>
<table border="0" cellpadding="0" cellspacing="0" width="786" height="424">
	<!-- MSTableType="layout" -->
	<tr>
		<td valign="top" colspan="3" height="61">
		<!-- MSCellType="ContentHead" -->
		<img border="0" src="image/ja_b_01.gif" width="255" height="61"><img border="0" src="image/ja_b_02.gif" width="260" height="61"><img border="0" src="image/ja_b_04.gif" width="269" height="61"></td>
	</tr>
	<tr>
		<td valign="top" width="20">
		<!-- MSCellType="NavBody" -->
		<img border="0" src="image/ja_b_07.gif" width="19" height="100%"></td>
		<td width="740" height="336" valign="top"><div id="result"></div></td>
		<td valign="top" height="336" width="27">
		<!-- MSCellType="NavBody2" -->
		<img border="0" src="image/ja_b_10.gif" width="25" height="100%"></td>
	</tr>
	<tr>
		<td valign="top" colspan="3" height="26">
		<!-- MSCellType="ContentFoot" -->
		<img border="0" src="image/ja_b_11.gif" width="19" height="19"><img border="0" src="image/ja_b_12.gif" width="740" height="19"><img border="0" src="image/ja_b_14.gif" width="25" height="19"></td>
	</tr>
</table>
<div id="other"></div>
<div id="readcardnum">
<object id='readCard' width=0px height=0px classid='CLSID:0D756A0B-7DB9-49EB-99CC-0C0786C2AB53' CODEBASE='<%=basePath%>activex/readcard_ocx.cab#version=1,0,0,0'></object>
</div>
</BODY>
</HTML>

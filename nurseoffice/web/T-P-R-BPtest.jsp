
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title>T-P-R-BPtest.html</title>

		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/style.css">
		<style type="text/css">
.xhx {
	border: 1px;
	border-bottom-style: solid;
	border-top-style: none;
	border-left-style: none;
	border-right-style: none;
}
#table1 {
	border: 1px solid #d6d6d6;
	border-width: 1px 0 0 1px;
	width: 100%;
	border-spacing: 0;
	font-size: 12px;
	border-collapse: collapse;
	text-align: center;
}

#table1 td {
	border: solid #d6d6d6;
	border-width: 0 1px 1px 0;
	padding: 2px;
}
#table2 {
	border: 1px solid #d6d6d6;
	border-width: 1px 0 0 1px;
	width: 100%;
	border-spacing: 0;
	font-size: 12px;
	border-collapse: collapse;
	text-align: center;
}

#table2 td {
	border: solid #d6d6d6;
	border-width: 0 1px 1px 0;
	padding: 2px;
}
</style>
	<script type="text/javascript" src="script/jquery-1.3.2.js"></script>
	<script type="text/javascript" src="script/url.js"></script>
<script type="text/javascript" src="script/jquery.url.js"></script>
<script type="text/javascript" src="script/jquery.jPrintArea.js"></script>
<script type="text/javascript" src="script/strim.js"></script>
<script type="text/javascript" src="script/readcard.js"></script>
<script type="text/javascript" src="script/calculate_age.js"></script>
	<script type="text/javascript" src="script/t-p-r-bptest.js"></script>
	</head>

	<body>
	<table border="0" cellpadding="0" cellspacing="0" width="786"
	height="360" id="imgtable">
	<tr>
		<td valign="top" colspan="3" height="61">
		<img border="0" src="image/ja_b_01.gif" width="255"
			height="61"><img border="0"
			src="image/ja_b_02.gif" width="260" height="61"><img
			border="0" src="image/ja_b_04.gif" width="269"
			height="61"></td>
	</tr>
	<tr>
		<td valign="top" width="20"> <img
			border="0" src="image/ja_b_07.gif" width="19"
			height="100%"></td>
		<td width="740" height="273" valign="top">
		<center><table id='table1' >
					<tr bgcolor=#C5E1EF><td colspan=6 style='height:30px;font-weight:bold;font-size:14px' align='center'>居民基本信息</td></tr>
					
					<tr><td bgcolor='#cccccc'><b>门诊号码</b></td><td width=24%><input id='mzhm' name='mzhm' readOnly type='text' size='20' value='' style='width:80%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'><a href='#' onClick='readcard()'>读卡</a></td>
					<td bgcolor='#cccccc'><b>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 名</b></td><td width=24%><input id='brxm' name='brxm'  type='text' size='20' value='' style='width:100%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>
					<td bgcolor='#cccccc'><b>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</b></td><td width=24%><SELECT name="brxb" id="brxb" style='position:relative;float:left;clear:right;left:-2px;top:-2px;font-size:11px;width:103px;line-height:14px;border:0px;color:#00000;'>
						<OPTION value=""></OPTION>
						<OPTION value="0">
							未知性别
						</OPTION>
						<OPTION value="1">
							男
						</OPTION>
						<OPTION value="2">
							女
						</OPTION>
						<OPTION value="9">
							未说明性别
						</OPTION>
					</SELECT></td></tr>
					
					
					<tr><td bgcolor='#cccccc'><b>家庭地址</b></td><td width=24%><input id='jtdz' name='jtdz' type='text' size='20' value='' style='width:100%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>
					<td bgcolor='#cccccc'><b>联系电话</b></td><td width=24%><input id='lxdh' name='lxdh' type='text' size='20' value='' style='width:100%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>
					<td bgcolor='#cccccc'><b>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄</b></td><td width=24%><input id='brnl' name='brnl' type='text' size='20' value='' style='width:80%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td></tr>
					<tr><td bgcolor='#cccccc'><b>过&nbsp;&nbsp;敏&nbsp;&nbsp;史</b></td><td width=24%><SELECT name="gms" id="gms" style='position:relative;float:left;clear:right;left:-2px;top:-2px;font-size:11px;width:103px;line-height:14px;border:0px;color:#00000;'>
						<OPTION value=""></OPTION>
						<OPTION value="01">
							有
						</OPTION>
						<OPTION value="02">
							无
						</OPTION>
						<OPTION value="98">
							不详
						</OPTION>
					</SELECT></td>
					<td bgcolor='#cccccc'><b>过敏物质</b></td><td width=24%><input id='gmwz' name='gmwz' type='text' size='20' value='' style='width:100%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>
					<td bgcolor='#cccccc'><b>婚&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;姻</b></td><td width=24%><div id='hybox'><div id='hylabel'><SELECT name="hy" id="hy" style='position:relative;float:left;clear:right;left:-2px;top:-2px;font-size:11px;width:103px;line-height:14px;border:0px;color:#00000;'>
						<OPTION value=""></OPTION>
						<OPTION value="10">
							未婚
						</OPTION>
						<OPTION value="20">
							已婚
						</OPTION>
						<OPTION value="21">
							初婚
						</OPTION>
						<OPTION value="22">
							再婚
						</OPTION>
						<OPTION value="23">
							复婚
						</OPTION>
						<OPTION value="30">
							丧偶
						</OPTION>
						<OPTION value="40">
							离婚
						</OPTION>
						<OPTION value="90">
							未说明的婚姻状况
						</OPTION>
					</SELECT></div></div></td></tr>
					<tr>
					<td bgcolor='#cccccc'><b>血&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型</b></td><td width=24%><SELECT name="xx" id="xx" style='position:relative;float:left;clear:right;left:-2px;top:-2px;font-size:11px;width:103px;line-height:14px;border:0px;color:#00000;'>
						<OPTION value=""></OPTION>
						<OPTION value="1">
							A型
						</OPTION>
						<OPTION value="2">
							B型
						</OPTION>
						<OPTION value="3">
							AB型
						</OPTION>
						<OPTION value="4">
							O型
						</OPTION>
						<OPTION value="5">
							RH型
						</OPTION>
						<OPTION value="9">
							不详
						</OPTION>
					</SELECT></td>
					<td bgcolor='#cccccc'><b>门诊诊断</b></td><td width=24% ><input id='mzzd' name='mzzd' type='text' size='20' value='' style='width:80%;border-bottom-style: none;border-top-style: none;border-right-style: none;border-left-style: none;background-color: transparent;'></td>
					<td bgcolor='#cccccc'><b>病人性质</b></td><td width=24% ><SELECT name="brxz" id="brxz" style='position:relative;float:left;clear:right;left:-2px;top:-2px;font-size:11px;width:103px;line-height:14px;border:0px;color:#00000;'>
						<OPTION value=""></OPTION>
						<OPTION value="1000">
							自费
						</OPTION>
						<OPTION value="6000">
							园区医保
						</OPTION>
						<OPTION value="6021">
							吴中医保
						</OPTION>
						<OPTION value="7000">
							医疗保险
						</OPTION>
						<OPTION value="8001">
							苏州离休
						</OPTION>
						<OPTION value="8002">
							相城医保
						</OPTION>
					</SELECT></td>
					</tr>
</table>
		<br><br>
		<table id="table2">
			<tr bgcolor=#C5E1EF><td colspan=4 style='height:30px;font-weight:bold;font-size:14px' align='center'>操作信息录入</td></tr>
			<tr>
				<td   bgcolor='#cccccc'>
					<b>体温（T）</b>
				</td>
				<td align="left" width="200">
					<input id="tw" name="tw" value="" size="5" class="xhx">℃
				</td>
				<td align='center' bgcolor='#cccccc'> 
					<b>脉&nbsp;&nbsp;搏（P）</b>
				</td>
				<td align="left" width="200">
					<input id="mb" name="mb" value="" size="5" class="xhx">次/分
				</td>
				
			</tr>
			
			<tr>
				<td  align='center' bgcolor='#cccccc'>
					<b>呼吸（R）</b>
				</td>
				<td align="left" width="200">
					<input id="hx" name="hx" value="" size="5" class="xhx">次/分
				</td>
				<td width='15%'  align='center' bgcolor='#cccccc'>
					<b>血压（BP）</b>
				</td>
				<td align="left" width="200">
					<input id="ssy" name="ssy" value="" size="5" class="xhx">
			/
			<input id="szy" name="szy" value="" size="5" class="xhx">mmHg
				</td>
			</tr>
			
			<tr>
			<td  align='center' bgcolor='#cccccc' >
									<b>是否与类似症状人员接触</b>
								</td>
								
								<td align="left" >
									<SELECT name="sflsry" id="sflsry" 
										style='position: relative; float: left; clear: right; left: -2px; top: -2px; font-size: 11px; width: 103px; line-height: 14px; border: 0px; color: #00000;'>
										<OPTION value=""></OPTION>
										<OPTION value="0">
											是
										</OPTION>
										<OPTION value="1">
											否
										</OPTION>
									</SELECT>
								</td>
								
								<td  align='center' bgcolor='#cccccc' >
									<b>是否与疫区人员接触</b>
								</td>
								
								<td align="left" >
									<SELECT name="sfyqry" id="sfyqry" 
										style='position: relative; float: left; clear: right; left: -2px; top: -2px; font-size: 11px; width: 103px; line-height: 14px; border: 0px; color: #00000;'>
										<OPTION value=""></OPTION>
										<OPTION value="0">
											是
										</OPTION>
										<OPTION value="1">
											否
										</OPTION>
									</SELECT>
								</td>
			</tr>
			
			
			<tr>
			<td width='15%' align='center' bgcolor='#cccccc'><b>处&nbsp;&nbsp;理&nbsp;&nbsp;人</b></td>
				<td colspan="2" align="left">
						<div id='clr2'></div>
				</td>
				<td  align="left"></td>
			</tr>
		</table></center>
		</td>
		<td valign="top" height="273" width="27">
		<img border="0" src="image/ja_b_10.gif" width="25"
			height="100%"></td>
	</tr>
	<tr>
		<td valign="top" colspan="3" height="26">
		<img border="0" src="image/ja_b_11.gif" width="19"
			height="19"><img border="0"
			src="image/ja_b_12.gif" width="740" height="19"><img
			border="0" src="image/ja_b_14.gif" width="25"
			height="19"></td>
	</tr>
</table>
		<center><div >
		<input id="appid" name="appid" value="" type="hidden">
		<input id="sbxh" name="sbxh" value="" type="hidden">
		<input  id="add" name="add" type="button" value="新增" style="width: 102px; height: 32px" onclick="saveTprbp2()">
					<input id="save" name="save" type="button" value="保存" style="width: 102px; height: 32px" onclick="saveTprbp()"></div></center>
		
		<div id="readcardnum">
<object id='readCard' width=0px height=0px classid='CLSID:0D756A0B-7DB9-49EB-99CC-0C0786C2AB53' CODEBASE='<%=basePath%>activex/readcard_ocx.cab#version=1,0,0,0'></object>
</div>
	</body>
</html>

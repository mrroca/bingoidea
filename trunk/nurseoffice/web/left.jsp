<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title></title>
</head>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<style type="text/css">
a {
	text-decoration: none;
	color: black;
	fason: expression(window.status = '');
}
a:link {
	text-decoration: none;
	color: black;
	fason: expression(window.status = '');
}  /* 未访问的链接 */
a:visited {
	text-decoration: none;
	color: black;
	fason: expression(window.status = '');
}  /* 已访问的链接 */
a:hover {
	text-decoration: none;
	color: black;
	fason: expression(window.status = '');
}  /* 当有鼠标悬停在链接上 */
a:active {
	text-decoration: none;
	color: black;
	fason: expression(window.status = '');
}
div,ul,li,p,h1,h2 {
	margin: 0;
	padding: 0;
	border: 0;
	background: #FFFFFF;
	font-family: Arial, Helvetica, sans-serif, "宋体"
}

body {
	margin: 0;
	padding: 0;
	border: 0;
	background: #FFFFFF;
	font-family: Arial, Helvetica, sans-serif, "宋体";
	text-align: left;
	font-size: 12px
}

li {
	list-style: none
}

.rolinList {
	width: 128px;
	height: auto;
	margin: 20px auto 0 auto;
	text-align: left
}

.rolinList li {
	margin-bottom: 1px;
	border: 1px solid #DADADA
}

.rolinList li h2 {
	width: 126px;
	height: 30px;
	background: #C5E1EF;
	font-size: 14px;
	line-height: 30px;
	padding-left: 0px;
	color: #333;
	cursor: pointer
}

.content {
	text-align: center;
	height: 320px;
	padding-bottom: 2px;
	width: 126px;
	background: #FFFFFF
}

.content p {
	text-align: center;
	margin: 2px;
}
-->
</style>
<script type="text/javascript">
	//<![CDATA[
	window.onload = function() {
		rolinTab("rolin")
	}
	function rolinTab(obj) {
		var list = $(obj).getElementsByTagName("LI");
		var state = {
			show : false,
			hidden : false,
			showObj : false
		};
		for ( var i = 0; i < list.length; i++) {
			var tmp = new rolinItem(list[i], state);
			if (i == 0)
				tmp.pShow();
		}
	}
	function rolinItem(obj, state) {
		var speed = 0.7;
		var range = 1;
		var interval;
		var tarH;
		var tar = this;
		var head = getFirstChild(obj);
		var content = getNextChild(head);
		var isOpen = false;
		this.pHidden = function() {
			if (isOpen)
				hidden();
		}
		this.pShow = show;
		var baseH = content.offsetHeight;
		content.style.display = "none";
		var isOpen = false;
		head.onmouseover = function() {
			this.style.background = "#99CCFF";
		}
		head.onmouseout = mouseout;
		head.onclick = function() {
			this.style.background = "#99CCFF";
			if (!state.show && !state.hidden) {
				if (!isOpen) {
					head.onmouseout = null;
					show();
				} else {
					hidden();
				}
			}
		}
		function mouseout() {
			this.style.background = "#C5E1EF"
		}
		function show() {
			head.style.borderBottom = "1px solid #DADADA";
			state.show = true;
			if (state.openObj && state.openObj != tar) {
				state.openObj.pHidden();
			}
			content.style.height = "0px";
			content.style.display = "block";
			content.style.overflow = "hidden";
			state.openObj = tar;
			tarH = baseH;
			interval = setInterval(move, 10);
		}
		function showS() {
			isOpen = true;
			state.show = false;
		}
		function hidden() {
			state.hidden = true;
			tarH = 0;
			interval = setInterval(move, 10);
		}
		function hiddenS() {
			head.style.borderBottom = "none";
			head.onmouseout = mouseout;
			head.onmouseout();
			content.style.display = "none";
			isOpen = false;
			state.hidden = false;
		}
		function move() {
			var dist = (tarH - content.style.height.pxToNum()) * speed;
			if (Math.abs(dist) < 1)
				dist = dist > 0 ? 1 : -1;
			content.style.height = (content.style.height.pxToNum() + dist)
					+ "px";
			if (Math.abs(content.style.height.pxToNum() - tarH) <= range) {
				clearInterval(interval);
				content.style.height = tarH + "px";
				if (tarH != 0) {
					showS()
				} else {
					hiddenS();
				}
			}
		}
	}
	var $ = function($) {
		return document.getElementById($)
	};
	String.prototype.pxToNum = function() {
		return Number(this.replace("px", ""))
	}
	function getFirstChild(obj) {
		var result = obj.firstChild;
		while (!result.tagName) {
			result = result.nextSibling;
		}
		return result;
	}
	function getNextChild(obj) {
		var result = obj.nextSibling;
		while (!result.tagName) {
			result = result.nextSibling;
		}
		return result;
	}
	//]]>
</script>
<body>
<!--<div id="lefttitle" style="text-align:center">门诊护士工作</div>-->
<table border="0" cellpadding="0" cellspacing="0" width="161"
	height="100%">
	<!-- MSTableType="layout" -->

	<tr>
		<td width="128" valign='top'>
		<table border="0" cellpadding="0" cellspacing="0" width="148">
	<!-- MSTableType="layout" -->
	<tr>
	<td valign="bottom" colspan="3" height="20"><img border="0" src="image/menutop.png" width=148></td>
	</tr>
	<tr>
		<td valign="top" width="5" height="430px" style='padding-top:0px'>
		<!-- MSCellType="NavBody" -->
		<img border="0" src="image/menuleft.png" width="5" height="450px"></td>
		<td width="128" valign='top' height="430px" style='padding-top:0px'>
		<div id='leftlist'>
		<ul class="rolinList" id="rolin">
			<li>
			<h2 align=center>门诊工作</h2>
			<div class="content">
			<p><a href="patientlist.html" onfocus="javascript:this.blur();"
				target="mainFrame"><img style="CURSOR: hand" onclick=""
				src='image/zcsy.ico'><br> 注射/输液</a></p>
			<p><a href="clinicservice.html" onfocus="javascript:this.blur();"
				target="mainFrame"><img style="CURSOR: hand" onclick=""
				src='image/mzgcjl.ico'><br> 门诊观察记录</a></p>
			<p><a href="tprbplist.html" onfocus="javascript:this.blur();"
				target="mainFrame"><img style="CURSOR: hand" onclick=""
				src='image/TPRBP.ico'><br> T/P/R/BP测量</a></p>

			</div>
			</li>
			<li>
			<h2 align=center>其他工作</h2>
			<div class="content">
			<p><a href="visitservicelist.html"
				onfocus="javascript:this.blur();" target="mainFrame"><img
				style="CURSOR: hand" onclick="" src='image/smsf.ico'><br>
			上门服务/巡诊</a></p>
			<p><a href="treatment.html" onfocus="javascript:this.blur();"
				target="mainFrame"><img style="CURSOR: hand" onclick=""
				src='image/hyskcl.ico'"><br> 伤口处理/换药</a></p>
			<p><a href="blood.html" onfocus="javascript:this.blur();"
				target="mainFrame"><img style="CURSOR: hand" onclick=""
				src='image/cx.ico'><br> 抽血 / 取样</a></p>
			<p><a href="sanitation.html" onfocus="javascript:this.blur();"
				target="mainFrame"><img style="CURSOR: hand" onclick=""
				src='image/wsxd.ico'><br> 卫生、消毒等</a></p>
			</div>
			</li>
			<%
			    if (session.getAttribute("username").equals("bssa"))
			    {
			%>
			<li>
			<h2 align=center>用户管理</h2>
			<div class="content">
			<p><a href="admin.html" onfocus="javascript:this.blur();"
				target="mainFrame"><img style="CURSOR: hand" onclick=""
				src='image/smsf.ico'><br> 用户管理</a></p>
			</div>
			</li>
			<%
			    }
			%>
		</ul>
		</div></td>
		<td valign="top" height="430px" width="5">
		<!-- MSCellType="NavBody2" -->
		<img border="0" src="image/menuright.png" width="5" height="450px"></td>
	</tr>
	<tr>
		<td valign="top" colspan="3" height="20"><img border="0" src="image/menubottom.png" width=148></td>
	</tr>
</table>
		</td>
		<td valign="top" height="800px" width="12"><!-- MSCellType="NavBody2" -->
		<img border="0" src="image/ja_b_10.gif" width="12" height="800px"></td>
	</tr>

</table>
</body>
</html>

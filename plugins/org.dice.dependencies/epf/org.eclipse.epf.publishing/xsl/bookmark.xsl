<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Copyright (c) 2005, 2006 IBM Corporation and others.
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the Eclipse Public License v1.0
    which accompanies this distribution, and is available at
    http://www.eclipse.org/legal/epl-v10.html
    Contributors:
    IBM Corporation - initial implementation
-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">

<xsl:output method="html" version="1.0" encoding="UTF-8" 
	doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" 
	doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" 
	indent="yes"/>
	
	<xsl:template match="/Element">
		<html>
			<xsl:attribute name="lang"><xsl:value-of select="@lang"/></xsl:attribute>
			<xsl:attribute name="xml:lang"><xsl:value-of select="@lang"/></xsl:attribute>
			<head>
				<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
				<title>Contents</title>
				<style type="text/css">
/*******************************************************************************
 * Copyright (c) 2000, 2004, 2005 IBM Corporation. All Rights Reserved. 
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

* {
	font-family: arial, helvetica, sans-serif;
	font-size: 9pt;
	margin: 0;
	padding: 0;
}

BODY {
	background-color: Window;
	font: icon;
	margin:0;
	padding:0;
	border:0;
}

UL {
	border-width:0;
	margin-left:15px;
}

#root {
	margin-top:5px;
	margin-left:5px;
}

UL.expanded {
	display:block;
}

UL.collapsed {
	display: none;
}

LI {
	margin-top:3px;
	list-style-image:none;
	list-style-type:none;
		white-space: nowrap;
}

IMG {
	vertical-align: top;
	border:0px;
	margin:0px;
	padding:0px;
	margin-right:4px;
}


A {
	text-decoration:none;
	color:#258
	padding-right:2px;
	/* this works in ie5.5, but not in ie5.0  */
	white-space: nowrap;
}

A:hover{
	text-decoration:underline;
}

A.active{
	background:Highlight;
	color:HighlightText;
	width:100%;
}

A.active:hover{
	text-decoration:underline;
	background:Highlight;
	color:HighlightText;
	width:100%;
}



.h {
	visibility:hidden;
}

</style>
				<base target="ory_doc"/>
				<script language="JavaScript">

// Preload images
minus = new Image();
minus.src = "images"+"/minus.gif";
plus = new Image();
plus.src = "images"+"/plus.gif";
altTopicClosed = "Topic\u0020closed";
altTopicOpen = "Topic\u0020open";
</script>
				<script language="JavaScript" src="toc.js"/>
				<script language="JavaScript" src="tree.js"/>
			</head>
			<body dir="ltr" onload="setLoaded();">
				<ul dir="ltr" class="expanded" id="root">
					<xsl:call-template name="processNode">
						<xsl:with-param name="node" select="Element"/>
						<xsl:with-param name="parentId" select="''"/>
					</xsl:call-template>
				</ul>
			</body>
		</html>
	</xsl:template>
	<xsl:template name="processNode">
		<xsl:param name="node"/>
		<xsl:param name="parentId"/>
		<xsl:for-each select="$node">
			<xsl:variable name="name">
				<xsl:value-of select="@name"/>
			</xsl:variable>
			<xsl:variable name="id">
				<xsl:value-of select="@nodeId"/>
			</xsl:variable>
			<xsl:variable name="guid">
				<xsl:value-of select="@guid"/>
			</xsl:variable>
			<xsl:variable name="iconurl">./images/<xsl:value-of select="@closedIconName"/>
			</xsl:variable>
			<xsl:variable name="url">./../<xsl:value-of select="@url"/>
			</xsl:variable>
			<li>
				<xsl:choose>
					<xsl:when test="count(Element) > 0 ">
						<img src="images/plus.gif" class="collapsed" alt="" title="" showIcon="true"/>
					</xsl:when>
					<xsl:otherwise>
						<!-- <span style="width:12"></span> -->
						<img src="images/noplus.gif" class="collapsed" alt="" title="" showIcon="false"/>
					</xsl:otherwise>
				</xsl:choose>
				<a id="{$id}" parentId="{$parentId}" guid="{$guid}" href="{$url}">
					<img src="{$iconurl}" alt="" title=""/>
					<xsl:value-of select="$name"/>
				</a>
				<xsl:if test="count(Element) > 0 ">
					<ul class="collapsed">
						<xsl:call-template name="processNode">
							<xsl:with-param name="node" select="Element"/>
							<xsl:with-param name="parentId" select="$id"/>
						</xsl:call-template>
					</ul>
				</xsl:if>
			</li>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>

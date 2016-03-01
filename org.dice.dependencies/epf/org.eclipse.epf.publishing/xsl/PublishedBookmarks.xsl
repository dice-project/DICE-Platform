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
	indent="yes"/>
<!-- this mess up the layout. disable it before the layout csss is fixed.
<xsl:output method="html" version="1.0" encoding="UTF-8" 
	doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" 
	doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" 
	indent="yes"/>
-->

	<xsl:param name="whereAmIText"/>
	<xsl:param name="treeSetsText"/>
	<xsl:template match="Bookmarks">
	
<xsl:text disable-output-escaping="yes">
	<![CDATA[
<!--
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
-->
]]>
</xsl:text>


		<html>
			<xsl:attribute name="lang"><xsl:value-of select="@lang"/></xsl:attribute>
			<xsl:attribute name="xml:lang"><xsl:value-of select="@lang"/></xsl:attribute>
			<head>
				<style type="text/css">
body {
        background-color: #ffffff;
        font-family: arial, helvetica, sans-serif;
        font-size: 10pt;
        margin-left: 0px;
        margin-right: 0px;
        margin-top: 0px;
        margin-bottom: 0px;
        text-decoration: none;
}

td.menu {
        background:#006699;
}

span.menu {
        cursor : hand;
        font-size: 8pt;
        color: #ffffff;
        white-space:nowrap;
        padding-right: 0.5em;
        border-right: 2px solid #ffffff;
}

span.menu img {
        vertical-align: middle;
}

td.tab {
        background:#ffffff;
        padding-top: 1px;
        padding-bottom: 1px;
}

span.tab, span.selectedTab {
	cursor: pointer;
        font-size: 10pt;
        white-space:nowrap;
        line-height: 1.4em;
}

span.tab {
        background:#c0c0c0;
        border-top:   1px solid #666666;
        border-right: 1px solid #666666;
        border-left:  1px solid #666666;
}

span.selectedTab {
        background:#e0e0e0;
        border-top:   1px solid #a0a0a0;
        border-right: 1px solid #a0a0a0;
        border-left:  1px solid #a0a0a0;
}

td.tree {
        border-top: 1px solid #999999;
}
</style>
				<script type="text/javascript" src="bookmarkviews.js">
</script>
				<script>
function checkKey(e, func) {
        if (window.event) {
                key = window.event.keyCode;
                target = window.event.srcElement;
        } else if (e) {
                key = e.which;
                target = e.target;
        }

        if (key==13 || key==32) {
                func(target);
        }
}
</script>
			</head>
			<body marginheight="0" marginwidth="0" onload="init();">
				<table cellspacing="1px" cellpadding="0px" border="0" height="100%" width="100%">
					<!-- add the tab menus -->
					<tr>
						<td class="menu">
							<xsl:text>
</xsl:text>
							<span class="menu" tabindex="1" onclick="whereAmI();" onkeypress="checkKey(event, whereAmI);">
								<img alt="" title="" src="images/ni_where.gif"/>
								<xsl:value-of select="$whereAmIText"/>
							</span>
							<xsl:text>
</xsl:text>
							<span class="menu" tabindex="1" onclick="togViews();" onkeypress="checkKey(event, togViews);">
								<img alt="" title="" src="images/action_unhide.gif"/>
								<xsl:value-of select="$treeSetsText"/>
							</span>
							<xsl:text>
</xsl:text>
						</td>
					</tr>
					<!-- load the tabs -->
					<tr>
						<td class="tab">
							<xsl:text>
</xsl:text>
							<xsl:for-each select="Bookmark">
								<xsl:variable name="pos">
									<xsl:value-of select="position()-1"/>
								</xsl:variable>
								<span class="tab" id="tab{$pos}" tabindex="1" onkeypress="checkKey(event, displayTree);" onclick="displayTree(this);">
									<xsl:attribute name="url"><xsl:value-of select="@url"/></xsl:attribute>
									<xsl:attribute name="isDefaultView"><xsl:value-of select="@default"/></xsl:attribute>&#160;<xsl:value-of select="@tabName"/>&#160;</span>
								<xsl:text>
</xsl:text>
							</xsl:for-each>
						</td>
					</tr>
					<!-- placeholder for the content tree -->
					<tr height="100%">
						<td class="tree">
							<iframe name="ory_toc" title="" style="visibility:visible" tabindex="1" frameborder="no" width="100%" height="100%" scrolling="auto"/>
						</td>
					</tr>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>

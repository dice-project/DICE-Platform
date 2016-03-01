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

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">

<xsl:output method="html" version="1.0" encoding="UTF-8" 
	doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" 
	doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" 
	indent="yes"/>
	
<xsl:param name="title"/>
<xsl:param name="glossaryText"/>

<xsl:template match="Glossary">

<html>
			<xsl:attribute name="lang"><xsl:value-of select="@lang"/></xsl:attribute>
			<xsl:attribute name="xml:lang"><xsl:value-of select="@lang"/></xsl:attribute>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title><xsl:value-of select="$glossaryText"/> - <xsl:value-of select="$title"/></title>
</head>
<body text="#000000" bgcolor="#ffffff">
<font face="Arial, Helvetica, sans-serif">
<dl>

<xsl:for-each select="item">
<dt>
<b>
<a>
<xsl:attribute name="id">_GLOSSARY_ITEM_<xsl:value-of select="@name"/></xsl:attribute>
<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute><xsl:value-of select="@presentationName"/>
</a>
</b>
</dt>

<dd>
<xsl:value-of disable-output-escaping="yes" select="@content"/>	
</dd>
</xsl:for-each>

</dl>
</font>
</body>
</html>

</xsl:template>
	
</xsl:stylesheet>

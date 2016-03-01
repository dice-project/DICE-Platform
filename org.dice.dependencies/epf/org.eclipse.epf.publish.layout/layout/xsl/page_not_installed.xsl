<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Copyright (c) 2005, 2007 IBM Corporation and others.
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the Eclipse Public License v1.0
    which accompanies this distribution, and is available at
    http://www.eclipse.org/legal/epl-v10.html
    Contributors:
    IBM Corporation - initial implementation
-->

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">

<xsl:output method="html" version="1.0" encoding="UTF-8" 
	doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" 
	doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" 
	indent="yes"/>
	
	<xsl:include href="resources.xsl"/>

	<xsl:template match="/Element">
		<xsl:variable name="backPath" select="@BackPath"/>
		<xsl:variable name="imagePath" select="concat($backPath, 'images/')"/>
		<xsl:variable name="copyright" select="copyright"/>
		<xsl:variable name="showTreeBrowser" select="@showTreeBrowser"/>		
		<html>	
			<xsl:attribute name="lang"><xsl:value-of select="@lang"/></xsl:attribute>
			<xsl:attribute name="xml:lang"><xsl:value-of select="@lang"/></xsl:attribute>
			<head>
				<title><xsl:value-of select="$pageNotInstalledText"/></title>
			</head>		
			<body>
			<xsl:if test="$backgroundImage!=''">
				<xsl:attribute name="background"><xsl:value-of select="concat($imagePath, $backgroundImage)"/></xsl:attribute>
			</xsl:if>
			<xsl:choose>
				<xsl:when test="@invalidLink">
					<b><h3><xsl:value-of select="$elementDoesNotExistText"/></h3></b>
					<p/>
					<xsl:value-of select="$isNotValidText"/>
					<br/>	
					<xsl:value-of select="$elementMightBeDeletedText"/>
					<p/>
					<xsl:value-of select="$pleaseFixLinkText"/>										
				</xsl:when>
			<xsl:otherwise>
				<b><h3><xsl:value-of select="$missingElementText"/></h3></b>
				<p/>
				<xsl:value-of select="$notAvailableText"/>								
			</xsl:otherwise>
			</xsl:choose>
			</body>
		</html>
	</xsl:template>

</xsl:stylesheet>

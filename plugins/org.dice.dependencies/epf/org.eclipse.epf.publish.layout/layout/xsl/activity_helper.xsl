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

	<xsl:include href="resources.xsl"/>
	<xsl:include href="common.xsl"/>
	<xsl:include href="mapping.xsl"/>
	<xsl:include href="overview.xsl"/>
	<xsl:include href="guidance_helper.xsl"/>
	<xsl:include href="extended_refs.xsl"/>
	<xsl:include href="extended_rtes.xsl"/>

	<xsl:template name="tabUrlAttribute">
		<xsl:param name="url"/>
		<xsl:attribute name="href"><xsl:value-of select="$url"/></xsl:attribute>
	</xsl:template>

	<xsl:template name="displayDiagram">
		<xsl:param name="diagram"/>
		<xsl:variable name="single-quote">'</xsl:variable>
		<xsl:variable name="special-quote">`</xsl:variable>
		<p/>
		<img border="0" diagramType="{$diagram/@name}">
			<xsl:attribute name="id">diagram_<xsl:value-of select="$diagram/@name"/></xsl:attribute>
			<xsl:attribute name="src"><xsl:value-of select="$diagram/map/@src"/></xsl:attribute>
			<xsl:attribute name="alt"><xsl:value-of select="translate($diagram/@alt,$single-quote,$special-quote)"/></xsl:attribute>
			<xsl:attribute name="title"><xsl:value-of select="translate($diagram/@alt,$single-quote,$special-quote)"/></xsl:attribute>
			<xsl:attribute name="usemap">#<xsl:value-of select="$diagram/map/@name"/></xsl:attribute>
		</img>
		<map name="{$diagram/map/@name}">
			<xsl:for-each select="$diagram/map/area">
				<area>
					<xsl:attribute name="href"><xsl:value-of select="@href"/></xsl:attribute>
					<xsl:attribute name="relPath">,<xsl:value-of select="@guid"/></xsl:attribute>
					<xsl:attribute name="alt"><xsl:value-of select="@alt"/></xsl:attribute>
					<xsl:attribute name="title"><xsl:value-of select="@alt"/></xsl:attribute>
					<xsl:attribute name="coords"><xsl:value-of select="@coords"/></xsl:attribute>
					<xsl:attribute name="shape"><xsl:value-of select="@shape"/></xsl:attribute>
				</area>
			</xsl:for-each>
		</map>
	</xsl:template>

</xsl:stylesheet>

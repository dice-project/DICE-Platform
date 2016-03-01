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

	<xsl:template name="mapping">
		<xsl:param name="elementType"/>
		<xsl:choose>
			<xsl:when test="$elementType = 'Task'">
				<xsl:attribute name="content"><xsl:value-of select="'activity'"/></xsl:attribute>
			</xsl:when>
			<xsl:when test="$elementType = 'ToolMentor'">
				<xsl:attribute name="content"><xsl:value-of select="'tool_mentor'"/></xsl:attribute>
			</xsl:when>
			<xsl:when test="$elementType = 'Outcome'">
				<xsl:attribute name="content"><xsl:value-of select="'artifact'"/></xsl:attribute>
			</xsl:when>
			<xsl:when test="$elementType = 'Deliverable'">
				<xsl:attribute name="content"><xsl:value-of select="'artifact'"/></xsl:attribute>
			</xsl:when>
			<xsl:when test="$elementType = 'Activity'">
				<xsl:attribute name="content"><xsl:value-of select="'workflow_detail'"/></xsl:attribute>
			</xsl:when>
			<xsl:when test="$elementType = 'Concept'">
				<xsl:attribute name="content"><xsl:value-of select="'concept'"/></xsl:attribute>
			</xsl:when>
			<xsl:when test="$elementType = 'Example'">
				<xsl:attribute name="content"><xsl:value-of select="'example'"/></xsl:attribute>
			</xsl:when>
			<xsl:otherwise>
				<xsl:attribute name="content"><xsl:value-of select="'other'"/></xsl:attribute>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

</xsl:stylesheet>

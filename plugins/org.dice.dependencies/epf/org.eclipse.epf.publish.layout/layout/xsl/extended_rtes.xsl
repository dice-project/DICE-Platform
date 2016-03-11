<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Copyright (c) 2005, 2012 IBM Corporation and others.
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

	<xsl:template name="extendedRtesSectionsAll">
		<xsl:param name="descriptionDown"/>
		<xsl:param name="layoutLocation"/>
		
		<!-- render all sections with a specific layout="VALUE" except no layout= attribute -->
		<xsl:if test="string($layoutLocation)">
			<xsl:variable name="rteSections" select="$descriptionDown/section[@type='rte' and @layout=$layoutLocation]"/>
			<xsl:call-template name="renderRteSections">
				<xsl:with-param name="rteSections" select="$rteSections"/>
			</xsl:call-template>
		</xsl:if>

		<!-- render all sections without layout= attribute -->
		<xsl:if test="not(string($layoutLocation))">
			<xsl:variable name="rteSections" select="$descriptionDown/section[@type='rte' and (not(@layout)) ]"/>
			<xsl:call-template name="renderRteSections">
				<xsl:with-param name="rteSections" select="$rteSections"/>
			</xsl:call-template>
		</xsl:if>
			
	</xsl:template>	
	
	<!-- for internal use in this file -->
	<xsl:template name="renderRteSections">
		<xsl:param name="rteSections"/>
	
		<xsl:for-each select="$rteSections">
		
			<xsl:for-each select="rte">
				<xsl:variable name="rteText" select="text()"/>
				<xsl:if test="string($rteText) != ''">
				
					<div class="sectionHeading">
						<xsl:value-of select="@name"/>
					</div>
					<div class="sectionContent">
						<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
							<tr valign="top">
								<td class="sectionTableSingleCell">
									<xsl:value-of disable-output-escaping="yes" select="$rteText"/>
								</td>
							</tr>
						</table>
					</div>

				</xsl:if> 
			</xsl:for-each>
			
		</xsl:for-each>
		
	</xsl:template>

</xsl:stylesheet>

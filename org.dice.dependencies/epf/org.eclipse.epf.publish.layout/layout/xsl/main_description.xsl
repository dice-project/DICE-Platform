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
	
	<!--
	<xsl:include href="extended_rtes.xsl"/>
	-->
	
	<xsl:template name="mainDescriptionSection">
		<xsl:param name="description"/>
		<xsl:variable name="mainDescription" select="$description/attribute[@name='mainDescription']"/>
		<xsl:if test="$mainDescription != ''">
		
			<div class="sectionHeading">
				<xsl:value-of select="$mainDescriptionText"/>
			</div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					<tr valign="top">
						<td class="sectionTableSingleCell">
							<xsl:value-of disable-output-escaping="yes" select="$mainDescription"/>
						</td>
					</tr>
				</table>
			</div>
		</xsl:if>
		
		<!--
		<xsl:call-template name="extendedRtesSectionsAll">
			<xsl:with-param name="descriptionDown" select="$description"/>
			<xsl:with-param name="layoutLocation" select="''"/>
		</xsl:call-template>
		-->
	</xsl:template>

</xsl:stylesheet>

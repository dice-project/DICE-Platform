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

	<xsl:template name="tailoringSection">
		<xsl:param name="description"/>
		<xsl:variable name="impactOfNotHaving" select="$description/attribute[@name='impactOfNotHaving']"/>
		<xsl:variable name="reasonsForNotNeeding" select="$description/attribute[@name='reasonsForNotNeeding']"/>
		<xsl:variable name="representationOptions" select="$description/attribute[@name='representationOptions']"/>
		<xsl:if test="$impactOfNotHaving != '' or $reasonsForNotNeeding != '' or $representationOptions != ''">
			<div class="sectionHeading">
				<xsl:value-of select="$tailoringText"/>
			</div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					<xsl:if test="$impactOfNotHaving != ''">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row">
								<xsl:value-of select="$impactOfNotHavingText"/>
							</th>
							<td class="sectionTableCell">
								<xsl:value-of disable-output-escaping="yes" select="$impactOfNotHaving"/>
							</td>
						</tr>
					</xsl:if>
					<xsl:if test="$reasonsForNotNeeding != ''">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row">
								<xsl:value-of select="$reasonsForNotNeedingText"/>
							</th>
							<td class="sectionTableCell">
								<xsl:value-of disable-output-escaping="yes" select="$reasonsForNotNeeding"/>
							</td>
						</tr>
					</xsl:if>
					<xsl:if test="$representationOptions != ''">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row">
								<xsl:value-of select="$representationOptionsText"/>
							</th>
							<td class="sectionTableCell">
								<xsl:value-of disable-output-escaping="yes" select="$representationOptions"/>
							</td>
						</tr>
					</xsl:if>
				</table>
			</div>
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>

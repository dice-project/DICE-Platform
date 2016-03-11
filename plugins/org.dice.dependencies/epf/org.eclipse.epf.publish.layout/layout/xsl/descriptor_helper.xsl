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

	<xsl:template name="addDescriptors">
		<xsl:param name="descriptors"/>
		<xsl:param name="colspan"/>
		<xsl:variable name="showFullMethodContent" select="@ShowFullMethodContent"/>		
		<xsl:if test="count($descriptors) > 0">
			<tr valign="top">
				<th class="sectionTableHeading" scope="row">
					<xsl:value-of select="$processUsageText"/>			
				</th>
				<td class="sectionTableCell">
					<xsl:attribute name="colspan"><xsl:value-of select="$colspan"/></xsl:attribute>
					<ul>
						<xsl:for-each select="$descriptors/../*">
						<xsl:sort select="Element[1]/@DisplayName"/>
							<li>
								<!-- add the parent link -->
								<xsl:for-each select="Element">
									<xsl:call-template name="addElementWithLink">
										<xsl:with-param name="element" select="."/>
									</xsl:call-template>
									&#160;&gt;&#160;
								</xsl:for-each>
								<xsl:call-template name="addElementWithLink">
									<xsl:with-param name="element" select="."/>
								</xsl:call-template>
								<xsl:variable name="EntryValue" select="attribute/@activityEntryState"/>
								<xsl:variable name="ExitValue" select="attribute/@activityExitState"/>																 
								<xsl:if test="$EntryValue != ''">
									[<xsl:value-of select="$EntryValue"/>]	
								</xsl:if>								
								<xsl:if test="$ExitValue != ''">
									[<xsl:value-of select="$ExitValue"/>]
								</xsl:if>																
							</li>
						</xsl:for-each>
					</ul>
				</td>
			</tr>
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>

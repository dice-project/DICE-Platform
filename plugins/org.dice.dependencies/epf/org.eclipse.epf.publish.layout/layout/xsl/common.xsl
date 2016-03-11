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

	<xsl:template name="umaMetaTags">
		<xsl:param name="elementType"/>
		<xsl:param name="elementName"/>
		<xsl:param name="elementPresentationName"/>
		<meta name="uma.type" content="{$elementType}"/>
		<meta name="uma.name" content="{$elementName}"/>
		<meta name="uma.presentationName" content="{$elementPresentationName}"/>
		<xsl:if test="$elementType = 'Role'">
			<xsl:call-template name="roleMetaInfo"/>
		</xsl:if>
		<xsl:if test="$elementType = 'Task'">
			<xsl:call-template name="taskMetaInfo"/>
		</xsl:if>
		<xsl:if test="$elementType = 'Artifact' or $elementType = 'Deliverable' or $elementType = 'Outcome'">
			<xsl:call-template name="workProductMetaInfo"/>
		</xsl:if>
		<xsl:if test="$elementType = 'ToolMentor'">
			<xsl:call-template name="toolMentorMetaInfo"/>
		</xsl:if>
	</xsl:template>

	<xsl:template name="workProductMetaInfo">
		<xsl:variable name="domains" select="referenceList[@name='domains']/Element"/>
		<xsl:variable name="workProductTypes" select="referenceList[@name='workProductTypes']/Element[@Type='WorkProductType']"/>
		<xsl:if test="count($domains) > 0">
			<xsl:for-each select="$domains">
				<meta name="uma.category" content="@Type:@Name:@DisplayName"/>
			</xsl:for-each>
		</xsl:if>
		<xsl:if test="count($workProductTypes) > 0">
			<xsl:for-each select="$workProductTypes">
				<meta name="uma.category" content="{$workProductTypes/@Type}:{$workProductTypes/@Name}:{$workProductTypes/@DisplayName}"/>
			</xsl:for-each>
		</xsl:if>
	</xsl:template>

	<xsl:template name="taskMetaInfo">
		<xsl:variable name="disciplines" select="referenceList[@name='disciplines']/Element[@Type='Discipline']"/>
		<xsl:if test="count($disciplines) > 0">
			<xsl:for-each select="$disciplines">
				<meta name="uma.category" content="{$disciplines/@Type}:{$disciplines/@Name}:{$disciplines/@DisplayName}"/>
			</xsl:for-each>
		</xsl:if>
	</xsl:template>

	<xsl:template name="roleMetaInfo">
		<xsl:variable name="roleSets" select="referenceList[@name='roleSets']/Element[@Type='RoleSet']"/>
		<xsl:if test="$roleSets/@Name != ''">
			<meta name="uma.category" content="{$roleSets/@Type}:{$roleSets/@Name}:{$roleSets/@DisplayName}"/>
		</xsl:if>
	</xsl:template>

	<xsl:template name="toolMentorMetaInfo">
		<xsl:variable name="tool" select="reference[@name='tool']/Element"/>
		<xsl:if test="$tool/@Name != ''">
			<meta name="uma.category" content="{$tool/@Type}:{$tool/@Name}:{$tool/@DisplayName}"/>
		</xsl:if>
	</xsl:template>

	<xsl:template name="addReferences">
		<xsl:param name="refName"/>
		<xsl:param name="refElement"/>
		<xsl:if test="$refElement">
			<xsl:if test="count($refElement)>0">
				<tr valign="top">
					<th class="sectionTableHeading" scope="row">
						<xsl:value-of select="$refName"/>
					</th>
					<td class="sectionTableCell">
						<ul>
							<xsl:for-each select="$refElement/../*">
							<xsl:sort select="@DisplayName"/>
								<li>
									<xsl:call-template name="addElementWithLink">
										<xsl:with-param name="element" select="."/>
									</xsl:call-template>
								</li>
							</xsl:for-each>
						</ul>
					</td>
				</tr>
			</xsl:if>
		</xsl:if>
	</xsl:template>

	<xsl:template name="addElementWithLink">
		<xsl:param name="element"/>
		<xsl:choose>
			<xsl:when test="$element/@Url != '' ">
				<a>
					<xsl:attribute name="href"><xsl:value-of select="/Element/@BackPath"/><xsl:value-of select="$element/@Url"/></xsl:attribute>
					<xsl:value-of disable-output-escaping="yes" select="$element/@DisplayName"/>
				</a>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of disable-output-escaping="yes" select="$element/@DisplayName"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>	

	<xsl:template name="copyright">
		<xsl:param name="copyright"/>
		<table class="copyright" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="copyright">
					<xsl:value-of disable-output-escaping="yes" select="$copyright"/>
				</td>
			</tr>
		</table>
	</xsl:template>
	
	<xsl:template name="showParentPractices">
			<xsl:variable name="practices" select="referenceList[@name='Practices']/Element"/>
			<xsl:if test="count($practices) > 0">
					<tr valign="top">
						<th class="sectionTableHeading" scope="row"><xsl:value-of select="$practicesText"/></th>
						<td class="sectionTableCell" colspan="2">
						<ul>
							<xsl:for-each select="$practices">
							<xsl:sort select="@DisplayName"/>
								<li>
									<a>
										<xsl:attribute name="href"><xsl:value-of select="/Element/@BackPath"/><xsl:value-of select="@Url"/></xsl:attribute>
										<xsl:value-of select="@DisplayName"/>
									</a>
								</li>
							</xsl:for-each>
						</ul>
						</td>
					</tr>
				</xsl:if>	
	</xsl:template>
	
</xsl:stylesheet>

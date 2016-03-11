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
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
	<xsl:output method="html" version="1.0" encoding="UTF-8" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" indent="yes"/>
	<xsl:include href="guidance.xsl"/>
	
	<xsl:template match="/Element">
		<xsl:variable name="elementType" select="@Type"/>
		<xsl:variable name="elementTypeName" select="@TypeName"/>
		<xsl:variable name="elementName" select="@Name"/>
		<xsl:variable name="elementPresentationName" select="@DisplayName"/>
		<xsl:variable name="backPath" select="@BackPath"/>
		<xsl:variable name="imagePath" select="concat($backPath, 'images/')"/>
		<xsl:variable name="shapeImage" select="concat($backPath,@ShapeiconUrl)"/>
		<xsl:variable name="presentation" select="reference[@name='presentation']"/>
		<xsl:variable name="contentDescription" select="$presentation/Element"/>
		<xsl:variable name="copyright" select="copyright"/>
		<xsl:variable name="showTreeBrowser" select="@showTreeBrowser"/>
		<xsl:variable name="responsibleRole" select="reference[@name='responsibleRole']/Element[@Type='Role']"/>
		<xsl:variable name="tagValues" select="@TagValues"/>
		<html>
			<xsl:attribute name="lang"><xsl:value-of select="@lang"/></xsl:attribute>
			<xsl:attribute name="xml:lang"><xsl:value-of select="@lang"/></xsl:attribute>
			<head>
				<title>
					<xsl:call-template name="elementPageTitleText">
						<xsl:with-param name="elementType" select="$elementType"/>
						<xsl:with-param name="elementTypeName" select="$elementTypeName"/>
						<xsl:with-param name="elementPresentationName" select="$elementPresentationName"/>
					</xsl:call-template>
				</title>
				<xsl:call-template name="umaMetaTags">
					<xsl:with-param name="elementType" select="$elementType"/>
					<xsl:with-param name="elementName" select="$elementName"/>
					<xsl:with-param name="elementPresentationName" select="$elementPresentationName"/>
				</xsl:call-template>
				<meta name="element_type" content="{$elementType}"/>
				<meta name="filetype" content="description"/>
				<meta name="role">
					<xsl:choose>  
						<xsl:when test="$responsibleRole/@DisplayName!=''">
							<xsl:attribute name="content"><xsl:value-of select="$responsibleRole/@DisplayName"/></xsl:attribute>
						</xsl:when>  
						<xsl:otherwise>
							<xsl:attribute name="content">none</xsl:attribute>
						</xsl:otherwise>
					</xsl:choose>
				</meta>
				<xsl:if test="$tagValues!=''">
					<meta name="tags" content="{$tagValues}"/>
				</xsl:if>
				<link rel="StyleSheet" href="{$backPath}css/default.css" type="text/css"/>
				<script src="{$backPath}scripts/ContentPageResource.js" type="text/javascript" language="JavaScript"/>
				<script src="{$backPath}scripts/ContentPageSection.js" type="text/javascript" language="JavaScript"/>
				<script src="{$backPath}scripts/ContentPageSubSection.js" type="text/javascript" language="JavaScript"/>
				<script src="{$backPath}scripts/ContentPageToolbar.js" type="text/javascript" language="JavaScript"/>
				<script src="{$backPath}scripts/contentPage.js" type="text/javascript" language="JavaScript"/>
				<script type="text/javascript" language="JavaScript">
					var backPath = '<xsl:value-of select="$backPath"/>';
					var imgPath = '<xsl:value-of select="$imagePath"/>';
					var nodeInfo=null;
					contentPage.preload(imgPath, backPath, nodeInfo,  '', false, false, false);
				</script>
			</head>
			<body>
			<xsl:if test="$backgroundImage!=''">
				<xsl:attribute name="background"><xsl:value-of select="concat($imagePath, $backgroundImage)"/></xsl:attribute>
			</xsl:if>
				<div id="breadcrumbs"/>
				<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td valign="top">
							<a name="Top"/>
							<xsl:call-template name="overview">
								<xsl:with-param name="elementType" select="$elementType"/>
								<xsl:with-param name="elementTypeName" select="$elementTypeName"/>
								<xsl:with-param name="elementPresentationName" select="$elementPresentationName"/>
								<xsl:with-param name="elementIcon" select="$shapeImage"/>
								<xsl:with-param name="backPath" select="$backPath"/>
								<xsl:with-param name="showTreeBrowser" select="$showTreeBrowser"/>
							</xsl:call-template>
							<xsl:call-template name="relationshipsSection2"/>
							<xsl:call-template name="extendedRefsSectionsAll">
								<xsl:with-param name="elementDown" select="/Element"/>
								<xsl:with-param name="layoutLocation" select="''"/>
							</xsl:call-template>
							<xsl:call-template name="descriptionSection">
								<xsl:with-param name="description" select="$contentDescription"/>
							</xsl:call-template>
							<xsl:call-template name="extendedRtesSectionsAll">
								<xsl:with-param name="descriptionDown" select="$contentDescription"/>
								<xsl:with-param name="layoutLocation" select="''"/>
							</xsl:call-template>
							<xsl:call-template name="copyright">
								<xsl:with-param name="copyright" select="$copyright"/>
							</xsl:call-template>
						</td>
					</tr>
				</table>
			</body>
			<script type="text/javascript" language="JavaScript">
				contentPage.onload();
			</script>
		</html>
	</xsl:template>
	<xsl:template name="descriptionSection">
		<xsl:param name="description"/>
		<xsl:variable name="mainDescription" select="$description/attribute[@name='mainDescription']"/>
		<xsl:variable name="problem" select="$description/attribute[@name='problem']"/>
		<xsl:variable name="background" select="$description/attribute[@name='background']"/>
		<xsl:variable name="goals" select="$description/attribute[@name='goals']"/>
		<xsl:variable name="application" select="$description/attribute[@name='application']"/>
		<xsl:variable name="levelsOfAdoption" select="$description/attribute[@name='levelsOfAdoption']"/>
		<xsl:variable name="additionalInfo" select="$description/attribute[@name='additionalInfo']"/>
		<xsl:if test="$mainDescription != '' or $problem != '' or $background != '' or $goals != '' or $application != '' or $levelsOfAdoption != '' or $additionalInfo != ''">
			
		<xsl:if test="$problem != ''">
			
			<div class="sectionHeading">
				<xsl:value-of select="$purposeText"/>
			</div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					
						<tr valign="top">
							<td class="sectionTableCell">
								<xsl:value-of disable-output-escaping="yes" select="$problem"/>
							</td>
						</tr>
					
				</table>
			</div>
		</xsl:if>	
		
		<xsl:if test="$goals != ''">
			<div class="sectionHeading">
				<xsl:value-of select="$goalsText"/>
			</div>	
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
						<tr valign="top">
							<td class="sectionTableCell">
								<xsl:value-of disable-output-escaping="yes" select="$goals"/>
							</td>
						</tr>				
				</table>
			</div>			
		</xsl:if>	
		
		
		<xsl:if test="$background != ''">
		<div class="sectionHeading">
				<xsl:value-of select="$backgroundText"/>
		</div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
						<tr valign="top">
							
							<td class="sectionTableCell">
								<xsl:value-of disable-output-escaping="yes" select="$background"/>
							</td>
						</tr>
				</table>
			</div>
		</xsl:if>		
		
		<xsl:if test="$mainDescription != ''">
		 <div class="sectionHeading">
				<xsl:value-of select="$mainDescriptionText"/>
		</div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">					
						<tr valign="top">
							<td class="sectionTableCell">
								<xsl:value-of disable-output-escaping="yes" select="$mainDescription"/>
							</td>
						</tr>
					
				</table>
			</div>
		</xsl:if>
		<xsl:if test="$application != ''">
			<div class="sectionHeading">
				<xsl:value-of select="$practice_application_text"/>
			</div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					
						<tr valign="top">
							<td class="sectionTableCell">
								<xsl:value-of disable-output-escaping="yes" select="$application"/>
							</td>
						</tr>					
				</table>
			</div>
		</xsl:if>	
		
			
			<xsl:if test="$levelsOfAdoption != ''">			
				<div class="sectionHeading">
					<xsl:value-of select="$levelsOfAdoptionText"/>
				</div>
				<div class="sectionContent">
					<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">					
						<tr valign="top">
							<td class="sectionTableCell">
								<xsl:value-of disable-output-escaping="yes" select="$levelsOfAdoption"/>
							</td>
						</tr>					
				</table>
			</div>
			</xsl:if>
			

			<xsl:if test="$additionalInfo != ''">			
				<div class="sectionHeading">
					<xsl:value-of select="$additionalInfoText"/>
				</div>
				<div class="sectionContent">
					<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">					
						<tr valign="top">
							<td class="sectionTableCell">
								<xsl:value-of disable-output-escaping="yes" select="$additionalInfo"/>
							</td>
						</tr>					
				</table>
			</div>
			</xsl:if>
		</xsl:if>
		
	</xsl:template>
	<xsl:template name="relationshipsSection2">
				<xsl:variable name="practices" select="referenceList[@name='Practices']/Element"/>
		<xsl:variable name="subPractices" select="referenceList[@name='subPractices']/Element"/>
		<xsl:variable name="inputSlots" select="referenceList[@name='Input work product slots']/Element"/>
		<xsl:variable name="contentReferences" select="referenceList[@name='contentReferences']/Element"/>
		<xsl:variable name="activityReferences" select="referenceList[@name='activityReferences']/Element"/>
		<xsl:variable name="categories" select="referenceList[@name='ContentElement_CustomCategories']/Element"/>
		<xsl:variable name="practiceTree" select="referenceList[@name='Practice guidance tree']"/>
		<xsl:if test="count($practices) + count($contentReferences) + count($subPractices) + count($activityReferences) + count($categories) + count($inputSlots) > 0">
			<div class="sectionHeading">
				<xsl:value-of select="$relationshipsText"/>
			</div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
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
					<xsl:call-template name="addReferences">
						<xsl:with-param name="refName" select="$categoriesText"/>
						<xsl:with-param name="refElement" select="$categories"/>
					</xsl:call-template>
					<xsl:call-template name="addReferences">
						<xsl:with-param name="refName" select="$subPracticesText"/>
						<xsl:with-param name="refElement" select="$subPractices"/>
					</xsl:call-template>
					<tr valign="top">
						<th class="sectionTableHeading" scope="row">
							<xsl:value-of select="$contentReferencesText"/>
						</th>
						<td class="sectionTableCell">
							<xsl:for-each select="$practiceTree/*">
								<ul>
									<xsl:if test="name()='Element'">
										<li>
											<img>
												<xsl:attribute name="src">./../../../<xsl:value-of select="@ShapeiconUrl"/></xsl:attribute>
												<xsl:attribute name="height">16</xsl:attribute>
												<xsl:attribute name="width">16</xsl:attribute>
											</img>
											<a>
												<xsl:attribute name="href"><xsl:value-of select="@BackPath"/><xsl:value-of select="@Url"/></xsl:attribute>
												<xsl:value-of disable-output-escaping="yes" select="@DisplayName"/>
											</a>
										</li>
									</xsl:if>
									<xsl:if test="name()='referenceList'">
										<li>
											<!--Defect 42868【Browsing/Publishing】The strings in prictice and its sub group folder page is not translated by the skin resource-->
											<xsl:choose>
												<xsl:when test="@name = 'Work Products'">
													<xsl:value-of select="$workProductsText"/>
												</xsl:when>
												<xsl:when test="@name = 'Tasks'">
													<xsl:value-of select="$tasksText"/>
												</xsl:when>
												<xsl:when test="@name = 'Guidance'">
													<xsl:value-of select="$guidancesText"/>
												</xsl:when>
												<xsl:when test="@name = 'Roles'">
													<xsl:value-of select="$rolesText"/>
												</xsl:when>
												<xsl:otherwise>
													<xsl:value-of select="@name"/>
												</xsl:otherwise>	
											</xsl:choose>
										</li>
										<ul>
											<xsl:for-each select="./*">
												<xsl:if test="name()='referenceList'">
													<li>
														<xsl:value-of select="@name"/>
													</li>
													<ul>
														<xsl:for-each select="./*">
															<li>
																<img>
																	<xsl:attribute name="src">./../../../<xsl:value-of select="@ShapeiconUrl"/></xsl:attribute>
																	<xsl:attribute name="height">16</xsl:attribute>
																	<xsl:attribute name="width">16</xsl:attribute>
																</img>
																<a>
																	<xsl:attribute name="href"><xsl:value-of select="@BackPath"/><xsl:value-of select="@Url"/></xsl:attribute>
																	<xsl:value-of disable-output-escaping="yes" select="@DisplayName"/>
																</a>
															</li>
														</xsl:for-each>
													</ul>
												</xsl:if>
												<xsl:if test="name()='Element'">
													<li>
														<img>
															<xsl:attribute name="src">./../../../<xsl:value-of select="@ShapeiconUrl"/></xsl:attribute>
															<xsl:attribute name="height">16</xsl:attribute>
															<xsl:attribute name="width">16</xsl:attribute>
														</img>
														<a>
															<xsl:attribute name="href"><xsl:value-of select="@BackPath"/><xsl:value-of select="@Url"/></xsl:attribute>
															<xsl:value-of disable-output-escaping="yes" select="@DisplayName"/>
														</a>
													</li>
												</xsl:if>
											</xsl:for-each>
										</ul>
									</xsl:if>
								</ul>
							</xsl:for-each>
						</td>
					</tr>
					<xsl:call-template name="addReferences">
						<xsl:with-param name="refName" select="$inputsText"/>
						<xsl:with-param name="refElement" select="$inputSlots"/>
					</xsl:call-template>
				</table>
			</div>
		</xsl:if>
	</xsl:template>
</xsl:stylesheet>

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
	<xsl:include href="common.xsl"/>

	<xsl:template match="/Element">
		<xsl:variable name="elementType" select="@Type"/>
		<xsl:variable name="elementTypeName" select="@TypeName"/>
		<xsl:variable name="elementName" select="@Name"/>
		<xsl:variable name="elementPresentationName" select="@DisplayName"/>
		<xsl:variable name="backPath" select="@BackPath"/>
		<xsl:variable name="shapeImage" select="concat($backPath,@ShapeiconUrl)"/>
		<xsl:variable name="imagePath" select="concat($backPath, 'images/')"/>		
		<xsl:variable name="presentation" select="reference[@name='presentation']"/><xsl:variable name="presentationName" select="@DisplayName"/>	
		<xsl:variable name="copyright" select="copyright"/>
		<xsl:variable name="showTreeBrowser" select="@showTreeBrowser"/>
		<xsl:variable name="tagValues" select="@TagValues"/>
		
		<html>
			<xsl:attribute name="lang"><xsl:value-of select="@lang"/></xsl:attribute>
			<xsl:attribute name="xml:lang"><xsl:value-of select="@lang"/></xsl:attribute>
			<head>
				<title><xsl:value-of select="$elementType"/>: <xsl:value-of select="$elementPresentationName"/></title>
				<xsl:call-template name="umaMetaTags">
					<xsl:with-param name="elementType" select="$elementType"/>
					<xsl:with-param name="elementName" select="$elementName"/>
					<xsl:with-param name="elementPresentationName" select="$elementPresentationName"/>
				</xsl:call-template>
				<meta name="element_type" content="{$elementType}"/>
				<meta name="filetype" content="description"/>				
				<xsl:if test="$tagValues!=''">
				    <meta name="tags" content="{$tagValues}"/>
				</xsl:if>
				<link rel="StyleSheet" href="{$backPath}css/default.css" type="text/css"/>
				<script src="{$backPath}scripts/ContentPageResource.js" type="text/javascript" language="JavaScript"></script>
				<script src="{$backPath}scripts/ContentPageSection.js" type="text/javascript" language="JavaScript"></script>
				<script src="{$backPath}scripts/ContentPageSubSection.js" type="text/javascript" language="JavaScript"></script>
				<script src="{$backPath}scripts/ContentPageToolbar.js" type="text/javascript" language="JavaScript"></script>
				<script src="{$backPath}scripts/contentPage.js" type="text/javascript" language="JavaScript"></script>
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
			<div id="breadcrumbs"></div>
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td valign="top">
							<a name="Top"/>
							<xsl:call-template name="summaryOverview">
								<xsl:with-param name="elementType" select="$elementType"/>
								<xsl:with-param name="elementTypeName" select="$elementTypeName"/>
								<xsl:with-param name="elementPresentationName" select="$elementPresentationName"/>
								<xsl:with-param name="elementIcon" select="$shapeImage"/>
								<xsl:with-param name="backPath" select="$backPath"/>
								<xsl:with-param name="showTreeBrowser" select="$showTreeBrowser"/>
							</xsl:call-template>							
							<xsl:for-each select="referenceList">
								<xsl:if test="count(Element) > 0">
									<h2 class="banner"><xsl:value-of select="@name"/></h2>
									<ul>
										<xsl:for-each select="Element">
										<xsl:sort select="@TypeName"/>
										<xsl:sort select="@DisplayName"/>
											<li>
												<a>
													<xsl:attribute name="href">
														<xsl:value-of select="$backPath"/>
														<xsl:value-of select="@Url"/>
													</xsl:attribute>
													<!--Defect 42868【Browsing/Publishing】The strings in prictice and its sub group folder page is not translated by the skin resource-->
													<xsl:choose>
														<xsl:when test="@TypeName = 'Artifact'">
															<xsl:value-of select="$artifactText"/>
														</xsl:when>
														<xsl:when test="@TypeName = 'Deliverable'">
															<xsl:value-of select="$deliverableText"/>
														</xsl:when>
														<xsl:when test="@TypeName = 'Outcome'">
															<xsl:value-of select="$outcomeText"/>
														</xsl:when>
														<xsl:when test="@TypeName = 'Work Product Slot'">
															<xsl:value-of select="$workProductSlotText"/>
														</xsl:when>
														<xsl:when test="@TypeName = 'Task'">
															<xsl:value-of select="$taskText"/>
														</xsl:when>
														<xsl:when test="@TypeName = 'Role'">
															<xsl:value-of select="$roleText"/>
														</xsl:when>
														<xsl:when test="@TypeName = 'Checklist'">
															<xsl:value-of select="$checklistText"/>
														</xsl:when>
														<xsl:when test="@TypeName = 'Guideline'">
															<xsl:value-of select="$guidelineText"/>
														</xsl:when>
														<xsl:when test="@TypeName = 'Supporting Material'">
															<xsl:value-of select="$supportingMaterialText"/>
														</xsl:when>
														<xsl:when test="@TypeName = 'Term Definition'">
															<xsl:value-of select="$termDefinitionText"/>
														</xsl:when>
														<xsl:when test="@TypeName = 'Estimation Considerations'">
															<xsl:value-of select="$estimationConsiderationsText"/>
														</xsl:when>
														<xsl:when test="@TypeName = 'Example'">
															<xsl:value-of select="$exampleText"/>
														</xsl:when>
														<xsl:when test="@TypeName = 'Practice'">
															<xsl:value-of select="$practiceText"/>
														</xsl:when>
														<xsl:when test="@TypeName = 'Report'">
															<xsl:value-of select="$reportText"/>
														</xsl:when>
														<xsl:when test="@TypeName = 'Reusable Asset'">
															<xsl:value-of select="$reusableAssetText"/>
														</xsl:when>
														<xsl:when test="@TypeName = 'Template'">
															<xsl:value-of select="$templateText"/>
														</xsl:when>
														<xsl:when test="@TypeName = 'Tool Mentor'">
															<xsl:value-of select="$toolMentorText"/>
														</xsl:when>
														<xsl:otherwise>
															<xsl:value-of select="@TypeName"/>
														</xsl:otherwise>	
													</xsl:choose>
													<xsl:value-of select="$colon_with_space"/>
													<xsl:value-of select="@DisplayName"/>
												</a>
											</li>
										</xsl:for-each>
									</ul>
									<p/>
								</xsl:if>
							</xsl:for-each>													
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

	<xsl:template name="summaryOverview">
		<xsl:param name="elementType"/>
		<xsl:param name="elementTypeName"/>
		<xsl:param name="elementPresentationName"/>
		<xsl:param name="elementIcon"/>
		<xsl:variable name="briefDescription" select="attribute[@name='briefDescription']"/>		
		<xsl:variable name="synonyms" select="reference/Element/attribute[@name='synonyms']"/>
		<xsl:variable name="externalId" select="reference/Element/attribute[@name='externalId']"/>
		<xsl:variable name="imagePath" select="concat(/Element/@BackPath, 'images/')"/>
		
		<table border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td class="pageTitle">
					<xsl:value-of select="$summaryText"/>
					<xsl:value-of select="$colon_with_space"/>
					<!--【Browsing/Publishing】The strings in prictice and its sub group folder page is not translated by the skin resource-->
					<xsl:choose>
						<xsl:when test="$elementPresentationName = 'Work Products'">
							<xsl:value-of select="$workProductsText"/>
						</xsl:when>
						<xsl:when test="$elementPresentationName = 'Tasks'">
							<xsl:value-of select="$tasksText"/>
						</xsl:when>
						<xsl:when test="$elementPresentationName = 'Guidance'">
							<xsl:value-of select="$guidancesText"/>
						</xsl:when>
						<xsl:when test="$elementPresentationName = 'Roles'">
							<xsl:value-of select="$rolesText"/>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="$elementPresentationName"/>
						</xsl:otherwise>	
					</xsl:choose>
					<xsl:if test="$externalId != ''">
						(<xsl:value-of select="$externalId"/>)
					</xsl:if>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="pageTitleSeparator">
					<img src="{$imagePath}shim.gif" alt="" title="" height="1"/>
				</td>
			</tr>
		</table>		
	</xsl:template>

</xsl:stylesheet>
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

<xsl:output method="html" version="1.0" encoding="UTF-8" 
	doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" 
	doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" 
	indent="yes"/>
	
	<xsl:include href="resources.xsl"/>
	<xsl:include href="common.xsl"/>
	<xsl:include href="mapping.xsl"/>	
	<xsl:include href="overview.xsl"/>
	<xsl:include href="guidance_helper.xsl"/>
	<xsl:include href="main_description.xsl"/>
	<xsl:include href="escape.xsl"/>	
	<xsl:include href="extended_refs.xsl"/>
	<xsl:include href="extended_rtes.xsl"/>


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
		<xsl:variable name="tool" select="reference[@name='tool']/Element[@Type='Tool']"/>
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
				<meta name="element_type">
					<xsl:call-template name="mapping">
						<xsl:with-param name="elementType" select="$elementType"/>
					</xsl:call-template>
				</meta>
				<xsl:if test="$elementType = 'ToolMentor'">
					<meta name="tool" content="{$tool/@DisplayName}"/>
				</xsl:if>
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
							<xsl:call-template name="overview">
								<xsl:with-param name="elementType" select="$elementType"/>
								<xsl:with-param name="elementTypeName" select="$elementTypeName"/>
								<xsl:with-param name="elementPresentationName" select="$elementPresentationName"/>
								<xsl:with-param name="elementIcon" select="$shapeImage"/>
								<xsl:with-param name="backPath" select="$backPath"/>
								<xsl:with-param name="showTreeBrowser" select="$showTreeBrowser"/>
							</xsl:call-template>
							<xsl:call-template name="relationshipsSection"/>
							<xsl:call-template name="extendedRefsSectionsAll">
								<xsl:with-param name="elementDown" select="/Element"/>
								<xsl:with-param name="layoutLocation" select="''"/>
							</xsl:call-template>
							<xsl:call-template name="mainDescriptionSection">
								<xsl:with-param name="description" select="$contentDescription"/>
							</xsl:call-template>
							<xsl:call-template name="extendedRtesSectionsAll">
								<xsl:with-param name="descriptionDown" select="$contentDescription"/>
								<xsl:with-param name="layoutLocation" select="''"/>
							</xsl:call-template>
							<xsl:call-template name="descriptionSection">
								<xsl:with-param name="description" select="$contentDescription"/>
							</xsl:call-template>
							<xsl:call-template name="moreInfoSection">
								<xsl:with-param name="referenceList" select="referenceList"/>
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

	<xsl:template name="relationshipsSection">
	    	<xsl:variable name="practices" select="referenceList[@name='Practices']/Element"/>
		<xsl:variable name="contentElements" select="referenceList[@name='contentElements']/Element"/>
		<xsl:variable name="categories" select="referenceList[@name='ContentElement_CustomCategories']/Element"/>
		<xsl:if test="count($practices) + count($contentElements) + count($categories) > 0">
			<div class="sectionHeading">
				<xsl:value-of select="$relationshipsText"/>
			</div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
				
				<xsl:call-template name="showParentPractices"></xsl:call-template> 		
				<xsl:if test="count($contentElements) + count($categories) > 0">
					<xsl:call-template name="addReferences">
						<xsl:with-param name="refName" select="$categoriesText"/>
						<xsl:with-param name="refElement" select="$categories"/>
					</xsl:call-template>
					<tr valign="top">
						<th class="sectionTableHeading" scope="row">
							<xsl:value-of select="$relatedElementsText"/>
						</th>
						<td class="sectionTableCell">
							<ul>
								<xsl:for-each select="$contentElements">
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
				</table>
			</div>
		</xsl:if>
	</xsl:template>

	<xsl:template name="descriptionSection">
		<xsl:param name="description"/>	
		<xsl:variable name="attachments" select="$description/attribute[@name='attachments']"/>				

		<xsl:if test="$attachments != ''">
			<div class="sectionHeading"><xsl:value-of select="$descriptionText"/></div>
			<div class="sectionContent">			
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">		
					<xsl:if test="$attachments != ''">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row"><xsl:value-of select="$attachedFilesText"/></th>
							<td class="sectionTableCell">
							<UL>
								<xsl:for-each select="attribute[@name='attachedFile']">
								<xsl:sort select="@fileName"/>
								<li>
									<a href="{@url}" target="_blank"><xsl:value-of select="@fileName"/></a>
								</li>
								</xsl:for-each>
							</UL>
							</td>
						</tr>
					</xsl:if>				
				</table>
			</div>
		</xsl:if>
	</xsl:template>

	<xsl:template name="moreInfoSection">
		<!-- use the specific named list,  otherwise, the variability base element will be included, bug 163885 -->
		<xsl:variable name="checklists" select="referenceList[@name ='checklists']/Element[@Type='Checklist']"/>
		<xsl:variable name="concepts" select="referenceList[@name ='conceptsAndPapers']/Element[@Type='Concept']"/>
		<xsl:variable name="guidelines" select="referenceList[@name ='guidelines']/Element[@Type='Guideline']"/>
		<xsl:variable name="supportingMaterials" select="referenceList[@name ='supportingMaterials']/Element"/>
		<xsl:variable name="toolMentors" select="referenceList[@name !='contentElements']/Element[@Type='ToolMentor']"/>
		<xsl:variable name="whitePapers" select="referenceList[@name ='conceptsAndPapers']/Element[@Type='Whitepaper']"/>
		<xsl:variable name="examples" select="referenceList[@name ='examples']/Element[@Type='Example']"/>
		<xsl:variable name="reusableAssets" select="referenceList[@name ='assets']/Element[@Type='ReusableAsset']"/>
		<xsl:if test="count($checklists) + count($concepts) + count($guidelines) + count($supportingMaterials) + count($toolMentors) + count($whitePapers) + count($reusableAssets) + count($examples) > 0">
			<div class="sectionHeading">
				<xsl:value-of select="$moreInfoText"/>
			</div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					<xsl:call-template name="addChecklists">
						<xsl:with-param name="checklists" select="$checklists"/>
					</xsl:call-template>
					<xsl:call-template name="addConcepts">
						<xsl:with-param name="concepts" select="$concepts"/>
					</xsl:call-template>
					<xsl:call-template name="addExamples">
						<xsl:with-param name="examples" select="$examples"/>
					</xsl:call-template>
					<xsl:call-template name="addGuidelines">
						<xsl:with-param name="guidelines" select="$guidelines"/>
					</xsl:call-template>
					<xsl:call-template name="addReusableAssets">
						<xsl:with-param name="reusableAssets" select="$reusableAssets"/>
					</xsl:call-template>
					<xsl:call-template name="addSupportingMaterials">
						<xsl:with-param name="supportingMaterials" select="$supportingMaterials"/>
					</xsl:call-template>
					<xsl:call-template name="addToolMentors">
						<xsl:with-param name="toolMentors" select="$toolMentors"/>
					</xsl:call-template>
					<xsl:call-template name="addWhitePapers">
						<xsl:with-param name="whitePapers" select="$whitePapers"/>
					</xsl:call-template>
				</table>
			</div>
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>

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
	<xsl:include href="overview.xsl"/>
	<xsl:include href="main_description.xsl"/>
	<xsl:include href="key_consideration.xsl"/>
	<xsl:include href="property.xsl"/>
	<xsl:include href="guidance_helper.xsl"/>
	<xsl:include href="illustration.xsl"/>

	<xsl:template match="/Element">
		<xsl:variable name="elementType" select="@Type"/>
		<xsl:variable name="elementTypeName" select="@TypeName"/>
		<xsl:variable name="elementName" select="@Name"/>
		<xsl:variable name="elementPresentationName" select="@DisplayName"/>
		<xsl:variable name="backPath" select="@BackPath"/>
		<xsl:variable name="imagePath" select="concat($backPath, 'images/')"/>
	    <xsl:variable name="shapeImage" select="concat($backPath,@ShapeiconUrl)"/>
		<xsl:variable name="presentation" select="reference[@name='presentation']"/>
		<xsl:variable name="contentDescription" select="$presentation/Element[@Type='Milestone']"/>
		<xsl:variable name="milestoneDescription" select="$presentation/Element[@Type='Milestone']"/>
		<xsl:variable name="descriptorDescription" select="$presentation/Element[@Type='BreakdownElementDescription']"/>		
		<xsl:variable name="copyright" select="copyright"/>
		<xsl:variable name="showTreeBrowser" select="@showTreeBrowser"/>
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
				<xsl:if test="$tagValues!=''">
				    <meta name="tags" content="{$tagValues}"/>
				</xsl:if>
				<link rel="StyleSheet" href="{$backPath}css/default.css" type="text/css"/>
				<script src="{$backPath}scripts/ContentPageResource.js" type="text/javascript" language="JavaScript"></script>
				<script src="{$backPath}scripts/ContentPageSection.js" type="text/javascript" language="JavaScript"></script>
				<script src="{$backPath}scripts/ContentPageSubSection.js" type="text/javascript" language="JavaScript"></script>
				<script src="{$backPath}scripts/ActivityTreeTable.js" type="text/javascript" language="JavaScript"></script>
				<script src="{$backPath}scripts/ProcessElementPage.js" type="text/javascript" language="JavaScript"></script>
				<script src="{$backPath}scripts/ContentPageToolbar.js" type="text/javascript" language="JavaScript"></script>
				<script src="{$backPath}scripts/contentPage.js" type="text/javascript" language="JavaScript"></script>
				<script src="{$backPath}scripts/processElementData.js" type="text/javascript" language="JavaScript"></script>
				<script type="text/javascript" language="JavaScript">
					var backPath = '<xsl:value-of select="$backPath"/>';
					var imgPath = '<xsl:value-of select="$imagePath"/>';
					var nodeInfo=null;
					contentPage.preload(imgPath, backPath, nodeInfo,  '', false, true, false);
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
							<xsl:call-template name="overview">
								<xsl:with-param name="elementType" select="$elementType"/>
								<xsl:with-param name="elementTypeName" select="$elementTypeName"/>
								<xsl:with-param name="elementPresentationName" select="$elementPresentationName"/>
								<xsl:with-param name="elementIcon" select="$shapeImage"/>
								<xsl:with-param name="backPath" select="$backPath"/>
								<xsl:with-param name="showTreeBrowser" select="$showTreeBrowser"/>
							</xsl:call-template>
							<xsl:call-template name="mainDescriptionSection">
								<xsl:with-param name="description" select="$descriptorDescription"/>
							</xsl:call-template>
							<xsl:call-template name="propertiesSection">
								<xsl:with-param name="contentDescription" select="$contentDescription"/>
							</xsl:call-template>
							<xsl:call-template name="usageSection">
								<xsl:with-param name="contentDescription" select="$descriptorDescription"/>
							</xsl:call-template>							
							<xsl:call-template name="illustrationsSection"/>
							<xsl:call-template name="keyConsiderationsSection">
								<xsl:with-param name="description" select="$descriptorDescription"/>
							</xsl:call-template>					
							<xsl:call-template name="moreInfoSection"/>	
							<xsl:call-template name="relationshipsSection"/>	
							<xsl:call-template name="copyright">
								<xsl:with-param name="copyright" select="$copyright"/>
							</xsl:call-template>
						</td>						
					</tr>
				</table>
			</body>
				<script language="JavaScript" type="text/javascript">
					contentPage.onload();
				</script>
		</html>
	</xsl:template>

	<xsl:template name="propertiesSection">
		<xsl:param name="contentDescription"/>
		<div class="sectionHeading"><xsl:value-of select="$propertiesText"/></div>
		<div class="sectionContent">
			<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
			    <xsl:if test="referenceList[@name='linkToPredecessor']/Element">
					<xsl:call-template name="addReferences">
						<xsl:with-param name="refName" select="$predecessorText"/>
						<xsl:with-param name="refElement" select="referenceList[@name='linkToPredecessor']/Element"/>
					</xsl:call-template>
				</xsl:if>
				<xsl:if test="attribute[@name='PlanningData']">
					<xsl:call-template name="property">
						<xsl:with-param name="fieldLabel" select="$planningDataText"/>
						<xsl:with-param name="fieldText" select="attribute[@name='PlanningData']"/>
					</xsl:call-template>
				</xsl:if>
				<xsl:call-template name="property">
					<xsl:with-param name="fieldLabel" select="$multipleOccurrencesText"/>
					<xsl:with-param name="fieldText" select="attribute[@name='hasMultipleOccurrences']"/>
				</xsl:call-template>
				<xsl:call-template name="property">
					<xsl:with-param name="fieldLabel" select="$eventDrivenText"/>
					<xsl:with-param name="fieldText" select="attribute[@name='isEventDriven']"/>
				</xsl:call-template>
				<xsl:if test="attribute[@name='isOngoing']">
					<xsl:call-template name="property">
						<xsl:with-param name="fieldLabel" select="$ongoingText"/>
						<xsl:with-param name="fieldText" select="attribute[@name='isOngoing']"/>
					</xsl:call-template>
				</xsl:if>
				<xsl:if test="attribute[@name='isOptional']">
					<xsl:call-template name="property">
						<xsl:with-param name="fieldLabel" select="$optionalText"/>
						<xsl:with-param name="fieldText" select="attribute[@name='isOptional']"/>
					</xsl:call-template>
				</xsl:if>
				<xsl:if test="attribute[@name='isPlanned']">
					<xsl:call-template name="property">
						<xsl:with-param name="fieldLabel" select="$plannedText"/>
						<xsl:with-param name="fieldText" select="attribute[@name='isPlanned']"/>
					</xsl:call-template>
				</xsl:if>				
				<xsl:if test="attribute[@name='isRepeatable']">
					<xsl:call-template name="property">
						<xsl:with-param name="fieldLabel" select="$repeatableText"/>
						<xsl:with-param name="fieldText" select="attribute[@name='isRepeatable']"/>
					</xsl:call-template>
				</xsl:if>				
				<xsl:choose>
				    <xsl:when test="referenceList[@name='requiredResults']/Element">
					    <xsl:call-template name="addReferences">
						    <xsl:with-param name="refName" select="$requiredResultsText"/>
						    <xsl:with-param name="refElement" select="referenceList[@name='requiredResults']/Element"/>
					    </xsl:call-template>
				    </xsl:when>
				    <xsl:otherwise>
					    <xsl:call-template name="property">
						    <xsl:with-param name="fieldLabel" select="$requiredResultsText"/>
						    <xsl:with-param name="fieldText" select="'&#160;'"/>
					    </xsl:call-template>
				    </xsl:otherwise>
				</xsl:choose>  
			</table>
		</div>
	</xsl:template>

	<xsl:template name="usageSection">
		<xsl:param name="contentDescription"/>
		<xsl:if test="$contentDescription/attribute[@name='usageGuidance'] != ''">
			<div class="sectionHeading"><xsl:value-of select="$usageText"/></div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					<xsl:call-template name="property">
						<xsl:with-param name="fieldLabel" select="$usageGuidanceText"/>
						<xsl:with-param name="fieldText" select="$contentDescription/attribute[@name='usageGuidance']"/>
					</xsl:call-template>					
				</table>
			</div>
		</xsl:if>
	</xsl:template>
	
	<xsl:template name="moreInfoSection">
		<xsl:variable name="checklists" select="referenceList/Element[@Type='Checklist']"/>
		<xsl:variable name="concepts" select="referenceList/Element[@Type='Concept']"/>
		<xsl:variable name="guidelines" select="referenceList/Element[@Type='Guideline']"/>
		<xsl:variable name="supportingMaterials" select="referenceList/Element[@Type='SupportingMaterial']"/>
		<xsl:variable name="whitePapers" select="referenceList/Element[@Type='Whitepaper']"/>
		<xsl:variable name="udts" select="referenceList[@name='User defined type references']/Element[@Type='udt']"/>
		
		<xsl:if test="count($checklists) + count($concepts) + count($guidelines) + count($supportingMaterials) + count($whitePapers) + count($udts) > 0">
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
					<xsl:call-template name="addGuidelines">
						<xsl:with-param name="guidelines" select="$guidelines"/>
					</xsl:call-template>
					<xsl:call-template name="addSupportingMaterials">
						<xsl:with-param name="supportingMaterials" select="$supportingMaterials"/>
					</xsl:call-template>
					<xsl:call-template name="addWhitePapers">
						<xsl:with-param name="whitePapers" select="$whitePapers"/>
					</xsl:call-template>
					<xsl:call-template name="addUdts">
						<xsl:with-param name="udts" select="$udts"/>
					</xsl:call-template>					
				</table>
			</div>
		</xsl:if>
	</xsl:template>

	<xsl:template name="relationshipsSection">
		<xsl:variable name="categories" select="referenceList[@name='ContentElement_CustomCategories']/Element"/>
		<xsl:if test="count($categories) > 0">
			<div class="sectionHeading">
				<xsl:value-of select="$relationshipsText"/>
			</div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					<xsl:call-template name="addReferences">
						<xsl:with-param name="refName" select="$categoriesText"/>
						<xsl:with-param name="refElement" select="$categories"/>
					</xsl:call-template>
				</table>
			</div>
		</xsl:if>
	</xsl:template>	
					
					
</xsl:stylesheet>

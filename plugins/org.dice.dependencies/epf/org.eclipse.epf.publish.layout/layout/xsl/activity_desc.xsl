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
	
	<xsl:include href="activity_helper.xsl"/>	
	<xsl:include href="property.xsl"/>

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
		<xsl:variable name="queryString" select="@queryString"/>
		<xsl:variable name="relProcessPath" select="@relProcessPath"/>
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
				<link rel="StyleSheet" href="{$backPath}css/default.css" type="text/css"/>
				<script src="{$backPath}scripts/ContentPageResource.js" type="text/javascript" language="JavaScript"/>
				<script src="{$backPath}scripts/ContentPageSection.js" type="text/javascript" language="JavaScript"/>
				<script src="{$backPath}scripts/ContentPageSubSection.js" type="text/javascript" language="JavaScript"/>
				<script src="{$backPath}scripts/ActivityTreeTable.js" type="text/javascript" language="JavaScript"/>
				<script src="{$backPath}scripts/ProcessElementPage.js" type="text/javascript" language="JavaScript"/>
				<script src="{$backPath}scripts/ContentPageToolbar.js" type="text/javascript" language="JavaScript"/>
				<script src="{$backPath}scripts/contentPage.js" type="text/javascript" language="JavaScript"/>
				<script src="{$backPath}scripts/processElementData.js" type="text/javascript" language="JavaScript"/>
				<script type="text/javascript" language="JavaScript">
					var defaultQueryStr = '<xsl:value-of select="$queryString"/>';
					var backPath = '<xsl:value-of select="$backPath"/>';
					var imgPath = '<xsl:value-of select="$imagePath"/>';
					var nodeInfo=null;
					contentPage.preload(imgPath, backPath, nodeInfo, defaultQueryStr, false, true, false);
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
							<xsl:call-template name="overview">
								<xsl:with-param name="elementType" select="$elementType"/>
								<xsl:with-param name="elementTypeName" select="$elementTypeName"/>
								<xsl:with-param name="elementPresentationName" select="$elementPresentationName"/>
								<xsl:with-param name="elementIcon" select="$shapeImage"/>
								<xsl:with-param name="backPath" select="$backPath"/>
								<xsl:with-param name="showTreeBrowser" select="$showTreeBrowser"/>
							</xsl:call-template>
							<xsl:call-template name="activityTabs"/>
							<xsl:call-template name="generalTextFieldSection">
								<xsl:with-param name="fieldLabel" select="$scopeText"/>
								<xsl:with-param name="fieldText" select="$contentDescription/attribute[@name='scope']"/>
							</xsl:call-template>
							<xsl:call-template name="generalTextFieldSection">
								<xsl:with-param name="fieldLabel" select="$purposeText"/>
								<xsl:with-param name="fieldText" select="$contentDescription/attribute[@name='purpose']"/>
							</xsl:call-template>
							<xsl:if test="$elementType = 'DeliveryProcess' ">
								<xsl:call-template name="projectSpecificsSection">
									<xsl:with-param name="contentDescription" select="$contentDescription"/>
								</xsl:call-template>
							</xsl:if>
							<xsl:call-template name="relationshipsSection"/>
							
							<!-- display custom references in the Activity description page -->
							<xsl:call-template name="extendedRefsSectionsAll">
								<xsl:with-param name="elementDown" select="/Element"/>
								<xsl:with-param name="iconLevel" select="'two'"/>
								<xsl:with-param name="layoutLocation" select="''"/>
							</xsl:call-template>
							<!-- display custom RTEs in the Activity description page -->
							<xsl:call-template name="extendedRtesSectionsAll">
								<xsl:with-param name="descriptionDown" select="/Element/reference[@name='presentation']/Element"/>
								<xsl:with-param name="layoutLocation" select="''"/>
							</xsl:call-template>
							
							<xsl:call-template name="generalTextFieldSection">
								<xsl:with-param name="fieldLabel" select="$descriptionText"/>
								<xsl:with-param name="fieldText" select="$contentDescription/attribute[@name='mainDescription']"/>
							</xsl:call-template>
							<xsl:call-template name="propertiesSection">
								<xsl:with-param name="contentDescription" select="$contentDescription"/>
							</xsl:call-template>
							<xsl:call-template name="IllustrationsSection"/>
							<xsl:call-template name="generalTextFieldSection">
								<xsl:with-param name="fieldLabel" select="$staffingText"/>
								<xsl:with-param name="fieldText" select="$contentDescription/attribute[@name='howtoStaff']"/>
							</xsl:call-template>
							<xsl:call-template name="usageSection">
								<xsl:with-param name="contentDescription" select="$contentDescription"/>
							</xsl:call-template>
							<xsl:call-template name="generalTextFieldSection">
								<xsl:with-param name="fieldLabel" select="$keyConsiderationsText"/>
								<xsl:with-param name="fieldText" select="$contentDescription/attribute[@name='keyConsiderations']"/>
							</xsl:call-template>
							<xsl:call-template name="generalTextFieldSection">
								<xsl:with-param name="fieldLabel" select="$alternativesText"/>
								<xsl:with-param name="fieldText" select="$contentDescription/attribute[@name='alternatives']"/>
							</xsl:call-template>
							<xsl:call-template name="moreInfoSection">
								<xsl:with-param name="contentDescription" select="$contentDescription"/>
							</xsl:call-template>
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

	<xsl:template name="activityTabs">
		<xsl:variable name="imagePath" select="concat(/Element/@BackPath, 'images/')"/>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr valign="middle">
				<td width="10" class="activeTab">
					<img src="{$imagePath}shim.gif" width="10" height="17" alt="" title=""/>
				</td>
				<td nowrap="nowrap" class="activeTab">
					<xsl:value-of select="$descriptionText"/>
				</td>
				<td width="21">
					<img src="{$imagePath}tab_middle-a_i.gif" width="21" height="17" align="absmiddle" alt="" title=""/>
				</td>
				<td nowrap="nowrap" class="tab">
					<a class="tab" id="TAB_WBS">
						<xsl:call-template name="tabUrlAttribute">
							<xsl:with-param name="url" select="concat(/Element/@BackPath, tabs/tab[@name='WBS']/@url)"/>
						</xsl:call-template>
						<span style="white-space:nowrap;">
							<xsl:value-of select="$wbsText"/>
						</span>
					</a>
				</td>
				<td width="1">
					<img src="{$imagePath}tab_middle-i_i.gif" width="21" height="17" align="absmiddle" alt="" title=""/>
				</td>
				<td nowrap="nowrap" class="tab">
					<a class="tab" id="TAB_TBS">
						<xsl:call-template name="tabUrlAttribute">
							<xsl:with-param name="url" select="concat(/Element/@BackPath, tabs/tab[@name='TBS']/@url)"/>
						</xsl:call-template>
						<span style="white-space:nowrap;">
							<xsl:value-of select="$tbsText"/>
						</span>
					</a>
				</td>
				<td width="21">
					<img src="{$imagePath}tab_middle-i_i.gif" width="21" height="17" align="absmiddle" alt="" title=""/>
				</td>
				<td nowrap="nowrap" class="tab">
					<a class="tab" id="TAB_WPBS">
						<xsl:call-template name="tabUrlAttribute">
							<xsl:with-param name="url" select="concat(/Element/@BackPath, tabs/tab[@name='WPBS']/@url)"/>
						</xsl:call-template>
						<span style="white-space:nowrap;">
							<xsl:value-of select="$wpbsText"/>
						</span>
					</a>
				</td>
				<td width="21">
					<img src="{$imagePath}tab_end-i.gif" width="21" height="17" alt="" title=""/>
				</td>
				<td width="50%">
					<img src="{$imagePath}shim.gif" width="10" height="17" alt="" title=""/>
				</td>
			</tr>
			<tr>
				<td colspan="2" class="activeTab">
					<img src="{$imagePath}shim.gif" height="2" alt="" title=""/>
				</td>
				<td class="activeTab">
					<img src="{$imagePath}tab_space.gif" width="21" height="2" alt="" title=""/>
				</td>
				<td colspan="2">
					<img src="{$imagePath}shim.gif" height="2" alt="" title=""/>
				</td>
			</tr>
			<tr>
				<td colspan="10" class="activeTab">
					<img src="{$imagePath}shim.gif" height="5" alt="" title=""/>
				</td>
			</tr>
		</table>
	</xsl:template>

	<xsl:template name="IllustrationsSection">
		<xsl:if test="count(referenceList[@name='roadmaps']/Element) + count(referenceList[@name='examples']/Element) > 0">
			<div class="sectionHeading">
				<xsl:value-of select="$illustrationsText"/>
			</div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					<xsl:call-template name="addReferences">
						<xsl:with-param name="refName" select="$roadmapText"/>
						<xsl:with-param name="refElement" select="referenceList[@name='roadmaps']/Element"/>
					</xsl:call-template>
					<xsl:call-template name="addReferences">
						<xsl:with-param name="refName" select="$examplesText"/>
						<xsl:with-param name="refElement" select="referenceList[@name='examples']/Element"/>
					</xsl:call-template>
				</table>
			</div>
		</xsl:if>
	</xsl:template>

	<xsl:template name="projectSpecificsSection">
		<xsl:param name="contentDescription"/>
		<xsl:if test="$contentDescription/attribute[@name='typeOfContract'] != '' or $contentDescription/attribute[@name='projectCharacteristics'] != '' or $contentDescription/attribute[@name='projectMemberExpertise'] != '' or $contentDescription/attribute[@name='riskLevel'] != '' or $contentDescription/attribute[@name='scale'] != ''">
			<div class="sectionHeading">
				<xsl:value-of select="$projectSpecificsText"/>
			</div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					<xsl:call-template name="property">
						<xsl:with-param name="fieldLabel" select="$typeofContractText"/>
						<xsl:with-param name="fieldText" select="$contentDescription/attribute[@name='typeOfContract']"/>
					</xsl:call-template>
					<xsl:call-template name="property">
						<xsl:with-param name="fieldLabel" select="$projectCharacteristicsText"/>
						<xsl:with-param name="fieldText" select="$contentDescription/attribute[@name='projectCharacteristics']"/>
					</xsl:call-template>
					<xsl:call-template name="property">
						<xsl:with-param name="fieldLabel" select="$projectMemberExpertiseText"/>
						<xsl:with-param name="fieldText" select="$contentDescription/attribute[@name='projectMemberExpertise']"/>
					</xsl:call-template>
					<xsl:call-template name="property">
						<xsl:with-param name="fieldLabel" select="$estimatingTechniqueText"/>
						<xsl:with-param name="fieldText" select="$contentDescription/attribute[@name='estimatingTechnique']"/>
					</xsl:call-template>
					<xsl:call-template name="property">
						<xsl:with-param name="fieldLabel" select="$riskLevelText"/>
						<xsl:with-param name="fieldText" select="$contentDescription/attribute[@name='riskLevel']"/>
					</xsl:call-template>
					<xsl:call-template name="property">
						<xsl:with-param name="fieldLabel" select="$scaleText"/>
						<xsl:with-param name="fieldText" select="$contentDescription/attribute[@name='scale']"/>
					</xsl:call-template>
				</table>
			</div>
		</xsl:if>
	</xsl:template>

	<xsl:template name="usageSection">
		<xsl:param name="contentDescription"/>
		<xsl:if test="$contentDescription/attribute[@name='usageGuidance'] != '' or $contentDescription/attribute[@name='usageNotes'] != ''">
			<div class="sectionHeading">
				<xsl:value-of select="$usageText"/>
			</div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					<xsl:call-template name="property">
						<xsl:with-param name="fieldLabel" select="$usageGuidanceText"/>
						<xsl:with-param name="fieldText" select="$contentDescription/attribute[@name='usageGuidance']"/>
					</xsl:call-template>
					<xsl:call-template name="property">
						<xsl:with-param name="fieldLabel" select="$usageNotesText"/>
						<xsl:with-param name="fieldText" select="$contentDescription/attribute[@name='usageNotes']"/>
					</xsl:call-template>
				</table>
			</div>
		</xsl:if>
	</xsl:template>

	<xsl:template name="relationshipsSection">
		    <xsl:variable name="practices" select="referenceList[@name='Practices']/Element"/>
		<xsl:variable name="categories" select="referenceList[@name='ContentElement_CustomCategories']/Element"/>
		<xsl:if test="count($practices) + count($categories) + count(referenceList[@name='validContext']/Element) + count(reference[@name='superActivities']/Element) + count(referenceList[@name='includesPatterns']/Element) > 0">
			<div class="sectionHeading">
				<xsl:value-of select="$relationshipsText"/>
			</div>	
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">	
					<xsl:call-template name="showParentPractices"></xsl:call-template> 
					<xsl:call-template name="addReferences">
						<xsl:with-param name="refName" select="$categoriesText"/>
						<xsl:with-param name="refElement" select="$categories"/>
					</xsl:call-template>
					<xsl:choose>
						<xsl:when test="count(referenceList[@name='validContext']/Element) > 0">
							<xsl:call-template name="addcontexts">
								<xsl:with-param name="refName" select="$contextText"/>
								<xsl:with-param name="refElement" select="referenceList[@name='validContext']/Element"/>
							</xsl:call-template>
						</xsl:when>
						<xsl:otherwise>
							<xsl:call-template name="addcontexts">
								<xsl:with-param name="refName" select="$contextText"/>
								<xsl:with-param name="refElement" select="reference[@name='defaultContext']/Element"/>
							</xsl:call-template>
						</xsl:otherwise>
					</xsl:choose>
					<xsl:call-template name="addReferences">
						<xsl:with-param name="refName" select="$parentActivitiesText"/>
						<xsl:with-param name="refElement" select="reference[@name='superActivities']/Element"/>
					</xsl:call-template>
					<!-- 
					<xsl:call-template name="addReferences">
						<xsl:with-param name="refName" select="$includedPatternsText"/>
						<xsl:with-param name="refElement" select="referenceList[@name='includesPatterns']/Element"/>
					</xsl:call-template>
					 -->
				</table>
			</div>
		</xsl:if>
	</xsl:template>

	<xsl:template name="propertiesSection">
		<xsl:param name="contentDescription"/>
		<div class="sectionHeading">
			<xsl:value-of select="$propertiesText"/>
		</div>
		<div class="sectionContent">
			<table class="sectionTable" border="0" cellspacing="0" cellpadding="0" >
				<xsl:attribute name="title"><xsl:value-of select="$propertiesText"/></xsl:attribute>
				<xsl:attribute name="summary"><xsl:value-of select="$propertiesText"/></xsl:attribute>
				<xsl:call-template name="property">
					<xsl:with-param name="fieldLabel" select="$eventDrivenText"/>
					<xsl:with-param name="fieldText" select="attribute[@name='isEventDriven']"/>
				</xsl:call-template>
				<xsl:call-template name="property">
					<xsl:with-param name="fieldLabel" select="$multipleOccurrencesText"/>
					<xsl:with-param name="fieldText" select="attribute[@name='hasMultipleOccurrences']"/>
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
				<xsl:if test="attribute[@name='PlanningData']">
					<xsl:call-template name="property">
						<xsl:with-param name="fieldLabel" select="$planningDataText"/>
						<xsl:with-param name="fieldText" select="attribute[@name='PlanningData']"/>
					</xsl:call-template>
				</xsl:if>
				<xsl:if test="referenceList[@name='linkToPredecessor']/Element">
					<xsl:call-template name="addReferences">
						<xsl:with-param name="fieldLabel" select="$predecessorText"/>
						<xsl:with-param name="elements" select="referenceList[@name='linkToPredecessor']/Element"/>
					</xsl:call-template>
				</xsl:if>
				<xsl:if test="attribute[@name='isRepeatable']">
					<xsl:call-template name="property">
						<xsl:with-param name="fieldLabel" select="$repeatableText"/>
						<xsl:with-param name="fieldText" select="attribute[@name='isRepeatable']"/>
					</xsl:call-template>
				</xsl:if>
			</table>
		</div>
	</xsl:template>

	<xsl:template name="generalTextFieldSection">
		<xsl:param name="fieldLabel"/>
		<xsl:param name="fieldText"/>
		<xsl:if test="$fieldText != ''">
			<div class="sectionHeading">
				<xsl:value-of select="$fieldLabel"/>
			</div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					<tr valign="top">
						<td class="sectionTableSingleCell">
							<xsl:value-of disable-output-escaping="yes" select="$fieldText"/>
						</td>
					</tr>
				</table>
			</div>
		</xsl:if>
	</xsl:template>

	<xsl:template name="moreInfoSection">
		<xsl:param name="contentDescription"/>
		<xsl:variable name="udts" select="referenceList[@name='User defined type references']/Element[@Type='udt']"/>
		
		<xsl:if test="count(referenceList[@name='concepts']/Element) + count(referenceList[@name='checklists']/Element) + count(referenceList[@name='communicationsMaterials']/Element) + count(referenceList[@name='guidelines']/Element) + count(referenceList[@name='supportingMaterials']/Element) + count(referenceList[@name='reusableAssets']/Element) + count($udts) > 0">
			<div class="sectionHeading">
				<xsl:value-of select="$moreInfoText"/>
			</div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0"> 
					<xsl:call-template name="addChecklists">
						<xsl:with-param name="checklists" select="referenceList[@name='checklists']/Element"/>
					</xsl:call-template>
					<xsl:if test="referenceList[@name='communicationsMaterials']">
						<xsl:call-template name="addReferences">
							<xsl:with-param name="refName" select="$communicationsMaterialsText"/>
							<xsl:with-param name="refElement" select="referenceList[@name='communicationsMaterials']/Element"/>
						</xsl:call-template>
					</xsl:if>
					<xsl:if test="referenceList[@name='educationMaterials']">
						<xsl:call-template name="addReferences">
							<xsl:with-param name="refName" select="$educationMaterialsText"/>
							<xsl:with-param name="refElement" select="referenceList[@name='educationMaterials']/Element"/>
						</xsl:call-template>
					</xsl:if>
					<xsl:call-template name="addGuidelines">
						<xsl:with-param name="guidelines" select="referenceList[@name='guidelines']/Element"/>
					</xsl:call-template>
					<xsl:call-template name="addConcepts">
						<xsl:with-param name="concepts" select="referenceList/Element[@Type='Concept']"/>
					</xsl:call-template>
					<xsl:call-template name="addReusableAssets">
						<xsl:with-param name="reusableAssets" select="referenceList[@name='reusableAssets']/Element"/>
					</xsl:call-template>
					<xsl:call-template name="addReferences">
						<xsl:with-param name="refName" select="$supportingMaterialsText"/>
						<xsl:with-param name="refElement" select="referenceList[@name='supportingMaterials']/Element"/>
					</xsl:call-template>
					<xsl:call-template name="addWhitePapers">
						<xsl:with-param name="whitePapers" select="referenceList/Element[@Type='Whitepaper']"/>
					</xsl:call-template>
					<xsl:call-template name="addUdts">
						<xsl:with-param name="udts" select="$udts"/>
					</xsl:call-template>					
				</table>
			</div>
		</xsl:if>
	</xsl:template>

	<xsl:template name="addcontexts">
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
									<a>
										<xsl:value-of select="@DisplayName"/>
									</a>
								</li>
							</xsl:for-each>
						</ul>
					</td>
				</tr>
			</xsl:if>
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>

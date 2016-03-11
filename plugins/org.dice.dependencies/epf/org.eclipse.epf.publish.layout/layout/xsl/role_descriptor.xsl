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
	
	<xsl:include href="descriptor.xsl"/>
	<xsl:include href="guidance_helper.xsl"/>
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
		<xsl:variable name="contentDescription" select="$presentation/Element[@Type='RoleDescriptor']"/>
		<xsl:variable name="roleDescription" select="$presentation/Element[@Type='RoleDescription']"/>
		<xsl:variable name="descriptorDescription" select="$presentation/Element[@Type='DescriptorDescription']"/>
		<xsl:variable name="copyright" select="copyright"/>
		<xsl:variable name="showTreeBrowser" select="@showTreeBrowser"/>
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
				<meta name="element_type" content="{$elementType}"/>
				<meta name="filetype" content="description"/>
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
								<xsl:with-param name="iconLevel" select="'two'"/>
								<xsl:with-param name="layoutLocation" select="''"/>
							</xsl:call-template>
							<xsl:call-template name="refinedDescriptionSection">
								<xsl:with-param name="description" select="$descriptorDescription"/>
							</xsl:call-template>			
							<xsl:call-template name="extendedRtesSectionsAll">
								<xsl:with-param name="descriptionDown" select="$descriptorDescription"/>
								<xsl:with-param name="layoutLocation" select="''"/>
							</xsl:call-template>
							<xsl:call-template name="propertiesSection">
								<xsl:with-param name="contentDescription" select="$contentDescription"/>
							</xsl:call-template>
							<xsl:call-template name="usageSection">
								<xsl:with-param name="contentDescription" select="$descriptorDescription"/>
							</xsl:call-template>
							<xsl:call-template name="illustrationsSection"/>
							<xsl:call-template name="staffingSection"/>
							<xsl:call-template name="keyConsiderationsSection">
								<xsl:with-param name="description" select="$descriptorDescription"/>
							</xsl:call-template>
							<xsl:call-template name="moreInfoSection"/>
							<xsl:call-template name="copyright">
								<xsl:with-param name="copyright" select="$copyright"/>
							</xsl:call-template>
						</td>
					</tr>
				</table>
			</body>
			<script language="JavaScript" type="text/javascript">
				contentPage.onload();
				contentPage.processPage.fixDescriptorLinks();
			</script>
		</html>
	</xsl:template>

	<xsl:template name="propertiesSection">
		<xsl:param name="contentDescription"/>
		<div class="sectionHeading">
			<xsl:value-of select="$propertiesText"/>
		</div>
		<div class="sectionContent">
			<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
				<xsl:call-template name="property">
					<xsl:with-param name="fieldLabel" select="$multipleOccurrencesText"/>
					<xsl:with-param name="fieldText" select="attribute[@name='hasMultipleOccurrences']"/>
				</xsl:call-template>
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
						<xsl:with-param name="refName" select="$predecessorText"/>
						<xsl:with-param name="refElement" select="referenceList[@name='linkToPredecessor']/Element"/>
					</xsl:call-template>
				</xsl:if>
			</table>
		</div>
	</xsl:template>

	<xsl:template name="usageSection">
		<xsl:param name="contentDescription"/>
		<xsl:variable name="usageGuidance" select="$contentDescription/attribute[@name='usageGuidance']"/>
		<xsl:if test="$usageGuidance != ''">
			<div class="sectionHeading">
				<xsl:value-of select="$usageGuidanceText"/>
			</div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					<tr valign="top">
						<td class="sectionTableCell">
							<xsl:value-of disable-output-escaping="yes" select="$usageGuidance"/>
						</td>
					</tr>
				</table>
			</div>
		</xsl:if>
	</xsl:template>

	<xsl:template name="relationshipsSection">
		<xsl:variable name="performs" select="referenceList[@name='RoleDescriptor_PrimaryTaskDescriptors']/Element"/>
		<xsl:variable name="responsibleFor" select="referenceList[@name='responsibleFor']/Element"/>
		<xsl:variable name="additionallyPerforms" select="referenceList[@name='RoleDescriptor_AdditionalTaskDescriptors']/Element"/>
		<xsl:variable name="modifies" select="referenceList[@name='modifies']/Element"/>
		<div class="sectionHeading">
			<xsl:value-of select="$relationshipsText"/>
		</div>
		<div class="sectionContent">
			<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="sectionTableCell" valign="top">
						<b><span class="sectionTableCellHeading">
							<xsl:value-of select="$performsText"/>:</span></b>
						<xsl:if test="count($performs) > 0">
							<ul>
								<xsl:for-each select="$performs">
								<xsl:sort select="@DisplayName"/>
									<li>
										<a>
											<xsl:attribute name="href"><xsl:value-of select="/Element/@BackPath"/><xsl:value-of select="@Url"/></xsl:attribute>
											<xsl:value-of select="@DisplayName"/>
										</a>
									</li>
								</xsl:for-each>
							</ul>
						</xsl:if>
					</td>
					<td class="sectionTableCell" valign="top">
						<b><span class="sectionTableCellHeading">
							<xsl:value-of select="$responsibleForText"/>:</span></b>
						<xsl:if test="count($responsibleFor) > 0">
							<ul>
								<xsl:for-each select="$responsibleFor">
								<xsl:sort select="@DisplayName"/>
									<li>
										<a>
											<xsl:attribute name="href"><xsl:value-of select="/Element/@BackPath"/><xsl:value-of select="@Url"/></xsl:attribute>
											<xsl:value-of select="@DisplayName"/>
										</a>
									</li>
								</xsl:for-each>
							</ul>
						</xsl:if>
					</td>
				</tr>
				<tr>
					<td class="sectionTableCell" valign="top">
						<b><span class="sectionTableCellHeading">
							<xsl:value-of select="$additionallyPerformsText"/>:</span></b>
						<xsl:if test="count($additionallyPerforms) > 0">
							<ul>
								<xsl:for-each select="$additionallyPerforms">
								<xsl:sort select="@DisplayName"/>
									<li>
										<a>
											<xsl:attribute name="href"><xsl:value-of select="/Element/@BackPath"/><xsl:value-of select="@Url"/></xsl:attribute>
											<xsl:value-of select="@DisplayName"/>
										</a>
									</li>
								</xsl:for-each>
							</ul>
						</xsl:if>
					</td>
					<td class="sectionTableCell" valign="top">
						<b><span class="sectionTableCellHeading">
							<xsl:value-of select="$modifiesText"/>:</span></b>
						<xsl:if test="count($modifies) > 0">
							<ul>
								<xsl:for-each select="$modifies">
								<xsl:sort select="@DisplayName"/>
									<li>
										<a>
											<xsl:attribute name="href"><xsl:value-of select="/Element/@BackPath"/><xsl:value-of select="@Url"/></xsl:attribute>
											<xsl:value-of select="@DisplayName"/>
										</a>
									</li>
								</xsl:for-each>
							</ul>
						</xsl:if>
					</td>
				</tr>
			</table>
		</div>
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

	<xsl:template name="staffingSection">
		<xsl:variable name="skills" select="reference/Element/attribute[@name='skills']"/>
		<xsl:variable name="assignmentApproaches" select="reference/Element/attribute[@name='assignmentApproaches']"/>
		<xsl:variable name="synonyms" select="reference/Element/attribute[@name='synonyms']"/>
		<xsl:if test="$skills != '' or $assignmentApproaches != '' or $synonyms != '' ">
			<div class="sectionHeading">
				<xsl:value-of select="$staffingText"/>
			</div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					<xsl:if test="$skills != ''">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row">
								<xsl:value-of select="$skillsText"/>
							</th>
							<td class="sectionTableCell">
								<xsl:value-of disable-output-escaping="yes" select="$skills"/>
							</td>
						</tr>
					</xsl:if>
					<xsl:if test="$assignmentApproaches != ''">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row">
								<xsl:value-of select="$assignmentApproachesText"/>
							</th>
							<td class="sectionTableCell">
								<xsl:value-of disable-output-escaping="yes" select="$assignmentApproaches"/>
							</td>
						</tr>
					</xsl:if>
					<xsl:if test="$synonyms != ''">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row">
								<xsl:value-of select="$synonymsText"/>
							</th>
							<td class="sectionTableCell">
								<xsl:value-of disable-output-escaping="yes" select="$synonyms"/>
							</td>
						</tr>
					</xsl:if>
				</table>
			</div>
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>

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
	<xsl:include href="extended_refs.xsl"/>
	<xsl:include href="main_description.xsl"/>
	<xsl:include href="extended_rtes.xsl"/>
	<xsl:include href="illustration.xsl"/>
	<xsl:include href="key_consideration.xsl"/>
	<xsl:include href="guidance_helper.xsl"/>
	<xsl:include href="descriptor_helper.xsl"/>
	<xsl:include href="custom_opposite.xsl"/>

	<xsl:template match="/Element">
		<xsl:variable name="elementType" select="@Type"/>
		<xsl:variable name="elementTypeName" select="@TypeName"/>
		<xsl:variable name="elementName" select="@Name"/>
		<xsl:variable name="elementPresentationName" select="@DisplayName"/>
		<xsl:variable name="backPath" select="@BackPath"/>
		<xsl:variable name="imagePath" select="concat($backPath, 'images/')"/>
	    <xsl:variable name="shapeImage" select="concat($backPath,@ShapeiconUrl)"/>
		<xsl:variable name="presentation" select="reference[@name='presentation']"/>
		<xsl:variable name="roleDescription" select="$presentation/Element[@Type='RoleDescription']"/>
		<xsl:variable name="roleSets" select="referenceList[@name='roleSets']/Element[@Type='RoleSet']"/>
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
				<meta name="role" content="{$elementPresentationName}"/>
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
								<xsl:with-param name="iconLevel" select="'two'"/>
								<xsl:with-param name="layoutLocation" select="''"/>
							</xsl:call-template>
								<!--  next template call is an example for advanced skin customization -->
								<xsl:call-template name="customOppositeRelationshipsAll">
									<xsl:with-param name="elementDown" select="/Element"/>
									<xsl:with-param name="iconLevel" select="'two'"/>
									<xsl:with-param name="layoutLocation" select="'Reverse References'"/>
								</xsl:call-template>
							<xsl:call-template name="mainDescriptionSection">
								<xsl:with-param name="description" select="$roleDescription"/>
							</xsl:call-template>
							<xsl:call-template name="extendedRtesSectionsAll">
								<xsl:with-param name="descriptionDown" select="$roleDescription"/>
								<xsl:with-param name="layoutLocation" select="''"/>
							</xsl:call-template>
							<xsl:call-template name="illustrationsSection"/>
							<xsl:call-template name="staffingSection"/>
							<xsl:call-template name="keyConsiderationsSection">
								<xsl:with-param name="description" select="$roleDescription"/>
							</xsl:call-template>
							<xsl:call-template name="moreInfoSection"/>
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
		<xsl:variable name="Role_Primary_Tasks" select="referenceList[@name='Role_Primary_Tasks']/Element"/>
		<xsl:variable name="additionallyPerforms" select="referenceList[@name='additionallyPerforms']/Element"/>
		<xsl:variable name="workProductModifies" select="referenceList[@name='modifies']/Element[@Type!='WorkProductSlot']"/>
		<xsl:variable name="workProductSlotModifies" select="referenceList[@name='modifies']/Element[@Type='WorkProductSlot']"/>
		<xsl:variable name="categories" select="referenceList[@name='ContentElement_CustomCategories']/Element"/>
		<div class="sectionHeading"><xsl:value-of select="$relationshipsText"/></div>
		<div class="sectionContent">
			<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="sectionTableSingleCell" colspan="3" align="center">	
						<xsl:value-of disable-output-escaping="yes" select="diagram"/>
					</td>
				</tr>
				
				<xsl:call-template name="customOppositeRelationshipsEmbedded">
					<xsl:with-param name="elementDown" select="/Element"/>
					<xsl:with-param name="iconLevel" select="'two'"/>
					<xsl:with-param name="layoutLocation" select="''"/>
				</xsl:call-template>
				
				<xsl:call-template name="showParentPractices"></xsl:call-template> 	
					
				<xsl:call-template name="addReferences">
					<xsl:with-param name="refName" select="$categoriesText"/>
					<xsl:with-param name="refElement" select="$categories"/>
				</xsl:call-template>
				<xsl:call-template name="addReferences">
					<xsl:with-param name="refName" select="$primaryPerformsText"/>
					<xsl:with-param name="refElement" select="$Role_Primary_Tasks"/>
				</xsl:call-template>
				<xsl:call-template name="addReferences">
					<xsl:with-param name="refName" select="$additionallyPerformsText"/>
					<xsl:with-param name="refElement" select="$additionallyPerforms"/>
				</xsl:call-template>
					<!--  next template call is an example for advanced skin customization -->
					<xsl:call-template name="customOppositeRelationshipsEmbedded">
						<xsl:with-param name="elementDown" select="/Element"/>
						<xsl:with-param name="iconLevel" select="'two'"/>
						<xsl:with-param name="layoutLocation" select="'rel_middle'"/>
					</xsl:call-template>
				<xsl:if test="count($workProductModifies) + count($workProductSlotModifies) > 0">
					<tr valign="top">
						<th class="sectionTableHeading" scope="row"><xsl:value-of select="$modifiesText"/></th>
						<td class="sectionTableCell" colspan="2">
							<ul>
							<xsl:for-each select="$workProductModifies">
							<xsl:sort select="@DisplayName"/>
								<li>
									<a>
										<xsl:attribute name="href"><xsl:value-of select="/Element/@BackPath"/>
											<xsl:value-of select="@Url"/></xsl:attribute><xsl:value-of select="@DisplayName"/>
									</a>
								</li>
							</xsl:for-each>
							</ul>
							<ul>
							<xsl:for-each select="$workProductSlotModifies">
							<xsl:sort select="@DisplayName"/>
								<li>
									<a>
										<xsl:attribute name="href"><xsl:value-of select="/Element/@BackPath"/>
											<xsl:value-of select="@Url"/></xsl:attribute><xsl:value-of select="@DisplayName"/>
									</a>
									<ul>
									<xsl:for-each select="referenceList[@name='FulFills_FullFillableElements']/Element">
									<xsl:sort select="@DisplayName"/>
									<li>
										<a>
											<xsl:attribute name="href"><xsl:value-of select="/Element/@BackPath"/>
												<xsl:value-of select="@Url"/></xsl:attribute><xsl:value-of select="@DisplayName"/>
										</a>
									</li>
									</xsl:for-each>
									</ul>
								</li>
							</xsl:for-each>
							</ul>
						</td>
					</tr>
				</xsl:if>
				<xsl:call-template name="addDescriptors">
					<xsl:with-param name="descriptors" select="referenceList[@name='descriptors']/Element"/>
					<xsl:with-param name="colspan" select="'1'"/>
				</xsl:call-template>
					<!--  next template call is an example for advanced skin customization -->
					<xsl:call-template name="customOppositeRelationshipsEmbedded">
						<xsl:with-param name="elementDown" select="/Element"/>
						<xsl:with-param name="iconLevel" select="'two'"/>
						<xsl:with-param name="layoutLocation" select="'rel_tail'"/>
					</xsl:call-template>
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
			<div class="sectionHeading"><xsl:value-of select="$moreInfoText"/></div>
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
		
		<xsl:if test="$skills != '' or $assignmentApproaches != '' or $synonyms != '' " >
			<div class="sectionHeading"><xsl:value-of select="$staffingText"/></div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					<xsl:if test="$skills != ''">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row"><xsl:value-of select="$skillsText"/></th>
							<td class="sectionTableCell">
								<xsl:value-of disable-output-escaping="yes" select="$skills"/>
							</td>
						</tr>
					</xsl:if>
					<xsl:if test="$assignmentApproaches != ''">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row"><xsl:value-of select="$assignmentApproachesText"/></th>
							<td class="sectionTableCell">
								<xsl:value-of disable-output-escaping="yes" select="$assignmentApproaches"/>
							</td>
						</tr>
					</xsl:if>			
					<xsl:if test="$synonyms != ''">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row"><xsl:value-of select="$synonymsText"/></th>
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

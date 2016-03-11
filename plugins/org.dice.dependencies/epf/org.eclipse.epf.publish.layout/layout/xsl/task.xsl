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
	<xsl:include href="mapping.xsl"/>
	<xsl:include href="overview.xsl"/>
	<xsl:include href="purpose.xsl"/>
	<xsl:include href="extended_refs.xsl"/>
	<xsl:include href="main_description.xsl"/>
	<xsl:include href="extended_rtes.xsl"/>
	<xsl:include href="illustration.xsl"/>
	<xsl:include href="key_consideration.xsl"/>
	<xsl:include href="guidance_helper.xsl"/>
	<xsl:include href="descriptor_helper.xsl"/>
	
		
	<xsl:template match="/Element">
		<xsl:variable name="elementType" select="@Type"/>
		<xsl:variable name="elementTypeName" select="@TypeName"/>
		<xsl:variable name="elementName" select="@Name"/>
		<xsl:variable name="elementPresentationName" select="@DisplayName"/>
		<xsl:variable name="backPath" select="@BackPath"/>
		<xsl:variable name="imagePath" select="concat($backPath, 'images/')"/>
	    <xsl:variable name="shapeImage" select="concat($backPath,@ShapeiconUrl)"/>
		<xsl:variable name="presentation" select="reference[@name='presentation']"/>
		<xsl:variable name="taskDescription" select="$presentation/Element[@Type='TaskDescription']"/>
		<xsl:variable name="copyright" select="copyright"/>
		<xsl:variable name="showTreeBrowser" select="@showTreeBrowser"/>
		<xsl:variable name="performingRole" select="referenceList[@name='performedBy']/Element[@Type='Role']"/>
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
				<meta name="filetype" content="description"/>
				<meta name="role">
					<xsl:choose>  
						<xsl:when test="$performingRole/@DisplayName!=''">
							<xsl:attribute name="content"><xsl:value-of select="$performingRole/@DisplayName"/></xsl:attribute>
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
					contentPage.preload(imgPath, backPath, nodeInfo, '', true, false, false);
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
							<xsl:call-template name="purposeSection">
								<xsl:with-param name="description" select="$taskDescription"/>
							</xsl:call-template>
							<xsl:call-template name="relationshipsSection"/>
							<xsl:call-template name="extendedRefsSectionsAll">
								<xsl:with-param name="elementDown" select="/Element"/>
								<xsl:with-param name="iconLevel" select="'two'"/>
								<xsl:with-param name="layoutLocation" select="''"/>
							</xsl:call-template>
								<!--  next template call is an example for advanced skin customization -->
								<xsl:call-template name="extendedRtesSectionsAll">
									<xsl:with-param name="descriptionDown" select="$taskDescription"/>
									<xsl:with-param name="layoutLocation" select="'rte_group1'"/>
								</xsl:call-template>
							<xsl:call-template name="mainDescriptionSection">
								<xsl:with-param name="description" select="$taskDescription"/>
							</xsl:call-template>
								<!--  next template call is an example for advanced skin customization -->
								<xsl:call-template name="extendedRefsSectionsAll">
									<xsl:with-param name="elementDown" select="/Element"/>
									<xsl:with-param name="iconLevel" select="'two'"/>
									<xsl:with-param name="layoutLocation" select="'ref_group1'"/>
								</xsl:call-template>
							<xsl:call-template name="extendedRtesSectionsAll">
								<xsl:with-param name="descriptionDown" select="$taskDescription"/>
								<xsl:with-param name="layoutLocation" select="''"/>
							</xsl:call-template>
							<xsl:call-template name="stepsSection">
								<xsl:with-param name="description" select="$taskDescription"/>
							</xsl:call-template>	
							<xsl:call-template name="illustrationsSection"/>
							<xsl:call-template name="keyConsiderationsSection">
								<xsl:with-param name="description" select="$taskDescription"/>
							</xsl:call-template>
							<xsl:call-template name="alternativesSection">
								<xsl:with-param name="description" select="$taskDescription"/>
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
		<xsl:variable name="performedBy" select="referenceList[@name='performedBy']/Element"/>
		<xsl:variable name="additionallyPerformedBy" select="referenceList[@name='additionallyPerformedBy']/Element"/>
		<xsl:variable name="workProductMandatoryInputs" select="referenceList[@name='mandatoryInput']/Element[@Type!='WorkProductSlot']"/>
		<xsl:variable name="workProductSlotMandatoryInputs" select="referenceList[@name='mandatoryInput']/Element[@Type='WorkProductSlot']"/>
		<xsl:variable name="workProductOptionalInputs" select="referenceList[@name='optionalInput']/Element[@Type!='WorkProductSlot']"/>
		<xsl:variable name="workProductSlotOptionalInputs" select="referenceList[@name='optionalInput']/Element[@Type='WorkProductSlot']"/>
		<xsl:variable name="workProductOutputs" select="referenceList[@name='output']/Element[@Type!='WorkProductSlot']"/>
		<xsl:variable name="workProductSlotOutputs" select="referenceList[@name='output']/Element[@Type='WorkProductSlot']"/>
		<xsl:variable name="imagePath" select="concat(/Element/@BackPath, 'images/')"/>
		<xsl:variable name="categories" select="referenceList[@name='ContentElement_CustomCategories']/Element"/>

		<xsl:if test="count($practices) + count($performedBy) + count($categories) + count($additionallyPerformedBy) + count($workProductMandatoryInputs) + count($workProductSlotMandatoryInputs) + count($workProductOptionalInputs) + count($workProductSlotOptionalInputs) + count($workProductOutputs) + count($workProductSlotOutputs) > 0">
			<div class="sectionHeading"><xsl:value-of select="$relationshipsText"/></div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">			
					<xsl:call-template name="showParentPractices"></xsl:call-template> 						
					<xsl:if test="count($categories) > 0">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row"><xsl:value-of select="$categoriesText"/></th>
							<td class="sectionTableCell" colspan="2">
							<ul>
								<xsl:for-each select="$categories">
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
							<!--  next template call is an example for advanced skin customization -->
							<xsl:call-template name="extendedRefsSectionsEmbedded">
								<xsl:with-param name="elementDown" select="/Element"/>
								<xsl:with-param name="iconLevel" select="'two'"/>
								<xsl:with-param name="layoutLocation" select="'rel_head'"/>
							</xsl:call-template>
							
					<xsl:if test="count($performedBy) + count($additionallyPerformedBy) > 0">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row"><xsl:value-of select="$rolesText"/></th>
							<td class="sectionTableCell" width="42%">
								<span class="sectionTableCellHeading">
									<xsl:value-of select="$primaryPerformerText"/>:
								</span>
								<xsl:if test="count($performedBy) > 0">									
								<ul>
									<xsl:for-each select="$performedBy">
									<xsl:sort select="@DisplayName"/>
										<li>
											<a><xsl:attribute name="href"><xsl:value-of select="/Element/@BackPath"/><xsl:value-of select="@Url"/></xsl:attribute><xsl:value-of select="@DisplayName"/></a>
										</li>
									</xsl:for-each>
								</ul>
								</xsl:if>
							</td>
							<td class="sectionTableCell">
								<span class="sectionTableCellHeading">
									<xsl:value-of select="$additionalPerformerText"/>:
								</span>
								<xsl:if test="count($additionallyPerformedBy) > 0">									
								<ul>
									<xsl:for-each select="$additionallyPerformedBy">
									<xsl:sort select="@DisplayName"/>
										<li>
											<a><xsl:attribute name="href"><xsl:value-of select="/Element/@BackPath"/><xsl:value-of select="@Url"/></xsl:attribute><xsl:value-of select="@DisplayName"/></a>
										</li>
									</xsl:for-each>
								</ul>
								</xsl:if>
							</td>
						</tr>
					</xsl:if>
					<xsl:if test="count($workProductMandatoryInputs) + count($workProductSlotMandatoryInputs) + count($workProductOptionalInputs) + count($workProductSlotOptionalInputs) > 0">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row"><xsl:value-of select="$inputsText"/></th>
							<td class="sectionTableCell" width="42%">
								<span class="sectionTableCellHeading">
									<xsl:value-of select="$mandatoryText"/>:
								</span>
								<xsl:choose>
									<xsl:when test="count($workProductMandatoryInputs) + count($workProductSlotMandatoryInputs) > 0">
										<ul>
										<xsl:for-each select="$workProductMandatoryInputs">		
										<xsl:sort select="@DisplayName"/>								
											<li>
												<a><xsl:attribute name="href"><xsl:value-of select="/Element/@BackPath"/><xsl:value-of select="@Url"/></xsl:attribute><xsl:value-of select="@DisplayName"/></a>
											</li>
										</xsl:for-each>							
										</ul>
										<ul>
										<xsl:for-each select="$workProductSlotMandatoryInputs">
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
									</xsl:when>				
									<xsl:otherwise>
									<ul>
										<li>
											<xsl:value-of select="$noneText"/>
										</li>									
									</ul>
									</xsl:otherwise>
								</xsl:choose>
							</td>
							<td class="sectionTableCell">
								<span class="sectionTableCellHeading">
									<xsl:value-of select="$optionalText"/>:
								</span>
								<xsl:choose>
									<xsl:when test="count($workProductOptionalInputs) + count($workProductSlotOptionalInputs) > 0">
										<ul>
										<xsl:for-each select="$workProductOptionalInputs">				
										<xsl:sort select="@DisplayName"/>						
											<li>
												<a><xsl:attribute name="href"><xsl:value-of select="/Element/@BackPath"/><xsl:value-of select="@Url"/></xsl:attribute><xsl:value-of select="@DisplayName"/></a>
											</li>
										</xsl:for-each>							
										</ul>
										<ul>
										<xsl:for-each select="$workProductSlotOptionalInputs">
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
									</xsl:when>				
									<xsl:otherwise>
									<ul>
										<li>
											<xsl:value-of select="$noneText"/>
										</li>									
									</ul>
									</xsl:otherwise>
								</xsl:choose>
							</td>					
						</tr>
					</xsl:if>
					<xsl:if test="count($workProductOutputs) + count($workProductSlotOutputs) > 0">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row"><xsl:value-of select="$outputsText"/></th>
							<td class="sectionTableCell" colspan="2">
								<ul>
								<xsl:for-each select="$workProductOutputs">
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
								<xsl:for-each select="$workProductSlotOutputs">
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
						<xsl:with-param name="colspan" select="'2'"/>
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
		<xsl:variable name="toolMentors" select="referenceList/Element[@Type='ToolMentor']"/>
		<xsl:variable name="whitePapers" select="referenceList/Element[@Type='Whitepaper']"/>		
		<xsl:variable name="imagePath" select="concat(/Element/@BackPath, 'images/')"/>
		<xsl:variable name="estimationConsiderations" select="referenceList/Element[@Type='EstimationConsiderations']"/>
		<xsl:variable name="udts" select="referenceList[@name='User defined type references']/Element[@Type='udt']"/>

		<xsl:if test="count($checklists) + count($concepts) + count($guidelines) + count($supportingMaterials) + count($toolMentors) + count($whitePapers) + count($estimationConsiderations) + count($udts) > 0">
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
					<xsl:call-template name="addToolMentors">
						<xsl:with-param name="toolMentors" select="$toolMentors"/>
					</xsl:call-template>
					<xsl:call-template name="addWhitePapers">
						<xsl:with-param name="whitePapers" select="$whitePapers"/>
					</xsl:call-template>
					<xsl:call-template name="addEstimationConsiderations">
						<xsl:with-param name="estimationConsiderations" select="$estimationConsiderations"/>
					</xsl:call-template>
					<xsl:call-template name="addUdts">
						<xsl:with-param name="udts" select="$udts"/>
					</xsl:call-template>
				</table>
			</div>
		</xsl:if>
	</xsl:template>

	<xsl:template name="stepsSection">
		<xsl:param name="description"/>
		<xsl:variable name="sections" select="$description/referenceList[@name='sections']/Element[@Type='Section']"/>
		<xsl:if test="count($sections) > 0">
			<div class="sectionHeading"><xsl:value-of select="$stepsText"/></div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td class="sectionTableSingleCell">
							<xsl:for-each select="$sections">
								<div class="stepHeading"><xsl:value-of select="attribute[@name='name']"/></div>
								<div class="stepContent">
									<table class="stepTable" border="0" cellspacing="0" cellpadding="0">
										<tr valign="top">
											<td>
												<xsl:value-of disable-output-escaping="yes" select="attribute[@name='sectionDescription']"/>
											</td>
										</tr>
									</table>
								</div>						
							</xsl:for-each>
						</td>
					</tr>
				</table>
			</div>
		</xsl:if>
	</xsl:template>

	<xsl:template name="alternativesSection">
		<xsl:param name="description"/>
		<xsl:variable name="alternatives" select="$description/attribute[@name='alternatives']"/>
		<xsl:if test="$alternatives != '' ">
			<div class="sectionHeading"><xsl:value-of select="$alternativesText"/></div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					<tr valign="top">
						<td class="sectionTableSingleCell">
							<xsl:value-of disable-output-escaping="yes" select="$alternatives"/>
						</td>
					</tr>
				</table>
			</div>
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>

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
	<xsl:include href="main_description.xsl"/>
	<xsl:include href="illustration.xsl"/>
	<xsl:include href="key_consideration.xsl"/>
	<xsl:include href="guidance_helper.xsl"/>
	<xsl:include href="extended_refs.xsl"/>
	<xsl:include href="extended_rtes.xsl"/>
	
	<xsl:template match="/Element">
		<xsl:variable name="Name" select="@Name"/>
		<xsl:variable name="elementType" select="@Type"/>
		<xsl:variable name="elementTypeName" select="@TypeName"/>
		<xsl:variable name="elementName" select="@Name"/>
		<xsl:variable name="elementPresentationName" select="@DisplayName"/>
		<xsl:variable name="backPath" select="@BackPath"/>
		<xsl:variable name="shapeImage" select="concat($backPath,@ShapeiconUrl)"/>
		<xsl:variable name="imagePath" select="concat($backPath, 'images/')"/>	
		<xsl:variable name="presentationName_temp" select="reference[@name='presentation']/Element/attribute[@name='presentationName']" />
    	<xsl:variable name="presentationName">
			<xsl:choose>
				<xsl:when test="$presentationName_temp != '' "><xsl:value-of select="$presentationName_temp"/></xsl:when>
				<xsl:otherwise><xsl:value-of select="$Name"/></xsl:otherwise>				
			</xsl:choose>
    	</xsl:variable>
	    <xsl:variable name="presentation" select="reference[@name='presentation']"/>
		<xsl:variable name="description" select="$presentation/Element"/>
		<xsl:variable name="copyright" select="copyright"/>
	    <xsl:variable name="showTreeBrowser" select="@showTreeBrowser"/>
		<xsl:variable name="briefDescription" select="attribute[@name='briefDescription']"/>	    
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
					contentPage.preload(imgPath, backPath, nodeInfo, '', false, false, false);
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
							<xsl:call-template name="relationshipsSection">
								<xsl:with-param name="elementType" select="$elementType"/>
							</xsl:call-template>
							<xsl:call-template name="extendedRefsSectionsAll">
								<xsl:with-param name="elementDown" select="/Element"/>
								<xsl:with-param name="iconLevel" select="'two'"/>
								<xsl:with-param name="layoutLocation" select="''"/>
							</xsl:call-template>
							<xsl:call-template name="mainDescriptionSection">
								<xsl:with-param name="description" select="$description"/>
							</xsl:call-template>
							<xsl:call-template name="extendedRtesSectionsAll">
								<xsl:with-param name="descriptionDown" select="$description"/>
								<xsl:with-param name="layoutLocation" select="''"/>
							</xsl:call-template>
							<xsl:if test="$elementType != 'CustomCategory'">
								<xsl:call-template name="illustrationsSection"/>
							</xsl:if>
							<xsl:call-template name="keyConsiderationsSection">
								<xsl:with-param name="description" select="$description"/>
							</xsl:call-template>
							<xsl:call-template name="moreInfoSection">
								<xsl:with-param name="elementType" select="$elementType"/>
								<xsl:with-param name="briefDescription" select="$briefDescription"/>
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

	<xsl:template name="moreInfoSection">
		<xsl:param name="elementType"/>
		<xsl:param name="briefDescription"/>
		<xsl:variable name="checklists" select="referenceList/Element[@Type='Checklist']"/>
		<xsl:variable name="concepts" select="referenceList/Element[@Type='Concept']"/>
		<xsl:variable name="guidelines" select="referenceList/Element[@Type='Guideline']"/>
		<xsl:variable name="supportingMaterials" select="referenceList/Element[@Type='SupportingMaterial']"/>
		<xsl:variable name="whitePapers" select="referenceList/Element[@Type='Whitepaper']"/>
		<xsl:variable name="conceptsAndPapers" select="referenceList/Element[@Type='conceptsAndPapers']"/>
		<xsl:variable name="udts" select="referenceList[@name='User defined type references']/Element[@Type='udt']"/>

		<xsl:choose>
			<xsl:when test="$elementType = 'CustomCategory'">
			</xsl:when>
			<xsl:otherwise>
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
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

    <xsl:template name="relationshipsSection">
		
		<xsl:param name="elementType"/>
		<xsl:if test="$elementType = 'Discipline'">
			<xsl:call-template name="showDisciple"/>
		</xsl:if>
		<xsl:if test="$elementType = 'DisciplineGrouping'">
			<xsl:call-template name="showDiscipleGrouping"/>
		</xsl:if>
		<xsl:if test="$elementType = 'Domain'">
			<xsl:call-template name="showDomain"/>
		</xsl:if>
		<xsl:if test="$elementType = 'WorkProductType'">
			<xsl:call-template name="showWorkProductType"/>
		</xsl:if>		
		<xsl:if test="$elementType = 'RoleSetGrouping'">
			<xsl:call-template name="showRoleSetGrouping"/>
		</xsl:if>
		<xsl:if test="$elementType = 'RoleSet'">
			<xsl:call-template name="showRoleSet"/>
		</xsl:if>
		<xsl:if test="$elementType = 'Tool'">
			<xsl:call-template name="showTool"/>
		</xsl:if>
		<xsl:if test="$elementType = 'CustomCategory'">
			<xsl:call-template name="showCustomCategory"/>
		</xsl:if>		
    </xsl:template>
	
    <xsl:template name="showDisciple">
    		<xsl:variable name="practices" select="referenceList[@name='Practices']/Element"/>
		<xsl:variable name="referenceWorkflows" select="referenceList[@name='referenceWorkflows']/Element"/>
		<xsl:variable name="tasks" select="referenceList[@name='tasks']/Element"/>
		<xsl:variable name="subdisciplines" select="referenceList[@name='subdiscipline']/Element"/>
		<xsl:variable name="categories" select="referenceList[@name='ContentElement_CustomCategories']/Element"/>
		<xsl:if test="count($practices) + count($referenceWorkflows) + count($tasks) + count($subdisciplines)> 0">
			<div class="sectionHeading"><xsl:value-of select="$relationshipsText"/></div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					<xsl:call-template name="showParentPractices"></xsl:call-template> 
					<xsl:call-template name="addReferences">
						<xsl:with-param name="refName" select="$categoriesText"/>
						<xsl:with-param name="refElement" select="$categories"/>
					</xsl:call-template>
					<xsl:if test="count($referenceWorkflows) > 0">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row"><xsl:value-of select="$referenceWorkflowsText"/></th>
							<td class="sectionTableCell">
								<ul>
									<xsl:for-each select="$referenceWorkflows">
									<xsl:sort data-type="text" select="@DisplayName" order="ascending" />
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
					<xsl:if test="count($tasks) > 0">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row"><xsl:value-of select="$tasksText"/></th>
							<td class="sectionTableCell">
								<ul>
									<xsl:choose>
										<xsl:when test="$tasks/../@sortValue">
											<xsl:for-each select="$tasks">
												<li>
													<a>
														<xsl:attribute name="href"><xsl:value-of select="/Element/@BackPath"/><xsl:value-of select="@Url"/></xsl:attribute>
														<xsl:value-of select="@DisplayName"/>
													</a>
												</li>
											</xsl:for-each>
										</xsl:when>
										<xsl:otherwise>
											<xsl:for-each select="$tasks">
											<xsl:sort select="@DisplayName"/>
												<li>
													<a>
														<xsl:attribute name="href"><xsl:value-of select="/Element/@BackPath"/><xsl:value-of select="@Url"/></xsl:attribute>
														<xsl:value-of select="@DisplayName"/>
													</a>
												</li>
											</xsl:for-each>
										</xsl:otherwise>
									</xsl:choose>
								</ul>
							</td>
						</tr>
					</xsl:if>
					<xsl:if test="count($subdisciplines) > 0">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row"><xsl:value-of select="$subdisciplinesText"/></th>
							<td class="sectionTableCell">
								<ul>
									<xsl:for-each select="$subdisciplines">
										<xsl:sort data-type="text" select="@DisplayName" order="ascending" />
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

    <xsl:template name="showDiscipleGrouping">
    		<xsl:variable name="practices" select="referenceList[@name='Practices']/Element"/>
		<xsl:variable name="disciplines" select="referenceList[@name='disciplines']/Element"/>
		<xsl:variable name="categories" select="referenceList[@name='ContentElement_CustomCategories']/Element"/>
		<xsl:if test="count($practices) + count($disciplines) > 0">
			<div class="sectionHeading"><xsl:value-of select="$relationshipsText"/></div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					<xsl:call-template name="showParentPractices"></xsl:call-template> 
					<xsl:call-template name="addReferences">
						<xsl:with-param name="refName" select="$categoriesText"/>
						<xsl:with-param name="refElement" select="$categories"/>
					</xsl:call-template>
					<tr valign="top">
						<th class="sectionTableHeading" scope="row"><xsl:value-of select="$disciplinesText"/></th>
						<td class="sectionTableCell">
							<ul>
								<xsl:for-each select="$disciplines">
								<xsl:sort data-type="text" select="@DisplayName" order="ascending" />
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
				</table>
			</div>
		</xsl:if>
    </xsl:template>

    <xsl:template name="showDomain">
        	<xsl:variable name="practices" select="referenceList[@name='Practices']/Element"/>
		<xsl:variable name="subdomains" select="referenceList[@name='subdomains']/Element"/>
		<xsl:variable name="workProducts" select="referenceList[@name='workProducts']/Element"/>
		<xsl:variable name="categories" select="referenceList[@name='ContentElement_CustomCategories']/Element"/>						
		<xsl:if test="count($practices) + count($subdomains) + count($workProducts) > 0">
			<div class="sectionHeading"><xsl:value-of select="$relationshipsText"/></div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					<xsl:call-template name="showParentPractices"></xsl:call-template> 
					<xsl:call-template name="addReferences">
						<xsl:with-param name="refName" select="$categoriesText"/>
						<xsl:with-param name="refElement" select="$categories"/>
					</xsl:call-template>
					<xsl:if test="count($subdomains) > 0">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row"><xsl:value-of select="$subdomainsText"/></th>
							<td class="sectionTableCell">
								<ul>
									<xsl:for-each select="$subdomains">
										<xsl:sort data-type="text" select="@DisplayName" order="ascending" />
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
					<xsl:if test="count($workProducts) > 0">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row"><xsl:value-of select="$workProductsText"/></th>
							<td class="sectionTableCell">
								<ul>
									<xsl:for-each select="$workProducts">
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

    <xsl:template name="showWorkProductType">
        	<xsl:variable name="practices" select="referenceList[@name='Practices']/Element"/>
		<xsl:variable name="workProducts" select="referenceList[@name='workProducts']/Element"/>
		<xsl:variable name="categories" select="referenceList[@name='ContentElement_CustomCategories']/Element"/>
		<xsl:if test="count($practices) + count($workProducts) > 0">
			<div class="sectionHeading"><xsl:value-of select="$relationshipsText"/></div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					<xsl:call-template name="showParentPractices"></xsl:call-template> 
					<xsl:call-template name="addReferences">
							<xsl:with-param name="refName" select="$categoriesText"/>
							<xsl:with-param name="refElement" select="$categories"/>
					</xsl:call-template>
					<tr valign="top">
						<th class="sectionTableHeading" scope="row"><xsl:value-of select="$workProductsText"/></th>
						<td class="sectionTableCell">
							<ul>
								<xsl:for-each select="$workProducts">
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
				</table>
			</div>
		</xsl:if>
    </xsl:template>

    <xsl:template name="showRoleSetGrouping">
        	<xsl:variable name="practices" select="referenceList[@name='Practices']/Element"/>
		<xsl:variable name="roleSets" select="referenceList[@name='roleSets']/Element"/>
		<xsl:variable name="categories" select="referenceList[@name='ContentElement_CustomCategories']/Element"/>
		<xsl:if test="count($practices) + count($roleSets) > 0">
			<div class="sectionHeading"><xsl:value-of select="$relationshipsText"/></div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					<xsl:call-template name="showParentPractices"></xsl:call-template> 
					<xsl:call-template name="addReferences">
							<xsl:with-param name="refName" select="$categoriesText"/>
							<xsl:with-param name="refElement" select="$categories"/>
					</xsl:call-template>
					<tr valign="top">
						<th class="sectionTableHeading" scope="row"><xsl:value-of select="$roleSetsText"/></th>
						<td class="sectionTableCell">
							<ul>
								<xsl:for-each select="$roleSets">
								<xsl:sort data-type="text" select="@DisplayName" order="ascending" />
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
				</table>
			</div>
		</xsl:if>
    </xsl:template>

    <xsl:template name="showRoleSet">
        	<xsl:variable name="practices" select="referenceList[@name='Practices']/Element"/>
		<xsl:variable name="roles" select="referenceList[@name='roles']/Element"/>
		<xsl:variable name="categories" select="referenceList[@name='ContentElement_CustomCategories']/Element"/>
		<xsl:if test="count($practices) + count($roles) > 0">
			<div class="sectionHeading"><xsl:value-of select="$relationshipsText"/></div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					<xsl:call-template name="showParentPractices"></xsl:call-template> 
					<xsl:call-template name="addReferences">
							<xsl:with-param name="refName" select="$categoriesText"/>
							<xsl:with-param name="refElement" select="$categories"/>
					</xsl:call-template>
					<tr valign="top">
						<th class="sectionTableHeading" scope="row"><xsl:value-of select="$rolesText"/></th>
						<td class="sectionTableCell">
							<ul>
								<xsl:for-each select="$roles">
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
				</table>
			</div>
		</xsl:if>
    </xsl:template>

    <xsl:template name="showTool">
        	<xsl:variable name="practices" select="referenceList[@name='Practices']/Element"/>
		<xsl:variable name="toolMentors" select="referenceList[@name='toolMentors']/Element"/>
		<xsl:variable name="categories" select="referenceList[@name='ContentElement_CustomCategories']/Element"/>
		<xsl:if test="count($practices) + count($toolMentors) > 0">
			<div class="sectionHeading"><xsl:value-of select="$relationshipsText"/></div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					<xsl:call-template name="showParentPractices"></xsl:call-template> 
					<xsl:call-template name="addReferences">
							<xsl:with-param name="refName" select="$categoriesText"/>
							<xsl:with-param name="refElement" select="$categories"/>
					</xsl:call-template>
					<tr valign="top">
						<th class="sectionTableHeading" scope="row"><xsl:value-of select="$toolMentorsText"/></th>
						<td class="sectionTableCell">
							<ul>
								<xsl:for-each select="$toolMentors">
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
				</table>
			</div>
		</xsl:if>
    </xsl:template>

    <xsl:template name="showCustomCategory">
  			<xsl:variable name="practices" select="referenceList[@name='Practices']/Element"/>
  		<xsl:variable name="categorizedElements" select="referenceList[@name='categorizedElements']/Element"/>
        <xsl:variable name="categories" select="referenceList[@name='ContentElement_CustomCategories']/Element"/>
        <xsl:if test="count($practices) + count($categorizedElements) > 0">
        	<div class="sectionHeading"><xsl:value-of select="$relationshipsText"/></div>
            <div class="sectionContent">
            	<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">    
            		<xsl:call-template name="showParentPractices"></xsl:call-template>                             
					<xsl:call-template name="addReferences">
						<xsl:with-param name="refName" select="$categoriesText"/>
						<xsl:with-param name="refElement" select="$categories"/>
					</xsl:call-template>
               		<tr valign="top">
                   		<th class="sectionTableHeading" scope="row"><xsl:value-of select="$contentsText"/></th>
                   		<td class="sectionTableCell">
                       		<ul>
                           		<xsl:for-each select="$categorizedElements">
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
            	</table>
         	</div>
    	</xsl:if>
    </xsl:template>

</xsl:stylesheet>

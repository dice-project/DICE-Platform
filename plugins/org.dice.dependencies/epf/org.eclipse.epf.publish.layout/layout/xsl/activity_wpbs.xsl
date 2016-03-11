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
		<xsl:variable name="showFullMethodContent" select="@ShowFullMethodContent"/>
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
					contentPage.preload(imgPath, backPath, nodeInfo, defaultQueryStr, false, true, true);
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
						<div autoWrap="true">
							<xsl:call-template name="overview">
								<xsl:with-param name="elementType" select="$elementType"/>
								<xsl:with-param name="elementTypeName" select="$elementTypeName"/>
								<xsl:with-param name="elementPresentationName" select="$elementPresentationName"/>
								<xsl:with-param name="elementIcon" select="$shapeImage"/>
								<xsl:with-param name="backPath" select="$backPath"/>
								<xsl:with-param name="showTreeBrowser" select="$showTreeBrowser"/>
							</xsl:call-template>
							</div>
							<xsl:call-template name="activityTabs"/>
							<xsl:call-template name="workflowSection"/>
							<xsl:call-template name="wpbsSection">
								<xsl:with-param name="showFullMethodContent" select="$showFullMethodContent"/>
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
				<td width="10" class="tab">
					<img src="{$imagePath}shim.gif" width="10" height="17" alt="" title=""/>
				</td>
				<td nowrap="nowrap" class="tab">
					<a class="tab" id="TAB_Description">
						<xsl:call-template name="tabUrlAttribute">
							<xsl:with-param name="url" select="concat(/Element/@BackPath, tabs/tab[@name='Description']/@url)"/>
						</xsl:call-template>
						<xsl:value-of select="$descriptionText"/>
					</a>
				</td>
				<td width="21">
					<img src="{$imagePath}tab_middle-i_i.gif" width="21" height="17" align="absmiddle" alt="" title=""/>
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
					<img src="{$imagePath}tab_middle-i_a.gif" width="21" height="17" align="absmiddle" alt="" title=""/>
				</td>
				<td nowrap="nowrap" class="activeTab">
					<span style="white-space:nowrap;">
						<xsl:value-of select="$wpbsText"/>
					</span>
				</td>
				<td width="21">
					<img src="{$imagePath}tab_end-a.gif" width="21" height="17" alt="" title=""/>
				</td>
				<td width="50%">
					<img src="{$imagePath}shim.gif" width="10" height="17" alt="" title=""/>
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<img src="{$imagePath}shim.gif" height="2" alt="" title=""/>
				</td>
				<td>
					<img src="{$imagePath}tab_space_middle.gif" width="21" height="2" alt="" title=""/>
				</td>
				<td class="activeTab">
					<img src="{$imagePath}shim.gif" width="10" height="2" alt="" title=""/>
				</td>
				<td>
					<img src="{$imagePath}tab_space.gif" width="21" height="2" alt="" title=""/>
				</td>
				<td colspan="1">
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

	<xsl:template name="workflowSection">
		<xsl:if test="diagrams/diagram[@name='WPDependency'] or diagrams/userdiagram[@name='WPDependency'] ">
			<div class="sectionHeading">
				<xsl:value-of select="$workProductDependenciesText"/>
			</div>
			<div class="sectionContent">
				<!--
			<p align="center">
				<xsl:value-of disable-output-escaping="yes" select="diagrams/diagram[@name='WPDependency']"/>
			</p>

				<xsl:call-template name="displayDiagram">
					<xsl:with-param name="diagram" select="diagrams/diagram[@name='WPDependency']"/>
				</xsl:call-template>
							-->
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">			
					<xsl:choose>
						<xsl:when test="diagrams/userdiagram[@name='WPDependency']">
							<tr>
								<td class="sectionTableSingleCell" colspan="2" align="left">
									<xsl:value-of disable-output-escaping="yes" select="diagrams/userdiagram[@name='WPDependency']"/>
								</td>
							</tr>
						</xsl:when>
						<xsl:otherwise>
							<xsl:if test="diagrams/diagram[@name='WPDependency']">
								<tr>
									<td class="sectionTableSingleCell" colspan="2" align="left">
										<xsl:call-template name="displayDiagram">
											<xsl:with-param name="diagram" select="diagrams/diagram[@name='WPDependency']"/>
										</xsl:call-template>
									</td>
								</tr>
							</xsl:if>
						</xsl:otherwise>
					</xsl:choose>
				</table>	
			</div>
		</xsl:if>
	</xsl:template>

	<xsl:template name="wpbsSection">
		<xsl:param name="showFullMethodContent"/>
		<xsl:variable name="imagePath" select="concat(/Element/@BackPath, 'images/')"/>
		<xsl:variable name="colSize" select="count(breakdown[@name='Work Product Breakdown Structure']/columns/column)"/>
		<div class="sectionHeading">
			<xsl:value-of select="$workProductBreakdownText"/>
		</div>
		<div class="sectionContent">
		<div id="treeContent"></div>
<script language="JavaScript">
contentPage.processPage.treeTable.data = {
title: "<xsl:value-of select="$workProductBreakdownText"/>", summary: "<xsl:value-of select="$workProductBreakdownText"/>", 
columns: [<xsl:for-each select="breakdown[@name='Work Product Breakdown Structure']/columns/column">
["<xsl:value-of select="@id"/>", "<xsl:value-of select="@label"/>"]<xsl:if test="position() != $colSize">,</xsl:if>
					</xsl:for-each>],
rows: [<xsl:for-each select="breakdown[@name='Work Product Breakdown Structure']/Element">
						<xsl:sort data-type="text" select="@DisplayName" order="ascending"/>
						<xsl:call-template name="wbsItem">
							<xsl:with-param name="element" select="."/>
							<xsl:with-param name="indent" select="0"/>
							<xsl:with-param name="showFullMethodContent" select="$showFullMethodContent"/>
							<xsl:with-param name="parentNodeId" select=" '' "/>
						</xsl:call-template>
					</xsl:for-each>
]};
				</script>
		</div>
	</xsl:template>

	<xsl:template name="wbsItem">
		<xsl:param name="element"/>
		<xsl:param name="parentNodeId"/>
		<xsl:param name="indent"/>
		<xsl:param name="showFullMethodContent"/>
		<xsl:variable name="elementType" select="$element/@Type"/>			
		<xsl:variable name="imagePath" select="concat(/Element/@BackPath, 'images/')"/>
		<xsl:variable name="hasChildren">
			<xsl:choose>
				<xsl:when test="count($element/Element) > 0">true</xsl:when>
				<xsl:otherwise>false</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:variable name="typeName">
			<xsl:choose>
				<xsl:when test="$showFullMethodContent='true'">
					<xsl:choose>										
						<xsl:when test="$elementType='TaskDescriptor'">
							<xsl:value-of select="$taskText"/>
						</xsl:when>
						<xsl:when test="$elementType='RoleDescriptor'">
							<xsl:value-of select="$roleText"/>
						</xsl:when>
						<xsl:when test="$elementType='WorkProductDescriptor'">
							<xsl:call-template name="wpdConcreteTypeText">
								<xsl:with-param name="showFullMethodContent" select="$showFullMethodContent"/>
								<xsl:with-param name="concreteType" select="$element/@ConcreteType"/>
							</xsl:call-template>
						</xsl:when>
						<xsl:otherwise>
							<xsl:call-template name="elementTypeText">
								<xsl:with-param name="elementType" select="$elementType"/>
								<xsl:with-param name="elementTypeName" select="$element/@TypeName"/>
							</xsl:call-template>
						</xsl:otherwise>
					</xsl:choose>									
				</xsl:when>
				<xsl:otherwise>
					<xsl:choose>														
						<xsl:when test="$elementType = 'WorkProductDescriptor'">
							<xsl:call-template name="wpdConcreteTypeText">
								<xsl:with-param name="showFullMethodContent" select="$showFullMethodContent"/>
								<xsl:with-param name="concreteType" select="$element/@ConcreteType"/>
							</xsl:call-template>
						</xsl:when>
						<xsl:otherwise>
							<xsl:call-template name="elementTypeText">
								<xsl:with-param name="elementType" select="$elementType"/>
								<xsl:with-param name="elementTypeName" select="$element/@TypeName"/>
							</xsl:call-template>
						</xsl:otherwise>
					</xsl:choose>															
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>	
		<xsl:variable name="modelInfoText">
    	    <xsl:choose>
    	        <xsl:when test="$element/@ModelInfoKey!=''">
         	        <xsl:call-template name="modelInfoTextRecursively">
     				    <xsl:with-param name="modelInfoKey" select="$element/@ModelInfoKey"/>
			        </xsl:call-template>   	            
    	        </xsl:when>
    	        <xsl:otherwise>
    	            <xsl:value-of select="''"/>
    	        </xsl:otherwise>
    	    </xsl:choose>
    	</xsl:variable>
			
{id: "<xsl:value-of select="$element/@nodeId"/>", parentId: "<xsl:value-of select="$parentNodeId"/>", relPath: "<xsl:value-of select="$element/@relProcessPath"/>", isSuppressed: "<xsl:value-of select="$element/@isSupressed"/>", indentSize: <xsl:value-of select="$indent"/>, hasChildren:<xsl:value-of select="$hasChildren"/>, 
	index: "<xsl:value-of select="$element/@Index"/>", prefix: "<xsl:value-of select="$element/attribute[@name='prefix']"/>", name: "<xsl:value-of select="$element/@Name"/>", title: "<xsl:value-of select="$element/@DisplayName"/>", url: "<xsl:value-of select="concat(/Element/@BackPath, $element/@Url)"/>", 
	predecessors: "<xsl:value-of select="$element/@Predecessors"/>", info: "<xsl:value-of select="$modelInfoText"/>", type: "<xsl:value-of select="$typeName"/>", 
	repeatable: "<xsl:value-of select="$element/attribute[@name='isRepeatable']"/>", multiOccurences: "<xsl:value-of select="$element/attribute[@name='hasMultipleOccurrences']"/>", optional: "<xsl:value-of select="$element/attribute[@name='isOptional']"/>", planned: "<xsl:value-of select="$element/attribute[@name='isPlanned']"/>",  ongoing: "<xsl:value-of select="$element/attribute[@name='isOngoing']"/>", eventDriven: "<xsl:value-of select="$element/attribute[@name='isEventDriven']"/>", 
	team: "", entryState: "<xsl:value-of select="$element/@EntryState"/>", exitState: "<xsl:value-of select="$element/@ExitState"/>", deliverable: "<xsl:value-of select="$element/@Deliverable"/>", variabilityType: ""},
<!--
contentPage.processPage.treeTable.wbsItemHtml.initRow("<xsl:value-of select="$element/@nodeId"/>", "<xsl:value-of select="$parentNodeId"/>", "<xsl:value-of select="$element/@relProcessPath"/>", "<xsl:value-of select="$element/@isSupressed"/>", <xsl:value-of select="$indent"/>, <xsl:value-of select="$hasChildren"/>, "<xsl:value-of select="$element/@Index"/>", "<xsl:value-of select="$element/attribute[@name='prefix']"/>", "<xsl:value-of select="$element/@Name"/>", "<xsl:value-of select="$element/@DisplayName"/>", "<xsl:value-of select="concat(/Element/@BackPath, $element/@Url)"/>", "", "<xsl:value-of select="$element/@Predecessors"/>", "<xsl:value-of select="$element/@ModelInfo"/>", "<xsl:value-of select="$typeName"/>", "<xsl:value-of select="$element/attribute[@name='isRepeatable']"/>", "<xsl:value-of select="$element/attribute[@name='hasMultipleOccurrences']"/>", "<xsl:value-of select="$element/attribute[@name='isOptional']"/>", "<xsl:value-of select="$element/attribute[@name='isPlanned']"/>",  "<xsl:value-of select="$element/attribute[@name='isOngoing']"/>", "<xsl:value-of select="$element/attribute[@name='isEventDriven']"/>", "", "<xsl:value-of select="$element/@EntryState"/>", "<xsl:value-of select="$element/@ExitState"/>", "<xsl:value-of select="$element/@Deliverable"/>", "", "");
contentPage.processPage.treeTable.wbsItemHtml.writeRow();	
-->
		<xsl:if test="count($element/Element) > 0">
			<xsl:for-each select="$element/Element">
			<!-- <xsl:sort select="@DisplayName"/>  -->
				<xsl:call-template name="wbsItem">
					<xsl:with-param name="element" select="."/>
					<xsl:with-param name="indent" select="$indent+1"/>
					<xsl:with-param name="showFullMethodContent" select="$showFullMethodContent"/>
					<xsl:with-param name="parentNodeId" select="$element/@nodeId"/>
				</xsl:call-template>
			</xsl:for-each>
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>

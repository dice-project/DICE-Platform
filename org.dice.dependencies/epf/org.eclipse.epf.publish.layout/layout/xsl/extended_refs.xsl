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

	<xsl:include href="qualified_ref.xsl"/>
	<xsl:include href="tables.xsl"/>
	
	<xsl:template name="extendedRefsSectionsAll">
		<xsl:param name="elementDown"/>
		<xsl:param name="iconLevel"/>
		<xsl:param name="layoutLocation"/>
		
		<!-- render all sections with a specific layout="VALUE" except no layout= attribute -->
		<xsl:if test="string($layoutLocation)">
			<xsl:variable name="theSections" select="$elementDown/section[@type='reference' and @layout=$layoutLocation]"/>
			<xsl:call-template name="renderReferenceLists">
				<xsl:with-param name="sections" select="$theSections"/>
				<xsl:with-param name="iconLevelDown" select="$iconLevel"/>
			</xsl:call-template>
		</xsl:if>

		<!-- render all sections without layout= attribute -->
		<xsl:if test="not(string($layoutLocation))">
			<xsl:variable name="theSections" select="$elementDown/section[@type='reference' and (not(@layout)) ]"/>
			<xsl:call-template name="renderReferenceLists">
				<xsl:with-param name="sections" select="$theSections"/>
				<xsl:with-param name="iconLevelDown" select="$iconLevel"/>
			</xsl:call-template>
		</xsl:if>
				
	</xsl:template>

	<!-- for internal use in this file -->
	<xsl:template name="renderReferenceLists">
		<xsl:param name="sections"/>
		<xsl:param name="iconLevelDown"/>
		
		<xsl:for-each select="$sections">
			  <xsl:if test="count(*) > 0">
					<div class="sectionHeading"><xsl:value-of select="@name"/></div>
					<div class="sectionContent">
						
					<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">	
					
					<xsl:for-each select="referenceList[@format='nested list']">
						
						<xsl:call-template name="qualifiedRefField">
							<xsl:with-param name="iconLevel" select="$iconLevelDown"/>
						</xsl:call-template>
						 
					</xsl:for-each>
					   
					<xsl:call-template name="displayTables">
						<xsl:with-param name="sectionElement" select="."/>
					</xsl:call-template>
							 
					</table>
					
					</div>
			   </xsl:if>	
			</xsl:for-each>

	</xsl:template>
	
	<!-- for use to embed custom references into existing relationships section -->
	<xsl:template name="extendedRefsSectionsEmbedded">
		<xsl:param name="elementDown"/>
		<xsl:param name="iconLevel"/>
		<xsl:param name="layoutLocation"/>
		
		<xsl:for-each select="$elementDown/section[@type='reference' and @layout=$layoutLocation]">

			<xsl:for-each select="referenceList[@format='nested list']">
				
				<xsl:call-template name="qualifiedRefField">
					<xsl:with-param name="iconLevel" select="$iconLevel"/>
				</xsl:call-template>
				 
			</xsl:for-each>
			
		   <!--  do NOT render tables in embedded relationship section -->
		   <!-- 
			<xsl:call-template name="displayTables">
				<xsl:with-param name="sectionElement" select="."/>
			</xsl:call-template>
			-->
				
		</xsl:for-each>
		
	</xsl:template>
	
</xsl:stylesheet>

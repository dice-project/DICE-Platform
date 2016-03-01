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

	<xsl:template name="illustrationsSection">
		<xsl:variable name="examples" select="referenceList/Element[@Type='Example']"/>
		<xsl:variable name="reusableAssets" select="referenceList/Element[@Type='ReusableAsset']"/>
		<xsl:if test="count($examples) + count($reusableAssets) > 0">
			<div class="sectionHeading">
				<xsl:value-of select="$illustrationsText"/>
			</div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					<xsl:call-template name="addExamples">
						<xsl:with-param name="examples" select="$examples"/>
					</xsl:call-template>
					<xsl:call-template name="addReusableAssets">
						<xsl:with-param name="reusableAssets" select="$reusableAssets"/>
					</xsl:call-template>
				</table>
			</div>
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>

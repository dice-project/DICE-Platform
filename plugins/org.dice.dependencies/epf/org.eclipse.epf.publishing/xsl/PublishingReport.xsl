<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Copyright (c) 2005, 2006 IBM Corporation and others.
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the Eclipse Public License v1.0
    which accompanies this distribution, and is available at
    http://www.eclipse.org/legal/epl-v10.html
    Contributors:
    IBM Corporation - initial implementation
-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">

<xsl:output method="html" version="1.0" encoding="UTF-8" 
	doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" 
	doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" 
	indent="yes"/>
	
<xsl:param name="selectedConfigurationText"/>
<xsl:param name="detailInfoText"/>
<xsl:param name="showErrorText"/>
<xsl:param name="logsWarningText"/>
<xsl:param name="pagesNotInstalledText"/>
<xsl:param name="invalidExternalText"/>
<xsl:param name="nonExistingText"/>
<xsl:param name="missingReferencesTitleText"/>
<xsl:param name="missingReferencesText"/>
<xsl:param name="missingResourceFilesTitleText"/>
<xsl:param name="missingResourceFilesText"/>
<xsl:param name="missingResourceText"/>
<xsl:param name="ownerResourceText"/>
<xsl:param name="ownerElementText"/>
<xsl:param name="missingElementText"/>
<xsl:param name="invalidElementText"/>
<xsl:param name="invalidElementLinksText"/>
<xsl:param name="pleaseCheckText"/>
<xsl:param name="pleaseFindText"/>
<xsl:param name="publishingReportText"/>
<xsl:param name="summaryText"/>
<xsl:param name="infoText"/>


<xsl:template match="PublishingReport">
<html>
			<xsl:attribute name="lang"><xsl:value-of select="@lang"/></xsl:attribute>
			<xsl:attribute name="xml:lang"><xsl:value-of select="@lang"/></xsl:attribute>
<head>
<link type="text/css" href="./../css/default.css" rel="StyleSheet"/>
<script src="./../scripts/ContentPageResource.js" type="text/javascript" language="JavaScript"></script>
<script src="./../scripts/ContentPageSection.js" type="text/javascript" language="JavaScript"></script>
<script src="./../scripts/contentPage.js" type="text/javascript" language="JavaScript"></script>
<script type="text/javascript" language="JavaScript">
					backPath = '../';
					imgPath = '../images/';
					var nodeInfo=null;
					contentPage.preload(imgPath, backPath, nodeInfo,  '', false, false, false);
				</script>
</head>

<body>
<h2><xsl:value-of select="$publishingReportText"/></h2>

<div class="sectionHeading"><xsl:value-of select="$summaryText"/></div>
<div align="left">
<p/><xsl:value-of select="$selectedConfigurationText"/>:<p/>

<b>
<xsl:value-of select="pubDir/@path"/>
</b>

<p/>

<xsl:value-of select="$detailInfoText"/>:
<ul>
<li>
<a>
<xsl:attribute name="href">
<xsl:value-of select="errorLog/@path"/>
</xsl:attribute>
<xsl:value-of select="errorLog/@name"/>
</a>
 - <xsl:value-of select="$showErrorText"/> </li>
<li>
<a>
<xsl:attribute name="href">
<xsl:value-of select="warningLog/@path"/>
</xsl:attribute>
<xsl:value-of select="warningLog/@name"/>
</a>
 - <xsl:value-of select="$logsWarningText"/> </li>
</ul>

<xsl:value-of select="$pagesNotInstalledText"/>

</div>


<xsl:if test="validatorInfo/invalidExternalLinks">
<div class="sectionHeading"><xsl:value-of select="$invalidExternalText"/>:</div>
<div align="left">
<p/>
<table class="sectionTable">
	<tbody>
		<tr>
			<th>Url</th><th><xsl:value-of select="$ownerElementText"/></th><th><xsl:value-of select="$infoText"/></th>
		</tr>
		<xsl:for-each select="validatorInfo/invalidExternalLinks/entry">
		<tr>
			<td><xsl:value-of select="@url"/></td>
			<td><xsl:value-of select="@owner"/></td>
			<td><xsl:value-of select="@message"/>&#160;</td>
		</tr>
	</xsl:for-each>
	</tbody>
</table>
</div>
</xsl:if>

<xsl:if test="validatorInfo/invalidReferences and count(validatorInfo/invalidReferences/entry) > 0">

<div class="sectionHeading"><xsl:value-of select="$invalidElementLinksText"/>:</div>
<div align="left">
<p/>
<xsl:value-of select="$nonExistingText"/><br/>
<xsl:value-of select="$pleaseFindText"/>
<p/>
<table class="sectionTable">
	<tbody>
		<tr>
			<th><xsl:value-of select="$invalidElementText"/></th><th><xsl:value-of select="$ownerElementText"/></th>
		</tr>
		<xsl:for-each select="validatorInfo/invalidReferences/entry">
		<tr>
			<td><xsl:value-of select="@element"/> (<xsl:value-of select="@guid"/>)</td>
			<td><xsl:value-of select="@owner"/></td>
		</tr>
	</xsl:for-each>
	</tbody>
</table>
</div>
</xsl:if>


<xsl:if test="validatorInfo/missingReferences and count(validatorInfo/missingReferences/entry) > 0">

<div class="sectionHeading"> <xsl:value-of select="$missingReferencesTitleText"/>:</div>
<div align="left">
<p/>
<xsl:value-of select="$missingReferencesText"/><br/>
<xsl:value-of select="$pleaseCheckText"/>
<p/>
<table class="sectionTable">
	<tbody>
		<tr>
			<th><xsl:value-of select="$missingElementText"/></th><th><xsl:value-of select="$ownerElementText"/></th>
		</tr>
		<xsl:for-each select="validatorInfo/missingReferences/entry">
		<tr>
			<td><xsl:value-of select="@element"/></td>
			<td><xsl:value-of select="@owner"/></td>
		</tr>
	</xsl:for-each>
	</tbody>
</table>
</div>
</xsl:if>

<xsl:if test="validatorInfo/missingResources and count(validatorInfo/missingResources/entry) > 0">

<div class="sectionHeading"><xsl:value-of select="$missingResourceFilesTitleText"/>:</div>
<div align="left">
<p/>
<xsl:value-of select="$missingResourceFilesText"/><br/>
<p/>
<table class="sectionTable">
	<tbody>
		<tr>
			<th><xsl:value-of select="$missingResourceText"/></th><th><xsl:value-of select="$ownerResourceText"/></th><th><xsl:value-of select="$ownerElementText"/></th>
		</tr>
		<xsl:for-each select="validatorInfo/missingResources/entry">
		<tr>
			<td><xsl:value-of select="@url"/></td>
			<td><xsl:value-of select="@resource"/></td>
			<td><xsl:value-of select="@owner"/></td>
		</tr>
	</xsl:for-each>
	</tbody>
</table>
</div>
</xsl:if>


</body>
<script type="text/javascript" language="JavaScript">
				contentPage.onload();
</script>
</html>


</xsl:template>
</xsl:stylesheet>

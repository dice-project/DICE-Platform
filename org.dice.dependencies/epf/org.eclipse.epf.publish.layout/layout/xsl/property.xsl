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

	<xsl:template name="property">
		<xsl:param name="fieldLabel"/>
		<xsl:param name="fieldText"/>
		<xsl:variable name="imagePath" select="concat(/Element/@BackPath, 'images/')"/>
		<xsl:variable name="id">property_<xsl:value-of select="$fieldLabel"/></xsl:variable>
		<xsl:if test="$fieldText != '' ">
			<tr valign="top">
				<th class="sectionTableHeading" scope="row">
					<xsl:attribute name="id"><xsl:value-of select="$id"/></xsl:attribute>
					<xsl:attribute name="abbr"><xsl:value-of select="$fieldLabel"/></xsl:attribute>
					<xsl:value-of select="$fieldLabel"/>
				</th>
				<xsl:choose>
					<xsl:when test="$fieldText = 'true' or $fieldText = 'false'">
						<td class="sectionTableCell" align="left">
							<xsl:attribute name="headers"><xsl:value-of select="$id"/></xsl:attribute>
							<xsl:choose>
								<xsl:when test="$fieldText = 'true'">
									<img width="20" height="15" alt="Yes" title="Yes">
										<xsl:attribute name="src"><xsl:value-of select="$imagePath"/>true.gif</xsl:attribute>
									</img>
								</xsl:when>
								<xsl:otherwise>
									<img width="20" height="15" alt="" title="">
										<xsl:attribute name="src"><xsl:value-of select="$imagePath"/>indent.gif</xsl:attribute>
									</img>
								</xsl:otherwise>
							</xsl:choose>
							<!--
							<label for="cbox1"/>
							<input type="checkbox" name="property">
								<xsl:attribute name="readonly">true</xsl:attribute>
								<xsl:if test="$fieldText = 'true'">
									<xsl:attribute name="checked">checked</xsl:attribute>
								</xsl:if>
								<xsl:attribute name="id">cbox1</xsl:attribute>
								<xsl:attribute name="value">cbox1</xsl:attribute>
								<xsl:attribute name="onclick"><xsl:choose><xsl:when test="$fieldText = 'true'">
	 								this.checked=true;
 								</xsl:when><xsl:otherwise>
 									this.checked=false;
 								</xsl:otherwise></xsl:choose></xsl:attribute>
							</input>
							-->
						</td>
					</xsl:when>
					<xsl:otherwise>
						<td class="sectionTableCell">
							<xsl:attribute name="headers"><xsl:value-of select="$id"/></xsl:attribute>
							<xsl:value-of disable-output-escaping="yes" select="$fieldText"/>
						</td>
					</xsl:otherwise>
				</xsl:choose>
			</tr>
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>

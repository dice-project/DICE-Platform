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

	<xsl:template name="displayTables">
		<xsl:param name="sectionElement"/>
	
		<xsl:for-each select="table">
		
		<table border="1">
				
		<br/>			
		<!-- generate table column heads -->
		<tr>
			<th><xsl:value-of select="@tableName"/></th>
			<xsl:for-each select="columnList/column">
				<th>
					<a>
						<xsl:attribute name="href"><xsl:value-of select="reference/Element/@BackPath"/><xsl:value-of select="reference/Element/@Url"/></xsl:attribute>
						<xsl:value-of disable-output-escaping="yes" select="reference/Element/@DisplayName"/>
					</a>
				</th>
			</xsl:for-each>
		</tr>
		
		<xsl:for-each select="rowList/row">
			<tr>
				<th>
					<a>
						<xsl:attribute name="href"><xsl:value-of select="reference/Element/@BackPath"/><xsl:value-of select="reference/Element/@Url"/></xsl:attribute>
						<xsl:value-of disable-output-escaping="yes" select="reference/Element/@DisplayName"/>
					</a>
				</th>
				<xsl:for-each select="Cell">
						<xsl:choose> 
							<xsl:when test="string-length(@name) = 0"> 
								<td>&#160;</td>
							</xsl:when> 
							<xsl:otherwise> 
								<td>
									<a>
									<xsl:attribute name="href"><xsl:value-of select="reference/Element/@BackPath"/><xsl:value-of select="reference/Element/@Url"/></xsl:attribute>
									<xsl:value-of disable-output-escaping="yes" select="reference/Element/@DisplayName"/>
									</a>		
								</td>
							</xsl:otherwise> 
						</xsl:choose> 
						
				</xsl:for-each>
			</tr>
		</xsl:for-each>
			
		</table>
		
		</xsl:for-each>
		
	</xsl:template>

</xsl:stylesheet>

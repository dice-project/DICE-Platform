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

	<xsl:template name="qualifiedRefField">
		<xsl:param name="iconLevel"/>
		<!--
		<br/><xsl:value-of select="@referenceId"></xsl:value-of>
		<xsl:value-of select="$iconLevel"/>
		-->
				
		<xsl:if test="count(*) >0">
					<xsl:if test="count(*) > 0">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row">
								<xsl:value-of select="@referenceName"/>
							</th>
							<td class="sectionTableCell">
								<xsl:for-each select="*">
									<ul>
										<xsl:if test="name()='Element'">
											<li>
												<img>
													<xsl:choose> 
														<xsl:when test="$iconLevel = 'two'"> 
															<xsl:attribute name="src">./../../<xsl:value-of select="@ShapeiconUrl"/></xsl:attribute>
														</xsl:when> 
														<xsl:otherwise> 
															<xsl:attribute name="src">./../../../<xsl:value-of select="@ShapeiconUrl"/></xsl:attribute>
														</xsl:otherwise>
													</xsl:choose> 				
													<xsl:attribute name="height">16</xsl:attribute>
													<xsl:attribute name="width">16</xsl:attribute>
												</img>
												<a>
													<xsl:attribute name="href"><xsl:value-of select="@BackPath"/><xsl:value-of select="@Url"/></xsl:attribute>
													<xsl:value-of disable-output-escaping="yes" select="@DisplayName"/>
												</a>
											</li>
										</xsl:if>
										<xsl:if test="name()='referenceList'">
											<li>
												<xsl:value-of select="@qualifierName"/>
											</li>
											<ul>
												<xsl:for-each select="./*">
													<xsl:if test="name()='referenceList'">
														<li>
															<xsl:value-of select="@name"/>
														</li>
														<ul>
															<xsl:for-each select="./*">
																<li>
																	<img>
																		<xsl:choose> 
																			<xsl:when test="$iconLevel = 'two'"> 
																				<xsl:attribute name="src">./../../<xsl:value-of select="@ShapeiconUrl"/></xsl:attribute>
																			</xsl:when> 
																			<xsl:otherwise> 
																				<xsl:attribute name="src">./../../../<xsl:value-of select="@ShapeiconUrl"/></xsl:attribute>
																			</xsl:otherwise>
																		</xsl:choose> 
																		<xsl:attribute name="height">16</xsl:attribute>
																		<xsl:attribute name="width">16</xsl:attribute>
																	</img>
																	<a>
																		<xsl:attribute name="href"><xsl:value-of select="@BackPath"/><xsl:value-of select="@Url"/></xsl:attribute>
																		<xsl:value-of disable-output-escaping="yes" select="@DisplayName"/>
																	</a>
																</li>
															</xsl:for-each>
														</ul>
													</xsl:if>
													<xsl:if test="name()='Element'">
														<li>
															<img>
																<xsl:choose> 
																	<xsl:when test="$iconLevel = 'two'"> 
																		<xsl:attribute name="src">./../../<xsl:value-of select="@ShapeiconUrl"/></xsl:attribute>
																	</xsl:when> 
																	<xsl:otherwise> 
																		<xsl:attribute name="src">./../../../<xsl:value-of select="@ShapeiconUrl"/></xsl:attribute>
																	</xsl:otherwise>
																</xsl:choose> 
																<xsl:attribute name="height">16</xsl:attribute>
																<xsl:attribute name="width">16</xsl:attribute>
															</img>
															<a>
																<xsl:attribute name="href"><xsl:value-of select="@BackPath"/><xsl:value-of select="@Url"/></xsl:attribute>
																<xsl:value-of disable-output-escaping="yes" select="@DisplayName"/>
															</a>
														</li>
													</xsl:if>
												</xsl:for-each>
											</ul>
										</xsl:if>
									</ul>
								</xsl:for-each>
							</td>
						</tr>
					</xsl:if>
			
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>

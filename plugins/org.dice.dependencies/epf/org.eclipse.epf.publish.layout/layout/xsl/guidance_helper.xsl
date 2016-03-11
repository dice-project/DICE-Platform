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

	<xsl:template name="addChecklists">
		<xsl:param name="checklists"/>
		<xsl:if test="count($checklists) > 0">
			<tr valign="top">
				<th class="sectionTableHeading" scope="row">
					<xsl:value-of select="$checklistsText"/>
				</th>
				<td class="sectionTableCell">
					<ul>
						<xsl:for-each select="$checklists/../*">
						<xsl:sort select="@DisplayName"/>
							<xsl:if test="@Type='Checklist'">
								<li>
									<xsl:call-template name="addElementWithLink">
										<xsl:with-param name="element" select="."/>
									</xsl:call-template>
								</li>
							</xsl:if>
						</xsl:for-each>
					</ul>
				</td>
			</tr>
		</xsl:if>
	</xsl:template>

	<xsl:template name="addConcepts">
		<xsl:param name="concepts"/>
		<xsl:if test="count($concepts) > 0">
			<tr valign="top">
				<th class="sectionTableHeading" scope="row">
					<xsl:value-of select="$conceptsText"/>
				</th>
				<td class="sectionTableCell">
					<ul>
						<xsl:for-each select="$concepts/../*">
						<xsl:sort select="@DisplayName"/>
							<xsl:if test="@Type='Concept'">
								<li>
									<xsl:call-template name="addElementWithLink">
										<xsl:with-param name="element" select="."/>
									</xsl:call-template>
								</li>
							</xsl:if>
						</xsl:for-each>
					</ul>
				</td>
			</tr>
		</xsl:if>
	</xsl:template>

	<xsl:template name="addEstimationConsiderations">
		<xsl:param name="estimationConsiderations"/>
		<xsl:if test="count($estimationConsiderations) > 0">
			<tr valign="top">
				<th class="sectionTableHeading" scope="row">
					<xsl:value-of select="$estimationConsiderationsText"/>
				</th>
				<td class="sectionTableCell">
					<ul>
						<xsl:for-each select="$estimationConsiderations/../*">
						<xsl:sort select="@DisplayName"/>
							<xsl:if test="@Type='EstimationConsiderations'">
								<li>
									<xsl:call-template name="addElementWithLink">
										<xsl:with-param name="element" select="."/>
									</xsl:call-template>
								</li>
							</xsl:if>
						</xsl:for-each>
					</ul>
				</td>
			</tr>
		</xsl:if>
	</xsl:template>

	<xsl:template name="addExamples">
		<xsl:param name="examples"/>
		<xsl:if test="count($examples) > 0">
			<tr valign="top">
				<th class="sectionTableHeading" scope="row">
					<xsl:value-of select="$examplesText"/>
				</th>
				<td class="sectionTableCell">
					<ul>
						<xsl:for-each select="$examples/../*">
						<xsl:sort select="@DisplayName"/>
							<xsl:if test="@Type='Example'">
								<li>
									<xsl:call-template name="addElementWithLink">
										<xsl:with-param name="element" select="."/>
									</xsl:call-template>
								</li>
							</xsl:if>
						</xsl:for-each>
					</ul>
				</td>
			</tr>
		</xsl:if>
	</xsl:template>

	<xsl:template name="addGuidelines">
		<xsl:param name="guidelines"/>
		<xsl:if test="count($guidelines) > 0">
			<tr valign="top">
				<th class="sectionTableHeading" scope="row">
					<xsl:value-of select="$guidelinesText"/>
				</th>
				<td class="sectionTableCell">
					<ul>
						<xsl:for-each select="$guidelines/../*">
						<xsl:sort select="@DisplayName"/>
							<xsl:if test="@Type='Guideline'">
								<li>
									<xsl:call-template name="addElementWithLink">
										<xsl:with-param name="element" select="."/>
									</xsl:call-template>
								</li>
							</xsl:if>
						</xsl:for-each>
					</ul>
				</td>
			</tr>
		</xsl:if>
	</xsl:template>

	<xsl:template name="addReports">
		<xsl:param name="reports"/>
		<xsl:if test="count($reports) > 0">
			<tr valign="top">
				<th class="sectionTableHeading" scope="row">
					<xsl:value-of select="$reportsText"/>
				</th>
				<td class="sectionTableCell">
					<ul>
						<xsl:for-each select="$reports/../*">
						<xsl:sort select="@DisplayName"/>
							<xsl:if test="@Type='Report'">
								<li>
									<xsl:call-template name="addElementWithLink">
										<xsl:with-param name="element" select="."/>
									</xsl:call-template>
								</li>
							</xsl:if>
						</xsl:for-each>
					</ul>
				</td>
			</tr>
		</xsl:if>
	</xsl:template>

	<xsl:template name="addReusableAssets">
		<xsl:param name="reusableAssets"/>
		<xsl:if test="count($reusableAssets) > 0">
			<tr valign="top">
				<th class="sectionTableHeading" scope="row">
					<xsl:value-of select="$reusableAssetsText"/>
				</th>
				<td class="sectionTableCell">
					<ul>
						<xsl:for-each select="$reusableAssets/../*">
						<xsl:sort select="@DisplayName"/>
							<xsl:if test="@Type='ReusableAsset'">
								<li>
									<xsl:call-template name="addElementWithLink">
										<xsl:with-param name="element" select="."/>
									</xsl:call-template>
								</li>
							</xsl:if>
						</xsl:for-each>
					</ul>
				</td>
			</tr>
		</xsl:if>
	</xsl:template>

	<xsl:template name="addSupportingMaterials">
		<xsl:param name="supportingMaterials"/>
		<xsl:if test="count($supportingMaterials) > 0">
			<tr valign="top">
				<th class="sectionTableHeading" scope="row">
					<xsl:value-of select="$supportingMaterialsText"/>
				</th>
				<td class="sectionTableCell">
					<ul>
						<xsl:for-each select="$supportingMaterials/../*">
						<xsl:sort select="@DisplayName"/>
							<xsl:if test="@Type='SupportingMaterial'">
								<li>
									<xsl:call-template name="addElementWithLink">
										<xsl:with-param name="element" select="."/>
									</xsl:call-template>
								</li>
							</xsl:if>
						</xsl:for-each>
					</ul>
				</td>
			</tr>
		</xsl:if>
	</xsl:template>

	<xsl:template name="addTechniques">
		<xsl:param name="techniques"/>
		<xsl:if test="count($techniques) > 0">
			<tr valign="top">
				<th class="sectionTableHeading" scope="row">
					<xsl:value-of select="$techniquesText"/>
				</th>
				<td class="sectionTableCell">
					<ul>
						<xsl:for-each select="$techniques/../*">
						<xsl:sort select="@DisplayName"/>
							<xsl:if test="@Type='Technique'">
								<li>
									<xsl:call-template name="addElementWithLink">
										<xsl:with-param name="element" select="."/>
									</xsl:call-template>
								</li>
							</xsl:if>
						</xsl:for-each>
					</ul>
				</td>
			</tr>
		</xsl:if>
	</xsl:template>

	<xsl:template name="addTemplates">
		<xsl:param name="templates"/>
		<xsl:if test="count($templates) > 0">
			<tr valign="top">
				<th class="sectionTableHeading" scope="row">
					<xsl:value-of select="$templatesText"/>
				</th>
				<td class="sectionTableCell">
					<ul>
						<xsl:for-each select="$templates/../*">
						<xsl:sort select="@DisplayName"/>
							<xsl:if test="@Type='Template'">
								<li>
									<xsl:call-template name="addElementWithLink">
										<xsl:with-param name="element" select="."/>
									</xsl:call-template>
								</li>
							</xsl:if>
						</xsl:for-each>
					</ul>
				</td>
			</tr>
		</xsl:if>
	</xsl:template>

	<xsl:template name="addWhitePapers">
		<xsl:param name="whitePapers"/>
		<xsl:if test="count($whitePapers) > 0">
			<tr valign="top">
				<th class="sectionTableHeading" scope="row">
					<xsl:value-of select="$whitepapersText"/>
				</th>
				<td class="sectionTableCell">
					<ul>
						<xsl:for-each select="$whitePapers/../*">
						<xsl:sort select="@DisplayName"/>
							<xsl:if test="@Type='Whitepaper'">
								<li>
									<xsl:call-template name="addElementWithLink">
										<xsl:with-param name="element" select="."/>
									</xsl:call-template>
								</li>
							</xsl:if>
						</xsl:for-each>
					</ul>
				</td>
			</tr>
		</xsl:if>
	</xsl:template>

	<xsl:template name="addToolMentors">
		<xsl:param name="toolMentors"/>
		<xsl:if test="count($toolMentors) > 0">
			<tr valign="top">
				<th class="sectionTableHeading" scope="row">
					<xsl:value-of select="$toolMentorsText"/>
				</th>
				<td class="sectionTableCell">
					<ul>
						<xsl:for-each select="$toolMentors/../*">
						<xsl:sort select="@DisplayName"/>
							<xsl:if test="@Type='ToolMentor'">
								<li>
									<xsl:call-template name="addElementWithLink">
										<xsl:with-param name="element" select="."/>
									</xsl:call-template>
								</li>
							</xsl:if>
						</xsl:for-each>
					</ul>
				</td>
			</tr>
		</xsl:if>
	</xsl:template>
	<xsl:template name="addUdts">
		<xsl:param name="udts"/>
		<xsl:if test="count($udts) > 0">
			<tr valign="top">
				<th class="sectionTableHeading" scope="row">
					<xsl:value-of select="$udtText"/>
				</th>
				<td class="sectionTableCell">
					<ul>
						<xsl:for-each select="$udts/../*">
						<xsl:sort select="@DisplayName"/>
							<xsl:if test="@Type='udt'">
								<li>
									<xsl:call-template name="addElementWithLink">
										<xsl:with-param name="element" select="."/>
									</xsl:call-template>
								</li>
							</xsl:if>
						</xsl:for-each>
					</ul>
				</td>
			</tr>
		</xsl:if>
	</xsl:template>	

</xsl:stylesheet>

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

	<xsl:include href="guidance_helper.xsl"/>
	<xsl:include href="descriptor_helper.xsl"/>
	<xsl:include href="custom_opposite.xsl"/>

	<xsl:template name="workProductIllustrationsSection">
		<xsl:variable name="templates" select="referenceList/Element[@Type='Template']"/>
		<xsl:variable name="reports" select="referenceList/Element[@Type='Report']"/>
		<xsl:variable name="examples" select="referenceList/Element[@Type='Example']"/>
		<xsl:variable name="reusableAssets" select="referenceList/Element[@Type='ReusableAsset']"/>	
		<xsl:if test="count($templates) + count($reports) + count($examples) + count($reusableAssets) > 0">
			<div class="sectionHeading"><xsl:value-of select="$illustrationsText"/></div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">
					<xsl:call-template name="addTemplates">
						<xsl:with-param name="templates" select="$templates"/>
					</xsl:call-template>
					<xsl:call-template name="addReports">
						<xsl:with-param name="reports" select="$reports"/>
					</xsl:call-template>
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

	<xsl:template name="relationshipsSection">
	    	<xsl:variable name="practices" select="referenceList[@name='Practices']/Element"/>
		<xsl:variable name="deliverableParents" select="referenceList[@name='WorkProduct_Deliverables']/Element"></xsl:variable>
		<xsl:variable name="fulfillingWorkProducts" select="referenceList[@name='FulFills_FullFillableElements']/Element"></xsl:variable>
		<xsl:variable name="fulfilledSlots" select="referenceList[@name='fulfills']/Element"></xsl:variable>
		<xsl:variable name="containerArtifact" select="reference[@name='containerArtifact']/Element"/>
		<xsl:variable name="containedArtifacts" select="referenceList[@name='containedArtifacts']/Element[@Type='Artifact']"/>
		<xsl:variable name="responsibleRoles" select="referenceList[@name='responsibleRoles']/Element[@Type='Role']"/>
		<xsl:variable name="modifyRoles" select="referenceList[@name='modifyRoles']/Element"/>		
		<xsl:variable name="mandatoryInputToTasks" select="referenceList[@name='mandatoryInputToTasks']/Element[@Type='Task']"/>
		<xsl:variable name="optionalInputToTasks" select="referenceList[@name='optionalInputToTasks']/Element[@Type='Task']"/>
		<xsl:variable name="outputFromTasks" select="referenceList[@name='outputFromTasks']/Element[@Type='Task']"/>
		<xsl:variable name="mandatoryInputToTasks_fromSlots" select="referenceList[@name='mandatoryInputToTasks_fromSlots']/Element[@Type='Task']"/>
		<xsl:variable name="optionalInputToTasks_fromSlots" select="referenceList[@name='optionalInputToTasks_fromSlots']/Element[@Type='Task']"/>
		<xsl:variable name="outputFromTasks_fromSlots" select="referenceList[@name='outputFromTasks_fromSlots']/Element[@Type='Task']"/>
		<xsl:variable name="categories" select="referenceList[@name='ContentElement_CustomCategories']/Element"/>
		
		<xsl:if test="count(/Element/referenceList[@referenceType='customOpposite']) + count($practices) + count($deliverableParents) + count($fulfillingWorkProducts) + count($fulfilledSlots) + count($categories) + count($responsibleRoles) + count($containerArtifact) + count($containedArtifacts) + count($mandatoryInputToTasks) + count($optionalInputToTasks) + count($outputFromTasks) + count($mandatoryInputToTasks_fromSlots) + count($optionalInputToTasks_fromSlots) + count($outputFromTasks_fromSlots) > 0">
			<div class="sectionHeading"><xsl:value-of select="$relationshipsText"/></div>
			<div class="sectionContent">
				<table class="sectionTable" border="0" cellspacing="0" cellpadding="0">

					<xsl:call-template name="showParentPractices"></xsl:call-template>
					
					<xsl:call-template name="customOppositeRelationshipsEmbedded">
						<xsl:with-param name="elementDown" select="/Element"/>
						<xsl:with-param name="iconLevel" select="'two'"/>
						<xsl:with-param name="layoutLocation" select="''"/>
					</xsl:call-template>
				
					<xsl:if test="count($deliverableParents) > 0">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row"><xsl:value-of select="$deliverableParentsText"/></th>
							<td class="sectionTableCell" colspan="2">
							<ul>
								<xsl:for-each select="$deliverableParents">
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
					
					<xsl:if test="count($fulfillingWorkProducts) > 0">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row"><xsl:value-of select="$fulfillingWorkProductsText"/></th>
							<td class="sectionTableCell" colspan="2">
							<ul>
								<xsl:for-each select="$fulfillingWorkProducts">
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
					
					<xsl:if test="count($fulfilledSlots) > 0">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row"><xsl:value-of select="$fulfilledSlotsText"/></th>
							<td class="sectionTableCell" colspan="2">
							<ul>
								<xsl:for-each select="$fulfilledSlots">
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
					
					<xsl:if test="count($containerArtifact) > 0">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row"><xsl:value-of select="$containerArtifactText"/></th>
							<td class="sectionTableCell" colspan="2">
							<ul>
								<xsl:for-each select="$containerArtifact">
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
					<xsl:if test="count($containedArtifacts) > 0">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row"><xsl:value-of select="$containedArtifactsText"/></th>
							<td class="sectionTableCell" colspan="2">
							<ul>
								<xsl:for-each select="$containedArtifacts">
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
					
					<xsl:if test="count($responsibleRoles) + count($modifyRoles) > 0">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row"><xsl:value-of select="$rolesText"/></th>
							<td class="sectionTableCell" width="42%">
								<span class="sectionTableCellHeading">
									<xsl:value-of select="$responsibleText"/>:
								</span>
								<xsl:if test="count($responsibleRoles) > 0">
								<ul>
									<xsl:for-each select="$responsibleRoles">
									<xsl:sort select="@DisplayName"/>
										<li>
											<xsl:call-template name="addElementWithLink">
												<xsl:with-param name="element" select="."/>
											</xsl:call-template>
										</li>
									</xsl:for-each>
								</ul>
								</xsl:if>
							</td>
							<td class="sectionTableCell">
								<span class="sectionTableCellHeading">
									<xsl:value-of select="$modifiedByText"/>:
								</span>
								<xsl:if test="count($modifyRoles) > 0">									
								<ul>
									<xsl:for-each select="$modifyRoles">
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
						<!--  next template call is an example for advanced skin customization -->
						<xsl:call-template name="customOppositeRelationshipsEmbedded">
							<xsl:with-param name="elementDown" select="/Element"/>
							<xsl:with-param name="iconLevel" select="'two'"/>
							<xsl:with-param name="layoutLocation" select="'rel_middle'"/>
						</xsl:call-template>
					<xsl:if test="count($mandatoryInputToTasks) + count($optionalInputToTasks) + count($outputFromTasks) + count($mandatoryInputToTasks_fromSlots) + count($optionalInputToTasks_fromSlots) + count($outputFromTasks_fromSlots) > 0">
						<tr valign="top">
							<th class="sectionTableHeading" scope="row"><xsl:value-of select="$tasksText"/></th>
							<td class="sectionTableCell" width="42%">
								<span class="sectionTableCellHeading">
									<xsl:value-of select="$inputToText"/>:
								</span>
								<xsl:if test="count($mandatoryInputToTasks) + count($optionalInputToTasks) + count($mandatoryInputToTasks_fromSlots) + count($optionalInputToTasks_fromSlots) > 0">
								    <xsl:if test="count($mandatoryInputToTasks) > 0">
									<ul>
										<xsl:for-each select="$mandatoryInputToTasks">
										<xsl:sort select="@DisplayName"/>
											<li>
												<a><xsl:attribute name="href"><xsl:value-of select="/Element/@BackPath"/><xsl:value-of select="@Url"/></xsl:attribute><xsl:value-of select="@DisplayName"/></a>
											</li>
										</xsl:for-each>
									</ul>
									</xsl:if>
									<xsl:if test="count($optionalInputToTasks) > 0">
									  <xsl:if test="count($mandatoryInputToTasks) > 0"><br/></xsl:if>
									<ul>		
										<xsl:for-each select="$optionalInputToTasks">
										<xsl:sort select="@DisplayName"/>
											<li>
												<a><xsl:attribute name="href"><xsl:value-of select="/Element/@BackPath"/><xsl:value-of select="@Url"/></xsl:attribute><xsl:value-of select="@DisplayName"/></a>
											</li>
										</xsl:for-each>										
									</ul>
									</xsl:if>
									<xsl:if test="count($mandatoryInputToTasks_fromSlots) > 0">
										<xsl:if test="count($mandatoryInputToTasks) + count($optionalInputToTasks) > 0"><br/></xsl:if>
									<ul>
										<xsl:for-each select="$mandatoryInputToTasks_fromSlots">
										<xsl:sort select="@DisplayName"/>
											<li>
												<a><xsl:attribute name="href"><xsl:value-of select="/Element/@BackPath"/><xsl:value-of select="@Url"/></xsl:attribute><xsl:value-of select="@DisplayName"/></a>
											</li>
										</xsl:for-each>
									</ul>
									</xsl:if>
									<xsl:if test="count($optionalInputToTasks_fromSlots) > 0">
									  <xsl:if test="count($mandatoryInputToTasks) + count($optionalInputToTasks) + count($mandatoryInputToTasks_fromSlots)> 0"><br/></xsl:if>
									<ul>		
										<xsl:for-each select="$optionalInputToTasks_fromSlots">
										<xsl:sort select="@DisplayName"/>
											<li>
												<a><xsl:attribute name="href"><xsl:value-of select="/Element/@BackPath"/><xsl:value-of select="@Url"/></xsl:attribute><xsl:value-of select="@DisplayName"/></a>
											</li>
										</xsl:for-each>										
									</ul>
									</xsl:if>
								</xsl:if>
							</td>
							<td class="sectionTableCell">
								<span class="sectionTableCellHeading">
									<xsl:value-of select="$outputFromText"/>:
								</span>
								<xsl:if test="count($outputFromTasks) > 0">									
									<ul>
										<xsl:for-each select="$outputFromTasks">
										<xsl:sort select="@DisplayName"/>
											<li>
												<a><xsl:attribute name="href"><xsl:value-of select="/Element/@BackPath"/><xsl:value-of select="@Url"/></xsl:attribute><xsl:value-of select="@DisplayName"/></a>
											</li>
										</xsl:for-each>
									</ul>
								</xsl:if>
								<xsl:if test="count($outputFromTasks_fromSlots) > 0">	
									<xsl:if test="count($outputFromTasks) > 0"><br/></xsl:if>								
									<ul>
										<xsl:for-each select="$outputFromTasks_fromSlots">
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
					
					<!--  next template call is an example for advanced skin customization -->
					<xsl:call-template name="customOppositeRelationshipsEmbedded">
						<xsl:with-param name="elementDown" select="/Element"/>
						<xsl:with-param name="iconLevel" select="'two'"/>
						<xsl:with-param name="layoutLocation" select="'rel_tail'"/>
					</xsl:call-template>
					
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

</xsl:stylesheet>

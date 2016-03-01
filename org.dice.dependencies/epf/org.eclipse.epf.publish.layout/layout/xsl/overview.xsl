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

	<xsl:template name="overview">
		<xsl:param name="elementType"/>
		<xsl:param name="elementTypeName"/>
		<xsl:param name="elementPresentationName"/>
		<xsl:param name="elementIcon"/>
		<xsl:param name="backPath"/>
		<xsl:param name="showTreeBrowser"/>
		<xsl:param name="workProductType"/>
		<xsl:variable name="prefix" select="attribute[@name='prefix']"/>
		<xsl:variable name="briefDescription" select="attribute[@name='briefDescription']"/>
		<xsl:variable name="synonyms" select="reference/Element/attribute[@name='synonyms']"/>
		<xsl:variable name="externalId" select="reference/Element/attribute[@name='externalId']"/>
		<xsl:variable name="imagePath" select="concat(/Element/@BackPath, 'images/')"/>
		<xsl:variable name="showFullMethodContent" select="@ShowFullMethodContent"/>
		<xsl:variable name="visibleTags" select="@VisibleTags"/>
		<div id="page-guid">
			<xsl:attribute name="value"><xsl:value-of select="attribute[@name='guid']"/></xsl:attribute>
		</div>
		<table border="0" cellspacing="0" cellpadding="0" width="100%">
			<tr>
				<td class="pageTitle" nowrap="true">
					<xsl:call-template name="elementPageTitleText">
						<xsl:with-param name="elementType" select="$elementType"/>
						<xsl:with-param name="elementTypeName" select="$elementTypeName"/>
						<xsl:with-param name="elementPresentationName" select="$elementPresentationName"/>
						<xsl:with-param name="workProductType" select="$workProductType"/>
					</xsl:call-template>
				</td>
				<td width="100%">
					<div align="right" id="contentPageToolbar"/>
				</td>
				<xsl:if test="$showTreeBrowser">
					<td width="100%" class="expandCollapseLink" align="right">
						<a name="mainIndex" href="{$backPath}index.htm"/>
						<script language="JavaScript" type="text/javascript" src="{$backPath}scripts/treebrowser.js"/>
					</td>
				</xsl:if>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="pageTitleSeparator">
					<img src="{$imagePath}shim.gif" alt="" title="" height="1"/>
				</td>
			</tr>
		</table>
		<xsl:choose>
			<xsl:when test="$elementType = 'CustomCategory' and $briefDescription = ''">
				<br/>
			</xsl:when>
			<xsl:otherwise>
				<div class="overview">
					<table width="97%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="50">
								<img src="{$elementIcon}" alt="" title=""/>
							</td>
							<td>
								<table class="overviewTable" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td valign="top">
											<xsl:value-of disable-output-escaping="yes" select="$briefDescription"/>
										</td>
									</tr>
									<xsl:choose>
										<xsl:when test="$elementType = 'Role'">
											<xsl:call-template name="roleKeyInfo"/>
										</xsl:when>
										<xsl:when test="$elementType = 'Task'">
											<xsl:call-template name="taskKeyInfo"/>
										</xsl:when>
										<xsl:when test="$elementType = 'Artifact' or $elementType = 'Deliverable' or $elementType = 'Outcome' or $elementType = 'WorkProductSlot'">
											<xsl:call-template name="workProductKeyInfo"/>
										</xsl:when>
										<xsl:when test="$elementType = 'ToolMentor'">
											<xsl:call-template name="toolMentorKeyInfo"/>
										</xsl:when>
										<xsl:when test="$elementType = 'TaskDescriptor'">
											<xsl:call-template name="taskDescriptorKeyInfo">
												<xsl:with-param name="showFullMethodContent" select="$showFullMethodContent"/>
											</xsl:call-template>
										</xsl:when>
										<xsl:when test="$elementType = 'RoleDescriptor'">
											<xsl:call-template name="roleDescriptorKeyInfo">
												<xsl:with-param name="showFullMethodContent" select="$showFullMethodContent"/>
											</xsl:call-template>
										</xsl:when>
										<xsl:when test="$elementType = 'WorkProductDescriptor'">
											<xsl:call-template name="workProductDescriptorKeyInfo">
												<xsl:with-param name="showFullMethodContent" select="$showFullMethodContent"/>
											</xsl:call-template>
										</xsl:when>
										<xsl:otherwise>
											<xsl:variable name="baseElement" select="reference[@name='variabilityBasedOnElement']/Element"/>
											<xsl:variable name="variabilityType" select="attribute[@name='variabilityType']"/>
											<xsl:if test="count($baseElement) > 0">
												<tr>
													<td valign="top">
														<xsl:if test="$variabilityType = 'replaces'">
															<xsl:value-of select="$replacesText"/>
															<xsl:value-of select="$colon_with_space"/>
														</xsl:if>
														<xsl:if test="$variabilityType = 'contributes' ">
															<xsl:value-of select="$contributesText"/>
															<xsl:value-of select="$colon_with_space"/>
														</xsl:if>
														<xsl:if test="$variabilityType = 'extendsReplaces' ">
															<xsl:value-of select="$extendsReplacesText"/>
															<xsl:value-of select="$colon_with_space"/>
														</xsl:if>
														<!-- display localCOntribution and localReplacement as extends for now -->
														<xsl:if test="$variabilityType = 'extends' or $variabilityType = 'localContribution' or $variabilityType = 'localReplacement' ">
															<xsl:value-of select="$extendsText"/>
															<xsl:value-of select="$colon_with_space"/>
														</xsl:if>
														<xsl:for-each select="$baseElement">
														<xsl:sort select="@DisplayName"/>
															<xsl:call-template name="addElementWithLink">
																<xsl:with-param name="element" select="."/>
															</xsl:call-template>
														</xsl:for-each>
													</td>
												</tr>
											</xsl:if>
										</xsl:otherwise>
									</xsl:choose>	
									<xsl:if test="$visibleTags">
										<tr><td>
										<xsl:call-template name="processTags">
											<xsl:with-param name="tagStr" select="$visibleTags"/>
										</xsl:call-template>
										</td></tr>
									</xsl:if>					
								</table>
							</td>
						</tr>
					</table>
				</div>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template name="processTags">
		<xsl:param name="tagStr"/>
		<xsl:choose>
			<xsl:when test="contains($tagStr,',,')">
				<xsl:value-of select="substring-before($tagStr,',,')"/>
				<br/>
				<xsl:call-template name="processTags">
					<xsl:with-param name="tagStr" select="substring-after($tagStr,',,')"/>
				</xsl:call-template>
			</xsl:when>
			<xsl:otherwise>
				<xsl:value-of select="$tagStr"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template name="elementTypeText">
		<xsl:param name="elementType"/>
		<xsl:param name="elementTypeName"/>
		<xsl:choose>										
		    <!-- Alex: workaround for RATLC00330706 [Light] searching workproductslot missing Icon in result -->						
			<xsl:when test="$elementTypeName = $workProductSlotText">
				<xsl:value-of select="$workProductSlotText"/>
			</xsl:when>		
			<xsl:when test="$elementType = 'Activity'">
					<xsl:value-of select="$activityText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'Artifact'">
					<xsl:value-of select="$artifactText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'CapabilityPattern'">
					<xsl:value-of select="$capabilityPatternText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'Checklist'">
					<xsl:value-of select="$checklistText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'CompositeRole'">
					<xsl:value-of select="$compositeRoleText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'Concept'">
					<xsl:value-of select="$conceptText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'CustomCategory'">
					<xsl:value-of select="$customCategoryText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'Deliverable'">
					<xsl:value-of select="$deliverableText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'DeliveryProcess'">
					<xsl:value-of select="$deliveryProcessText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'Discipline'">
					<xsl:value-of select="$disciplineText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'DisciplineGrouping'">
					<xsl:value-of select="$disciplineGroupingText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'Domain'">
					<xsl:value-of select="$domainText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'EstimationConsiderations'">
					<xsl:value-of select="$estimationConsiderationsText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'Example'">
					<xsl:value-of select="$exampleText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'Guideline'">
					<xsl:value-of select="$guidelineText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'Iteration'">
					<xsl:value-of select="$iterationText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'Milestone'">
					<xsl:value-of select="$milestoneText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'Outcome'">
					<xsl:value-of select="$outcomeText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'Phase'">
					<xsl:value-of select="$phaseText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'Practice'">
					<xsl:value-of select="$practiceText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'Report'">
					<xsl:value-of select="$reportText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'ReusableAsset'">
					<xsl:value-of select="$reusableAssetText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'Roadmap'">
					<xsl:value-of select="$roadmapText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'Role'">
					<xsl:value-of select="$roleText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'RoleDescriptor'">
					<xsl:value-of select="$roleDescriptorText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'RoleSet'">
					<xsl:value-of select="$roleSetText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'RoleSetGrouping'">
					<xsl:value-of select="$roleSetGroupingText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'SupportingMaterial'">
					<xsl:value-of select="$supportingMaterialText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'Task'">
					<xsl:value-of select="$taskText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'TaskDescriptor'">
					<xsl:value-of select="$taskDescriptorText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'TeamProfile'">
					<xsl:value-of select="$teamProfileText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'Template'">
					<xsl:value-of select="$templateText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'TermDefinition'">
					<xsl:value-of select="$termDefinitionText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'Tool'">
					<xsl:value-of select="$toolText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'ToolMentor'">
					<xsl:value-of select="$toolMentorText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'Whitepaper'">
					<xsl:value-of select="$whitepaperText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'WorkProductDescriptor'">
					<xsl:value-of select="$workProductDescriptorText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'WorkProductType'">
					<xsl:value-of select="$workProductTypeText"/>
			</xsl:when>
			<xsl:when test="$elementType = 'WorkProductSlot'">
					<xsl:value-of select="$workProductSlotText"/>
			</xsl:when>
			<xsl:otherwise>
					<xsl:value-of select="$elementTypeName"/>
			</xsl:otherwise>
		</xsl:choose>	
	</xsl:template>
	
	<xsl:template name="wpdConcreteTypeText">
		<xsl:param name="concreteType"/>
		<xsl:param name="showFullMethodContent"/>
		<xsl:choose>
		    <xsl:when test="$showFullMethodContent='true'">
		        <xsl:choose>
			        <xsl:when test="$concreteType='Artifact'">
					    <xsl:value-of select="$artifactText"/>
			        </xsl:when>
			        <xsl:when test="$concreteType='Deliverable'">
					    <xsl:value-of select="$deliverableText"/>
			        </xsl:when>
			        <xsl:when test="$concreteType='Outcome'">
					    <xsl:value-of select="$outcomeText"/>
			        </xsl:when>					        
		        </xsl:choose>
		    </xsl:when>
		    <xsl:otherwise>
		        <xsl:choose>
		            <xsl:when test="$concreteType='Artifact'">
					    <xsl:value-of select="$artifactDescriptorText"/>
		            </xsl:when>
		            <xsl:when test="$concreteType='Deliverable'">
					    <xsl:value-of select="$deliverableDescriptorText"/>
		            </xsl:when>
		            <xsl:when test="$concreteType='Outcome'">
					    <xsl:value-of select="$outcomeDescriptorText"/>
		            </xsl:when>	
		        </xsl:choose>	    
		    </xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	
	<xsl:template name="modelInfoTextRecursively">
		<xsl:param name="modelInfoKey"/>
        <xsl:variable name="normalizedModelInfoKey" select="normalize-space($modelInfoKey)"/>
        <xsl:choose>
            <xsl:when test="contains($normalizedModelInfoKey,',')">
                <xsl:variable name="oneOfKeys" select="substring-before($normalizedModelInfoKey,',')"/>
                <xsl:variable name="oneOfValues">
               	    <xsl:call-template name="modelInfoText">
     				    <xsl:with-param name="modelInfoKey" select="$oneOfKeys"/>
			        </xsl:call-template>                
                </xsl:variable>
                <xsl:variable name="anotherKeys" select="substring-after($normalizedModelInfoKey,',')"/>
                <xsl:variable name="anotherValues">
               	    <xsl:call-template name="modelInfoTextRecursively">
     				    <xsl:with-param name="modelInfoKey" select="$anotherKeys"/>
			        </xsl:call-template>             
                </xsl:variable>
                <xsl:value-of select="concat($oneOfValues, ', ', $anotherValues)"/>
            </xsl:when>
            <xsl:otherwise>
               	<xsl:call-template name="modelInfoText">
     			    <xsl:with-param name="modelInfoKey" select="$normalizedModelInfoKey"/>
			    </xsl:call-template>   
            </xsl:otherwise>
        </xsl:choose>
	</xsl:template>
	
	<xsl:template name="modelInfoText">
		<xsl:param name="modelInfoKey"/>
		<xsl:choose>
		    <xsl:when test="$modelInfoKey='mandatoryInput'">
		        <xsl:value-of select="$modelInfo_mandatoryInputText"/>
		    </xsl:when>
		    <xsl:when test="$modelInfoKey='optionalInput'">
		        <xsl:value-of select="$modelInfo_optionalInputText"/>
		    </xsl:when>		
		    <xsl:when test="$modelInfoKey='externalInput'">
		        <xsl:value-of select="$modelInfo_externalInputText"/>
		    </xsl:when>		
		    <xsl:when test="$modelInfoKey='output'">
		        <xsl:value-of select="$modelInfo_outputText"/>
		    </xsl:when>		
		    <xsl:when test="$modelInfoKey='performedPrimarilyBy'">
		        <xsl:value-of select="$modelInfo_performedPrimarilyByText"/>
		    </xsl:when>		
		    <xsl:when test="$modelInfoKey='additionallyPerformedBy'">
		        <xsl:value-of select="$modelInfo_additionallyPerformedByText"/>
		    </xsl:when>		
		    <xsl:when test="$modelInfoKey='assistedBy'">
		        <xsl:value-of select="$modelInfo_assistedByText"/>
		    </xsl:when>		
		    <xsl:when test="$modelInfoKey='ResponsibleFor'">
		        <xsl:value-of select="$modelInfo_responsibleForText"/>
		    </xsl:when>		
		    <xsl:when test="$modelInfoKey='Modifies'">
		        <xsl:value-of select="$modelInfo_modifiesText"/>
		    </xsl:when>		
		    <xsl:when test="$modelInfoKey='primaryTasks'">
		        <xsl:value-of select="$modelInfo_primaryTasksText"/>
		    </xsl:when>		
		    <xsl:when test="$modelInfoKey='additionalTasks'">
		        <xsl:value-of select="$modelInfo_additionalTasksText"/>
		    </xsl:when>		
		    <xsl:when test="$modelInfoKey='assistTasks'">
		        <xsl:value-of select="$modelInfo_assistTasksText"/>
		    </xsl:when>		
			<xsl:otherwise>
					<xsl:value-of select="$modelInfoKey"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template name="roleKeyInfo">
		<xsl:variable name="synonyms" select="reference/Element/attribute[@name='synonyms']"/>
		<xsl:variable name="baseRole" select="reference[@name='variabilityBasedOnElement']/Element[@Type='Role']"/>
		<xsl:variable name="roleSets" select="referenceList[@name='roleSets']/Element[@Type='RoleSet']"/>
		<xsl:variable name="variabilityType" select="attribute[@name='variabilityType']"/>
		<xsl:if test="$synonyms != ''">
			<tr>
				<td>
					<xsl:value-of select="$synonymsText"/>
					<xsl:value-of select="$colon_with_space"/>
					<xsl:value-of disable-output-escaping="yes" select="$synonyms"/>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count($baseRole) > 0">
			<tr>
				<td>
					<xsl:if test="$variabilityType = 'replaces'">
						<xsl:value-of select="$replacesText"/>
						<xsl:value-of select="$colon_with_space"/>
					</xsl:if>
					<xsl:if test="$variabilityType = 'contributes'">
						<xsl:value-of select="$contributesText"/>
						<xsl:value-of select="$colon_with_space"/>
					</xsl:if>
					<xsl:if test="$variabilityType = 'extendsReplaces'">
						<xsl:value-of select="$extendsReplacesText"/>
						<xsl:value-of select="$colon_with_space"/>						
					</xsl:if>
					<xsl:if test="$variabilityType = 'extends'">
						<xsl:value-of select="$extendsText"/>
						<xsl:value-of select="$colon_with_space"/>
					</xsl:if>
					<xsl:for-each select="$baseRole">
					<xsl:sort select="@DisplayName"/>
						<xsl:call-template name="addElementWithLink">
							<xsl:with-param name="element" select="."/>
						</xsl:call-template>
					</xsl:for-each>
				</td>
			</tr>
		</xsl:if>
		<xsl:if test="count($roleSets) > 0">
			<xsl:variable name="size" select="count($roleSets)"/>
			<tr>
				<td>
					<xsl:value-of select="$roleSetsText"/>
					<xsl:value-of select="$colon_with_space"/>
					<xsl:for-each select="$roleSets">
					<xsl:sort select="@DisplayName"/>
						<xsl:call-template name="addElementWithLink">
							<xsl:with-param name="element" select="."/>
						</xsl:call-template>
						<xsl:if test="position() != $size">,&#160;</xsl:if>
					</xsl:for-each>
				</td>
			</tr>
		</xsl:if>
	</xsl:template>

	<xsl:template name="roleDescriptorKeyInfo">
		<xsl:param name="showFullMethodContent"/>
		<xsl:variable name="sourceRole" select="reference[@name='Role']/Element[@Type='Role']"/>
		<xsl:if test="$showFullMethodContent = 'false' and count($sourceRole) > 0">
			<tr>
				<td>
					<xsl:value-of select="$basedOnMethodRoleText"/>
					<xsl:value-of select="$colon_with_space"/>
					<xsl:for-each select="$sourceRole">
					<xsl:sort select="@DisplayName"/>
						<xsl:call-template name="addElementWithLink">
							<xsl:with-param name="element" select="."/>
						</xsl:call-template>
					</xsl:for-each>
				</td>
			</tr>
		</xsl:if>
	</xsl:template>

	<xsl:template name="taskKeyInfo">
		<xsl:variable name="disciplines" select="referenceList[@name='disciplines']/Element[@Type='Discipline']"/>
		<xsl:variable name="baseTask" select="reference[@name='variabilityBasedOnElement']/Element"/>
		<xsl:variable name="variabilityType" select="attribute[@name='variabilityType']"/>
		<xsl:if test="count($disciplines) > 0">
			<tr>
				<td>
					<xsl:value-of select="$disciplinesText"/>
					<xsl:value-of select="$colon_with_space"/>
					<xsl:for-each select="$disciplines">
					<xsl:sort select="@DisplayName"/>
						<xsl:call-template name="addElementWithLink">
							<xsl:with-param name="element" select="."/>
						</xsl:call-template>
						<xsl:if test="count($disciplines) > position()">,&#160;</xsl:if>
					</xsl:for-each>
					<xsl:if test="count($baseTask) > 0">
						<xsl:if test="count($disciplines) > 0">
							<br/>
						</xsl:if>
						<xsl:if test="$variabilityType = 'replaces'">
							<xsl:value-of select="$replacesText"/>
							<xsl:value-of select="$colon_with_space"/>
						</xsl:if>
						<xsl:if test="$variabilityType = 'contributes'">
							<xsl:value-of select="$contributesText"/>
							<xsl:value-of select="$colon_with_space"/>
						</xsl:if>
						<xsl:if test="$variabilityType = 'extendsReplaces'">
							<xsl:value-of select="$extendsReplacesText"/>
							<xsl:value-of select="$colon_with_space"/>
						</xsl:if>
						<xsl:if test="$variabilityType = 'extends'">
							<xsl:value-of select="$extendsText"/>
							<xsl:value-of select="$colon_with_space"/>
						</xsl:if>
						<xsl:for-each select="$baseTask">
						<xsl:sort select="@DisplayName"/>
							<xsl:call-template name="addElementWithLink">
								<xsl:with-param name="element" select="."/>
							</xsl:call-template>
						</xsl:for-each>
					</xsl:if>
				</td>
			</tr>
		</xsl:if>
	</xsl:template>

	<xsl:template name="taskDescriptorKeyInfo">
		<xsl:param name="showFullMethodContent"/>
		<xsl:variable name="sourceTask" select="reference[@name='Task']/Element[@Type='Task']"/>		
		<xsl:if test="$showFullMethodContent = 'false' and count($sourceTask) > 0">
			<tr>
				<td>
					<xsl:value-of select="$basedOnMethodTaskText"/>
					<xsl:value-of select="$colon_with_space"/>
					<xsl:for-each select="$sourceTask">
					<xsl:sort select="@DisplayName"/>
						<xsl:call-template name="addElementWithLink">
							<xsl:with-param name="element" select="."/>
						</xsl:call-template>
					</xsl:for-each>
				</td>
			</tr>
		</xsl:if>
	</xsl:template>

	<xsl:template name="workProductDescriptorKeyInfo">
		<xsl:param name="showFullMethodContent"/>
		<xsl:variable name="sourceWorkProduct" select="reference[@name='WorkProduct']/Element"/>
		<xsl:if test="$showFullMethodContent = 'false' and count($sourceWorkProduct) > 0">
			<tr>
				<td>
					<xsl:value-of select="$basedOnMethodWorkProductText"/>
					<xsl:value-of select="$colon_with_space"/>
					<xsl:for-each select="$sourceWorkProduct">
					<xsl:sort select="@DisplayName"/>
						<xsl:call-template name="addElementWithLink">
							<xsl:with-param name="element" select="."/>
						</xsl:call-template>
					</xsl:for-each>
				</td>
			</tr>
		</xsl:if>
	</xsl:template>

	<xsl:template name="workProductKeyInfo">
		<xsl:variable name="domains" select="referenceList[@name='domains']/Element"/>
		<xsl:variable name="baseWorkProduct" select="reference[@name='variabilityBasedOnElement']/Element"/>
		<xsl:variable name="workProductTypes" select="referenceList[@name='workProductTypes']/Element[@Type='WorkProductType']"/>
		<xsl:variable name="states" select="@States"/>
		<xsl:variable name="variabilityType" select="attribute[@name='variabilityType']"/>
		<tr>
			<td>
				<xsl:if test="count($domains) > 0">
					<xsl:value-of select="$domainsText"/>
					<xsl:value-of select="$colon_with_space"/>
					<xsl:for-each select="$domains">
					<xsl:sort select="@DisplayName"/>
						<xsl:call-template name="addElementWithLink">
							<xsl:with-param name="element" select="."/>
						</xsl:call-template>
						<xsl:if test="count($domains) > position()">,&#160;</xsl:if>
					</xsl:for-each>
				</xsl:if>
				<xsl:if test="count($workProductTypes) > 0">
					<xsl:if test="count($domains) > 0">
						<br/>
					</xsl:if>
					<xsl:value-of select="$workProductTypesText"/>
					<xsl:value-of select="$colon_with_space"/>
					<xsl:for-each select="$workProductTypes">
					<xsl:sort select="@DisplayName"/>
						<xsl:call-template name="addElementWithLink">
							<xsl:with-param name="element" select="."/>
						</xsl:call-template>
						<xsl:if test="count($workProductTypes) > position()">,&#160;</xsl:if>
					</xsl:for-each>
				</xsl:if>
				<xsl:if test="count($states) > 0">
					<xsl:if test="count($domains) + count($workProductTypes) > 0">
						<br/>
					</xsl:if>
					<xsl:value-of select="$wpStatesText"/>
					<xsl:value-of select="$colon_with_space"/>
					<xsl:value-of select="$states"/>
				</xsl:if>
				<xsl:if test="count($baseWorkProduct) > 0">
					<xsl:if test="count($domains) + count($workProductTypes) + count($states) > 0">
						<br/>
					</xsl:if>
					<xsl:if test="$variabilityType = 'replaces'">
						<xsl:value-of select="$replacesText"/>
						<xsl:value-of select="$colon_with_space"/>
					</xsl:if>
					<xsl:if test="$variabilityType = 'contributes'">
						<xsl:value-of select="$contributesText"/>
						<xsl:value-of select="$colon_with_space"/>
					</xsl:if>
					<xsl:if test="$variabilityType = 'extendsReplaces'">
						<xsl:value-of select="$extendsReplacesText"/>
						<xsl:value-of select="$colon_with_space"/>
					</xsl:if>
					<xsl:if test="$variabilityType = 'extends'">
						<xsl:value-of select="$extendsText"/>
						<xsl:value-of select="$colon_with_space"/>
					</xsl:if>
					<xsl:for-each select="$baseWorkProduct">
					<xsl:sort select="@DisplayName"/>
						<xsl:call-template name="addElementWithLink">
							<xsl:with-param name="element" select="."/>
						</xsl:call-template>
					</xsl:for-each>
				</xsl:if>
			</td>
		</tr>
	</xsl:template>

	<xsl:template name="toolMentorKeyInfo">
		<xsl:variable name="tool" select="reference[@name='tool']/Element"/>
		<xsl:variable name="baseElement" select="reference[@name='variabilityBasedOnElement']/Element"/>
		<xsl:variable name="variabilityType" select="attribute[@name='variabilityType']"/>
		<tr>
			<td>
				<xsl:if test="count($tool) > 0">
					<xsl:value-of select="$toolText"/>
					<xsl:value-of select="$colon_with_space"/>
					<xsl:for-each select="$tool">
					<xsl:sort select="@DisplayName"/>
						<xsl:call-template name="addElementWithLink">
							<xsl:with-param name="element" select="."/>
						</xsl:call-template>
						<xsl:text> </xsl:text>
					</xsl:for-each>
				</xsl:if>
				<xsl:if test="count($baseElement) > 0">
					<xsl:if test="count($tool)  > 0">
						<br/>
					</xsl:if>
					<xsl:if test="$variabilityType = 'replaces'">
						<xsl:value-of select="$replacesText"/>
						<xsl:value-of select="$colon_with_space"/>
					</xsl:if>
					<xsl:if test="$variabilityType = 'contributes'">
						<xsl:value-of select="$contributesText"/>
						<xsl:value-of select="$colon_with_space"/>
					</xsl:if>
					<xsl:if test="$variabilityType = 'extendsReplaces'">
						<xsl:value-of select="$extendsReplacesText"/>
						<xsl:value-of select="$colon_with_space"/>
					</xsl:if>
					<xsl:if test="$variabilityType = 'extends'">
						<xsl:value-of select="$extendsText"/>
						<xsl:value-of select="$colon_with_space"/>
					</xsl:if>
					<xsl:for-each select="$baseElement">
					<xsl:sort select="@DisplayName"/>
						<xsl:call-template name="addElementWithLink">
							<xsl:with-param name="element" select="."/>
						</xsl:call-template>
					</xsl:for-each>
				</xsl:if>
			</td>
		</tr>
	</xsl:template>

	<xsl:template name="overviewSeparator">
		<xsl:variable name="imagePath" select="concat(/Element/@BackPath, 'images/')"/>
		<table id="overviewSeparator" width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="overviewSeparator">
					<img src="{$imagePath}shim.gif" alt="" title="" height="1"/>
				</td>
			</tr>
			<tr>
				<td>
					<img src="{$imagePath}shim.gif" alt="" title="" height="5"/>
				</td>
			</tr>
		</table>
	</xsl:template>
	
	<xsl:template name="elementPageTitleText">
		<xsl:param name="elementType"/>
		<xsl:param name="elementTypeName"/>
		<xsl:param name="elementPresentationName"/>
		<xsl:param name="workProductType"/>
		<xsl:variable name="showFullMethodContent" select="@ShowFullMethodContent"/>
		<xsl:variable name="prefix" select="attribute[@name='prefix']"/>
		<xsl:variable name="externalId" select="reference/Element/attribute[@name='externalId']"/>
		
		<xsl:choose>
			<xsl:when test="$elementType = 'CustomCategory' or $elementType = 'SupportingMaterial' or $elementType = 'Summary'">
				<xsl:value-of select="$elementPresentationName"/>
			</xsl:when>
			<xsl:otherwise>							
				<xsl:choose>
					<xsl:when test="$showFullMethodContent = 'true'">
						<xsl:choose>										
							<xsl:when test="$elementType = 'TaskDescriptor'">
								<xsl:value-of select="$taskText"/>
							</xsl:when>
							<xsl:when test="$elementType = 'RoleDescriptor'">
								<xsl:value-of select="$roleText"/>
							</xsl:when>
							<xsl:when test="$elementType = 'WorkProductDescriptor'">
								<xsl:choose>
									<!--fix the workProducttype is not G11N with skin problem by shijin Defect 42723-->
									<xsl:when test="$workProductType = 'Artifact'">
										<xsl:value-of select="concat($workProductDescriptorText, ' (', $artifactText, ')')"/>
									</xsl:when>
									<xsl:when test="$workProductType = 'Deliverable'">
										<xsl:value-of select="concat($workProductDescriptorText, ' (', $deliverableText, ')')"/>
									</xsl:when>
									<xsl:when test="$workProductType = 'Outcome'">
										<xsl:value-of select="concat($workProductDescriptorText, ' (', $outcomeText, ')')"/>
									</xsl:when>
									<xsl:otherwise>
										<xsl:value-of select="$workProductText"/>
									</xsl:otherwise>	
								</xsl:choose>											
							</xsl:when>
							<xsl:otherwise>
							<!--
								<xsl:value-of select="$elementTypeName"/>
							-->
								<xsl:call-template name="elementTypeText">
											<xsl:with-param name="elementType" select="$elementType"/>
											<xsl:with-param name="elementTypeName" select="$elementTypeName"/>
								</xsl:call-template>
							</xsl:otherwise>
						</xsl:choose>									
					</xsl:when>
					<xsl:when test="$elementType = 'WorkProductDescriptor'">
						<xsl:choose>
							<!--fix the workProducttype is not G11N with skin problem by shijin Defect 42723-->
							<xsl:when test="$workProductType = 'Artifact'">
								<xsl:value-of select="concat($workProductDescriptorText, ' (', $artifactText, ')')"/>
							</xsl:when>
							<xsl:when test="$workProductType = 'Deliverable'">
								<xsl:value-of select="concat($workProductDescriptorText, ' (', $deliverableText, ')')"/>
							</xsl:when>
							<xsl:when test="$workProductType = 'Outcome'">
								<xsl:value-of select="concat($workProductDescriptorText, ' (', $outcomeText, ')')"/>
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="$workProductText"/>
							</xsl:otherwise>	
						</xsl:choose>	
					</xsl:when>
					<xsl:otherwise>
							<!--
								<xsl:value-of select="$elementTypeName"/>
							-->
								<xsl:call-template name="elementTypeText">
											<xsl:with-param name="elementType" select="$elementType"/>
											<xsl:with-param name="elementTypeName" select="$elementTypeName"/>
								</xsl:call-template>
					</xsl:otherwise>
				</xsl:choose>
				<xsl:value-of select="$colon_with_space"/>
				<xsl:choose>
					<xsl:when test="$prefix != ''">
						<xsl:value-of select="$prefix"/> - <xsl:value-of select="$elementPresentationName"/>
					</xsl:when>
					<xsl:otherwise>
						<xsl:value-of select="$elementPresentationName"/>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:otherwise>						
		</xsl:choose>
		<xsl:if test="$externalId != ''">
			(<xsl:value-of select="$externalId"/>)
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>

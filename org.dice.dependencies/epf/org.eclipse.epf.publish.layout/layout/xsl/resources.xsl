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
	
	
<!-- the element type text -->
<xsl:param name="activityText"/>
<xsl:param name="artifactText"/>
<xsl:param name="artifactDescriptorText"/>
<xsl:param name="capabilityPatternText"/>
<xsl:param name="checklistText"/>
<xsl:param name="compositeRoleText"/>
<xsl:param name="conceptText"/>
<xsl:param name="customCategoryText"/>
<xsl:param name="deliverableText"/>
<xsl:param name="deliverableDescriptorText"/>
<xsl:param name="deliveryProcessText"/>
<xsl:param name="disciplineText"/>
<xsl:param name="disciplineGroupingText"/>
<xsl:param name="domainText"/>
<xsl:param name="estimationConsiderationsText"/>
<xsl:param name="exampleText"/>
<xsl:param name="guidelineText"/>
<xsl:param name="iterationText"/>
<xsl:param name="milestoneText"/>
<xsl:param name="outcomeText"/>
<xsl:param name="outcomeDescriptorText"/>
<xsl:param name="phaseText"/>
<xsl:param name="practiceText"/>
<xsl:param name="reportText"/>
<xsl:param name="reusableAssetText"/>
<xsl:param name="roadmapText"/>
<xsl:param name="roleText"/>
<xsl:param name="roleDescriptorText"/>
<xsl:param name="roleSetText"/>
<xsl:param name="roleSetGroupingText"/>
<xsl:param name="supportingMaterialText"/>
<xsl:param name="taskText"/>
<xsl:param name="taskDescriptorText"/>
<xsl:param name="teamProfileText"/>
<xsl:param name="templateText"/>
<xsl:param name="termDefinitionText"/>
<xsl:param name="toolText"/>
<xsl:param name="toolMentorText"/>
<xsl:param name="whitepaperText"/>
<xsl:param name="workProductDescriptorText"/>
<xsl:param name="workProductTypeText"/>
<xsl:param name="workProductSlotText"/>

<!-- other text variables -->
	<xsl:param name="activityEntryStateText"/>
	<xsl:param name="activityExitStateText"/>
	<xsl:param name="additionalInfoText"/>
	<xsl:param name="additionallyPerformsText"/>
	<xsl:param name="additionalPerformerText"/>
	<xsl:param name="additionalText"/>
	<xsl:param name="aggregatesText"/>
	<xsl:param name="alternativesText"/>
	<xsl:param name="applicationText"/>
	<xsl:param name="assetsText"/>
	<xsl:param name="assignmentApproachesText"/>
	<xsl:param name="assistingText"/>
	<xsl:param name="attachedFilesText"/>
	<xsl:param name="backgroundText"/>
	<xsl:param name="basedOnMethodRoleText"/>
	<xsl:param name="basedOnMethodTaskText"/>
	<xsl:param name="basedOnMethodWorkProductText"/>
	<xsl:param name="breakdownElementText"/>
	<xsl:param name="briefOutlineText"/>
	<xsl:param name="categoriesText"/>
	<xsl:param name="checkItemsText"/>
	<xsl:param name="checklistsText"/>

	<xsl:param name="collapseAllText"/>
	<xsl:param name="communicationsMaterialsText"/>
	<xsl:param name="conceptsText"/>
	<xsl:param name="containedArtifactsText"/>
	<xsl:param name="containerArtifactText"/>
	<xsl:param name="contentElementsText"/>
	<xsl:param name="contentReferencesText"/>
	<xsl:param name="referenceQualifiersText"/>
	<xsl:param name="contentsText"/>
	<xsl:param name="contextText"/>
	<xsl:param name="contributesText"/>
	<xsl:param name="deliverablePropertiesText"/>
	<xsl:param name="deliveredPartsText"/>
	<xsl:param name="dependentText"/>
	<xsl:param name="descriptionText"/>
	<xsl:param name="descriptorsText"/>	
	<xsl:param name="disciplinesText"/>

	<xsl:param name="domainsText"/>
	<xsl:param name="educationMaterialsText"/>
	<xsl:param name="elementDoesNotExistText"/>
	<xsl:param name="elementMightBeDeletedText"/>
	<xsl:param name="elementNameText"/>
	<xsl:param name="elementTypeText"/>
	<xsl:param name="entryStateText"/>
	<xsl:param name="estimatingTechniqueText"/>
	<xsl:param name="eventDrivenText"/>
	<xsl:param name="examplesText"/>
	<xsl:param name="exitStateText"/>
	<xsl:param name="expandAllText"/>
	<xsl:param name="extendsText"/>
	<xsl:param name="externalDescriptionText"/>
	<xsl:param name="externalIdText"/>
	<xsl:param name="externalText"/>
	<xsl:param name="goalsText"/>
	<xsl:param name="guidelinesText"/>
	<xsl:param name="guidText"/>
	<xsl:param name="illustrationsText"/>
	<xsl:param name="impactingText"/>
	<xsl:param name="impactOfNotHavingText"/>
	<xsl:param name="includedPatternsText"/>
	<xsl:param name="indexText"/>
	<xsl:param name="infoAboutMissingElementText"/>
	<xsl:param name="inputsText"/>
	<xsl:param name="inputToText"/>
	<xsl:param name="isNotValidText"/>
	<xsl:param name="keyConsiderationsText"/>
	<xsl:param name="levelsOfAdoptionText"/>
	<xsl:param name="mainDescriptionText"/>
	<xsl:param name="mainResponsibleText"/>
	<xsl:param name="mainText"/>
	<xsl:param name="mandatoryText"/>
	<xsl:param name="missingElementText"/>
	<xsl:param name="modelInfoText"/>
	<xsl:param name="modifiedByText"/>
	<xsl:param name="modifiesText"/>
	<xsl:param name="moreInfoText"/>
	<xsl:param name="multipleOccurrencesText"/>
	<xsl:param name="noneText"/>
	<xsl:param name="notationText"/>
	<xsl:param name="notAvailableText"/>
	<xsl:param name="ongoingText"/>
	<xsl:param name="optionalText"/>
	<xsl:param name="outputFromText"/>
	<xsl:param name="outputsText"/>
	<xsl:param name="packagingGuidanceText"/>
	<xsl:param name="packagingInstructionsText"/>
	<xsl:param name="pageNotInstalledText"/>
	<xsl:param name="parentActivitiesText"/>
	<xsl:param name="performingRolesText"/>
	<xsl:param name="performsText"/>
	<xsl:param name="plannedText"/>
	<xsl:param name="planningDataText"/>
	<xsl:param name="pleaseFixLinkText"/>
	<xsl:param name="pluginNameText"/>
	<xsl:param name="predecessorsText"/>
	<xsl:param name="predecessorText"/>
	<xsl:param name="prefixText"/>
	<xsl:param name="primaryPerformerText"/>
	<xsl:param name="problemText"/>
	<xsl:param name="projectCharacteristicsText"/>
	<xsl:param name="projectMemberExpertiseText"/>
	<xsl:param name="projectSpecificsText"/>
	<xsl:param name="propertiesText"/>
	<xsl:param name="purposeText"/>
	<xsl:param name="reasonsForNotNeedingText"/>
	<xsl:param name="referenceWorkflowsText"/>
	<xsl:param name="relatedElementsText"/>
	<xsl:param name="relationshipsText"/>
	<xsl:param name="repeatableText"/>
	<xsl:param name="replacesText"/>
	<xsl:param name="reportsText"/>
	<xsl:param name="representationOptionsText"/>
	<xsl:param name="requiredResultsText"/>
	<xsl:param name="responsibleForText"/>
	<xsl:param name="responsibleText"/>
	<xsl:param name="reusableAssetsText"/>
	<xsl:param name="riskLevelText"/>
	<xsl:param name="practice_application_text"/>

	<xsl:param name="roleSetsText"/>
	<xsl:param name="rolesText"/>
	<xsl:param name="scaleText"/>
	<xsl:param name="scopeText"/>
	<xsl:param name="skillsText"/>
	<xsl:param name="staffingText"/>
	<xsl:param name="stepsText"/>
	<xsl:param name="subcategoriesText"/>
	<xsl:param name="subdomainsText"/>
	<xsl:param name="subPracticesText"/>
	<xsl:param name="subTeamsText"/>
	<xsl:param name="summaryText"/>
	<xsl:param name="supportingMaterialsText"/>
	<xsl:param name="synonymsText"/>
	<xsl:param name="tailoringText"/>
	<xsl:param name="tasksText"/>
	<xsl:param name="tbsText"/>
	<xsl:param name="teamProfileBreakdownText"/>
	<xsl:param name="teamRolesText"/>
	<xsl:param name="teamStructureText"/>
	<xsl:param name="teamText"/>
	<xsl:param name="techniquesText"/>
	<xsl:param name="templatesText"/>
	<xsl:param name="toolMentorsText"/>

	<xsl:param name="typeofContractText"/>
	<xsl:param name="typeText"/>
	<xsl:param name="usageGuidanceText"/>
	<xsl:param name="usageNotesText"/>
	<xsl:param name="usageText"/>
	<xsl:param name="wbsText"/>
	<xsl:param name="whitepapersText"/>
	<xsl:param name="workBreakdownText"/>
	<xsl:param name="workflowText"/>
	<xsl:param name="workProductBreakdownText"/>
	<xsl:param name="workProductDependenciesText"/>
	<xsl:param name="workProductsText"/>
	<xsl:param name="workProductTypesText"/>
	<xsl:param name="wpbsText"/>

	<xsl:param name="activityReferencesText"/>
	<xsl:param name="colon_with_space"/>
	<xsl:param name="subdisciplinesText"/>
	<xsl:param name="representationText"/>
	<xsl:param name="extendsReplacesText"/>

	<xsl:param name="workProductText"/>
	<xsl:param name="processUsageText"/>
	
	<xsl:param name="fulfilledSlotsText"/>
	<xsl:param name="fulfillingWorkProductsText"/>
	
	<xsl:param name="deliverableParentsText"/>
	<xsl:param name="practicesText"/>
	
	<xsl:param name="modelInfo_mandatoryInputText"/>
	<xsl:param name="modelInfo_optionalInputText"/>
	<xsl:param name="modelInfo_externalInputText"/>
	<xsl:param name="modelInfo_outputText"/>
	<xsl:param name="modelInfo_performedPrimarilyByText"/>
	<xsl:param name="modelInfo_additionallyPerformedByText"/>
	<xsl:param name="modelInfo_assistedByText"/>
	<xsl:param name="modelInfo_responsibleForText"/>
	<xsl:param name="modelInfo_modifiesText"/>
	<xsl:param name="modelInfo_primaryTasksText"/>
	<xsl:param name="modelInfo_additionalTasksText"/>
	<xsl:param name="modelInfo_assistTasksText"/>
	<xsl:param name="backgroundImage"/>
	
	<xsl:param name="wpStatesText"/>
	
	<xsl:param name="udtText"/>
	
	<xsl:param name="guidancesText"/>
	<xsl:param name="primaryPerformsText"/>
</xsl:stylesheet>

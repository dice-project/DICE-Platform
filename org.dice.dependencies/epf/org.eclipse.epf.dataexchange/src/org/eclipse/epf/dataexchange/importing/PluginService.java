//------------------------------------------------------------------------------
// Copyright (c) 2005, 2006 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.dataexchange.importing;

import java.util.List;

import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.CapabilityPattern;
import org.eclipse.epf.uma.Checklist;
import org.eclipse.epf.uma.Concept;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.Deliverable;
import org.eclipse.epf.uma.DeliveryProcess;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.DisciplineGrouping;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.Example;
import org.eclipse.epf.uma.Guidance;
import org.eclipse.epf.uma.Iteration;
import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Outcome;
import org.eclipse.epf.uma.Phase;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.Report;
import org.eclipse.epf.uma.ReusableAsset;
import org.eclipse.epf.uma.Roadmap;
import org.eclipse.epf.uma.Role;
import org.eclipse.epf.uma.RoleSet;
import org.eclipse.epf.uma.RoleSetGrouping;
import org.eclipse.epf.uma.SupportingMaterial;
import org.eclipse.epf.uma.Task;
import org.eclipse.epf.uma.Template;
import org.eclipse.epf.uma.TermDefinition;
import org.eclipse.epf.uma.Tool;
import org.eclipse.epf.uma.ToolMentor;
import org.eclipse.epf.uma.Whitepaper;
import org.eclipse.epf.uma.WorkProductType;

/**
 * Plugin service for importing external plugin data into the current library
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public interface PluginService {

	public ContentPackage getContentPackageRoot();

	/**
	 * create a Discipline object add add to the system default Disciplines package
	 * @param name
	 * @return Discipline
	 */
	public Discipline createDiscipline();

	/**
	 * create a DisciplineGrouping object add add to the system default Disciplines package
	 * @param name
	 * @return DisciplineGrouping
	 */
	public DisciplineGrouping createDisciplineGrouping();

	/**
	 * create a Tool object add add to the system default Tools package
	 * @param name
	 * @return Tool
	 */
	public Tool createTool();

	/**
	 * create a Domain object add add to the system default Domains package
	 * @param name
	 * @return Domain
	 */
	public Domain createDomain();

	/**
	 * create a WorkProductType object add add to the system default WorkProductTypes package
	 * @param name
	 * @return WorkProductType
	 */
	public WorkProductType createWorkProductType();

	/**
	 * create a RoleSet object add add to the system default RoleSets package
	 * @param name
	 * @return RoleSet
	 */
	public RoleSet createRoleSet();

	/**
	 * create a RoleSetGrouping object add add to the system default RoleSets package
	 * @param name
	 * @return RoleSetGrouping
	 */
	public RoleSetGrouping createRoleSetGrouping();

	/**
	 * create a CustomCategory object add add to the system default CustomCategories package
	 * @param name
	 * @return CustomCategory
	 */
	public CustomCategory createCustomCategory(CustomCategory parent);

	/**
	 * create a CapabilityPattern object add add to the system default CapabilityPatterns package
	 * perform other initializations as needed, such as create the containing ProcessComponent.
	 * @param name
	 * @return ProcessService a ProcessService that contains the created CapabilityPattern
	 */
	public CapabilityPattern createCapabilityPattern(MethodConfiguration defaultConfig);

	/**
	 * create a DeliveryProcess object add add to the system default DeliveryProcesses package.
	 * perform other initializations as needed, such as create the containing ProcessComponent.
	 * @param name
	 * @return ProcessService a ProcessService that contains the created DeliveryProcess
	 */
	public DeliveryProcess createDeliveryProcess(MethodConfiguration defaultConfig);

	/**
	 * create a content package as the child of the internal root content package,
	 * @return ContentPackageService a ContentPackageService that contains the created ContentPackage
	 */
	public ContentPackage createContentPackage(ContentPackage container);


	/**
	 * create a new child package
	 * @return ContentPackageService a ContentPackageService for the created package
	 */
	//public ContentPackage createChildPackage(ContentPackage container);

	public Artifact createArtifact(ContentPackage container);
	public Checklist createChecklist(ContentPackage container);
	public Concept createConcept(ContentPackage container);
	public Deliverable createDeliverable(ContentPackage container);
	public Example createExample(ContentPackage container);
	public Guidance createGuidance(ContentPackage container);
	public Outcome createOutcome(ContentPackage container);
	public Practice createPractice(ContentPackage container);
	public Report createReport(ContentPackage container);
	public ReusableAsset createReusableAsset(ContentPackage container);
	public Roadmap createRoadmap(ContentPackage container);
	public Role createRole(ContentPackage container);
	public SupportingMaterial createSupportingMaterial(ContentPackage container);
	public Task createTask(ContentPackage container);
	public Template createTemplate(ContentPackage container);
	public TermDefinition createTermDefinition(ContentPackage container);
	public ToolMentor createToolMentor(ContentPackage container);
	public Whitepaper createWhitepaper(ContentPackage container);


	/**
	 * create a new Activity as a breakdown element of the servicing element.
	 * @return Activity
	 */
	public Activity createActivity(Activity superActivity);

	/**
	 * create a new Phase as a breakdown element of the servicing element.
	 * @return Phase
	 */
	public Phase createPhase(Activity superActivity);

	/**
	 * create a new Iteration as a breakdown element of the servicing element.
	 * @return Iteration
	 */
	public Iteration createIteration(Activity superActivity);

	/**
	 * create a new Milestone as a breakdown element of the servicing element.
	 * @return Milestone
	 */
	public Milestone createMilestone(Activity superActivity);


	/**
	 * add the task and create the breakdown elements associated with the task
	 * call this method only when the cross references (Roles, workproducts) of the Task is already established.
	 * @param element
	 */
	public void addTaskToActivity(Activity act, Task element);

	public void addTasksToActivity(Activity act, List tasks);

}

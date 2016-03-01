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
package org.eclipse.epf.dataexchange.internal.importing;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.epf.dataexchange.importing.PluginService;
import org.eclipse.epf.library.edit.process.command.WBSDropCommand;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.edit.util.TngUtil;
import org.eclipse.epf.library.services.SafeUpdateController;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.BreakdownElement;
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
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Milestone;
import org.eclipse.epf.uma.Outcome;
import org.eclipse.epf.uma.Phase;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;
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
import org.eclipse.epf.uma.UmaFactory;
import org.eclipse.epf.uma.Whitepaper;
import org.eclipse.epf.uma.WorkProductType;
import org.eclipse.epf.uma.util.UmaUtil;


/**
 * Plugin service for importing external plugin data into the current library
 * 
 * @author Jinhua Xi
 * @since 1.0
 *
 */
public class PluginServiceImpl implements PluginService {

	MethodPlugin plugin;

	public PluginServiceImpl(MethodPlugin element)
	{
		plugin = element;
	}

	/**
	 * get the method plughin
	 * @return {@link MethodPlugin}
	 */
	public MethodPlugin getPlugin()
	{
		return (MethodPlugin)plugin;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#getContentPackageRoot()
	 */
	public ContentPackage getContentPackageRoot()
	{
		return UmaUtil.findContentPackage(plugin, ModelStructure.DEFAULT.coreContentPath);
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createDiscipline()
	 */
	public Discipline createDiscipline() {
		Discipline d = UmaFactory.eINSTANCE.createDiscipline();
		ContentPackage pkg = (ContentPackage)UmaUtil.findMethodPackage(getPlugin(), ModelStructure.DEFAULT.disciplineDefinitionPath);
		pkg.getContentElements().add(d);

		return d;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createDisciplineGrouping()
	 */
	public DisciplineGrouping createDisciplineGrouping() {
		DisciplineGrouping d = UmaFactory.eINSTANCE.createDisciplineGrouping();
		ContentPackage pkg = (ContentPackage)UmaUtil.findMethodPackage(getPlugin(), ModelStructure.DEFAULT.disciplineDefinitionPath);
		pkg.getContentElements().add(d);

		return d;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createTool()
	 */
	public Tool createTool() {
		Tool tool = UmaFactory.eINSTANCE.createTool();
		ContentPackage pkg = UmaUtil.findContentPackage(getPlugin(), ModelStructure.DEFAULT.toolPath);
		((ContentPackage)pkg).getContentElements().add(tool);
		return tool;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createDomain()
	 */
	public Domain createDomain() {
		Domain d = UmaFactory.eINSTANCE.createDomain();
		ContentPackage pkg = UmaUtil.findContentPackage(getPlugin(), ModelStructure.DEFAULT.domainPath);
		((ContentPackage)pkg).getContentElements().add(d);
		return d;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createWorkProductType()
	 */
	public WorkProductType createWorkProductType() {
		WorkProductType w = UmaFactory.eINSTANCE.createWorkProductType();
		ContentPackage pkg = UmaUtil.findContentPackage(getPlugin(), ModelStructure.DEFAULT.workProductTypePath);
		((ContentPackage)pkg).getContentElements().add(w);
		return w;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createRoleSet()
	 */
	public RoleSet createRoleSet() {
		RoleSet r = UmaFactory.eINSTANCE.createRoleSet();
		ContentPackage pkg = UmaUtil.findContentPackage(getPlugin(), ModelStructure.DEFAULT.roleSetPath);
		((ContentPackage)pkg).getContentElements().add(r);
		return r;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createRoleSetGrouping()
	 */
	public RoleSetGrouping createRoleSetGrouping() {
		RoleSetGrouping r = UmaFactory.eINSTANCE.createRoleSetGrouping();
		ContentPackage pkg = UmaUtil.findContentPackage(getPlugin(), ModelStructure.DEFAULT.roleSetPath);
		((ContentPackage)pkg).getContentElements().add(r);
		return r;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createCustomCategory(CustomCategory)
	 */
	public CustomCategory createCustomCategory(CustomCategory parent) {
		ContentPackage pkg = UmaUtil.findContentPackage(getPlugin(), ModelStructure.DEFAULT.customCategoryPath);
		CustomCategory c = UmaFactory.eINSTANCE.createCustomCategory();

		// also need to add the category object into the parent package
		pkg.getContentElements().add(c);
		if ( parent != null )
		{
			((CustomCategory)parent).getCategorizedElements().add(c);
		}
		else
		{
			// add to the root category
			TngUtil.getRootCustomCategory(getPlugin()).getCategorizedElements().add(c);
		}

		return c;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createCapabilityPattern(MethodConfiguration)
	 */
	public CapabilityPattern createCapabilityPattern(MethodConfiguration defaultConfig) {

		CapabilityPattern cp = UmaFactory.eINSTANCE.createCapabilityPattern();
		cp.setDefaultContext(defaultConfig);

		ProcessPackage capabilityPatternPkgs = (ProcessPackage)UmaUtil.findMethodPackage(getPlugin(), ModelStructure.DEFAULT.capabilityPatternPath);
		List items = capabilityPatternPkgs.getChildPackages();

		// need to create a package for this capability pattern
		//System.out.println("Create pkg, TODIO");
		// to add a capability process, you need to create a ProcessComponent package to hold the process
		org.eclipse.epf.uma.ProcessComponent pc = UmaFactory.eINSTANCE.createProcessComponent();
		pc.setGuid(EcoreUtil.generateUUID());
		items.add(pc);
		pc.setProcess(cp);

		return cp;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createDeliveryProcess(MethodConfiguration)
	 */
	public DeliveryProcess createDeliveryProcess(MethodConfiguration defaultConfig) {
		DeliveryProcess dp = UmaFactory.eINSTANCE.createDeliveryProcess();
		dp.setDefaultContext(defaultConfig);

		// this is the package that stores all capability patterns
		MethodPlugin plugin = getPlugin();
		ProcessPackage deliveryProcessPkgs = (ProcessPackage)UmaUtil.findMethodPackage(plugin, ModelStructure.DEFAULT.deliveryProcessPath);
		List items = deliveryProcessPkgs.getChildPackages();

		// you need to create a ProcessComponent package to hold the process
		org.eclipse.epf.uma.ProcessComponent pc = UmaFactory.eINSTANCE.createProcessComponent();
		pc.setGuid(EcoreUtil.generateUUID());
		items.add(pc);
		pc.setProcess(dp);

		return dp;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createContentPackage(ContentPackage)
	 */
	public ContentPackage createContentPackage(ContentPackage container) {

		ContentPackage pkg = UmaFactory.eINSTANCE.createContentPackage();
		if ( container == null)
		{
			container = UmaUtil.findContentPackage(getPlugin(), ModelStructure.DEFAULT.coreContentPath);
		}
		container.getChildPackages().add(pkg);
		return pkg;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createArtifact(ContentPackage)
	 */
	public Artifact createArtifact(ContentPackage container) {
		Artifact e = UmaFactory.eINSTANCE.createArtifact();
		container.getContentElements().add(e);
		return e;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createChecklist(ContentPackage)
	 */
	public Checklist createChecklist(ContentPackage container) {
		Checklist e = UmaFactory.eINSTANCE.createChecklist();
		container.getContentElements().add(e);
		return e;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createConcept(ContentPackage)
	 */
	public Concept createConcept(ContentPackage container) {
		Concept e = UmaFactory.eINSTANCE.createConcept();
		container.getContentElements().add(e);
		return e;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createDeliverable(ContentPackage)
	 */
	public Deliverable createDeliverable(ContentPackage container) {
		Deliverable e = UmaFactory.eINSTANCE.createDeliverable();
		container.getContentElements().add(e);
		return e;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createExample(ContentPackage)
	 */
	public Example createExample(ContentPackage container) {
		Example e = UmaFactory.eINSTANCE.createExample();
		container.getContentElements().add(e);
		return e;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createGuidance(ContentPackage)
	 */
	public Guidance createGuidance(ContentPackage container) {
		Guidance e = UmaFactory.eINSTANCE.createGuideline();
		container.getContentElements().add(e);
		return e;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createOutcome(ContentPackage)
	 */
	public Outcome createOutcome(ContentPackage container) {
		Outcome e = UmaFactory.eINSTANCE.createOutcome();
		container.getContentElements().add(e);
		return e;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createPractice(ContentPackage)
	 */
	public Practice createPractice(ContentPackage container) {
		Practice e = UmaFactory.eINSTANCE.createPractice();
		container.getContentElements().add(e);
		return e;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createReport(ContentPackage)
	 */
	public Report createReport(ContentPackage container) {
		Report e = UmaFactory.eINSTANCE.createReport();
		container.getContentElements().add(e);
		return e;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createReusableAsset(ContentPackage)
	 */
	public ReusableAsset createReusableAsset(ContentPackage container) {
		ReusableAsset e = UmaFactory.eINSTANCE.createReusableAsset();
		container.getContentElements().add(e);
		return e;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createRoadmap(ContentPackage)
	 */
	public Roadmap createRoadmap(ContentPackage container) {
		Roadmap e = UmaFactory.eINSTANCE.createRoadmap();
		container.getContentElements().add(e);
		return e;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createRole(ContentPackage)
	 */
	public Role createRole(ContentPackage container) {
		Role e = UmaFactory.eINSTANCE.createRole();
		container.getContentElements().add(e);
		return e;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createSupportingMaterial(ContentPackage)
	 */
	public SupportingMaterial createSupportingMaterial(ContentPackage container) {
		SupportingMaterial e = UmaFactory.eINSTANCE.createSupportingMaterial();
		container.getContentElements().add(e);
		return e;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createTask(ContentPackage)
	 */
	public Task createTask(ContentPackage container) {
		Task e = UmaFactory.eINSTANCE.createTask();
		container.getContentElements().add(e);
		return e;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createTemplate(ContentPackage)
	 */
	public Template createTemplate(ContentPackage container) {
		Template e = UmaFactory.eINSTANCE.createTemplate();
		container.getContentElements().add(e);
		return e;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createTermDefinition(ContentPackage)
	 */
	public TermDefinition createTermDefinition(ContentPackage container) {
		TermDefinition e = UmaFactory.eINSTANCE.createTermDefinition();
		container.getContentElements().add(e);
		return e;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createToolMentor(ContentPackage)
	 */
	public ToolMentor createToolMentor(ContentPackage container) {
		ToolMentor e = UmaFactory.eINSTANCE.createToolMentor();
		container.getContentElements().add(e);
		return e;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createWhitepaper(ContentPackage)
	 */
	public Whitepaper createWhitepaper(ContentPackage container) {
		Whitepaper e = UmaFactory.eINSTANCE.createWhitepaper();
		container.getContentElements().add(e);
		return e;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createActivity(Activity)
	 */
	public Activity createActivity(Activity superActivity) {
		Activity e = UmaFactory.eINSTANCE.createActivity();
		superActivity.getBreakdownElements().add(e);

		addActivityToContainer(e);
		
		return e;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createPhase(Activity)
	 */
	public Phase createPhase(Activity superActivity) {
		Phase e = UmaFactory.eINSTANCE.createPhase();
		superActivity.getBreakdownElements().add(e);

		addActivityToContainer(e);

		return e;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createIteration(Activity)
	 */
	public Iteration createIteration(Activity superActivity) {
		Iteration e = UmaFactory.eINSTANCE.createIteration();
		superActivity.getBreakdownElements().add(e);

		addActivityToContainer(e);

		return e;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#createMilestone(Activity)
	 */
	public Milestone createMilestone(Activity superActivity) {
		Milestone e = UmaFactory.eINSTANCE.createMilestone();
		superActivity.getBreakdownElements().add(e);

		return e;
	}

	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#addTaskToActivity(Activity, Task)
	 */
	public void addTaskToActivity(Activity act, Task task) {
		addTasksToActivity(act, Collections.singletonList(task));
	}
	
	/**
	 * @see org.eclipse.epf.dataexchange.importing.PluginService#addTasksToActivity(Activity, List)
	 */
	public void addTasksToActivity(final Activity act, final List tasks) {
		// this method does not work any moredue to later changes
		// use our own code instead
		//ProcessUtil.addTaskToActivity(task, act);
		
		// before doing the command, make sure the object is 
		// in the default configuration of the owing process
		// otherwise you will get msg box asking for that, which may cause error for background process
		org.eclipse.epf.uma.Process proc = TngUtil.getOwningProcess(act);
		MethodConfiguration config = proc.getDefaultContext();
		if ( config != null ) {
			
			for (Iterator it = tasks.iterator(); it.hasNext(); ) {
				Task task = (Task)it.next();			
				MethodPackage pkg = (MethodPackage)task.eContainer();
				List pkgs = config.getMethodPackageSelection();
				if ( !pkgs.contains(pkg) ) {
					pkgs.add(pkg);
				}
			}
		}

		SafeUpdateController.syncExec(new Runnable() {public void run() {
			WBSDropCommand cmd = new WBSDropCommand(act, tasks);
			try {
					cmd.execute();
			} catch (Exception ex) {
				ex.printStackTrace();
			}	
			finally {
				cmd.dispose();
			}	
	    }});
	}

	/**
	 * get process component for the element
	 * @param e BreakdownElement
	 * @return ProcessComponent
	 */
	public static ProcessComponent getProcessComponent(BreakdownElement e) {
		
		if ( e.eContainer() == null ) {
			Activity parent = e.getSuperActivities();
			if ( parent == null ) {
				return null;
			}
			
			return getProcessComponent(parent);
		}
	
		EObject container;
		for (container = e.eContainer(); container != null
				&& !(container instanceof ProcessComponent); container = container
				.eContainer())
			;

		if ( container != null ) {
			return ((ProcessComponent) container);
		}
		
		return null;
	}
	
	private void addActivityToContainer(Activity act) {
		ProcessComponent pc = getProcessComponent(act);
		if ( pc != null ) {
			// create a process package for the activity
			 ProcessPackage pkg = UmaFactory.eINSTANCE.createProcessPackage();
			 pkg.setName(act.getName());
			 pkg.setGuid(EcoreUtil.generateUUID());
			 pc.getChildPackages().add(pkg);
			 
			 pkg.getProcessElements().add(act);
		}
	}
}

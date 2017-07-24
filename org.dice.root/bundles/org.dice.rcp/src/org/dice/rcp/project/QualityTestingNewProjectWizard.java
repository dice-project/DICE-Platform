package org.dice.rcp.project;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.apache.maven.project.MavenProject;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.m2e.core.embedder.ICallable;
import org.eclipse.m2e.core.embedder.IMavenExecutionContext;
import org.eclipse.m2e.core.internal.MavenPluginActivator;
import org.eclipse.m2e.core.internal.embedder.MavenExecutionContext;
import org.eclipse.m2e.core.internal.lifecyclemapping.LifecycleMappingFactory;
import org.eclipse.m2e.core.internal.project.LifecycleMappingConfiguration;
import org.eclipse.m2e.core.internal.project.registry.ProjectRegistryManager;
import org.eclipse.m2e.core.project.IMavenProjectFacade;
import org.eclipse.m2e.core.project.configurator.ILifecycleMapping;
import org.eclipse.m2e.core.project.configurator.ProjectConfigurationRequest;
import org.eclipse.m2e.core.ui.internal.wizards.MavenProjectWizard;

/**
 * The Class creates a new DICE project resource in the workspace that contains
 * the DICE template model
 */
@SuppressWarnings("restriction")
public class QualityTestingNewProjectWizard extends MavenProjectWizard {

	@Override
	public boolean performFinish() {
		if (!super.performFinish()) {
			return false;
		}

		Model model = getModel();
		IProject project = getProject(model);
		IMavenProjectFacade facade = getFacade(project);

		addDependency(model, facade);
		updateProjectConfiguration(facade);

		return true;
	}

	/**
	 * Get the IProject object of the new created project
	 */
	@SuppressWarnings("deprecation")
	private IProject getProject(Model model) {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		final IWorkspaceRoot root = workspace.getRoot();
		final IProject project = getProjectImportConfiguration().getProject(root, model);

		return project;
	}

	/**
	 * Get the Maven Facade object of the new created project
	 */
	private IMavenProjectFacade getFacade(IProject project) {
		ProjectRegistryManager projectManager = MavenPluginActivator.getDefault().getMavenProjectManagerImpl();
		IMavenProjectFacade facade = null;

		while (facade == null) {
			// wait until the project was created
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			facade = projectManager.create(project, new NullProgressMonitor());
		}

		return facade;
	}

	/**
	 * Add the Maven dependency to the new created project
	 */
	private void addDependency(Model model, IMavenProjectFacade facade) {
		Dependency dependency = new Dependency();
		dependency.setGroupId("com.github.dice-project");
		dependency.setArtifactId("qt-lib");
		dependency.setVersion("1.0.0");

		model.addDependency(dependency);

		try {
			FileOutputStream fos = new FileOutputStream(facade.getPomFile());
			new MavenXpp3Writer().write(fos, model);
		} catch (IOException e) {
		}
	}

	/**
	 * Update the new created project
	 */
	private void updateProjectConfiguration(IMavenProjectFacade facade) {
		ProjectRegistryManager projectManager = MavenPluginActivator.getDefault().getMavenProjectManagerImpl();
		try {
			MavenProject mavenProject = facade.getMavenProject(new NullProgressMonitor());
			ProjectConfigurationRequest request = new ProjectConfigurationRequest(facade, mavenProject);
			MavenExecutionContext executionContext = projectManager.createExecutionContext(facade.getPom(),
					facade.getResolverConfiguration());

			executionContext.execute(mavenProject, new ICallable<Void>() {
				public Void call(IMavenExecutionContext context, IProgressMonitor monitor) throws CoreException {
					ILifecycleMapping lifecycleMapping = LifecycleMappingFactory.getLifecycleMapping(facade);

					if (lifecycleMapping != null) {
						lifecycleMapping.configure(request, monitor);

						LifecycleMappingConfiguration.persist(request.getMavenProjectFacade(), monitor);
					}
					return null;
				}
			}, new NullProgressMonitor());
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

}
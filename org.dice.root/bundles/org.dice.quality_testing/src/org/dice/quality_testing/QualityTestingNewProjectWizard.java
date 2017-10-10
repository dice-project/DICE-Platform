package org.dice.quality_testing;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.IOUtil;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * The Class creates a new DICE project resource in the workspace that contains
 * the DICE template model
 */
@SuppressWarnings("restriction")
public class QualityTestingNewProjectWizard extends MavenProjectWizard {

	private final String groupId = "com.github.dice-project";
	private final String artifactId = "qt-lib";

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
		String latestVersion = getLatestVersion();

		Dependency dependency = new Dependency();
		dependency.setGroupId(groupId);
		dependency.setArtifactId(artifactId);
		dependency.setVersion(latestVersion);

		model.addDependency(dependency);

		try {
			FileOutputStream fos = new FileOutputStream(facade.getPomFile());
			new MavenXpp3Writer().write(fos, model);
		} catch (IOException e) {
		}
	}

	private String getLatestVersion() {
		String url = "http://search.maven.org/solrsearch/select";
		String param = "g:\"_groupid_\" AND a:\"_artifactid_\"";
		param = param.replace("_groupid_", groupId).replace("_artifactid_", artifactId);

		try {
			List<NameValuePair> paramList = new ArrayList<>();
			NameValuePair nvp1 = new BasicNameValuePair("q", param);
			paramList.add(nvp1);
			NameValuePair nvp2 = new BasicNameValuePair("wt", "json");
			paramList.add(nvp2);

			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);
			URI uri = new URIBuilder(request.getURI()).addParameters(paramList).build();
			request.setURI(uri);

			HttpResponse response = client.execute(request);
			if (response.getStatusLine().getStatusCode() != 200) {
				return "1.0.0";
			}

			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			String res = IOUtil.toString(is);

			Gson gson = new GsonBuilder().create();
			MavenResponse mavenResponse = gson.fromJson(res, MavenResponse.class);
			if (mavenResponse.response.docs == null || mavenResponse.response.docs.isEmpty()) {
				return "1.0.0";
			}

			return mavenResponse.response.docs.get(0).latestVersion;
		} catch (Exception e) {
			return "1.0.0";
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

	@SuppressWarnings("unused")
	private class MavenResponse {
		private Response response;

		private class Response {
			private Integer numFound;
			private List<Document> docs;
		}

		private class Document {
			private String id;
			private String g;
			private String a;
			private String latestVersion;
			private String repositoryId;
			private String p;
			private String timestamp;
			private String versionCount;
		}
	}

}
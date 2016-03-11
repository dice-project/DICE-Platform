//------------------------------------------------------------------------------
// Copyright (c) 2005, 2007 IBM Corporation and others.
// All rights reserved. This program and the accompanying materials
// are made available under the terms of the Eclipse Public License v1.0
// which accompanies this distribution, and is available at
// http://www.eclipse.org/legal/epl-v10.html
//
// Contributors:
// IBM Corporation - initial implementation
//------------------------------------------------------------------------------
package org.eclipse.epf.search.ui.internal;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.epf.common.xml.AbstractSAXParser;
import org.eclipse.epf.library.LibraryService;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.library.util.LibraryUtil;
import org.eclipse.epf.search.ui.SearchUIPlugin;
import org.eclipse.epf.search.ui.SearchUIResources;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.ContentDescription;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Practice;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessElement;
import org.eclipse.epf.uma.ProcessPackage;
import org.eclipse.epf.uma.util.UmaUtil;
import org.xml.sax.Attributes;

/**
 * Performs a method search by iterating the current method library and parsing
 * the content element XMI files where necessary.
 * 
 * @author Kelvin Low
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class MethodSearchOperation implements IMethodSearchOperation {

	private static final String SCAN_LIBRARY_TEXT = SearchUIResources.scanLibraryTask_name; 

	private static final String SCAN_ELEMENT_TEXT = SearchUIResources.scanElementTask_name; 

	private boolean debug;

	protected MethodSearchInput searchInput;

	private ISearchResultCollector result;

	private String searchString;

	private boolean caseSensitive;

	private boolean regExp;

	private Pattern searchStringPattern;

	private Pattern elementNamePattern;

	private MethodElementScanner elementScanner;

	private List<String> parsedFiles = new ArrayList<String>();

	private IProgressMonitor progressMonitor;

	// Scans the content element XMI file associated with a Method element to
	// look for the search string.
	private class MethodElementScanner extends AbstractSAXParser {

		private MethodElement element;

		/**
		 * Creates a new instance.
		 */
		public MethodElementScanner() {
			super(null, false);
		}

		/**
		 * Parses the content XMI file associated with the given Method element.
		 * 
		 * @param element
		 *            a method element
		 */
		public void parse(MethodElement element) {
			this.element = element;
			String elementContentPath = getElementPath(element);
			String briefDescription = element.getBriefDescription();
			if (searchStringPattern.matcher(briefDescription).find()) {
				result.accept(element);
			}
			if (elementContentPath != null
					&& !parsedFiles.contains(elementContentPath)) {
				super.parse(elementContentPath);
				parsedFiles.add(elementContentPath);
			}
		}

		/**
		 * @see AbstractSAXParser#startElement(String, String, String,
		 *      Attributes)
		 */
		public void startElement(String namespaceURL, String localName,
				String qname, Attributes attributes) {
			for (int i = 0; i < attributes.getLength(); i++) {
				String name = attributes.getQName(i);
				if (!name.equals("briefDescription")) { //$NON-NLS-1$
					String value = attributes.getValue(i);
					if (searchStringPattern.matcher(value).find()) {
						result.accept(element);
					}
				}
			}
		}

		/**
		 * @see AbstractSAXParser#endElement(String, String, String)
		 */
		public void endElement(String namespaceURL, String localName,
				String qname) {
			String value = strBuf.toString();
			if (searchStringPattern.matcher(value).find()) {
				result.accept(element);
			}
			strBuf.setLength(0);
		}

	}

	/**
	 * Creates a new instance.
	 * 
	 * @param searchString
	 *            a search string
	 * @param namePattern
	 *            a name pattern
	 * @param result
	 *            a search result collector
	 */
	public MethodSearchOperation(MethodSearchInput searchInput,
			ISearchResultCollector result) {
		initialize(searchInput, result);
	}

	public MethodSearchOperation() {
	}

	private void initialize(MethodSearchInput searchInput,
			ISearchResultCollector result) {
		this.searchInput = searchInput;
		this.result = result;

		caseSensitive = searchInput.getCaseSensitive();
		regExp = searchInput.getRegularExpression();
		elementNamePattern = MethodSearchPattern.createPattern(searchInput
				.getNamePattern(), false, false);

		debug = SearchUIPlugin.getDefault().isDebugging();

		searchString = searchInput.getSearchString();
		if (searchString == null)
			searchString = ""; //$NON-NLS-1$
		if (searchString.length() > 0 && !searchString.equals("*")) { //$NON-NLS-1$
			elementScanner = new MethodElementScanner();
			searchStringPattern = MethodSearchPattern.createPattern(
					searchString, caseSensitive, regExp);
		}

		if (debug) {
			System.out
					.println("MethodSearchOperation constructor: searchString=" //$NON-NLS-1$
							+ searchString
							+ ", elementNamePattern=" //$NON-NLS-1$
							+ searchInput.getNamePattern()
							+ ", caseSensitive=" + caseSensitive); //$NON-NLS-1$
		}
	}
	
	protected Collection<MethodPlugin> getSearchableMethodPlugins() {
		return LibraryUtil.getMethodPlugins(LibraryService.getInstance().getCurrentMethodLibrary());
	}
	
	/**
	 * Executes the search operation.
	 * 
	 * @param progressMonitor
	 *            a progress monitor
	 */
	public void execute(IProgressMonitor progressMonitor) {
		this.progressMonitor = progressMonitor;
		this.progressMonitor.beginTask(SCAN_LIBRARY_TEXT, 7500);

		// Iterate the Method Plugin to look for elements that match the name
		// pattern.
		Collection<MethodPlugin> methodPlugins = getSearchableMethodPlugins();
		for (Iterator<MethodPlugin> i = methodPlugins.iterator(); i.hasNext()
				&& !progressMonitor.isCanceled();) {
			MethodPlugin methodPlugin = (MethodPlugin) i.next();
			matchPattern(methodPlugin);

			MethodPackage methodPackage;
			if (searchInput.getSearchScope().includeCoreContent()) {
				methodPackage = UmaUtil.findMethodPackage(methodPlugin,
						ModelStructure.DEFAULT.coreContentPath);
				searchMethodPackages(methodPackage);
			}

			if (searchInput.getSearchScope().include(
					MethodSearchScope.STANDARD_CATEGORY)) {
				methodPackage = UmaUtil.findMethodPackage(methodPlugin,
						ModelStructure.DEFAULT.disciplineDefinitionPath);
				searchMethodPackages(methodPackage);
				methodPackage = UmaUtil.findMethodPackage(methodPlugin,
						ModelStructure.DEFAULT.domainPath);
				searchMethodPackages(methodPackage);
				methodPackage = UmaUtil.findMethodPackage(methodPlugin,
						ModelStructure.DEFAULT.workProductTypePath);
				searchMethodPackages(methodPackage);
				methodPackage = UmaUtil.findMethodPackage(methodPlugin,
						ModelStructure.DEFAULT.roleSetPath);
				searchMethodPackages(methodPackage);
				methodPackage = UmaUtil.findMethodPackage(methodPlugin,
						ModelStructure.DEFAULT.toolPath);
				searchMethodPackages(methodPackage);
			}

			if (searchInput.getSearchScope().include(
					MethodSearchScope.CUSTOM_CATEGORY)) {
				methodPackage = UmaUtil.findMethodPackage(methodPlugin,
						ModelStructure.DEFAULT.customCategoryPath);
				searchMethodPackages(methodPackage);
			}

			if (searchInput.getSearchScope().include(
					MethodSearchScope.CAPABILITY_PATTERN)) {
				methodPackage = UmaUtil.findMethodPackage(methodPlugin,
						ModelStructure.DEFAULT.capabilityPatternPath);
				searchMethodPackages(methodPackage);
			}

			if (searchInput.getSearchScope().include(
					MethodSearchScope.DELIVERY_PROCESS)) {
				methodPackage = UmaUtil.findMethodPackage(methodPlugin,
						ModelStructure.DEFAULT.deliveryProcessPath);
				searchMethodPackages(methodPackage);
			}
		}
	}

	/**
	 * Searches a Method Package.
	 * 
	 * @param methodPackage
	 *            a method package
	 */
	protected void searchMethodPackages(MethodPackage methodPackage) {
		if (methodPackage == null
				|| methodPackage.getName().equals(
						ModelStructure.HIDDEN_PACKAGE_NAME)) {
			return;
		}
		if (methodPackage instanceof ProcessComponent) {
			// Add the associated CapabilityPattern or DeliveryProcess to the
			// search result.
			ProcessComponent processComponent = (ProcessComponent) methodPackage;
			matchPattern(processComponent.getProcess());
		} else if (!isInternalProcessPackage(methodPackage)) {
			matchPattern(methodPackage);
		}

		List childPackages = methodPackage.getChildPackages();
		for (Iterator i = childPackages.iterator(); i.hasNext()
				&& !progressMonitor.isCanceled();) {
			searchMethodPackages((MethodPackage) i.next());
		}
		if (methodPackage instanceof ContentPackage) {
			ContentPackage contentPackage = (ContentPackage) methodPackage;
			List contentElements = contentPackage.getContentElements();
			for (Iterator j = contentElements.iterator(); j.hasNext()
					&& !progressMonitor.isCanceled();) {
				ContentElement contentElement = (ContentElement) j.next();
				matchPattern(contentElement);
				if (contentElement instanceof Artifact) {
					searchContainedArtifacts((Artifact) contentElement);
				} else if (contentElement instanceof Discipline) {
					searchSubDisciplines((Discipline) contentElement);
				} else if (contentElement instanceof Practice) {
					searchSubPractices((Practice) contentElement);
				} else if (contentElement instanceof Domain) {
					searchSubDomains((Domain) contentElement);
				}
			}
		} else if (methodPackage instanceof ProcessPackage) {
			ProcessPackage processPackage = (ProcessPackage) methodPackage;
			List processElements = processPackage.getProcessElements();
			for (Iterator j = processElements.iterator(); j.hasNext()
					&& !progressMonitor.isCanceled();) {
				ProcessElement processElement = (ProcessElement) j.next();
//				if (!(processElement instanceof Activity)) {
					matchPattern(processElement);
//				}
			}
		}
	}

	/**
	 * Returns <code>true</code> if the given package is an internal Process
	 * Package.
	 * 
	 * @param methodPackage
	 *            a method or process package
	 * @return <code>true</code> if the given package is an internal process
	 *         package
	 */
	protected boolean isInternalProcessPackage(MethodPackage methodPackage) {
		if (methodPackage instanceof ProcessPackage) {
			if (UmaUtil.getProcessComponent(methodPackage) != null) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Searches the contained artifacts of an artifact.
	 * 
	 * @param artifact
	 *            an artifact
	 */
	protected void searchContainedArtifacts(Artifact artifact) {
		if (artifact != null) {
			for (Iterator i = artifact.getContainedArtifacts().iterator(); i
					.hasNext()
					&& !progressMonitor.isCanceled();) {
				Artifact containedArtifact = (Artifact) i.next();
				matchPattern(containedArtifact);
				searchContainedArtifacts(containedArtifact);
			}
		}
	}

	/**
	 * Searches the sub disciplines of a discipline.
	 * 
	 * @param discipline
	 *            a discipline
	 */
	protected void searchSubDisciplines(Discipline discipline) {
		if (discipline != null) {
			for (Iterator i = discipline.getSubdiscipline().iterator(); i
					.hasNext()
					&& !progressMonitor.isCanceled();) {
				Discipline subDiscipline = (Discipline) i.next();
				matchPattern(subDiscipline);
				searchSubDisciplines(subDiscipline);
			}
		}
	}

	/**
	 * Searches the sub practices of a practice.
	 * 
	 * @param practice
	 *            a practice
	 */
	protected void searchSubPractices(Practice practice) {
		if (practice != null) {
			for (Iterator i = practice.getSubPractices().iterator(); i
					.hasNext()
					&& !progressMonitor.isCanceled();) {
				Practice subPractice = (Practice) i.next();
				matchPattern(subPractice);
				searchSubPractices(subPractice);
			}
		}
	}

	/**
	 * Searches the sub domains of a domain.
	 * 
	 * @param domain
	 *            a domain
	 */
	protected void searchSubDomains(Domain domain) {
		if (domain != null) {
			for (Iterator i = domain.getSubdomains().iterator(); i.hasNext()
					&& !progressMonitor.isCanceled();) {
				Domain subDomain = (Domain) i.next();
				matchPattern(subDomain);
				searchSubDomains(subDomain);
			}
		}
	}

	/**
	 * Adds the given method element to the search result collection if its name
	 * or presentation name matches the name pattern.
	 * 
	 * @param element
	 *            a method element
	 */
	protected void matchPattern(MethodElement element) {
		try {
			if (searchInput.getSearchScope().include(element)) {
				String name = element.getName();
				String taskName = MessageFormat.format(SCAN_ELEMENT_TEXT,
						new Object[] { name });
				progressMonitor.setTaskName(taskName);
				boolean foundMatch = false;
				if (element instanceof DescribableElement) {
					String presentationName = ((DescribableElement) element)
							.getPresentationName();
					foundMatch = (name != null && elementNamePattern.matcher(
							name).matches())
							|| (presentationName != null && elementNamePattern
									.matcher(presentationName).matches());
				} else {
					foundMatch = (name != null && elementNamePattern.matcher(
							name).matches());
				}
				if (foundMatch) {
					if (searchString.length() == 0 || searchString.equals("*")) { //$NON-NLS-1$
						result.accept(element);
					} else {
						try {
							elementScanner.parse(element);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				progressMonitor.worked(1);
			}
		} catch (Exception e) {
			SearchUIPlugin.getDefault().getLogger().logError(e);
		}
	}

	/**
	 * Returns the path of the content XML file associated with a method
	 * element.
	 */
	protected String getElementPath(MethodElement element) {
		Resource resource = null;
		if (element instanceof DescribableElement) {
			DescribableElement describableElement = (DescribableElement) element;
			ContentDescription contentDescription = describableElement
					.getPresentation();
			if (contentDescription == null) {
				return null;
			}
			resource = contentDescription.eResource();
		} else {
			resource = element.eResource();
		}
		if (resource != null) {
			URI resourceURI = resource.getURI();
			if (resourceURI != null) {
				return resourceURI.toFileString();
			}
		}
		return null;
	}

	public void execute(MethodSearchInput searchInput,
			ISearchResultCollector result, IProgressMonitor monitor) {
		initialize(searchInput, result);
		execute(monitor);
	}

}
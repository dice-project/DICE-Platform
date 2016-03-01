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
package org.eclipse.epf.library.edit.itemsfilter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.epf.common.utils.StrUtil;
import org.eclipse.epf.library.edit.PresentationContext;
import org.eclipse.epf.library.edit.util.MethodElementUtil;
import org.eclipse.epf.library.edit.util.ModelStructure;
import org.eclipse.epf.uma.Activity;
import org.eclipse.epf.uma.Artifact;
import org.eclipse.epf.uma.ContentElement;
import org.eclipse.epf.uma.ContentPackage;
import org.eclipse.epf.uma.CustomCategory;
import org.eclipse.epf.uma.DescribableElement;
import org.eclipse.epf.uma.Discipline;
import org.eclipse.epf.uma.DisciplineGrouping;
import org.eclipse.epf.uma.Domain;
import org.eclipse.epf.uma.MethodElement;
import org.eclipse.epf.uma.MethodPackage;
import org.eclipse.epf.uma.MethodPlugin;
import org.eclipse.epf.uma.Process;
import org.eclipse.epf.uma.ProcessComponent;
import org.eclipse.epf.uma.ProcessPackage;

/**
 * A helper class for managing element filtering in the method element selection
 * dialogs.
 * 
 * @author Shashidhar Kannoori
 * @author Phong Nguyen Le
 * @since 1.0
 */
public class FilterHelper {

	private String pattern;

	private String type;

	private Pattern regex;

	private String tabStr;

	private Object contentElement;

	private List existingElements = new ArrayList();

	private boolean showPresentationName = false;
	
	// This is separate handling for ContentContegory 
	private String contentCategoryTabStr;

	public FilterHelper(Object contentElement, String tabStr, String pattern,

	String filterTypeStr, List alreadySelectedList) {

		this.contentElement = contentElement;

		this.tabStr = tabStr;

		this.type = filterTypeStr;

		this.existingElements = alreadySelectedList;

		this.pattern = pattern;

		showPresentationName = PresentationContext.INSTANCE
		.isShowPresentationNames();


	}
	/*
	 * method to set contentCategoryTabStr, this 
	 * special handle for content category, tabStr implementation 
	 * donot work for this. 
	 */
	public void setContentCategoryTabStr(String contentCategoryTabStr){
		this.contentCategoryTabStr = contentCategoryTabStr;
	}
	/*
	 * method to set contentCategoryTabStr, this 
	 * special handle for content category, tabStr implementation 
	 * donot work for this. 
	 */
	public String getContentCategoryTabStr(){
		return this.contentCategoryTabStr;
	}

	public List getAlreadySelectedList() {

		return existingElements;

	}

	public void setAlreadySelectedList(List alreadySelectedList) {

		this.existingElements = alreadySelectedList;

	}

	public boolean matchPattern(Object obj) {
		return match(obj);
	}

	/*
	 * Convenient method to check any CategorizedElements (tree traversal) of
	 * CustomCategory has any match with matcher. @param CustomCategory
	 */
	private boolean hasMatchedChildCustomCategory(CustomCategory topCategory) {
		List list = topCategory.getCategorizedElements();

		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object object = iter.next();
			if (object instanceof CustomCategory) {
				CustomCategory category = (CustomCategory) object;
				if (matcher(category)) {
					return true;
				} else if (hasMatchedChildCustomCategory(category)) {
					return true;
				}
			}
		}
		return false;
	}

	/*
	 * Convenient method to check any subArtifacts (tree traversal) of Artifact
	 * has any match with matcher. @param CustomCategory
	 */
	private boolean hasMatchedArtifact(Artifact artifact) {

		List list = artifact.getContainedArtifacts();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object object = iter.next();
			if (object instanceof Artifact) {
				Artifact subartifact = (Artifact) object;
				if (matcher(subartifact)) {
					return true;
				} else if (hasMatchedArtifact(subartifact)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/*
	 * Convenient method to check any subArtifacts (tree traversal) of Artifact
	 * has any match with  alreadyexisting elements.
	 */
	private boolean hasMatchedArtifact(Artifact artifact, List existingList) {

		List list = artifact.getContainedArtifacts();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object object = iter.next();
			if (object instanceof Artifact) {
				Artifact subartifact = (Artifact) object;
				if(!existingList.contains(subartifact)){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Convenient method to check any ChildPackages (tree traversal) of Content
	 * Package has any match with matcher.
	 * 
	 * @param CustomCategory
	 */
	private boolean hasMatchedChild(ContentPackage pkg) {

		for (Iterator iter = pkg.getChildPackages().iterator(); iter.hasNext();) {
			Object childPkg = iter.next();
			if (childPkg instanceof ContentPackage) {
				ContentPackage contentPkg = (ContentPackage) childPkg;
				if (matcher(contentPkg)) {
					return true;
				} else if (hasMatchedChild(contentPkg)) {
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * Convenient method to check any subDomain (tree traversal) of Domain has
	 * any match with matcher <link>matcher(Object obj)</link>
	 * 
	 * @param CustomCategory
	 */
	private boolean hasMatchedSubDomains(Domain domain) {
		List list = domain.getSubdomains();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object subdomain = iterator.next();
			if (matcher(subdomain)) {
				return true;
			} else if (hasMatchedSubDomains((Domain) subdomain)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 */
	private boolean hasMatchedSubDiscipline(Discipline discipline){
		List list = discipline.getSubdiscipline();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object subdiscipline = iterator.next();
			if (matcher(subdiscipline)) {
				return true;
			} else if (hasMatchedSubDiscipline((Discipline) subdiscipline)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 */
	private boolean hasMatchedDisciplines(DisciplineGrouping grouping){
		List list = grouping.getDisciplines();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object subdiscipline = iterator.next();
			if (matcher(subdiscipline)) {
				return true;
			} else if (hasMatchedSubDiscipline((Discipline) subdiscipline)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Convenient method to check any activity (tree traversal) of activity has
	 * any match with matcher <link>matcher(Object obj)</link>
	 * 
	 * @param CustomCategory
	 */
	private boolean hasMatchedActivities(Activity activity) {
		List list = activity.getBreakdownElements();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object element = iterator.next();
			if (element instanceof Activity) {
				if (matcher(element)) {
					return true;
				} else if (hasMatchedActivities((Activity) element)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Convenient method to check any activity (tree traversal) of ProcessPackage has
	 * any match with matcher <link>matcher(Object obj)</link>
	 * 
	 * @param CustomCategory
	 */
	private boolean hasMatchedActivities(ProcessPackage procPackage) {
		if(procPackage instanceof ProcessComponent) {
			Process proc = ((ProcessComponent) procPackage).getProcess();
			if(proc != null && (matcher(proc) || hasMatchedActivities(proc))) {
				return true;
			}
		}
		
		// check child packages
		List<?> list = procPackage.getChildPackages();
		for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
			Object element = iterator.next();
			if (element instanceof ProcessPackage) {
				if (hasMatchedActivities((ProcessPackage) element)) {
					return true;
				}
			}
		}
		return false;
	}


	public String getFilterTypeStr() {

		// Trim "-"(hyphen) from the strings (types).  Only in case methodplugin
		// don't trim "-" because methodplug-in contain "-" (hyphen)and useful.
		while (type != null && type.indexOf(FilterConstants.space) > -1
				&& !type.equals(FilterConstants.METHO_PLUGINS)) {
			type = type.substring(type.indexOf(FilterConstants.space) + 1, type
					.length());
		}
		return type;
	}

	public String getPattern() {

		return pattern;

	}

	public Pattern getRegexPattern() {

		return regex;

	}

	public String getTabStr() {

		return tabStr;

	}

	/*
	 * 
	 * Method checks whether arbitrary object passed is
	 * 
	 * equal to current content element's variability or not.
	 * 
	 * If variability of ContentElement matches arbitrary object,
	 * 
	 * returns true.
	 * 
	 * @retun boolean.
	 * 
	 */

	public boolean checkVariability(Object obj) {
		if (contentElement instanceof ContentElement) {

			Object variabilityObject = ((ContentElement) contentElement)
					.getVariabilityBasedOnElement();
			if (variabilityObject != null) {
				if (obj.equals(variabilityObject))
					return true;
			}
		}
		return false;
	}

	public Object getContentElement() {

		return contentElement;

	}

	public void setContentElement(Object contentElement) {

		this.contentElement = contentElement;

	}

	public void setFilterTypeStr(String filterTypeStr) {

		this.type = filterTypeStr;

	}

	public void setPattern(String pattern) {

		this.pattern = pattern;

	}

	public void setRegexPattern(Pattern regexPattern) {

		this.regex = regexPattern;

	}

	public void setTabStr(String tabStr) {

		this.tabStr = tabStr;

	}

	public String[] getPathBasedOnTabString(String tabStr) {

		if (FilterConstants.contentElementStrs.contains(tabStr)) {
			return ModelStructure.DEFAULT.coreContentPath;
		} else if (tabStr.equals(FilterConstants.ROLESETS)
				|| tabStr.equals(FilterConstants.ROLE_SET_GROUPINGS)) {
			return ModelStructure.DEFAULT.roleSetPath;
		} else if (tabStr.equals(FilterConstants.DISCIPLINES)
				|| tabStr.equals(FilterConstants.DISCIPLINE_GROUPINGS)) {
			return ModelStructure.DEFAULT.disciplineDefinitionPath;
		} else if (tabStr.equals(FilterConstants.TOOLS)) {
			return ModelStructure.DEFAULT.toolPath;
		} else if (tabStr.equals(FilterConstants.WORKPRODUCTTYPES)) {
			return ModelStructure.DEFAULT.workProductTypePath;
		} else if (tabStr.equals(FilterConstants.DOMAINS)) {
			return ModelStructure.DEFAULT.domainPath;
		} else if (tabStr.equals(FilterConstants.CUSTOM_CATEGORIES)) {
			return ModelStructure.DEFAULT.customCategoryPath;
		} else
			return ModelStructure.DEFAULT.coreContentPath;

	}

	/**
	 * 
	 * This is convenient method to check all the condition, like matchPattern,
	 * if object exists in already selected list, check variability match, and
	 * is object itself donot display.
	 * 
	 * @param object
	 * @return boolean
	 */

	public boolean checkObjectAccepted(Object obj) {

		if (!match(obj))
			return false;
		// Check if already existing (associated elements) don't display.
		if (getAlreadySelectedList() != null
				&& getAlreadySelectedList().contains(obj)){
			// special case handling for Artifact and sub-artifact.
			if(obj instanceof Artifact){
				if(!hasMatchedArtifact((Artifact)obj,getAlreadySelectedList()))
					return false;
			}else{
				return false;
			}
		}

		if (checkVariability(obj))
			return false;

		// if object itself don't display
		// Do equal check only on elements otherthan method plugin, In case of
		// Copyright(supporting material) associating to methodplugin, if
		// methodplugin (contentElemtn) equals
		// obj, then results false.Plugin will lost from tree in the filter. In
		// turn supporting material for that particular plugin will not be
		// displayed.
		if (!(obj instanceof MethodPlugin)) {
			if (contentElement != null && obj.equals(contentElement))
				return false;
		}
		return true;

	}

	/**
	 * 
	 * Convenient method for checking MethodPlugin accepted or not. Method needs
	 * to invoked only for MethodPlugin object.
	 * 
	 * @param object
	 * @return boolean
	 */

	public boolean acceptMethodPlugin(Object obj) {
		if (contentElement != null) {
			if (MethodElementUtil.getAllModels(contentElement).contains(obj))
				return true;
			else
				return false;
		} else {
			return true;
		}
	}

	/**
	 * Convenient method for checking Content Package accepted or not. Method
	 * needs to invoked only for Content Package object.
	 * 
	 * @param object
	 * @return boolean
	 */
	public boolean acceptContentPackage(Object obj) {

		if (((ContentPackage) obj).getChildPackages().isEmpty()
				&& ((ContentPackage) obj).getContentElements().isEmpty())
			return false;
		else
			return true;
	}

	public boolean isObjectInSelectedItems(Object obj) {

		if (getAlreadySelectedList() != null
				&& getAlreadySelectedList().contains(obj))
			return false;
		else
			return true;
	}

	public boolean isContributor(ContentElement element) {
		// Just returning true, because contribution checked in other place.
		return false;
	}

	public boolean matchPatternBasedOnType(Object obj) {
		return match(obj);
	}

	public boolean matchPatternOnPresentationName(Object obj) {
		return match(obj);
	}

	public boolean isShowPresentationName() {
		return showPresentationName;
	}

	public void setShowPresentationName(boolean showPresentationName) {
		this.showPresentationName = showPresentationName;
	}

	/**
	 * Generic method to check match of an object based on regular expression
	 * also based on type that selected in the dialog.
	 */

	private boolean match(Object obj) {

		// Do not do Pattern match if type or pattern string is null.
		if (type == null) {
			return true;
		}
		if (pattern == null || pattern.equalsIgnoreCase(StrUtil.EMPTY_STRING)) {
			if(type.equals(FilterConstants.ACTIVITIES) && obj instanceof ProcessPackage) {
				ProcessPackage pkg = (ProcessPackage) obj;
				
				// filter out package without any process
				//
				if(!pkg.getChildPackages().isEmpty()) {
					AbstractTreeIterator<MethodPackage> iterator = new AbstractTreeIterator<MethodPackage>(pkg) {

						private static final long serialVersionUID = 1L;

						@Override
						protected Iterator<? extends MethodPackage> getChildren(
								Object object) {
							return ((MethodPackage) object).getChildPackages().iterator();
						}

					};
					while(iterator.hasNext()) {
						MethodPackage childPkg = iterator.next();
						if(childPkg instanceof ProcessComponent && ((ProcessComponent) childPkg).getProcess() != null) {
							return true;
						}
					}
				}

				return false;
			}
			return true;
		}

		// Check if type is of Content Package
		if (type.equals(FilterConstants.CONTENT_PACKAGES)) {

			if (obj instanceof ContentPackage) {

				if (matcher(obj) || hasMatchedChild((ContentPackage) obj)) {
					return true;
				} else {
					return false;
				}

			} else {

				// else for all content elements, if their content pakcage
				// matches , return true.
				if (obj instanceof ContentElement) {
					Object container = ((ContentElement) obj).getContainer();
					if (container instanceof ContentPackage) {
						if (!matcher(container))
							return false;
					}
				}
				return true;
			}

		} else if (type.equals(FilterConstants.METHO_PLUGINS)) {
			// Check if type is of Method Plugins
			if (obj instanceof MethodPlugin) {
				// if is a MethodPlugin, return true if matches the pattern
				return matcher(obj);

			} else {
				return true;
			}

		} else if (type.equals(FilterConstants.ACTIVITIES)) {
			if (obj instanceof Activity) {
				if (matcher(obj)) {
					return true;
				} else {
					return hasMatchedActivities((Activity)obj);
				}
			} else if (obj instanceof ProcessPackage) {
				return hasMatchedActivities((ProcessPackage)obj);
			} else {
				return true;
			}
		} else if (type != null) {

			if (obj instanceof ContentElement
					|| obj instanceof ProcessComponent
					|| obj instanceof Process) {

				if (type.indexOf(FilterConstants.space) > -1) {
					type = type.substring(type.indexOf(FilterConstants.space), type.length());
				}

				Class cls = FilterInitializer.getInstance().getClassForType(
						type);
				if (cls != null && !cls.isInstance(obj)) {
					return true;
				}

				if (matcher(obj)) {
					return true;
				} else {

					if (obj instanceof CustomCategory)
						return hasMatchedChildCustomCategory((CustomCategory) obj);

					if (obj instanceof Artifact) {
						return hasMatchedArtifact((Artifact) obj);
					}
					if (obj instanceof Domain) {
						return hasMatchedSubDomains((Domain) obj);
					}
					if (obj instanceof Discipline){
						return hasMatchedSubDiscipline((Discipline)obj);
					}
					if (obj instanceof DisciplineGrouping){
						return hasMatchedDisciplines((DisciplineGrouping)obj);
					}
					return false;
				}
			} else { // else for all other types of elements
				return true;
			}

		} else
			// the All case
			return true;
	}

	/**
	 * Convenient method which does actual matching of given object with regular
	 * expression. Checks to do presentationname or name.
	 */
	public boolean matcher(Object obj) {
		if(regex != null){
			Matcher m = regex.matcher(((MethodElement) obj).getName());
			if (showPresentationName) {
				if (obj instanceof DescribableElement
						&& ((DescribableElement) obj).getPresentationName() != null)
					m = regex.matcher(((DescribableElement) obj)
							.getPresentationName());
			}
			return m.matches();
		}
		return true;
	}
}

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
package org.eclipse.epf.publishing.services;

import java.util.List;

import org.eclipse.epf.uma.Process;

/**
 * The Publishing Options.
 * 
 * @author Jinhua Xi
 * @author Kelvin Low
 * @since 1.0
 */
public class PublishOptions {

	// The publish directory.
	protected String publishDir;

	// The list of processes to be published.
	protected List<Process> processes;

	// Publish entire method configuration option.
	protected boolean publishConfiguration;

	// Publish one or more processes option.
	protected boolean publishProcess;

	// Publish unopen activity detail diagrams option.
	protected boolean publishUnopenADD;

	// Publish activity diagram for base activties with unmodified
	// extensions option.
	protected boolean publishBaseAD;

	// Publish light weight navigation tree option.
	protected boolean publishLightWeightTree;

	// Show method content in the descriptor pages option.
	protected boolean showMethodContentInDescriptors;
	
	protected boolean showLinkedPageForDescriptor;

	// Show related descriptors in the task, role, and work product pages
	// option.
	protected boolean showRelatedDescriptors;
	
	//Show all indirect (green) occurrences in extended patterns
	protected boolean showRelatedDescriptorsOption;

	// Show task descriptors in the navigation tree option.
	protected boolean showDescriptorsInNavigationTree;

	// Publish empty categories option (currently not supported in the UI).
	protected boolean publishEmptyCategories;
	
	//Show related links in the task, role, and work product pages
	protected boolean showRelatedLinks;

	/**
	 * Creates a new instance.
	 */
	public PublishOptions() {
	}

	/**
	 * Gets the publish directory.
	 * 
	 * @return a directory for storing the published content
	 */
	public String getPublishDir() {
		return publishDir;
	}

	/**
	 * Sets the publish directory.
	 * 
	 * @param publishDir
	 *            a directory for storing the published content
	 */
	public void setPublishDir(String publishDir) {
		this.publishDir = publishDir;
	}

	/**
	 * Gets the list of processes that will be published.
	 * 
	 * @return a collection of <code>Process</code>
	 */
	public List<Process> getProcesses() {
		return processes;
	}

	/**
	 * Sets the list of processes that will be published.
	 * 
	 * @param processes
	 *            a collection of <code>Process</code>
	 */
	public void setProcesses(List<Process> processes) {
		this.processes = processes;
	}

	/**
	 * Gets the publish method configuration option.
	 * 
	 * @return <code>true</code> if the entire method configuration will be
	 *         published
	 */
	public boolean isPublishConfiguration() {
		return publishConfiguration;
	}

	/**
	 * Sets the publish method configuration option.
	 * 
	 * @param publishConfiguration
	 *            if <code>true</code>, publish the entire method
	 *            configuration
	 */
	public void setPublishConfiguration(boolean publishConfiguration) {
		this.publishConfiguration = publishConfiguration;
	}

	/**
	 * Gets the publish process option.
	 * 
	 * @return <code>true</code> if the one or more processes in a method
	 *         configuration will be published
	 */
	public boolean isPublishProcess() {
		return publishProcess;
	}

	/**
	 * Sets the publish process option.
	 * 
	 * @param publishProcess
	 *            if <code>true</code>, publish one or more processes in a
	 *            method configuration
	 */
	public void setPublishProcess(boolean publishProcess) {
		this.publishProcess = publishProcess;
	}

	/**
	 * Gets the publish unopen activity detail diagrams option.
	 * 
	 * @return <code>true</code> if the unopen activity detail diagrams will
	 *         be published
	 */
	public boolean isPublishUnopenADD() {
		return publishUnopenADD;
	}

	/**
	 * Sets the publish unopen activity detail diagrams option.
	 * 
	 * @param publishUnopenADD
	 *            if <code>true</code>, publish unopen activity detail
	 *            diagrams
	 */
	public void setPublishUnopenADD(boolean publishUnopenADD) {
		this.publishUnopenADD = publishUnopenADD;
	}

	/**
	 * Gets the publish activity diagram for base activties with unmodified
	 * extensions option.
	 * 
	 * @return <code>true</code> if the activity diagram for base activties
	 *         with unmodified extensions will be published
	 */
	public boolean isPublishBaseAD() {
		return publishBaseAD;
	}

	/**
	 * Sets the publish activity diagram for base activties with unmodified
	 * extensions option.
	 * 
	 * @param publishBaseAD
	 *            if <code>true</code>, publish activity diagram for base
	 *            activties with unmodified extensions
	 */
	public void setPublishBaseAD(boolean publishBaseAD) {
		this.publishBaseAD = publishBaseAD;
	}

	/**
	 * Gets the publish light weight navigation tree option.
	 * 
	 * @return <code>true</code> if a light weight navigation tree will be
	 *         published
	 */
	public boolean isPublishLightWeightTree() {
		return publishLightWeightTree;
	}

	/**
	 * Sets the publish light weight navigation tree option.
	 * 
	 * @param publishLightWeightTree
	 *            if <code>true</code>, publish a light weight navigation
	 *            tree
	 */
	public void setPublishLightWeightTree(boolean publishLightWeightTree) {
		this.publishLightWeightTree = publishLightWeightTree;
	}

	/**
	 * Gets the show method content in descriptor pages option.
	 * 
	 * @return <code>true</code> if the method content will be displayed in
	 *         the descriptor pages
	 */
	public boolean isShowMethodContentInDescriptors() {
		return showMethodContentInDescriptors;
	}

	/**
	 * Sets the show method content in descriptor pages option.
	 * 
	 * @param showMethodContentInDescriptors
	 *            if <code>true</code>, display the method content in the
	 *            descriptor pages
	 */
	public void setShowMethodContentInDescriptors(
			boolean showMethodContentInDescriptors) {
		this.showMethodContentInDescriptors = showMethodContentInDescriptors;
	}
	
	public boolean isShowLinkedPageForDescriptor() {
		return showLinkedPageForDescriptor;
	}
	
	public void setShowLinkedPageForDescriptor(boolean showLinkedPageForDescriptor) {
		this.showLinkedPageForDescriptor = showLinkedPageForDescriptor;
	}

	/**
	 * Gets the show related descriptors in the task, role, and work product
	 * pages option.
	 * 
	 * @return <code>true</code> if related descriptors will be displayed in
	 *         the task, role, and work product pages
	 */
	public boolean isShowRelatedDescriptors() {
		return showRelatedDescriptors;
	}

	/**
	 * Sets the show related descriptors in the task, role, and work product
	 * pages option.
	 * 
	 * @param showRelatedDescriptors
	 *            if <code>true</code>, display the related descriptors in
	 *            the task, role, and work product pages
	 */
	public void setShowRelatedDescriptors(boolean showRelatedDescriptors) {
		this.showRelatedDescriptors = showRelatedDescriptors;
	}
	
	/**
	 * Gets the show related links in the task, role, and work product
	 * pages option.
	 * 
	 * @return <code>true</code> if related links will be displayed in
	 *         the task, role, and work product pages
	 */
	public boolean isShowRelatedLinks() {
		return showRelatedLinks;
	}

	/**
	 * Sets the show related links in the task, role, and work product
	 * pages option.
	 * 
	 * @param showRelatedDescriptors
	 *            if <code>true</code>, display the related links in
	 *            the task, role, and work product pages
	 */
	public void setShowRelatedLinks(boolean showRelatedLinks) {
		this.showRelatedLinks = showRelatedLinks;
	}

	
	/**
	 * Gets show all indirect (green) occurrences in extended patterns option
	 * 
	 * @return <code>true</code> if all indirect descriptors will be displayed in
	 *         the task, role, and work product pages
	 */
	public boolean isShowRelatedDescriptorsOption() {
		return showRelatedDescriptorsOption;
	}

	/**
	 * Sets the show all indirect (green) occurrences in extended patterns option
	 * 
	 * @param showRelatedDescriptorsOption
	 *            if <code>true</code>, display all indirect descriptors in
	 *            the task, role, and work product pages
	 */
	public void setShowRelatedDescriptorsOption(boolean showRelatedDescriptorsOption) {
		this.showRelatedDescriptorsOption = showRelatedDescriptorsOption;
	}

	/**
	 * Gets the show task descriptors in navigation tree option.
	 * 
	 * @return <code>true</code> if task descriptors will be displayed in the
	 *         navigation tree
	 */
	public boolean isShowDescriptorsInNavigationTree() {
		return showDescriptorsInNavigationTree;
	}

	/**
	 * Sets the show task descriptors in navigation tree option.
	 * 
	 * @param showDescriptorsInTree
	 *            if <code>true</code>, display the task descriptors in the
	 *            navigation tree
	 */
	public void setShowDescriptorsInNavigationTree(
			boolean showDescriptorsInNavigationTree) {
		this.showDescriptorsInNavigationTree = showDescriptorsInNavigationTree;
	}

	/**
	 * Gets the publish empty categories option.
	 * 
	 * @return <code>true</code> if empty categories will be published
	 */
	public boolean isPublishEmptyCategories() {
		return publishEmptyCategories;
	}

	/**
	 * Sets the publish empty categories option.
	 * 
	 * @param publishEmptyCategories
	 *            if <code>true</code>, publish the empty categories
	 */
	public void setPublishEmptyCategories(boolean publishEmptyCategories) {
		this.publishEmptyCategories = publishEmptyCategories;
	}

	/**
	 * Validates the publish options.
	 * 
	 * @return <code>true</code> if the publish options is valid.
	 */
	public void validate() {
	}

}
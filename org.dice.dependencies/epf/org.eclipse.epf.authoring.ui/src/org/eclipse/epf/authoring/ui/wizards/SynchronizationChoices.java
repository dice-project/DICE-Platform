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
package org.eclipse.epf.authoring.ui.wizards;

import org.eclipse.epf.uma.MethodConfiguration;
import org.eclipse.epf.uma.Process;

/**
 * It stores and manages all choices for synchronization
 * 
 * @author BingXue Xu
 * @since 1.0
 *
 */
public class SynchronizationChoices {

	public static int SYNC_NONE = 0;
	public static int SYNC_FROM_CONTENT = 1;
	public static int SYNC_FROM_PROCESS = 2;
	
	private Process selectedProcess;
	private MethodConfiguration selectedConfig;
	private int syncName= SYNC_FROM_CONTENT;
	private int syncPresName= SYNC_FROM_CONTENT;
	private int syncBriefDesc= SYNC_FROM_CONTENT;
	private int syncOptInput= SYNC_FROM_CONTENT;
	private int syncManInput= SYNC_FROM_CONTENT;
	private int syncOutput= SYNC_FROM_CONTENT;
	private int syncPrimPerformer= SYNC_FROM_CONTENT;
	private int syncAddnPerformer= SYNC_FROM_CONTENT;
	private int syncRespRole= SYNC_FROM_CONTENT;
	private int syncContArtifact= SYNC_FROM_CONTENT;
	private int syncDelivPart= SYNC_FROM_CONTENT;
	private int syncSelStep= SYNC_FROM_CONTENT;
	private int syncGuidance = SYNC_FROM_CONTENT;
	
	public boolean isFinishPressed;

	/**
	 * Get selected process
	 */
	public Process getSelectedProcess() {
		return selectedProcess;
	}

	/**
	 * Set selected process
	 * @param selectedProcess
	 */
	public void setSelectedProcess(Process selectedProcess) {
		this.selectedProcess = selectedProcess;
	}

	/**
	 * Get selected method configuration
	 */
	public MethodConfiguration getSelectedConfig() {
		return selectedConfig;
	}

	/**
	 * Set selected method configuration
	 * @param selectedConfig
	 */
	public void setSelectedConfig(MethodConfiguration selectedConfig) {
		this.selectedConfig = selectedConfig;
	}

	/**
	 * Get option of synchronizing contained artifacts
	 */
	public int getSyncContArtifact() {
		return syncContArtifact;
	}

	/**
	 * Set option for synchronizing contained artifacts
	 */
	public void setSyncContArtifact(int syncContArtifact) {
		this.syncContArtifact = syncContArtifact;
	}

	/**
	 * Get option of synchronizing deliverable parts
	 */
	public int getSyncDelivPart() {
		return syncDelivPart;
	}

	/**
	 * Set option for synchronizing deliverable parts
	 */
	public void setSyncDelivPart(int syncDelivPart) {
		this.syncDelivPart = syncDelivPart;
	}

	/**
	 * Get option of synchronizing mandatory input
	 */
	public int getSyncManInput() {
		return syncManInput;
	}

	/**
	 * Set option for synchronizing mandatory input
	 */
	public void setSyncManInput(int syncManInput) {
		this.syncManInput = syncManInput;
	}

	/**
	 * Get option of synchronizing optional input
	 */
	public int getSyncOptInput() {
		return syncOptInput;
	}

	/**
	 * Set option for synchronizing optional input
	 */
	public void setSyncOptInput(int syncOptInput) {
		this.syncOptInput = syncOptInput;
	}

	/**
	 * Get option of synchronizing output
	 */
	public int getSyncOutput() {
		return syncOutput;
	}

	/**
	 * Set option for synchronizing output
	 */
	public void setSyncOutput(int syncOutput) {
		this.syncOutput = syncOutput;
	}

	/**
	 * Get option of synchronizing presentation name
	 */
	public int getSyncPresName() {
		return syncPresName;
	}

	/**
	 * Set option for synchronizing presetnation name
	 */
	public void setSyncPresName(int syncPresName) {
		this.syncPresName = syncPresName;
	}

	/**
	 * Get option of synchronizing responsible role
	 */
	public int getSyncRespRole() {
		return syncRespRole;
	}

	/**
	 * Set option for synchronizing responsible role
	 */
	public void setSyncRespRole(int syncRespRole) {
		this.syncRespRole = syncRespRole;
	}

	/**
	 * Get option of synchronizing steps
	 */
	public int getSyncSelStep() {
		return syncSelStep;
	}

	/**
	 * Set option for synchronizing steps
	 */
	public void setSyncSelStep(int syncSelStep) {
		this.syncSelStep = syncSelStep;
	}

	/**
	 * Returns boolean value based on wizard is completed or not
	 */
	public boolean isFinishPressed() {
		return isFinishPressed;
	}

	/**
	 * Set option based on wizard in completed or not
	 */
	public void setFinishPressed(boolean isFinishPressed) {
		this.isFinishPressed = isFinishPressed;
	}

	/**
	 * Get option of synchronizing additional performer
	 */
	public int getSyncAddnPerformer() {
		return syncAddnPerformer;
	}

	/**
	 * Set option for synchronizing additional performer
	 */
	public void setSyncAddnPerformer(int syncAddnPerformer) {
		this.syncAddnPerformer = syncAddnPerformer;
	}

	/**
	 * Get option of synchronizing primary performer
	 */
	public int getSyncPrimPerformer() {
		return syncPrimPerformer;
	}

	/**
	 * Set option for synchronizing primary performer
	 */
	public void setSyncPrimPerformer(int syncPrimPerformer) {
		this.syncPrimPerformer = syncPrimPerformer;
	}

	/**
	 * Displays all synchronization options/choices
	 */
	public String toString() {
		
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("[Process:" + ((selectedProcess != null)?selectedProcess.getName():"null") + ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		sbuf.append("Configuration:" + ((selectedConfig != null)?selectedConfig.getName():"null") + ";"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		sbuf.append("Name:" + syncName + ";"); //$NON-NLS-1$ //$NON-NLS-2$
		sbuf.append("PresName:" + syncPresName + ";"); //$NON-NLS-1$ //$NON-NLS-2$
		sbuf.append("BreifDesc:" + syncBriefDesc + ";"); //$NON-NLS-1$ //$NON-NLS-2$
		sbuf.append("OptInput:" + syncOptInput + ";"); //$NON-NLS-1$ //$NON-NLS-2$
		sbuf.append("ManInput:" + syncManInput + ";"); //$NON-NLS-1$ //$NON-NLS-2$
		sbuf.append("Output:" + syncOutput + ";"); //$NON-NLS-1$ //$NON-NLS-2$
		sbuf.append("PrimPerformer:" + syncPrimPerformer + ";"); //$NON-NLS-1$ //$NON-NLS-2$
		sbuf.append("AddnPerformers:" + syncAddnPerformer + ";"); //$NON-NLS-1$ //$NON-NLS-2$
		sbuf.append("RespRole:" + syncRespRole + ";"); //$NON-NLS-1$ //$NON-NLS-2$
		sbuf.append("ContArtifact:" + syncContArtifact + ";"); //$NON-NLS-1$ //$NON-NLS-2$
		sbuf.append("DelivPart:" + syncDelivPart + ";"); //$NON-NLS-1$ //$NON-NLS-2$
		sbuf.append("SelStep:" + syncSelStep + "]"); //$NON-NLS-1$ //$NON-NLS-2$

		return sbuf.toString();
	}

	/**
	 * Get option of synchronizing brief description
	 */
	public int getSyncBriefDesc() {
		return syncBriefDesc;
	}

	/**
	 * Set option for synchronizing brief description
	 */
	public void setSyncBriefDesc(int syncBriefDesc) {
		this.syncBriefDesc = syncBriefDesc;
	}

	/**
	 * Get option of synchronizing name
	 */
	public int getSyncName() {
		return syncName;
	}

	/**
	 * Set option for synchronizing name
	 */
	public void setSyncName(int syncName) {
		this.syncName = syncName;
	}

	public void setSyncGuidance(int i) {
		this.syncGuidance = i;
	}
	
	public int getSyncGuidance() {
		return syncGuidance;
	}
}

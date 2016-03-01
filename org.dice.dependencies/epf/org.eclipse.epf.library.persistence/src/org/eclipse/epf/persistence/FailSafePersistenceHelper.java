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
package org.eclipse.epf.persistence;

import java.io.File;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.persistence.util.PersistenceResources;
import org.eclipse.osgi.util.NLS;

/**
 * @author Phong Nguyen Le
 *
 * @since 1.2
 */
public class FailSafePersistenceHelper {
	private URI finalURI;
	private URI oldURI;
	private String currentTxID;
	private Resource resource;
	private URI tempURI;
	private String id;
	private String backupFile;
	private URIConverter converter;
	private boolean commitEmptyResource;
	
	public FailSafePersistenceHelper(Resource resource, String id) {
		this.resource = resource; 
		this.id = id;
		this.converter = resource.getResourceSet() != null ? resource.getResourceSet().getURIConverter() : null;
	}
	
	public void setCommitEmptyResource(boolean commitEmptyResource) {
		this.commitEmptyResource = commitEmptyResource;
	}

	/**
	 * Sets the temporary URI to save this resource to and it will be renamed to
	 * the original URI when saving is done
	 */
	public URI setTempURI(String txID) {
		if (finalURI == null) {
			finalURI = oldURI = resource.getURI();
			currentTxID = txID;

			tempURI = createTempURI();
			resource.setURI(tempURI);
		}
		return tempURI;
	}
	
	private URI createTempURI() {
		return URI.createFileURI(new StringBuffer(MultiFileXMIResourceImpl.getTempDir()).append(
				File.separator).append(currentTxID)
				.append("new").append(id).toString()); //$NON-NLS-1$
	}
	
	public String getBackupFilePath() {
		String backupFile = new StringBuffer(MultiFileXMIResourceImpl.getTempDir())
				.append(File.separator)
				.append(currentTxID)
				.append("old").append(id).toString(); //$NON-NLS-1$
		return backupFile;
	}
	
	private String toFileString(URI uri) {
		return FileManager.toFileString(uri, converter);
	}
	
	public void commit() {
		if (finalURI != null && (commitEmptyResource || !resource.getContents().isEmpty())) {
			File finalFile = new File(toFileString(finalURI));
			boolean wasMove = !oldURI.equals(finalURI);
			if (wasMove) {
			} else {
				// back up the file
				//
				String backup = getBackupFilePath();
				File bakFile = new File(backup);

				// trying to delete the old backup file if it exists
				//
				if (bakFile.exists()) {
					bakFile.delete();
				}

				if (finalFile.exists()) {
					// some CM provider like ClearCase renamed the versioned
					// file it its repository as soon as user renamed the file
					// in the workspace. To avoid this, use only regular rename
					// routine of java.io.File instead of IResource routine
					//
					if (FileUtil.moveFile(finalFile, bakFile)) {
						backupFile = backup;
					} else {
						String msg = NLS.bind(
								PersistenceResources.renameError_msg,
								finalFile, backup);
						throw new MultiFileIOException(msg);
					}
				}
			}

			// rename the resource file to the original name
			//
			File currentFile = new File(wasMove ? toFileString(oldURI)
					: toFileString(resource.getURI()));
			boolean success = false;
			if (wasMove) {
				success = MultiFileSaveUtil.move(resource, currentFile, finalFile);
			} else {
				// some CM provider like ClearCase renamed the versioned file it
				// its repository as soon as user renamed the file
				// in the workspace. To avoid this, use only regular rename
				// routine of java.io.File instead of IResource routine
				//
				success = FileUtil.moveFile(currentFile, finalFile);
			}
			if (!success) {
				String msg = NLS.bind(PersistenceResources.renameError_msg,
						currentFile, finalFile);
				throw new MultiFileIOException(msg);
			} else {
				if (wasMove) {
					didMove();
				}
			}
		}
	}

	/**
	 * Subclass to override to handle post move
	 */
	protected void didMove() {
	}

	public void deleteBackup() {
		if (backupFile != null) {
			try {
				// FileManager.getInstance().delete(backupFile);
				new File(backupFile).delete();
				backupFile = null;
			} catch (Throwable e) {
				CommonPlugin.INSTANCE.log(e);
				if (MultiFileSaveUtil.DEBUG) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public boolean hasTempURI() {
		if (currentTxID != null) {
			return createTempURI().equals(resource.getURI());
		}
		return false;
	}

	public URI getFinalURI() {
		if (finalURI != null) {
			return finalURI;
		}
		return resource.getURI();
	}

	public boolean restore() {
		File src = null, dest = null;
		boolean moved = false;
		if (backupFile != null) {
			src = new File(backupFile);
			dest = new File(toFileString(getFinalURI()));
		} else {
			moved = oldURI != null && !oldURI.equals(finalURI);
			if (moved) {
				File file = new File(toFileString(getFinalURI()));
				dest = new File(toFileString(oldURI));
				moved = file.exists() && !dest.exists();
				if (moved) {
					src = file;
				}
			}
		}
		if (src != null) {
			if (dest.exists()) {
				FileUtil.moveFile(dest,new File(toFileString(resource.getURI())));
			}
			boolean success;
			if (moved) {
				success = MultiFileSaveUtil.move(resource, src, dest);
				// if(success) {
				// MultiFileSaveUtil.updateURIMappings(this, null);
				// }
			} else {
				success = FileUtil.moveFile(src,dest);
			}
			if (!success) {
				throw new MultiFileIOException(NLS.bind(
						PersistenceResources.restoreResourceError_msg, this));
			}
			return true;
		}
		return false;
	}

	public void txFinished(boolean successful) {
		if (successful) {
			resource.setURI(finalURI);
			resource.setModified(false);
				FileManager.getInstance().refresh(resource);
		} else {
			restoreURI();
		}
		currentTxID = null;
		finalURI = null;
	}

	/**
	 * Restores the resource URI to the original one. This method must be call
	 * after saving regarless of its success.
	 * 
	 */
	private void restoreURI() {
		if (oldURI != null) {
			resource.setURI(oldURI);
		}
	}

}

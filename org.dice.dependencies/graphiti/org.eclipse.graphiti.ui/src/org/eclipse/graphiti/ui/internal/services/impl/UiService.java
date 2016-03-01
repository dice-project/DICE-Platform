/*******************************************************************************
 * <copyright>
 *
 * Copyright (c) 2005, 2010 SAP AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    SAP AG - initial API, implementation and documentation
 *
 * </copyright>
 *
 *******************************************************************************/
package org.eclipse.graphiti.ui.internal.services.impl;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.graphiti.internal.util.T;
import org.eclipse.graphiti.ui.internal.GraphitiUIPlugin;
import org.eclipse.graphiti.ui.internal.services.GraphitiUiInternal;
import org.eclipse.graphiti.ui.internal.services.IUiService;
import org.eclipse.graphiti.ui.internal.util.ui.print.SaveFigureAsImageDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
 * A collection of static helper methods regarding Draw2d.
 * 
 * @noinstantiate This class is not intended to be instantiated by clients.
 * @noextend This class is not intended to be subclassed by clients.
 */
public class UiService implements IUiService {

	@Override
	public byte[] createImage(Image image, int format) throws Exception {
		ByteArrayOutputStream result = new ByteArrayOutputStream();

		try {
			ImageData imDat = null;
			// at the moment saving as GIF is only working if not more than 256
			// colors are used in the figure
			if (format == SWT.IMAGE_GIF) {
				imDat = create8BitIndexedPaletteImage(image);
			}

			if (imDat == null)
				imDat = image.getImageData();

			ImageLoader imageLoader = new ImageLoader();
			imageLoader.data = new ImageData[] { imDat };
			try {
				imageLoader.save(result, format);
			} catch (SWTException e) {
				String error = "Depth: " + Integer.toString(image.getImageData().depth) + "\n" + "X: " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						+ Integer.toString(image.getImageData().x) + "\n" + "Y: " + Integer.toString(image.getImageData().y); //$NON-NLS-1$ //$NON-NLS-2$
				throw new IllegalStateException(error, e);
			}

		} finally {
			image.dispose();
		}

		return result.toByteArray();
	}

	@Override
	public ImageData create8BitIndexedPaletteImage(Image image) throws Exception {
		int upperboundWidth = image.getBounds().width;
		int upperboundHeight = image.getBounds().height;
		ImageData imageData = image.getImageData();

		// determine number of used colors
		ArrayList<Integer> colors = new ArrayList<Integer>();
		for (int x = 0; x < upperboundWidth; x++) {
			for (int y = 0; y < upperboundHeight; y++) {
				int color = imageData.getPixel(x, y);
				Integer colorInteger = new Integer(color);
				if (!colors.contains(colorInteger))
					colors.add(colorInteger);
			}
		}

		// at the moment this is only working if not more than 256 colors are
		// used in the image
		if (colors.size() > 256)
			throw new Exception("Image contains more than 256 colors. \n Automated color reduction is currently not supported."); //$NON-NLS-1$

		// create an indexed palette
		RGB[] rgbs = new RGB[256];
		for (int i = 0; i < 256; i++)
			rgbs[i] = new RGB(255, 255, 255);
		for (int i = 0; i < colors.size(); i++) {
			int pixelValue = ((colors.get(i))).intValue();
			int red = (pixelValue & imageData.palette.redMask) >>> Math.abs(imageData.palette.redShift);
			int green = (pixelValue & imageData.palette.greenMask) >>> Math.abs(imageData.palette.greenShift);
			int blue = (pixelValue & imageData.palette.blueMask) >>> Math.abs(imageData.palette.blueShift);
			rgbs[i] = new RGB(red, green, blue);
		}

		// create new imageData
		PaletteData palette = new PaletteData(rgbs);
		ImageData newImageData = new ImageData(imageData.width, imageData.height, 8, palette);

		// adjust imageData with regard to the palette
		for (int x = 0; x < upperboundWidth; x++) {
			for (int y = 0; y < upperboundHeight; y++) {
				int color = imageData.getPixel(x, y);
				newImageData.setPixel(x, y, colors.indexOf(new Integer(color)));
			}
		}

		return newImageData;
	}

	@Override
	public void startSaveAsImageDialog(GraphicalViewer graphicalViewer) {
		String METHOD = "startSaveAsImageDialog(graphicalViewer)"; //$NON-NLS-1$

		Shell shell = GraphitiUiInternal.getWorkbenchService().getShell();
		// select image-format and scale-factor
		SaveFigureAsImageDialog saveAsImageDialog = new SaveFigureAsImageDialog(shell, graphicalViewer);
		saveAsImageDialog.open();
		if (saveAsImageDialog.getReturnCode() == Window.CANCEL)
			return;

		// select filename with file-dialog
		FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
		String fileExtensions[] = new String[] { "*." + saveAsImageDialog.getFileExtensionForImageFormat() }; //$NON-NLS-1$
		fileDialog.setFilterExtensions(fileExtensions);
		String filename = fileDialog.open();
		if (filename != null) {
			try {
				// add extension to filename (if none exists)
				IPath path = new Path(filename);
				if (path.getFileExtension() == null)
					filename = filename + "." + saveAsImageDialog.getFileExtensionForImageFormat(); //$NON-NLS-1$
				// create image-data
				byte image[] = createImage(saveAsImageDialog.getScaledImage(), saveAsImageDialog.getImageFormat());

				// save image as file
				WorkspaceModifyOperation saveOperation = saveContentsToFile(filename, image);
				new ProgressMonitorDialog(shell).run(false, false, saveOperation);
			} catch (Exception e) {
				String message = "Can not save image: "; //$NON-NLS-1$
				MessageDialog.openError(shell, "Can not save image", message + e.getMessage()); //$NON-NLS-1$
				T.racer().error(METHOD, message + "\nDetails: " + GraphitiUiInternal.getTraceService().getStacktrace(e)); //$NON-NLS-1$
				e.printStackTrace();
			}
		}
	}

	/**
	 * Returns a WorkspeceModifyOperation, which saves the given contents to a
	 * File with the given filename.
	 * 
	 * @param filename
	 *            The name of the file, where to save the contents.
	 * @param contents
	 *            The contents to save into the file.
	 * @throws Exception
	 *             On any errors that occur.
	 */
	private WorkspaceModifyOperation saveContentsToFile(final String filename, final byte contents[]) throws Exception {
		WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
			@Override
			protected void execute(IProgressMonitor monitor) throws CoreException {
				FileOutputStream outputStream = null;
				try {
					outputStream = new FileOutputStream(filename);
					outputStream.write(contents);
				} catch (Exception e) {
					// convert exceptions to CoreExceptions
					Status status = new Status(IStatus.ERROR, GraphitiUIPlugin.PLUGIN_ID, IStatus.ERROR, "Can not save image as file: " //$NON-NLS-1$
							+ e.getMessage(), e);
					throw new CoreException(status);
				} finally {
					try {
						outputStream.close();
					} catch (Exception x) {
						T.racer().error("close output stream failed", x); //$NON-NLS-1$
					}
				}
			}
		};

		return operation;
	}

}

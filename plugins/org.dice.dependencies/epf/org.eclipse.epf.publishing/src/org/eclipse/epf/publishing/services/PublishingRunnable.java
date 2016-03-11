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

import org.eclipse.epf.common.utils.Timer;
import org.eclipse.epf.library.layout.HtmlBuilder;
import org.eclipse.epf.library.layout.IElementLayout;
import org.eclipse.epf.uma.MethodElement;

/**
 * runnable class to monitor the html generation
 * 
 * @author Jinhua Xi
 * @since 1.2
 *
 */
public class PublishingRunnable implements Runnable {

	int timeout_limit = 600000; 
	//PublishingContentValidator validator; 
	HtmlBuilder htmlBuilder;
	String html_file;
	IElementLayout layout;
	
	public PublishingRunnable(HtmlBuilder htmlBuilder, int timeout) {
		this.timeout_limit = timeout;
		this.htmlBuilder = htmlBuilder;
	}
	
	private PublishingContentValidator getValidator() {
		return (PublishingContentValidator)htmlBuilder.getValidator();
	}
	
	public void run() {
		if ( htmlBuilder != null && this.layout != null ) {
			this.html_file = null;
			this.html_file = htmlBuilder.generateHtml(layout);
		}
	}
	
	public String generateHtml(IElementLayout layout) {
		
		this.layout = layout;
		MethodElement element = this.layout.getElement();
		
		Timer timer = new Timer();
		try {
			// set the target element for thre content validator
			getValidator().setTargetElement(element);

			// run the publishing and check the time, if timeout, terminate it
			Thread t = new Thread(this);
			t.start();
			t.join(timeout_limit);
			if (t.isAlive()) {
				// wait for the thread to die and log an error
				timer.stop();
				getValidator()
						.logInfo(
								element,
								"publishing element takes " + timer.getTime() + " mini seconds already and is still not done yet ..."); //$NON-NLS-1$ //$NON-NLS-2$
				timer.start();
				t.join();			
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} finally {
			getValidator().setTargetElement(null);
			getValidator().getReferencedElements().remove(element);

			timer.stop();
			getValidator().logInfo(element,
					timer.getTotalTime() + " mini seconds publishing element"); //$NON-NLS-1$
		}
		
		return this.html_file;
		
	}

	public void dispose() {
		this.layout = null;
		this.html_file = null;
		this.htmlBuilder = null;
	}
}

package org.eclipse.epf.library.edit.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.epf.common.utils.FileUtil;
import org.eclipse.epf.uma.MethodElement;

/**
 * Utility class for logging debug info
 * @author Weiping Lu
 *
 */
public class DebugLog {

	private String indent; //$NON-NLS-1$
	private String prompt;
	private boolean console = false;
	private boolean log = true;

	public DebugLog(String prompt) {
		this.prompt = prompt;
		int n = 5 + (prompt == null ? 0 : prompt.length());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < n; i++) {
			sb.append(" ");	//$NON-NLS-1$
		}
		indent = sb.toString();
	}		
	
	protected boolean isConsole() {
		return console;
	}

	public void setConsole(boolean console) {
		this.console = console;
	}
	
	protected boolean isLog() {
		return log;
	}

	public void setLog(boolean log) {
		this.log = log;
	}
	
	public void log(String msg) {
		if (isConsole()) {
			System.out.println(prompt + msg);
		}
		if (isLog()) {
			FileUtil.log(prompt + msg);
		}
	}
	
	public void logElements(String label, Collection<? extends MethodElement> elements, boolean showEmpty) {
		logElements(label, elements, showEmpty, true);
	}
	
	public void logElements(String label, Collection<? extends MethodElement> elements, boolean showEmpty, boolean showTags) {
		boolean empty = elements == null || elements.isEmpty();
		if (!showEmpty && empty) {
			return;
		}
		String str = prompt + label;	//$NON-NLS-1$
		
		if (!empty) {
			str += " size = " + elements.size();//$NON-NLS-1$			
			
			List<MethodElement> sortedElements = new ArrayList<MethodElement>(elements);			
			Collections.sort(sortedElements, Comparators.NAME_GUID_COMPARATOR);
			
			for (MethodElement element : sortedElements) {
				String line = "\n" + indent + toString(element, 2, showTags, indent + indent);//$NON-NLS-1$
				str += line;
			}
			
		} else {
			str += "\n" + indent + "Empty list";		//$NON-NLS-1$ //$NON-NLS-2$
		}
		if (isConsole()) {
			System.out.println(str + "\n");
		}
		if (isLog()) {
			FileUtil.log(str + "\n");//$NON-NLS-1$
		}
	}
	
	public String toString(MethodElement element, int ix, boolean showTags, String tagLineInden) {
		return DebugUtil.toString(element, ix);
	}
	
	public void logFiles(String label, IFile[] files,  boolean showEmpty) {
		List<IFile> fileList = new ArrayList<IFile>();
		if (files != null && files.length > 0) {
			for (IFile file : files) {
				fileList.add(file);
			}
		}
		logFiles(label, fileList, showEmpty);
	}
 		
	public void logFiles(String label, Collection<IFile> files,  boolean showEmpty) {
		boolean empty = files == null || files.isEmpty();
		if (!showEmpty && empty) {
			return;
		}
		String str = prompt + label;	
		if (!empty) {
			str += " size = " + files.size();//$NON-NLS-1$			
			
			for (IFile file : files) {
				String line = "\n" + indent + file;//$NON-NLS-1$
				str += line;
			}
			
		} else {
			str += "\n" + indent + "Empty list";		//$NON-NLS-1$ //$NON-NLS-2$
		}
		if (isConsole()) {
			System.out.println(str + "\n");
		}
		if (isLog()) {
			FileUtil.log(str + "\n");//$NON-NLS-1$
		}
	}
	
}

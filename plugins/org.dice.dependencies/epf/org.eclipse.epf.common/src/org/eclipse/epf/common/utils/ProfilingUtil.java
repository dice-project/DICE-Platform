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
package org.eclipse.epf.common.utils;

import java.io.PrintStream;


/**
 * Utility class for profiling
 * 
 * @author Weiping Lu
 * @since 1.0
 */

public class ProfilingUtil {

	public static boolean traceFootprint = false;
	
	private static PrintStream output = System.out;
	
	public static void setOutput(PrintStream ps) {
		output = ps;
	}
	
	public static void fullGC() {
		Runtime rt = Runtime.getRuntime();
		long isFree = rt.freeMemory();
		long wasFree;
		do {
			wasFree = isFree;
			rt.runFinalization();
			rt.gc();
			isFree = rt.freeMemory();
		} while (isFree > wasFree);
	}
	
	public static long traceUsedMemory(String location) {
		Runtime rt = Runtime.getRuntime();
		long usedMem = rt.totalMemory() - rt.freeMemory();
		output.println("Footprint> " + location + ": " + usedMem/1000 + " K"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$	
										
		return usedMem;
	}

	public static long traceUsedMemoryDiff(String locationInfo, long currUsed, long PrevUsed) {
		long diff = currUsed - PrevUsed;
		output.println("Footprint> Diff, " + locationInfo + ": " + diff/1000 + " K\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
										
		return diff;
	}
	
}

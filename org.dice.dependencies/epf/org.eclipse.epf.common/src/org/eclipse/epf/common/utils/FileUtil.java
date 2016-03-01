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
package org.eclipse.epf.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.epf.common.CommonPlugin;
import org.osgi.framework.Bundle;

/**
 * Utility class for managing directories and files.
 * 
 * @author Kelvin Low
 * @author Jinhua Xi
 * @since 1.0
 */
public class FileUtil {

	/**
	 * Platform-specific line separator.
	 */
	public static final String LINE_SEP = System.getProperty("line.separator"); //$NON-NLS-1$

	/**
	 * Platform-specific file separator.
	 */
	public static final String FILE_SEP = System.getProperty("file.separator"); //$NON-NLS-1$

	/**
	 * Platform-specific line separator length.
	 */
	public static final int LINE_SEP_LENGTH = LINE_SEP.length();

	/**
	 * UNC path prefix.
	 */
	public static final String UNC_PATH_PREFIX = "\\\\"; //$NON-NLS-1$

	/**
	 * UNC path prefix length.
	 */
	public static final int UNC_PATH_PREFIX_LENGTH = UNC_PATH_PREFIX.length();

	/**
	 * ISO-8859-1 encoding.
	 */
	public static final String ENCODING_ISO_8859_1 = "ISO-8859-1"; //$NON-NLS-1$

	/**
	 * UTF-8 encoding.
	 */
	public static final String ENCODING_UTF_8 = "UTF-8";//$NON-NLS-1$
	
	private static Map<File, File> copiedFileMap;

	/**
	 * Private constructor to prevent this class from being instantiated. All
	 * methods in this class should be static.
	 */
	private FileUtil() {
	}

	/**
	 * Returns the absolute path for a file or directory.
	 * 
	 * @param file
	 *            a file or directory
	 * @return the absolute path to the file or directory
	 */
	public static String getAbsolutePath(File file) {
		return file.getAbsolutePath().replace('\\', '/');
	}

	/**
	 * Returns the absolute path for a file or directory.
	 * 
	 * @param path
	 *            a path to a file or directory
	 * @return an absolute path to the file or directory
	 */
	public static String getAbsolutePath(String path) {
		return getAbsolutePath(new File(path));
	}

	/**
	 * Returns the absolute path for a URL.
	 * 
	 * @param url
	 *            a URL
	 * @return the absolute path of the URL
	 */
	public static String getAbsolutePath(URL url) {
		String pathName = url.getFile().substring(1);
		String result = NetUtil.decodeUrl(pathName, null);
		return result;
	}

	/**
	 * Returns the parent directory of a path.
	 * 
	 * @param path
	 *            a path name
	 * @return the name of the parent directory
	 */
	public static String getParentDirectory(String path) {
		return (new File(path)).getParent();
	}

	/**
	 * Returns the file name and extension from a path.
	 * 
	 * @param path
	 *            a path name
	 * @return the file name including the file extension
	 */
	public static String getFileName(String path) {
		return getFileName(path, true);
	}

	/**
	 * Returns the file name from a path, with or without the file extension.
	 * 
	 * @param path
	 *            a path name
	 * @param withExtension
	 *            if <code>true</code>, include the file extension in the
	 *            result
	 * @return the file name with or without the file extension
	 */
	public static String getFileName(String path, boolean withExtension) {
		String normalizedPath = path.replace('\\', '/');

		int prefixLength = 0;
		if (normalizedPath.startsWith(NetUtil.FILE_URI_PREFIX)) {
			prefixLength = NetUtil.FILE_URI_PREFIX_LENGTH;
		} else if (normalizedPath.startsWith(NetUtil.HTTP_URI_PREFIX)) {
			prefixLength = NetUtil.HTTP_URI_PREFIX_LENGTH;
		}

		String fileName;
		int index = normalizedPath.lastIndexOf("/"); //$NON-NLS-1$
		if (index < prefixLength) {
			fileName = normalizedPath.substring(prefixLength);
		} else {
			fileName = path.substring(index + 1);
		}

		if (withExtension) {
			return fileName;
		}

		index = fileName.indexOf("."); //$NON-NLS-1$
		return (index > 0) ? fileName.substring(0, index) : fileName;
	}

	/**
	 * Returns the relative path of a path from a base path.
	 * 
	 * @param path
	 *            a path
	 * @param basePath
	 *            the base path
	 * @return a relative path
	 */
	public static String getRelativePathToBase(File path, File basePath) {
		try {
			String dir = path.toURL().toExternalForm();
			String baseDir = basePath.toURL().toExternalForm();
			StringBuffer result = new StringBuffer();
			if (dir.indexOf(baseDir) == 0) {
				String delta = dir.substring(baseDir.length());
				for (int i = 0; i < delta.length(); i++) {
					if (delta.charAt(i) == '/') {
						result.append("../"); //$NON-NLS-1$
					}
				}
			}
			return result.toString();
		} catch (Exception e) {
			return ""; //$NON-NLS-1$
		}
	}

	public static String getRelativePath(File path, File basePath) {
		try {
			String dir = path.toURL().toExternalForm();
			String baseDir = appendSeparator(basePath.toURL().toExternalForm(),
					"/"); //$NON-NLS-1$
			StringBuffer result = new StringBuffer();
			while (dir.indexOf(baseDir) == -1) {
				basePath = basePath.getParentFile();
				baseDir = appendSeparator(basePath.toURL().toExternalForm(),
						"/"); //$NON-NLS-1$
				result.append("../"); //$NON-NLS-1$
			}
			if (dir.indexOf(baseDir) == 0) {
				String delta = dir.substring(baseDir.length());
				result.append(delta);
			}
			return result.toString();
		} catch (Exception e) {
			return ""; //$NON-NLS-1$
		}
	}

	/**
	 * Appends the platform specific path separator to the end of a path.
	 * 
	 * @param path
	 *            a path name
	 * @return the path name appended with the platform specific path separator
	 */
	public static String appendSeparator(String path) {
		return appendSeparator(path, File.separator);
	}

	/**
	 * Appends the given path separator to the end of a path
	 * 
	 * @param path
	 *            a path name
	 * @param separator
	 *            a path separator
	 * @return the path name appended with the given separator
	 */
	public static String appendSeparator(String path, String separator) {
		return path.endsWith(separator) ? path : path + separator;
	}

	/**
	 * Removes the ending path separator from a path.
	 * 
	 * @param path
	 *            a path name
	 * @return the path name minus the platform specific path separator
	 */
	public static String removeSeparator(String path) {
		return path.endsWith(File.separator) ? path.substring(0,
				path.length() - 1) : path;
	}

	/**
	 * Removes the ending path separator from a path.
	 * 
	 * @param path
	 *            a path name
	 * @return the path name minus the path separator "\\" or "/"
	 */
	public static String removeAllSeparator(String path) {
		return path.endsWith("/") || path.endsWith("\\") ? path.substring(0, path.length() - 1) : path; //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Removes the ending path separator from a path.
	 * 
	 * @param path
	 *            a path name
	 * @param separator
	 *            a path separator
	 * @return the path name minus the separator
	 */
	public static String removeSeparator(String path, String separator) {
		return path.endsWith(separator) ? path.substring(0, path.length() - 1)
				: path;
	}

	/**
	 * Replaces the file name with another in a path.
	 * 
	 * @param path
	 *            a path name
	 * @param oldFileName
	 *            the old file name
	 * @param newFileName
	 *            the new file name
	 * @return the new path name with the new file name
	 */
	public static String replaceFileName(String path, String oldFileName,
			String newFileName) {
		int index = path.lastIndexOf(oldFileName);
		return path.substring(0, index) + newFileName;
	}

	/**
	 * Replaces the file extension with another in a path.
	 * 
	 * @param path
	 *            a path name
	 * @param oldFileExt
	 *            rhe old file extension
	 * @param newFileExt
	 *            the new file extension
	 * @return the new path with the new file extension
	 */
	public static String replaceExtension(String path, String oldExt,
			String newExt) {
		int index = path.lastIndexOf(oldExt);
		return path.substring(0, index) + newExt;
	}

	/**
	 * Returns the locale-specific path of a base path.
	 * 
	 * @param path
	 *            a base path name
	 * @param localeStr
	 *            a locale string
	 * @return the locale-specific path
	 */
	public static String getLocalePath(String path, String localeStr) {
		if (StrUtil.isBlank(localeStr)) {
			return path;
		}
		String fileName = getFileName(path);
		return replaceFileName(path, fileName, localeStr + "/" + fileName); //$NON-NLS-1$
	}

	/**
	 * Returns the locale-specific path of a base path.
	 * 
	 * @param path
	 *            a base path name
	 * @param locale
	 *            a locale object
	 * @return the locale-specific path
	 */
	public static String getLocalePath(String path, Locale locale) {
		return locale == null ? path : getLocalePath(path, locale.toString());
	}

	/**
	 * Writes the given text to a text file.
	 * 
	 * @param fileName
	 *            the target file name
	 * @param text
	 *            the text to write
	 * @return <code>true</code> if the given text is written successfully to
	 *         file
	 */
	public static boolean writeFile(String filename, String text) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(filename);
			writer.write(text);
			writer.flush();
		} catch (IOException e) {
		} finally {
			if (writer != null) {
				try {
					writer.close();
					return true;
				} catch (Exception e) {
				}
			}
		}
		return false;
	}

	/**
	 * Write the given text to a file with UTF-8 encoding.
	 * 
	 * @param fileName
	 *            the target file name
	 * @param text
	 *            the text to write
	 * @param append
	 *            if <code>true</code>, append the text to the end of the
	 *            file, if <code>false</code>, override the file
	 * @return <code>true</code> if the given text is written successfully to
	 *         file
	 */
	public static boolean writeUTF8File(String filename, String text) {
		return writeUTF8File(filename, text, false);
	}

	/**
	 * Write the given text to a file with UTF-8 encoding.
	 * 
	 * @param fileName
	 *            the target file name
	 * @param text
	 *            the text to write
	 * @param append
	 *            if <code>true</code>, append the text to the end of the
	 *            file, if <code>false</code>, override the file
	 * @return <code>true</code> if the given text is written successfully to
	 *         file
	 */
	public static boolean writeUTF8File(String filename, String text,
			boolean append) {
		OutputStreamWriter writer = null;
		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(filename, append);
			writer = new OutputStreamWriter(fileOut, ENCODING_UTF_8);
			writer.write(text);
			writer.flush();
			fileOut.flush();
		} catch (IOException e) {
			CommonPlugin.getDefault().getLogger().logError(e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
					return true;
				} catch (Exception e) {
				}
			}
			if (fileOut != null) {
				try {
					fileOut.close();
					return true;
				} catch (Exception e) {
				}
			}
		}
		return false;
	}

	/**
	 * Write the content of the given URI to an output stream.
	 * 
	 * @param uri
	 *            the source URI
	 * @param output
	 *            the output stream
	 */
	public static void writeFile(String uri, OutputStream output)
			throws IOException {
		if (uri == null) {
			return;
		}

		InputStream input = null;
		try {
			input = NetUtil.getInputStream(uri);
			int bytesRead;
			byte[] buf = new byte[4096];
			while ((bytesRead = input.read(buf, 0, 4096)) > 0) {
				output.write(buf, 0, bytesRead);
			}
			output.flush();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * Write the content of the given URI to a <code>PrintWriter</code>.
	 * 
	 * @param uri
	 *            the source URI
	 * @param writer
	 *            the <code>PrintWriter</code> object
	 */
	public static void writeFile(String uri, PrintWriter pw) throws IOException {
		if (uri == null) {
			return;
		}

		InputStreamReader input = null;
		try {
			input = new InputStreamReader(NetUtil.getInputStream(uri));
			int charsRead;
			char[] buf = new char[4096];
			while ((charsRead = input.read(buf, 0, 4096)) > 0) {
				pw.write(buf, 0, charsRead);
			}
			pw.flush();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * Recursively delete all sub-directories and files in a directory except
	 * for the directory itself.
	 * 
	 * @param dir
	 *            the directory containing the sub-directories and files
	 * @return <code>true</code> if the delete operation is successful
	 */
	public static boolean deleteAllFiles(String dir) {
		boolean ret = true;
		File targetDir = new File(dir);
		File[] files = targetDir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					ret = ret && deleteAllFiles(files[i].getAbsolutePath());
				}
				ret = ret && files[i].delete();
			}
		}

		return ret;
	}
	
	public static boolean deleteTree(File file) {
		boolean ret = true;

		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {
				for (File f : files) {
					if (!deleteTree(f)) {
						ret = false;
					}
				}
			}
		}

		if (!file.delete()) {
			ret = false;
		}

		return ret;
	}

	/**
	 * Recursively delete all sub-directories and files in a directory except
	 * for the directory itself and the specified file.
	 * 
	 * @param dir
	 *            the directory containing the sub-directories and files
	 * @param filesNotToDelete
	 *            a list of files and/or directories that should not be deleted
	 * @return <code>true</code> if delete operation is successful
	 */
	public static boolean deleteAllFiles(String dir, List<File> filesNotToDelete) {
		boolean ret = true;
		File targetDir = new File(dir);
		File[] files = targetDir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				if (!filesNotToDelete.contains(files[i])) {
					if (files[i].isDirectory()) {
						ret = ret
								&& deleteAllFiles(files[i].getAbsolutePath(),
										filesNotToDelete);
					}
					ret = ret && files[i].delete();
				}
			}
		}

		return ret;
	}

	/**
	 * Copies the content of the source file to the target file. Will overwrite
	 * an existing file if it has write permission
	 * 
	 * @param srcFile
	 *            the source file or path
	 * @param tgtFile
	 *            the target file or path
	 */
	public static boolean copyFile(File srcFile, File tgtFile) {
		Map<File, File> map = getCopiedFileMap();
		File keyFile = null;
		File valFile = null;
		if (map != null) {
			try {
				keyFile = tgtFile.getCanonicalFile();
				valFile = srcFile.getCanonicalFile();
				if (valFile.equals(map.get(keyFile))) {
					return true;
				}
			} catch (Exception e) {
				keyFile = valFile = null;
			}			
		}
				
		try {
			boolean ret = copyfile(srcFile, tgtFile);
			if (map != null && keyFile != null && valFile != null) {
				map.put(keyFile, valFile);
			}
			return ret;
		} catch (IOException ex) {
			CommonPlugin.getDefault().getLogger().logError(ex);
			return false;
		}
	}

	/**
	 * Copies the content of the source file to the target file.
	 * 
	 * @param srcFileName
	 *            the source file name
	 * @param tgtFileName
	 *            the target file name
	 */
	public static boolean copyFile(String srcFileName, String tgtFileName) {
		return copyFile(new File(srcFileName), new File(tgtFileName));
	}

	/**
	 * Copies one file to another.
	 * <p>
	 * If both source and destination are directories, delegates to
	 * copydirectory().
	 * <p>
	 * source must exist and be readable
	 * <p>
	 * cannot copy a directory to a file
	 * <p>
	 * will not copy if timestamps and filesize match, will overwrite otherwise
	 * 
	 * @param source
	 *            the source file
	 * @param dest
	 *            the destination file
	 * @throws IOException
	 *             if an error occurs during the copy operation
	 */
	private static boolean copyfile(File source, File dest) throws IOException {
		if (source.equals(dest))
			// do not copy equal files
			return false;

		if (!source.exists() || !source.canRead()) {
			// source does not exist or can't read
			return false;
		}

		if (dest.exists() && !dest.canWrite()) {
			// dest exists and cannot be written
			return false;
		}

		if (source.isDirectory()) {
			if (dest.isFile()) {
				// can't copy a directory to a file
				return false;
			} else {
				// delegate to copydirectory
				return copydirectory(source, dest);
			}
		} else {
			// source is a file
			if (dest.isDirectory()) {
				String sourceFileName = source.getName();
				return copyfile(source, new File(dest, sourceFileName));
			}
			// both source and dest are files
			boolean needCopy = true;
			if (dest.exists()) {
				needCopy = (dest.lastModified() != source.lastModified())
						|| (dest.length() != source.length());
			}

			if (needCopy) {
				FileInputStream input = null;
				FileOutputStream output = null;

				try {
					input = new FileInputStream(source);
					FileChannel in = input.getChannel();
					if (!dest.exists()) {
						dest.getParentFile().mkdirs();
					}
					output = new FileOutputStream(dest);
					FileChannel out = output.getChannel();
					out.transferFrom(in, 0, source.length());
					dest.setLastModified(source.lastModified());
					return true;
				} finally {
					if (input != null) {
						try {
							input.close();
						} catch (IOException e) {
						}
					}
					if (output != null) {
						try {
							output.close();
						} catch (IOException e) {
						}
					}
				}
			} else {
				// did not copy
				// return true because dest file is same as source
				return true;
			}
		}
	}
	
	public static boolean copyFileToDir(File srcFile, String tgtDirName) {
		String tgtFileName = tgtDirName + File.separator + srcFile.getName();
		File tgtFile = new File(tgtFileName);
		
		if (tgtFile.exists()) {
			return true;
		} else {
			return copyFile(srcFile, tgtFile);
		}		
	}

	/**
	 * Copies the content of a directory to another directory.
	 * 
	 * @param srcDirName
	 *            the source directory name
	 * @param tgtDirName
	 *            the target directory name
	 */
	public static boolean copyDir(File srcDir, File tgtDir) {
		try {
			return copydirectory(srcDir, tgtDir);
		} catch (IOException ex) {
			CommonPlugin.getDefault().getLogger().logError(ex);
			return false;
		}
	}

	/**
	 * Copies the content of a directory to another directory.
	 * 
	 * @param srcDirName
	 *            the source directory name
	 * @param tgtDirName
	 *            the target directory name
	 */
	public static boolean copyDir(String srcDirName, String tgtDirName) {
		return copyDir(new File(srcDirName), new File(tgtDirName));
	}

	/**
	 * Copies one directory to another - operates ONLY on directories.
	 * <p>
	 * Both source and dest must exist.
	 */
	private static boolean copydirectory(File sourceDir, File destDir)
			throws IOException {
		if (!sourceDir.exists() || !destDir.exists()) {
			return false;
		}

		if (!sourceDir.isDirectory() || !destDir.isDirectory()) {
			return false;
		}

		File[] files = sourceDir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				// calc destination name
				String destName = destDir
						+ File.separator
						+ files[i].getAbsolutePath().substring(
								sourceDir.getAbsolutePath().length() + 1);
				if (files[i].isFile()) {
					// copy the file
					copyfile(files[i], new File(destName));
				} else if (files[i].isDirectory()) {
					// copy directory recursively
					File destFile = new File(destName);
					destFile.mkdirs();
					copydirectory(files[i], destFile);
				}
			}
		}
		return true;
	}

	// for some reason, this guy locks the file, if you try to update the file,
	// got the following exception
	// java.io.FileNotFoundException:
	// (The requested operation cannot be performed on a file with a user-mapped
	// section open)
	// need to handle later
	public static CharBuffer readFile(File file) throws IOException {
		FileInputStream input = null;
		CharBuffer charBuffer = null;
		try {
			input = new FileInputStream(file);
			FileChannel inChannel = input.getChannel();
			int length = (int) inChannel.size();
			MappedByteBuffer byteBuffer = inChannel.map(
					FileChannel.MapMode.READ_ONLY, 0, length);
			Charset charset = Charset.forName(ENCODING_ISO_8859_1);
			CharsetDecoder decoder = charset.newDecoder();
			charBuffer = decoder.decode(byteBuffer);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
				}
			}
		}
		return charBuffer;
	}

	public static String readInputStream(InputStream input) throws IOException {
		String result = "";  //$NON-NLS-1$
		byte[] readData = new byte[8 * 1024];
		try {
			int bytesRead = 0;
			while ( (bytesRead = input.read(readData)) > 0) {
				result += new String(readData, 0, bytesRead);
			}
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
				}
			}
		}
		return result;
	}

	public static StringBuffer readFile(File file, String encoding)
			throws IOException {

		StringBuffer result = new StringBuffer();
		FileInputStream fis = null;
		InputStreamReader reader = null;
		int size;
		try {
			Charset cs = Charset.forName(encoding);
			CharsetDecoder csd = cs.newDecoder();
			csd.onMalformedInput(CodingErrorAction.REPLACE);
			char[] buffer = new char[1024];
			fis = new FileInputStream(file);
			reader = new InputStreamReader(fis, csd);
			while ((size = reader.read(buffer, 0, 1024)) > 0) {
				result.append(buffer, 0, size);
			}
		} catch (Exception e) {
			CommonPlugin.getDefault().getLogger().logError(e);
		} finally {
			if (fis != null) {
				fis.close();
			}

			if (reader != null) {
				reader.close();
			}
		}

		return result;
	}
	
	public static long getSize(File file) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			return fis.getChannel().size();			
		}
		catch (IOException e) {
			//
		}
		finally {
			try {
				fis.close();
			}
			catch(Exception e) {
				//
			}
		}
		return -1;
	}

	/**
	 * Uses Java 1.4's FileLock class to test for a file lock
	 * 
	 * @param file
	 * @return
	 */
	public static boolean isFileLocked(File file) {
		boolean isLocked = false;
		FileOutputStream input = null;
		FileLock lock = null;

		if (!file.exists()) {
			return false;
		}
		try {
			input = new FileOutputStream(file);
			FileChannel fileChannel = input.getChannel();

			lock = fileChannel.tryLock();

			if (lock == null)
				isLocked = true;
			else
				lock.release();
		} catch (Exception e) {
			if (e instanceof SecurityException)
				// Can't write to file.
				isLocked = true;
			else if (e instanceof FileNotFoundException)
				isLocked = false;
			else if (e instanceof IOException)
				isLocked = true;
			// OverlappingFileLockException means that this JVM has it locked
			// therefore it is not locked to us
			else if (e instanceof OverlappingFileLockException)
				isLocked = false;
			// Could not get a lock for some other reason.
			else
				isLocked = true;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (Exception ex) {
				}
			}
		}
		return isLocked;
	}

	/**
	 * Locks a file for the current JVM. Will create the file if it does not
	 * exist
	 * 
	 * @param file
	 * @return a FileLock object, or null if file could not be locked
	 */
	public static FileLock lockFile(File file) {
		FileOutputStream input = null;
		FileLock lock = null;
		try {
			input = new FileOutputStream(file);
			FileChannel fileChannel = input.getChannel();
			lock = fileChannel.tryLock();

			if (lock.isValid())
				return lock;
		} catch (Exception e) {
			// Could not get a lock for some reason.
			return null;
		} finally {
			try {
				if (input != null && (lock == null || !lock.isValid())) {
					input.close();
				}
			} catch (Exception ex) {
			}
		}
		return null;
	}

	/**
	 * Gets all files in a specified path.
	 * 
	 * @param path
	 *            absolute path of a folder
	 * @param fileList
	 *            a list to collect the files
	 * @param recursive
	 *            if <code>true</code>, find the files in sub folders as well
	 */
	public static void getAllFiles(File path, List<File> fileList,
			boolean recursive) {
		if (path.isDirectory()) {
			File[] files = path.listFiles();
			if (files != null) {
				for (int i = 0; i < files.length; i++) {
					if (files[i].isFile()) {
						fileList.add(files[i]);
					} else if (recursive) {
						getAllFiles(files[i], fileList, recursive);
					}
				}
			}
		}
	}

	/**
	 * Given a directory and extension, returns all files (recursively) whose
	 * extension starts with a given extension.
	 * 
	 * @param file
	 *            a directory
	 * @param extension
	 *            a file extension
	 * @return a colleciton of <code>File</code> with the given extension
	 */
	public static List<File> fileList(File f, String extension) {
		extension = extension.toUpperCase();
		List<File> returnList = new ArrayList<File>();
		try {
			if (f.isDirectory()) {
				String[] flist = f.list();
				for (int i = 0; i < flist.length; ++i) {
					File fc = new File(f.getPath(), flist[i]);
					returnList.addAll(fileList(fc, extension));
				}
			} else {
				if (extension != null) {
					String name = f.getName().toUpperCase();
					if (name.lastIndexOf(".") != -1) //$NON-NLS-1$
						if (name
								.substring(name.lastIndexOf(".") + 1).startsWith(extension)) { //$NON-NLS-1$
							returnList.add(f);
						}
				} else
					returnList.add(f);
			}
		} catch (Exception e) {
			CommonPlugin.getDefault().getLogger().logError(e);
		}
		return returnList;
	}

	/**
	 * Given a directory and extension, returns all files (recursively) whose
	 * extension does not starts with a given extension.
	 * 
	 * @param file
	 *            a directory
	 * @param extension
	 *            a file extension
	 * @return a colleciton of <code>File</code> without the given extension
	 */
	public static List<File> fileListExcludeExt(File f, String extension) {
		List<File> returnList = new ArrayList<File>();
		try {
			if (f.isDirectory()) {
				String[] flist = f.list();
				for (int i = 0; i < flist.length; ++i) {
					File fc = new File(f.getPath(), flist[i]);
					returnList.addAll(fileListExcludeExt(fc, extension));
				}
			} else {
				if (extension != null) {
					String name = f.getName();
					if (name.lastIndexOf(".") != -1) //$NON-NLS-1$
						if (!(name.substring(name.lastIndexOf(".") + 1).startsWith(extension))) { //$NON-NLS-1$
							returnList.add(f);
						}
				} else
					returnList.add(f);
			}
		} catch (Exception e) {
			CommonPlugin.getDefault().getLogger().logError(e);
		}
		return returnList;
	}

	/**
	 * Gets all file paths in the specified path.
	 * 
	 * @param path,
	 *            absolute path of a folder
	 * @param recursive
	 *            if <code>true</code>, find the files in sub folders as well
	 */
	public static ArrayList<String> getAllFileAbsolutePaths(File path,
			boolean recursive) {
		ArrayList<File> files = new ArrayList<File>();
		getAllFiles(path, files, recursive);
		ArrayList<String> paths = new ArrayList<String>();
		for (int i = 0; i < files.size(); i++) {
			String absPath = ((File) files.get(i)).getAbsolutePath();
			paths.add(absPath);
		}
		return paths;
	}

	/**
	 * Moves a file from a directory to another.
	 * <p>
	 * Attempts to rename the file first. If that fails, will copy the
	 * sourceFile to destFile and delete the sourceFile.
	 * 
	 * @param sourceFile
	 *            the source file
	 * @param destFile
	 *            the destination file
	 * @return
	 */
	public static boolean moveFile(File sourceFile, File destFile) {
		try {
			doMoveFile(sourceFile, destFile);
			return true;
		} catch (Exception e) {
			CommonPlugin.getDefault().getLogger().logError(e);
			return false;
		}
	}

	public static void doMoveFile(File sourceFile, File destFile) throws IOException {
		// Try to rename the source file to the destination file.
		if (!sourceFile.renameTo(destFile)) {
			// Try to copy the source file to the destination file and
			// delete
			// the source file.
			copyfile(sourceFile, destFile);
			sourceFile.delete();
		}
	}

	private static Map<File, File> getCopiedFileMap() {
		return copiedFileMap;
	}

	public static void setCopiedFileMap(Map<File, File> copiedFileMap) {
		FileUtil.copiedFileMap = copiedFileMap;
	}
	
	
	/**
	 * Unzips the contents of a zip file to a directory
	 * 
	 * @param zipfile
	 *            source zip file
	 * @param tgtDir
	 *            target directory
	 */
	public static boolean unzip(File srcZipFile, File tgtDir) {
		if (! srcZipFile.exists() || ! tgtDir.exists()) {
			return false;
		}

		if (! tgtDir.isDirectory()) {
			return false;
		}

		try {
			ZipFile zipFile = new ZipFile(srcZipFile);
			Enumeration entries = zipFile.entries();

			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				
				File tgtFile = new File(tgtDir, entry.getName());

				if (entry.isDirectory() && ! tgtFile.exists()) {
					tgtFile.mkdirs();
					
				} else {
					File parentFolder = tgtFile.getParentFile();
					if (! parentFolder.exists()) {
						parentFolder.mkdirs();
					}
										
					copyInputStream(zipFile.getInputStream(entry),
							new BufferedOutputStream(new FileOutputStream(
									new File(tgtDir, entry.getName()))));
				}
			}

			zipFile.close();
		} catch (IOException ioe) {
			return false;
		}
		return false;
	}

	private static final void copyInputStream(InputStream in, OutputStream out)
			throws IOException {
		byte[] buffer = new byte[1024];
		int len;

		while ((len = in.read(buffer)) >= 0)
			out.write(buffer, 0, len);

		in.close();
		out.close();
	}
	
	public static class FileChecker {		
		public IStatus syncExecCheckModify(List<String> modifiedFiles) {
			return Status.OK_STATUS;
		}				
	}
	
	private static FileChecker fileChecker;
	private static void loadDeafultFileChecker() {
		if (fileChecker != null) {
			return;
		}
		Bundle bundle = Platform.getBundle("org.eclipse.epf.import");
		try { 
			bundle.start();
		} catch (Exception e) {
		}
	}
	public static void setFileChecker(FileChecker fileChecker) {
		FileUtil.fileChecker = fileChecker;
	}
	
	public static IStatus syncExecCheckModify(List<String> modifiedFiles) {
		loadDeafultFileChecker();
		if (fileChecker != null) {
			return fileChecker.syncExecCheckModify(modifiedFiles);
		}
		return Status.OK_STATUS;
	}
		
	public static boolean binaryEqual(File f1, File f2) {
		BufferedInputStream is1 = null;
		BufferedInputStream is2 = null;

		int bufSz = 1024;
		byte buff1[] = new byte[bufSz];
		byte buff2[] = new byte[bufSz];

		try {
			is1 = new BufferedInputStream(new FileInputStream(f1));
			is2 = new BufferedInputStream(new FileInputStream(f2));

			int read1 = -1;
			int read2 = -1;

			do {
				int offset1 = 0;
				while (offset1 < bufSz
						&& (read1 = is1.read(buff1, offset1, bufSz - offset1)) >= 0) {
					offset1 += read1;
				}

				int offset2 = 0;
				while (offset2 < bufSz
						&& (read2 = is2.read(buff2, offset2, bufSz - offset2)) >= 0) {
					offset2 += read2;
				}
				if (offset1 != offset2)
					return false;
				if (offset1 != bufSz) {
					Arrays.fill(buff1, offset1, bufSz, (byte) 0);
					Arrays.fill(buff2, offset2, bufSz, (byte) 0);
				}
				if (!Arrays.equals(buff1, buff2))
					return false;
			} while (read1 >= 0 && read2 >= 0);
			if (read1 < 0 && read2 < 0)
				return true; 
			return false;

		} catch (Exception e) {
			CommonPlugin.getDefault().getLogger().logError(e);
			return false;
		}
	}
	
	public static void log(String msg) {
		CommonPlugin.getDefault().getLogger().logInfo(msg);
	}
	
	private static ValidateEdit validateEdit;
	public static ValidateEdit getValidateEdit() {
		if (validateEdit != null) {
			return validateEdit;
		}
		Object obj = ExtensionHelper.create(ValidateEdit.class, null);
		if (obj instanceof ValidateEdit) {
			validateEdit = (ValidateEdit) obj;
			return validateEdit;
		}
		//Don't cache this default one.
		return new ValidateEdit();
	}
	
	public static IStatus validateEdit(IWorkspace workspace, IFile[] files, Object context) {
		ValidateEdit validateEdit = getValidateEdit();
		return validateEdit.validateEdit(workspace, files, context);
	}
	
}
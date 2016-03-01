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
package org.eclipse.epf.publishing.services.index;

import java.util.StringTokenizer;

class HelpMessages
{

  static String EXCEPTION = "Exception: "; //$NON-NLS-1$
  static String BAD_OUTPUT_STREAM = "Bad output stream!"; //$NON-NLS-1$
  static String BAD_PARSER = "Bad parser object!"; //$NON-NLS-1$
  static String INPUT_PARAMETER_NULL = "Input parameter is null!"; //$NON-NLS-1$
  static String INVALID_PARAMETER_NAME = "No valid parameter named "; //$NON-NLS-1$
  static String WRITE_EXCEPTION = "Exception writing to file "; //$NON-NLS-1$
  static String MISSING_URL = "Missing url "; //$NON-NLS-1$
  static String MISSING_LABEL = "Missing label "; //$NON-NLS-1$
  static String MISSING_NODE = "Missing node "; //$NON-NLS-1$
  static String OPEN_FILE_FAILED = "Error reading file "; //$NON-NLS-1$
  static String WRITE_FILE = "Writes file: "; //$NON-NLS-1$
  static String WRITE_ERROR = "Error writing file: "; //$NON-NLS-1$
  static String PARAMETER_MISSING = "Parameter missing: "; //$NON-NLS-1$
  static String PARAMETER_NOT_NUMBER = " have to be a number, the value is not a number "; //$NON-NLS-1$
  static String PARAMETER_MISSING_VALUE = "Value missing for parameter "; //$NON-NLS-1$
  static String FONT_AND_STYLE_MISSING = "Both of the following parameters are missing "; //$NON-NLS-1$
  static String NO_DIRECTORY = " is not a readable directory!"; //$NON-NLS-1$
  static String DIRECTORY_NOT_PARSED = "Does not parse directory "; //$NON-NLS-1$
  static String NOT_ENOUGH_INPUT = "Not enough input, operation canceled!"; //$NON-NLS-1$
  static String INCORRECT_HEADERFILE = "Header file probably incorrect!"; //$NON-NLS-1$
  static String INCORRECT_FOOTERFILE = "Footer file probably incorrect!"; //$NON-NLS-1$
  static String KEYWORD_SYNTAX_ERROR = " is not a valid key word! Have to begin with "; //$NON-NLS-1$
  static String KEYWORD_FILE_SYNTAX_ERROR = " containing keywords contains syntax errors!"; //$NON-NLS-1$
  static String RELATED_KEYWORD_NOT_FOUND = " reffered by the following keyword does not exist "; //$NON-NLS-1$
  static String PARSE_DIRECTORY = "Parses directory: "; //$NON-NLS-1$
  static String TAKE_A_WHILE = "It may take a while ..."; //$NON-NLS-1$
  static String TITLE_ANCHOR_MISSING = "Title and/or anchor text is missing!"; //$NON-NLS-1$
  static String MISSING_RESPONSE_FILE = "Error: Missing -r response file"; //$NON-NLS-1$
  static String NO_OF_CHARACTERS_READ = " chars read in "; //$NON-NLS-1$
  static String NO_OF_FILES_CHECKED = " files checked in "; //$NON-NLS-1$
  static String SECOND = " s "; //$NON-NLS-1$
  static String WORDS = " words"; //$NON-NLS-1$
  static String COMPILED = "Compiled "; //$NON-NLS-1$
  static String DOCUMENTS_CONATINING = " documents, containing "; //$NON-NLS-1$
  static String BYTES_STORED = " bytes, stored in "; //$NON-NLS-1$
  static String WEB_DATABASE = "Web database of "; //$NON-NLS-1$
  static String CDROM_DATABASE = "CD-ROM database of "; //$NON-NLS-1$
  static String BYTES =  "bytes"; //$NON-NLS-1$
  static String BYTES_AND =  "bytes, and "; //$NON-NLS-1$
  static String FATAL_ERROR = "Fatal Error: "; //$NON-NLS-1$
  static String FILE_DOES_NOT_EXIST = "Error: Couldn't find file: "; //$NON-NLS-1$
  static String MISSING_F_FILE = "Error: Missing -f filename"; //$NON-NLS-1$
  static String CANONICAL_FILE_PATH_FAILED = "Error: couldn't create canonical filepath for "; //$NON-NLS-1$
  static String DOCUMENT_DEPENDENCY_LIST =  "Document dependancy list for "; //$NON-NLS-1$
  static String DATA_DEPENDENCY_LIST =  "Data dependancy list for "; //$NON-NLS-1$
  static String EXTERNAL_LINK_LIST =  "External links list for "; //$NON-NLS-1$
  static String MISSING_LINK_LIST =  "Missing links list for "; //$NON-NLS-1$
  static String NOTHING_TO_DO =  "Error: nothing to do: -nb -nh -nl -nt"; //$NON-NLS-1$
  static String INTERMEDIATE_DATA_FILE =  "Intermediate data: file://"; //$NON-NLS-1$
  static String TOO_MANY_FILES =  "Error: Too many files for the search applet database"; //$NON-NLS-1$
  static String ILLEGAL_NUMERIC_VALUE = "Error: Illegal numeric value -im "; //$NON-NLS-1$

  static void loadHelpMessages(String fileName, String characterset)
  {
       String helpTexts = MiscStatic.loadFile(fileName,characterset);
       StringTokenizer parser = new StringTokenizer(
                       helpTexts, "\n\t"); //$NON-NLS-1$

       while(parser.hasMoreTokens())
       {
          String param = parser.nextToken().trim();
          String value = parser.nextToken().trim();
          HelpMessages.addValue(param, " " + value + " "); //$NON-NLS-1$ //$NON-NLS-2$
       }
  }

  static void addValue(String param, String value)
  {

    if(param.equalsIgnoreCase("INPUT_PARAMETER_NULL")) //$NON-NLS-1$
    {
      HelpMessages.INPUT_PARAMETER_NULL = value;
    }
    else if(param.equalsIgnoreCase("ILLEGAL_NUMERIC_VALUE")) //$NON-NLS-1$
    {
      HelpMessages.ILLEGAL_NUMERIC_VALUE = value;
    }
    else if(param.equalsIgnoreCase("EXCEPTION")) //$NON-NLS-1$
    {
      HelpMessages.EXCEPTION = value;
    }
    else if(param.equalsIgnoreCase("INTERMEDIATE_DATA_FILE")) //$NON-NLS-1$
    {
      HelpMessages.INTERMEDIATE_DATA_FILE = value;
    }
    else if(param.equalsIgnoreCase("TOO_MANY_FILES")) //$NON-NLS-1$
    {
      HelpMessages.TOO_MANY_FILES = value;
    }
    else if(param.equalsIgnoreCase("NO_OF_FILES_CHECKED")) //$NON-NLS-1$
    {
      HelpMessages.NO_OF_FILES_CHECKED = value;
    }
    else if(param.equalsIgnoreCase("NOTHING_TO_DO")) //$NON-NLS-1$
    {
      HelpMessages.NOTHING_TO_DO = value;
    }
    else if(param.equalsIgnoreCase("INTERMEDIATE_DATA_FILE")) //$NON-NLS-1$
    {
      HelpMessages.INTERMEDIATE_DATA_FILE = value;
    }
    else if(param.equalsIgnoreCase("DOCUMENT_DEPENDENCY_LIST")) //$NON-NLS-1$
    {
      HelpMessages.DOCUMENT_DEPENDENCY_LIST = value;
    }
    else if(param.equalsIgnoreCase("DATA_DEPENDENCY_LIST")) //$NON-NLS-1$
    {
      HelpMessages.DATA_DEPENDENCY_LIST = value;
    }
    else if(param.equalsIgnoreCase("EXTERNAL_LINK_LIST")) //$NON-NLS-1$
    {
      HelpMessages.EXTERNAL_LINK_LIST = value;
    }
    else if(param.equalsIgnoreCase("MISSING_LINK_LIST")) //$NON-NLS-1$
    {
      HelpMessages.MISSING_LINK_LIST = value;
    }
    else if(param.equalsIgnoreCase("WORDS")) //$NON-NLS-1$
    {
      HelpMessages.WORDS = value;
    }
    else if(param.equalsIgnoreCase("COMPILED")) //$NON-NLS-1$
    {
      HelpMessages.COMPILED = value;
    }
    else if(param.equalsIgnoreCase("DOCUMENTS_CONATINING")) //$NON-NLS-1$
    {
      HelpMessages.DOCUMENTS_CONATINING = value;
    }
    else if(param.equalsIgnoreCase("BYTES_STORED")) //$NON-NLS-1$
    {
      HelpMessages.BYTES_STORED = value;
    }
    else if(param.equalsIgnoreCase("WEB_DATABASE")) //$NON-NLS-1$
    {
      HelpMessages.WEB_DATABASE = value;
    }
    else if(param.equalsIgnoreCase("CDROM_DATABASE")) //$NON-NLS-1$
    {
      HelpMessages.CDROM_DATABASE = value;
    }
    else if(param.equalsIgnoreCase("BYTES")) //$NON-NLS-1$
    {
      HelpMessages.BYTES = value;
    }
    else if(param.equalsIgnoreCase("BYTES_AND")) //$NON-NLS-1$
    {
      HelpMessages.BYTES_AND = value;
    }
    else if(param.equalsIgnoreCase("MISSING_F_FILE")) //$NON-NLS-1$
    {
      HelpMessages.MISSING_F_FILE = value;
    }
    else if(param.equalsIgnoreCase("CANONICAL_FILE_PATH_FAILED")) //$NON-NLS-1$
    {
      HelpMessages.CANONICAL_FILE_PATH_FAILED = value;
    }
    else if(param.equalsIgnoreCase("NO_OF_CHARACTERS_READ")) //$NON-NLS-1$
    {
      HelpMessages.NO_OF_CHARACTERS_READ = value;
    }
    else if(param.equalsIgnoreCase("FILE_DOES_NOT_EXIST")) //$NON-NLS-1$
    {
      HelpMessages.FILE_DOES_NOT_EXIST = value;
    }
    else if(param.equalsIgnoreCase("FATAL_ERROR")) //$NON-NLS-1$
    {
      HelpMessages.FATAL_ERROR = value;
    }
    else if(param.equalsIgnoreCase("SECOND")) //$NON-NLS-1$
    {
      HelpMessages.SECOND = value;
    }
    else if(param.equalsIgnoreCase("BAD_PARSER")) //$NON-NLS-1$
    {
      HelpMessages.BAD_PARSER = value;
    }
     else if(param.equalsIgnoreCase("MISSING_RESPONSE_FILE")) //$NON-NLS-1$
    {
      HelpMessages.MISSING_RESPONSE_FILE = value;
    }
    else if(param.equalsIgnoreCase("TITLE_ANCHOR_MISSING")) //$NON-NLS-1$
    {
      HelpMessages.TITLE_ANCHOR_MISSING = value;
    }
    else if(param.equalsIgnoreCase("KEYWORD_FILE_SYNTAX_ERROR")) //$NON-NLS-1$
    {
      HelpMessages.KEYWORD_FILE_SYNTAX_ERROR = value;
    }
    else if(param.equalsIgnoreCase("KEYWORD_SYNTAX_ERROR")) //$NON-NLS-1$
    {
      HelpMessages.KEYWORD_SYNTAX_ERROR = value;
    }
    else if(param.equalsIgnoreCase("PARSE_DIRECTORY")) //$NON-NLS-1$
    {
      HelpMessages.PARSE_DIRECTORY = value;
    }
    else if(param.equalsIgnoreCase("TAKE_A_WHILE")) //$NON-NLS-1$
    {
      HelpMessages.TAKE_A_WHILE = value;
    }
    else if(param.equalsIgnoreCase("INCORRECT_HEADERFILE")) //$NON-NLS-1$
    {
      HelpMessages.INCORRECT_HEADERFILE = value;
    }
    else if(param.equalsIgnoreCase("RELATED_KEYWORD_NOT_FOUND")) //$NON-NLS-1$
    {
      HelpMessages.RELATED_KEYWORD_NOT_FOUND = value;
    }
    else if(param.equalsIgnoreCase("INCORRECT_FOOTERFILE")) //$NON-NLS-1$
    {
      HelpMessages.INCORRECT_FOOTERFILE = value;
    }
    else if(param.equalsIgnoreCase("NOT_ENOUGH_INPUT")) //$NON-NLS-1$
    {
      HelpMessages.NOT_ENOUGH_INPUT = value;
    }
    else if(param.equalsIgnoreCase("NO_DIRECTORY")) //$NON-NLS-1$
    {
      HelpMessages.NO_DIRECTORY = value;
    }
    else if(param.equalsIgnoreCase("DIRECTORY_NOT_PARSED")) //$NON-NLS-1$
    {
      HelpMessages.DIRECTORY_NOT_PARSED = value;
    }
    else if(param.equalsIgnoreCase("BAD_OUTPUT_STREAM")) //$NON-NLS-1$
    {
      HelpMessages.BAD_OUTPUT_STREAM = value;
    }
    else if(param.equalsIgnoreCase("WRITE_EXCEPTION")) //$NON-NLS-1$
    {
      HelpMessages.WRITE_EXCEPTION = value;
    }
    else if(param.equalsIgnoreCase("OPEN_FILE_FAILED")) //$NON-NLS-1$
    {
      HelpMessages.OPEN_FILE_FAILED = value;
    }
    else if(param.equalsIgnoreCase("WRITE_ERROR")) //$NON-NLS-1$
    {
      HelpMessages.WRITE_ERROR = value;
    }
    else if(param.equalsIgnoreCase("WRITE_FILE")) //$NON-NLS-1$
    {
      HelpMessages.WRITE_FILE = value;
    }
    else if(param.equalsIgnoreCase("MISSING_LABEL")) //$NON-NLS-1$
    {
      HelpMessages.MISSING_LABEL  = value;
    }
     else if(param.equalsIgnoreCase("MISSING_URL")) //$NON-NLS-1$
    {
      HelpMessages.MISSING_URL  = value;
    }
    else if(param.equalsIgnoreCase("MISSING_LABEL")) //$NON-NLS-1$
    {
      HelpMessages.MISSING_LABEL  = value;
    }
    else if(param.equalsIgnoreCase("MISSING_NODE")) //$NON-NLS-1$
    {
      HelpMessages.MISSING_NODE  = value;
    }
    else if(param.equalsIgnoreCase("PARAMETER_MISSING")) //$NON-NLS-1$
    {
      HelpMessages.PARAMETER_MISSING  = value;
    }
     else if(param.equalsIgnoreCase("PARAMETER_MISSING_VALUE")) //$NON-NLS-1$
    {
      HelpMessages.PARAMETER_MISSING_VALUE  = value;
    }
    else if(param.equalsIgnoreCase("FONT_AND_STYLE_MISSING")) //$NON-NLS-1$
    {
      HelpMessages.FONT_AND_STYLE_MISSING  = value;
    }
     else if(param.equalsIgnoreCase("PARAMETER_NOT_NUMBER")) //$NON-NLS-1$
    {
      HelpMessages.PARAMETER_NOT_NUMBER  = value;
    }
    else if(param.equalsIgnoreCase("INVALID_PARAMETER_NAME")) //$NON-NLS-1$
    {
      HelpMessages.INVALID_PARAMETER_NAME  = value;
    }
    else
    {
      System.out.println("Unknown parameter " + param); //$NON-NLS-1$
    }
  }
}




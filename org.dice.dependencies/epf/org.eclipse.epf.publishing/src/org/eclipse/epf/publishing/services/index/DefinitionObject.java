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

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Hashtable;
import java.util.Vector;

import com.ibm.icu.util.StringTokenizer;

//Object holding definitionfile information
public class DefinitionObject
{
  private String indextitle = "Index - Basic Unified Process"; //$NON-NLS-1$
  private String see = ", See "; //$NON-NLS-1$
  private String seealso  = ", See also "; //$NON-NLS-1$
  private String characterset = "utf-8"; //$NON-NLS-1$
  String keyWordSeparator = "|"; //$NON-NLS-1$
  String levelSeparatorReplace = ":"; //$NON-NLS-1$
  String wwwRoot = null;
  int stipLength=0;
  String relativepath = null;
  String target=null;
  String specKeyWordFile=null;
  String mainResultFile=null;
  String indexResultFile=null;
  String indexTarget=null;
  String indexHeight=null;
  String keywordTarget=null;
  String keywordResultFile=null;
  String headerFile=null;
  String footerFile=null;
  String keyWordPrefix=Def.DefaultKeyWordPrefix;
  String keyWordLevelSeparator=Def.DefaultKeyWordLevelSeparator;

  boolean multidocumentkeyword=false;
  boolean showdocumenttitle=false;
  Vector stopDirsV=null;
   Hashtable fontDefinitions=null;
  Hashtable styleDefinitions=null;

  private File defFileRoot;

  //Constructor that parses the definitionfile
  DefinitionObject(String pubDir, String deffile, String charSet)
  {
	  setwwwRoot(pubDir);
	  defFileRoot = new File(deffile).getParentFile();

     // Read the definition file and split the deffile into it's individual lines.
	String infile = MiscStatic.loadFile(deffile,charSet);
    StringTokenizer parser = new StringTokenizer(infile, "\t\n"); //$NON-NLS-1$

     stopDirsV = new Vector();
     fontDefinitions = new Hashtable(10);
     styleDefinitions = new Hashtable(10);
     while(parser.hasMoreTokens())
     {
        String param = parser.nextToken().trim();

        if(param.equalsIgnoreCase(Def.LevelFont))
        {
           asignFont(parser);
        }
        else if(param.equalsIgnoreCase(Def.LevelStyle))
        {
           asignStyle(parser);
        }      
        else
        {
           String value = parser.nextToken().trim();
           if(value==null)
           {
             System.err.println("DefinitionObject:addValue\n" + //$NON-NLS-1$
                      HelpMessages.PARAMETER_MISSING_VALUE + param);
             return;
           }
           asignValue(param, value);
        }
     }

  }

  //Check if enough input is read
  boolean enoughInput()
  {
     boolean enough=true;
     StringBuffer buff = new StringBuffer();
     buff.append("\n"); //$NON-NLS-1$
     if(wwwRoot==null)
     {
        buff.append(HelpMessages.PARAMETER_MISSING + Def.WWWRootParam);
        enough=false;
     }

     if(keywordResultFile==null)
     {
        buff.append(HelpMessages.PARAMETER_MISSING + Def.KeyWordResultFile);
        enough=false;
     }

     if(indexResultFile!=null && mainResultFile==null)
     {
        buff.append(HelpMessages.PARAMETER_MISSING + Def.MainResultFile);
        enough=false;
     }

     if(indexResultFile!=null && indexTarget==null)
     {
        buff.append(HelpMessages.PARAMETER_MISSING + Def.IndexTarget);
        enough=false;
     }

     if(indexResultFile!=null && keywordTarget==null)
     {
        buff.append(HelpMessages.PARAMETER_MISSING + Def.KeyWordTarget);
        enough=false;
     }

     if(indexResultFile!=null && indexHeight==null)
     {
        buff.append(HelpMessages.PARAMETER_MISSING + Def.IndexHeight);
        enough=false;
     }

     if(relativepath==null)
     {
        buff.append(HelpMessages.PARAMETER_MISSING + Def.RelPath);
        enough=false;
     }

     if(headerFile==null)
     {
        buff.append(HelpMessages.PARAMETER_MISSING + Def.HeaderFile);
        enough=false;
     }

     if(footerFile==null)
     {
        buff.append(HelpMessages.PARAMETER_MISSING + Def.FooterFile);
        enough=false;
     }

     FontObject f = (FontObject)fontDefinitions.get(Def.DefaultFont);
     FontObject fs = (FontObject)styleDefinitions.get(Def.DefaultStyle);
     if(f==null && fs==null)
     {
        buff.append(HelpMessages.FONT_AND_STYLE_MISSING + Def.DefaultFont + " " + Def.DefaultStyle); //$NON-NLS-1$
        enough=false;
     }
     if(!enough)
     {
        buff.append("\n"); //$NON-NLS-1$
        System.err.println(buff);
     }
     return enough;
  }


  //Asigns values to the different attributes
  private void asignValue(String param, String value)
  {

    if(param.equalsIgnoreCase(Def.MainResultFile))
    {
       mainResultFile = value;
    }
    else if(param.equalsIgnoreCase(Def.CharacterSet))
    {
       characterset = value;
    }
    else if(param.equalsIgnoreCase(Def.See))
    {
       see = value;
    }
    else if(param.equalsIgnoreCase(Def.SeeAlso))
    {
       seealso = value;
    }
    else if(param.equalsIgnoreCase(Def.IndexResultFile))
    {
       indexResultFile = value;
    }
    else if(param.equalsIgnoreCase(Def.KeyWordResultFile))
    {
       keywordResultFile = value;
    }
    else if(param.equalsIgnoreCase(Def.HeaderFile))
    {
       headerFile = new File(defFileRoot, value).getAbsolutePath();;
    }
    else if(param.equalsIgnoreCase(Def.FooterFile))
    {
       footerFile = new File(defFileRoot, value).getAbsolutePath();;
    }
    else if(param.equalsIgnoreCase(Def.RelPath))
    {
       relativepath = value;
    }
    else if(param.equalsIgnoreCase(Def.DefaultTargetParam))
    {
       target = value;
    }
    else if(param.equalsIgnoreCase(Def.KeyWordFile))
    {
       specKeyWordFile = new File(defFileRoot, value).getAbsolutePath();
    }
    else if(param.equalsIgnoreCase(Def.IndexTitle))
    {
       indextitle = value;
    }
    else if(param.equalsIgnoreCase(Def.KeyWordTarget))
    {
       keywordTarget = value;
    }
    else if(param.equalsIgnoreCase(Def.IndexTarget))
    {
       indexTarget = value;
    }
    else if(param.equalsIgnoreCase(Def.IndexHeight))
    {
       indexHeight = value;
    }
    else if(param.equalsIgnoreCase(Def.MultiDocKeyWord))
    {
       multidocumentkeyword = value.equalsIgnoreCase(Def.True);
    }
    else if(param.equalsIgnoreCase(Def.ShowDocTitle))
    {
       showdocumenttitle = value.equalsIgnoreCase(Def.True);
    }
    else if(param.equalsIgnoreCase(Def.LeaveDir))
    {
       stopDirsV.addElement(value);
    }
      else if(param.equalsIgnoreCase(Def.KeyWordPrefix))
    {
       keyWordPrefix = value;
    }
    else if(param.equalsIgnoreCase(Def.KeyWordLevelSeparator))
    {
       keyWordLevelSeparator = value;
    }
    else
    {

       System.err.println(HelpMessages.INVALID_PARAMETER_NAME + param);
    }

  }

  //Parses a fontdefinition
  private void asignFont(StringTokenizer parser)
  {
    String stringLevel = parser.nextToken().trim();
    Object key = stringLevel;
    if(!stringLevel.equalsIgnoreCase(Def.DefaultFont) && !stringLevel.equalsIgnoreCase(Def.HeadLineFont))
    {
       key =  new Integer(stringLevel);
    }
    String name = parser.nextToken().trim();
    String style = parser.nextToken().trim();
    String size = parser.nextToken().trim();

    fontDefinitions.put(key,new FontObject(name, style, size));
  }

  //Parses a styledefinition
  private void asignStyle(StringTokenizer parser)
  {
    String stringLevel = parser.nextToken().trim();
    Object key = stringLevel;
    if(!stringLevel.equalsIgnoreCase(Def.DefaultStyle) && !stringLevel.equalsIgnoreCase(Def.HeadLineStyle))
    {
       key =  new Integer(stringLevel);
    }

    String style = parser.nextToken().trim();
    styleDefinitions.put(key,new FontObject(style));
  }
  
  //Check if the reached directory is whithin the stopdirlist
    boolean isInStopdir(String str)
   {
      for(int i=0;i<stopDirsV.size();i++)
      {
         String tmpStr = (String) stopDirsV.elementAt(i);
         if(str.substring(KeyWordIndexHelper.defObj.getWwwRoot().length()+1).compareTo(tmpStr)==0)
         {
            return true;
         }
      }
      return false;
  }

  //Accessmethods
  String getIndexTitle()
  {
    return indextitle;
  }

  String getSee()
  {
    return see;
  }

  String getSeeAlso()
  {
    return seealso;
  }

  String getCharacterSet()
  {
    return characterset;
  }
  boolean getMulitDocumentKeyword()
  {
    return multidocumentkeyword;
  }

  boolean getShowDocumentTitle()
  {
    return showdocumenttitle;
  }

  String getKeyWordTarget()
  {
    return keywordTarget;
  }

  String getIndexTarget()
  {
    return indexTarget;
  }

  String getIndexHeight()
  {
    return indexHeight;
  }

  String getWwwRoot()
  {
    return wwwRoot;
  }

  int getWwwRootLength()
  {
    return stipLength;
  }

  String getTarget()
  {
    return target;
  }

  String getKeyWordFile()
  {
    return specKeyWordFile;
  }

  String getMainResultFile()
  {
    return mainResultFile;
  }

  String getIndexResultFile()
  {
    return indexResultFile;
  }

  String getKeywordResultFile()
  {
    return keywordResultFile;
  }


  String getRelativePath()
  {
    return relativepath;
  }

  String getHeaderFile()
  {
    return headerFile;
  }

   String getFooterFile()
  {
    return footerFile;
  }

 String getKeyWordPrefix()
 {
   return keyWordPrefix;
 }

 String getKeyWordLevelSeparator()
 {
   return keyWordLevelSeparator;
 }

 FontObject getFont(int level)
  {
     FontObject f = (FontObject)fontDefinitions.get(new Integer(level));
     if(f==null)
     {
        f = (FontObject)fontDefinitions.get(Def.DefaultFont);
     }
     return f;
  }

  FontObject getFont(Object key)
  {
     return (FontObject)fontDefinitions.get(key);

  }

  FontObject getStyleSheet(int level)
  {
     FontObject f = (FontObject)styleDefinitions.get(new Integer(level));
     if(f==null)
     {
        f = (FontObject)styleDefinitions.get(Def.DefaultStyle);
     }
     return f;
  }

  FontObject getStyleSheet(Object key)
  {
    return (FontObject)styleDefinitions.get(key);
  }

  void printStart(OutputStreamWriter outP,int level)
  {
    if(getStyleSheet(Def.DefaultStyle)==null)
    {
      FontObject f = getFont(level);
      if(f==null)
      {
        f = getFont(Def.DefaultFont);
      }
      printStart(outP, f);
    }
    else
    {
        FontObject f = getStyleSheet(level);
        if(f==null)
        {
           f = getStyleSheet(Def.DefaultStyle);
        }
        printStart(outP, f);
    }
  }
  void printStart(OutputStreamWriter outP,FontObject f)
  {

    if(outP==null)
    {
        System.out.println("DefinitionObject:printStart\n" + HelpMessages.BAD_OUTPUT_STREAM); //$NON-NLS-1$
        return;
    }


    if(f!=null && f.getStyleSheet()==null)
    {
      MiscStatic.print(outP,"<FONT FACE=\"" + f.getName() + "\" SIZE=\"" + f.getSize() + "\" >\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
      if(f.isItalic())
      {
        MiscStatic.print(outP,"<I>"); //$NON-NLS-1$
      }

      if(f.isBold())
      {
        MiscStatic.print(outP,"<B>"); //$NON-NLS-1$
      }
    }
    else
    {
        MiscStatic.print(outP, "<P CLASS=\"" + f.getStyleSheet() + "\">\n"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  void printEnd(OutputStreamWriter outP, int level)
  {

    if(outP==null)
    {
        System.out.println("DefinitionObject:printEnd\n" + HelpMessages.BAD_OUTPUT_STREAM); //$NON-NLS-1$
        return;
    }

    if(getStyleSheet(Def.DefaultStyle)==null)
    {
      FontObject f = getFont(level);
      if(f==null)
      {
        f = getFont(Def.DefaultFont);
      }
      printEnd(outP, f);
    }
    else
    {
        printEnd(outP, null);
    }
  }

  void printEnd(OutputStreamWriter outP, FontObject f)
  {
        if(outP==null)
    {
        System.out.println("DefinitionObject:printEnd\n" + HelpMessages.BAD_OUTPUT_STREAM); //$NON-NLS-1$
        return;
    }

    if(f!=null && f.getStyleSheet()==null)
    {
      if(f.isBold())
      {
        MiscStatic.print(outP,"</B>"); //$NON-NLS-1$
      }

      if(f.isItalic())
      {
        MiscStatic.print(outP,"</I>"); //$NON-NLS-1$
      }
      MiscStatic.print(outP,"</FONT>\n"); //$NON-NLS-1$
    }
    else
    {
        MiscStatic.print(outP,"</P>\n"); //$NON-NLS-1$
    }
  }
  //---------------------------------------------------------------------------------------------
  //
  //	RPW 2.0 Integration -- Do not modify this code unless you know what you are doing.
  //	  //	Because RPW calls these applications from a different location than the batch file
  //	it is neccessary to override the defaults in the config file.
  //
  //	The following methods simply allow the values to be over-ridden.
  //
  //----------------------------------------------------------------------------------------------
  public void setwwwRoot(String newRoot)
  {
      try {
		File wwwDir = new File(newRoot);
		wwwRoot = wwwDir.getCanonicalPath() + "/"; //$NON-NLS-1$
		wwwRoot = wwwRoot.replace('\\' , '/');
		stipLength = wwwRoot.length()-1;
	} catch (IOException e) {
		e.printStackTrace();
	}
  }

  void setRelativePath(String newPath)
  {
	relativepath = newPath;
  }
  
  /**
   * set the index title
   * 
   * @param title String
   */
  public void setIndexTitle(String title)
  {
	  indextitle = title;
  }  
}
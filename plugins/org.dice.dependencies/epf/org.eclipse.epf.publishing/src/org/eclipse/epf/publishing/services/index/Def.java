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


public class Def
{

    // Argument parameter
    static String ArgumentPrefix = "-"; //$NON-NLS-1$
    static String PrintTempFileArg = Def.ArgumentPrefix + "tf"; //$NON-NLS-1$
    static String ResponseFileArg = Def.ArgumentPrefix + "r"; //$NON-NLS-1$
    static String CharacterSetArg = Def.ArgumentPrefix + "c"; //$NON-NLS-1$
    static String LanguageFileArg = Def.ArgumentPrefix + "l"; //$NON-NLS-1$
    static String TrimArg = Def.ArgumentPrefix + "t"; //$NON-NLS-1$
    static String TopDirArg = Def.ArgumentPrefix + "d"; //$NON-NLS-1$
    static String ResultFileArg = Def.ArgumentPrefix + "rf"; //$NON-NLS-1$
    static String ShowUsageArg = Def.ArgumentPrefix + "h"; //$NON-NLS-1$
    static String FileArg = Def.ArgumentPrefix + "f"; //$NON-NLS-1$
    static String PrintDebugArg = Def.ArgumentPrefix + "debug"; //$NON-NLS-1$
    //KeywordindexerParam
    static String IndexTitle = "maintitle"; //$NON-NLS-1$
    static String SeeAlso = "seealso"; //$NON-NLS-1$
    static String See = "see"; //$NON-NLS-1$
    static String Delimeters = "delimeters"; //$NON-NLS-1$
    static String CharacterSet = "characterset"; //$NON-NLS-1$
    static String KeyWordPrefix = "prefix"; //$NON-NLS-1$
    static String KeyWordLevelSeparator = "levelseparator"; //$NON-NLS-1$
    static String DefaultKeyWordPrefix = "XE_"; //$NON-NLS-1$
    static String DefaultKeyWordLevelSeparator = "__"; //$NON-NLS-1$
    static String LeaveDir = "leaveDir"; //$NON-NLS-1$
    static String StartDir = "startDir"; //$NON-NLS-1$
    static String KeyWordFile = "keywordfile"; //$NON-NLS-1$
    static String MainResultFile = "mainresultfile"; //$NON-NLS-1$
    static String IndexResultFile = "indexresultfile"; //$NON-NLS-1$
    static String IndexHeight = "indexheight"; //$NON-NLS-1$
    static String KeyWordResultFile = "keywordresultfile"; //$NON-NLS-1$
    static String KeyWordTarget = "keywordtarget"; //$NON-NLS-1$
    static String IndexTarget = "indextarget"; //$NON-NLS-1$
    static String MultiDocKeyWord = "mulitdocumentkeyword"; //$NON-NLS-1$
    static String ShowDocTitle = "showdocumenttitle"; //$NON-NLS-1$

    static String HeaderFile = "headerfile"; //$NON-NLS-1$
    static String FooterFile = "footerfile"; //$NON-NLS-1$

    //Sitmap parameter
    static String TruncateLabel = "truncatelabel"; //$NON-NLS-1$
    static String ConvertAmpersand = "convertampersand"; //$NON-NLS-1$
    static String TableWidth = "tablewidth"; //$NON-NLS-1$
    static int DefaultRowSpan = 1;
    static int DefaultColSpan = 1;
    static String Mode = "mode"; //$NON-NLS-1$
    static String NodeStart = "<--"; //$NON-NLS-1$
    static String NodeEnd = "-->"; //$NON-NLS-1$
    static String NodeText = "label"; //$NON-NLS-1$
    static String NodeLevel = "level"; //$NON-NLS-1$
    static String ColumnWidth = "colwidth"; //$NON-NLS-1$
    static String Column = "col"; //$NON-NLS-1$
    static String Row = "row"; //$NON-NLS-1$
    static String ColSpan = "colspan"; //$NON-NLS-1$
    static String RowSpan = "rowspan"; //$NON-NLS-1$
    static String CellColor = "cellcolor"; //$NON-NLS-1$
    static String Links = "links"; //$NON-NLS-1$
    static String VisitOnce = "visitonce"; //$NON-NLS-1$
    static String OnlyThis = "onlythis"; //$NON-NLS-1$
    static String TitleLength = "titlelength"; //$NON-NLS-1$
    static String DefaultTitleLength = "defaulttitlelength"; //$NON-NLS-1$

    static String RemoveDatFile = "removedatfile"; //$NON-NLS-1$
    static String SynonymFile = "synonymfile"; //$NON-NLS-1$
    static String SubTree = "includesubtree"; //$NON-NLS-1$
 //   static String DatTop = "datatop";
    static String ResFile = "resultfile"; //$NON-NLS-1$
    static String RelPath = "relativepath"; //$NON-NLS-1$
    static String MaxLevels = "maxlevels"; //$NON-NLS-1$
    static String LevelStyle = "levelstyle"; //$NON-NLS-1$
    static String DefaultStyle = "defaultstyle"; //$NON-NLS-1$
    static String HeadLineStyle = "headlinestyle"; //$NON-NLS-1$
    static String LevelFont = "levelfont"; //$NON-NLS-1$
    static String DefaultFont = "defaultfont"; //$NON-NLS-1$
    static String HeadLineFont = "headlinefont"; //$NON-NLS-1$
    static String Bold = "bold"; //$NON-NLS-1$
    static String Italic = "italic"; //$NON-NLS-1$
    static String Plain = "plain"; //$NON-NLS-1$

    static String MaxOpenNodesParam = "maxvisiblenodes"; //$NON-NLS-1$
    static int DefaultMaxOpenNodes = 100;
    static String GarbageCollectorParam = "gc"; //$NON-NLS-1$
    static String DefaultGarbageCollector = "10"; //$NON-NLS-1$
    static String VerticalIncrementParam = "verticalincrement"; //$NON-NLS-1$
    static String HorizontalIncrementParam = "horizontalincrement"; //$NON-NLS-1$
    static String Mailto = "mailto:"; //$NON-NLS-1$
    static String DebugParam = "debug"; //$NON-NLS-1$
    static String WWWRootParam = "wwwroot"; //$NON-NLS-1$
    static String ImageDir = "images"; //$NON-NLS-1$
    static String ImageIconParam = "frameimage"; //$NON-NLS-1$
    static String BgImageParam = "bgimage"; //$NON-NLS-1$
    static String VisitOnceMarkAllParam ="visitoncemarkall"; //$NON-NLS-1$
    static String ShadowParam ="mouseovershadow"; //$NON-NLS-1$

    static String LoadingTreeParam = "loadinglabel"; //$NON-NLS-1$
    static String LoadingTreeBrowser = "TreeBrowser is loading::"; //$NON-NLS-1$
    static String LoadingTree = "Loading tree. Please wait..."; //$NON-NLS-1$
    static String LoadingSubTree = "Loading sub tree. Please wait..."; //$NON-NLS-1$


    static String CharactersetParam = "characterset"; //$NON-NLS-1$
    static String DelimParam = "delimeters"; //$NON-NLS-1$
    static String LOAD_PAGE = "loadpage"; //$NON-NLS-1$
    static String FINISH_PAGE = "finishpage"; //$NON-NLS-1$
    static String DataFileParam = "datafile"; //$NON-NLS-1$
    static String DataFile = "tree.dat"; //$NON-NLS-1$
    static String None = ""; //$NON-NLS-1$
    static String Http = "http://"; //$NON-NLS-1$
    static String Https = "https://"; //$NON-NLS-1$
    static String Ftp = "ftp://"; //$NON-NLS-1$
    static String File = "file:/"; //$NON-NLS-1$
    static String Slash = "/"; //$NON-NLS-1$
    static String Space = " "; //$NON-NLS-1$
    static String ZipFile = ".zip"; //$NON-NLS-1$
    //Color parameters
    static String BgColorParam = "bgcolor"; //$NON-NLS-1$
    static String BgColor = "255,255,255"; //$NON-NLS-1$
    static String FgColorParam = "fgcolor"; //$NON-NLS-1$
    static String FgColor = "0,0,0"; //$NON-NLS-1$
    static String SelBgColorParam = "selbgcolor"; //$NON-NLS-1$
    static String SelBgColor = "0,0,255"; //$NON-NLS-1$
    static String SelFgColorParam = "selfgcolor"; //$NON-NLS-1$
    static String SelFgColor = "255,255,255"; //$NON-NLS-1$
    static String VisitColorParam = "visitcolor"; //$NON-NLS-1$
    static String VisitColor = "255,51,51"; //$NON-NLS-1$
    static String MouseOverColorParam = "mouseovercolor"; //$NON-NLS-1$
    static String MouseOverColor = "blue"; //$NON-NLS-1$


    //Frame parameters
    static String LabelPosParam = "labelpos"; //$NON-NLS-1$
    static String LabelPos = "below"; //$NON-NLS-1$
    static String FrameTitleParam = "frametitle"; //$NON-NLS-1$
    static String FrameTitle = "Tree Browser"; //$NON-NLS-1$
    static String FrameWidthParam = "framewidth"; //$NON-NLS-1$
    static String FrameHeightParam = "frameheight"; //$NON-NLS-1$
    static String FrameWidth = "250"; //$NON-NLS-1$
    static String FrameHeight = "400"; //$NON-NLS-1$
    static String FrameParam = "frame"; //$NON-NLS-1$
    static String DefaultFrame = "false"; //$NON-NLS-1$
    static String False = "false"; //$NON-NLS-1$
    static String True = "true"; //$NON-NLS-1$

    static String LabelParam = "framelabel"; //$NON-NLS-1$
    static String DefaultLabel = "Tree Browser"; //$NON-NLS-1$
    static String LabelBgColorParam= "framelabelbgcolor"; //$NON-NLS-1$
    static String DefaultLabelBgColor= "255,255,255"; //$NON-NLS-1$
    static String BorderParam = "border"; //$NON-NLS-1$
    static String DefaultBorder = "false"; //$NON-NLS-1$

    //Tree constants
    static String CellSizeParam = "cellsize"; //$NON-NLS-1$
    static String DefaultCellSize = "16"; //$NON-NLS-1$
    static int ImageInset = 3;
    static int TextInset = 6;
    static int TextBaseLine = 3;
    static int DoubleClickResolution = 333;
    static int ScrollWidth = 16;
    static String ScrollBarLeftParam = "scrollbartoleft"; //$NON-NLS-1$


    static String DefaultTargetParam = "target"; //$NON-NLS-1$
    static String DefaultTarget = "_top"; //$NON-NLS-1$
    static String TreeFontParam = "treefont"; //$NON-NLS-1$
    static String DefaultTreeFont = "Arial,PLAIN,12"; //$NON-NLS-1$
    static String LabelFontParam = "framelabelfont"; //$NON-NLS-1$
    static String DefaultLabelFont = "Arial,BOLD,12"; //$NON-NLS-1$
}
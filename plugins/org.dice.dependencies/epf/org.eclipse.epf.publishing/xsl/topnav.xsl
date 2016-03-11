<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Copyright (c) 2005, 2006 IBM Corporation and others.
    All rights reserved. This program and the accompanying materials
    are made available under the terms of the Eclipse Public License v1.0
    which accompanies this distribution, and is available at
    http://www.eclipse.org/legal/epl-v10.html
    Contributors:
    IBM Corporation - initial implementation
-->

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format">

<xsl:output method="html" version="1.0" encoding="UTF-8" 
	doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" 
	doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" 
	indent="yes"/>
	
<xsl:param name="glossaryText"/>
<xsl:param name="indexText"/>
<xsl:param name="feedbackText"/>
<xsl:param name="aboutText"/>
<xsl:param name="searchText"/>
<xsl:param name="printText"/>


<xsl:template match="/PublishingOption">

<xsl:variable name="title"><xsl:value-of select="@title"/></xsl:variable>
<xsl:variable name="bannerImage"><xsl:value-of select="@bannerImage"/></xsl:variable>
<xsl:variable name="bannerImageHeight"><xsl:value-of select="@bannerImageHeight"/></xsl:variable>
<xsl:variable name="allowAppletSearch"><xsl:value-of select="@allowAppletSearch"/></xsl:variable>
<xsl:variable name="showGlossary"><xsl:value-of select="@showGlossary"/></xsl:variable>
<xsl:variable name="showIndex"><xsl:value-of select="@showIndex"/></xsl:variable>
<xsl:variable name="feedbackUrl"><xsl:value-of select="@feedbackUrl"/></xsl:variable>
<xsl:variable name="serverSideSearch"><xsl:value-of select="@serverSideSearch"/></xsl:variable>

<html>
			<xsl:attribute name="lang"><xsl:value-of select="@lang"/></xsl:attribute>
			<xsl:attribute name="xml:lang"><xsl:value-of select="@lang"/></xsl:attribute>
<head>
<title><xsl:value-of select="$title"/></title>

<script language="javascript" src="scripts/common.js" type="text/javascript"></script>
<script language="JavaScript" src="scripts/scripts.js" type="text/javascript"></script>
<script language="JavaScript" src="scripts/topnav.js" type="text/javascript"></script>
<script type="text/javascript" language="JavaScript">
var feedback_url= '<xsl:value-of select="$feedbackUrl"/>';
</script>
<style type="text/css">

.banner {
	background-color: #999999;
	background-image: url('images/<xsl:value-of select="$bannerImage"/>');
	background-repeat: no-repeat;
	width: 100%;
	height: <xsl:value-of select="$bannerImageHeight"/>px;
}
.topbardivider { 
	 background-color: #d0d0d0 
 }
</style>

</head>

<body marginheight="0" marginwidth="0">

 <form action="#" name="formTopframe">
  <table class="banner" border="0" cellspacing="0" cellpadding="0" >
    <tr>
	  <td>
       <table border="0" cellspacing="0" cellpadding="0" align="right" summary="">
          <tr>
			  <xsl:if test="$showGlossary='true' ">
   			<td><a href="JavaScript:openGlossary();" class="toprightnav"><img src="./images/ni_glossary.gif" width="18" height="10" border="0" alt="{$glossaryText}" title="{$glossaryText}" /></a></td>
			<td nowrap="nowrap"><a href="JavaScript:openGlossary();" class="toprightnav"><xsl:value-of select="$glossaryText"/></a></td>
            <td><img src="./images/shim.gif" width="5" height="11" alt="" title="" /></td>
            <td class="topbardivider"><img src="./images/shim.gif" width="1" height="11" alt="" title="" /></td>
            <td><img src="./images/shim.gif" width="5" height="11" alt="" title="" /></td>                  
			</xsl:if>
			<xsl:if test="$showIndex='true' ">
  			<td><a href="javascript:openIndex();" class="toprightnav">
			<img src="./images/ni_index.gif" width="17" height="9" border="0" alt="{$indexText}" title="{$indexText}" /></a></td>
            <td nowrap="nowrap"><a href="javascript:openIndex();" class="toprightnav"><xsl:value-of select="$indexText"/></a></td>
            <td><img src="./images/shim.gif" width="5" height="11" alt="" title=""/></td>
            <td class="topbardivider"><img src="./images/shim.gif" width="1" height="11" alt="" title=""/></td>
            <td><img src="./images/shim.gif" width="5" height="11" alt="" title=""/></td>
          </xsl:if>

			<td><a href="JavaScript:sendFeedback(feedback_url);" class="toprightnav">
			<img src="./images/ni_feedback.gif" width="18" height="10" border="0" alt="{$feedbackText}" title="{$feedbackText}"/></a></td>
            <td nowrap="nowrap">
				<a href="JavaScript:sendFeedback(feedback_url);" class="toprightnav"><xsl:value-of select="$feedbackText"/></a></td>
            
            <td><img src="./images/shim.gif" width="5" height="11" alt="" title=""/></td>
            <td class="topbardivider"><img src="./images/shim.gif" width="1" height="11" alt="" title=""/></td>
            <td><img src="./images/shim.gif" width="5" height="11" alt="" title=""/></td>
			<td><a href="javascript:void(null)" class="toprightnav" onclick="javascript:window.open('about.htm','about','height=250,width=250,status=no,resizable=yes');">
              <img src="./images/ni_about.gif" width="10" height="10" border="0" alt="{$aboutText}" title="{$aboutText}"/></a></td>
            <td nowrap="nowrap"><a href="javascript:void(null)" class="toprightnav" onclick="javascript:window.open('about.htm','about','height=250,width=250,status=no,resizable=yes');">&#160;<xsl:value-of select="$aboutText"/></a></td>
            <td><img src="./images/shim.gif" width="5" height="11" alt="" title=""/></td>
          </tr>
        </table> 
      </td>
    </tr>
  </table>
  
  <!-- toolbar goes here -->
<table border="0" cellspacing="0" cellpadding="0" width="100%">
    <tr>
      <td class="topframebg" >
	  <table border="0" cellspacing="0" cellpadding="0" align="right"  summary="">
          <tr>
            <td><img src="./images/shim.gif" width="5" height="20" alt="image" title="image"/></td>
			<td>
            <font size="1">
			</font>
            </td>
 <xsl:if test="$allowAppletSearch='true' ">
			<td>&#160;</td>
            <td nowrap="nowrap" class="headermedlist">&#160;</td>
			<td><img src="./images/shim.gif" width="5" height="20" alt="image" title="image"/></td>
            <td nowrap="nowrap" class="headermedlist">&#160;</td>
			<td><img src="./images/shim.gif" width="10" height="8" alt="" title=""/></td>
            <td class="topframeshadow"><img src="./images/shim.gif" width="1" height="1" alt="" title=""/></td>
            <td class="topframehighlight2"><img src="./images/shim.gif" width="1" height="1" alt="" title=""/></td>
            <td><img src="./images/shim.gif" width="10" height="1" alt="" title=""/></td>
		    <td><a href="JavaScript:openSearch();" class="toprightnav"><img src="./images/ni_search.gif" width="17" height="17" border="0" alt="{$searchText}" title="{$searchText}"/></a></td>
            <td nowrap="nowrap"><a href="JavaScript:openSearch();"  class="toprightnavgrey"><xsl:value-of select="$searchText"/></a></td>
            <td><img src="./images/shim.gif" width="5" height="11" alt="" title=""/></td>
            <td><img src="./images/shim.gif" width="10" height="1" alt="" title=""/></td>
   </xsl:if>
    <xsl:if test="$serverSideSearch='true' ">
			<td>&#160;</td>
            <td nowrap="nowrap" class="headermedlist">&#160;</td>
			<td><img src="./images/shim.gif" width="5" height="20" alt="image" title="image"/></td>
            <td nowrap="nowrap" class="headermedlist">&#160;</td>
			<td><img src="./images/shim.gif" width="10" height="8" alt="" title=""/></td>
            <td class="topframeshadow"><img src="./images/shim.gif" width="1" height="1" alt="" title=""/></td>
            <td class="topframehighlight2"><img src="./images/shim.gif" width="1" height="1" alt="" title=""/></td>
            <td><img src="./images/shim.gif" width="10" height="1" alt="" title=""/></td>
		    <td><a href="JavaScript:openProcessSearch();" class="toprightnav"><img src="./images/ni_search.gif" width="17" height="17" border="0" alt="{$searchText}" title="{$searchText}"/></a></td>
            <td nowrap="nowrap"><a href="JavaScript:openProcessSearch();"  class="toprightnavgrey"><xsl:value-of select="$searchText"/></a></td>
            <td><img src="./images/shim.gif" width="5" height="11" alt="" title=""/></td>
            <td><img src="./images/shim.gif" width="10" height="1" alt="" title=""/></td>
   </xsl:if>
            <td class="topframeshadow"><img src="./images/shim.gif" width="1" height="1" alt="" title=""/></td>
            <td class="topframehighlight2"><img src="./images/shim.gif" width="1" height="1" alt="" title=""/></td>
            <td><img src="./images/shim.gif" width="10" height="1" alt="" title=""/></td>
            <td><a href="JavaScript:printPage();" class="toprightnav"><img src="./images/ni_print.gif" width="16" height="17" border="0" alt="{$printText}" title="{$printText}"/></a></td>
            <td nowrap="nowrap"><a href="JavaScript:printPage();"  class="toprightnavgrey"><xsl:value-of select="$printText"/></a></td>
            <td><img src="./images/shim.gif" width="10" height="1" alt="" title=""/></td>
          </tr>
        </table>
	  </td>
    </tr>
    <tr>
      <td class="topframeshadow" colspan="3"><img src="./images/shim.gif" width="1" height="1" alt="" title=""/></td>
    </tr>
    <tr>
      <td class="bgblack" colspan="3"><img src="./images/shim.gif" width="1" height="1" alt="" title=""/></td>
    </tr>
  </table>
 </form>

</body>

</html>


</xsl:template>
</xsl:stylesheet>

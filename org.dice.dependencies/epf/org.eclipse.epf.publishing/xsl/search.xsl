<?xml version="1.0" encoding="UTF-8"?>
<!-- 
    Copyright (c) 2005, 2007 IBM Corporation and others.
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
	
<xsl:param name="searchTitleText"/>
<xsl:param name="label1Text"/>
<xsl:param name="label2Text"/>
<xsl:param name="label3Text"/>
<xsl:param name="lable4Text"/>
<xsl:param name="searchButtonText"/>

<xsl:template match="/Search">
	<xsl:variable name="webAppName"><xsl:value-of select="@webAppName"/></xsl:variable>
	<xsl:variable name="searchURL"><xsl:value-of select="@searchURL"/></xsl:variable>
	<html>
			<xsl:attribute name="lang"><xsl:value-of select="@lang"/></xsl:attribute>
			<xsl:attribute name="xml:lang"><xsl:value-of select="@lang"/></xsl:attribute>
		<head>
		<title><xsl:value-of select="$searchTitleText"/></title>
		<link rel="StyleSheet" href="./../css/default.css" type="text/css"/>
		<script type="text/javascript" language="JavaScript">
		<![CDATA[
		
			// should not hard code the web app name here, user can change the app name
			//var webAppName= '<xsl:value-of select="$webAppName"/>';
			
			// the openenr is the topnav
			// this method is defined in topnav.js
			var baseUrl = window.opener.getBaseUrl();

			function createRequest() {
     			try {
       				request = new XMLHttpRequest();
    	 		} catch (tryMs) {
	       			try {
         				request = new ActiveXObject("Msxml2.XMLHTTP");
      				} catch (otherMs) {
        	 			try {
    	       				request = new ActiveXObject("Microsoft.XMLHTTP");
	         			} catch (failed) {
           					request = null;
         				}
       				}
     			}
			}

		   	function doSearch() {
		   		var searchString = document.searchForm.searchInput.value;
		   		var resultsPerPage = document.searchForm.searchScopeSizePerPage.value;
		   		var url = baseUrl + "/]]><xsl:choose>  
						<xsl:when test="$searchURL!=''"><xsl:value-of select="$searchURL"/></xsl:when>  
						<xsl:otherwise><![CDATA[SearchServlet]]></xsl:otherwise></xsl:choose><![CDATA[?searchString=" + encodeURIComponent(searchString) + "&hitsPerPage=" + resultsPerPage;
     			createRequest();
     			request.open("GET", makeUnique(url), true);
     			request.onreadystatechange = updatePage;
     			request.send(null);    			   			   
  			}

			function updatePage() {
    			if (request.readyState == 4) {
    				var rDiv = document.getElementById("container");
					if (rDiv != null) 
						rDiv.parentNode.removeChild(rDiv);
      				rDiv = document.createElement("div");
      				rDiv.setAttribute("id", "container");
					rDiv.innerHTML = request.responseText;
					var resultsContainer = document.getElementById("results");
					resultsContainer.appendChild(rDiv);
    			}    			
  			}
  			
  			function getPrevNextPage(url) {				
				createRequest();

     			request.open("GET", makeUnique(url), true);
     			request.onreadystatechange = updatePage;
     			request.send(null);				
			}
			
			function makeUnique(url) {
				// note some browser such as IE will try to determine if the cached page is up to date or not,
				// and may not issue a request for the same url
				// for example, if you use the same url to get next or previous page, 
				// the subsequent calls for next or previous will not be executed.
				// you need to set the Last Modified header to make sure the content is loaded.
				// to avoid this problem, we allways issue a different url by appending the request time
				
				var t = new Date().getTime();
				url = url + "&timeRequested=" + t;

				return url;
			}
				]]>
		</script>
		</head>
		<body bgcolor="#FFFFFF">
			<form id="searchForm" name="searchForm" action="javascript:doSearch();" >
				<table border="0" cellspacing="0" cellpadding="0" align="left">
					<tr>
						<td>
							<input ucfirst="false" trim="true" value="" name="searchString" type="text" id="searchInput"/>
						</td>
						<td>
							<input value="Search" caption="" style="vertical-align:middle" id="searchButton" type="button"  onClick="doSearch(); return false;"/>
						</td>
						<td>
							<select name="searchScopeSizePerPage" id="searchScopeSizePerPage" onChange="doSearch(); return false;">
								<option selected="selected" value="10">10</option>
								<option value="20">20</option>
								<option value="50">50</option>
								<option value="100">100</option>
								<option value="200">200</option>
							</select>
						</td>
					</tr>
				</table>
			</form>
			<br/><br/>
			<div id="results"></div>			
		</body>
	</html>
</xsl:template>
</xsl:stylesheet>
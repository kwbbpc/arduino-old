

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@ page import="has.definitions.SessionAttributes" %>
<%@ page import="has.user.*" %>
<%@ page import="has.sensors.core.*" %>
<%@ page import="has.sensors.*" %>
<%@ page import="has.drawer.sensors.*" %> 
<%@ page import="has.definitions.*" %> 
<%@ page import="has.core.page.exceptions.*" %> 

<%@ page errorPage="error.jsp" %>



<html>
	<head>
		<title>HAS Control Panel</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/profile.css' />
		<link rel='stylesheet' type='text/css' href='css/banner.css' />
		
		<script type='text/javascript' src='Javascript/profileCallback.js'></script>
	</head>

	<body>
	<%@include file="JspTemplates/banner.jsp" %> 
	
	
	<div class='titleBLock'>
		
		<div class='title'><h2><%= user.getUsername() + "'s profile"  %></h2></div> 
		
		<div class='container'>
		
		
			<div class='password'><form><label for='password'>Change Password:</label><input type='text' name='<%=FormValue.password %>'> <input type='submit' value="Save"></form></div>
		
			
			<%
				if(request.getAttribute(CommonParams.profileExecute) != null)
				{
					out.println("<div class='result'>");
					PageType pageType = (PageType) request.getAttribute(CommonParams.profileExecute);
					if(pageType == PageType.ProfileSuccess)
					{
						out.print("<p>Successfully applied the profile</p>");
					}
					else if(pageType == PageType.ProfileFailure)
					{
						out.print("<p>The profile was applied, but encountered errors.  There may be controls set in the profile that aren't communicating anymore.</p>");
					}
					out.println("</div>");
				}
				
			%>
				
			<div class='allProfiles'>
				<div><h2>Saved Profiles</h2></div>
				
				
				<% 
				
				for(HasProfile profile : user.getSavedProfiles())
				{
					//print off all the profile buttons here
					out.print("<div class='profile'><a href='" + 
					response.encodeURL("HasMain?" 
					+ CommonParams.page + "=" + PageType.ExecuteProfile 
					+ "&" + CommonParams.profileId + "=" + profile.getUuid()) 
					+ "'>"+ profile.getName() + "</a>"
					
					//add the "x" to delete the profile
					+ "<div class='deleteprofile'><a href='" + 
					response.encodeURL("HasMain?" 
					+ CommonParams.page + "=" + PageType.DeleteProfile 
					+ "&" + CommonParams.profileId + "=" + profile.getUuid()) 
					+ "'>x</a></div>"
					+ "</div>");
					
				}
				
				
				%>
				
				<div class='newprofile'><a href='<%= response.encodeURL("HasMain?" + CommonParams.page + "=" + PageType.CreateProfile) %>'>Create New...</a></div>
				
			
			</div>
			
			
			
		
		
		
		</div>
		
	</div>
	
	
		
	
	
	
	</body>

</html>
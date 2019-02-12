

<%@page import="has.sensors.impl.SensorNull"%>
<%@page import="has.drawer.page.BasicElementDrawer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@ page import="has.definitions.SessionAttributes" %>
<%@ page import="has.user.*" %>
<%@ page import="has.sensors.*" %>
<%@ page import="has.sensors.core.*" %>
<%@ page import="has.drawer.sensors.*" %> 
<%@ page import="has.definitions.*" %> 
<%@ page import="has.core.page.exceptions.*" %> 

<%@ page errorPage="error.jsp" %>


 
<html>
	<head>
		<title>HAS Control Panel</title>

		
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/banner.css' />
		<link rel='stylesheet' type='text/css' href='css/createprofile.css' />
		<link rel='stylesheet' type='text/css' href='css/unitLabel.css' />
		<link rel='stylesheet' type='text/css' href='css/control.css' />
		<link rel='stylesheet' type='text/css' href='css/clickableUnit.css' />
		<link rel='stylesheet' type='text/css' href='css/hasCheckbox.css' />
		
		
		
		<script type='text/javascript' src='Javascript/profileCallbacks.js'></script>
	</head>

	<body>
	<%@include file="JspTemplates/banner.jsp" %> 
	
	
	<div class='titleBLock'>
		
		<div class='title'><h2>Profile Creation</h2></div> 
		
		<div class='container'>
		
		
			<%
			
				HttpSession overview = request.getSession();
				ISensorManager sensorManager = (ISensorManager) overview.getAttribute(SessionAttributes.sensorManager);
				 String profileName = request.getParameter("profileName");
				 if(profileName == null)
					 profileName = "";
				
			%>
			
			<form action='<%=response.encodeURL("HasMain") %>' method='POST'>
			
 			<div class='name'>
 			<input type='hidden' name='<%= CommonParams.page %>' value='<%= PageType.SaveProfile %>' >
			<label for='profileName'>Profile Name:</label><input type='text' name='<%= FormValue.profileName %>' value='<%= profileName %>'>
			<input type='submit' value='Save'>
			</div>
			
			
			<div class='thermostat'>
			<% 
			
			
			List<HasUnit> unitList = sensorManager.getUnitCollection().getByType(TypeSensor.THERMOSTAT);
			
			HasUnit thermostat = null;
			
			if(!unitList.isEmpty())
				 thermostat = unitList.get(0);
			
				
				
				if(thermostat != null)
				{
	
					out.print(BasicElementDrawer.drawHasCheckbox(thermostat.getUniqueId()));
	
	
					out.print("<label for='" + thermostat.getUniqueId()
							+ "'>Keep the average temperature at:</label>");
	
					out.print("<input type='text' name='" + thermostat.getUniqueId()
							+ "' >");
	
				}
			
			
			%>
			</div>
			
			<div class='roomContainer'>
			<%
				out.print(UnitDrawer.drawControlsByRoom(sensorManager.getUnitCollection(), sensorManager.getRoomSet(), response.encodeURL("HasMain")));
			%> 
			</div>
			<span></span>
			<div class='uncategorized'>
			<%
				out.print(UnitDrawer.drawControlsUncategorized(sensorManager.getUnitCollection(), response.encodeURL("HasMain")));
			%> 
			</div>
			</form>
				
		</div>
	</div>
		
		
	
	
	
	</body>

</html>
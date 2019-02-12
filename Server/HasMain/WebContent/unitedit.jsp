

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.List"%>
<%@ page import="has.definitions.SessionAttributes" %>
<%@ page import="has.user.*" %>
<%@ page import="has.sensors.*" %>
<%@ page import="has.sensors.core.*" %>
<%@ page import="has.core.page.exceptions.*" %>
<%@ page import="has.drawer.sensors.UnitDrawer" %>

<%@ page errorPage="error.jsp" %>



<html> 
	<head>
		<title>HAS Control Panel</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/unitedit.css' />
		<link rel='stylesheet' type='text/css' href='css/banner.css' />
		<link rel='stylesheet' type='text/css' href='css/thermostat.css' />
		<link rel='stylesheet' type='text/css' href='css/hasCheckbox.css' />
		
		<script src='Javascript/unitedit.js'></script>
		
		<script type='text/javascript' src='Javascript/controlCallbacks.js'></script>
		

		
	</head>

	<body onload="onLoad()">
	<%@include file="JspTemplates/banner.jsp" %>
	
	
	
	

			<%
				HttpSession httpSession = request.getSession();
			
				ISensorManager sensorManager = (ISensorManager) httpSession.getAttribute(SessionAttributes.sensorManager);
			
				if(sensorManager == null)
				{
					throw new PageProcessingException("The H.A.S. unit manager couldn't be found.");
				}
				
				HasUnit unit = sensorManager.find(request.getParameter(FormValue.unitEditId));
				
				if(unit == null)
				{
					throw new UnitNotFoundException("The sensor to edit couldn't be found in the sensor manager.");
				}
								
				
				 
			%>
		 
		<div class='titleBLock'>
		
			<div class='title'><h2><%=unit.getTypeSensor().getInterfaceType().toString()%> Details</h2></div>
		
			
			<form class='editForm' action='<%=response.encodeURL("HasMain") %>' method="POST">
			
			
				<input type="hidden" name='<%= CommonParams.page %>' value='<%=PageType.UnitEditModify.toString()%>' >
				<input type="hidden" name='<%= FormValue.unitEditId %>' value='<%= unit.getUniqueId().toString() %>' >
			
				<input type="submit" value="Save Settings"> 
				
				<div class='type'>
				<%=UnitDrawer.drawIcon(unit, response.encodeURL("HasMain"))%><label>Type: <%= unit.getTypeSensor().toString() %></label>
				</div>
				
				
				
				<div class='data'>
				
				<div class='basicData'>
					<div><label>UUID: (<%=unit.getUniqueId().toString() %>)</label></div>
					
					
					<%
						Set<String> roomSet = sensorManager.getRoomSet();
						roomSet.add(FormValue.roomLocationNewStr);
						out.print(UnitDrawer.drawGenericDetails(unit, roomSet));
					%>
					
					<div class='newRoom'><input type="text" id='roomExt' name='<%=FormValue.roomLocationExt%>' value='Enter the new room name here.'></div> 
				</div>
				
					<% out.print(UnitDrawer.drawExpandedDetails(unit)); %>
				
				</div>
				
				
				
			</form>
			

		</div>
	
	</body>

</html>
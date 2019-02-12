

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@ page import="has.definitions.SessionAttributes" %>
<%@ page import="has.user.*" %>
<%@ page import="has.sensors.*" %>
<%@ page import="has.drawer.sensors.*" %> 
<%@ page import="has.definitions.*" %> 
<%@ page import="has.core.page.exceptions.*" %> 

<%@ page errorPage="error.jsp" %>



<html>
	<head>
		<title>HAS Control Panel</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/banner.css' />
		<link rel='stylesheet' type='text/css' href='css/overview.css' />
		<link rel='stylesheet' type='text/css' href='css/unitLabel.css' />
		<link rel='stylesheet' type='text/css' href='css/clickableUnit.css' />
		
		<script type='text/javascript' src='Javascript/controlCallbacks.js'></script>
	</head>

	<body>
	<%@include file="JspTemplates/banner.jsp" %> 
	
	
	<div class='titleBLock'>
		 
		<div class='title'><h2>Room Overview</h2></div> 
		
			<div class='container'>
			<%
				HttpSession overview = request.getSession();
					ISensorManager sensorManager = (ISensorManager) overview.getAttribute(SessionAttributes.sensorManager);
					
					if(sensorManager == null)
					{
						throw new PageProcessingException("The H.A.S. unit manager couldn't be found.");
					}
					
					
			%>
			
			<div class='roomContainer'>
			<%
				out.print(UnitDrawer.drawUnitsByRoom(sensorManager.getUnitCollection(), sensorManager.getRoomSet(), response.encodeURL("HasMain")));
			%>
			</div>
			<span></span>
			<div class='uncategorized'>
			<% 
				out.print(UnitDrawer.drawUnitsUncategorized(sensorManager.getUnitCollection(), response.encodeURL("HasMain")));
			%>
			</div>

		
		</div>
	</div>
	
	
		
	
	
	
	</body>

</html>
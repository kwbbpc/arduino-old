

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
		<link rel='stylesheet' type='text/css' href='css/system.css' />
		<link rel='stylesheet' type='text/css' href='css/banner.css' />
		
		<script type='text/javascript' src='Javascript/system.js'></script>
	</head>

	<body onload="onLoad()">
	<%@include file="JspTemplates/banner.jsp" %> 
	
	
	<div class='titleBLock'>
		
		<div class='title'><h2>H.A.S. Server Status</h2></div> 
		
		<div class='container'>
		
		
		<div class='data'><p id='serverStartTime'><%= request.getSession().getAttribute(SessionAttributes.serverStartTime) %></p></div>
		<div class='data'><p id='sessionStartTime'><%= request.getSession().getAttribute(SessionAttributes.sessionTime) %></p></div>
		
		<div>Server Time: <p id='time'></p></div>
		<div>H.A.S. uptime: <p id='uptime'></p></div>
		<div>Your session time: <p id='session'></p></div>
		<div>Number of units reporting: <p id='unitNumber'><%= ((ISensorManager) request.getSession().getAttribute(SessionAttributes.sensorManager)).getUnitCollection().size() %></p></div>
		
		
		</div>
		
	</div>
	
	
		
	
	
	
	</body>

</html>
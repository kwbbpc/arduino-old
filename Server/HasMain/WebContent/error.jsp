




<html>
	<head>
		<title>HAS Control Panel</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/banner.css' />
		<link rel='stylesheet' type='text/css' href='css/error.css' />
	</head>

	<body>
	<%@ page isErrorPage="true" %>
	
	<%@include file="JspTemplates/banner.jsp" %> 
	
	
	
	
	<div class='titleBLock'>
		
		<div class='title'><h2>Error!</h2></div>
		
			<div class='container'>
			
				<h2>H.A.S. reported errors during the request.  Go back and try again.</h2> 
				
				<span>
				
				<% 
				
				if(exception!=null)
					exception.printStackTrace(response.getWriter()); 
				
				%>
				
				</span>
			
			</div>
		
		
	</div>
	
	
		
	
	
	
	</body>

</html>
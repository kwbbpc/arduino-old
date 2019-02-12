<%@ page session="false"%>

<%@page import="has.core.page.parameters.LoginParam"%>
<html>

<head>
<title>HAS - Main Login</title>

<script type='text/javascript' src='Javascript/login.js'></script>
<link rel='stylesheet' type='text/css' href='css/login.css' />


</head>

<%@ page import="has.definitions.PageType"%>
<%@ page import="has.core.page.parameters.LoginParam"%>
<%@ page import="has.definitions.CommonParams"%>


<body>
	<form action="HasMain" method="POST">
		<input type="text" id='user' name='<%=LoginParam.username %>' value="Username" onfocus='clearUsername(this)'/>
		<div id='passwordBox'><input type="text" id='password' name='<%=LoginParam.password %>' value="Password" onfocus='clearPassword(this)'/></div>
		<input type="hidden" name='<%=CommonParams.page %>' value='<%=PageType.Login %>' />
		<input type="submit" value="Login" />
	</form>
</body>

</html>
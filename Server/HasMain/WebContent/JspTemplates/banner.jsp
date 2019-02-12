
<%@ page import="has.user.*" %>
<%@ page import="has.definitions.*" %>


<%

//Get the user and make sure they're logged in
HttpSession instance = request.getSession();

User user = (User) instance.getAttribute(SessionAttributes.user);

if(user == null)
{
	response.sendRedirect(PageType.Login.getUrl());
	user = new User();
	
}


%>

<div class="wrapper">

<div class="banner">
	<div class="profileName">
        Welcome, <a href=<%=response.encodeURL("HasMain?" + CommonParams.page + "=" + PageType.Profile.toString())%> ><%= user.getUsername() %></a>
        <a class='logout' href=<%=response.encodeURL("HasMain?" + CommonParams.page + "=" + PageType.Logout.toString() )%> >(Logout)</a>
    </div>
	<img src ="img/banner.png">
    
</div>

<div class="tab"><a href="<%= response.encodeURL("HasMain?" + CommonParams.page + "=" + PageType.Overview.toString()) %>">Overview</a></div>
<div class="tab"><a href="<%= response.encodeURL("HasMain?" + CommonParams.page + "=" + PageType.System.toString()) %>">System Health</a></div>


</div>
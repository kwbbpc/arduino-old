

function createRequest()
{

	try{
		request = new XMLHttpRequest();
	}
	catch(tryMS)
	{
		try
		{
			request = new ActiveXObject("Msxml2.XMLHTTP");
			
		}
		catch(otherMS)
		{
			try
			{
				
				request = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch(failed)
			{
				request = null;
			}
			
		}
	}
	
	return request;

};

/**
 * Empty callback to do nothing when a request comes back.
 */
function emptyCallback()
{
	//purposly do nothing.
	x=  0;
	x=x+1;
};



/**
 * Executed when the move left button is clicked for the webcam.
 */
function onVideoButtonClick(uniqueId, direction)
{
	
	//get the request object
	request = createRequest();
	if(request==null)
	{
		alert("Could not make request -> Browser incompatible");
		return;
	}
	
	
	//Configure the request
	var url = "/HasMain/Has?control=" + escape(uniqueId) + "&data=" + escape(direction);	//escape() comes in handy to kill any chars that are problematic in a request URL string.
																						//this line tells the request object the URL to call.
																						// we send the name of the item, so the server knows which details to send.
	
	request.open("GET",url,true);	//this tells the request object how to handle the connection to the server
	
	//Setup the callback function that the server will call when there's new information available
	request.onreadystatechange = emptyCallback();
	
	
	//Send the request
	request.send(null); //null means no extra data is sent with the request
	
};

function onLightSwitchButtonClick(uniqueId, buttonText)
{
	
	buttonName = "switch" + uniqueId;
	
	//what's the actual state of the light?
	//	if the user clicks the button multiple times, the buttonText
	//	param will be invalid - it will stil reflect the state at last
	//	page reload.  Check it's validity by comparing to the innerHTML
	//	text.  If the two match, we're in sync and buttonText is valid.
	//	if they don't match, replace the buttonText with the innerHTML.
	jsState = document.getElementsByName(buttonName)[0].innerHTML;
	if(buttonText == jsState)
	{
		//the server and the page are in sync.
	}
	else
	{
		//server and page not in sync, so trust the innerHTML.
		buttonText = document.getElementsByName(buttonName)[0].innerHTML;
	}
	
	
	
	//get the request object
	request = createRequest();
	if(request==null)
	{
		alert("Could not make request -> Browser incompatible");
		return;
	}
	
	
	data = "unknown";
	newText = "Unknown State!";
	if(buttonText == "Turn on")
	{
		newText = "Turn off";
		data = "on";
	}
	else if(buttonText = "Turn off")
	{
		newText = "Turn on";
		data = "off";
	}
	
	
	//Configure the request
	var url = "/HasMain/Has?control=" + escape(uniqueId) + "&data=" + escape(data);	//escape() comes in handy to kill any chars that are problematic in a request URL string.
																						//this line tells the request object the URL to call.
																						// we send the name of the item, so the server knows which details to send.
	
	
	request.open("GET",url,true);	//this tells the request object how to handle the connection to the server
	
	//Setup the callback function that the server will call when there's new information available
	request.onreadystatechange = emptyCallback();
	
	
	//Send the request
	request.send(null); //null means no extra data is sent with the request

	
	
	document.getElementsByName(buttonName)[0].innerHTML = newText;



};
window.onload = initPage;
function initPage()
{
	
	//find all the thumbnails in the div tag
	thumbs = document.getElementById("thumbnailPane").getElementsByTagName("img");
	
	//setup the handler for each image
	for(var i=0; i<thumbs.length; ++i)
	{
		image = thumbs[i];
		
		//create the onclick function
		//	this is run whenever a thumbnail is clicked.
		image.onclick = function()	//javascript lets you define functions without a name.
		{
			//find the full-size image name
			detailURL = 'images/' + this.title + '-detail.jpg'; //when an image is clicked, that images title is used to figure out the image's URL.
			document.getElementById("itemDetail").src = detailURL; //clicking a thumbnail changes the detail image's src attribute (making the new image display)
			getDetails(this.title);	
		};
		
	}
	
	
}

function getDetails(itemName)
{

	//get the request object
	request = createRequest();
	if(request==null)
	{
		alert("Could not make request -> Browser incompatible");
		return;
	}
	
	
	//Configure the request
	var url = "HasServelet/ServeletCore?ImageID=" + escape(itemName);	//escape() comes in handy to kill any chars that are problematic in a request URL string.
															//this line tells the request object the URL to call.
															// we send the name of the item, so the server knows which details to send.
	
	request.open("GET",url,true);	//this tells the request object how to handle the connection to the server
	
	//Setup the callback function that the server will call when there's new information available
	request.onreadystatechange = displayDetails;
	
	
	//Send the request
	request.send(null); //null means no extra data is sent with the request

}

function displayDetails()
{
	if(request.readyState == 4)
	{
		if(request.status == 200)
		{
			detailDiv = document.getElementById("description");	//get a reference to the html element the item details will go in
			detailDiv.innerHTML = request.responseText;	//set the html returned by the server into that element.
		}
	}
	


}

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

}
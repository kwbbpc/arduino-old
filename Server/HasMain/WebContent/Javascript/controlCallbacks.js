function createRequest() {

	try {
		request = new XMLHttpRequest();
	} catch (tryMS) {
		try {
			request = new ActiveXObject("Msxml2.XMLHTTP");

		} catch (otherMS) {
			try {

				request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (failed) {
				request = null;
			}

		}
	}

	return request;

};

/**
 * Executed when the move left button is clicked for the webcam.
 */
function onVideoButtonClick(uniqueId, direction) {

	// get the request object
	request = createRequest();
	if (request == null) {
		alert("Could not make request -> Browser incompatible");
		return;
	}

	// Configure the request
	var url = "/HasMain/Has?control=" + escape(uniqueId) + "&data="
			+ escape(direction); // escape() comes in handy to kill any chars
	// that are problematic in a request URL
	// string.
	// this line tells the request object the URL to call.
	// we send the name of the item, so the server knows which details to send.

	request.open("GET", url, true); // this tells the request object how to
	// handle the connection to the server

	// Setup the callback function that the server will call when there's new
	// information available
	request.onreadystatechange = emptyCallback();

	// Send the request
	request.send(null); // null means no extra data is sent with the request

};

function onLightSwitchClick(img, imgMap, uuid) {

	relativeLoc = img.src.split("HasMain/")[1];

	var dataToSend;

	if (relativeLoc == imgMap.on) {
		// turn the light off
		dataToSend = "off";
		img.src = imgMap.off;

	} else if (relativeLoc == imgMap.off) {
		// turn the light on
		dataToSend = "on";
		img.src = imgMap.on;
	}

	// get the request object
	request = createRequest();
	if (request == null) {
		alert("Your browser doesn't support AJAX, so controls will be read only.");
		return;
	}

	/*
	 * // Configure the request var url = "/HasMain/Has?control=" +
	 * escape(uniqueId) + "&data=" + escape(data); // escape() comes in handy to
	 * kill any chars that // are problematic in a request URL string. // this
	 * line tells the request object the URL to call. // we send the name of the
	 * item, so the server knows which details to send.
	 * 
	 * request.open("GET", url, true); // this tells the request object how to //
	 * handle the connection to the server // Setup the callback function that
	 * the server will call when there's new // information available
	 * request.onreadystatechange = emptyCallback(); // Send the request
	 * request.send(null); // null means no extra data is sent with the request
	 * 
	 * document.getElementsByName(buttonName)[0].innerHTML = newText;
	 */
};

function onBlindsClick(img, imgMap, uuid) {

	relativeLoc = img.src.split("HasMain/")[1];

	var dataToSend;

	if (relativeLoc == imgMap.up) {
		// go to blinds next state
		img.src = imgMap.open;
		details = document.getElementById(uuid.concat("_value"));
		details.innerHTML = "Open";

	} else if (relativeLoc == imgMap.open) {
		// go to blinds next state
		img.src = imgMap.down;
		details.innerHTML = "Closed, down";
	} else if (relativeLoc == imgMap.down) {
		// go to blinds next state
		img.src = imgMap.up;
		details.innerHTML = "Closed, up";
	}

	// get the request object
	request = createRequest();
	if (request == null) {
		alert("Your browser doesn't support AJAX, so controls will be read only.");
		return;
	}

	/*
	 * // Configure the request var url = "/HasMain/Has?control=" +
	 * escape(uniqueId) + "&data=" + escape(data); // escape() comes in handy to
	 * kill any chars that // are problematic in a request URL string. // this
	 * line tells the request object the URL to call. // we send the name of the
	 * item, so the server knows which details to send.
	 * 
	 * request.open("GET", url, true); // this tells the request object how to //
	 * handle the connection to the server // Setup the callback function that
	 * the server will call when there's new // information available
	 * request.onreadystatechange = emptyCallback(); // Send the request
	 * request.send(null); // null means no extra data is sent with the request
	 * 
	 * document.getElementsByName(buttonName)[0].innerHTML = newText;
	 */
};
function onLightSwitchClick(img, imgMap, uuid) {

	relativeLoc = img.src.split("HasMain/")[1];

	var dataToSend;

	if (relativeLoc == imgMap.on) {
		// turn the light off
		dataToSend = "off";
		img.src = imgMap.off;

		// update the sensor value to save
		var element = document.getElementById(uuid.concat("_value"))
		element.value = "0";

	} else if (relativeLoc == imgMap.off) {
		// turn the light on
		dataToSend = "on";
		img.src = imgMap.on;

		// update the sensor value to save
		var element = document.getElementById(uuid.concat("_value"))
		element.value = "1";
	}

};

function onBlindsClick(img, imgMap, uuid) {

	relativeLoc = img.src.split("HasMain/")[1];

	if (relativeLoc == imgMap.up) {
		img.src = imgMap.open;
		// update the sensor value to save
		var element = document.getElementById(uuid.concat("_value"))
		element.value = "open";

	} else if (relativeLoc == imgMap.open) {
		img.src = imgMap.down;

		// update the sensor value to save
		var element = document.getElementById(uuid.concat("_value"))
		element.value = "down";
	} else if (relativeLoc == imgMap.down) {
		img.src = imgMap.up;

		// update the sensor value to save
		var element = document.getElementById(uuid.concat("_value"))
		element.value = "up";
	}

};
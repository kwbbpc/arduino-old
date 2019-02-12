function onLoad() {
	document.getElementById("roomExt").style.display = "none";
}

function onRoomSelectChange(dropdown) {
	var selectedOption = dropdown.options[dropdown.selectedIndex].value;

	if (selectedOption == "New...") {
		document.getElementById("roomExt").style.display = "inline-block";
	} else {
		document.getElementById("roomExt").style.display = "none";
	}

	return;
}

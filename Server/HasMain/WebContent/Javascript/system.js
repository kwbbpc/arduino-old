function onLoad() {

	updateCurrentTime();
	setInterval('updateCurrentTime()', 1000);

	updateServerUpTime();
	setInterval('updateServerUpTime()', 1000);

	updateSessionTime();
	setInterval('updateSessionTime()', 1000);

}

function updateCurrentTime() {

	var currentTime = new Date();

	document.getElementById("time").innerHTML = formatTimeStr(currentTime);

	return;
}

function updateServerUpTime() {

	var currentTime = new Date();

	var startTime = document.getElementById("serverStartTime").innerHTML;

	var uptime = Math.abs(startTime - currentTime);

	document.getElementById("uptime").innerHTML = formatTimePassedStr(uptime);
}

function updateSessionTime() {

	var currentTime = new Date();

	var startTime = document.getElementById("sessionStartTime").innerHTML;

	var uptime = Math.abs(startTime - currentTime);

	document.getElementById("session").innerHTML = formatTimePassedStr(uptime);
}

function formatTimeStr(time) {
	var day = time.getDate();
	var month = time.getMonth();
	var year = time.getFullYear();

	var hr = time.getHours();
	var min = time.getMinutes();
	var sec = time.getSeconds();

	var timeStr = year + "/" + month + "/" + day + ", " + hr + ":" + min + ":"
			+ sec;

	return timeStr;
}

function formatTimePassedStr(time) {

	x = time / 1000;
	var sec = x % 60;

	x /= 60
	var min = x % 60;

	x /= 60
	var hr = x % 24;

	x /= 24
	days = x;

	var timeStr = parseInt(days) + " days, " + parseInt(hr) + " hours, "
			+ parseInt(min) + " minutes, " + parseInt(sec) + " seconds";

	return timeStr;
}
function initialize() {
	applyRoundedCorners();
	setupEnterKeyEvent();
	setInputFocus();
}

function applyRoundedCorners() {
	$(".subpanelDivInner").corner("round 8px top").parent().corner("round 12px top");
	$(".leftSubpanelDivInner").corner("round 8px top").parent().corner("round 12px top");
}

function setupEnterKeyEvent() {
	$("#chatInput").keyup(function(e) {
		if (e.keyCode == 13) {
			submitChatInput();
		}
	});
}

function setInputFocus() {
	$("#chatInput").focus();
}

function submitChatInput() {
	var chatInputJquery = $("#chatInput");
	var chatOutputJquery = $("#chatOutput");

	var outputText = chatInputJquery.attr("value") + "<br/>";
	chatOutputJquery.append(outputText);
	chatOutputJquery.attr("scrollTop", chatOutputJquery.attr("scrollHeight"));
	
	chatInputJquery.attr("value", "");
	setInputFocus();
}
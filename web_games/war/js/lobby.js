var messageIndex = 0;
var readyForNewMessages = true;

function initialize() {
	applyRoundedCorners();
	getMessageIndex();
	setupEnterKeyEvent();
	setInputFocus();
	setTimeout(getNewMessages, 2000);
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
	var messageText = chatInputJquery.attr("value");
	chatInputJquery.attr("value", "");

	sendMessage(messageText);
	
	setInputFocus();
}

function getMessageIndex() {
	var json = { "command": "retrieve_message_index" };
	$.post("lobby.json", json,
  		function(data){
			messageIndex = data.message_index;
  		}, 
  	"json");
}

function sendMessage(messageText) {
	var json = { "command": "add_message", "message": messageText, "message_index": messageIndex };
	$.post("lobby.json", json,
  		function(data){
			updateChatOutput(data.messages);
			messageIndex = data.message_index;
  		},
  	"json");
}

function getNewMessages() {
	var json = { "command": "retrieve_messages", "message_index": messageIndex };
	$.post("lobby.json", json,
  		function(data){
			updateChatOutput(data.messages);
			messageIndex = data.message_index;
			setTimeout(getNewMessages, 2000);
  		},
  	"json");
}

function updateChatOutput(messages) {
	var chatInputJquery = $("#chatInput");
	var chatOutputJquery = $("#chatOutput");

	for (var i = 0; i < messages.length; i++) {
		var outputText = messages[i] + "<br/>";
		chatOutputJquery.append(outputText);
	}
	
	chatOutputJquery.attr("scrollTop", chatOutputJquery.attr("scrollHeight"));
}
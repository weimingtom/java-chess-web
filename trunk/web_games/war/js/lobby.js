var CHAT_RETRIEVE_TIME = 1500;
var USER_RETRIEVE_TIME = 5000;
var USER_REFRESH_TIME = 2500;

var messageIndex = 0;

function initialize() {
	applyRoundedCorners();
	setupEnterKeyEvent();
	setInputFocus();
	refreshUser();
	getUsers();
	getNewMessages();
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

function sendMessage(messageText) {
	var json = { "command": "add_message", "message": messageText, "message_index": messageIndex };
	$.post("lobby.json", json,
  		function(data){
			updateChatOutput(data.messages);
			messageIndex = data.message_index;
  		},
  	"json");
}

function refreshUser() {
	var json = { "command": "refresh_user" };
	$.post("lobby.json", json,
  		function(data){
			setTimeout(refreshUser, USER_REFRESH_TIME);
  		},
  	"json");
}

function getUsers() {
	var json = { "command": "retrieve_users" };
	$.post("lobby.json", json,
  		function(data){
			if (data.users) {
				updateUserList(data.users);
			}
			setTimeout(getUsers, USER_RETRIEVE_TIME);
  		},
  	"json");
}

function getNewMessages() {
	var json = { "command": "retrieve_messages", "message_index": messageIndex };
	$.post("lobby.json", json,
  		function(data){
			if (data.messages) {
				updateChatOutput(data.messages);
			}
			messageIndex = data.message_index;
			setTimeout(getNewMessages, CHAT_RETRIEVE_TIME);
  		},
  	"json");
}

function updateUserList(users) {
	var userListJquery = $("#userList");
	
	var outputText = "";
	for (var i = 0; i < users.length; i++) {
		outputText += users[i] + "<br />";
	}

	userListJquery.html(outputText);
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

function openGameWindow(mypage,myname,w,h,scroll,pos) {
	var win=null;
	if(pos=="random"){LeftPosition=(screen.width)?Math.floor(Math.random()*(screen.width-w)):100;TopPosition=(screen.height)?Math.floor(Math.random()*((screen.height-h)-75)):100;}
	if(pos=="center"){LeftPosition=(screen.width)?(screen.width-w)/2:100;TopPosition=(screen.height)?(screen.height-h)/2:100;}
	else if((pos!="center" && pos!="random") || pos==null){LeftPosition=0;TopPosition=20}
	settings='width='+w+',height='+h+',top='+TopPosition+',left='+LeftPosition+',scrollbars='+scroll+',location=no,directories=no,status=yes,menubar=yes,toolbar=yes,resizable=no';
	win=window.open(mypage,myname,settings);
}
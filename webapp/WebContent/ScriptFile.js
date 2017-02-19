var wsUri = "ws://localhost:8080/webapp/echo"; 
	
function messageWebSocket(msg)
{
  	websocket = new WebSocket(wsUri);
  	websocket.onopen = function(evt) { onOpen(msg) };
  	websocket.onclose = function(evt) { onClose(evt) };
  	websocket.onmessage = function(evt) { onMessage(evt) };
  	websocket.onerror = function(evt) { onError(evt) };
}

function onOpen(evt)
{
  	writeToScreen("CONNECTED");
  	doSend(evt);
}

function onClose(evt)
{
  	writeToScreen("DISCONNECTED");
}

function onMessage(evt)
{
  	writeToScreen('<span style="color: blue;">RESPONSE: ' + evt.data+'</span>');
  	websocket.close();
}

function onError(evt)
{
	writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}

function doSend(message)
{
  	writeToScreen("SENT: " + message);
  	websocket.send(message);
}

function writeToScreen(message)
{
  	var pre = document.createElement("p");
  	pre.style.wordWrap = "break-word";
  	pre.innerHTML = message;
  	document.getElementById("testMsg").appendChild(pre);
}

/*append a new message to view dynamically*/
 function createMessage(msg){
		return "<div class='container' id='msgs' style='border-style: none none solid none'>" + 			
					"<div class='col-sm-4'>" + 
						"<p><span class='label label-primary'>user name:</span></p>" + 
					"</div>" + 
					"<div class='col-sm-4'>" + 
						"<p>" + msg + "</p>" + 
					"</div>" + 
					"<div class='col-sm-4'>" + 
						"<p><a id='reply' href='#msg' onclick='handleReply()'>reply</a></p>" + 
					"</div>" + 
				"</div>"; 	
	}
 
 function validate(text, id){
		var location = text.indexOf(",");
		var count = text.length;
		
		if (count>=10){
			alert("too many charachters' only 10 allowed");
		}
		if(location != -1){
			 alert("can't use the charrachter: ,");
			 document.getElementById(id).value = "";
		 }
	}
 /*create json object from user forms text/input fields*/
 function createJSON(my_form){
		var arr = my_form.getElementsByTagName("input"); 
		var jsonObj = {};
		jsonObj["username"] = arr[0].value.toString();
		jsonObj["password"] = arr[1].value.toString();
		jsonObj["nickname"] = arr[2].value.toString();
		jsonObj["description"] = arr[3].value.toString();
		jsonObj["photo"] = arr[4].value.toString();
		
		return jsonObj;
	}
 
 /*create json obtect from a given array */
 function createGeneralJSON(my_form){
		var arr = my_form.getElementsByTagName("input"); 
		var jsonObj = {};
		jsonObj["username"] = arr[0].value.toString();
		jsonObj["password"] = arr[1].value.toString();
		jsonObj["nickname"] = arr[2].value.toString();
		jsonObj["description"] = arr[3].value.toString();
		jsonObj["photo"] = arr[4].value.toString();
		
		return jsonObj;
	}
 
 function handleReply(){
		alert("reply needed");
	}

 $(document).ready(function(){
	 /*registration or login handling*/
	 $("#rgstrBtn").click(function(){
		var my_form = document.getElementById("frm");
		var url = 'http://localhost:8080/webapp/UserServlet' + '?query=insert';		
		var myjson = JSON.stringify(createJSON(my_form));
		/*new here*/
		
		
		$.post(	url,
				myjson,
				function(data){
		});
	});

	 /*login handeling*/
	$("#lgnBtn").click(function(){
		/*TODO: add code here to handle loging in..*/
	});
	 
	 /*send message handling*/
	 $("#sndmsg").click(function(event){
		  
		event.preventDefault();

		var id = 1;					//TODO: add code here to handle msg counter
		var nickname = "me"; 		//TODO: add code to get user nickname from localStorage obj
		var timeStamp = "now";
		var msg = $("#msg").val();
		var reply = 0;
		var replyTo = "you";		//TODO: get name of the user that wrote the msg replyed to
		var offset = 1;				//TODO: find msg replyed to and increase indent by 1

		var obj = {	
					"\msgID": 1, 
					"\nickname": "me",
					"\timestamp": "later",
					"\content": msg,
					"\reply": 1,
					"\replyTo": "you",
					"\indent": 1
				};
		if(msg=="") alert("message is empty");
		else
			{
			
				$("#conversation").append(createMessage(msg));
				$("#msg").val("");
				messageWebSocket(JSON.stringify(obj));  
			}
	 });
	 
});

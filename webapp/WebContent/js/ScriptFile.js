/**************************************************/
/*this is where the app functionality takes place */
/*all function JQuery and standard functions 	  */
/*												  */
/* last modified by: shahar aizikovitz			  */
/* 			   date: 16.2.2017				   	  */
/**************************************************/

/* VARIABLES: */
/*------------*/
var wsUri = "ws://localhost:8080/webapp/message";		//message socket address 


/* FUNCTIONS: */
/*------------*/ 

/* functions to handle web socket functionality*/
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
  	writeToScreen("SENT: " + message);	//writes to page for control, to be deleted
  	websocket.send(message);
}
function writeToScreen(message)
{
  	var pre = document.createElement("p");
  	pre.style.wordWrap = "break-word";
  	pre.innerHTML = message;
  	document.getElementById("testMsg").appendChild(pre);
}



/*append a new message to view dynamically, message is an object consist of 1 row and 3 columns
 * of equal size, each contain a paragraph. each message has a user label, paragraph for message and reply button*/
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
			 alert("can't use the charrachter: ','");
			 document.getElementById(id).value = "";
		 }
	}

/*create json object from user forms text/input fields*/
function createUserObject(my_form){
		var arr = my_form.getElementsByTagName("input"); 
		var um = arr[0].value;
		var pw = arr[1].value;
		var nm = arr[2].value;
		var ds = arr[3].value;
		var ph = arr[4].value; 
		
		var Obj = {
					userName : um,
					password : pw,
					nickName : nm,
					description : ds,
					photo : ph
				   };
		alert("user:" + um + "pass:" + pw + "nickname:" + nm + "description:" + ds + "photo:" + ph); 
		return Obj;
	}

function handleReply(){
	alert("reply needed");
}

/*When the user clicks anywhere outside of the modal, close it
 * 	$("#signin").click(function(){
		alert("sign");
		modal.style.display = "block"; 
	});
	
	*		$(".modal-fade").modal('show');/
*/


/*handling sign-in form fade in form*/
$(document).ready(function(){
	$("#signin").click(function(){
		alert("l"); 
		$("#loginModal").modal({show:true});
	});
	
	$("#modal").click(function(){
		alert();
		$("#loginModal").modal({show:true});
	});
});

$(document).ready(function(){
	
	 /*registration or login handling*/
	 $("#rgstrBtn").click(function(){
		var my_form = document.getElementById("frm");
		var url = 'http://localhost:8080/webapp/UserServlet' + '?query=insert';		
		var msg = (createUserObject(my_form));
		/*TODO: check form fields before sending to server*/
		alert(JSON.stringify(msg));
		$.post(	url,
				JSON.stringify(msg),
				function(data){
		});
	});
	 
	 /*send message handling*/
	 $("#sndmsg").click(function(event){		  
		event.preventDefault();

		var id = 1;					//TODO: add code here to handle msg counter
		var nickname = "me"; 		//TODO: add code to get user nickname from localStorage obj
		var dt = (new Date()); 		//Date(year, month, day, hours, minutes, seconds, milliseconds); 
		var msg = $("#msg").val();	//the message
		var reply = 0;
		var replyTo = "you";		//TODO: get name of the user that wrote the msg replyed to
		var offset = 1;				//TODO: find msg replyed to and increase indent by 1
		
		var obj = {	
					id: 1, 
					user: "me",
					timeStamp: dt,
					content: msg,
					test : dt,
					isReplyable: 1,
					replyedTo: 3,
					offset: 1
					};
		if(msg=="") alert("message is empty");
		else
			{
			
				$("#conversation").append(createMessage(msg));
				$("#msg").val("");
				alert(JSON.stringify(obj));		//TODO: erase later
				messageWebSocket(JSON.stringify(obj));  
			}
	 });
	 
});
 
/* count the length of the message, under 500 characters alert the user and erase overflow*/
 function wordCount(){
	 
 }

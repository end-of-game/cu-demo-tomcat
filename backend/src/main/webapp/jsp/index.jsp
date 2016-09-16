<!DOCTYPE html>
<html>
<head>
<title>Calculator App Using Spring 4 WebSocket</title>
<script src="resources/sockjs.js"></script>
<script src="resources/stomp.js"></script>
<script type="text/javascript">
	var stompClient = null;
	function setConnected(connected) {
		document.getElementById('connect').disabled = connected;
		document.getElementById('disconnect').disabled = !connected;
		document.getElementById('calResponse').innerHTML = '';
	}
	function connect() {
		var socket = new SockJS('/my-socket');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame) {
			setConnected(true);
			console.log('Connected: ' + frame);
			stompClient.subscribe('/topic/results', function(calResult) {
				console.log(JSON.parse(calResult.body))
				showResult(JSON.parse(calResult.body));
			});
		});
	}
	function disconnect() {
		stompClient.disconnect();
		setConnected(false);
		console.log("Disconnected");
	}
	
	function showResult(message) {
		var response = document.getElementById('calResponse');
		var p = document.createElement('p');
		p.style.wordWrap = 'break-word';
		p.appendChild(document.createTextNode(message.name + message.price));
		response.appendChild(p);
	}
</script>
</head>
<body>
	<noscript>
		<h2>Enable Java script and reload this page to run Websocket Demo</h2>
	</noscript>
	<h1>Spring 4 WebSocket</h1>
</body>
</html>

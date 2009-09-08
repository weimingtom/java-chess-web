<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
	<title>Chess Lobby - Brasee.com</title>
	<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="css/menu.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="css/lobby.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="css/smoothness/jquery-ui-1.7.1.custom.css" type="text/css" media="screen" />
	<script type="text/javascript" src="js/lobby.js"></script>
	<script type="text/javascript" src="js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="js/jquery-ui-1.7.1.custom.min.js"></script>
	<script type="text/javascript" src="js/jquery.corner.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			initialize();
		});
	</script>
</head>
<body>
	<div id="pageborder">
		<div id="page">
			<div id="header"><table border="0" cellpadding="0" cellspacing="0" width="100%"><tr><td align="left" valign="top"><a href="<c:url value="/"/>"><img src="img/games_header_link.jpg" alt="Brasee.com Games"/></a></td><td align="right" valign="top"><a href="/"><img src="img/games_header_back_to_main.jpg" alt="Brasee.com Home"/></a></td></tr></table></div>
			<div id="nav">
				<ul>
					<li><a href="lobby.htm"><img src="img/chess_icon.png" alt="Chess Lobby"/> Chess Lobby</a></li>
					<li><a href="chessSingleClient.htm"><img src="img/chess_icon.png" alt="Play Chess (same computer)"/> Play Chess (same computer)</a></li>
				</ul>
  			</div> 
			<div id="container">
				
				<div id="leftcontent">
					<table border="0" cellspacing="0" cellpadding="5">
						<tr>
							<c:forEach items="${gameMap}" var="gameMapEntry">
								<td>
									<b>Game ${gameMapEntry.key}</b>
									<br/> 
									<img src="gamePreview.htm?gameId=${gameMapEntry.key}" />
									<br/>
									<b>${gameMapEntry.value}</b>
								</td>
							</c:forEach>
						</tr>
					</table>
				</div>	<!-- /leftcontent -->
				
				<div id="rightcontent">
					<center><h3>Welcome, ${user.name}!</h3></center>
					<br/>
					<div class="subpanelDivOuter"><div class="subpanelDivInner">
						<div class="subpanelHeader">Online Users</div>
						<span id="userList"></span>
					</div></div>
				</div> <!-- /rightcontent -->
				
				<div id="bottomcontent">
					<div class="bottomSubpanelDivOuter"><div class="bottomSubpanelDivInner">
						<center><div id="chatOutput"></div></center>
						<input type="text" id="chatInput"></input>
					</div></div> 
				</div> <!-- /bottomcontent -->
				
			</div> <!-- /container -->
			<div id="footer-wrapper">
				<div id="footer">
					<br/>
					Created by Kaleb Brasee.
					<br/>
					<br/>
					<a href="http://code.google.com/p/java-chess-web">Available on Google Code</a>.
					<br/>
					&nbsp;
				</div> <!-- /footer -->
			</div> <!-- /footer-wrapper -->
		</div> <!-- /page -->
	</div> <!-- /pageborder -->
	
</body>
</html>
<#import "spring.ftl" as spring/>

<html>
<head>
	<title>Welcome!</title>
</head>
<body>
	<h1>Welcome <@spring.message "${user}"/> !</h1>
</body>
</html>
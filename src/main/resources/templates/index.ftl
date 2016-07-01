<#import "spring.ftl" as spring/>

<html>
  <body>
    Hello
    <@spring.message '${name}'/>
  </body>
</html>

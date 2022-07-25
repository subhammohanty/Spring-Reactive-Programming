Steps:
======
1)Include Reactive Dependency in pom.xml
2)Create a Route Class
3)Route to Handler Class
4)In Handler Class always return Mono<ServerResponse> (if Flux then add it to ServerResponse Body)

To Handle Exception:
===================
Create a class that extends exception class (BookAPIException)

1)Include key value properties in application.properties
2)Create a Exception Class That Extends AbstractErrorWebExceptionHandler
3)Overide unimplemente methods and super constructor.
4)Create a Method that returs ServerResponse type of request, Content Type , etc
5)Create a class that Extends DefaultErrorAttributes 
6)Create Map Object and Throwable Object.
7)Put all the attributes you want.
8)return map;
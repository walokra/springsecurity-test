# Spring Security sample project

Sample project of using Spring Security with database authentication provider.

The project has multiple branches with different technologies:

* spring-2:

  * Java 1.5
  * Using Spring 2.5.6 and Spring Security 2.0
	* JSF 1.2
  * RichFaces 3.3.3
  * JPA 1

* spring-3 (to be done)

  * Java 1.8
  * Spring 3.x and Spring Security 3.x
  * JSF 2.x
  * JPA 2

Custom authentication provider is done with JPA and Hibernate and using H2 database in memory.

## Running

Deploy the project to e.g. Tomcat 6 (spring-2). The application can be accessed them from URL http://localhost:8080/sectest/.

You can run the H2 database also as TCP server. The command will start the h2 in tcp mode and it will open the h2 console in a browser (http://localhost:8082/).

    java -cp h2*.jar org.h2.tools.Server

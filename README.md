
How to run the server
=======================

> Maven: mvn exec:java (The sever will be run on port 12667 by default and rootPath will be run on 'C:\' by default. You can change it using bash or cmd)


> IDE: run TaskMain.java as Java application.


Running example
================

* Run the server as above explanation. 
* open a bash or cmd terminal and run: telnet localhost 12667.
  
> "5 img"

How to build the jar
=====================
> mvn clean install (will run the tests too)

> Maven build jar

  * mvn clean install to build the jar
  * cd target/ then run: java -jar telnet-server.jar



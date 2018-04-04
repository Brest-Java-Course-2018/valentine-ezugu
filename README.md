# valentine-ezugu
#simple transport company order of petrol management for Brest java course(2018)

[![Build Status](https://travis-ci.org/Brest-Java-Course-2018/valentine-ezugu.svg?branch=master)](https://travis-ci.org/Brest-Java-Course-2018/valentine-ezugu)
[![Coverage Status](https://coveralls.io/repos/github/Brest-Java-Course-2018/valentine-ezugu/badge.svg)](https://coveralls.io/github/Brest-Java-Course-2018/valentine-ezugu)

# Getting Started
 1. Check  
       
   $java -version  
        
   $export JAVA_HOME = ...
        
   $mvn -version
        
2. Build
    
   from main directory     
   $mvn clean install
        
3. Preparing reports
      
        $mvn site
      
        $mvn site:stage
      
        check:/target/stage/index.html
        ``
# To run jetty-server
        
  To run  web-app -/web-app  mvn jetty run 
        
  TO run Rest webservices producer -/rest-app  mvn jetty run
        
4. web-app runs on http://localhost:8082/

5. Travis CI integration
    https://travis-ci.org/Brest-Java-Course-2018/valentine-ezugu/

To be found in this projects are software products of the following versions :

JDK: 1.8

Maven: 3.3.9

Spring: 4

jetty 9.4.8.v20171121

travis ci - also to add jenkins 
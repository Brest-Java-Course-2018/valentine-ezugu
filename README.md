# valentine-ezugu
#simple transport company order of petrol management for Brest java course(2018)

[![Build Status](https://travis-ci.org/Brest-Java-Course-2018/valentine-ezugu.svg?branch=master)](https://travis-ci.org/Brest-Java-Course-2018/valentine-ezugu)
[![Coverage Status](https://coveralls.io/repos/github/Brest-Java-Course-2018/valentine-ezugu/badge.svg)](https://coveralls.io/github/Brest-Java-Course-2018/valentine-ezugu)

# Getting Started
 1. Check  
       
    $java -version  
        
    $export JAVA_HOME = ..
        
    $mvn -version
    
    Get a copy from clone or download section on gitHub.
        
2. Build
    
   from main directory     
   $mvn clean install
   
        
3. Preparing reports
      
        $mvn site
      
        $mvn site:stage
      
        check:/target/stage/index.html
        ``
4.  To run jetty server
        
   To run  rest service go to dir* -/rest-producer mvn jetty:run 
  
# use embedded jetty server to test rest-producer
     
     mvn -pl rest-producer/ jetty:run
     
     Once started, the REST server should be available at:
     
     http://localhost:8088
     
     Try CURL:
     
     curl -v localhost:8088/trucks
     
     curl -v localhost:8088/trucks/1
     
     curl -H "Content-Type: application/json" -X POST -d '{"truckCode":"BY3354","purchasedDate":"xyz", "descriptions":"my truck"}' -v localhost:8088/trucks
     
     curl -X "DELETE" localhost:8088/trucks/1
     
     curl -v localhost:8088/orders/
     
     curl -X  "DELETE" localhost:8088/orders/2
     
     curl -v  http://localhost:8088/orders?start=2007-01-01&end=2008-01-01

#Angular 

  **what you will need**
    Prerequisites
  
npm is required
how to install npm

https://www.npmjs.com/get-npm 
    
  **This project was generated with Angular CLI version 1.7.3.**
   
   https://cli.angular.io/
   
  **Move to directory ng-client**
   
5. when in directory you will need npm - run  npm install 

6. To run test- from directory run - ng test

7.To start angular run - ng serve -o 

8.  /ng-client app runs on http://localhost:4200/

   Travis CI integration
    https://travis-ci.org/Brest-Java-Course-2018/valentine-ezugu/

To be found in this projects are software products of the following versions :

JDK: 1.8

Maven: 3.3.9

Spring: 4

jetty 9.4.8.v20171121

travis ci - also to add jenkins 
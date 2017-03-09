# JSON Web Token / React / Spring Boot example

This is an example project where a Spring REST API is secured using JSON Web Tokens. There are few examples available for Java and there are some pitfalls, I decided to extract my proof of concept into a stand-alone example and publish it for all to see.


### Setup
- Install node version 4.4.7
- Install npm version 2.15.8
- Install Java jdk 1.8
- Install Maven 3.x
- Add Java and Maven to the env variable PATH 

Everything tested on ubuntu 15.10

### Install
- In the folder back-end run "mvn install"
- In the folder back-end/target run "sample-app-1.0-SNAPSHOT.jar " and keep it running
- In the folder front-end/app-react run "sh run.sh" and keep it running

### Usage
URL access:  http://localhost:8080

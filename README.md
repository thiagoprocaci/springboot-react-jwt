# JSON Web Token / React / Spring Boot example

This is an example project where a Spring REST API is secured using JSON Web Tokens. There are few examples available for Java and React and there are some traps. So, I decided to make my proof of concept, creating a standalone project. Everything is published in this repository.

### JSON Web Tokens

JSON Web Token (JWT) is an open standard (RFC 7519) that defines a compact and self-contained way for securely transmitting information between parties as a JSON object. This information can be verified and trusted because it is digitally signed. JWTs can be signed using a secret (with the HMAC algorithm) or a public/private key pair using RSA [1].


### When should you use JSON Web Tokens?

Here are some scenarios where JSON Web Tokens are useful:

- Authentication: This is the most common scenario for using JWT. Once the user is logged in, each subsequent request will include the JWT, allowing the user to access routes, services, and resources that are permitted with that token. Single Sign On is a feature that widely uses JWT nowadays, because of its small overhead and its ability to be easily used across different domains [1].

- Information Exchange: JSON Web Tokens are a good way of securely transmitting information between parties, because as they can be signed, for example using public/private key pairs, you can be sure that the senders are who they say they are. Additionally, as the signature is calculated using the header and the payload, you can also verify that the content hasn't been tampered with [1].


### More Information About JSON Web Tokens

For more details, check out the website https://jwt.io/ .


### Server Side: Spring Boot

Spring boot is excellent to create RESTful services. On the server side, the JWT signing is done in the /api/authentication REST call in AuthProviderService (the class that implements the AuthenticationProvider from Spring Security).  In addition, it has an H2 database containing 2 users (username: admin ; pass: admin / username: tomcat ; pass: tomcat).  The api/user REST call in  UserController returns the user data, if the user is already aauthenticated.

The verification is done in the Filter (JwtAuthenticationTokenFilter).  It filters every request different from  /api/authentication and api/user. If a correct token isn't found an 401 http response is returned.  If a correct token is found, the Authentication object is added to the Spring Security Context  and can be used in any REST endpoint (as shown in UserController).

The JWT signing is done by an excellent Java JWT library (https://github.com/jwtk/jjwt).


### Client Side: ReactJS

The simple React app shows a login page. On successful login it, the user is redirected to the main page which shows his username and token.


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


### References

[1] https://jwt.io/introduction/

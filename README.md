# ΒoatApp
An app with SpringBoot,AngularJS and Bootstrap


# Deployment

1) gradle build
2) java -jar build/libs/boatApp-master-0.0.1-SNAPSHOT.jar
3) open on your browser http://localhost:8090/#/

# Technology used
SpringBoot
AngularJS
Bootstrap

# Authentication
For Authentication I used UUID token which  I keep it in  a ConcurrentHashMap.
User get this token when he login and then in every call he should send it in his header call.


# Database
I used a mock database in order to be more easy to deploy.
But I used Jpa repository as I will do if I use a normal database more example how to connect to database there are in this project https://github.com/seintaridis/kinderplan-1


# Login
For login there is a user in the system
username: seinta
password: 123

But you can also signup your user and connect with his credentials after.


# Demo data
After deployment there are in the system two sample boats and a user.

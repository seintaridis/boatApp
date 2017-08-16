# boatApp
An app with SpringBoot and AngularJS


# deployment

1) gradle build
2) java -jar build/libs/boatApp-master-0.0.1-SNAPSHOT.jar
3) open on your browser http://localhost:8090/#/

# Technology used
SpringBoot
AngularJS
Bootstrap

#Authentication
For Authentication I used UUID token which  I keep it in  a ConcurrentHashMap.
User get this token when he login and then in every call he should send it in his header call.

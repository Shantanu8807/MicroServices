#from command ensures to get all the required depencency before running the docker image
FROM openjdk:17-jdk-slim

#who is maintianing the image 
MAINTAINER shantanu

#location of the jar 
COPY target/account-0.0.1-SNAPSHOT.jar account-0.0.1-SNAPSHOT.jar

#command to run a jar 
ENTRYPOINT [ "java","-jar","account-0.0.1-SNAPSHOT.jar" ]


#To create docker image docker build . -t shantanu7088/accounts:v1 from cmd fro the accounts microsrvice filder 

#to run docker image command is docker run -d -p 8080:8080 shantanu7088/accounts:v1
#this command will craete a container and by running this command multiple times we can have different container but the port number should be different


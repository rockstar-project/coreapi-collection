FROM ibmcom/ibmjava
MAINTAINER Rockstar Team

VOLUME /tmp
ADD target/coreapi-collection-1.0.0-SNAPSHOT.jar coreapi-collection.jar
RUN bash -c 'touch /coreapi-collection.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/coreapi-collection.jar"]

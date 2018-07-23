FROM openjdk
VOLUME /tmp

ADD target/MDEDefinitions-0.0.1-1.jar ./MDEDefinitions.jar

RUN sh -c 'touch ./MDEDefinitions.jar'
ENTRYPOINT ["java","-Dspring.profiles.active=DOCKER","-Djava.security.egd=file:/dev/./urandom","-jar","./MDEDefinitions.jar"]
# Set java 8 base image
FROM java:8

# File Author / Maintainer
MAINTAINER Arnaud Zdziobeck <laloutre42@gmail.com>

VOLUME /tmp

ADD target/foodrecipes-server*.jar app.jar
CMD bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
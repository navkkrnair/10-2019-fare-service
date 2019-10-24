FROM openjdk:8-jre-alpine
MAINTAINER "navkkrnair@gmail.com"
#Alpine docker image doesn't have bash installed by default. 
#You will need to add following commands to get bash:
RUN apk add --no-cache bash
# Adding curl
RUN apk --no-cache add curl
ENV APPROOT="/app"
WORKDIR $APPROOT 
COPY target/fare-service-2.0.jar $APPROOT
EXPOSE 8080
ENTRYPOINT ["java"]
CMD ["-jar","-Xmx512m","-Xms512m","-Djava.security.egd=file:/dev/./urandom", "fare-service-2.0.jar"]

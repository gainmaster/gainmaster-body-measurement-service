FROM bachelorthesis/java:latest
MAINTAINER Tony Hesjevik <tony@hesjevik.no>

COPY ./ /srv/http/gainmaster-body-measurement-service

WORKDIR /srv/http/gainmaster-body-measurement-service

RUN ["./gradlew", "build"]

CMD ["java", "-jar", "./build/libs/gainmaster-body-measurement-service-0.1.0.war"]

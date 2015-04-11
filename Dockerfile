FROM bachelorthesis/java:latest
MAINTAINER Tony Hesjevik <tony@hesjevik.no>

COPY ./ /srv/http/gainmaster-user-measurement-service

WORKDIR /srv/http/gainmaster-user-measurement-service

CMD ["./gradlew", "bootRun"]

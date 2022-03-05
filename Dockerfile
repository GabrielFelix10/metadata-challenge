FROM openjdk:11
WORKDIR /usr/src/metadata
LABEL maintainer="gafelix10@gmail.com"
COPY build/libs/class-registration-0.0.1-SNAPSHOT.jar /usr/src/metadata
EXPOSE 9094
CMD ["java", "-jar", "class-registration-0.0.1-SNAPSHOT.jar"]

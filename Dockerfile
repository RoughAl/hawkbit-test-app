FROM nfnty/arch-jre8
MAINTAINER docker-maven-plugin@jolokia.org
ENV SPRING_PROFILES_ACTIVE=docker
EXPOSE 5683 5684 8082
ENTRYPOINT ["java","-jar","target/hawkbit-test-app-0.0.1-SNAPSHOT.jar"]

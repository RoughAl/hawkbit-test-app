FROM nfnty/arch-jre8
MAINTAINER docker-maven-plugin@jolokia.org
SPRING_PROFILES_ACTIVE=docker
EXPOSE 5683 5684 8082
COPY maven /maven/
ENTRYPOINT ["java","-jar","maven/hawkbit-test-app-0.0.1-SNAPSHOT.jar"]

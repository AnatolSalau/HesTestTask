FROM openjdk:21

COPY build/libs .

EXPOSE 8081
EXPOSE 8087

ENTRYPOINT ["java", "-jar", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8087", "HesTestTask.jar"]
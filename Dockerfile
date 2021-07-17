FROM openjdk:11-jre

LABEL Name="PeopleFlow"
LABEL Description="WorkMotion PeopleFlow global HR platform"
LABEL Maintainer="mahmoudragab726@gmail.com"
LABEL Url="https://github.com/MahmoudRagab726/peopleflow"
COPY ./target/peopleflow*.jar peopleflow.jar

EXPOSE 9090

HEALTHCHECK --start-period=60s \
  CMD curl -f http://localhost:9090/actuator/health || exit 1

USER 1001

ENTRYPOINT ["java","-jar","/peopleflow.jar"]

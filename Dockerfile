FROM openjdk:8
COPY target/person-project.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar", "--db.host=${DB_HOST:mongodb}"]
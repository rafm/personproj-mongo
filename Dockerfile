FROM openjdk:8
COPY target/personproj.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar", "--db.host=${DB_HOST:mongodb}", "--elasticsearch.host=${ELASTICSEARCH_HOST:localhost}"]
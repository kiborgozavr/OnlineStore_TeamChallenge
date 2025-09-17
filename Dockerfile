FROM bellsoft/liberica-runtime-container:jre-21-slim-musl
VOLUME /tmp
ARG JAR_FILE=core/target/core-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

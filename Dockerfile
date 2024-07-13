FROM openjdk

WORKDIR /DEV/bookstore

COPY target/jpa-0.0.1-SNAPSHOT.jar /app/jpa-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "jpa-0.0.1-SNAPSHOT.jar"]
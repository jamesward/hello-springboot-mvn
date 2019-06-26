FROM adoptopenjdk/openjdk8 as builder

WORKDIR /app
COPY . /app

RUN ./mvnw package spring-boot:repackage

FROM adoptopenjdk/openjdk8:jre

COPY --from=builder /app/target/*.jar /hello-springboot.jar

CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/hello-springboot.jar"]

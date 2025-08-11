ARG MAVEN_VERSION='3.8.8-amazoncorretto-21-al2023'
ARG JDK_VERSION='21.0.5'

FROM maven:${MAVEN_VERSION} as build

WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

FROM amazoncorretto:${JDK_VERSION}

WORKDIR /app

COPY --from=build ./build/target/*jar ./app.jar

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE='docker'

ENTRYPOINT exec java -jar app.jar

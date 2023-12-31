FROM azul/zulu-openjdk:18 as builder
WORKDIR /build
COPY . .
RUN ./gradlew build -x test

FROM azul/zulu-openjdk:18
COPY --from=builder /build/libs/challenge.jar /app/challenge.jar
EXPOSE 8080

CMD ["java", "-jar", "/app/challenge.jar"]

FROM adoptopenjdk/openjdk8:latest
LABEL Description="TexoIt Test" Vender="Jemerson" Version="1.0"

RUN mkdir /app
COPY target/avaliacao-texoit-0.0.1-SNAPSHOT.jar /app

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "avaliacao-texoit-0.0.1-SNAPSHOT.jar"]

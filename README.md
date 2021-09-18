# Getting Started

[![CI - build / test / deploy heroku](https://github.com/jfnandopr/teste-texoit/actions/workflows/pipeline.yml/badge.svg?branch=main)](https://github.com/jfnandopr/teste-texoit/actions/workflows/pipeline.yml)

[![CD - Deploy to Amazon ECS](https://github.com/jfnandopr/teste-texoit/actions/workflows/aws.yml/badge.svg)](https://github.com/jfnandopr/teste-texoit/actions/workflows/aws.yml)

## Build Project

Executar instrução:

```
./mvnw package -DskipTests=true
```

## Run Tests

Executar instrução:

```
./mvnw verify
```


## Run Project

Executar instrução:

```
java -jar target/avaliacao-texoit-0.0.1-SNAPSHOT.jar
```

Atenção: O arquivo csv que é importado está na pasta `src/main/resources/input`.


### Application API

Acessar[http://localhost:8080/api/movies/awards](http://localhost:8080/api/movies/awards)

### Database access

[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

`JDBC URL`: jdbc:h2:mem:texoit



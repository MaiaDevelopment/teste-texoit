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

Acessar[https://textoit-test.herokuapp.com/api/movies/awards](https://textoit-test.herokuapp.com/api/movies/awards)

Localmente pode ser acesso pelo endereço `localhost:8080`

### Database access

[https://textoit-test.herokuapp.com/h2-console](https://textoit-test.herokuapp.com/h2-console)

`JDBC URL`: jdbc:h2:mem:texoit



# Getting Started

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

Acessar http://localhost:8080/api/movies/awards

### Database access

http://localhost:8080/h2-console

`JDBC URL`: jdbc:h2:mem:texoit


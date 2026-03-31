# Etapa 1: usar uma imagem com JDK para compilar
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app
COPY . .

# Compila o projeto e gera o .jar
RUN ./mvnw clean package -DskipTests

# Etapa 2: usar uma imagem mais leve só com JRE
FROM eclipse-temurin:17-jre

WORKDIR /app
COPY --from=build /app/target/cake-0.0.1-SNAPSHOT.jar app.jar

# Porta padrão do Spring Boot
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java","-jar","app.jar"]
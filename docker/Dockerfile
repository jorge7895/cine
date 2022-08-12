# Imagen de maven con sistema operativo.
FROM maven:3.8.6-eclipse-temurin-11-focal AS construir

# Copiado del proyecto y compilado.
ADD ./componentes/pom.xml pom.xml
ADD ./componentes/src src/
RUN mvn -P prod clean package

# Generacion del sistema operacion y actualizacion de este.
FROM eclipse-temurin:11-focal
RUN apt-get update \
    && apt-get upgrade -y
    
# Compilado en el docker.
COPY --from=construir target/*.jar /aplicacion/aplicacion.jar

# Sincronizado de la base de datos, creacion del properties y entrado a la carpeta en el docker.
COPY ./componentes/wait-for-it.sh /aplicacion/wait-for-it.sh
RUN touch /aplicacion/application.properties
WORKDIR /aplicacion

# Exponemos el puerto y ejecutamos el comando en el docker.
EXPOSE 8080
CMD ./wait-for-it.sh database:3306 -t 60 -- java -jar aplicacion.jar

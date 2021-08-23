# ProyectoSBMQ
Proyecto springboot Rabbit MQ 
EJECUCION
windows:
  mvnw.cmd spring-boot:run
linux:
  mvnw spring-boot:run

ENVIRONMET
  URL_BD : url de la conexión a BD
  USERNAME_BD : Usuairo de la BD
  PASS_BD : Password de la BD
  DRIVER_CLASS_NAME :  
  DB_PLATFORM : 
  SERVER_RABBITMQ : Host Rabbit MQ
  
TEST

 curl --location --request GET http://localhost:8080/hit/test --header 'Accept: application/json'

 curl --location --request GET http://localhost:8080/hit --header 'Accept: application/json'
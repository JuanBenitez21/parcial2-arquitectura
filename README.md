Escriba explícitamente en el readme del aplicativo como hacer correr el aplicativo de manera local usando una base de datos mysql (con docker)
con las conexiones especificadas en el archivo application.yml

Para esto primero buscamos la informacion de la DB la cual encontramos en src/main/resources/application.properties
que nos da esta informacion
server.port=8080
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/yms
spring.datasource.username=yms_user
spring.datasource.password=yms_clave
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect

Despuyes de esto creamos un contenedor en Docker

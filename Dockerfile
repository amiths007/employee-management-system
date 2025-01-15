#docker file for springboot

FROM openjdk:21
LABEL authors="Amith"
WORKDIR /emp-mng-app
COPY target/employee.management.system.www-0.0.1-SNAPSHOT.jar /emp-mng-app/target/employee.management.system.www-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/emp-mng-app/target/employee.management.system.www-0.0.1-SNAPSHOT.jar"]
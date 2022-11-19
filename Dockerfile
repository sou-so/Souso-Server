FROM openjdk:11-jre-slim
COPY ./build/libs/*.jar app.jar
ARG JDBC_URL
ARG DB_USER
ARG DB_PWD
ARG JWT_SECRET_KEY
ARG REDIS_HOST
ARG REDIS_PORT

ENV JDBC_URL=${JDBC_URL}
ENV DB_USER=${DB_USER}
ENV DB_PWD=${DB_PWD}
ENV JWT_SECRET_KEY=${JWT_SECRET_KEY}
ENV REDIS_HOST=${REDIS_HOST}
ENV REDIS_PORT=${REDIS_PORT}

ENTRYPOINT ["java","-jar","-Dspring.datasource.url=${JDBC_URL}","-Dspring.datasource.username=${DB_USER}", "-Dspring.datasource.password=${DB_PWD}", "-Dspring.jwt.secret-key=${JWT_SECRET_KEY}", "-Dspring.redis.host=${REDIS_HOST}", "-Dspring.redis.port=${REDIS_PORT}","/app.jar"]

FROM openjdk:11-jre-slim
COPY ./build/libs/*.jar app.jar
ARG JDBC_URL
ARG DB_USER
ARG DB_PWD
ARG JWT_SECRET_KEY
ARG REDIS_HOST
ARG REDIS_PORT
ARG REDIS_PASSWORD
ARG COOLSMS_API_KEY
ARG COOLSMS_SECRET_KEY
ARG COOLSMS_PHONE_NUMBER
ARG S3_ACCESS_KEY
ARG S3_SECRET_KEY
ARG S3_BUCKET
ARG S3_REGION

ENV JDBC_URL=${JDBC_URL}
ENV DB_USER=${DB_USER}
ENV DB_PWD=${DB_PWD}
ENV JWT_SECRET_KEY=${JWT_SECRET_KEY}
ENV REDIS_HOST=${REDIS_HOST}
ENV REDIS_PORT=${REDIS_PORT}
ENV REDIS_PASSWORD=${REDIS_PASSWORD}
ENV COOLSMS_API_KEY=${COOLSMS_API_KEY}
ENV COOLSMS_SECRET_KEY=${COOLSMS_SECRET_KEY}
ENV COOLSMS_PHONE_NUMBER=${COOLSMS_PHONE_NUMBER}
ENV S3_ACCESS_KEY=${S3_ACCESS_KEY}
ENV S3_SECRET_KEY=${S3_SECRET_KEY}
ENV S3_BUCKET=${S3_BUCKET}
ENV S3_REGION=${S3_REGION}

ENTRYPOINT ["java","-jar","-Dspring.datasource.url=${JDBC_URL}","-Dspring.datasource.username=${DB_USER}", "-Dspring.datasource.password=${DB_PWD}", "-Dspring.jwt.secret-key=${JWT_SECRET_KEY}", "-Dspring.redis.host=${REDIS_HOST}", "-Dspring.redis.port=${REDIS_PORT}", "-Dspring.redis.password=${REDIS_PASSWORD}", "-Dspring.coolsms.key=${COOLSMS_API_KEY}", "-Dspring.coolsms.secret=${COOLSMS_SECRET_KEY}", "-Dspring.coolsms.phone-number=${COOLSMS_PHONE_NUMBER}", "-Dspring.cloud.aws.credentials.accessKey=${S3_ACCESS_KEY}", "-Dspring.cloud.aws.credentials.secretKey=${S3_SECRET_KEY}", "-Dspring.cloud.aws.s3.bucket=${S3_BUCKET}", "-Dspring.cloud.aws.region.static=${S3_REGION}",  "/app.jar"]

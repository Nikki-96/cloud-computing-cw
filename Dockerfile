FROM openjdk:8-alpine AS BUILD_IMAGE
ENV APP_BUILD_HOME=/root/dev/cloudComputingCW
WORKDIR ${APP_BUILD_HOME}
COPY build.gradle gradlew gradlew.bat ${APP_BUILD_HOME}/
COPY gradle gradle
## download gradle dependencies and cache them - Do not cache build time dependencies :(
#RUN ./gradlew build -x :bootJar -x test --continue
COPY src src
RUN ./gradlew build
#RUN mkdir -p ${APP_BUILD_HOME}/build/libs/dependency && (cd ${APP_BUILD_HOME}/build/libs/dependency; jar -xf ../*.jar)

FROM openjdk:8-jre-alpine
ENV APP_PROD_HOME=/opt/cloudComputingCW
RUN addgroup -g 1001 -S appuser && adduser -u 1001 -S appuser -G appuser
RUN mkdir -p ${APP_PROD_HOME} && chown -R appuser:appuser ${APP_PROD_HOME}
USER appuser
WORKDIR ${APP_PROD_HOME}
COPY --from=BUILD_IMAGE /root/dev/cloudComputingCW/build/libs/cloud-computing-cw.jar .
EXPOSE 8080
#ENTRYPOINT exec java -Djavax.net.ssl.trustStore=$TRUSTSTORE -Djavax.net.ssl.trustStorePassword=$TRUSTSTORE_PASSWORD -jar batch-api.jar
ENTRYPOINT exec java -DMYSQL_URL="jdbc:mysql://db4free.net:3306/nikkitest" -DMYSQL_USERNAME="nikki123" -DMYSQL_PASSWORD="admin1234" -DMYSQL_POOL_NAME="cloud_cw_pool" -jar cloud-computing-cw.jar
FROM adoptopenjdk/openjdk11-openj9:jdk-11.0.2.9_openj9-0.12.1-alpine as build
WORKDIR /workspace/app

COPY . .
RUN apk add maven
RUN mvn install --projects service --also-make -DskipTests
RUN rm service/target/*jar-as-dependency.jar
RUN mkdir -p service/target/dependency && (cd service/target/dependency; jar -xf ../*.jar)

FROM adoptopenjdk/openjdk11-openj9:jdk-11.0.2.9_openj9-0.12.1-alpine
VOLUME /tmp
RUN ls -la
ARG DEPENDENCY=/workspace/app/service/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.ibm.project.service.container.ContainerApplication"]
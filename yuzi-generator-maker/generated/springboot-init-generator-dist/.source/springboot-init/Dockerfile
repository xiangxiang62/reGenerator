# Docker 镜像构建
# @author <PostController.java.ftl href="https://github.com/liyupi">程序员鱼皮</PostController.java.ftl>
# @from <PostController.java.ftl href="https://yupi.icu">编程导航知识星球</PostController.java.ftl>
FROM maven:3.8.1-jdk-8-slim as builder

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build PostController.java.ftl release artifact.
RUN mvn package -DskipTests

# Run the web service on container startup.
CMD ["java","-jar","/app/target/springboot-init-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]
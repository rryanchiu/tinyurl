FROM openjdk:17-jdk-alpine

WORKDIR /app

# 将本地的 Jar 文件复制到容器的 /app 目录
COPY target/tinyurl-0.0.1-SNAPSHOT.jar /app/tinyurl-0.0.1-SNAPSHOT.jar

# 公开应用程序所需的端口
EXPOSE 8765

# 指定容器启动时要运行的命令
ENTRYPOINT ["java", "-jar", "/app/tinyurl-0.0.1-SNAPSHOT.jar","--MYSQL_USERNAME=root --MYSQL_PASSWORD=abcd1234 --MYSQL_HOST=111.229.130.92:3306 --spring.profiles.active=prod "]

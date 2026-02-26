# AGENTS.md

## Cursor Cloud specific instructions

### Overview

This is a Spring Boot 2 + MyBatis + MySQL web demo (`com.lifujian.webdemo`). It exposes a health check at `GET /isalive` and a student CRUD endpoint at `GET /student/get?id={id}` on port 8080.

### System dependencies

- **JDK 8** (`openjdk-8-jdk`): Project targets `java.version=1.8`. Set `JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64` before running Maven.
- **Maven** (`maven`): Build tool. System Maven 3.8.7 works.
- **MySQL 8.0** (`mysql-server`): Required. See startup caveats below.

### MySQL startup caveats

MySQL's `apt` post-install script fails in this container environment. After installing via `apt`, MySQL must be started manually:

```bash
sudo mkdir -p /var/run/mysqld && sudo chown mysql:mysql /var/run/mysqld
sudo mysqld --user=mysql --daemonize
sudo chmod 755 /var/run/mysqld/
```

The `chmod 755` on `/var/run/mysqld/` is required so non-root processes (the Spring Boot app) can connect via the Unix socket.

### Database setup

The schema is in `sql/sql.sql`. To initialize locally:

```bash
sudo mysql -u root <<'EOF'
CREATE DATABASE IF NOT EXISTS test;
USE test;
CREATE TABLE IF NOT EXISTS `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `age` int(11) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL DEFAULT '1970-01-01 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
INSERT INTO student (name, age, create_time) VALUES ('test_student', 28, NOW());
CREATE USER IF NOT EXISTS 'lfj'@'localhost' IDENTIFIED BY 'E@CdVp8Z28Y1SQ';
GRANT ALL PRIVILEGES ON test.* TO 'lfj'@'localhost';
FLUSH PRIVILEGES;
EOF
```

### application.properties

The JDBC URL in `src/main/resources/application.properties` must point to `localhost:3306` (not the hardcoded remote IP). The `allowPublicKeyRetrieval=true` parameter is needed for MySQL 8 caching_sha2_password auth.

### Build and run

```bash
export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
mvn clean compile        # build
mvn spring-boot:run      # run on port 8080
```

### Known issues

- The test class `WebdemoApplicationTests` uses JUnit 5 style (package-private class/method) but the project's surefire plugin runs JUnit 4. `mvn test` will fail with "class is not public" errors. This is a pre-existing issue.
- The log directory `/data/logs/webdemo/` must exist for file-based logging to work.

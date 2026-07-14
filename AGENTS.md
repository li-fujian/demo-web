# AGENTS.md

## Cursor Cloud specific instructions

### What this is
`webdemo` is a small Spring Boot 2.1.2 + MyBatis + MySQL REST demo (Java 8, Maven). It exposes:
- `GET /isalive` → returns `ok` (no DB needed)
- `GET /student/get?id={id}` → reads a `student` row from MySQL and returns JSON
Server runs on embedded Tomcat at port `8080`.

### Toolchain (installed via the update-script environment / snapshot)
- Java **8** is required — Spring Boot 2.1.2 does **not** work on the VM's default Java 21. The default `java`/`javac` alternatives are set to Java 8 and `JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64` is exported in `~/.bashrc`. If `java -version` ever shows 21, re-run `sudo update-alternatives --set java /usr/lib/jvm/java-8-openjdk-amd64/jre/bin/java` (and same for `javac`). `mvn` picks up whichever `java` is on PATH.

### MySQL (must be started each session — no systemd)
- Start with `sudo service mysql start` (not `systemctl`).
- The `test` database, `student` table (+ one seed row), and app user `webdemo` / `webdemo` are created during setup and persist in the snapshot. If they are missing, recreate the schema from `sql/sql.sql` — note that file lacks a `USE test;` statement, so run `create database`, then `USE test;`, then the `CREATE TABLE`/`INSERT`, and create the `webdemo` user with `GRANT ALL ON test.*`.

### Datasource override (do NOT rely on committed config)
`src/main/resources/application.properties` points the datasource at a remote host (`39.105.205.251`) that is not usable for local dev. The properties use a custom `test.datasource.*` prefix (bound in `BootConfig` via `@ConfigurationProperties`), not `spring.datasource.*`. Override at runtime pointing to local MySQL — e.g.:
`--test.datasource.jdbc-url="jdbc:mysql://127.0.0.1:3306/test?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8" --test.datasource.username=webdemo --test.datasource.password=webdemo`

### Logging gotcha
`logback-spring.xml` writes to the hardcoded path `/data/logs/webdemo/`. That directory must exist and be writable or the app fails at startup. It is created during setup; if missing run `sudo mkdir -p /data/logs/webdemo && sudo chown -R $(id -u) /data`.

### Build / run / test
- Build: `mvn -DskipTests package` → `target/webdemo-0.0.1-SNAPSHOT.jar`.
- Run: `java -jar target/webdemo-0.0.1-SNAPSHOT.jar <datasource overrides>` or `mvn spring-boot:run -Dspring-boot.run.arguments="<datasource overrides>"`. Verify: `curl http://localhost:8080/isalive` and `curl "http://localhost:8080/student/get?id=1"`.
- Tests: `mvn test`. Note `WebdemoApplicationTests` is a **pre-existing broken test** — it is written in JUnit 5 style (package-private class/method) but Spring Boot 2.1.2 runs it under JUnit 4 (`org.junit.Test`), producing "class is not public" / "method should be public" errors. This is a code defect, not an environment problem.
- Lint: no lint/style tooling (checkstyle/spotless/etc.) is configured in `pom.xml`.

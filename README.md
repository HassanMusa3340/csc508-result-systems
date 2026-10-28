# Departmental Result System (CSC 508, Option A)

Three-layer desktop app: JavaFX (`app.ui`) -> services (`app.service`) -> JDBC DAOs (`app.dao`) -> SQLite.

## Prerequisites
- JDK 17+ and Maven 3.8+

## Configure
```
cp src/main/resources/config.properties.example src/main/resources/config.properties
```
`config.properties` is git-ignored. Edit `db.url` if you want the database file elsewhere.
The schema is created automatically on start-up; `db.seed=true` also loads demo data from `db/seed.sql`.

## Run
```
mvn javafx:run
```

## Test
```
mvn test
```
Unit tests (service layer, no database) plus `TransactionRollbackTest`, which uses a real temporary SQLite file.

## Build the runnable JAR
```
mvn clean package
java -jar target/result-system-1.0.0.jar
```
The JAR bundles JavaFX for the operating system it was built on. Build on the same OS you will run it on.

## Layout
```
db/schema.sql, db/seed.sql          3NF schema and synthetic demo data
src/main/java/app/model             entities, Grade, GradeScale, transcript records
src/main/java/app/dao               DAO interfaces + JDBC implementations (PreparedStatement only), transactions
src/main/java/app/service           business rules, GPA/CGPA, validation
src/main/java/app/ui                JavaFX controller + app entry (no SQL, no business rules)
src/test/java                       JUnit 5 tests
docs/                               logs and retrospective templates
```

## Grading scale (edit `FiveBandGradeScale` to match your department)
70-100 A (5), 60-69 B (4), 50-59 C (3), 45-49 D (2), 40-44 E (1), 0-39 F (0).
GPA = sum(grade points x credit units) / sum(credit units).

## Atomic transaction
`ResultService.recordBatch` saves a batch of results through `JdbcTransactionRunner`
(`setAutoCommit(false)` -> writes -> `commit()`, or `rollback()` on any failure).

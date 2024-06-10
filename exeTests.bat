@echo off

@REM V1 pas de ./bin

@REM javac -classpath "lib\hamcrest-2.2.jar;lib\jacocoagent.jar;lib\jacococli.jar;lib\junit-4.13.2.jar;lib\junit-jupiter-api-5.3.2.jar;lib\junit-jupiter-engine-5.3.2.jar;lib\junit-platform-console-1.3.2.jar;lib\junit-platform-console-standalone-1.3.2.jar;lib\mysql-connector-j-8.3.0.jar;lib\mysql-connector-java-5.1.44.jar" .\src\epreuves\*.java .\src\participants\*.java .\src\sports\*.java .\src\comparateurs\*.java .\src\exceptions\*.java .\src\testsJacoco\testEquipe.java
@REM java -javaagent:lib/jacocoagent.jar=destfile=jacoco.exec -cp "src;lib/*;src/testsJacoco/testEquipe.java" org.junit.platform.console.ConsoleLauncher --scan-class-path

@REM v2 ./bin
@REM javac -d ./bin -classpath "lib/hamcrest-2.2.jar;lib/jacocoagent.jar;lib/jacococli.jar;lib/junit-4.13.2.jar;lib/junit-jupiter-api-5.3.2.jar;lib/junit-jupiter-engine-5.3.2.jar;lib/junit-platform-console-1.3.2.jar;lib/junit-platform-console-standalone-1.3.2.jar;lib/mysql-connector-j-8.3.0.jar;lib/mysql-connector-java-5.1.44.jar" ./src/epreuves/*.java ./src/participants/*.java ./src/sports/*.java ./src/comparateurs/*.java ./src/exceptions/*.java ./src/testsJacoco/*.java

@REM java -javaagent:lib/jacocoagent.jar=destfile=jacoco.exec -cp "./bin;lib/*" org.junit.platform.console.ConsoleLauncher --scan-class-path


@echo off
@REM compile .java
javac -d ./bin -classpath "lib/hamcrest-2.2.jar;lib/jacocoagent.jar;lib/jacococli.jar;lib/junit-4.13.2.jar;lib/junit-jupiter-api-5.3.2.jar;lib/junit-jupiter-engine-5.3.2.jar;lib/junit-platform-console-1.3.2.jar;lib/junit-platform-console-standalone-1.3.2.jar;lib/mysql-connector-j-8.3.0.jar;lib/mysql-connector-java-5.1.44.jar" ./src/epreuves/*.java ./src/participants/*.java ./src/sports/*.java ./src/comparateurs/*.java ./src/exceptions/*.java ./src/testsJacoco/*.java 

@REM test avec JaCoCo et JUnit
java -javaagent:lib/jacocoagent.jar=destfile=jacoco.exec -cp "./bin;lib/*" org.junit.platform.console.ConsoleLauncher --class-path "./bin" --scan-class-path --include-classname ".*test.*"
pause

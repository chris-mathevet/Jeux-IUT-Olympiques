@REM @echo off
cls
javac -d ./bin/ src/exceptions/*.java
javac -cp ./bin/ -d ./bin/ src/participants/*.java src/sports/*.java src/epreuves/*.java
javac -cp ./bin/ -d ./bin/ src/comparateurs/*.java
javac -d ./bin/ -cp ./bin/ src/executable/*.java
@REM cls
java -cp ./bin/ executable.Executable
@REM javac -cp "./lib/junit-4.13.2.jar;./bin/" -d ./bin/ src/tests/*.java
@REM java -ea -cp "./lib/hamcrest-2.2.jar;./lib/junit-4.13.2.jar;./bin" org.junit.runner.JUnitCore tests.TestEquipe
@REM java -ea -cp "./lib/hamcrest-2.2.jar;./lib/junit-4.13.2.jar;./bin" org.junit.runner.JUnitCore tests.TestEpreuveMatch
@REM java -ea -cp "./lib/hamcrest-2.2.jar;./lib/junit-4.13.2.jar;./bin" org.junit.runner.JUnitCore tests.TestSport





cls
javac -d ./bin/ src/exceptions/*.java
javac -cp ./bin/ -d ./bin/ src/participants/*.java src/sports/*.java src/epreuves/*.java
javac -cp "./lib/junit-4.13.2.jar;./bin/" -d ./bin/ src/tests/*.java
java -ea -cp "./lib/hamcrest-2.2.jar;./lib/junit-4.13.2.jar;./bin" org.junit.runner.JUnitCore tests.TestEquipe
java -ea -cp "./lib/hamcrest-2.2.jar;./lib/junit-4.13.2.jar;./bin" org.junit.runner.JUnitCore tests.TestEpreuveMatch
java -ea -cp "./lib/hamcrest-2.2.jar;./lib/junit-4.13.2.jar;./bin" org.junit.runner.JUnitCore tests.TestSport

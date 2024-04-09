cls
javac -d  .\bin -cp .\junit-4.13.2.jar *.java
java -ea -cp ".\hamcrest-2.2.jar;.\junit-4.13.2.jar;.\bin" org.junit.runner.JUnitCore TestSport
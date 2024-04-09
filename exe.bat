cls
javac -d  .\bin -cp .\lib\junit-4.13.2.jar .\src\*.java
java -ea -cp ".\lib\hamcrest-2.2.jar;.\lib\junit-4.13.2.jar;.\bin" org.junit.runner.JUnitCore TestSport
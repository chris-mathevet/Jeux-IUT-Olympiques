cls

@REM test:
javac -cp "C:\Users\marqu\OneDrive\Bureau\SAEjava\SAE-JAVA\lib\mysql-connector-java-5.1.44.jar;C:\Users\marqu\OneDrive\Bureau\SAEjava\SAE-JAVA\lib\hamcrest-2.2.jar;C:\Users\marqu\OneDrive\Bureau\SAEjava\SAE-JAVA\lib\junit-4.13.2.jar" *.java
java -ea -classpath "C:\Users\marqu\OneDrive\Bureau\SAEjava\SAE-JAVA\lib\mysql-connector-java-5.1.44.jar;C:\Users\marqu\OneDrive\Bureau\SAEjava\SAE-JAVA\lib\hamcrest-2.2.jar;C:\Users\marqu\OneDrive\Bureau\SAEjava\SAE-JAVA\lib\junit-4.13.2.jar;." org.junit.runner.JUnitCore Testdatabase

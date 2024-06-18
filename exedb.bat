@REM jdbc seul (exeutable): version 8
@REM javac -cp "C:\Users\marqu\OneDrive\Bureau\SAEjava\SAE-JAVA\lib\mysql-connector-j-8.3.0" *.java
@REM java -classpath "C:\Users\marqu\OneDrive\Bureau\SAEjava\SAE-JAVA\lib\mysql-connector-j-8.3.0.jar;." Database
cls

@REM jdbc seul (exeutable): version 5 (version du serveur car flemme de mettre a jour lol)

set chemin="C:\Julian\lib_java\javafx-sdk-22\lib" 

javac -d ./bin/ src/exceptions/*.java
javac -cp ./bin/ -d ./bin/ src/participants/*.java src/sports/*.java src/epreuves/*.java
javac -cp ./bin/ -d ./bin/ src/comparateurs/*.java
javac -d ./bin/ -cp ./bin/ src/executable/*.java
javac -d ./bin/ -cp "C:\Users\marqu\OneDrive\Bureau\SAEjava\SAE-JAVA\lib\mysql-connector-java-5.1.44.jar;./bin" src/database/*.java

java -classpath "C:\Users\marqu\OneDrive\Bureau\SAEjava\SAE-JAVA\lib\mysql-connector-java-5.1.44.jar;./bin" database.ExeTest

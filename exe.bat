@REM @echo off

@REM A changé pour chaque PC
@REM Chris : "C:/localApps/openjfx/lib" // 
@REM Julian : "C:\Julian\lib_java\javafx-sdk-22\lib" // 

@REM set chemin="C:/localApps/openjfx/lib" 
set chemin="C:\Julian\lib_java\javafx-sdk-22\lib"
javac -d ./bin/ src/exceptions/*.java
javac -cp ./bin/ -d ./bin/ src/participants/*.java src/sports/*.java src/epreuves/*.java
javac -cp ./bin/ -d ./bin/ src/comparateurs/*.java
javac -d ./bin/ -cp ".\lib\mysql-connector-java-5.1.44.jar;./bin" src/database/*.java
javac -d ./bin/ -cp ./bin/ src/executable/*.java
javac -d ./bin/ -cp ./bin/ --module-path %chemin% --add-modules javafx.controls,javafx.fxml src/MVC/tableClass/*.java 
javac -d ./bin/ -cp ./bin/ --module-path %chemin% --add-modules javafx.controls,javafx.fxml src/MVC/modele/*.java 
javac -d ./bin/ -cp ./bin/ --module-path %chemin% --add-modules javafx.controls,javafx.fxml src/MVC/vues/*.java src/MVC/controleur/*.java

@REM cls
@REM java -cp ./bin/ executable.Executable
java -cp "./bin/;./img/autre/;.\lib\mysql-connector-java-5.1.44.jar" --module-path %chemin% --add-modules javafx.controls,javafx.fxml MVC.vues.AppliJO
@REM javac -cp "./lib/junit-4.13.2.jar;./bin/" -d ./bin/ src/tests/*.java
@REM java -ea -cp "./lib/hamcrest-2.2.jar;./lib/junit-4.13.2.jar;./bin" org.junit.runner.JUnitCore tests.TestEquipe
@REM java -ea -cp "./lib/hamcrest-2.2.jar;./lib/junit-4.13.2.jar;./bin" org.junit.runner.JUnitCore tests.TestEpreuveMatch
@REM java -ea -cp "./lib/hamcrest-2.2.jar;./lib/junit-4.13.2.jar;./bin" org.junit.runner.JUnitCore tests.TestSport
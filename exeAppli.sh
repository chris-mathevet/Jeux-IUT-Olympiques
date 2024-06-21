#!/bin/bash

# Compilation
javac -d ./bin/ src/exceptions/*.java
javac -cp ./bin -d ./bin src/participants/*.java src/sports/*.java src/epreuves/*.java
javac -cp ./bin -d ./bin src/comparateurs/*.java
javac -cp ./bin -d ./bin src/executable/*.java
javac -d ./bin -cp ./lib/mysql-connector-j-8.3.0.jar:./bin --module-path /usr/share/openjfx/lib --add-modules javafx.controls,javafx.fxml src/database/*.java src/MVC/modele/*.java src/MVC/user/*.java src/MVC/tableClass/*.java src/MVC/vues/*.java src/MVC/controleur/*.java

# # Exécution
java -cp ./bin:./img/autre:./img/pays:./img/sexe:./img/sports:./lib/mysql-connector-java-5.1.44.jar --module-path /usr/share/openjfx/lib --add-modules javafx.controls,javafx.fxml MVC.vues.AppliJO


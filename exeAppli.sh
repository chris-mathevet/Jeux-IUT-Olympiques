clear
javac -d ./bin/ src/exceptions/*.java
javac -cp ./bin/ -d ./bin/ src/participants/*.java src/sports/*.java src/epreuves/*.java
javac -cp ./bin/ -d ./bin/ src/comparateurs/*.java
javac -cp ./bin/ -d ./bin/ -cp ./bin/ src/executable/*.java
javac -cp ./bin/ -d ./bin/ --module-path /usr/share/openjfx/lib --add-modules javafx.controls,javafx.fxml src/MVC/modele/*.java
javac -cp ./bin/ -d ./bin/ --module-path /usr/share/openjfx/lib --add-modules javafx.controls,javafx.fxml src/MVC/vues/*.java src/MVC/controleur/*.java

java -cp ./bin/ --module-path /usr/share/openjfx/lib --add-modules javafx.controls,javafx.fxml MVC.vues.AppliJO

clear

javac -d ./bin/ src/exceptions/*.java
javac -cp ./bin/ -d ./bin/ src/participants/*.java src/sports/*.java src/epreuves/*.java
javac -cp ./bin/ -d ./bin/ src/comparateurs/*.java
javac -d ./bin/ -cp ./bin/ src/executable/*.java
javac -d ./bin/ -cp "/home/iut45/Etudiants/o22301304/Documents/SAE/SAE-JAVA/lib/mysql-connector-java-5.1.44.jar:./bin" src/database/*.java

echo uwu

java -classpath "/home/iut45/Etudiants/o22301304/Documents/SAE/SAE-JAVA/lib/mysql-connector-java-5.1.44.jar:./bin" database.ExeTest


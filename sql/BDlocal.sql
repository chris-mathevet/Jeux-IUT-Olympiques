-- Création des tables
CREATE TABLE PAYS (
    nomPays VARCHAR(20),
    nbMedailleOr INT,
    nbMedailleArgent INT,
    nbMedaillebronze INT,
    PRIMARY KEY (nomPays)
);

CREATE TABLE ATHLETE (
    nomAthlete VARCHAR(20),
    prenomAthlete VARCHAR(20),
    sexe CHAR,
    capaciteForce INT,
    endurance INT,
    agilite INT,
    nomPays VARCHAR(20),
    PRIMARY KEY (nomAthlete, prenomAthlete, sexe, nomPays)
);

CREATE TABLE EPREUVE (
    descriptionEpreuve VARCHAR(250),
    nomSport VARCHAR(50),
    sexe CHAR,
    PRIMARY KEY (descriptionEpreuve, sexe)
);

CREATE TABLE MANCHE (
    nomManche VARCHAR(20),
    numeroManche INT,
    descriptionEpreuve VARCHAR(250),
    sexe CHAR,
    PRIMARY KEY (descriptionEpreuve, sexe, nomManche),
    FOREIGN KEY (descriptionEpreuve, sexe) REFERENCES EPREUVE (descriptionEpreuve, sexe)
);

CREATE TABLE EQUIPE (
    nomEquipe VARCHAR(20) PRIMARY KEY
);

-- Associations
CREATE TABLE EST_CONSTITUE (
    nomPays VARCHAR(20),
    nomAthlete VARCHAR(20),
    prenomAthlete VARCHAR(20),
    sexe CHAR,
    nomEquipe VARCHAR(20),
    PRIMARY KEY (nomPays, nomAthlete, prenomAthlete, sexe, nomEquipe),
    FOREIGN KEY (nomEquipe) REFERENCES EQUIPE (nomEquipe),
    FOREIGN KEY (nomAthlete, prenomAthlete, sexe, nomPays) REFERENCES ATHLETE (nomAthlete, prenomAthlete, sexe, nomPays)
);

CREATE TABLE PARTICIPER_ATHLETE (
    resultat INT,
    nomPays VARCHAR(20),
    nomAthlete VARCHAR(20),
    prenomAthlete VARCHAR(20),
    sexe CHAR,
    nomManche VARCHAR(20),
    descriptionEpreuve VARCHAR(250),
    sexeEpreuve CHAR,
    PRIMARY KEY (nomAthlete, prenomAthlete, sexe, nomPays, descriptionEpreuve, nomManche, sexeEpreuve),
    FOREIGN KEY (descriptionEpreuve, sexeEpreuve, nomManche) REFERENCES MANCHE (descriptionEpreuve, sexe, nomManche),
    FOREIGN KEY (nomAthlete, prenomAthlete, sexe, nomPays) REFERENCES ATHLETE (nomAthlete, prenomAthlete, sexe, nomPays)
);

CREATE TABLE PARTICIPER_EQUIPE (
    resultat INT,
    nomManche VARCHAR(20),
    descriptionEpreuve VARCHAR(250),
    sexeEpreuve CHAR,
    nomEquipe VARCHAR(20),
    PRIMARY KEY (descriptionEpreuve, nomManche, sexeEpreuve, nomEquipe),
    FOREIGN KEY (descriptionEpreuve, sexeEpreuve, nomManche) REFERENCES MANCHE (descriptionEpreuve, sexe, nomManche),
    FOREIGN KEY (nomEquipe) REFERENCES EQUIPE (nomEquipe)
);

CREATE TABLE USER (
    idPseudo VARCHAR(50) NOT NULL PRIMARY KEY,
    mdp INT NOT NULL,
    email VARCHAR(250),
    type ENUM('visiteur', 'admin', 'organisateur') NOT NULL
);

CREATE TABLE DRAPEAU (
    nomPays VARCHAR(50) PRIMARY KEY,
    pathImg VARCHAR(255) DEFAULT 'img/autre/erreur.png'
);

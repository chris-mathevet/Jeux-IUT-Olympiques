
-- Tableaux


CREATE TABLE PAYS (
    nomPays VARCHAR(20),
    nbMedailleOr int,
    nbMedailleArgent int,
    nbMedaillebronze int,
    PRIMARY KEY (nomPays)
);

CREATE TABLE ATHLETE (
    nomAthlete VARCHAR(20) ,
    prenomAthlete VARCHAR(20),
    sexe CHAR,
    capaciteForce INT,
    endurance INT,
    agilite INT,
    nomPays VARCHAR(20), 
    PRIMARY key (nomAthlete,prenomAthlete,sexe,nomPays) 
);

CREATE TABLE EPREUVE (
    descriptionEpreuve VARCHAR(250),
    typeEpreuve VARCHAR(20),
    nomSport VARCHAR(50), 
    sexe CHAR,
    PRIMARY KEY (descriptionEpreuve, sexe)
);

CREATE TABLE MANCHE (
    nomManche VARCHAR(20),
    numeroManche INT,
    -- cle epreuve
    descriptionEpreuve VARCHAR(250),
    sexe CHAR,

    PRIMARY KEY (descriptionEpreuve,sexe,nomManche),
    
    -- cle epreuve
    FOREIGN KEY (descriptionEpreuve) REFERENCES EPREUVE (descriptionEpreuve),
    FOREIGN KEY (sexe) REFERENCES EPREUVE (sexe)
);

CREATE TABLE EQUIPE (
    nomEquipe VARCHAR(20) PRIMARY KEY
);

-- Associations

CREATE TABLE EST_CONSTITUE (
    -- cle Athlete
    nomPays VARCHAR(20), 
    nomAthlete VARCHAR(20) ,
    prenomAthlete VARCHAR(20),
    sexe CHAR,

    -- cle equipe
    nomEquipe VARCHAR(30),

    PRIMARY KEY (nomPays, nomAthlete, prenomAthlete, sexe, nomEquipe),
    
    -- cle equipe    
    FOREIGN KEY (nomEquipe) REFERENCES EQUIPE (nomEquipe),
    
    -- cle Athlete
    FOREIGN KEY (nomPays) REFERENCES ATHLETE (nomPays),
    FOREIGN KEY (nomAthlete) REFERENCES ATHLETE (nomAthlete),
    FOREIGN KEY (prenomAthlete) REFERENCES ATHLETE (prenomAthlete),
    FOREIGN KEY (sexe) REFERENCES ATHLETE (sexe)
);

CREATE TABLE PARTICIPER_ATHLETE (

    resultat INT,

    -- cle Athlete
    nomPays VARCHAR(20), 
    nomAthlete VARCHAR(20) ,
    prenomAthlete VARCHAR(20),
    sexe CHAR,
    -- cle manche
    nomManche VARCHAR(20),
    descriptionEpreuve VARCHAR(250),
    sexeEpreuve CHAR,

    PRIMARY KEY (nomAthlete, prenomAthlete, sexe, nomPays, descriptionEpreuve, nomManche,sexeEpreuve),
    
    -- cle manche
    FOREIGN KEY (sexeEpreuve) REFERENCES MANCHE (sexe),
    FOREIGN KEY (descriptionEpreuve) REFERENCES MANCHE (descriptionEpreuve),
    FOREIGN KEY (nomManche) REFERENCES MANCHE (nomManche),

    -- cle Athlete
    FOREIGN KEY (nomPays) REFERENCES ATHLETE (nomPays),
    FOREIGN KEY (nomAthlete) REFERENCES ATHLETE (nomAthlete),
    FOREIGN KEY (prenomAthlete) REFERENCES ATHLETE (prenomAthlete),
    FOREIGN KEY (sexe) REFERENCES ATHLETE (sexe)
);

CREATE TABLE PARTICIPER_EQUIPE (
    
    resultat INT,

    -- cle manche
    nomManche VARCHAR(20),
    descriptionEpreuve VARCHAR(250),
    sexeEpreuve CHAR,
    -- cle equipe
    nomEquipe VARCHAR(30),


    PRIMARY KEY (descriptionEpreuve, nomManche,sexeEpreuve, nomEquipe),

    
    -- cle manche
    FOREIGN KEY (sexeEpreuve) REFERENCES MANCHE (sexe),
    FOREIGN KEY (descriptionEpreuve) REFERENCES MANCHE (descriptionEpreuve),
    FOREIGN KEY (nomManche) REFERENCES MANCHE (nomManche)

);

create table USER(
    idPseudo VARCHAR(50) NOT NULL PRIMARY KEY,
    mdp INT NOT NULL,
    email VARCHAR(250),
    type ENUM('visiteur', 'admin', 'organisateur') NOT NULL
);

    create table DRAPEAU(
        nomPays VARCHAR(50) PRIMARY KEY,
        pathImg varchar(255) DEFAULT 'img/autre/erreur.png'
    );



-- Tableaux


CREATE TABLE PAYS (
    idPays INT PRIMARY KEY,
    nomPays VARCHAR(20)
);

CREATE TABLE SPORT (
    idSport INT,
    nomSport VARCHAR(20),
    nbParEquipe INT
);

CREATE TABLE ATHLETE (
    idAthlete INT PRIMARY KEY,
    nomAthlete VARCHAR(20),
    prenomAthlete VARCHAR(20),
    sexe CHAR,
    capaciteForce INT,
    endurance INT,
    agilite INT,
    idPays INT, 
    FOREIGN KEY (idPays) REFERENCES PAYS (idPays)
);

CREATE TABLE EPREUVE (
    -- idEpreuve INT PRIMARY KEY,
    idEpreuve INT,
    descriptionEpreuve VARCHAR(250),
    typeEpreuve VARCHAR(20),
    idSport int, 
    PRIMARY KEY (idEpreuve),
    FOREIGN KEY (idSport) REFERENCES SPORT (idSport)

    -- FOREIGN KEY (idSport) REFERENCES SPORT (idSport)
);

CREATE TABLE MATCH_TABLE (
    idMatch INT PRIMARY KEY,
    typeMatch VARCHAR(20),
    idEpreuve INT,
    FOREIGN KEY (idEpreuve) REFERENCES EPREUVE (idEpreuve)
);

CREATE TABLE EQUIPE (
    idEquipe INT PRIMARY KEY,
    nomEquipe VARCHAR(20)
);

-- Associations

CREATE TABLE EST_CONSTITUER (
    idEquipe INT,
    idAthlete INT,
    PRIMARY KEY (idEquipe, idAthlete),
    FOREIGN KEY (idEquipe) REFERENCES EQUIPE (idEquipe),
    FOREIGN KEY (idAthlete) REFERENCES ATHLETE (idAthlete)
);

CREATE TABLE PARTICIPER_ATHLETE (
    idMatch INT,
    idAthlete INT,
    resultat INT,
    PRIMARY KEY (idMatch, idAthlete),
    FOREIGN KEY (idMatch) REFERENCES MATCH_TABLE (idMatch),
    FOREIGN KEY (idAthlete) REFERENCES ATHLETE (idAthlete)
);
&   
CREATE TABLE PARTICIPER_EQUIPE (
    idMatch INT,
    idEquipe INT,
    resultat INT,
    PRIMARY KEY (idMatch, idEquipe),
    FOREIGN KEY (idMatch) REFERENCES MATCH_TABLE (idMatch),
    FOREIGN KEY (idEquipe) REFERENCES EQUIPE (idEquipe)
);

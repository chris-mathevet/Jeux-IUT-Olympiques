
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
=======
-- table
create table PAYS(
    idPays int primary key,
    nomPays varchar(20)
);
create table SPORT(
    idSport int,
    nomSport varchar(20),
    nbParEquipe int
);
create table ATHLETE(
    idAthlete int primary key,
    nomAthlete varchar(20),
    prenomAthlete varchar(20),
    sexe char,
    force int,
    endurance int,
    agilite int,
    idPays int, 
    FOREIGN KEY (idPays) REFERENCES PAYS (idPays)
);
create table EPREUVE(
    idEpreuve int primary key,
    description varchar(250),
    typeEpreuve varchar(20),
    idSport int, 
    FOREIGN KEY (idSport) REFERENCES SPORT (idSport)
);
create table MATCH(
    idMatch int primary key,
    typeMatch varchar(20),
    idEpreuve int,
    FOREIGN KEY(idEpreuve) REFERENCES EPREUVE (idEpreuve)
);
create table EQUIPE(
    idEquipe int primary key,
    nomEquipe varchar(20)
);

-- asso

create table EST_CONSTITUER(
    idEquipe int,
    idAthlete int,
    primary key(idEquipe, idAthlete),
    FOREIGN KEY (idEquipe) REFERENCES EQUIPE(idEquipe),
    FOREIGN KEY (idAthlete) REFERENCES ATHLETE(idAthlete)
);

create table PARTICIPER_ATHLETE(
    idMatch int,
    idAthlete int,
    resultat int,
    primary key(idMatch, idAthlete),
    FOREIGN KEY (idMatch) REFERENCES MATCH(idMatch),
    FOREIGN KEY (idAthlete) REFERENCES ATHLETE(idAthlete)
);

create table PARTICIPER_EQUIPE(
    idMatch int,
    idEquipe int,
    resultat int,
    primary key(idMatch, idEquipe),
    FOREIGN KEY (idMatch) REFERENCES MATCH(idMatch),
    FOREIGN KEY (idEquipe) REFERENCES EQUIPE(idEquipe)
);
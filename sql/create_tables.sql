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
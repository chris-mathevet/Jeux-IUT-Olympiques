-- table
create table SPORT(
    idPays int primary key,
    nomPays varchar(20)
);

create table ATHLETE(
    idAthlete int primary key,
    nomAthlete varchar(20),
    prenomAthlete varchar(20),
    sexeAthlete char,
    forceAthlete int,
    enduranceAthlete int,
    agiliteAthlete int,
);
create table MATCH(
    idMatch int primary key,
    typeMatch varchar(20),
    resultat int
);
create table EPREUVE(
    idEpreuve int primary key,
    description varchar(250),
    typeEpreuve varchar(20)
);
create table EQUIPE(
    idEquipe int primary key,
    nomEquipe varchar(20),
    nbParEquipe int
);

-- asso
create table ORIGINE(
    idPays int,
    idAthlete int,
    primary key(idPays, idAthlete)
    FOREIGN KEY (idPays) REFERENCES PAYS(idPays),
    FOREIGN KEY (idAthlete) REFERENCES ATHLETE(idAthlete)
);

create table AVOIR(
    idSport int,
    idEpreuve int,
    primary key(idSport, idEpreuve)
    FOREIGN KEY (idSport) REFERENCES SPORT(idSport),
    FOREIGN KEY (idEpreuve) REFERENCES EPREUVE(idEpreuve)
);

create table EQUIPEestConstitueAthlete(
    idEquipe int,
    idAthlete int,
    primary key(idEquipe, idAthlete)
    FOREIGN KEY (idPays) REFERENCES PAYS(idPays),
    FOREIGN KEY (idAthlete) REFERENCES ATHLETE(idAthlete)
);

create table PARTICIPERATHLETE(
    idMatch int,
    idAthlete int,
    primary key(idMatch, idAthlete)
    FOREIGN KEY (idMatch) REFERENCES MATCH(idMatch),
    FOREIGN KEY (idAthlete) REFERENCES ATHLETE(idAthlete)
);

create table PARTICIPEREQUIPE(
    idMatch int,
    idEquipe int,
    primary key(idMatch, idEquipe)
    FOREIGN KEY (idMatch) REFERENCES MATCH(idMatch),
    FOREIGN KEY (idEquipe) REFERENCES EQUIPE(idEquipe)
);

create table MATCHestConstitueEPREUVE(
    idMatch int,
    idEpreuve int,
    primary key(idMatch, idEquipe)
    FOREIGN KEY (idMatch) REFERENCES MATCH(idMatch),
    FOREIGN KEY (idEpreuve) REFERENCES EPREUVE(idEpreuve)
);








-- database: ../database/StarshipGame.db

DROP TABLE IF EXISTS LoginUs;
DROP TABLE IF EXISTS Score;
DROP TABLE IF EXISTS Jugador;


CREATE TABLE Jugador (
     idJugador        INTEGER PRIMARY KEY AUTOINCREMENT
    ,nombre           VARCHAR(15) NOT NULL
    ,apellido         VARCHAR(15) NOT NULL
    ,contrasena       VARCHAR(15) NOT NULL
    ,puntajeMax       INTEGER DEFAULT 0
    ,estado           VARCHAR(1) DEFAULT 'A'
    ,FechaCreacion    DATETIME NOT NULL DEFAULT (DATETIME('now', 'localtime'))
    ,FechaModifica    DATETIME DEFAULT (DATETIME('now', 'localtime'))
);


CREATE TABLE Score (
     idScore         INTEGER PRIMARY KEY AUTOINCREMENT
    ,idJugador       INTEGER NOT NULL REFERENCES Jugador (idJugador)
    ,puntaje         INTEGER DEFAULT 0
    ,estado          VARCHAR(1) DEFAULT 'A'
    ,FechaCreacion   DATETIME NOT NULL DEFAULT (DATETIME('now', 'localtime'))
    ,FechaModifica   DATETIME DEFAULT (DATETIME('now', 'localtime'))
);


CREATE TABLE LoginUs (
     idLogin         INTEGER PRIMARY KEY AUTOINCREMENT
    ,idJugador       INTEGER NOT NULL   REFERENCES Jugador (idJugador)
    ,estado          VARCHAR(1) DEFAULT 'A'
    ,FechaCreacion   DATETIME NOT NULL DEFAULT (DATETIME('now', 'localtime'))
    ,FechaModifica   DATETIME DEFAULT (DATETIME('now', 'localtime'))
);








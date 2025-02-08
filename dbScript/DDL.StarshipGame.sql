-- database: ../dataBase/a.db

DROP TABLE IF EXISTS LoginUs;
DROP TABLE IF EXISTS Score;
DROP TABLE IF EXISTS Jugador;


CREATE TABLE Jugador (
     IdJugador        INTEGER PRIMARY KEY AUTOINCREMENT
    ,Nickname         VARCHAR(15) NOT NULL
    ,Clave            VARCHAR(15) NOT NULL
    ,Estado           VARCHAR(1) DEFAULT 'A'
    ,FechaCreacion    DATETIME NOT NULL DEFAULT (DATETIME('now', 'localtime'))
    ,FechaModifica    DATETIME 
);

CREATE TABLE Score (
     IdScore         INTEGER PRIMARY KEY AUTOINCREMENT
    ,IdJugador       INTEGER NOT NULL REFERENCES Jugador (idJugador)
    ,Puntaje         INTEGER DEFAULT 0
    ,Estado          VARCHAR(1) DEFAULT 'A'
    ,FechaCreacion   DATETIME NOT NULL DEFAULT (DATETIME('now', 'localtime'))
    ,FechaModifica   DATETIME 
);


CREATE TABLE LoginUs (
     IdLogin         INTEGER PRIMARY KEY AUTOINCREMENT
    ,IdJugador       INTEGER NOT NULL   REFERENCES Jugador (idJugador)
    ,FechaCreacion   DATETIME NOT NULL DEFAULT (DATETIME('now', 'localtime'))
    ,FechaModifica   DATETIME 
    ,Estado          VARCHAR(1) DEFAULT 'A'
);

-- database: ../dataBase/StarshipGame.db

INSERT INTO Jugador (nickname, clave)
VALUES
('EthanP', 'abc123'),
('JuliaL', 'xyz456'),
('MiguelG', 'mno789'),
('MatiasM', 'pqr012'),
('LauraD', 'stu345'),
('SofíaG', 'vwx678'),
('AngelH', 'abc987'),
('ZoeS', 'efg234'),
('RaúlT', 'hij567'),
('VanesaP', 'klm890');

INSERT INTO Score (idJugador, puntaje)
VALUES
(1, 1500),
(2, 1800),
(3, 1900),
(4, 1700),
(5, 1600),
(6, 1550),
(7, 1800),
(8, 1750),
(9, 1850),
(10, 2000);

INSERT INTO LoginUs (idJugador)
VALUES
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10);

UPDATE Jugador
SET puntajeMax = 1200
WHERE idJugador = 3;

UPDATE Score
SET puntaje = 800, FechaModifica = datetime('now', 'localtime')
WHERE idJugador = 2 AND idScore = 5;

SELECT 
    j.nickname AS "Nickname Jugador", 
    j.puntajeMax AS "Puntaje Máximo",
    s.puntaje AS "Puntaje de la Última Partida", 
    s.FechaCreacion AS "Fecha de la Partida"
FROM Jugador j
JOIN Score s ON j.idJugador = s.idJugador
WHERE j.estado = 'A'
ORDER BY j.puntajeMax DESC
LIMIT 5;

CREATE VIEW ViewJugadoresPuntajes AS
SELECT 
    j.nickname AS "Nickname Jugador", 
    s.puntaje AS "Mejor Puntaje",
    j.estado AS "Estado Jugador"
FROM Jugador j
JOIN Score s ON j.idJugador = s.idJugador
WHERE j.estado = 'A'
ORDER BY s.puntaje DESC
LIMIT 5;

SELECT * FROM ViewJugadoresPuntajes;

DROP VIEW IF EXISTS ViewJugadoresPuntajes;

SELECT IdJugador, MAX(Puntaje) AS "Puntaje Record"
FROM Score
WHERE IdJugador = 1;

INSERT INTO Score (idJugador, puntaje)
VALUES
(1, 2500);
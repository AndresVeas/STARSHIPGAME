-- database: ../dataBase/StarshipGame.db

INSERT INTO Jugador (nombre, apellido, contrasena, puntajeMax, estado)
VALUES
('Ethan', 'Pérez', 'abc123', 1500, 'A'),
('Julia', 'López', 'xyz456', 1800, 'A'),
('Miguel', 'Gómez', 'mno789', 1900, 'A'),
('Matias', 'Méndez', 'pqr012', 1700, 'A'),
('Laura', 'Díaz', 'stu345', 1600, 'A'),
('Sofía', 'García', 'vwx678', 1550, 'A'),
('Angel', 'Hernández', 'abc987', 1800, 'A'),
('Zoe', 'Sánchez', 'efg234', 1750, 'A'),
('Raúl', 'Torres', 'hij567', 1850, 'A'),
('Vanesa', 'Pérez', 'klm890', 2000, 'A');

INSERT INTO Score (idJugador, puntaje, estado)
VALUES
(1, 1500, 'A'),
(2, 1800, 'A'),
(3, 1900, 'A'),
(4, 1700, 'A'),
(5, 1600, 'A'),
(6, 1550, 'A'),
(7, 1800, 'A'),
(8, 1750, 'A'),
(9, 1850, 'A'),
(10, 2000, 'A');

INSERT INTO LoginUs (idJugador, estado)
VALUES
(1, 'A'),
(2, 'A'),
(3, 'A'),
(4, 'A'),
(5, 'A'),
(6, 'A'),
(7, 'A'),
(8, 'A'),
(9, 'A'),
(10, 'A');

UPDATE Jugador
SET puntajeMax = 1200
WHERE idJugador = 3;

UPDATE Score
SET puntaje = 800, FechaModifica = datetime('now', 'localtime')
WHERE idJugador = 2 AND idScore = 5;

SELECT 
    j.nombre AS "Nombre Jugador", 
    j.apellido AS "Apellido Jugador", 
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
    j.nombre AS "Nombre Jugador", 
    j.apellido AS "Apellido Jugador", 
    j.puntajeMax AS "Puntaje Máximo",
    j.estado AS "Estado Jugador"
FROM Jugador j
WHERE j.estado = 'A';

SELECT * FROM ViewJugadoresPuntajes;

DROP VIEW IF EXISTS ViewJugadoresPuntajes;

CREATE DATABASE IF NOT EXISTS streamingdb DEFAULT CHARACTER SET = 'utf8mb4' DEFAULT COLLATE 'utf8mb4_general_ci';

USE streamingdb;

CREATE TABLE user (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone_num VARCHAR(50)
);

CREATE TABLE movie (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    director VARCHAR(50) NOT NULL,
    genre ENUM('HORROR', 'ACTION', 'COMEDY') NOT NULL,
    adults BOOLEAN NOT NULL,
    route VARCHAR(250)
);

CREATE TABLE watchlist (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    creationDate DATE NOT NULL,
    mov_count INT NOT NULL DEFAULT 0,
    id_user INT NOT NULL
);

CREATE TABLE watchlistMovie (
	id_watchlist INT NOT NULL,
    id_movie INT NOT NULL,
    PRIMARY KEY (id_watchlist, id_movie)
);

ALTER TABLE watchlist ADD CONSTRAINT fk_user FOREIGN KEY (id_user) REFERENCES user(id) ON DELETE CASCADE;
ALTER TABLE watchlistMovie ADD CONSTRAINT fk_watchlist FOREIGN KEY (id_watchlist) REFERENCES watchlist(id) ON DELETE CASCADE;
ALTER TABLE watchlistMovie ADD CONSTRAINT fk_movie FOREIGN KEY (id_movie) REFERENCES movie(id) ON DELETE CASCADE;

CREATE USER 'reto0'@'localhost' IDENTIFIED BY 'Reto0_Grupo4';

GRANT DELETE, EXECUTE, INSERT, SELECT, SHOW VIEW, UPDATE ON streamingb.* TO 'reto0'@'localhost';

FLUSH PRIVILEGES;

INSERT INTO movie (id, title, director, genre, adults, route) VALUES
(1, 'The Shining', 'Stanley Kubrick', 'HORROR', TRUE, 'src/main/java/images/the-shining.webp'),
(2, 'It', 'Andy Muschietti', 'HORROR', TRUE, 'src/main/java/images/it.webp'),
(3, 'The Conjuring', 'James Wan', 'HORROR', TRUE, 'src/main/java/images/the-conjuring.webp'),
(4, 'A Nightmare on Elm Street', 'Wes Craven', 'HORROR', TRUE, 'src/main/java/images/nightmare-on-elm-street.webp'),
(5, 'Scream', 'Wes Craven', 'HORROR', TRUE, 'src/main/java/images/scream.webp'),
(6, 'The Dark Knight', 'Christopher Nolan', 'ACTION', TRUE, 'src/main/java/images/the-dark-knight.webp'),
(7, 'Mad Max: Fury Road', 'George Miller', 'ACTION', TRUE, 'src/main/java/images/mad-max-fury-road.webp'),
(8, 'Die Hard', 'John McTiernan', 'ACTION', TRUE, 'src/main/java/images/die-hard.webp'),
(9, 'Gladiator', 'Ridley Scott', 'ACTION', TRUE, 'src/main/java/images/gladiator.webp'),
(10, 'John Wick', 'Chad Stahelski', 'ACTION', TRUE, 'src/main/java/images/john-wick.webp'),
(11, 'The Matrix', 'Lana Wachowski', 'ACTION', TRUE, 'src/main/java/images/the-matrix.webp'),
(12, 'Terminator 2', 'James Cameron', 'ACTION', TRUE, 'src/main/java/images/terminator-2.webp'),
(13, 'Mission: Impossible', 'Brian De Palma', 'ACTION', TRUE, 'src/main/java/images/mission-impossible.webp'),
(14, 'The Mask', 'Chuck Russell', 'COMEDY', FALSE, 'src/main/java/images/the-mask.webp'),
(15, 'Superbad', 'Greg Mottola', 'COMEDY', TRUE, 'src/main/java/images/superbad.webp'),
(16, 'Groundhog Day', 'Harold Ramis', 'COMEDY', FALSE, 'src/main/java/images/groundhog-day.webp'),
(17, 'Dumb and Dumber', 'Peter Farrelly', 'COMEDY', TRUE, 'src/main/java/images/dumb-and-dumber.webp'),
(18, 'Home Alone', 'Chris Columbus', 'COMEDY', FALSE, 'src/main/java/images/home-alone.webp'),
(19, 'The Hangover', 'Todd Phillips', 'COMEDY', TRUE, 'src/main/java/images/the-hangover.webp'),
(20, 'Mrs. Doubtfire', 'Chris Columbus', 'COMEDY', FALSE, 'src/main/java/images/mrs-doubtfire.webp');
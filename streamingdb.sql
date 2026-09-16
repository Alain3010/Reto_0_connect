CREATE DATABASE IF NOT EXISTS streamingdb DEFAULT CHARACTER SET = 'utf8mb4' DEFAULT COLLATE 'utf8mb4_general_ci';

USE streamingdb;

CREATE TABLE user (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
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
    mov_num INT NOT NULL DEFAULT 0,
    id_user INT NOT NULL
);

CREATE TABLE watchlistMovie (
	id_watchlist INT NOT NULL,
    id_movie INT NOT NULL,
    PRIMARY KEY (id_watchlist, id_movie)
);

ALTER TABLE watchlist ADD CONSTRAINT fk_user FOREIGN KEY (id_user) REFERENCES user(id);
ALTER TABLE watchlistMovie ADD CONSTRAINT fk_watchlist FOREIGN KEY (id_watchlist) REFERENCES watchlist(id);
ALTER TABLE watchlistMovie ADD CONSTRAINT fk_movie FOREIGN KEY (id_movie) REFERENCES movie(id);

INSERT INTO movie (id, title, director, genre, adults, route) VALUES
(1, 'The Shining', 'Stanley Kubrick', 'HORROR', TRUE, '/movies/the-shining.jpg'),
(2, 'It', 'Andy Muschietti', 'HORROR', TRUE, '/movies/it.jpg'),
(3, 'The Conjuring', 'James Wan', 'HORROR', TRUE, '/movies/the-conjuring.jpg'),
(4, 'A Nightmare on Elm Street', 'Wes Craven', 'HORROR', TRUE, '/movies/nightmare-on-elm-street.jpg'),
(5, 'Scream', 'Wes Craven', 'HORROR', TRUE, '/movies/scream.jpg'),
(6, 'The Dark Knight', 'Christopher Nolan', 'ACTION', TRUE, '/movies/the-dark-knight.jpg'),
(7, 'Mad Max: Fury Road', 'George Miller', 'ACTION', TRUE, '/movies/mad-max-fury-road.jpg'),
(8, 'Die Hard', 'John McTiernan', 'ACTION', TRUE, '/movies/die-hard.jpg'),
(9, 'Gladiator', 'Ridley Scott', 'ACTION', TRUE, '/movies/gladiator.jpg'),
(10, 'John Wick', 'Chad Stahelski', 'ACTION', TRUE, '/movies/john-wick.jpg'),
(11, 'The Matrix', 'Lana Wachowski', 'ACTION', TRUE, '/movies/the-matrix.jpg'),
(12, 'Terminator 2', 'James Cameron', 'ACTION', TRUE, '/movies/terminator-2.jpg'),
(13, 'Mission: Impossible', 'Brian De Palma', 'ACTION', TRUE, '/movies/mission-impossible.jpg'),
(14, 'The Mask', 'Chuck Russell', 'COMEDY', FALSE, '/movies/the-mask.jpg'),
(15, 'Superbad', 'Greg Mottola', 'COMEDY', TRUE, '/movies/superbad.jpg'),
(16, 'Groundhog Day', 'Harold Ramis', 'COMEDY', FALSE, '/movies/groundhog-day.jpg'),
(17, 'Dumb and Dumber', 'Peter Farrelly', 'COMEDY', TRUE, '/movies/dumb-and-dumber.jpg'),
(18, 'Home Alone', 'Chris Columbus', 'COMEDY', FALSE, '/movies/home-alone.jpg'),
(19, 'The Hangover', 'Todd Phillips', 'COMEDY', TRUE, '/movies/the-hangover.jpg'),
(20, 'Mrs. Doubtfire', 'Chris Columbus', 'COMEDY', FALSE, '/movies/mrs-doubtfire.jpg');
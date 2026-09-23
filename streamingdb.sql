DROP DATABASE IF exists streamingdb;
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

INSERT INTO movie (title, director, genre, adults, route) VALUES
('The Shining', 'Stanley Kubrick', 'HORROR', TRUE, '/movies/the-shining.jpg'),
('It', 'Andy Muschietti', 'HORROR', TRUE, '/movies/it.jpg'),
( 'The Conjuring', 'James Wan', 'HORROR', TRUE, '/movies/the-conjuring.jpg'),
( 'A Nightmare on Elm Street', 'Wes Craven', 'HORROR', TRUE, '/movies/nightmare-on-elm-street.jpg'),
( 'Scream', 'Wes Craven', 'HORROR', TRUE, '/movies/scream.jpg'),
( 'The Dark Knight', 'Christopher Nolan', 'ACTION', TRUE, '/movies/the-dark-knight.jpg'),
( 'Mad Max: Fury Road', 'George Miller', 'ACTION', TRUE, '/movies/mad-max-fury-road.jpg'),
( 'Die Hard', 'John McTiernan', 'ACTION', TRUE, '/movies/die-hard.jpg'),
( 'Gladiator', 'Ridley Scott', 'ACTION', TRUE, '/movies/gladiator.jpg'),
( 'John Wick', 'Chad Stahelski', 'ACTION', TRUE, '/movies/john-wick.jpg'),
( 'The Matrix', 'Lana Wachowski', 'ACTION', TRUE, '/movies/the-matrix.jpg'),
( 'Terminator 2', 'James Cameron', 'ACTION', TRUE, '/movies/terminator-2.jpg'),
( 'Mission: Impossible', 'Brian De Palma', 'ACTION', TRUE, '/movies/mission-impossible.jpg'),
( 'The Mask', 'Chuck Russell', 'COMEDY', FALSE, '/movies/the-mask.jpg'),
( 'Superbad', 'Greg Mottola', 'COMEDY', TRUE, '/movies/superbad.jpg'),
( 'Groundhog Day', 'Harold Ramis', 'COMEDY', FALSE, '/movies/groundhog-day.jpg'),
( 'Dumb and Dumber', 'Peter Farrelly', 'COMEDY', TRUE, '/movies/dumb-and-dumber.jpg'),
( 'Home Alone', 'Chris Columbus', 'COMEDY', FALSE, '/movies/home-alone.jpg'),
( 'The Hangover', 'Todd Phillips', 'COMEDY', TRUE, '/movies/the-hangover.jpg'),
( 'Mrs. Doubtfire', 'Chris Columbus', 'COMEDY', FALSE, '/movies/mrs-doubtfire.jpg');
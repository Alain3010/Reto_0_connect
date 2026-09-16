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
    mov_num INT NOT NULL DEFAULT 0,
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
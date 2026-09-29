/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.adt.Connect.dao;

import com.adt.Connect.model.Movie;
import com.adt.Connect.model.User;
import com.adt.Connect.model.Watchlist;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Jaime.Diaz
 */
public class WatchlistFileImplementation implements WatchlistDAO {

    @Override
    public boolean createWatchlist(Watchlist watchlist) {
        File file = new File("watchlists.dat");
        boolean exists = file.exists();

        try {
            FileOutputStream fos = new FileOutputStream(file, true);
            ObjectOutputStream oos;

            if (exists) {
                oos = new ObjectOutputStream(fos) {
                    @Override
                    protected void writeStreamHeader() throws IOException {
                        reset();
                    }
                };
            } else {
                oos = new ObjectOutputStream(fos);
            }

            oos.writeObject(watchlist);

            oos.close();
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private List<Watchlist> readAllWatchlists(File fich) {
        List<Watchlist> list = new ArrayList<>();
        if (fich == null || !fich.exists() || fich.length() == 0) {
            return list;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich))) {
            while (true) {
                list.add((Watchlist) ois.readObject());
            }
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

    private boolean writeAllWatchlists(File fich, List<Watchlist> watchlists) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fich))) {
            for (Watchlist w : watchlists) {
                oos.writeObject(w);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addMovieToWatchList(File fich, Movie movie, Integer id) {
        if (movie == null || id == null) {
            return false;
        }

        List<Watchlist> watchlists = readAllWatchlists(fich);
        if (watchlists == null) {
            return false;
        }

        for (Watchlist w : watchlists) {
            if (id.equals(w.getId())) {
                if (w.getMovies() == null) {
                    w.setMovies(new ArrayList<>());
                }

                for (Movie m : w.getMovies()) {
                    boolean sameId = movie.getId() != null && movie.getId().equals(m.getId());
                    boolean sameTitle = movie.getId() == null && movie.getTitle() != null && movie.getTitle().equalsIgnoreCase(m.getTitle());
                    if (sameId || sameTitle) {
                        return false;
                    }
                }

                w.getMovies().add(movie);
                w.setMovieCount(w.getMovies().size());
                return writeAllWatchlists(fich, watchlists);
            }
        }
        return false;
    }

    @Override
    public boolean viewUserWatchList(File fich, User user) {
        if (user == null || user.getId() == null) {
            return false;
        }

        List<Watchlist> watchlists = readAllWatchlists(fich);
        if (watchlists == null) {
            return false;
        }

        boolean found = false;
        for (Watchlist w : watchlists) {
            if (w.getUser() != null && Objects.equals(w.getUser().getId(), user.getId())) {
                found = true;
                int count = (w.getMovies() == null) ? 0 : w.getMovies().size();
                System.out.println("Watchlist: " + w.getName() + " (created " + w.getCreationDate() +
                        ", " + count + " movies)");

                if (count == 0) {
                    System.out.println("   (no movies yet)");
                } else {
                    for (Movie m : w.getMovies()) {
                        System.out.println("  - " + m.getTitle() + " | " + m.getDirector() + " | " + m.getGenre() + (m.isAdult() ? " | +18" : "")); // Cambiar este syso si eso
                    }
                }
            }
        }
        System.out.println("\n");
        return found;
    }

    @Override
    public ArrayList<Movie> viewWatchlistMovies(File file, Watchlist watchlist) {
        ArrayList<Movie> movies = null;
        boolean fileEnd = false, found = false;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            while (!fileEnd || !found) {
                try {
                    Watchlist w = (Watchlist) ois.readObject();
                    if (watchlist.getName().equalsIgnoreCase(w.getName())) {
                        movies = (ArrayList<Movie>) w.getMovies();
                        found = true;
                    }
                } catch (EOFException e) {
                    fileEnd = true;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return movies;
    }

    @Override
    public Watchlist selectWatchlist(File file, String name) {
        boolean fileEnd = false;
        boolean found = false;
        Watchlist watchlist = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            while (!fileEnd || !found) {
                try {
                    Watchlist w = (Watchlist) ois.readObject();
                    if(w.getName().equalsIgnoreCase(name)){
                        watchlist = w;
                    }
                } catch (EOFException e) {
                    fileEnd = true;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return watchlist;
    }
}

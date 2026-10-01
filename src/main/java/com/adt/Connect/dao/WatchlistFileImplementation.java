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

/**
 *
 * @author Jaime.Diaz
 */
public class WatchlistFileImplementation implements WatchlistDAO {

    private static WatchlistFileImplementation instance;

    private WatchlistFileImplementation() {
    }

    public static WatchlistFileImplementation getInstance() {
        if (instance == null) {
            instance = new WatchlistFileImplementation();
        }
        return instance;
    }

    @Override
    public boolean createWatchlist(Watchlist watchlist) {
        File file = new File("watchlists.dat");
        boolean exists = file.exists(), created;

        try {
            try (FileOutputStream fos = new FileOutputStream(file, true)) {
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
            }
            created = true;
        } catch (IOException e) {
            created = false;
        }
        return created;
    }

    private List<Watchlist> readAllWatchlists(File fich) {
        List<Watchlist> list = new ArrayList<>();
        if (fich != null || fich.exists() || fich.length() != 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich))) {
                while (true) {
                    list.add((Watchlist) ois.readObject());
                }
            } catch (EOFException e) {
            } catch (IOException | ClassNotFoundException e) {
                list = null;
            }
        }
        return list;
    }

    private boolean writeAllWatchlists(File fich, List<Watchlist> watchlists) {
        boolean writen;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fich))) {
            for (Watchlist w : watchlists) {
                oos.writeObject(w);
            }
            writen = true;
        } catch (IOException e) {
            writen = false;
        }
        return writen;
    }

    @Override
    public boolean addMovieToWatchList(File fich, Movie movie, String name) {
        boolean added = true;
        boolean sameId, sameTitle;
        List<Watchlist> watchlists;
        if (movie == null || name == null) {
            added = false;
        }
        watchlists = readAllWatchlists(fich);
        if (watchlists == null) {
            added = false;
        }
        for (Watchlist w : watchlists) {
            if (name.equals(w.getName())) {
                if (w.getMovies() == null) {
                    w.setMovies(new ArrayList<>());
                }

                for (Movie m : w.getMovies()) {
                    sameId = movie.getId() != null && movie.getId().equals(m.getId());
                    sameTitle = movie.getId() == null && movie.getTitle() != null && movie.getTitle().equalsIgnoreCase(m.getTitle());
                    if (sameId || sameTitle) {
                        added = false;
                    }
                }
                w.getMovies().add(movie);
                w.setMovieCount(w.getMovies().size());
                added = writeAllWatchlists(fich, watchlists);
            }
        }
        return added;
    }

    @Override
    public boolean viewUserWatchList(File fich, User user) {
        boolean found = false, notFound = true;
        int count;
        List<Watchlist> watchlists = readAllWatchlists(fich);
        if (user == null || user.getId() == null) {
            notFound = false;
        }

        if (watchlists == null) {
            notFound = false;
        }
        if (notFound) {
            for (Watchlist w : watchlists) {
                if (w.getUser() != null && (w.getUser().getId() == user.getId())) {
                    found = true;
                    count = (w.getMovies() == null) ? 0 : w.getMovies().size();
                    System.out.println("Watchlist: " + w.getName() + "(" + count + " movies)");

                    if (count == 0) {
                        System.out.println("   (no movies yet)");
                    } else {
                        for (Movie m : w.getMovies()) {
                            System.out.println("  - " + m.getTitle() + " | " + m.getDirector() + " | " + m.getGenre() + (m.isAdult() ? " | +18" : ""));
                        }
                    }
                }
            }
            System.out.println("\n");
        }
        return found;
    }

    @Override
    public ArrayList<Movie> viewWatchlistMovies(File file, String name) {
        selectWatchlist(file, name);
        ArrayList<Movie> movies = null;
        boolean fileEnd = false, found = false;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (!fileEnd && !found) {
                try {
                    Watchlist watchlist = (Watchlist) ois.readObject();
                    if (watchlist.getName().equalsIgnoreCase(name)) {
                        movies = (ArrayList<Movie>) watchlist.getMovies();
                        found = true;
                    }
                } catch (EOFException e) {
                    fileEnd = true;
                } catch (ClassNotFoundException e) {
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

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (!fileEnd && !found) {
                try {
                    Watchlist w = (Watchlist) ois.readObject();
                    if (w.getName().equalsIgnoreCase(name)) {
                        watchlist = w;
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
        return watchlist;
    }
}

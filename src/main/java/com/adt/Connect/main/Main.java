package com.adt.Connect.main;

import com.adt.Connect.dao.*;
import com.adt.Connect.model.*;
import com.adt.Connect.util.Utils;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int ele;
        Scanner sc = new Scanner(System.in);

        WatchlistDAO watchlistDAO = new WatchlistFileImplementation();
        File watchlistFile = new File("watchlists.dat");

        fillData(watchlistDAO, watchlistFile);

        do {
            ele = menu();
            switch (ele) {
                case 1:
                    System.out.println("\n--- Add movie ---");
                    break;

                case 2:
                    System.out.println("\n--- Add user ---");
                    break;

                case 3:
                    System.out.println("\n--- VIEW USER'S WATCHLIST ---");
                    System.out.print("Enter the name of the watchlist to view: ");
                    String wlNameToView = sc.nextLine();

                    Watchlist searchWl = new Watchlist();
                    searchWl.setName(wlNameToView);

                    ArrayList<Movie> wlMovies = watchlistDAO.viewWatchlistMovies(watchlistFile, searchWl);

                    if (wlMovies != null && !wlMovies.isEmpty()) {
                        System.out.println("Movies in '" + wlNameToView + "':");
                        for (Movie m : wlMovies) {
                            System.out.println("- " + m.getTitle());
                        }
                    } else {
                        System.out.println("Watchlist not found or has no movies.");
                    }
                    break;

                case 4:
                    System.out.println("\n--- CREATE NEW WATCHLIST ---");
                    System.out.print("Enter the new watchlist name: ");
                    String newWlName = sc.nextLine();

                    User owner = new User();
                    owner.setName("CurrentUser");

                    Watchlist newWatchlist = new Watchlist();
                    newWatchlist.setName(newWlName);
                    newWatchlist.setUser(owner);
                    newWatchlist.setMovies(new ArrayList<Movie>());

                    if (watchlistDAO.createWatchlist(newWatchlist)) {
                        System.out.println("Watchlist created successfully!");
                    } else {
                        System.out.println("Error creating watchlist.");
                    }
                    break;

                case 5:
                    System.out.println("\n--- ADD MOVIE TO WATCHLIST ---");
                    break;

                case 6:
                    System.out.println("\n--- View adult movies ---");
                    break;

                case 7:
                    System.out.println("\n--- View movies form a watchlist ---");

                    break;
                case 0:
                    System.out.println("\nSee you next time!");
                    break;

                default:
                    System.out.println("\nTry again");
                    break;
            }
        } while (ele != 0);

        sc.close();
    }

    public static int menu() {
        int ele;
        System.out.println("""
            **********************MENU**********************
            1.\tAdd movie.
            2.\tAdd user.
            3.\tView user's watchlist.
            4.\tCreate new watchlist.
            5.\tAdd movie to watchlist.
            6.\tView adult movies.
            7.\tView movies form a watchlist.               
            0.\tExit
            """);
        System.out.print("Write an option: ");
        ele = Utils.leerInt(1, 7);
        return ele;
    }

    private static void fillData(WatchlistDAO watchlistDAO, File watchlistFile) {
        if (watchlistFile.exists()) {
            System.out.println("Test data file already exists, skipping filldata.\n");
            return;
        }

        System.out.println("Generating test data...");

        User user1 = new User(1, "Alice", "1234", "alice@test.com", "600111222");
        User user2 = new User(2, "Bob", "1234", "bob@test.com", "600333444");

        Movie matrix = new Movie("the-matrix", "The Wachowskis", Genre.ACTION, false, "");
        Movie johnWick = new Movie("john-wick", "Chad Stahelski", Genre.ACTION, true, "");
        Movie theShining = new Movie("the-shining", "Stanley Kubrick", Genre.HORROR, true, "");
        Movie superbad = new Movie("superbad", "Greg Mottola", Genre.COMEDY, true, "");

        Watchlist wl1 = new Watchlist(1, "Action Night", LocalDate.now(), 0, user1);
        wl1.getMovies().add(matrix);
        wl1.getMovies().add(johnWick);
        wl1.setMovieCount(wl1.getMovies().size());

        Watchlist wl2 = new Watchlist(2, "Weekend Fun", LocalDate.now(), 0, user2);
        wl2.getMovies().add(theShining);
        wl2.getMovies().add(superbad);
        wl2.setMovieCount(wl2.getMovies().size());

        watchlistDAO.createWatchlist(wl1);
        watchlistDAO.createWatchlist(wl2);

        System.out.println("Test data generated in '" + watchlistFile.getName() + "'.\n");
    }

    public static void viewMovieFormWatchlist(File file) {
        WatchlistDAO watchlistDAO = new WatchlistFileImplementation();
        String name = null;
        Watchlist watchlist = null;
        ArrayList<Movie> movies = new ArrayList<Movie>();
        System.out.println("Insert the name of the watchlist:\n");
        name = Utils.introducirCadena();
        watchlist = watchlistDAO.selectWatchlist(file, name);
        if (watchlist != null) {
            movies = watchlistDAO.viewWatchlistMovies(file, watchlist);
            for (Movie movie : movies) {
                System.out.println(movie.toString());
                File imageFile = new File("src/main/resources/images/" + movie.getTitle() + ".webp");
                if (!imageFile.exists()) {
                    imageFile = new File("src/main/java/images/" + movie.getTitle() + ".webp");
                }
                if (imageFile.exists()) {
                    if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                        try {
                            Desktop.getDesktop().open(imageFile);
                        } catch (IOException e) {
                            System.err.println("Could not open image viewer: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Desktop operations are not supported on this environment.");
                    }
                } else {
                    System.out.println("Warning: Image file not found at: " + imageFile.getAbsolutePath());
                }
            }
        } else {
            System.out.println("The watchlist that you want doesn`t exist.");
        }

    }

}

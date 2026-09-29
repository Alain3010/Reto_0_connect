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
        String watchlistName = null;
        WatchlistDAO watchlistDAO = WatchlistFileImplementation.getInstance();
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
                    System.out.println("Insert the watchlists name ");
                    watchlistName = Utils.introducirCadena();
                    watchlistDAO.viewWatchlistMovies(watchlistFile, watchlistName);
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
        Movie johnWick = new Movie("john-wick", "Chad Stahelski", Genre.ACTION, true, "src/main/java/images/john-wick.webp");
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
}

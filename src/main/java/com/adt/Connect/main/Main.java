package com.adt.Connect.main;

import com.adt.Connect.dao.*;
import com.adt.Connect.model.*;
import com.adt.Connect.util.Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int ele;
        Scanner sc = new Scanner(System.in);

        WatchlistDAO watchlistDAO = new WatchlistFileImplementation();
        File watchlistFile = new File("watchlists.dat");

        ele = menu();
        switch (ele) {
            case 1:
                System.out.println("\n--- MOVIE LIST ---");
                break;

            case 2:
                System.out.println("\n--- USER LIST ---");
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
                System.out.println("\nSee you next time!");
                break;
        }

        sc.close();
    }

    public static int menu() {
        int ele;
        System.out.println("""
            
            **********************MENU**********************
            1.\tMovie list.
            2.\tUser list.
            3.\tView user's watchlist.
            4.\tCreate new watchlist.
            5.\tAdd movie to watchlist.
            6.\tExit
            """);
        System.out.print("Write an option: ");
        ele = Utils.leerInt(1, 6);
        return ele;
    }
}

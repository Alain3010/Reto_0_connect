package com.adt.Connect.main;

import com.adt.Connect.controller.MovieController;
import com.adt.Connect.controller.UserController;
import com.adt.Connect.controller.WatchlistController;
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
        try (Scanner sc = new Scanner(System.in)) {
            WatchlistController watchlistCont = new WatchlistController();
            MovieController movieCont = new MovieController();
            UserController userCont = new UserController();
            File watchlistFile = new File("watchlists.dat");
            fillData(watchlistCont, watchlistFile);
            do {
                ele = menu();
                switch (ele) {
                    case 1:
                        System.out.println("\n--- Add movie ---");
                        addMovie(movieCont);
                        break;

                    case 2:
                        System.out.println("\n--- Add user ---");
                        addUser(userCont);
                        break;

                    case 3:
                        System.out.println("\n--- VIEW USER'S WATCHLISTS ---");
                        viewUsersWatchlist(watchlistCont, userCont, watchlistFile);
                        break;

                    case 4:
                        System.out.println("\n--- CREATE NEW WATCHLIST ---");
                        createWatchlist(watchlistCont, userCont);
                        break;

                    case 5:
                        System.out.println("\n--- ADD MOVIE TO WATCHLIST ---");
                        addMovieToWatchlist(watchlistCont, watchlistFile, movieCont);
                        break;

                    case 6:
                        System.out.println("\n--- View adult movies ---");
                        viewAdultMovies(movieCont);
                        break;

                    case 7:
                        System.out.println("\n--- View movies form a watchlist ---");
                        viewMovieFormWatchlist(watchlistFile, sc, watchlistCont);
                        break;

                    case 0:
                        System.out.println("\nSee you next time!");
                        break;

                    default:
                        System.out.println("\nTry again");
                        break;
                }
            } while (ele != 0);
        }
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
        ele = Utils.leerInt(0, 7);
        return ele;
    }

    private static void fillData(WatchlistController watchlistCont, File watchlistFile) {
        if (watchlistFile.exists()) {
            System.out.println("Test data file already exists, skipping filldata.\n");
            return;
        }

        System.out.println("Generating test data...");

        User user1 = new User(1, "Alice", "1234", "alice@test.com", "600111222");
        User user2 = new User(2, "Bob", "1234", "bob@test.com", "600333444");

        Movie matrix = new Movie("The Matrix", "The Wachowskis", Genre.ACTION, false, "src/main/java/images/the-matrix.webp");
        Movie johnWick = new Movie("John Wick", "Chad Stahelski", Genre.ACTION, true, "src/main/java/images/john-wick.webp");
        Movie theShining = new Movie("The Shining", "Stanley Kubrick", Genre.HORROR, true, "src/main/java/images/the-shining.webp");
        Movie superbad = new Movie("Superbad", "Greg Mottola", Genre.COMEDY, true, "src/main/java/images/superbad.webp");

        Watchlist wl1 = new Watchlist(1, "Action Night", LocalDate.now(), 0, user1);
        wl1.getMovies().add(matrix);
        wl1.getMovies().add(johnWick);
        wl1.setMovieCount(wl1.getMovies().size());

        Watchlist wl2 = new Watchlist(2, "Weekend Fun", LocalDate.now(), 0, user2);
        wl2.getMovies().add(theShining);
        wl2.getMovies().add(superbad);
        wl2.setMovieCount(wl2.getMovies().size());

        watchlistCont.createWatchlist(wl1);
        watchlistCont.createWatchlist(wl2);

        System.out.println("Test data generated in '" + watchlistFile.getName() + "'.\n");
    }

    public static void viewMovieFormWatchlist(File file, Scanner sc, WatchlistController watchlistCont) {
        String name;
        Watchlist watchlist;
        ArrayList<Movie> movies;
        System.out.println("Insert the name of the watchlist:");
        name = sc.nextLine();
        watchlist = watchlistCont.selectWatchlist(file, name);
        if (watchlist != null) {
            movies = watchlistCont.viewWatchlistMovies(file, name);
            for (Movie movie : movies) {
                System.out.println(movie.toString());
                File imageFile = new File(movie.getRoute());
                if (!imageFile.exists()) {
                    imageFile = new File(movie.getRoute());
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

    public static void addMovie(MovieController movieCont) {
        Boolean adult = null;
        Genre genre = null;
        String input;
        System.out.print("Title: ");
        String title = Utils.introducirCadena();
        System.out.print("Director: ");
        String director = Utils.introducirCadena();
        while (genre == null) {
            System.out.print("Genre (HORROR, ACTION, COMEDY): ");
            try {
                genre = Genre.valueOf(Utils.introducirCadena().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid genre. Try again.");
            }
        }
        while (adult == null) {
            input = Utils.introducirCadena("yes", "no");
            if (input.equalsIgnoreCase("yes")) {
                adult = true;
            } else if (input.equalsIgnoreCase("no")) {
                adult = false;
            } else {
                System.out.println("Invalid input. Please write 'yes' or 'no'.");
            }
        }
        Movie newMovie = new Movie(title, director, genre, adult);
        if (movieCont.registerMovie(newMovie)) {
            System.out.println("Movie registered successfully.\n");
        } else {
            System.out.println("Failed to register movie. It might already exist.\n");
        }
    }

    public static void addUser(UserController userCont) {
        System.out.print("Name: ");
        String name = Utils.introducirCadena();
        System.out.print("Email: ");
        String email = Utils.introducirCadena();
        System.out.print("Password: ");
        String password = Utils.introducirCadena();
        System.out.print("Phone number: ");
        String phone = Utils.introducirCadena();

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setPhoneNumber(phone);

        if (userCont.createUser(newUser)) {
            System.out.println("User created successfully.\n");
        }
    }

    public static void viewUsersWatchlist(WatchlistController watchlistCont, UserController userCont, File watchlistFile) {
        User user = null;
        boolean foundWatchlists;
        String userName = null;
        System.out.print("Enter the Users name to search: ");
        userName = Utils.introducirCadena();
        user = userCont.checkUserName(userName);
        foundWatchlists = watchlistCont.viewUserWatchList(watchlistFile, user);
        if (!foundWatchlists) {
            System.out.println("No watchlists found for this user.\n");
        }
    }

    public static void createWatchlist(WatchlistController watchlistCont, UserController userCont) {
        User user = null;
        String userName = null;
        System.out.print("Enter the User´s name to search: ");
        userName = Utils.introducirCadena();
        user = userCont.checkUserName(userName);
        if (user != null) {
            System.out.print("Enter the new watchlist name: ");
            String newWlName = Utils.introducirCadena();
            Watchlist newWatchlist = new Watchlist();
            newWatchlist.setName(newWlName);
            newWatchlist.setUser(user);
            newWatchlist.setMovies(new ArrayList<>());

            if (watchlistCont.createWatchlist(newWatchlist)) {
                System.out.println("Watchlist created successfully!\n");
            } else {
                System.out.println("Error creating watchlist.\n");
            }
        } else {
            System.out.println("User not found");
        }
    }

    public static void addMovieToWatchlist(WatchlistController watchlistCont, File watchlistFile, MovieController movieCont) {
        String wlName;
        Watchlist watchlist;
        Movie mToAdd;
        System.out.print("Enter Watchlist Name: ");
        wlName = Utils.introducirCadena();
        watchlist = watchlistCont.selectWatchlist(watchlistFile, wlName);
        if (watchlist != null) {
            System.out.print("Enter Movie Title to add: ");
            String mTitle = Utils.introducirCadena();

            if (movieCont.checkMovie(mTitle) != null) {
                mToAdd = movieCont.checkMovie(mTitle);
                if (watchlistCont.addMovieToWatchList(watchlistFile, mToAdd, wlName)) {
                    System.out.println("Movie added to watchlist!\n");
                } else {
                    System.out.println("Error adding movie. The watchlist ID might not exist or the movie is already in the list.\n");
                }
            } else {
                System.out.println("Movie not found");
            }
        } else {
            System.out.println("Watchlist not found");
        }

    }

    public static void viewAdultMovies(MovieController movieCont) {
        ArrayList<Movie> adultMovies = movieCont.viewAdultMovies();
        if (adultMovies != null && !adultMovies.isEmpty()) {
            for (Movie m : adultMovies) {
                System.out.println("- " + m.getTitle() + " (Director: " + m.getDirector() + ")");
            }
            System.out.println("\n");
        } else {
            System.out.println("No adult movies found in the database.\n");
        }
    }
}

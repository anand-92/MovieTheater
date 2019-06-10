import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner reader = new Scanner(System.in);  // Reading from System.in

        ArrayList<Movie> movies = new ArrayList<>();
        String input = "";

        Movie Spiderman = new Movie("Spider-man", false);
        Movie Spiderman_IMAX = new Movie("Spider-man (IMAX)", true);
        movies.add(Spiderman);
        movies.add(Spiderman_IMAX);

        Movie Batman = new Movie("Batman", false);
        Movie Batman_IMAX = new Movie("Batman (IMAX)", true);
        movies.add(Batman);
        movies.add(Batman_IMAX);

        Movie MarelyAndMe = new Movie("Marely And  Me", false);
        movies.add(MarelyAndMe);

        Movie TheHaunting = new Movie("The Haunting", false);
        Movie TheHaunting_IMAX = new Movie("The Haunting (IMAX)", true);
        movies.add(TheHaunting);
        movies.add(TheHaunting_IMAX);

        Movie Guardians = new Movie("Guardians", false);
        movies.add(Guardians);


        while (true) {
            Movie.userInterface(reader, movies);
        }
        //reader.close();
    }
}

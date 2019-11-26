package Comparator;

import java.util.Collections;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("one", 45, 1970));
        movies.add(new Movie("two", 13, 1984));
        movies.add(new Movie("three", 24, 1975));
        movies.add(new Movie("four", 5, 2015));
        movies.add(new Movie("five", 9, 1998));
        movies.add(new Movie("six", 16, 2003));
        movies.add(new Movie("seven", 12, 2011));
        movies.add(new Movie("eight", 34, 2019));
        movies.add(new Movie("nine", 36, 2018));
        movies.add(new Movie("ten", 7, 2017));

        System.out.println("Sorted By rating\n=======================");
        Collections.sort(movies, new RatingCompare());
        for(Movie movie: movies){
            System.out.printf("rating: %s\t", movie.getRating());
            System.out.printf("Name: %s\t", movie.getName());
            System.out.printf("year: %s\n", movie.getYear());
        }

        System.out.println("sorted By name\n========================");
        Collections.sort(movies, new NameCompare());
        for(Movie movie: movies) {
            System.out.printf("Name: %s\t", movie.getName());
            System.out.printf("rating: %s\t", movie.getRating());
            System.out.printf("year: %s\n", movie.getYear());
        }

        System.out.println("sorted By year\n=======================");
        Collections.sort(movies);
        for(Movie movie: movies) {
            System.out.printf("year: %s\t", movie.getYear());
            System.out.printf("Name: %s\t", movie.getName());
            System.out.printf("rating: %s\n", movie.getRating());
        }
    }

}

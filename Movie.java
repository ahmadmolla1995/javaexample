package Comparable;

public class Movie implements Comparable<Movie>{
    private String name;
    private double rating;
    private int year;
    public Movie(String name, double rating, int year){
        this.name = name;
        this.rating = rating;
        this.year = year;
    }

    public int compareTo(Movie other) {
        return this.year - other.year;
    }

    public String getName(){
        return name;
    }

    public double getRating(){
        return rating;
    }

    public int getYear(){
        return year;
    }
}

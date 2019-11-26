package Comparator;

import java.util.Comparator;

public class NameCompare implements Comparator<Movie> {
    public int compare(Movie first, Movie second) {
        return first.getName().compareTo(second.getName());
    }
}

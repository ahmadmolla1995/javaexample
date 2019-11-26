package Comparator;

import java.util.Comparator;

public class RatingCompare implements Comparator<Movie> {
    public int compare(Movie first, Movie second){
        if(first.getRating() > second.getRating())
            return 1;
        else if(first.getRating() < second.getRating())
            return -1;
        return 0;
    }
}

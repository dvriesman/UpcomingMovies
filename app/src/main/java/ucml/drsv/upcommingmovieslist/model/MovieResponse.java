package ucml.drsv.upcommingmovieslist.model;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by denny on 21/12/17.
 */

public class MovieResponse {

    private int page;

    private LinkedList<Movie> results;

    DateInterval dates;

    @SerializedName("total_pages")
    private Integer totalPages;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public LinkedList<Movie> getResults() {
        return results;
    }

    public void setResults(LinkedList<Movie> results) {
        this.results = results;
    }

    public DateInterval getDates() {
        return dates;
    }

    public void setDates(DateInterval dates) {
        this.dates = dates;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }


}

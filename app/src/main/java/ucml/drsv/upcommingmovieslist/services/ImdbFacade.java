package ucml.drsv.upcommingmovieslist.services;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ucml.drsv.upcommingmovieslist.model.Genre;
import ucml.drsv.upcommingmovieslist.model.GenreResponse;
import ucml.drsv.upcommingmovieslist.model.MovieResponse;

/**
 * Created by denny on 21/12/17.
 */

public class ImdbFacade {

    private static String URL = "https://api.themoviedb.org/3";
    private static String API_KEY = "1f54bd990f1cdfb230adb312546d765d";

    private static ImdbFacade imdbFacade;

    private Imdb imdb;

    private ImdbFacade() {
        imdb = ImdbClientBuilder.createService(Imdb.class, URL);
    }

    public static ImdbFacade getInstance() {
        return imdbFacade == null ? (imdbFacade = new ImdbFacade()) : imdbFacade;
    }

    public MovieResponse listMovies(Integer page) {
        MovieResponse result =  imdb.upcomingMovies(API_KEY, page);
        return result;
    }

    public List<Genre> listGenre() {
        GenreResponse result = imdb.listGenre(API_KEY);
        return  result.getGenres();
    }


}

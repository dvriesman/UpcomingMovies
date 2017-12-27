package ucml.drsv.upcommingmovieslist.services;

import android.database.Observable;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import ucml.drsv.upcommingmovieslist.model.Genre;
import ucml.drsv.upcommingmovieslist.model.GenreResponse;
import ucml.drsv.upcommingmovieslist.model.MovieResponse;

public interface Imdb {


    @GET("/movie/upcoming")
    MovieResponse upcomingMovies(@Query("api_key") String apikey, @Query("page") Integer page);

    @GET("/genre/movie/list")
    GenreResponse listGenre(@Query("api_key") String apikey);

}

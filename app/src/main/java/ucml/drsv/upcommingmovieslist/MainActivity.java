package ucml.drsv.upcommingmovieslist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ucml.drsv.upcommingmovieslist.model.Genre;
import ucml.drsv.upcommingmovieslist.model.Movie;
import ucml.drsv.upcommingmovieslist.model.MovieResponse;
import ucml.drsv.upcommingmovieslist.services.ImdbFacade;
import ucml.drsv.upcommingmovieslist.services.DownloadTmdbImage;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Movie> movies;
    private MovieAdapter movieAdapter;
    private HashMap<Integer, String> genreMap;

    private int currentPage = 1;
    private int totalPages = 0;
    private boolean loading = false;

    private static final Integer LIST_LOADING_TRIGGER = 5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int index = -1;
        int top = -1;

        List<Movie> savedMovies = null;
        boolean hasData = (savedInstanceState != null && (savedMovies = savedInstanceState.getParcelableArrayList("movies")) != null);

        if (hasData) {
            movies = savedMovies;
            genreMap = (HashMap<Integer, String>) savedInstanceState.getSerializable("genreMap");

            currentPage = savedInstanceState.getInt("currentPage");
            totalPages = savedInstanceState.getInt("totalPages");

            top = savedInstanceState.getInt("top");
            index = savedInstanceState.getInt("index");

        } else {
            genreMap = new HashMap<>();
            GetGenre getGenre = new GetGenre();
            getGenre.execute();

            movies = new LinkedList<>();

            GetMovies getMovies = new GetMovies();
            getMovies.execute(currentPage);
        }


        movieAdapter = new MovieAdapter(this, movies);


        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(movieAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie movie  = (Movie) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(getContext(), MovieDetailActivity.class);
                Bundle params = new Bundle();
                params.putSerializable("movie", movie);
                intent.putExtras(params);

                startActivity(intent);


            }
        });

        if (index > -1 || top > -1) {
            listView.setSelectionFromTop(index, top);
        }

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //if not loading
                if (!loading) {
                    //if has more pages to load
                    if (totalPages > currentPage) {
                        //if first item visible + items on screen  + trigger (is close) to latest element
                        //load next page
                        if (firstVisibleItem + visibleItemCount + LIST_LOADING_TRIGGER > totalItemCount) {
                            loading = true;
                            GetMovies getMovies = new GetMovies();
                            getMovies.execute(currentPage+1);
                        }
                    }
                }
            }

        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("genreMap", genreMap);
        outState.putParcelableArrayList("movies", new ArrayList<Movie> (movies));
        outState.putInt("currentPage", currentPage);
        outState.putInt("totalPages",totalPages);

        int index = listView.getFirstVisiblePosition();
        View v = listView.getChildAt(0);
        int top = (v == null) ? 0 : (v.getTop() - listView.getPaddingTop());

        outState.putInt("index", index);
        outState.putInt("top", top);
    }

    private Context getContext() {
        return this;
    }


    private class GetGenre extends AsyncTask<Void, Void, List<Genre>> {
        @Override
        protected List<Genre> doInBackground(Void... voids) {
            return ImdbFacade.getInstance().listGenre();
        }

        @Override
        protected void onPostExecute(List<Genre> genres) {
            for (Genre genre : genres) {
                genreMap.put(genre.getId(),genre.getName());
            }
        }
    }

    private class GetMovies extends AsyncTask<Integer, Void, MovieResponse> {
        @Override
        protected MovieResponse doInBackground(Integer... integers) {
            MovieResponse response = ImdbFacade.getInstance().listMovies(integers[0]);
            Bitmap bitmap = null;
            for(Movie movie : response.getResults()) {
                bitmap = DownloadTmdbImage.getBitmap(movie.getPosterPath());
                if (bitmap != null) {
                    movie.setBitmap(bitmap);
                }
            }
            return response;
        }

        @Override
        protected void onPostExecute(MovieResponse movieResponse) {
            List<Movie> resultMovies = movieResponse.getResults();

            totalPages = movieResponse.getTotalPages();
            currentPage = movieResponse.getPage();

            for(Movie movie : resultMovies) {

                movie.setMovieMainGenre(genreMap.get(movie.getGenreIds() != null &&
                        movie.getGenreIds().size() > 0 ? movie.getGenreIds().get(0) : ""));

                movies.add(movie);
            }
            movieAdapter.notifyDataSetChanged();
            loading = false;
        }
    }
}

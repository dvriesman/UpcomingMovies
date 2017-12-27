package ucml.drsv.upcommingmovieslist;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import ucml.drsv.upcommingmovieslist.model.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);


        Bundle args = getIntent().getExtras();
        Movie movie = (Movie) args.getSerializable("movie");

        ImageView imageView = (ImageView) findViewById(R.id.imageId);
        imageView.setImageBitmap(movie.getBitmap());

        TextView title = (TextView) findViewById(R.id.titleId);
        title.setText(movie.getTitle());

        TextView genreTextView = (TextView) findViewById(R.id.genreId);
        genreTextView.setText(movie.getMovieMainGenre());

        TextView releaseDate = (TextView) findViewById(R.id.dateId);
        releaseDate.setText(movie.getReleaseDate());

        TextView overview = (TextView) findViewById(R.id.overviewId);
        overview.setText(movie.getOverview());


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);

    }




}

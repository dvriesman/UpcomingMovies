package ucml.drsv.upcommingmovieslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ucml.drsv.upcommingmovieslist.model.Movie;

/**
 * Created by denny on 23/12/17.
 */

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }
    
    @Override
    public int getCount() {
        return movies != null ? movies.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return movies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View movieView = LayoutInflater.from(context).inflate(R.layout.adapter_movie, viewGroup, false);
        TextView t = (TextView) movieView.findViewById(R.id.text);
        t.setText(movies.get(i).getTitle());

        TextView releaseDateTextView = (TextView) movieView.findViewById(R.id.release_date);
        releaseDateTextView.setText(movies.get(i).getReleaseDate());

        TextView mainGenreTextView = (TextView) movieView.findViewById(R.id.main_genre);
        mainGenreTextView.setText(movies.get(i).getMovieMainGenre());


        ImageView imageView = (ImageView) movieView.findViewById(R.id.image);
        if (movies.get(i).getBitmap() != null) {
            imageView.setImageBitmap(movies.get(i).getBitmap());
        }

        return movieView;
    }
}

package sleepyweasel.purplefluffernutter.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import sleepyweasel.purplefluffernutter.R;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.Configuration;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.Result;
import sleepyweasel.purplefluffernutter.rest.utils.ImageUrlBuilder;

public class MoviesSearchResultAdapter extends ArrayAdapter<Result> {

    List<Result> results;
    private Configuration configuration;

    public MoviesSearchResultAdapter(Context context, int textViewResourceId, List<Result> results, Configuration configuration) {
        super(context, textViewResourceId, results);
        this.results = results;
        this.configuration = configuration;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.movie_search_result, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        Result result = results.get(position);

        holder.titleView.setText(result.getTitle());
        holder.releaseDateView.setText(result.getReleaseDate() != null ? result.getReleaseDate().toString() : "?");
        String url = new ImageUrlBuilder().buildImageUrlFor(result, configuration);
        Picasso.with(getContext()).load(url).into(holder.posterImage);

        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.movieResultTitle) TextView titleView;
        @InjectView(R.id.movieResultReleaseDate) TextView releaseDateView;
        @InjectView(R.id.moviePoster) ImageView posterImage;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}

package sleepyweasel.purplefluffernutter;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.parceler.Parcels;

import sleepyweasel.purplefluffernutter.adapters.MoviesSearchResultAdapter;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.Configuration;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.SearchResult;


public class MoviesFromWebActivity extends ListActivity {

    SearchResult searchResult;
    MoviesSearchResultAdapter moviesSearchResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_from_web);

        searchResult = Parcels.unwrap(this.getIntent().getExtras().getParcelable(AddMovieActivity.FOUND_MOVIES_ID));
        Configuration configuration = ((PurpleFlufferNutterApplication) getApplication()).getConfiguration();
        moviesSearchResultAdapter = new MoviesSearchResultAdapter(this, R.layout.movie_search_result, searchResult.getResults(), configuration);
        setListAdapter(moviesSearchResultAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movies_from_web, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

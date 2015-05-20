package sleepyweasel.purplefluffernutter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import org.parceler.Parcels;

import sleepyweasel.purplefluffernutter.adapters.MoviesSearchResultAdapter;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.Configuration;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.SearchResult;


public class MoviesFromWebActivity extends ListActivity {

    public static final String SELECTED_MOVIE_ID = "selected_movie_id";

    SearchResult searchResult;
    MoviesSearchResultAdapter moviesSearchResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_from_web);

        searchResult = Parcels.unwrap(this.getIntent().getExtras().getParcelable(AddMovieActivity.FOUND_MOVIES_ID));
        Configuration configuration = ((PurpleFlufferNutterApplication) getApplication()).getConfiguration();
        moviesSearchResultAdapter = new MoviesSearchResultAdapter(this, R.layout.search_result_item, searchResult.getResults(), configuration);
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

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        Intent i = new Intent(getApplicationContext(), EditMovieActivity.class);
        i.putExtra(SELECTED_MOVIE_ID, Parcels.wrap(searchResult.getResults().get(position)));
        startActivity(i);
    }
}

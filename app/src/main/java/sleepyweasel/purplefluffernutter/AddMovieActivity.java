package sleepyweasel.purplefluffernutter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.Result;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.SearchResult;
import sleepyweasel.purplefluffernutter.rest.tmdb.Tmdb;

public class AddMovieActivity extends ActionBarActivity {

    public static final String MOVIE_TITLE_ID = "movie_title_id";
    public static final String FOUND_MOVIES_ID = "found_movies_id";

    @Inject
    Tmdb tmdb;

    @InjectView(R.id.titleTextField) EditText title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        ButterKnife.inject(this);
        ((PurpleFlufferNutterApplication) getApplication()).getTmdbComponent().inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_main_menu) {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            return true;
        }
        else if (id == R.id.action_settings) {
            Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.manualButton)
    @SuppressWarnings("unused")
    public void goToEdit() {
        String titleTextValue = title.getText().toString();
        if (titleTextValue.isEmpty()) {
            Toast.makeText(this, "Title cannot be empty!", Toast.LENGTH_LONG).show();
        }
        else {
            Intent i = new Intent(getApplicationContext(), EditMovieActivity.class);
            i.putExtra(AddMovieActivity.MOVIE_TITLE_ID, title.getText().toString());
            startActivity(i);
        }
    }

    @OnClick(R.id.webButton)
    @SuppressWarnings("unused")
    public void findOnWeb() {
        Log.d("Debug : ", "findOnWeb");

        tmdb.searchMovie(title.getText().toString(), new Callback<SearchResult>() {
            @Override
            public void success(SearchResult searchResult, Response response) {
                printSearchResult(searchResult);

                Intent i = new Intent(getApplicationContext(), MoviesFromWebActivity.class);
                i.putExtra(AddMovieActivity.FOUND_MOVIES_ID, Parcels.wrap(searchResult));
                startActivity(i);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Error : ", "Failed to fetch movies list.");
            }
        });
    }

    private static void printSearchResult(SearchResult searchResult) {
        Log.d("Debug : ", "Found " + searchResult.getTotalPages() + " page(s) with " + searchResult.getTotalResults() + " result(s) total.");
        Log.d("Debug : ", "Listing results from page: " + searchResult.getPage() + ".");
        for (Result result : searchResult.getResults()) {
            Log.d("Debug : ", result.toString());
        }
    }
}

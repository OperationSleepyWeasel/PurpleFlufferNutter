package sleepyweasel.purplefluffernutter;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.Result;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.SearchResult;
import sleepyweasel.purplefluffernutter.rest.tmdb.Tmdb;

public class AddMovieActivity extends ActionBarActivity {

    public static final String MOVIE_TITLE_ID = "movie_title_id";

    @Inject
    Tmdb tmdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        ButterKnife.inject(this);
        ((PurpleFlufferNutterApplication) getApplication()).getTmdbComponent().inject(this);

        //FIXME: temporary solution use async task:
        //http://stackoverflow.com/questions/19266553/android-caused-by-android-os-networkonmainthreadexception
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
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
        if (id == R.id.action_settings) {
            Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToEdit(View v) {
        Intent i = new Intent(getApplicationContext(), EditMovieActivity.class);
        i.putExtra(AddMovieActivity.MOVIE_TITLE_ID, ((EditText) findViewById(R.id.titleTextField)).getText().toString());
        startActivity(i);
    }

    @OnClick(R.id.webButton)
    @SuppressWarnings("unused")
    public void findOnWeb() {
        Log.d("Debug : ", "findOnWeb");

        SearchResult searchResult = tmdb.searchMovie("whatever");

        printSearchResult(searchResult);
    }

    private static void printSearchResult(SearchResult searchResult) {
        Log.d("Debug : ", "Found " + searchResult.getTotalPages() + " page(s) with " + searchResult.getTotalResults() + " result(s) total.");
        Log.d("Debug : ", "Listing results from page: " + searchResult.getPage() + ".");
        for (Result result : searchResult.getResults()) {
            Log.d("Debug : ", result.toString());
        }
    }
}

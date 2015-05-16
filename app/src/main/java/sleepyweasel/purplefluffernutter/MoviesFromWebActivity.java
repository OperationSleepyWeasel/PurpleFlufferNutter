package sleepyweasel.purplefluffernutter;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import sleepyweasel.purplefluffernutter.adapters.MoviesSearchResultAdapter;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.Result;


public class MoviesFromWebActivity extends ListActivity {

    List<Result> results;
    MoviesSearchResultAdapter moviesSearchResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_from_web);

        results = Arrays.asList(
                new Result("cos", new Date()),
                new Result("cos1", new Date()),
                new Result("cos2", new Date()),
                new Result("cos3", new Date())
        );

        moviesSearchResultAdapter = new MoviesSearchResultAdapter(this, R.layout.movie_search_result, results);
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

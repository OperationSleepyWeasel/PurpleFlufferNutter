package sleepyweasel.purplefluffernutter;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.parceler.Parcels;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.Result;
import sleepyweasel.purplefluffernutter.storage.MovieContentProvider;

public class EditMovieActivity extends ActionBarActivity {

    @InjectView(R.id.movie_title_value) TextView titleText;

    @InjectView(R.id.movie_year_value) TextView yearValue;

    private MovieEntry entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);
        ButterKnife.inject(this);

        Intent i = getIntent();

        String movieTitle = i.getStringExtra(AddMovieActivity.MOVIE_TITLE_ID);
        if (movieTitle != null) {
            titleText.setText(movieTitle);
        }

        if (this.getIntent().getExtras() != null) {
            Result result = Parcels.unwrap(this.getIntent().getExtras().getParcelable(MoviesFromWebActivity.SELECTED_MOVIE_ID));
            if (result != null) {
                titleText.setText(result.getTitle());
                DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy");
                yearValue.setText(formatter.print(new DateTime(result.getReleaseDate())));
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

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

    @OnClick(R.id.button_cancel)
    @SuppressWarnings("unused")
    public void goToList(View v) {
        Intent i = new Intent(getApplicationContext(), MovieListActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.button_save)
    @SuppressWarnings("unused")
    public void onClickSaveButton(View v) {
        addNewMovieToStorage();
        goToDetail();
    }

    public void addNewMovieToStorage() {
        ContentValues values = new ContentValues();
        values.put(MovieContentProvider.TITLE_COLUMN_NAME, titleText.getText().toString());
        values.put(MovieContentProvider.YEAR_COLUMN_NAME, yearValue.getText().toString());
        getContentResolver().insert(MovieContentProvider.CONTENT_URI, values);
    }

    public void goToDetail() {
        String movieTitle = titleText.getText().toString();
        int movieYear = Integer.parseInt(yearValue.getText().toString());
        entry = new MovieEntry(movieTitle, movieYear);
        Intent i = new Intent(getApplicationContext(), MovieDetailActivity.class);
        i.putExtra(MovieDetailFragment.MOVIE_ENTRY_ID, Parcels.wrap(MovieEntry.class, entry));
        startActivity(i);
    }
}

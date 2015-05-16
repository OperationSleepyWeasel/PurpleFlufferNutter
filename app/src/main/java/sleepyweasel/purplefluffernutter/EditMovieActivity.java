package sleepyweasel.purplefluffernutter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import sleepyweasel.purplefluffernutter.storage.StorageUtils;


public class EditMovieActivity extends ActionBarActivity {

    @InjectView(R.id.movie_title_value) TextView titleText;

    @InjectView(R.id.movie_year_value) TextView yearValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_movie);
        ButterKnife.inject(this);

        Intent i = getIntent();
        titleText.setText(i.getStringExtra(AddMovieActivity.MOVIE_TITLE_ID));
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
        String movieTitle = titleText.getText().toString();
        String movieYearString = yearValue.getText().toString();
        int movieYear = Integer.parseInt(movieYearString);
        MovieEntry movieEntry = new MovieEntry(movieTitle, movieYear);
        MovieEntryStorage storage = StorageUtils.getMovieStorage();
        storage.addMovie(movieEntry);
    }

    public void goToDetail() {
        Intent i = new Intent(getApplicationContext(), MovieDetailActivity.class);
        startActivity(i);
    }
}

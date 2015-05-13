package sleepyweasel.purplefluffernutter.dummy.retrofit;

import org.junit.Before;
import org.junit.Test;

import retrofit.RestAdapter;

import static org.assertj.core.api.Assertions.assertThat;

public class TmdbApiaryClientTest {
    //i am not sure how long does it last
    private static final String TMDB_APIARY_URL = "http://private-anon-975b18d78-themoviedb.apiary-mock.com/3";
    private static final String QUERY = "whatever";

    private static final Integer TOTAL_PAGES = 1;
    private static final Integer TOTAL_RESULTS = 12;

    private static final Integer FIRST_MOVIE_ID = 550;
    private static final String FIRST_MOVIE_TITLE = "Fight Club";

    private RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(TMDB_APIARY_URL).build();
    TmdbApiary tmdbApiary = restAdapter.create(TmdbApiary.class);

    @Test
    public void shouldReturnNumberOfPages() {
        assertThat(tmdbApiary.searchMovie(QUERY).getTotal_pages()).isEqualTo(TOTAL_PAGES);
    }

    @Test
    public void shouldReturnNumberOfResults() {
        assertThat(tmdbApiary.searchMovie(QUERY).getTotal_results()).isEqualTo(TOTAL_RESULTS);
    }

    @Test
    public void shouldReturnResultsFromFirstPage() {
        assertThat(tmdbApiary.searchMovie(QUERY).getPage()).isEqualTo(1);
    }

    @Test
    public void shouldReturnFirstMovie() {
        Result firstMovie = tmdbApiary.searchMovie(QUERY).getResults().get(0);

        assertThat(firstMovie.getId()).isEqualTo(FIRST_MOVIE_ID);
        assertThat(firstMovie.getTitle()).isEqualTo(FIRST_MOVIE_TITLE);
    }
}
package sleepyweasel.purplefluffernutter.rest.tmdb;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import sleepyweasel.purplefluffernutter.rest.deserializers.DateDeserializer;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.Result;

import static org.assertj.core.api.Assertions.assertThat;

public class TmdbApiaryClientTest {
    //i am not sure how long does it last
    private static final String TMDB_APIARY_URL = "http://private-anon-975b18d78-themoviedb.apiary-mock.com/3";
    private static final String QUERY = "whatever";

    private static final Integer TOTAL_PAGES = 1;
    private static final Integer TOTAL_RESULTS = 12;

    private static final Integer FIRST_MOVIE_ID = 550;
    private static final String FIRST_MOVIE_TITLE = "Fight Club";
    private static final Date FIRST_MOVIE_RELEASE_DATE = new GregorianCalendar(1999, 9, 14).getTime();

    private Gson gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .registerTypeAdapter(Date.class, new DateDeserializer())
        .create();

    private RestAdapter restAdapter = new RestAdapter.Builder()
        .setEndpoint(TMDB_APIARY_URL)
        .setConverter(new GsonConverter(gson))
        .build();

    private Tmdb tmdbApiary = restAdapter.create(Tmdb.class);

    @Test
    public void shouldReturnNumberOfPages() {
        assertThat(tmdbApiary.searchMovie(QUERY).getTotalPages()).isEqualTo(TOTAL_PAGES);
    }

    @Test
    public void shouldReturnNumberOfResults() {
        assertThat(tmdbApiary.searchMovie(QUERY).getTotalResults()).isEqualTo(TOTAL_RESULTS);
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
        assertThat(firstMovie.getReleaseDate()).isEqualTo(FIRST_MOVIE_RELEASE_DATE);
    }

    @Test
    public void shouldHandleEmptyReleaseDate() {
        Result firstMovie = tmdbApiary.searchMovie(QUERY).getResults().get(9);

        assertThat(firstMovie.getReleaseDate()).isNull();
    }

    @Test
    public void shouldHandleUnderscoreToCamelCaseConversion() {
        Result firstMovie = tmdbApiary.searchMovie(QUERY).getResults().get(0);

        assertThat(firstMovie.getOriginalTitle()).isEqualTo(FIRST_MOVIE_TITLE);
    }
}
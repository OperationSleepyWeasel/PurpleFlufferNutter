package sleepyweasel.purplefluffernutter.rest.tmdb;

import org.junit.Test;

import java.util.Date;
import java.util.GregorianCalendar;

import sleepyweasel.purplefluffernutter.components.DaggerTmdbComponent;
import sleepyweasel.purplefluffernutter.components.TmdbComponent;
import sleepyweasel.purplefluffernutter.modules.TmdbModule;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.Result;

import static org.assertj.core.api.Assertions.assertThat;

public class TmdbApiaryClientTest {
    private static final String QUERY = "whatever";

    private static final Integer TOTAL_PAGES = 1;
    private static final Integer TOTAL_RESULTS = 12;

    private static final Integer FIRST_MOVIE_ID = 550;
    private static final String FIRST_MOVIE_TITLE = "Fight Club";
    private static final Date FIRST_MOVIE_RELEASE_DATE = new GregorianCalendar(1999, 9, 14).getTime();

    TmdbComponent component = DaggerTmdbComponent.builder()
        .tmdbModule(new TmdbModule(TmdbModule.TMDB_APIARY_URL))
        .build();
    private Tmdb tmdbApiary = component.provideTmdb();

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
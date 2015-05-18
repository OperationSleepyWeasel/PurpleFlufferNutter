package sleepyweasel.purplefluffernutter.rest.utils;

import org.junit.Test;

import sleepyweasel.purplefluffernutter.rest.tmdb.domain.Configuration;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.Result;

import static org.assertj.core.api.Assertions.assertThat;

public class ImageUrlBuilderTest {

    private static final String BASE_URL = "http://image.tmdb.org/t/p/";
    private static final String POSTER_PATH = "/posterpath.png";
    private static final String EXPECTED_URL = BASE_URL + ImageUrlBuilder.DEFAULT_POSTER_SIZE + POSTER_PATH;

    private ImageUrlBuilder builder = new ImageUrlBuilder();

    @Test
    public void shouldCreatePosterUrl() {
        Result result = new Result();
        result.setPosterPath(POSTER_PATH);
        Configuration configuration = new Configuration();
        configuration.setImages(new Configuration.ImagesConfiguration());
        configuration.getImages().setBaseUrl(BASE_URL);

        String url = builder.buildImageUrlFor(result, configuration);

        assertThat(url).isEqualTo(EXPECTED_URL);
    }
}
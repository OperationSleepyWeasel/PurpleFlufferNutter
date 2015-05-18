package sleepyweasel.purplefluffernutter.rest.utils;

import sleepyweasel.purplefluffernutter.rest.tmdb.domain.Configuration;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.Result;

public class ImageUrlBuilder {

    public static final String DEFAULT_POSTER_SIZE = "w185";

    public String buildImageUrlFor(Result result, Configuration configuration) {
        //TODO: add validations (not null, size in available sizes)

        return configuration.getImages().getBaseUrl() + DEFAULT_POSTER_SIZE + result.getPosterPath();
    }
}

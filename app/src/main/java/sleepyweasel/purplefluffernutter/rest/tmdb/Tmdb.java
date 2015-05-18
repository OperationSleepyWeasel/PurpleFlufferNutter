package sleepyweasel.purplefluffernutter.rest.tmdb;

import retrofit.http.GET;
import retrofit.http.Query;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.Configuration;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.SearchResult;

public interface Tmdb {
    @GET("/search/movie")
    SearchResult searchMovie(@Query("query") String query);

    @GET("/configuration")
    Configuration getConfiguration();
}

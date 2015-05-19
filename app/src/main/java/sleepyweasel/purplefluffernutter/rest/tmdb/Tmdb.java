package sleepyweasel.purplefluffernutter.rest.tmdb;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.Configuration;
import sleepyweasel.purplefluffernutter.rest.tmdb.domain.SearchResult;

public interface Tmdb {
    //synchronous requests
    @GET("/search/movie")
    SearchResult searchMovie(@Query("query") String query);

    @GET("/configuration")
    Configuration getConfiguration();

    //asynchronous requests
    @GET("/search/movie")
    void searchMovie(@Query("query") String query, Callback<SearchResult> callback);

    @GET("/configuration")
    void getConfiguration(Callback<Configuration> callback);
}

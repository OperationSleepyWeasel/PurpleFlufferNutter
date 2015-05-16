package sleepyweasel.purplefluffernutter.dummy.retrofit;

import retrofit.http.GET;
import retrofit.http.Query;

public interface Tmdb {
    @GET("/search/movie")
    SearchResult searchMovie(@Query("query") String query);
}

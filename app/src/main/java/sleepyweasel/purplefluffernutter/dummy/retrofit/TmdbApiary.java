package sleepyweasel.purplefluffernutter.dummy.retrofit;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;

public interface TmdbApiary {
    @GET("/search/movie")
    SearchResult searchMovie(@Query("query") String query);
}

package sleepyweasel.purplefluffernutter.dummy.retrofit;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.Date;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import sleepyweasel.purplefluffernutter.dummy.retrofit.deserializers.DateDeserializer;

public class TmdbApiaryClient {
    //i am not sure how long does it last
    private static final String TMDB_APIARY_URL = "http://private-anon-975b18d78-themoviedb.apiary-mock.com/3";

    public static void main(String... args) {
        Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(Date.class, new DateDeserializer())
            .create();

        RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(TMDB_APIARY_URL)
            .setConverter(new GsonConverter(gson))
            .build();

        TmdbApiary tmdbApiary = restAdapter.create(TmdbApiary.class);

        SearchResult searchResult = tmdbApiary.searchMovie("whatever");

        printSearchResult(searchResult);
    }

    private static void printSearchResult(SearchResult searchResult) {
        System.out.println("Found " + searchResult.getTotalPages() + " page(s) with " + searchResult.getTotalResults() + " result(s) total.");
        System.out.println("Listing results from page: " + searchResult.getPage() + ".");
        for (Result result : searchResult.getResults()) {
            System.out.println(result);
        }
    }
}

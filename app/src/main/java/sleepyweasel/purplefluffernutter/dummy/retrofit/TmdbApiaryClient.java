package sleepyweasel.purplefluffernutter.dummy.retrofit;

import retrofit.RestAdapter;

public class TmdbApiaryClient {
    //i am not sure how long does it last
    private static final String TMDB_APIARY_URL = "http://private-anon-975b18d78-themoviedb.apiary-mock.com/3";

    public static void main(String... args) {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(TMDB_APIARY_URL).build();

        TmdbApiary tmdbApiary = restAdapter.create(TmdbApiary.class);

        SearchResult searchResult = tmdbApiary.searchMovie("whatever");

        printSearchResult(searchResult);
    }

    private static void printSearchResult(SearchResult searchResult) {
        System.out.println("Found " + searchResult.getTotal_pages() + " page(s) with " + searchResult.getTotal_results() + " result(s) total.");
        System.out.println("Listing results from page: " + searchResult.getPage() + ".");
        for (Result result : searchResult.getResults()) {
            System.out.println(result);
        }
    }
}

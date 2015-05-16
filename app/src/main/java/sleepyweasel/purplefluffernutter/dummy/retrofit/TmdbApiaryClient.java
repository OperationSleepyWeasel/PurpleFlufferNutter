package sleepyweasel.purplefluffernutter.dummy.retrofit;

import javax.inject.Inject;

import sleepyweasel.purplefluffernutter.components.DaggerTmdbComponent;
import sleepyweasel.purplefluffernutter.components.TmdbComponent;

public class TmdbApiaryClient {
    @Inject
    Tmdb tmdb;

    private TmdbApiaryClient() {
        TmdbComponent component = DaggerTmdbComponent.create();
        component.inject(this);
    }

    public static void main(String... args) {
        TmdbApiaryClient client = new TmdbApiaryClient();

        SearchResult searchResult = client.tmdb.searchMovie("whatever");

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
package sleepyweasel.purplefluffernutter.rest.tmdb.domain;

import org.parceler.Parcel;

import java.util.List;

@Parcel(Parcel.Serialization.BEAN)
public class SearchResult {
    private Integer page;
    private Integer totalPages;
    private Integer totalResults;

    private List<Result> results;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}

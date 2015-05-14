package sleepyweasel.purplefluffernutter.dummy.retrofit;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResult {
    private Integer page;
    private Integer totalPages;
    private Integer totalResults;

    private List<Result> results;
}

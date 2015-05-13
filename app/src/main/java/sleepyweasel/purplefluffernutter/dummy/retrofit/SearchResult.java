package sleepyweasel.purplefluffernutter.dummy.retrofit;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchResult {
    private Integer page;
    private Integer total_pages;
    private Integer total_results;

    private List<Result> results;
}

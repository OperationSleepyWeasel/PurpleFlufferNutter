package sleepyweasel.purplefluffernutter.dummy.retrofit;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Result {
    private Integer id;
    private String title;
    private String original_title;
    private String release_date;

    private Double popularity;
    private Long vote_count;
    private Double vote_average;

    private String backdrop_path;
    private String poster_path;

    private boolean adult;
    private boolean video;
}

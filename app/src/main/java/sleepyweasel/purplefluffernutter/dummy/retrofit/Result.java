package sleepyweasel.purplefluffernutter.dummy.retrofit;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Result {
    private Integer id;
    private String title;
    private String originalTitle;
    private Date releaseDate;

    private Double popularity;
    private Long voteCount;
    private Double voteAverage;

    private String backdropPath;
    private String posterPath;

    private boolean adult;
    private boolean video;
}

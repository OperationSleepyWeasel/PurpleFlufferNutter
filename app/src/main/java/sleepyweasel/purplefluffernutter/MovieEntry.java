package sleepyweasel.purplefluffernutter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
//@AllArgsConstructor
public class MovieEntry {

    private String title;

    MovieEntry(String title) {
        this.title = title;
    }
}

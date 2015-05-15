package sleepyweasel.purplefluffernutter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor(suppressConstructorProperties = true)
class MovieEntry {

    private String title;

    public String toString() {
        return title;
    }
}

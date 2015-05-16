package sleepyweasel.purplefluffernutter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor(suppressConstructorProperties = true)
class MovieEntry {
    private String title;
}

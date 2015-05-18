package sleepyweasel.purplefluffernutter;

import com.orm.SugarRecord;

import org.parceler.Parcel;

@Parcel(Parcel.Serialization.BEAN)
public class MovieEntry extends SugarRecord<MovieEntry> {
    private String title;
    private int year;

    //do not delete, required for @Parcel
    public MovieEntry() {
    }

    public MovieEntry(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieEntry entry = (MovieEntry) o;

        if (year != entry.year) return false;
        if (title != null ? !title.equals(entry.title) : entry.title != null) return false;

        return true;
    }
}

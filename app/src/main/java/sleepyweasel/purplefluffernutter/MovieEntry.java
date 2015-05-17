package sleepyweasel.purplefluffernutter;

public class MovieEntry {
    private String title;
    private int year;

    MovieEntry(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
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

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

    void setYear(String text) {
        try {
            this.year = Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            if (text.isEmpty())
                this.year = 0;
        }
    }
}

import java.util.Objects;

public class Movie {
    private String name;
    private int year;
    private String genre;
    private int duration;
    private String format;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie movie)) return false;
        return getYear() == movie.getYear() && getDuration() == movie.getDuration() && Objects.equals(getName(), movie.getName()) && Objects.equals(getGenre(), movie.getGenre()) && Objects.equals(getFormat(), movie.getFormat());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getYear(), getGenre(), getDuration(), getFormat());
    }

    public Movie(String name, int year, String genre, int duration, String format) {
        this.name = name;
        this.year = year;
        this.genre = genre;
        this.duration = duration;
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

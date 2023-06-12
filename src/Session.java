import java.util.Objects;

public class Session {
    private Cinema cinema;
    private Hall hall;

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Hall getHall() {
        return hall;
    }

    private Movie movie;
    private String time;

    public Session(Cinema cinema, Movie movie, String time, Hall hall) {
        this.cinema = cinema;
        this.movie = movie;
        this.time = time;
        this.hall = hall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Session session)) return false;
        return Objects.equals(getCinema(), session.getCinema()) && Objects.equals(getMovie(), session.getMovie()) && Objects.equals(getTime(), session.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCinema(), getMovie(), getTime());
    }

    public void sessionprint(){
        System.out.print("Кинотеатр: ");
        System.out.print(cinema.getName());
        System.out.print(", адрес: ");
        System.out.print(cinema.getAddress());
        System.out.print(", фильм: ");
        System.out.print(movie.getName());
        System.out.print(", время сеанса: ");
        System.out.println(time);
        System.out.println(hall.getType());
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }
}

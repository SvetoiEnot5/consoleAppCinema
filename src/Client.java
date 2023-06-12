public interface Client {
    double getDiscount();
    boolean auth(String login, String password);
    int getVisitCount();
}
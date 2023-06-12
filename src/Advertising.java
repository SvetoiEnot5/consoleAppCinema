public class Advertising {
    private String text;
    private int cost;
    private Session session;

    public Advertising(String text, int cost, Session session) {
        this.text = text;
        this.cost = cost;
        this.session = session;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}

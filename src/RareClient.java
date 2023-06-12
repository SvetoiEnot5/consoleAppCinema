import java.util.Objects;

public class RareClient implements Client {
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public RareClient(String FIO , String phoneNumber, String email, int money, int visitCount, String login, String password) {
        this.FIO = FIO;
        this.visitCount = visitCount;
        this.login = login;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.money = money;
        if (visitCount < 3){
            this.status = "Rare Client";
            this.discount = 1;
        } else if (visitCount >= 3 && visitCount < 7){
            this.status = "Chain friend";
            this.discount = 0.9;
        } else if (visitCount >= 7){
            this.status = "VIP Client";
            this.discount = 0.8;
        }
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
        if (visitCount < 3){
            this.status = "Rare Client";
            this.discount = 1;
        } else if (visitCount >= 3 && visitCount < 7){
            this.status = "Chain friend";
            this.discount = 0.9;
        } else if (visitCount >= 7){
            this.status = "VIP Client";
            this.discount = 0.8;
        }
    }

    public RareClient(int money){
        this.money = money;
        this.visitCount = 0;
        this.status = "Rare Client";
        this.discount = 1;
    }
    @Override
    public boolean auth(String login, String password) {
        if (Objects.equals(this.login, login) && Objects.equals(this.password, password)) {
            return true;
        }
        else {
            return false;
        }
    }
    @Override
    public int getVisitCount(){
        return this.visitCount;
    }
    private String login;
    private String password;
    private String FIO;
    private int visitCount;
    private String status;
    private double discount;

    private String phoneNumber;
    private String email;
    private int money;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public double getDiscount() {
        return discount;
    }
}

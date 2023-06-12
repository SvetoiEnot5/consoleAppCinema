import javax.swing.text.html.HTMLDocument;
import java.io.*;
import java.util.Iterator;
import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

import static java.lang.Math.round;

public class CaseMenu {
    private final Scanner scanner;

    public CaseMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    private void printMenu() {
        System.out.println("1. Выбрать сеанс по времени;");
        System.out.println("2. Выбрать сеанс по названию фильма;");
        System.out.println("3. Войти в систему администратором;");
        System.out.println("4. Посмотреть все купленные билеты");
        System.out.println("5. Завершить сеанс;");
    }

    public void start() {
        if (this.scanner != null) {
            int key;
            CreateArrays.clientFile();
            boolean flag = true;
            ArrayList<Session> ticketsBoughtSes = new ArrayList<>();
            ArrayList<Integer> ticketsBoughtSeats = new ArrayList<>();
            CreateArrays.listMaker();
            CreateArrays.advertisingListMaker();
            do {
                printMenu();
                System.out.print("Введите номер меню: ");
                key = this.scanner.nextInt();
                switch (key) {
                    case 1 -> {
                        System.out.print("Введите желаемое время сеанса: ");
                        Scanner scanner = new Scanner(System.in);
                        String time = scanner.nextLine();
                        System.out.println("Выбирете сеанс:");
                        ArrayList<Session> apSes = CreateArrays.timeChoose(time);
                        int maxSession = apSes.size();
                        int sessionKey = this.scanner.nextInt();
                        if (sessionKey <= maxSession) {
                            System.out.println("Выберете место:");
                            CreateArrays.seatChoose(apSes.get(sessionKey - 1), CreateArrays.client.getDiscount());
                            int seatKey = this.scanner.nextInt();
                            ArrayList<Integer> seatNums = CreateArrays.seatNum(apSes.get(sessionKey - 1), seatKey);
                            for (int i = 0; i < CreateArrays.sessionsList.length; i++) {
                                Hall hall = CreateArrays.sessionsList[i].getHall();
                                if (CreateArrays.sessionsList[i].equals(apSes.get(sessionKey - 1))) {
                                    if (hall.getHallInfo()[seatNums.get(0)][seatNums.get(1)][1] == 1 && CreateArrays.client.getMoney() >= hall.getHallInfo()[seatNums.get(0)][seatNums.get(1)][0]) {
                                        hall.getHallInfo()[seatNums.get(0)][seatNums.get(1)][1] = 0;
                                        System.out.println("Вы успешно записались на сеанс!");
                                        String statusWas = CreateArrays.client.getStatus();
                                        CreateArrays.client.setVisitCount(CreateArrays.client.getVisitCount() + 1);
                                        String statusBecome = CreateArrays.client.getStatus();
                                        if (!statusWas.equals(statusBecome)){
                                            System.out.println("Поздравляем! Вы получили статус " + statusBecome + "!" +  " Теперь ваша скидка - " + (int)(round((1 - CreateArrays.client.getDiscount()) * 100)) + "%");
                                        }
                                        ticketsBoughtSeats.add(seatKey);
                                        ticketsBoughtSes.add(apSes.get(sessionKey - 1));
                                        CreateArrays.client.setMoney(CreateArrays.client.getMoney() - hall.getHallInfo()[seatNums.get(0)][seatNums.get(1)][0]);
                                    } else if (CreateArrays.client.getMoney() < hall.getHallInfo()[seatNums.get(0)][seatNums.get(1)][0]) {
                                        System.out.println("У вас недостаточно денег");
                                    } else {
                                        System.out.println("Место уже занято");
                                    }
                                }
                            }
                        } else {
                            System.out.println("Нет такого сеанса");
                        }
                    }
                    case 2 -> {
                        System.out.print("Введите название фильма: ");
                        Scanner scanner1 = new Scanner(System.in);
                        String movie = scanner1.nextLine();
                        System.out.println("Выбирете сеанс:");
                        ArrayList<Session> apSesion = CreateArrays.movieChoose(movie);
                        int sessionKey1 = this.scanner.nextInt();
                        Hall hall = apSesion.get(sessionKey1 - 1).getHall();
                        if (sessionKey1 <= apSesion.size()) {
                            System.out.println("Выберете место:");
                            CreateArrays.seatChoose(apSesion.get(sessionKey1 - 1), CreateArrays.client.getDiscount());
                            int seatKey = this.scanner.nextInt();
                            ArrayList<Integer> seatNums1 = CreateArrays.seatNum(apSesion.get(sessionKey1 - 1), seatKey);
                            if (hall.getHallInfo()[seatNums1.get(0)][seatNums1.get(1)][1] == 1 && CreateArrays.client.getMoney() >= (hall.getHallInfo()[seatNums1.get(0)][seatNums1.get(1)][0])) {
                                hall.getHallInfo()[seatNums1.get(0)][seatNums1.get(1)][1] = 0;
                                System.out.println("Вы успешно записались на сеанс!");
                                String statusWas = CreateArrays.client.getStatus();
                                CreateArrays.client.setVisitCount(CreateArrays.client.getVisitCount() + 1);
                                String statusBecome = CreateArrays.client.getStatus();
                                if (!statusWas.equals(statusBecome)){
                                    System.out.println("Поздравляем! Вы получили статус " + statusBecome + "!" +  " Теперь ваша скидка - " + (int)(1 - CreateArrays.client.getDiscount() * 100) + "%");
                                }
                                ticketsBoughtSeats.add(seatKey);
                                ticketsBoughtSes.add(apSesion.get(sessionKey1 - 1));
                                CreateArrays.client.setMoney(CreateArrays.client.getMoney() - (hall.getHallInfo()[seatNums1.get(0)][seatNums1.get(1)][0]));
                            } else if (CreateArrays.client.getMoney() < hall.getHallInfo()[seatNums1.get(0)][seatNums1.get(1)][0]) {
                                System.out.println("У вас недостаточно денег");
                            } else {
                                System.out.println("Место уже занято");
                            }
                        } else {
                            System.out.println("Нет такого сеанса");
                        }
                    }
                    case 3 -> {
                        System.out.println("Введите логин:");
                        Scanner scanner2 = new Scanner(System.in);
                        String login = scanner2.nextLine();
                        System.out.println("Введите пароль:");
                        Scanner scanner3 = new Scanner(System.in);
                        String password = scanner3.nextLine();
                        boolean adminKey = true;
                        if (Objects.equals(login, CreateArrays.admin1.getLogin()) && Objects.equals(password, CreateArrays.admin1.getPassword())) {
                            System.out.println("Вы успешно вошли");
                            while (adminKey) {
                                System.out.println("1. Посмотреть общую выручку за все сеансы");
                                System.out.println("2. Посмотреть количество проданных билетов");
                                System.out.println("3. Посмотреть общую выручку за рекламу");
                                System.out.println("4. Статистика выручки по типу зала");
                                System.out.println("5. Статистика клиентов");
                                System.out.println("6. Редактирование");
                                System.out.println("7. Выгрузить результаты аналитики в файл");
                                System.out.println("8. Реклама");
                                System.out.println("9. Выйти");
                                int value = this.scanner.nextInt();
                                if (value == 1) {
                                    System.out.println("Общая выручка за все сеансы: " + CreateArrays.profit() + " рублей");
                                } else if (value == 2) {
                                    System.out.println("Количество проданных билетов: " + CreateArrays.ticketsinfo());
                                } else if (value == 3){
                                    System.out.println("Общая выручка за рекламу: " + CreateArrays.advProfit + " рублей");
                                }
                                else if (value == 4) {
                                    ArrayList<Integer> info = CreateArrays.hallTypesProfit();
                                    System.out.println("Статистика выручки по типу зала:");
                                    System.out.println("2D/3D Hall Profit: " + info.get(0) + "рублей");
                                    System.out.println("Children's Hall Profit: " + info.get(1) + "рублей");
                                    System.out.println("Comfort Hall Profit: " + info.get(2) + "рублей");
                                    System.out.println("IMAX Hall Profit: " + info.get(3) + "рублей");
                                    System.out.println("VIP Hall Profit: " + info.get(4) + "рублей");
                                    System.out.println("Dolby Atmos Hall Profit: " + info.get(5) + "рублей");
                                } else if (value == 5) {
                                    System.out.println("Статистика клиентов:");
                                    ArrayList<Integer> stats = CreateArrays.clientsStat();
                                    System.out.println("Количество клиентов с VIP статусом: " + stats.get(0));
                                    System.out.println("Количество обычных клиентов: " + stats.get(1));
                                    System.out.println("Количество клиентов со статусом друга сети: " + stats.get(2));
                                } else if (value == 6) {
                                    System.out.println("1. Создать кинотеатр");
                                    System.out.println("2. Добавить зал в кинотеатр");
                                    System.out.println("3. Удалить кинотеатр");
                                    System.out.println("4. Удалить зал из кинотеатра");
                                    int value1 = this.scanner.nextInt();
                                    switch (value1) {
                                        case 1 -> {
                                            System.out.println("Введите название кинотеатра: ");
                                            Scanner scanner4 = new Scanner(System.in);
                                            String name = scanner4.nextLine();
                                            System.out.println("Введите максимальную вместимость: ");
                                            int maxCapacity = this.scanner.nextInt();
                                            System.out.println("Введите адрес кинотеатра: ");
                                            String address = scanner4.nextLine();
                                            System.out.println("Введите количество залов: ");
                                            int hallCount = this.scanner.nextInt();
                                            Cinema cinema2 = new Cinema(name, maxCapacity, CreateArrays.hallList, address, new String[]{"2D", "3D"}, hallCount);
                                            CreateArrays.cinemaList.add(cinema2);
                                            System.out.println("Кинотеатр успешно создан");
                                        }
                                        case 2 -> {
                                            System.out.println("Введите ширину кинотеатра");
                                            int width = this.scanner.nextInt();
                                            System.out.println("Введите глубину кинотеатра");
                                            int depth = this.scanner.nextInt();
                                            System.out.println("Введите тип зала");
                                            Scanner scanner5 = new Scanner(System.in);
                                            String type = scanner5.nextLine();
                                            Hall hall3;
                                            switch (type) {
                                                case "2D/3D Hall" -> {
                                                    hall3 = new Hall2D3D(new int[width][depth][2]);
                                                }
                                                case "VIP Hall" -> {
                                                    hall3 = new VIPhall(new int[width][depth][2]);
                                                }
                                                case "IMAX Hall" -> {
                                                    hall3 = new IMAXhall(new int[width][depth][2]);
                                                }
                                                case "Dolby Atmos Hall" -> {
                                                    hall3 = new DolbyAtmosHall(new int[width][depth][2]);
                                                }
                                                case "Children's Hall" -> {
                                                    hall3 = new ChildrensHall(new int[width][depth][2]);
                                                }
                                                case "Comfort Hall" -> {
                                                    hall3 = new ComfortHall(new int[width][depth][2]);
                                                }
                                                default -> {
                                                    hall3 = null;
                                                }
                                            }
                                            System.out.println("Выберите кинотеатр, в который хотите добавить зал");
                                            int gg = 1;
                                            for (Cinema cinema : CreateArrays.cinemaList) {
                                                System.out.println(gg + ". " + cinema.getName() + " " + cinema.getAddress());
                                                gg = gg + 1;
                                            }
                                            int del = this.scanner.nextInt();
                                            CreateArrays.cinemaList.get(del - 1).getHallList().add(hall3);
                                            System.out.println("Зал добавлен в кинотеатр!");
                                        }
                                        case 3 -> {
                                            System.out.println("Выберите кинотеатр, который хотите удалить");
                                            for (Cinema cinema : CreateArrays.cinemaList) {
                                                int gg = 1;
                                                System.out.println(gg + ". " + cinema.getName() + cinema.getAddress());
                                                gg++;
                                            }
                                            int toRemove = this.scanner.nextInt();
                                            CreateArrays.cinemaList.remove(toRemove - 1);
                                        }
                                        case 4 -> {
                                            System.out.println("Выберите кинотеатр, из которого хотите удалить зал");
                                            int gg = 1;
                                            for (Cinema cinema : CreateArrays.cinemaList) {
                                                System.out.println(gg + ". " + cinema.getName() + cinema.getAddress());
                                                gg++;
                                            }
                                            int toRemove = this.scanner.nextInt();
                                            System.out.println("Выберите зал, который хотите удалить");
                                            int hh = 1;
                                            for (Hall hall : CreateArrays.cinemaList.get(toRemove - 1).getHallList()) {
                                                System.out.println(hh + ". " + "Тип зала: " + hall.getType() + "глубина: " + hall.getHallInfo().length + "ширина: " + hall.getHallInfo()[gg - 1].length);
                                                hh++;
                                            }
                                            int hallToRemove = this.scanner.nextInt();
                                            CreateArrays.cinemaList.get(toRemove - 1).getHallList().remove(hallToRemove - 1);
                                        }
                                    }
                                }else if (value == 7) {
                                    CreateArrays.adminInfo();
                                } else if(value == 8){
                                    System.out.println("Предложения рекламы:" );
                                    int qq = 1;
                                    Iterator<Advertising> advIterator = CreateArrays.advertisings.iterator();
                                    while (advIterator.hasNext()) {
                                        Advertising nextadv = advIterator.next();
                                        System.out.println(qq + ". " + "Компания:" + nextadv.getText() + " Цена:" + nextadv.getCost() + " Сеанс:" + nextadv.getSession().getMovie().getName());
                                        System.out.println("1. Принять предложение");
                                        System.out.println("2. Отклонить предложение");
                                        int value1 = this.scanner.nextInt();
                                        switch (value1){
                                            case 1 -> {
                                                CreateArrays.aproveAdv.add(nextadv);
                                                CreateArrays.advProfit += nextadv.getCost();
                                                CreateArrays.advertisingFile();
                                                advIterator.remove();
                                                }
                                            case 2 -> {
                                                advIterator.remove();
                                            }
                                            default-> {
                                                System.out.println("Вы ввели неверное значение меню\n");
                                            }
                                        }
                                        qq++;
                                            }
                                    if (CreateArrays.advertisings.size() == 0);
                                    System.out.println("Предложений больше нет.");
                                    }else if (value == 9) {
                                    adminKey = false;
                                } else {
                                    System.out.println("Нет такого варианта");
                                }
                            }
                        } else {
                            System.out.println("Неверный логин или пароль");
                        }
                    }
                    case 4 -> {
                        System.out.println("Информация о приобритенных билетах:");
                        if (ticketsBoughtSes.size() == 0) {
                            System.out.println("Нет приобритенных билетов");
                        } else {
                            for (int i = 0; i < ticketsBoughtSes.size(); i++) {
                                System.out.print("Билет ");
                                System.out.print(i + 1);
                                System.out.println(":");
                                ticketsBoughtSes.get(i).sessionprint();
                                System.out.print("Место: ");
                                System.out.println(ticketsBoughtSeats.get(i));
                            }
                        }
                    }
                    case 5 -> {
                        flag = false;
                    }
                    default -> System.out.println("Вы ввели неверное значение меню...\n");
                }
            } while (flag);
        }
    }
}

class CreateArrays {
    public static ArrayList<Hall> hallList = new ArrayList<Hall>();;
    public static Hall2D3D hall2d3d1 = new Hall2D3D(new int[4][5][2]);
    public static Hall2D3D hall2d3d2 = new Hall2D3D(new int[5][6][2]);
    public static ChildrensHall chHall1 = new ChildrensHall(new int[8][5][2]);
    public static ChildrensHall chHall2 = new ChildrensHall(new int[5][10][2]);
    public static ChildrensHall chHall3 = new ChildrensHall(new int[4][5][2]);
    public static ComfortHall comfortHall1 = new ComfortHall(new int[4][5][2]);
    public static ComfortHall comfortHall2 = new ComfortHall(new int[5][6][2]);
    public static DolbyAtmosHall dolbyAtmosHall1 = new DolbyAtmosHall(new int[5][10][2]);
    public static DolbyAtmosHall dolbyAtmosHall12 = new DolbyAtmosHall(new int[6][10][2]);
    public static IMAXhall imaxHall1 = new IMAXhall(new int[4][5][2]);
    public static IMAXhall imaxHall3 = new IMAXhall(new int[4][8][2]);
    public static IMAXhall imaxHall2 = new IMAXhall(new int[5][8][2]);
    public static VIPhall vipHall1 = new VIPhall(new int[5][6][2]);
    public static VIPhall vipHall3 = new VIPhall(new int[4][5][2]);
    public static VIPhall vipHall2 = new VIPhall(new int[4][5][2]);
    public static void listMaker(){
        hallList.add(hall2d3d1);
        hallList.add(hall2d3d2);
        hallList.add(chHall1);
        hallList.add(chHall2);
        hallList.add(comfortHall1);
        hallList.add(comfortHall2);
        hallList.add(dolbyAtmosHall1);
        hallList.add(dolbyAtmosHall12);
        hallList.add(imaxHall1);
        hallList.add(imaxHall2);
        hallList.add(vipHall1);
        hallList.add(vipHall2);
    }
    public static ArrayList<Movie> movieList = movieFile();
    public static ArrayList<Cinema> cinemaList = cinemaFile();
    public static Admin admin1 = new Admin("admin", "admin");
    public static ArrayList<RareClient> clients = new ArrayList<RareClient>();
    public static ArrayList<Advertising> advertisings = new ArrayList<Advertising>();
    public static ArrayList<Advertising> aproveAdv = new ArrayList<Advertising>();
    public static RareClient client;
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static Cinema cinema1 = new Cinema("Prada", 20, hallList, "Bazhenova street 2A", new String[]{"2D", "3D"}, hallList.size());
    public static Cinema cinema2 = new Cinema("Luksor", 30, hallList, "Rubcoviy periulok 7", new String[]{"2D", "3D"}, hallList.size());
    public static Cinema cinema3 = new Cinema("Prada", 40, hallList, "Bazhenova street 2A", new String[]{"2D", "3D"}, hallList.size());

    public static Session session1 = new Session(cinemaList.get((int)(Math.random() * cinemaList.size())), movieList.get((int)(Math.random() * movieList.size())), "14:00", chHall1);
    public static Session session2 = new Session(cinemaList.get((int)(Math.random() * cinemaList.size())), movieList.get((int)(Math.random() * movieList.size())), "16:00", chHall2);
    public static Session session3 = new Session(cinemaList.get((int)(Math.random() * cinemaList.size())), movieList.get((int)(Math.random() * movieList.size())), "18:00", chHall3);
    public static Session session4 = new Session(cinemaList.get((int)(Math.random() * cinemaList.size())), movieList.get((int)(Math.random() * movieList.size())), "11:00", imaxHall1);
    public static Session session5 = new Session(cinemaList.get((int)(Math.random() * cinemaList.size())), movieList.get((int)(Math.random() * movieList.size())), "12:00", imaxHall2);
    public static Session session6 = new Session(cinemaList.get((int)(Math.random() * cinemaList.size())), movieList.get((int)(Math.random() * movieList.size())), "20:00", imaxHall3);
    public static Session session7 = new Session(cinemaList.get((int)(Math.random() * cinemaList.size())), movieList.get((int)(Math.random() * movieList.size())), "10:00", dolbyAtmosHall1);
    public static Session session8 = new Session(cinemaList.get((int)(Math.random() * cinemaList.size())), movieList.get((int)(Math.random() * movieList.size())), "13:00", dolbyAtmosHall12);
    public static Session session9 = new Session(cinemaList.get((int)(Math.random() * cinemaList.size())), movieList.get((int)(Math.random() * movieList.size())), "15:00", hall2d3d1);
    public static Session session10 = new Session(cinemaList.get((int)(Math.random() * cinemaList.size())), movieList.get((int)(Math.random() * movieList.size())), "14:00", hall2d3d2);
    public static Session session11 = new Session(cinemaList.get((int)(Math.random() * cinemaList.size())), movieList.get((int)(Math.random() * movieList.size())), "19:00", vipHall1);
    public static Session session12 = new Session(cinemaList.get((int)(Math.random() * cinemaList.size())), movieList.get((int)(Math.random() * movieList.size())), "15:30", vipHall2);
    public static Session session13 = new Session(cinemaList.get((int)(Math.random() * cinemaList.size())), movieList.get((int)(Math.random() * movieList.size())), "16:00", vipHall3);
    public static Session session14 = new Session(cinemaList.get((int)(Math.random() * cinemaList.size())), movieList.get((int)(Math.random() * movieList.size())), "17:00", comfortHall1);
    public static Session session15 = new Session(cinemaList.get((int)(Math.random() * cinemaList.size())), movieList.get((int)(Math.random() * movieList.size())), "19:00", comfortHall2);
    public static void cinemaListMaker(){
        cinemaList.add(cinema1);
        cinemaList.add(cinema2);
        cinemaList.add(cinema3);
    }
    public static Advertising advertising1 = new Advertising("Милка", 400, session3);
    public static Advertising advertising2 = new Advertising("Кока-Кола", 600, session6);
    public static Advertising advertising3= new Advertising("Нвидиа", 350, session4);
    public static Advertising advertising4 = new Advertising("АМД", 450, session9);
    public static Advertising advertising5 = new Advertising("World of Warcraft", 150, session11);
    public static void advertisingListMaker(){
        advertisings.add(advertising1);
        advertisings.add(advertising2);
        advertisings.add(advertising3);
        advertisings.add(advertising4);
        advertisings.add(advertising5);
    }
    public static int advProfit;
    public static Session[] sessionsList = new Session[]{session1, session2, session3, session4, session5, session6, session7, session8, session9, session10, session11, session12, session13, session14, session15};

    public static ArrayList<Session> timeChoose(String time){
        ArrayList<Session> aproveSes = new ArrayList<>();
        int counter = 1;
        for (Session session : sessionsList) {
            if (session.getTime().compareTo(time) == 0) {
                System.out.print(counter);
                System.out.print(". ");
                aproveSes.add(session);
                session.sessionprint();
                counter++;
            }
        }
        return aproveSes;
    }
    public static ArrayList<Session> movieChoose(String movie){
        ArrayList<Session> aproveSes = new ArrayList<>();
        int counter = 1;
        for (Session session : sessionsList) {
            if (Objects.equals(session.getMovie().getName(), movie)) {
                System.out.print(counter);
                System.out.print(". ");
                aproveSes.add(session);
                session.sessionprint();
                counter++;
            }
        }
        return aproveSes;
    }
    public static ArrayList<Integer> seatNum(Session Ses, int key){
        int counter = 0;
        ArrayList<Integer> seatNum = new ArrayList<>();
        for (int i = 0; i < Ses.getHall().getHallInfo().length; i++) {
            for (int j = 0; j < Ses.getHall().getHallInfo()[i].length; j++){
                counter ++;
                if (key == counter){
                    seatNum.add(i);
                    seatNum.add(j);
                }
            }
        }
        return seatNum;
    }
    public static void seatChoose(Session Ses, double discount) {
        int counter = 0;
        for (int i = 0; i < Ses.getHall().getHallInfo().length; i++) {
            for (int j = 0; j < Ses.getHall().getHallInfo()[i].length; j++){
                counter ++;
                if (Ses.getHall().getHallInfo()[i][j][1] == 1) {
                    System.out.print(ANSI_GREEN + (int) (Ses.getHall().getHallInfo()[i][j][0] * discount) + ANSI_RESET + "(" + counter + ")" + "\t");
                } else {
                    System.out.print(ANSI_RED + (int) (Ses.getHall().getHallInfo()[i][j][0] * discount) + ANSI_RESET + "(" + counter + ")" + "\t");
                }
            }
            System.out.println();
        }
    }
    public static int ticketsinfo(){
        int ticketsSold = 0;
        for (Session session : sessionsList) {
            Hall hall = session.getHall();
            for (int j = 0; j < hall.getHallInfo().length; j++) {
                for (int k = 0; k < hall.getHallInfo()[j].length; k++) {
                    if (hall.getHallInfo()[j][k][1] == 0) {
                        ticketsSold++;
                    }
                }
            }
        }
        return ticketsSold;
    }
    public static int profit(){
        int profit = 0;
        for (Session session : sessionsList) {
            Hall hall = session.getHall();
            for (int j = 0; j < hall.getHallInfo().length; j++) {
                for (int k = 0; k < hall.getHallInfo()[j].length; k++) {
                    if (hall.getHallInfo()[j][k][1] == 0) {
                        profit += hall.getHallInfo()[j][k][0];
                    }
                }
            }
        }
        profit += CreateArrays.advProfit;
        return profit;
    }
    public static ArrayList<Integer> hallTypesProfit(){
        ArrayList<Integer> info = new ArrayList<>();
        int chProfit = 0;
        int comProfit = 0;
        int dolbyProfit = 0;
        int hall2dProfit = 0;
        int imaxProfit = 0;
        int vipProfit = 0;
        for (Session session : sessionsList) {
            if (session.getHall().getType() == "2D/3D Hall") {
                Hall hall = session.getHall();
                for (int j = 0; j < hall.getHallInfo().length; j++) {
                    for (int k = 0; k < hall.getHallInfo()[j].length; k++) {
                        if (hall.getHallInfo()[j][k][1] == 0) {
                            hall2dProfit += hall.getHallInfo()[j][k][0];
                        }
                    }
                }
            }
            if (session.getHall().getType() == "Children's Hall") {
                Hall hall = session.getHall();
                for (int j = 0; j < hall.getHallInfo().length; j++) {
                    for (int k = 0; k < hall.getHallInfo()[j].length; k++) {
                        if (hall.getHallInfo()[j][k][1] == 0) {
                            chProfit += hall.getHallInfo()[j][k][0];
                        }
                    }
                }
            }
            if (session.getHall().getType() == "Comfort Hall") {
                Hall hall = session.getHall();
                for (int j = 0; j < hall.getHallInfo().length; j++) {
                    for (int k = 0; k < hall.getHallInfo()[j].length; k++) {
                        if (hall.getHallInfo()[j][k][1] == 0) {
                            comProfit += hall.getHallInfo()[j][k][0];
                        }
                    }
                }
            }
            if (session.getHall().getType() == "Dolby Atmos Hall") {
                Hall hall = session.getHall();
                for (int j = 0; j < hall.getHallInfo().length; j++) {
                    for (int k = 0; k < hall.getHallInfo()[j].length; k++) {
                        if (hall.getHallInfo()[j][k][1] == 0) {
                            dolbyProfit += hall.getHallInfo()[j][k][0];
                        }
                    }
                }
            }
            if (session.getHall().getType() == "IMAX Hall") {
                Hall hall = session.getHall();
                for (int j = 0; j < hall.getHallInfo().length; j++) {
                    for (int k = 0; k < hall.getHallInfo()[j].length; k++) {
                        if (hall.getHallInfo()[j][k][1] == 0) {
                            imaxProfit += hall.getHallInfo()[j][k][0];
                        }
                    }
                }
            }
            if (session.getHall().getType() == "VIP Hall") {
                Hall hall = session.getHall();
                for (int j = 0; j < hall.getHallInfo().length; j++) {
                    for (int k = 0; k < hall.getHallInfo()[j].length; k++) {
                        if (hall.getHallInfo()[j][k][1] == 0) {
                            vipProfit += hall.getHallInfo()[j][k][0];
                        }
                    }
                }
            }
        }
        info.add(hall2dProfit);
        info.add(chProfit);
        info.add(comProfit);
        info.add(imaxProfit);
        info.add(vipProfit);
        info.add(dolbyProfit);
        return info;
    }
    public static ArrayList<Integer> clientsStat(){
        int vipCount = 0;
        int friendCount = 0;
        int rareCount = 0;
        ArrayList<Integer> clientStat = new ArrayList<Integer>();
        for (RareClient client: clients){
            switch (client.getStatus()){
                case "VIP Client" -> {
                    vipCount++;
                }
                case "Rare Client" -> {
                    rareCount++;
                }
                case "Chain friend" -> {
                    friendCount++;
                }
            }
        }
        clientStat.add(vipCount);
        clientStat.add(rareCount);
        clientStat.add(friendCount);
        return clientStat;
    }
    public static void clientFile(){
        BufferedReader br = null;
        try{
            File file = new File("client.txt");
            if (!file.exists()){
                file.createNewFile();
                System.out.println("Зарегестрируйтесь, заполните всю необходимую информацию о себе");
                System.out.println("Введите свое ФИО");
                Scanner scanner = new Scanner(System.in);
                String FIO = scanner.nextLine();
                System.out.println("Введите свой номер телефона");
                Scanner scanner1 = new Scanner(System.in);
                String phone = scanner1.nextLine();
                System.out.println("Введите свою почту");
                Scanner scanner2 = new Scanner(System.in);
                String email = scanner2.nextLine();
                System.out.println("Введите ваше количество денег");
                int money = scanner.nextInt();
                System.out.println("Введите логин");
                Scanner scanner3 = new Scanner(System.in);
                String login = scanner3.nextLine();
                System.out.println("Введите пароль");
                Scanner scanner4 = new Scanner(System.in);
                String password = scanner4.nextLine();
                PrintWriter pw = new PrintWriter(file);
                pw.println(FIO);
                pw.println(phone);
                pw.println(email);
                pw.println(money);
                pw.println(0);
                pw.println(login);
                pw.println(password);
                pw.close();
            }
            br = new BufferedReader(new FileReader("client.txt"));
            client = new RareClient(br.readLine(), br.readLine(), br.readLine(), Integer.parseInt(br.readLine()), Integer.parseInt(br.readLine()), br.readLine(), br.readLine());
            clients.add(client);
        } catch (IOException e){
            System.out.println("Error creating");
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                System.out.println("Error closing");
            }
        }
    }
    public static ArrayList<Movie> movieFile(){
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        BufferedReader br1 = null;
        try{
            File file1 = new File("MoviesInfo.txt");
            if (!file1.exists()){
                file1.createNewFile();
            }
            br1 = new BufferedReader(new FileReader("MoviesInfo.txt"));
            String line;
            while ((line = br1.readLine())!= null) {
                String name = line.substring(0, line.indexOf(","));
                line = line.replace(name.concat(", "), "");
                int year = Integer.parseInt(line.substring(0, line.indexOf(",")));
                line = line.replace(line.substring(0, line.indexOf(",")).concat(", "), "");
                String genre = line.substring(0, line.indexOf(","));
                line = line.replace(genre.concat(", "), "");
                int duration = Integer.parseInt(line.substring(0, line.indexOf(",")));
                line = line.replace(line.substring(0, line.indexOf(",")).concat(", "), "");
                Movie movie = new Movie(name, year, genre, duration, line);
                movieArrayList.add(movie);
            }
        } catch (IOException e){
            System.out.println("Error creating");
        } finally {
            try {
                br1.close();
            } catch (IOException e) {
                System.out.println("Error closing");
            }
        }
        return movieArrayList;
    }
    public static ArrayList<Cinema> cinemaFile(){
        ArrayList<Cinema> cinemaArrayList = new ArrayList<>();
        BufferedReader br2 = null;
        try{
            File file2 = new File("CinemaInfo.txt");
            if (!file2.exists()){
                file2.createNewFile();
            }
            br2 = new BufferedReader(new FileReader("CinemaInfo.txt"));
            String line;
            while ((line = br2.readLine())!= null) {
                String name = line.substring(0, line.indexOf(","));
                line = line.replace(name.concat(", "), "");
                int maxCapacity = Integer.parseInt(line.substring(0, line.indexOf(",")));
                line = line.replace(line.substring(0, line.indexOf(",")).concat(", "), "");
                cinemaArrayList.add(new Cinema(name, maxCapacity, hallList, line, new String[]{"2D", "3D"}, hallList.size()));
            }
        } catch (IOException e){
            System.out.println("Error creating");
        } finally {
            try {
                br2.close();
            } catch (IOException e) {
                System.out.println("Error closing");
            }
        }
        return cinemaArrayList;
    }
    public static void advertisingFile(){
        try {
            File file = new File("advertising.txt");
            if (!file.exists()){
                file.createNewFile();
            }
            PrintWriter pw = new PrintWriter(file);
            for (int i = 0; i < CreateArrays.aproveAdv.size(); i++) {
                pw.println(CreateArrays.aproveAdv.get(i).getText());
                pw.println(CreateArrays.aproveAdv.get(i).getCost());
                pw.println(CreateArrays.aproveAdv.get(i).getSession().getMovie().getName());
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("Error creating");
        }
    }
    public static void adminInfo(){
        try{
            File file5 = new File("admin.txt");
            PrintWriter pw = new PrintWriter(file5);
            ArrayList<Integer> info = new ArrayList<>();
            if (!file5.exists()){
                file5.createNewFile();
            }
            pw.println("Общая выручка за все сеансы: " + (CreateArrays.profit()+CreateArrays.advProfit) + " рублей");
            pw.println("Количество проданных билетов: " + CreateArrays.ticketsinfo());
            pw.println("Общая выручка за рекламу: " + CreateArrays.advProfit + " рублей");
            pw.println("Статистика выручки по типу зала:");
            info = CreateArrays.hallTypesProfit();
            pw.println("2D/3D Hall Profit: " + info.get(0) + "рублей");
            pw.println("Children's Hall Profit: " + info.get(1) + "рублей");
            pw.println("Comfort Hall Profit: " + info.get(2) + "рублей");
            pw.println("IMAX Hall Profit: " + info.get(3) + "рублей");
            pw.println("VIP Hall Profit: " + info.get(4) + "рублей");
            pw.println("Dolby Atmos Hall Profit: " + info.get(5) + "рублей");
            pw.println("Статистика клиентов:");
            ArrayList<Integer> stats = CreateArrays.clientsStat();
            pw.println("Количество клиентов с VIP статусом: " + stats.get(0));
            pw.println("Количество обычных клиентов: " + stats.get(0));
            pw.println("Количество клиентов со статусом друга сети: " + stats.get(0));
            pw.close();
        } catch (IOException e) {
            System.out.println("Error creating");
        }
    }
}


class CaseMenuTest {
    public static void main(String[] args){
        new CaseMenu(new Scanner(System.in)).start();
        try{
            File file = new File("client.txt");
            PrintWriter pw = new PrintWriter(file);
            pw.println(CreateArrays.client.getFIO());
            pw.println(CreateArrays.client.getPhoneNumber());
            pw.println(CreateArrays.client.getEmail());
            pw.println(CreateArrays.client.getMoney());
            pw.println(CreateArrays.client.getVisitCount());
            pw.println(CreateArrays.client.getLogin());
            pw.println(CreateArrays.client.getPassword());
            pw.close();
        } catch (IOException e) {
            System.out.println("Error creating");
        }
    }
}



import java.util.Arrays;
import java.util.Objects;
import java.util.ArrayList;

public class Cinema {
    private String name;
    private int maxCapacity;
    private String address;
    private String[] formatAllowed;

    private ArrayList<Hall> hallList;
    private int hallCount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cinema cinema)) return false;
        return getMaxCapacity() == cinema.getMaxCapacity() && hallCount == cinema.hallCount && Objects.equals(getName(), cinema.getName()) && Objects.equals(getAddress(), cinema.getAddress()) && Arrays.equals(getFormatAllowed(), cinema.getFormatAllowed()) && Objects.equals(getHallList(), cinema.getHallList());
    }


    public Cinema(String name, int maxCapacity, ArrayList<Hall> hallList, String address, String[] formatAllowed, int hallCoount) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.hallList = hallList;
        this.address = address;
        this.formatAllowed = formatAllowed;
        this.hallCount = hallCoount;
    }

    public String[] getFormatAllowed() {
        return formatAllowed;
    }

    public void setFormatAllowed(String[] formatAllowed) {
        this.formatAllowed = formatAllowed;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHallList(ArrayList<Hall> hallList) {
        this.hallList = hallList;
    }
    public ArrayList<Hall> getHallList() {
        return hallList;
    }
}

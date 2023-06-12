public class ComfortHall extends Hall {
    private final String type = "Comfort Hall";
    public String getType() {
        return type;
    }
    public ComfortHall(int[][][] hallInfo) {
        super(hallInfo);
        for (int i = 0; i < hallInfo.length; i++) {
            for (int j = 0; j < hallInfo[i].length; j++) {
                hallInfo[i][j][0] = 1;
                hallInfo[i][j][0] = 150 * 3 + 1;
                hallInfo[i][j][1] = (int) (Math.random() * 2);
            }
        }
    }
}

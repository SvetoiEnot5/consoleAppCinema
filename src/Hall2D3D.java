public class Hall2D3D extends Hall {
    private final String type = "2D/3D Hall";
    public String getType() {
        return type;
    }
    public Hall2D3D(int[][][] hallInfo) {
        super(hallInfo);
        for (int i = 0; i < hallInfo.length; i++) {
            for (int j = 0; j < hallInfo[i].length; j++) {
                hallInfo[i][j][0] = 1;
                hallInfo[i][j][0] = 150 * 1 + 1;
                hallInfo[i][j][1] = (int) (Math.random() * 2);
            }
        }
    }
}

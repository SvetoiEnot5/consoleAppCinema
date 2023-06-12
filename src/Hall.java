abstract class Hall {

    private int[][][] hallInfo;
    private String type;
    public String getType(){
        return type;
    }
    public int getLength(){
        return hallInfo.length;
    }
    public int getDepth(){
        return hallInfo[0].length;
    }
    public int getElem(int i, int j){
        return hallInfo[i][j][0];
    }
    public void setElem(int i, int j, int val){
        hallInfo[i][j][0] = val;
    }

    public Hall(int[][][] hallInfo) {
        this.hallInfo = hallInfo;
        for (int i = 0; i < hallInfo.length; i++) {
            for (int j = 0; j < hallInfo[i].length; j++) {
                hallInfo[i][j][0] = 1;
                while (hallInfo[i][j][0] % 5 != 0){
                    hallInfo[i][j][0] = (int) (Math.random() * 500 + 1);
                }
                hallInfo[i][j][1] = (int) (Math.random() * 2);
            }
        }
    }

    public int[][][] getHallInfo() {
        return hallInfo;
    }

    public void setHallInfo(int[][][] hallInfo) {
        this.hallInfo = hallInfo;
    }

    public void printHall() {
        for (int i = 0; i < hallInfo.length; i++){
            for (int j = 0; j < hallInfo[i].length; j++){
                System.out.print(hallInfo[i][j][0] + " ");
            }
            System.out.print("\n");
        }
    }

}

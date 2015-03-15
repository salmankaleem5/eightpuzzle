package eightpuzzle;

public class Pair<IntegerR,IntegerC> {
    private int r, c;
    public Pair(int r, int c){
        this.r = r;
        this.c = c;
    }
    public int getR(){ return r; }
    public int getC(){ return c; }
    public void setR(int r){ this.r = r; }
    public void setC(int c){ this.c = c; }
}

public class Position {

    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //Changes this position according to the supplied velocity, there is no edge check
    public void changePosition(Velocity velocity) {
        this.x += velocity.x;
        this.y += velocity.y;
    }
}

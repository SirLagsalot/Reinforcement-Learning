
//Stores a pair of velocity components
public class Velocity {

    protected final int x;
    protected final int y;

    public Velocity(int Vx, int Vy) {

        assert (Vx >= -5 && Vx <= 5) : "Invalid X velocity.";
        assert (Vy >= -5 && Vy <= 5) : "Invalid Y velocity.";

        this.x = Vx;
        this.y = Vy;
    }

    public int[] getVelocity() {
        return new int[]{x, y};
    }
}

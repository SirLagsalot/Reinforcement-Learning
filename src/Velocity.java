
//Stores a pair of velocity components
public final class Velocity {

    protected final int x;      //Im not certain if we want these to be final in the end
    protected final int y;      //State should be final, but it might make things easier if we relax that here.. up for debate

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

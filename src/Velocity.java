
//Stores a pair of velocity components
public final class Velocity {

    public int x;
    public int y;

    public Velocity(int Vx, int Vy) {

        assert (Vx >= -5 && Vx <= 5) : "Invalid X velocity.";
        assert (Vy >= -5 && Vy <= 5) : "Invalid Y velocity.";

        this.x = Vx;
        this.y = Vy;
    }

    public int[] getVelocity() {
        return new int[]{x, y};
    }

    //Accelerates the velocity with 80% success
    public boolean accelerate(Action acceleration) {

        assert (acceleration.x >= -1 && acceleration.x <= 1) : "Invalid X acceleration.";
        assert (acceleration.y >= -1 && acceleration.y <= 1) : "Invalid Y acceleration.";

        if (Math.random() > 0.2) {
            this.x = (this.x + acceleration.x > 5) ? 5 : this.x + acceleration.x;
            this.y = (this.y + acceleration.y > 5) ? 5 : this.y + acceleration.y;
            return true;
        }
        return false;
    }

    //Accelerates this velocity, for use when computing an expected new state
    public void changeVelocity(Action acceleration) {
        this.x = (this.x + acceleration.x > 5) ? 5 : this.x + acceleration.x;
        this.y = (this.y + acceleration.y > 5) ? 5 : this.y + acceleration.y;
    }
}

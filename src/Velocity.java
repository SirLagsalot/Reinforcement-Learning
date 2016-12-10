
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

    //Accelerates the velocity with 80% success
    public boolean accelerate(Action acceleration) {

        assert (acceleration.x >= -1 && acceleration.x <= 1) : "Invalid X acceleration.";
        assert (acceleration.y >= -1 && acceleration.y <= 1) : "Invalid Y acceleration.";

        if (Math.random() > 0.2) {
            this.x = this.x + acceleration.x;
            this.y = this.y + acceleration.y;
            if (this.x > 5) {
                this.x = 5;
            }
            if (this.x < -5) {
                this.x = -5;
            }
            if (this.y > 5) {
                this.y = 5;
            }
            if (this.y < -5) {
                this.y = -5;
            }
            return true;
        } else {
//            System.out.println("fuck accelerating");
        }
        return false;
    }

    //Accelerates this velocity, for use when computing an expected new state
    public void changeVelocity(Action acceleration) {
        this.x = this.x + acceleration.x;
        this.y = this.y + acceleration.y;
        if (this.x > 5) {
            this.x = 5;
        }
        if (this.x < -5) {
            this.x = -5;
        }
        if (this.y > 5) {
            this.y = 5;
        }
        if (this.y < -5) {
            this.y = -5;
        }
    }
}

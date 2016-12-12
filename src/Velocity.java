
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

            if (this.x + acceleration.x > 5) {
                this.x = 5;
            } else if (this.x + acceleration.x < -5) {
                this.x = -5;
            } else {
                this.x += acceleration.x;
            }

            if (this.y + acceleration.y > 5) {
                this.y = 5;
            } else if (this.y + acceleration.y < -5) {
                this.y = -5;
            } else {
                this.y += acceleration.y;
            }

            return true;
        } else {
            return false;
        }
    }

    //Accelerates this velocity, for use when computing an expected new state
    public void changeVelocity(Action acceleration) {

        if (this.x + acceleration.x > 5) {
            this.x = 5;
        } else if (this.x + acceleration.x < -5) {
            this.x = -5;
        } else {
            this.x += acceleration.x;
        }

        if (this.y + acceleration.y > 5) {
            this.y = 5;
        } else if (this.y + acceleration.y < -5) {
            this.y = -5;
        } else {
            this.y += acceleration.y;
        }
    }
}


public class State {

    Position position;
    int x = position.x, y = position.y;   //Agent's location
    int Vx = 0, Vy = 0; //Agent's velocity

    //
    //I don't think this should be here...
    public void accelerate(int Ax, int Ay) {

        assert (Ax >= -1 && Ax <= 1);
        assert (Ay >= -1 && Ay <= 1);

        //update velocities
        if (Vx + Ax <= 5) {
            Vx += Ax;
        } else {
            Vx = 5;
            System.out.println("X velocity capped at 5.");
        }

        if (Vy + Ay <= 5) {
            Vy += Ay;
        } else {
            Vy = 5;
            System.out.println("X velocity capped at 5.");
        }

        //update position
        //there prob needs to be a boundry / colision check here.. or immediatly after accelerate is called by the agent or something
        x += Vx;
        y += Vy;
    }
}

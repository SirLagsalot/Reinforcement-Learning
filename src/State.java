
public class State {

    int x = 0, y = 0, Vx = 0, Vy = 0;

    public void accelerate(int Ax, int Ay) {

        assert (Ax >= -1 && Ax <= 1);
        assert (Ay >= -1 && Ay <= 1);

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
    }

}

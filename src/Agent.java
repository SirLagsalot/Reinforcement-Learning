
public class Agent {

    private final Learner learner;

    public Agent(Learner learner) {
        this.learner = learner;
    }

    class State {

        int x;
        int y;
        int Vx;
        int Vy;

        void accelerate(int Ax, int Ay) {

            assert (Ax >= -1 && Ax <= 1);
            assert (Ay >= -1 && Ay <= 1);

            if (Vx + Ax <= 5) {
                Vx += Ax;
            } else {
                System.out.println("X velocity capped at 5.");
            }

            if (Vy + Ay <= 5) {
                Vy += Ay;
            } else {
                System.out.println("X velocity capped at 5.");
            }
        }
    }
}

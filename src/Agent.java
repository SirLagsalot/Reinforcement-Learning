
public class Agent {

    private final Learner learner;
    private State state;

    public Agent(Learner learner, int startX, int startY) {
        this.learner = learner;
        state.x = startX;
        state.y = startY;
    }

    private class State {

        int x = 0, y = 0, Vx = 0, Vy = 0;

        private void accelerate(int Ax, int Ay) {

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
}

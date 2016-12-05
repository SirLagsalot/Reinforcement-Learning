
public class State {

    public Position position;                   //Agent's (x,y) position
    public int x = position.x, y = position.y;  //Agent's location
    public int Vx = 0, Vy = 0;                  //Agent's velocity

    private double reward;

    public State() {
    }

    public State(Position position, int vX, int vY) {
        this.position = position;
        Vx = vX;
        Vy = vY;
    }

    //Returns the state result from an action
    public State nextState(Action action) {

        int[] acceleration = action.getAction();
        return new State(this.position, this.x + (acceleration[0] + this.Vx), this.y + (acceleration[1] + this.Vy));
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public double getReward() {
        return this.reward;
    }
}

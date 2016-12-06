
public class State {

    public Position position;                   //Agent's (x,y) position
    public int x = position.x, y = position.y;  //Agent's location
    public int Vx = 0, Vy = 0;                  //Agent's velocity

    private double utility;                     //Utility associated with being in this state

    public State() {
    }

    public State(Position position, int vX, int vY) {
        this.position = position;
        this.Vx = vX;
        this.Vy = vY;
        this.utility = Math.random();
    }

    //Returns the state result from an action
    public State nextState(Action action) {
        int[] acceleration = action.getAction();
        return new State(this.position, this.x + (acceleration[0] + this.Vx), this.y + (acceleration[1] + this.Vy));
    }

    //Set the utility of being in this state
    public void setUtility(double reward) {
        this.utility = reward;
    }

    //Return the utility of being in this state
    public double getUtility() {
        return this.utility;
    }
}

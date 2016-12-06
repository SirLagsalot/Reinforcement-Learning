
//All states should be created at the beinging of the simulation, should never be creating 'new' states
public final class State {

    public Position position;                  //Agent's (x,y) position
    public Velocity velocity;                  //Agent's velocity

    private double utility;                     //Utility associated with being in this state

    public State() {
        
    }
    
    public State(Position p, Velocity v) {
        this.position = p;
        this.velocity = v;
        this.utility = Math.random();
    }

    public State(int x, int y, int Vx, int Vy) {

        this.position = new Position(x, y);
        this.velocity = new Velocity(Vx, Vy);
        this.utility = Math.random();
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

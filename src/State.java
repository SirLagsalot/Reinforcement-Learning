
public class State {
    
    

    public Position position;
    public int x = position.x, y = position.y;   //Agent's location
    public int Vx = 0, Vy = 0; //Agent's velocity
    
    public State(Position position, int vX, int vY){
        this.position = position;
        Vx = vX;
        Vy = vY;
    }
}

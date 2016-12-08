
import java.util.ArrayList;

public class StateInfo {

    public Position position;
    public ArrayList<Velocity> validVelocities;     //A list of the valid velocites for a given state

    public int stateID;                             //A refernce index for mapping states and their corresponding info
    public int minVelocityX, maxVelocityX;
    public int minVelocityY, maxVelocityY;

    public boolean isStart = false;                 //Is on the starting line
    public boolean isFinal = false;                 //Is on the finish line
}

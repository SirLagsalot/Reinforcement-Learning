
import java.util.Set;
import javafx.util.Pair;


public class StateInfo {

    public Position position;
    public Set<Pair> validVelocities;               //A list of the valid velocites for a given state
    public int stateID;                             //A refernce index for mapping states and their corresponding info
    public int minVelocityX, maxVelocityX;          //Should condence into the validVelocites set
    public int minVelocityY, maxVelocityY;

    public boolean isStart = false;
    public boolean isFinal = false;
}

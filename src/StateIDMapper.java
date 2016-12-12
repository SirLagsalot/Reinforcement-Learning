
import java.util.ArrayList;

public class StateIDMapper {

    private ArrayList<StateInfo> stateInfos = new ArrayList<>();

    //Create mapper on initialization
    public StateIDMapper(char[][] track) {

        int startingStateID = 0;
        for (int row = 0; row < track.length; row++) {
            for (int col = 0; col < track[0].length; col++) {
                char curPosIdentifier = track[row][col];
                if (curPosIdentifier == '.' || curPosIdentifier == 'S' || curPosIdentifier == 'F') {
                    StateInfo info = new StateInfo();
                    if (curPosIdentifier == 'S') {
                        info.isStart = true;
                    } else if (curPosIdentifier == 'F') {       //final positions only have 0 velocity state
                        info.isFinal = true;
                        info.stateID = startingStateID;
                        info.maxVelocityX = 0;
                        info.minVelocityX = 0;
                        info.maxVelocityY = 0;
                        info.minVelocityY = 0;
                        info.position = new Position(col, row);
                        stateInfos.add(info);
                        startingStateID += 1;
                        continue;
                    }
                    info.position = new Position(col, row);
                    info.stateID = startingStateID;
                    info.maxVelocityX = findVelocityBound(true, true, track, row, col) - 1;
                    info.maxVelocityY = findVelocityBound(false, true, track, row, col) - 1;
                    info.minVelocityX = findVelocityBound(true, false, track, row, col) + 1;
                    info.minVelocityY = findVelocityBound(false, false, track, row, col) + 1;
                    stateInfos.add(info);
                    startingStateID += (info.maxVelocityX - info.minVelocityX + 1) * (info.maxVelocityY - info.minVelocityY + 1);
                }
            }
        }
    }

    //returns the maximum velocity possible by looking for a 'complete' wall in a given direction
    //a complete wall is one that spans 11 squares, that is, up to the maximal movement from a given
    //direction
    private int findVelocityBound(boolean isXDirection, boolean isMax, char[][] track, int posY, int posX) {

        if (isXDirection) {
            for (int a = 1; a <= 6; a++) {
                if (!isMax) {
                    a = -a;
                }
                for (int prime = - 5; prime < 5 + 1; prime++) {
                    if (posY + prime >= track.length || posY + prime < 0) {
                        if (prime == 5) {
                            return a;
                        }
                    } else if (track[posY + prime][posX - a] != '#') {
                        break;
                    } else if (prime == 5) {
                        return a;
                    }
                }
                if (!isMax) {
                    a = -a;
                }
            }
        } else if (!isXDirection) {
            for (int a = 1; a <= 6; a++) {
                if (!isMax) {
                    a = -a;
                }
                for (int prime = - 5; prime < 5 + 1; prime++) {
                    if (posX + prime >= track[0].length || posX + prime < 0) {
                        if (prime == 5) {
                            return a;
                        }
                    } else if (track[posY - a][posX + prime] != '#') {
                        break;
                    } else if (prime == 5) {
                        return a;
                    }
                }
                if (!isMax) {
                    a = -a;
                }
            }
        }

        if (isMax) {
            return 6;
        }
        return -6;
    }

    //returns the unique stateID by adding the offset of a states velocities from 
    //its minimal velocities to the stateInfo's startingStateID
    public int computeStateIDFromStateAndStateInfo(State state, StateInfo stateInfo) {

        int tempStateID = stateInfo.stateID + (state.velocity.x - stateInfo.minVelocityX) * (stateInfo.maxVelocityY - stateInfo.minVelocityY + 1) + state.velocity.y - stateInfo.minVelocityY;
        if (tempStateID < 0) {
            return 0;
        }
        return tempStateID;
    }

    //Gets a state from a stateID by finding the correspondingStateInfo
    //and finding the x/y velocities needed to go from the stateInfo's 
    //starting stateID to the given stateID
    public State GetStateFromID(int stateID) {

        State state = new State();
        StateInfo info = getStateInfoFromID(stateID);
        state.position = info.position;

        int idOffset = stateID - info.stateID;
        int yRange = info.maxVelocityY - info.minVelocityY + 1;
        int yVelocity = (idOffset % yRange) + info.minVelocityY;

        int xVelocity = ((int) ((idOffset - (idOffset % yRange)) / yRange)) + info.minVelocityX;
        state.velocity = new Velocity(xVelocity, yVelocity);
        return state;
    }

    //Gets stateInfo from a stateID by performing a binary
    //search on all stateID's until the two stateInfo's 
    //with state ID's that bound the given stateID below and 
    //above are found. It then returns the stateInfo corresponding
    //to the lower bound
    public StateInfo getStateInfoFromID(int stateID) {

        if (stateID >= stateInfos.get(stateInfos.size() - 1).stateID) {
            return stateInfos.get(stateInfos.size() - 1);
        }

        int currentIndex;
        currentIndex = (stateInfos.size() - 1) >> 1;
        int maxBound = stateInfos.size() - 1;
        int minBound = 0;
        while (minBound >= 0) {
            StateInfo curInfo = stateInfos.get(currentIndex);
            if (curInfo.stateID <= stateID) {
                if (stateInfos.get(currentIndex + 1).stateID > stateID) {
                    return curInfo;
                } else {
                    minBound = currentIndex;
                    currentIndex = (maxBound + minBound) >> 1;
                }
            } else {
                maxBound = currentIndex;
                currentIndex = (maxBound + minBound) >> 1;
            }
        }
        return new StateInfo();
    }

    //Computes the stateID from a state via the mapper function below. 
    //A stateID is unique to each position-velocity combo
    public int getStateIDFromState(State state) {

        StateInfo info = getStateInfoFromPosition(state.position);
        return info.stateID + (state.velocity.x - info.minVelocityX) * (info.maxVelocityY - info.minVelocityY + 1) + state.velocity.y - info.minVelocityY;
    }

    //Finds the stateInfo with the corresponding position
    public StateInfo getStateInfoFromPosition(Position pos) {

        for (StateInfo info : stateInfos) {
            if (info.position.x == pos.x && info.position.y == pos.y) {
                return info;
            }
        }
        System.out.println("Error: did not find stateinfo for position: " + pos.x + ", " + pos.y);
        System.exit(0);
        return new StateInfo();
    }

    //returns the highest possible stateID
    public int getMaxState() {

        StateInfo info = stateInfos.get(stateInfos.size() - 1);
        return info.stateID + (info.maxVelocityX - info.minVelocityX) * (info.maxVelocityY - info.minVelocityY + 1) + (info.maxVelocityY - info.minVelocityY);
    }
}

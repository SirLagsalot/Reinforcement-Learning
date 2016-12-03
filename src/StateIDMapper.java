
import java.util.ArrayList;

public class StateIDMapper {

    ArrayList<StateInfo> stateInfos = new ArrayList<>();

    public StateIDMapper(char[][] track){
        int startingStateID = 0;
        for (int row = 0; row < track.length; row++) {
            for (int col = 0; col < track[0].length; col++) {
                char curPosIdentifier = track[row][col];
                if(curPosIdentifier == '.' || curPosIdentifier == 'S' || curPosIdentifier == 'F'){
                    StateInfo info = new StateInfo();
                    if(curPosIdentifier == 'S'){
                        info.isStart = true;
                    }
                    else if(curPosIdentifier == 'F'){
                        info.isFinal = true;
                    }
                    info.position = new Position();
                    info.position.y = row;
                    info.position.x = col;
                    info.stateID = startingStateID;
                    info.maxVelocityX = findVelocityBound(true, true, track, row, col);
                    info.maxVelocityY = findVelocityBound(false, true, track, row, col);
                    info.minVelocityX = findVelocityBound(true, false, track, row, col);
                    info.minVelocityY = findVelocityBound(false, false, track, row, col);
                    startingStateID += (info.maxVelocityX - info.minVelocityX + 1)*(info.maxVelocityY - info.minVelocityY + 1);
                }
            }
        }
    }
    
    public int computeStateIDFromStateAndStateInfo(State state, StateInfo stateInfo){
        return stateInfo.stateID + (state.Vx-stateInfo.minVelocityX)*(stateInfo.maxVelocityY-stateInfo.minVelocityY)+state.Vy-stateInfo.minVelocityY;
    }
    
    private int findVelocityBound(boolean isXDirection, boolean isMax, char[][] track, int posY, int posX){
        if(isXDirection){
        for (int a = 1; a < 6; a++) {
            if(!isMax){
                a = -a;
            }
            for (int prime = - 5; prime < 5+1; prime++) {//TODO fix this stuff
                if(posY + prime >= track.length || posY +prime < 0){
                    continue;
                }
                else if(prime == 5){
                    return a;
                }
                else if(track[posX-a][posY+prime] != '#')
                    break;
            }
            if(!isMax){
                a = -a;
            }
        }
        }
        
        for (int a = 1; a < 6; a++) {
            if(!isMax){
                a = -a;
            }
            for (int prime = - 5; prime < 5+1; prime++) {
                if(posY - a >= track.length || posY - a < 0){
                    return a;
                }
                else if(track[posX+prime][posY-a] != '#')
                    break;
                else if(prime == 5){
                    return a;
                }
            }
            if(!isMax){
                a = -a;
            }
        }
        
        if (isMax) {
            return 5;
        }
        return -5;
    }
    
    public State GetStateFromID(int stateID) {
        State state = new State();
        StateInfo info = getStateInfoFromID(stateID);
        state.position = info.position;
        int currentId = info.stateID;
        
        //TODO
        for(int xVelocity = info.minVelocityX; xVelocity <= info.maxVelocityX; xVelocity++){
            for (int yVelocity = info.minVelocityY; yVelocity < info.maxVelocityY; yVelocity++) {
                if(currentId == stateID){
                    state.Vx = xVelocity;
                    state.Vy = yVelocity;
                    return state;
                }
                currentId++;
            }
        }

        return state;
    }

    private StateInfo getStateInfoFromID(int stateID) {
        if (stateID >= stateInfos.get(stateInfos.size() - 1).stateID) {
            return stateInfos.get(stateInfos.size() - 1);
        }

        int currentIndex;
        currentIndex = (stateInfos.size() - 1) / 2;
        int maxBound = stateInfos.size() - 1;
        int minBound = 0;
        while (minBound >= 0) {
            StateInfo curInfo = stateInfos.get(currentIndex);
            if (curInfo.stateID <= stateID) {
                if (stateInfos.get(currentIndex + 1).stateID > stateID) {
                    return curInfo;
                } else {
                    minBound = currentIndex;
                    currentIndex = (maxBound + minBound) / 2;
                }
            } else {
                maxBound = currentIndex;
                currentIndex = (maxBound + minBound) / 2;
            }
        }
        return new StateInfo();
    }

    public int getStateIDFromState(State state) {
        StateInfo info = getStateInfoFromPosition(state.position);
        return info.stateID + (state.Vx - info.minVelocityX) * (info.maxVelocityY - info.minVelocityY) + state.Vy - info.minVelocityY;
    }

    public StateInfo getStateInfoFromPosition(Position pos) {
        for (StateInfo info : stateInfos) {
            if (info.position.x == pos.x && info.position.y == pos.y) {
                return info;
            }
        }
        return new StateInfo();
    }

}

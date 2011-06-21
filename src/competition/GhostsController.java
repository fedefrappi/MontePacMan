package competition;

import core.GameStateInterface;

//Interface to create a ghost controller. Implement this and fill in the AI logic to determine the
//next directions to take
public interface GhostsController {
    int[] getActions(GameStateInterface gs);
}
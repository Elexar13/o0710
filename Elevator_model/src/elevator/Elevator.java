package elevator;

import java.util.ArrayList;
import java.util.Collections;

public class Elevator {
    final static int maxValue = 5;
    int numberOfFloor;
    int neededFloor;
    int peopleInside ;
    ArrayList<Human> humansOnElevator;
    boolean movementUp;

    Elevator(int numberOfFloor, int neededFloor){
        movementUp = true;
        humansOnElevator = new ArrayList<>();
        this.numberOfFloor = numberOfFloor;
        this.neededFloor = neededFloor;
    }

    void addHuman(Human human){
        humansOnElevator.add(human);
        peopleInside++;
        Collections.sort(humansOnElevator);
    }
}

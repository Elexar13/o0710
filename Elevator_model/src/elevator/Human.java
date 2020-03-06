package elevator;

public class Human implements Comparable<Human>{
    int neededNumberOfFloor;

    Human(int neededNumberOfFloor){
        this.neededNumberOfFloor = neededNumberOfFloor;
    }

    @Override
    public int compareTo(Human human) {
        if(this.neededNumberOfFloor>human.neededNumberOfFloor){
            return 1;
        } else if(this.neededNumberOfFloor<human.neededNumberOfFloor){
            return -1;
        }
        return 0;
    }
}

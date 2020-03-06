package elevator;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


import java.util.*;
public class Controller {
    @FXML private AnchorPane pane;

    @FXML private Button startButton;
    static int paneY = 0;
    private int numberOfFloors;
    private ArrayList<Integer> numberOfPeople;
    private ArrayList<Label> numberOfPeopleView;
    ArrayList<Human> humans = new ArrayList<>();
    Label elevatorLabel;
    ArrayList<Label> infoLabels = new ArrayList<>();

    HashMap<Integer, ArrayList<Human>> porch = new HashMap<>();//коллекция из номеров этажей и людьми на этих этажах
    int floor = 0;
    Elevator elevator;
    int etap = 0;//3 этапа, посадка, движение, высадка
    int tempSize;
    @FXML
    void initialize() {
        elevatorLabel = new Label();
        numberOfPeople = new ArrayList<>();
        numberOfPeopleView = new ArrayList<>();
        numberOfFloors = (int)(5 + Math.random()*16); //рандомная генерация количества этажей
        System.out.println(numberOfFloors);
        createNumberOfPeople();
        paneY+=(numberOfFloors*38)+40;
        Main.y = paneY;
        pane.setPrefSize(120, paneY);
        showPeople();
            startButton.setOnAction(e->{
                etap++;
                try {
                    tempSize = porch.get(floor).size();
                }catch (NullPointerException ex){
                    tempSize = 0;
                }
                    if (etap==3){
                    //высадка
                    export();
                    etap = 0;
                }
                    if (etap==2){
                        //движение
                        startButton.setText("Высадить пассажиров");
                        elevatorLabel.setLayoutY((numberOfFloors- floor)*35);
                    }
                    if (etap==1){
                        //посадка
                        input();
                        System.out.println("i "+ floor);
                        if (elevator.neededFloor< floor) elevator.movementUp = false;//определение направления
                        if (elevator.neededFloor> floor) elevator.movementUp = true;
                        if(elevator.movementUp) floor++;
                        else floor--;
                        System.out.println(elevator.movementUp);
                    }
            });
    }
    public void input(){
        startButton.setText("Двигаться дальше");
        for (int j = tempSize-1; j >= 0; j--){//j - номер человека
            if (elevator.peopleInside==Elevator.maxValue)  break;
            elevator.addHuman(porch.get(floor).get(j));
            porch.get(floor).remove(j);
        }
        if (elevator.movementUp){//определение наибольшего/наименьшего нужного этажа
            if (elevator.humansOnElevator.size()>0)
            elevator.neededFloor = elevator.humansOnElevator.get(elevator.humansOnElevator.size()-1).neededNumberOfFloor;
            else
                elevator.neededFloor = findPeople();
        }
        else {
            if (elevator.humansOnElevator.size()>0)
                elevator.neededFloor = elevator.humansOnElevator.get(0).neededNumberOfFloor;
            else
                elevator.neededFloor = findPeople();
        }
        elevatorLabel.setText(Integer.toString(elevator.peopleInside));
        numberOfPeopleView.get(floor).setText(Integer.toString(porch.get(floor).size()));
        createNeededFloor();
        createTooltip();
        createTooltipForElevator();
    }

    void export(){
        startButton.setText("Запустить пассажиров");
        if(elevator.peopleInside>0){
            for (Human human : elevator.humansOnElevator){
                if (human.neededNumberOfFloor== floor){
                    porch.get(floor).add(human);
                }
            }
            for (Iterator<Human> iterator = elevator.humansOnElevator.iterator(); iterator.hasNext(); ) {
                Human h = iterator.next();
                if (floor == h.neededNumberOfFloor) {
                    elevator.peopleInside--;
                    iterator.remove();
                }
            }
        }
        elevatorLabel.setText(Integer.toString(elevator.peopleInside));
        numberOfPeopleView.get(floor).setText(Integer.toString(porch.get(floor).size()));
        createNeededFloor();
        createTooltip();
        createTooltipForElevator();
    }

    void createNumberOfPeople(){//рандомная генерация людей на каждом этаже
        int neededFloor;
        for (int i = 0; i < numberOfFloors; i++) {
            numberOfPeople.add((int)(Math.random()*11));
            for (int j = 0; j < numberOfPeople.get(i); j++){
                do{
                    neededFloor = (int) (Math.random() * numberOfFloors);
                }while(neededFloor==i);
                humans.add(new Human(neededFloor));
            }
            porch.put(i, new ArrayList<>(humans));
            humans.clear();
            numberOfPeopleView.add(new Label(Integer.toString(numberOfPeople.get(i))));
            infoLabels.add(new Label(Integer.toString(i)));
            elevator = new Elevator(0,0);
        }
    }

    int findPeople(){
        int i = 0;
        for (ArrayList<Human> list : porch.values()){
            if (list.size()>0)  return i;
                i++;
        }
        return 0;
    }
    void createNeededFloor(){
        int neededFloor;
            for (int j = 0; j < porch.get(floor).size(); j++){
                do{
                    neededFloor = (int) (Math.random() * numberOfFloors);
                }while(neededFloor== floor);
                porch.get(floor).get(j).neededNumberOfFloor=neededFloor;
            }
    }
    void createTooltip(){
        String text;
        text = "Всего " + porch.get(floor).size() + " человека\n";
        for (int j = 0; j < porch.get(floor).size(); j++) {
            text += j + "-му нужно на " + porch.get(floor).get(j).neededNumberOfFloor + " этаж\n";
        }
        numberOfPeopleView.get(floor).setTooltip(new Tooltip(text));
    }
    void createTooltipForElevator(){
        String text;
        text = "Всего " + elevator.peopleInside + " человека\n";
        for (int j = 0; j < elevator.peopleInside; j++) {
            text += j + "-му нужно на " + elevator.humansOnElevator.get(j).neededNumberOfFloor + " этаж\n";
        }
        elevatorLabel.setTooltip(new Tooltip(text));
    }

    public void showPeople(){//отрисовка количества людей на дисплее
        String text;
        for (int i = numberOfFloors-1; i >= 0; i--){
            text = "Всего "+porch.get(i).size()+" человека\n";
            for(int j =0; j < porch.get(i).size();j++){
                text+=j+"-му нужно на "+porch.get(i).get(j).neededNumberOfFloor+" этаж\n";
            }
            numberOfPeopleView.get(i).setStyle("-fx-background-color: burlywood;");
            numberOfPeopleView.get(i).setAlignment(Pos.CENTER);
            numberOfPeopleView.get(i).setMinSize(20,5);
            numberOfPeopleView.get(i).setMaxSize(35,25);
            numberOfPeopleView.get(i).setLayoutY((numberOfFloors-i)*35);
            numberOfPeopleView.get(i).setLayoutX(80);
            numberOfPeopleView.get(i).setTooltip(new Tooltip(text));
            infoLabels.get(i).setText("Этаж "+i);
            infoLabels.get(i).setAlignment(Pos.CENTER);
            infoLabels.get(i).setMinSize(20,5);
            infoLabels.get(i).setMaxSize(50,25);
            infoLabels.get(i).setLayoutY((numberOfFloors-i)*35);
            infoLabels.get(i).setLayoutX(15);
            pane.getChildren().addAll(numberOfPeopleView.get(i), infoLabels.get(i));

        }
        startButton.setLayoutY(paneY-25);
        startButton.setLayoutX(50);
        startButton.setText("Запустить пассажиров");
        startButton.setMaxSize(250, 30);
        startButton.setMinSize(150, 30);
        elevatorLabel.setLayoutX(130);
        elevatorLabel.setLayoutY((numberOfFloors)*35);
        elevatorLabel.setMaxSize(50, 25);
        elevatorLabel.setMinSize(50, 25);
        elevatorLabel.setTextFill(Color.web("#FFFFFF"));
        elevatorLabel.setFont(Font.font(14));
        elevatorLabel.setStyle("-fx-background-color: darkgrey;");
        elevatorLabel.setAlignment(Pos.CENTER);
        pane.getChildren().add(elevatorLabel);
    }

}

package model;

public class Delivery {
    private String name;
    private int quantityOfWorkers;

    public Delivery(String name, int quantityOfWorkers) {
        this.name = name;
        this.quantityOfWorkers = quantityOfWorkers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantityOfWorkers() {
        return quantityOfWorkers;
    }

    public void setQuantityOfWorkers(int quantityOfWorkers) {
        this.quantityOfWorkers = quantityOfWorkers;
    }
}

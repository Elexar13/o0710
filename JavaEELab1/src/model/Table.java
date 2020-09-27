package model;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@SessionScoped
public class Table implements Serializable {

    private List<Integer> id;
    private List<Edition> editions;
    private List<Recipient> recipients;
    private List<Delivery> deliveries;
    public static int maxId;

    public Table(){
        editions = new ArrayList<>();
        recipients = new ArrayList<>();
        deliveries = new ArrayList<>();
        id = new ArrayList<>();

        maxId = 5;
        IntStream.range(1, 5+1).forEach(i -> id.add(i));

        editions.add(new Edition("ArtHuss", "Київ"));
        editions.add(new Edition("Caravela", "Київ"));
        editions.add(new Edition("Laurus Press", "Львів"));
        editions.add(new Edition("New Time", "Харків"));
        editions.add(new Edition("Аверс", "Львів"));

        recipients.add(new Recipient("Олександр Морозов", "Миколавїв"));
        recipients.add(new Recipient("Валентин Стрикало", "Житомир"));
        recipients.add(new Recipient("Юлія Коробка", "Київ"));
        recipients.add(new Recipient("Матвій Конюхов", "Херсон"));
        recipients.add(new Recipient("Валерій Лабковський", "Миколавїв"));

        deliveries.add(new Delivery("Нова Пошта", 30000));
        deliveries.add(new Delivery("Укрпошта", 13000));
        deliveries.add(new Delivery("Нова Пошта", 30000));
        deliveries.add(new Delivery("Интайм ", 9000));
        deliveries.add(new Delivery("Нова Пошта", 30000));

    }

    public void setId(List<Integer> id) {
        this.id = id;
    }

    public List<Integer> getId() {
        return id;
    }
    public List<Edition> getEditions() {
        return editions;
    }

    public List<Recipient> getRecipients() {
        return recipients;
    }

    public List<Delivery> getDeliveries() {
        return deliveries;
    }
}

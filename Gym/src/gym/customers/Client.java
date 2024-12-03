package gym.customers;

import java.util.ArrayList;
import java.util.List;

public class Client extends Person {
    private List<String> massages = new ArrayList<>();

    public Client(Person person) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthDate());
        this.setId(person.getId()); // Preserve the ID
    }

    public void notify(String massage){
        massages.add(massage);
    }

    public String getNotifications() {
        return "[" + String.join(", " ,massages ) + "]";
    }


    @Override
    public String toString() {
        return super.toString();
    }
}

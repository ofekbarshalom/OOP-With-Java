package gym.customers;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private Person person; // Use composition instead of inheritance
    private List<String> messages = new ArrayList<>();

    public Client(Person person) {
        this.person = person; // Reference the existing Person object
    }

    public Person getPerson() {
        return person;
    }

    public void notify(String message) {
        messages.add(message);
    }

    public String getNotifications() {
        return "[" + String.join(", ", messages) + "]";
    }

    public String getName(){
        return this.person.getName();
    }

    @Override
    public String toString() {
        return person.toString();
    }
}

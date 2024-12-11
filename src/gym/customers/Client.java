package gym.customers;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Client in the gym system, implementing the Observer interface.
 * A Client is associated with a Person object.
 */
public class Client implements Observer {

    private Person person;
    private List<String> messages = new ArrayList<>(); // List to store messages for the client

    /**
     * Constructs a new Client object associated with a specific Person.
     *
     * @param person The Person object to associate with this Client.
     */
    public Client(Person person) {
        this.person = person;
    }

    /**
     * Retrieves the Person object associated with this Client.
     *
     * @return The Person object linked to this Client.
     */
    public Person getPerson() {
        return person;
    }

    @Override
    public void notify(String message) {
        messages.add(message);
    }

    /**
     * Retrieves all notifications for the client as a string.
     *
     * @return A string containing all notifications, separated by commas.
     */
    public String getNotifications() {
        return "[" + String.join(", ", messages) + "]";
    }

    /**
    Getter method to retrieve the client's name via the associated Person.
     *
     * @return The name of the Person object linked to this Client.
     */
    public String getName() {
        return this.person.getName();
    }

    @Override
    public String toString() {
        return person.toString();
    }
}

package gym.management.Sessions;

/**
 * Represents a type of gym session, including its name, price, and maximum number of participants.
 */
public class SessionType {
    private final String name;
    private final int price;
    private final int maxPeople;

    /**
     * Constructs a new SessionType with the specified name, price, and maximum number of participants.
     *
     * @param name the name of the session type
     * @param price the price of the session
     * @param maxPeople the maximum number of participants in the session
     */
    public SessionType(String name, int price, int maxPeople) {
        this.name = name;
        this.price = price;
        this.maxPeople = maxPeople;
    }

    /**
     * Gets the name of the session type.
     *
     * @return the name of the session type
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price of the session.
     *
     * @return the price of the session
     */
    public int getPrice() {
        return price;
    }

    /**
     * Gets the maximum number of participants allowed in the session.
     *
     * @return the maximum number of participants
     */
    public int getMaxPeople() {
        return maxPeople;
    }

    @Override
    public String toString() {
        return name;
    }

    // Predefined session types for gym sessions used in the main.
    public static final SessionType Pilates = new SessionType("Pilates", 60, 30);
    public static final SessionType MachinePilates = new SessionType("MachinePilates", 80, 10);
    public static final SessionType ThaiBoxing = new SessionType("ThaiBoxing", 100, 20);
    public static final SessionType Ninja = new SessionType("Ninja", 150, 5);
}


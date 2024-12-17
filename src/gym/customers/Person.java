package gym.customers;

import gym.management.Gender;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Person in the gym system, with personal details such as name, balance, gender, and birth date.
 * Each person is assigned a unique ID and supports operations such as adding or deducting money from their balance
 * and calculating their age.
 */
public class Person {
    private static int nextId = 1111; // * Starting ID for auto-generated unique IDs *//
    private int id;
    private String name;
    private Gender gender;
    private String birthDate;
    private int balance;

    /**
     * Constructs a new Person object with the specified details.
     *
     * @param name      The name of the person.
     * @param balance   The initial balance of the person.
     * @param gender    The gender of the person.
     * @param birthDate The birthdate of the person in "dd-MM-yyyy" format.
     */
    public Person(String name, int balance, Gender gender, String birthDate) {
        this.id = nextId++;
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    /**
    Getter for the unique ID of the person.
    return:
        The unique ID assigned to this person.
    **/
    public int getId() {
        return id;
    }

    /**
     * Getter for the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for the current balance of the person.
     *
     * @return The balance of the person.
     */
    public int getBalance() {
        return this.balance;
    }

    /**
     * Getter for the gender of the person.
     *
     * @return The gender of the person.
     */
    public Gender getGender() {
        return this.gender;
    }

    /**
     * Deducts a specified amount of money from the person's balance.
     *
     * @param money The amount to deduct.
     */
    public void takeMoney(int money) {
        balance -= money;
    }

    /**
     * Adds a specified amount of money to the person's balance.
     *
     * @param money The amount to add.
     */
    public void addMoney(int money) {
        balance += money;
    }

    /**
     * Getter for the birth date of the person.
     *
     * @return The birth date of the person in "dd-MM-yyyy" format.
     */
    public String getBirthDate() {
        return this.birthDate;
    }

    /**
     * Calculates the age of the person as of a fixed comparison date ("03-12-2024").
     *
     * @return The age of the person in years, with partial years rounded.
     */
    public int getAge() {
        double totalAge = getExactAge();

        if (totalAge - Math.floor(totalAge) >= 0.9) {
            return (int) Math.ceil(totalAge);
        } else {
            return (int) Math.floor(totalAge);
        }
    }

    public double getExactAge(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthDate = LocalDate.parse(this.birthDate, formatter);
        LocalDate comparisonDate = LocalDate.parse("03-12-2024", formatter);

        Period period = Period.between(birthDate, comparisonDate);

        double years = period.getYears();
        double months = period.getMonths() / 12.0;
        double days = period.getDays() / 365.0;

        double totalAge = years + months + days;

        return totalAge;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Gender: " + gender + " | Birthday: " + birthDate + " | Age: " + this.getAge() + " | Balance: " + balance;
    }

    /**
     * Checks if this person is the same as another person (represented as a Client).
     *
     * @param c The client to compare with.
     * @return True if the ID, name, birth date, gender, and balance match; otherwise false.
     */
    public boolean isTheSamePerson(Client c) {
        return id == c.getPerson().getId() && name.equals(c.getPerson().getName()) &&
                birthDate.equals(c.getPerson().getBirthDate()) && gender == c.getPerson().getGender() && balance == c.getPerson().getBalance();
    }
}

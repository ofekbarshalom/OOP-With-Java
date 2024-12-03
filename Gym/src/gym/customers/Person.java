package gym.customers;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Person {
    private static int nextId = 1111; // Starting ID
    private int id;
    private String name;
    private gym.Gender gender;
    private String birthDate;
    private int balance;

    public Person(String name, int balance, gym.Gender gender, String birthDate) {
        this.id = nextId++;
        this.name = name;
        this.balance = balance;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public int getId(){return id;}

    public String getName() {
        return this.name;
    }

    public int getBalance() {
        return this.balance;
    }

    public gym.Gender getGender() {
        return this.gender;
    }

    public void takeMoney(int money){
        balance -= money;
    }

    public void addMoney(int money){
        balance += money;
    }

    public String getBirthDate() {
        return this.birthDate;
    }

    public int getAge() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate birthDate = LocalDate.parse(this.birthDate, formatter);
        LocalDate comparisonDate = LocalDate.parse("03-12-2024", formatter);

        Period period = Period.between(birthDate, comparisonDate);

        double years = period.getYears();
        double months = period.getMonths() / 12.0;
        double days = period.getDays() / 365.0;

        double totalAge = years + months + days;

        if (totalAge - Math.floor(totalAge) >= 0.9) {
            return (int) Math.ceil(totalAge);
        } else {
            return (int) Math.floor(totalAge);
        }
    }


    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Gender: " + gender + " | Birthday: " + birthDate + " | Age: " + this.getAge() + " | Balance: " + balance;
    }

    public boolean isTheSamePerson(Client c) {
        return id == c.getPerson().getId() && name.equals(c.getPerson().getName()) &&
                birthDate.equals(c.getPerson().getBirthDate()) && gender == c.getPerson().getGender() && balance == c.getPerson().getBalance();
    }
}

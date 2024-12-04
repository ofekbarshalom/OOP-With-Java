package gym.management;

import gym.customers.Person;
import java.util.List;

public class Instructor {
    private Person person; // Use composition instead of inheritance
    private int hourlyRate;
    private List<SessionType> specialties;

    public Instructor(Person person, int hourlyRate, List<SessionType> specialties) {
        this.person = person; // Reference the existing Person object
        this.hourlyRate = hourlyRate;
        this.specialties = specialties;
    }

    public Person getPerson() {
        return person;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public List<SessionType> getSpecialties() {
        return specialties;
    }

    @Override
    public String toString() {
        return person.toString() + " | Role: Instructor" + " | Salary per Hour: " + hourlyRate + " | Certified Classes: " + specialtiesToString(specialties);
    }

    public static String specialtiesToString(List<SessionType> specialties) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < specialties.size(); i++) {
            sb.append(specialties.get(i).toString());
            if (i != specialties.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}

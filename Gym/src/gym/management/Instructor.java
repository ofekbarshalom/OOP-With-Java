package gym.management;
import gym.customers.*;
import java.util.List;

public class Instructor extends Person {
    private int hourlyRate;
    private List<SessionType> specialties;

    public Instructor(Person person, int hourlyRate, List<SessionType> specialties) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthDate());
        this.setId(person.getId()); // Preserve the ID
        this.hourlyRate = hourlyRate;
        this.specialties = specialties;
    }

    public int getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public List<SessionType> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<SessionType> specialties) {
        this.specialties = specialties;
    }

    @Override
    public String toString() {
        return super.toString() + " | Role: Instructor" + " | Salary per Hour: " + hourlyRate + " | Certified Classes: " + specialtiesToString(specialties);
    }

    public static String specialtiesToString(List<SessionType> specialties){
        String s = "";
        for (int i = 0; i < specialties.size(); i++) {
            s += specialties.get(i).toString();
            if(i != specialties.size()-1){
                s += ", ";
            }
        }
        return s;
    }
}

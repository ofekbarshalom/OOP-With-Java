package gym.management;
import gym.customers.Client;
import gym.customers.Person;
import gym.management.gymSecretary;
import java.util.ArrayList;
import java.util.List;

public class Gym {
    private static Gym instance;
    private String name;
    private gym.management.gymSecretary gymSecretary;
    private List<Client> clients = new ArrayList<>();
    private List<Instructor> instructors = new ArrayList<>();
    private List<Sessions> sessions = new ArrayList<>();
    private int gymBalance = 0;
    private List<String> history = new ArrayList<>();



    private Gym() {}

    public static Gym getInstance() {
        if(instance == null){
            instance = new Gym();
        }
        return instance;
    }
    public void addAction(String action) {history.add(action);}
    public void setName(String name) {
        this.name = name;
    }
    public void setSecretary(Person p, int salary) {
        this.gymSecretary = new gymSecretary(p,salary);
    }
    public List<String> getHistory() {
        return history;
    }
    public gymSecretary getSecretary() {
        return gymSecretary;
    }
    public List<Client> getClients() {
        return clients;
    }
    public List<Instructor> getInstructors() {return instructors;}
    public List<Sessions> getSessions(){return sessions;}
    public void addMoney(int money){
        this.gymBalance += money;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Gym Name: ").append(name).append("\n");
        sb.append("Gym Secretary: ").append(gymSecretary).append("\n");
        sb.append("Gym Balance: ").append(gymBalance).append("\n\n");

        sb.append("Clients Data:\n");
        for (Client client : clients) {
            sb.append(client).append("\n");
        }

        sb.append("\nEmployees Data:\n");
        for (Instructor instructor : instructors) {
            sb.append(instructor).append("\n");
        }
        sb.append(gymSecretary).append("\n");

        sb.append("\nSessions Data:\n");
        for (Sessions session : sessions) {
            sb.append(session).append("\n");
        }

        return sb.toString();
    }
}

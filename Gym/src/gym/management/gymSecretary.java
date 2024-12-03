package gym.management;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import gym.Exception.*;
import gym.Gender;
import gym.customers.Client;
import gym.customers.Person;


public class gymSecretary {
    private Person person;
    private int salary;

    public gymSecretary(Person person, int salary) {
        this.person = person;
        this.salary = salary;
        Gym gym = Gym.getInstance();
        gym.addAction("A new secretary has started working at the gym: " + person.getName());
    }

    public Person getPerson() {return person;}

    public int getSalary() {return salary;}

    public void setPerson(Person person) {this.person = person;}

    public void setSalary(int salary) {this.salary = salary;}

    public void isGymSecretary() throws NullPointerException {
        Gym gym = Gym.getInstance();
        if(!gym.getSecretary().equals(this)){
            throw new NullPointerException();
        }
    }

    public static String formatToISO8601(String date) {
        // Define input and output formatters
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        // Parse and format the date
        LocalDateTime sessionDateTime = LocalDateTime.parse(date, inputFormatter);
        return sessionDateTime.format(outputFormatter);
    }


    public Client registerClient(Person p) throws InvalidAgeException, DuplicateClientException {
        isGymSecretary();
        Gym gym = Gym.getInstance();
        List<Client> clients = gym.getClients();

        if (p.getAge() < 18) {
            throw new InvalidAgeException("Error: Client must be at least 18 years old to register");
        }

        for (Client client : clients) {
            if (p.isTheSamePerson(client)) {
                throw new DuplicateClientException("Error: The client is already registered");
            }
        }

        Client newClient = new Client(p);
        clients.add(newClient);

        gym.addAction("Registered new client: " + p.getName());
        return newClient;
    }

    public void unregisterClient(Client c) throws ClientNotRegisteredException {
        isGymSecretary();
        Gym gym = Gym.getInstance();
        List<Client> clients = gym.getClients();

        // Throw an exception if the client is not found in the list
        if (!clients.remove(c)) {
            throw new ClientNotRegisteredException("Error: Registration is required before attempting to unregister");
        }

        // Log the unregistration action if the client was successfully removed
        gym.addAction("Unregistered client: " + c.getName());
    }


    public Instructor hireInstructor(Person person, int hourlyWage, ArrayList<SessionType> sessions) {
        isGymSecretary();
        Instructor newInstructor = new Instructor(person, hourlyWage, sessions);

        Gym gym = Gym.getInstance();
        List<Instructor> instructors = gym.getInstructors();
        instructors.add(newInstructor);
        gym.addAction("Hired new instructor: " + person.getName() + " with salary per hour: " + hourlyWage);
        return newInstructor;
    }

    public Sessions addSession(SessionType sessionType, String date, ForumType forumType, Instructor instructor) throws InstructorNotQualifiedException {
        isGymSecretary();
        Gym gym = Gym.getInstance();
        List<Sessions> sessions = gym.getSessions();

        if (!instructor.getSpecialties().contains(sessionType)) {
            throw new InstructorNotQualifiedException("Error: Instructor is not qualified to conduct this session type.");
        } else {
            String formattedDate = formatToISO8601(date);
            // Create the new session
            Sessions newSession = new Sessions(sessionType, date, forumType, instructor);
            sessions.add(newSession);

            // Construct the action log
            String actionLog = String.format(
                    "Created new session: %s on %s with instructor: %s",
                    sessionType, formattedDate, instructor.getName()
            );
            gym.addAction(actionLog);
            return newSession;
        }
    }


    public void registerClientToLesson(Client c, Sessions s) throws DuplicateClientException, ClientNotRegisteredException {
        isGymSecretary();
        Gym gym = Gym.getInstance();
        List<Client> clients = gym.getClients();

        if (s.getTrainees().contains(c)) {
            throw new DuplicateClientException("Error: The client is already registered for this lesson");
        }

        if (!clients.contains(c)) {
            throw new ClientNotRegisteredException("Error: The client is not registered with the gym and cannot enroll in lessons");
        }

        // Check if time is correct
        if (!s.hasDatePassed()) {
            gym.addAction("Failed registration: Session is not in the future");
            return;
        }

        // Check if the forum is correct
        if (!checkClientForm(c, s.getForumType())) {
            return;
        }

        // Check if there are available places left
        if (s.getTrainees().size() >= maxClientsInLesson(s.getSessionType())) {
            gym.addAction("Failed registration: No available spots for session");
            return;
        }

        // Check if the client has enough balance to pay
        int price = LessonPrice(s.getSessionType());
        if (price > c.getBalance()) {
            gym.addAction("Failed registration: Client doesn't have enough balance");
            return;
        }

        // Register the client to the session
        s.addTrainee(c);
        c.takeMoney(price);
        gym.addMoney(price);

        String actionLog = String.format(
                "Registered client: %s to session: %s on %s for price: %d",
                c.getName(),
                s.getSessionType(),
                formatToISO8601(s.getDate()),
                price
        );
        gym.addAction(actionLog);
    }

    public int maxClientsInLesson(SessionType sessionType){
        if(sessionType == SessionType.Pilates) {return 30;}
        if(sessionType == SessionType.MachinePilates) {return 10;}
        if(sessionType == SessionType.ThaiBoxing) {return 20;}
        else{return 5;} // SessionType.Ninja
    }

    public boolean checkClientForm(Client c, ForumType forumType){
        Gym gym = Gym.getInstance();
        if(forumType == ForumType.All){
            return true;
        }
        if((forumType == ForumType.Male && !(c.getGender() == Gender.Male)) ||
                (forumType == ForumType.Female && !(c.getGender() == Gender.Female))){
            gym.addAction("Failed registration: Client's gender doesn't match the session's gender requirements");

            return false;
        }
        if(forumType == ForumType.Seniors && c.getAge() < 65){
            gym.addAction("Failed registration: Client doesn't meet the age requirements for this session (Seniors)");
            return false;
        }
        return true;
    }

    public int LessonPrice(SessionType sessionType){
        if(sessionType == SessionType.Pilates) {return 60;}
        if(sessionType == SessionType.MachinePilates) {return 80;}
        if(sessionType == SessionType.ThaiBoxing) {return 100;}
        else{return 150;} // SessionType.Ninja
    }

    public void notify(Sessions session, String message) {
        isGymSecretary();
        Gym gym = Gym.getInstance();

        for (Client c : session.getTrainees()) {
            c.notify(message);
        }

        String formattedMessage = String.format(
                "A message was sent to everyone registered for session %s on %s: %s",
                session.getSessionType(),
                formatToISO8601(session.getDate()),
                message
        );

        gym.addAction(formattedMessage);
    }


    public void notify(String day, String message){
        isGymSecretary();
        Gym gym = Gym.getInstance();
        List<Sessions> sessions = gym.getSessions();

        for(Sessions session : sessions){
            if(session.getDay().equals(day)){
                for(Client client : session.getTrainees()){
                    client.notify(message);
                }
            }
        }
        gym.addAction("A message was sent to everyone registered for a session on " + day + " : " + message);
    }

    public void notify(String message){
        isGymSecretary();
        Gym gym = Gym.getInstance();
        List<Client> clients = gym.getClients();
        for (Client client : clients){
            client.notify(message);
        }

        gym.addAction("A message was sent to all gym clients: " + message);
    }

    public void paySalaries() {
        isGymSecretary();
        Gym gym = Gym.getInstance();
        List<Sessions> sessions = gym.getSessions();
        List<Instructor> instructors = gym.getInstructors();

        gym.takeMoney(salary); // pay the secretary
        person.addMoney(salary);

        for(Instructor instructor : instructors){  // pay the instructors
            gym.takeMoney(instructor.getHourlyRate());
            instructor.addMoney(instructor.getHourlyRate());
        }
        gym.addAction("Salaries have been paid to all employees");
    }

    public void printActions() {
        isGymSecretary();
        Gym gym = Gym.getInstance();
        for(String action : gym.getHistory()) {
            System.out.println(action);
        }
    }

    @Override
    public String toString() {
        return person.toString() + " | Role: Secretary" + " | Salary per Month: " + salary;
    }

}

package gym.management;
import gym.customers.Client;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Sessions {

    private SessionType sessionType;
    private String date;
    private ForumType forumType;
    private Instructor instructor;
    private List<Client> trainees;

    public Sessions(SessionType sessionType, String date, ForumType forumType, Instructor instructor) {
        this.sessionType = sessionType;
        this.date = date;
        this.forumType = forumType;
        this.instructor = instructor;
        this.trainees = new ArrayList<>();
    }

    public SessionType getSessionType(){return sessionType;}

    public boolean hasDatePassed() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime sessionDateTime = LocalDateTime.parse(this.date, formatter);
        LocalDateTime comparisonDate = LocalDateTime.parse("03-12-2024 00:00", formatter);

        return sessionDateTime.isAfter(comparisonDate);
    }


    public String getDay() {
        return date.substring(0,10);
    }

    public String getDate() {return date;}

    public ForumType getForumType() {return  forumType;}
    public List<Client> getTrainees() {return  trainees;}

    public void addTrainee(Client c) {
        this.trainees.add(c);
    }

    @Override
    public String toString() {
        return "Session Type: " + sessionType +
                " | Date: " + date +
                " | Forum: " + forumType +
                " | Instructor: " + instructor.getName() +
                " | Participants: " + trainees.size() + "/" + maxClientsInLesson();
    }

    public int maxClientsInLesson(){
        if(sessionType == SessionType.Pilates) {return 30;}
        if(sessionType == SessionType.MachinePilates) {return 10;}
        if(sessionType == SessionType.ThaiBoxing) {return 20;}
        else{return 5;} // SessionType.Ninja
    }

    public Instructor getInstructor() {
        return instructor;
    }
}

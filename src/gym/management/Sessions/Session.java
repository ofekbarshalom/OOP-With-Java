package gym.management.Sessions;

import gym.customers.Client;
import gym.management.Instructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private SessionType sessionType; // Type of the session
    private String date; // The date and time of the session in "dd-MM-yyyy HH:mm" format
    private ForumType forumType; // The forum type of the session
    private Instructor instructor; // The instructor conducting the session
    private List<Client> trainees; // List of clients registered for the session

    /**
     * Constructor to initialize a session.
     * @param sessionType The type of session.
     * @param date The date and time of the session in "dd-MM-yyyy HH:mm" format.
     * @param forumType The forum type of the session.
     * @param instructor The instructor conducting the session.
     */
    public Session(SessionType sessionType, String date, ForumType forumType, Instructor instructor) {
        this.sessionType = sessionType;
        this.date = date;
        this.forumType = forumType;
        this.instructor = instructor;
        this.trainees = new ArrayList<>();
    }

    /**
     * Getter for the session type.
     * @return The type of session.
     */
    public SessionType getSessionType() {
        return sessionType;
    }

    /**
     * Checks if the session date has passed compared to a fixed date ("03-12-2024 00:00").
     * @return True if the session date is after the comparison date, otherwise false.
     */
    public boolean hasDatePassed() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime sessionDateTime = LocalDateTime.parse(this.date, formatter);
        LocalDateTime comparisonDate = LocalDateTime.parse("03-12-2024 00:00", formatter);
        return sessionDateTime.isAfter(comparisonDate);
    }

    /**
     * Extracts and returns the day from the session date.
     * @return The day part of the session date in "dd-MM-yyyy" format.
     */
    public String getDay() {
        return date.substring(0, 10);
    }

    /**
     * Getter for the full session date.
     * @return The full date and time of the session.
     */
    public String getDate() {
        return date;
    }

    /**
     * Getter for the forum type of the session.
     * @return The forum type of the session.
     */
    public ForumType getForumType() {
        return forumType;
    }

    /**
     * Getter for the list of trainees registered for the session.
     * @return A list of clients registered for the session.
     */
    public List<Client> getTrainees() {
        return trainees;
    }

    /**
     * Adds a trainee to the session.
     * @param c The client to add as a trainee.
     */
    public void addTrainee(Client c) {
        this.trainees.add(c);
    }

    @Override
    public String toString() {
        return "Session Type: " + sessionType +
                " | Date: " + date +
                " | Forum: " + forumType +
                " | Instructor: " + instructor.getPerson().getName() +
                " | Participants: " + trainees.size() + "/" + maxClientsInLesson();
    }

    /**
     * Determines the maximum number of clients allowed in a session based on its type.
     * @return The maximum number of clients for the session.
     */
    public int maxClientsInLesson() {
        switch (sessionType) {
            case Pilates:
                return 30;
            case MachinePilates:
                return 10;
            case ThaiBoxing:
                return 20;
            case Ninja:
            default:
                return 5;
        }
    }

    /**
     * Determines the price of the session based on its type.
     * @return The price of the session.
     */
    public int LessonPrice() {
        switch (sessionType) {
            case Pilates:
                return 60;
            case MachinePilates:
                return 80;
            case ThaiBoxing:
                return 100;
            case Ninja:
            default:
                return 150;
        }
    }

    /**
     * Getter for the instructor conducting the session.
     * @return The instructor of the session.
     */
    public Instructor getInstructor() {
        return instructor;
    }
}

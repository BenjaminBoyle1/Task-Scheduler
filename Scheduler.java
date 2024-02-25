import java.io.*;
import java.util.*;

// Class to manage tasks and events
class Scheduler {
    private List<Task> tasks; // List to store tasks
    private List<Event> events; // List to store events

    // Constructor to initialize task and event lists
    public Scheduler() {
        tasks = new ArrayList<>();
        events = new ArrayList<>();
    }

    // Method to add a new task
    public void addTask(String description) {
        tasks.add(new Task(description));
    }

    // Method to mark a task as completed
    public void markTaskAsCompleted(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markAsCompleted();
        } else {
            System.out.println("Invalid task index.");
        }
    }

    // Method to add a new event
    public void addEvent(String eventName, Calendar dateTime) {
        events.add(new Event(eventName, dateTime));
    }

    // Method to remove an event
    public void removeEvent(int index) {
        if (index >= 0 && index < events.size()) {
            events.remove(index);
            System.out.println("Event removed successfully.");
        } else {
            System.out.println("Invalid event index.");
        }
    }

    // Method to display tasks
    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("Tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + ": " + tasks.get(i));
            }
        }
    }

    // Method to display events
    public void displayEvents() {
        if (events.isEmpty()) {
            System.out.println("No events found.");
        } else {
            System.out.println("Events:");
            for (int i = 0; i < events.size(); i++) {
                System.out.println(i + ": " + events.get(i));
            }
        }
    }

    // Method to save the calendar to a file
    public void saveCalendar(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Event event : events) {
                writer.write(event.getEventName() + ":" + event.getDateTime().getTimeInMillis());
                writer.newLine();
            }
            System.out.println("Calendar saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving calendar: " + e.getMessage());
        }
    }

    // Method to load the calendar from a file
    public void loadCalendar(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                String eventName = parts[0];
                long dateTimeMillis = Long.parseLong(parts[1]);
                Calendar eventDateTime = Calendar.getInstance();
                eventDateTime.setTimeInMillis(dateTimeMillis);
                events.add(new Event(eventName, eventDateTime));
            }
            System.out.println("Calendar loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading calendar: " + e.getMessage());
        }
    }
}

// Class to represent a task
class Task {
    private String description; // Task description
    private boolean completed; // Task completion status

    // Constructor to initialize task description and completion status
    public Task(String description) {
        this.description = description;
        this.completed = false;
    }

    // Getter method for task description
    public String getDescription() {
        return description;
    }

    // Getter method for task completion status
    public boolean isCompleted() {
        return completed;
    }

    // Method to mark the task as completed
    public void markAsCompleted() {
        this.completed = true;
    }

    // Method to format the task for display
    @Override
    public String toString() {
        return (completed ? "[X] " : "[ ] ") + description;
    }
}

// Class to represent an event
class Event {
    private String eventName; // Event name
    private Calendar dateTime; // Event date and time

    // Constructor to initialize event name and date/time
    public Event(String eventName, Calendar dateTime) {
        this.eventName = eventName;
        this.dateTime = dateTime;
    }

    // Getter method for event name
    public String getEventName() {
        return eventName;
    }

    // Getter method for event date/time
    public Calendar getDateTime() {
        return dateTime;
    }

    // Method to format the event for display
    @Override
    public String toString() {
        return eventName + " - " + dateTime.getTime();
    }
}

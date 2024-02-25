import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Create a new Scheduler object to manage tasks and events
        Scheduler scheduler = new Scheduler();
        
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        
        // Filename for storing the calendar
        String calendarFilename = "calendar.txt";
        
        // Load calendar from file
        scheduler.loadCalendar(calendarFilename);
        
        // Flag to control the main loop
        boolean running = true;
        
        // Main loop for the program
        while (running) {
            // Display menu options
            System.out.println("\nMenu:");
            System.out.println("1. View Events");
            System.out.println("2. Add Event");
            System.out.println("3. Remove Event");
            System.out.println("4. Save and Exit");
            System.out.print("Enter your choice: ");
            
            // Read user choice
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            // Process user choice
            switch (choice) {
                case 1:
                    // Display upcoming events
                    System.out.println("\nUpcoming Events:");
                    scheduler.displayEvents();
                    break;
                case 2:
                    // Add a new event
                    System.out.print("\nEnter event name: ");
                    String eventName = scanner.nextLine();
                    System.out.print("Enter year: ");
                    int year = scanner.nextInt();
                    System.out.print("Enter month (1-12): ");
                    int month = scanner.nextInt();
                    System.out.print("Enter day of month: ");
                    int dayOfMonth = scanner.nextInt();
                    System.out.print("Enter hour (0-23): ");
                    int hour = scanner.nextInt();
                    System.out.print("Enter minute (0-59): ");
                    int minute = scanner.nextInt();
                    // Create a Calendar object for the event date and time
                    Calendar eventDateTime = Calendar.getInstance();
                    eventDateTime.set(year, month - 1, dayOfMonth, hour, minute);
                    // Add the event to the scheduler
                    scheduler.addEvent(eventName, eventDateTime);
                    break;
                case 3:
                    // Remove an event
                    System.out.print("\nEnter the index of the event to remove: ");
                    int eventIndexToRemove = scanner.nextInt();
                    scheduler.removeEvent(eventIndexToRemove);
                    break;
                case 4:
                    // Save calendar to file and exit
                    scheduler.saveCalendar(calendarFilename);
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    // Invalid choice
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        
        // Close the scanner
        scanner.close();
    }
}

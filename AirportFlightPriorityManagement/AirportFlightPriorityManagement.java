import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class Flight {
    String flightNumber;
    String destination;
    int priority;

    Flight(String flightNumber, String destination, int priority) {
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.priority = priority;
    }

    void display() {
        System.out.println("Flight Number: " + flightNumber);
        System.out.println("Destination: " + destination);
        System.out.println("Priority: " + priority);
        System.out.println("-------------------------");
    }
}

public class AirportFlightPriorityManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        PriorityQueue<Flight> flights =
            new PriorityQueue<>(
                Comparator.comparingInt(f -> f.priority)
            );

        int choice;

        do {
            System.out.println("\n=== Airport Flight Priority Management ===");
            System.out.println("1. Add Flight");
            System.out.println("2. View Next Flight");
            System.out.println("3. Process Flight");
            System.out.println("4. Display All Flights");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Flight Number: ");
                    String number = sc.nextLine();

                    System.out.print("Enter Destination: ");
                    String destination = sc.nextLine();

                    System.out.println("1. Emergency");
                    System.out.println("2. Medical/Priority");
                    System.out.println("3. Scheduled");
                    System.out.println("4. Delayed/Low Priority");
                    System.out.print("Enter Priority: ");
                    int priority = sc.nextInt();
                    sc.nextLine();

                    if (priority < 1 || priority > 4) {
                        System.out.println("Invalid priority!");
                        break;
                    }

                    flights.add(
                        new Flight(number, destination, priority)
                    );
                    System.out.println("Flight added successfully!");
                    break;

                case 2:
                    if (flights.isEmpty()) {
                        System.out.println("No flights in queue.");
                    } else {
                        System.out.println("Next Flight:");
                        flights.peek().display();
                    }
                    break;

                case 3:
                    if (flights.isEmpty()) {
                        System.out.println("No flights to process.");
                    } else {
                        Flight f = flights.poll();
                        System.out.println("Processing Flight:");
                        f.display();
                    }
                    break;

                case 4:
                    if (flights.isEmpty()) {
                        System.out.println("No flights in queue.");
                    } else {
                        PriorityQueue<Flight> copy =
                            new PriorityQueue<>(flights);

                        System.out.println("Flights by Priority:");
                        while (!copy.isEmpty()) {
                            copy.poll().display();
                        }
                    }
                    break;

                case 5:
                    System.out.println("Exiting Airport System...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 5);

        sc.close();
    }
}
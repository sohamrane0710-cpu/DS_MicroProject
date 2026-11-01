
import java.util.Scanner;

class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class CircularQueue {
    private Customer[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        queue = new Customer[capacity];

        // Initially, the queue is empty.
        front = 0;
        rear = -1;
        size = 0;
    }

    // Add a customer at the rear of the queue.
    public boolean enqueue(Customer customer) {
        if (isFull()) {
            return false;
        }

        rear = (rear + 1) % capacity;
        queue[rear] = customer;
        size++;

        return true;
    }

    // Remove and return the customer at the front.
    public Customer dequeue() {
        if (isEmpty()) {
            return null;
        }

        Customer customer = queue[front];
        queue[front] = null;

        front = (front + 1) % capacity;
        size--;

        if (size == 0) {
            // Reset indices to a consistent empty state.
            front = 0;
            rear = -1;
        }

        return customer;
    }

    // View the next customer without removing them.
    public Customer peek() {
        if (isEmpty()) {
            return null;
        }

        return queue[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    // Display customers in FIFO order.
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("No customers are waiting.");
            return;
        }

        System.out.println("\n--- Customers Waiting ---");

        for (int i = 0; i < size; i++) {
            int index = (front + i) % capacity;

            System.out.println(
                (i + 1) + ". " + queue[index].getName()
            );
        }

        System.out.println("Total waiting: " + size);
    }
}

public class TicketBookingSystem {
    private static final int QUEUE_CAPACITY = 5;
    private static final int TOTAL_TICKETS = 10;

    private final CircularQueue bookingQueue;
    private int ticketsRemaining;
    private int nextTicketNumber;

    public TicketBookingSystem() {
        bookingQueue = new CircularQueue(QUEUE_CAPACITY);
        ticketsRemaining = TOTAL_TICKETS;
        nextTicketNumber = 1;
    }

    // Register a customer in the waiting queue.
    public void addCustomer(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Customer name cannot be empty.");
            return;
        }

        if (ticketsRemaining == 0) {
            System.out.println("All tickets have been sold.");
            return;
        }

        Customer customer = new Customer(name.trim());

        if (bookingQueue.enqueue(customer)) {
            System.out.println(
                customer.getName() + " added to the booking queue."
            );
        } else {
            System.out.println(
                "Booking queue is full. Please try again later."
            );
        }
    }

    // Process the next customer and issue a ticket.
    public void processBooking() {
        if (bookingQueue.isEmpty()) {
            System.out.println("No pending bookings to process.");
            return;
        }

        if (ticketsRemaining == 0) {
            System.out.println("No tickets are available.");
            return;
        }

        Customer customer = bookingQueue.dequeue();

        System.out.println("\nBooking successful!");
        System.out.println("Customer: " + customer.getName());
        System.out.println("Ticket number: " + nextTicketNumber);

        nextTicketNumber++;
        ticketsRemaining--;

        System.out.println(
            "Tickets remaining: " + ticketsRemaining
        );
    }

    public void showNextCustomer() {
        Customer customer = bookingQueue.peek();

        if (customer == null) {
            System.out.println("The booking queue is empty.");
        } else {
            System.out.println(
                "Next customer: " + customer.getName()
            );
        }
    }

    public void showStatus() {
        System.out.println("\n--- Booking System Status ---");
        System.out.println(
            "Customers waiting: " + bookingQueue.getSize()
            + "/" + bookingQueue.getCapacity()
        );
        System.out.println(
            "Tickets remaining: " + ticketsRemaining
            + "/" + TOTAL_TICKETS
        );

        System.out.println(
            "Queue empty: " + bookingQueue.isEmpty()
        );
        System.out.println(
            "Queue full: " + bookingQueue.isFull()
        );
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n================================");
            System.out.println("   TICKET BOOKING QUEUE SYSTEM");
            System.out.println("================================");
            System.out.println("1. Add customer to queue");
            System.out.println("2. Process next booking");
            System.out.println("3. Display waiting queue");
            System.out.println("4. View next customer");
            System.out.println("5. View system status");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine().trim();
            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(
                    "Invalid input. Enter a number from 1 to 6."
                );
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter customer name: ");
                    String name = scanner.nextLine();
                    addCustomer(name);
                    break;

                case 2:
                    processBooking();
                    break;

                case 3:
                    bookingQueue.displayQueue();
                    break;

                case 4:
                    showNextCustomer();
                    break;

                case 5:
                    showStatus();
                    break;

                case 6:
                    running = false;
                    System.out.println(
                        "Thank you for using the system."
                    );
                    break;

                default:
                    System.out.println(
                        "Invalid choice. Please select 1 to 6."
                    );
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();
        system.start();
    }
}

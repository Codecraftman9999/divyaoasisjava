import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class res {
    private List<Table> tables;
    private List<Reservation> reservations;

    public res() {
        tables = new ArrayList<>();
        reservations = new ArrayList<>();

        // Initialize tables
        for (int i = 1; i <= 10; i++) {
            tables.add(new Table(i));
        }
    }

    public void displayTables() {
        System.out.println("Available Tables:");
        for (Table table : tables) {
            System.out.println("Table " + table.getNumber() + " - " + (table.isAvailable() ? "Available" : "Reserved"));
        }
    }

    public void makeReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the number of guests: ");
        int numGuests = scanner.nextInt();
        System.out.print("Enter the date and time of the reservation (yyyy-mm-dd hh:mm): ");
        String dateTime = scanner.next();

        Table table = findAvailableTable(numGuests);
        if (table != null) {
            Reservation reservation = new Reservation(name, numGuests, dateTime, table);
            reservations.add(reservation);
            table.setAvailable(false);
            System.out.println("Reservation made successfully!");
        } else {
            System.out.println("Sorry, no tables available for " + numGuests + " guests.");
        }
    }

    public void cancelReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the reservation ID: ");
        int reservationId = scanner.nextInt();

        for (Reservation reservation : reservations) {
            if (reservation.getId() == reservationId) {
                reservation.getTable().setAvailable(true);
                reservations.remove(reservation);
                System.out.println("Reservation cancelled successfully!");
                return;
            }
        }
        System.out.println("Reservation not found.");
    }

    private Table findAvailableTable(int numGuests) {
        for (Table table : tables) {
            if (table.isAvailable() && table.getCapacity() >= numGuests) {
                return table;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        res system = new res();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Online Reservation System");
            System.out.println("1. Display Tables");
            System.out.println("2. Make Reservation");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    system.displayTables();
                    break;
                case 2:
                    system.makeReservation();
                    break;
                case 3:
                    system.cancelReservation();
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class Table {
    private int number;
    private int capacity;
    private boolean available;

    public Table(int number) {
        this.number = number;
        this.capacity = 4; // default capacity
        this.available = true;
    }

    public int getNumber() {
        return number;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Reservation {
    private int id;
    private String name;
    private int numGuests;
    private String dateTime;
    private Table table;

    public Reservation(String name, int numGuests, String dateTime, Table table) {
        this.id = generateId();
        this.name = name;
        this.numGuests = numGuests;
        this.dateTime = dateTime;
        this.table = table;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumGuests() {
        return numGuests;
    }

    public String getDateTime() {
        return dateTime;
    }

    public Table getTable() {
        return table;
    }

    private int generateId() {
        // generate a unique ID for the reservation
        return (int) (Math.random() * 1000);
    }
}
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private String category;
    private boolean available;
    private double price;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.available = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getPrice() {
        return price;
    }
}

class Reservation {
    private int reservationId;
    private String guestName;
    private Room room;
    private String checkInDate;
    private String checkOutDate;

    public Reservation(int reservationId, String guestName, Room room, String checkInDate, String checkOutDate) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public int getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public Room getRoom() {
        return room;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }
}

public class HotelReservationSystem {
    private static List<Room> rooms = new ArrayList<>();
    private static List<Reservation> reservations = new ArrayList<>();
    private static int reservationIdCounter = 1;

    static {
        // Initialize 15 rooms with different categories
        rooms.add(new Room(101, "Single", 50.0));
        rooms.add(new Room(102, "Single", 50.0));
        rooms.add(new Room(103, "Single", 50.0));
        rooms.add(new Room(104, "Single", 50.0));
        rooms.add(new Room(105, "Single", 50.0));

        rooms.add(new Room(201, "Double", 80.0));
        rooms.add(new Room(202, "Double", 80.0));
        rooms.add(new Room(203, "Double", 80.0));
        rooms.add(new Room(204, "Double", 80.0));
        rooms.add(new Room(205, "Double", 80.0));

        rooms.add(new Room(301, "Suite", 150.0));
        rooms.add(new Room(302, "Suite", 150.0));
        rooms.add(new Room(303, "Suite", 150.0));
        rooms.add(new Room(304, "Suite", 150.0));
        rooms.add(new Room(305, "Suite", 150.0));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to Hotel Reservation System");
            System.out.println("1. Search available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    searchAvailableRooms();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewBookingDetails(scanner);
                    break;
                case 4:
                    System.out.println("Thank you for using the system. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void searchAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        System.out.println("Room Number\tCategory\tPrice");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println(room.getRoomNumber() + "\t\t" + room.getCategory() + "\t\t$" + room.getPrice());
            }
        }
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter guest name: ");
        String guestName = scanner.nextLine();

        System.out.print("Enter room number to reserve: ");
        int roomNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Room selectedRoom = null;
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom != null && selectedRoom.isAvailable()) {
            System.out.print("Enter check-in date (MM/DD/YYYY): ");
            String checkInDate = scanner.nextLine();

            System.out.print("Enter check-out date (MM/DD/YYYY): ");
            String checkOutDate = scanner.nextLine();

            Reservation reservation = new Reservation(reservationIdCounter++, guestName, selectedRoom, checkInDate, checkOutDate);
            reservations.add(reservation);
            selectedRoom.setAvailable(false);
            System.out.println("Reservation successful. Your reservation ID is: " + reservation.getReservationId());
        } else {
            System.out.println("Room is not available or does not exist.");
        }
    }

    private static void viewBookingDetails(Scanner scanner) {
        System.out.print("Enter reservation ID: ");
        int reservationId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId) {
                System.out.println("\nBooking Details:");
                System.out.println("Reservation ID: " + reservation.getReservationId());
                System.out.println("Guest Name: " + reservation.getGuestName());
                System.out.println("Room Number: " + reservation.getRoom().getRoomNumber());
                System.out.println("Room Category: " + reservation.getRoom().getCategory());
                System.out.println("Check-in Date: " + reservation.getCheckInDate());
                System.out.println("Check-out Date: " + reservation.getCheckOutDate());
                return;
            }
        }

        System.out.println("Reservation not found.");
    }
}

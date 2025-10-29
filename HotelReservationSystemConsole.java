import java.util.*;

public class HotelReservationSystemConsole {
    static class Room {
        int number;
        boolean booked;
        String guest;

        Room(int number) {
            this.number = number;
            this.booked = false;
            this.guest = "";
        }
    }

    private static ArrayList<Room> rooms = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        initRooms();
        int choice;
        do {
            System.out.println("\n=== HOTEL RESERVATION SYSTEM ===");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. Check Out");
            System.out.println("4. View All Bookings");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = getInt();

            switch (choice) {
                case 1 -> showAvailableRooms();
                case 2 -> bookRoom();
                case 3 -> checkOut();
                case 4 -> showAllBookings();
                case 0 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    static void initRooms() {
        for (int i = 101; i <= 110; i++) {
            rooms.add(new Room(i));
        }
    }

    static void showAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        boolean found = false;
        for (Room r : rooms) {
            if (!r.booked) {
                System.out.println("Room " + r.number);
                found = true;
            }
        }
        if (!found) System.out.println("No rooms available.");
    }

    static void bookRoom() {
        System.out.print("Enter room number: ");
        int num = getInt();
        Room room = findRoom(num);
        if (room == null) {
            System.out.println("Invalid room number.");
            return;
        }
        if (room.booked) {
            System.out.println("Room already booked.");
            return;
        }
        System.out.print("Enter guest name: ");
        String guest = sc.nextLine();
        room.booked = true;
        room.guest = guest;
        System.out.println("Room " + num + " booked for " + guest);
    }

    static void checkOut() {
        System.out.print("Enter room number to check out: ");
        int num = getInt();
        Room room = findRoom(num);
        if (room == null || !room.booked) {
            System.out.println("Room not found or not booked.");
            return;
        }
        System.out.println("Guest " + room.guest + " has checked out.");
        room.booked = false;
        room.guest = "";
    }

    static void showAllBookings() {
        System.out.println("\nCurrent Bookings:");
        boolean found = false;
        for (Room r : rooms) {
            if (r.booked) {
                System.out.println("Room " + r.number + " - Guest: " + r.guest);
                found = true;
            }
        }
        if (!found) System.out.println("No bookings.");
    }

    static Room findRoom(int num) {
        for (Room r : rooms)
            if (r.number == num)
                return r;
        return null;
    }

    static int getInt() {
        while (true) try { return Integer.parseInt(sc.nextLine()); }
        catch (Exception e) { System.out.print("Enter number: "); }
    }
}
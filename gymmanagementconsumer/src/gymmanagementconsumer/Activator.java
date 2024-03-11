package gymmanagementconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import gymmanagementproducer.GymService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Activator implements BundleActivator {

    private ServiceReference<GymService> gymServiceReference;

    @Override
    public void start(BundleContext context) throws Exception {
        gymServiceReference = context.getServiceReference(GymService.class);
        GymService gymService = context.getService(gymServiceReference);

        if (gymService != null) {
            System.out.println("Gym Management Consumer Bundle started.");

            Scanner scanner = new Scanner(System.in);
            int choice = 0;

            while (choice != 4) {
                System.out.println("\nChoose an option:");
                System.out.println("1. Book a slot");
                System.out.println("2. Cancel booking");
                System.out.println("3. Check slot availability");
                System.out.println("4. Exit");

                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        bookSlot(gymService, scanner);
                        break;
                    case 2:
                        cancelBooking(gymService, scanner);
                        break;
                    case 3:
                        checkAvailability(gymService, scanner);
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } else {
            System.out.println("Gym service not available.");
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        context.ungetService(gymServiceReference);
        System.out.println("Gym Management Consumer Bundle stopped.");
    }

    private void bookSlot(GymService gymService, Scanner scanner) {
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine(); // Consume the newline character
        customerName = scanner.nextLine(); // Use nextLine() to capture the entire line
        System.out.print("Enter date (YYYY-MM-DD): ");
        String dateString = scanner.next();

        // Validate date format
        if (!dateString.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }

        System.out.print("Enter slot (morning/afternoon/evening): ");
        String slot = scanner.next();

        // Convert dateString to Date object
        Date date;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }

        if (gymService.bookSlot(customerName, date, slot)) {
            System.out.println("Slot booked successfully.");
        } else {
            System.out.println("Failed to book slot.");
        }
    }



    private void cancelBooking(GymService gymService, Scanner scanner) {
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine(); // Consume the newline character
        customerName = scanner.nextLine(); // Use nextLine() to capture the entire line
        System.out.print("Enter date (YYYY-MM-DD): ");
        String dateString = scanner.next();

        // Validate date format
        if (!dateString.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }

        System.out.print("Enter slot (morning/afternoon/evening): ");
        String slot = scanner.next();

        // Convert dateString to Date object
        Date date;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }

        if (gymService.cancelBooking(customerName, date, slot)) {
            System.out.println("Booking canceled successfully.");
        } else {
            System.out.println("Failed to cancel booking.");
        }
    }


    private void checkAvailability(GymService gymService, Scanner scanner) {
        System.out.print("Enter date (YYYY-MM-DD): ");
        String dateString = scanner.next();

        // Validate date format
        if (!dateString.matches("\\d{4}-\\d{2}-\\d{2}")) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }

        System.out.print("Enter slot (morning/afternoon/evening): ");
        String slot = scanner.next();

        // Convert dateString to Date object
        Date date;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return;
        }

        if (gymService.checkAvailability(date, slot)) {
            System.out.println("Slot is available.");
        } else {
            System.out.println("Slot is not available.");
        }
    }


}

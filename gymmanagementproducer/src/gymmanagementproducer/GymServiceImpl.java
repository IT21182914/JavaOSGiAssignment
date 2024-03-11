package gymmanagementproducer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GymServiceImpl implements GymService {

    private Map<Date, Map<String, String>> bookings;

    public GymServiceImpl() {
        this.bookings = new HashMap<>();
    }

    @Override
    public boolean bookSlot(String customerName, Date date, String slot) {
        if (!bookings.containsKey(date)) {
            bookings.put(date, new HashMap<>());
        }
        Map<String, String> dayBookings = bookings.get(date);
        if (!dayBookings.containsKey(slot)) {
            dayBookings.put(slot, customerName);
            System.out.println("Slot booked for " + customerName + " on " + date + " at " + slot);
            return true;
        } else {
            System.out.println("Slot already booked for " + slot + " on " + date);
            return false;
        }
    }

    @Override
    public boolean cancelBooking(String customerName, Date date, String slot) {
        if (bookings.containsKey(date) && bookings.get(date).containsKey(slot)) {
            String bookedCustomer = bookings.get(date).get(slot);
            if (bookedCustomer.equals(customerName)) {
                bookings.get(date).remove(slot);
                System.out.println("Booking canceled for " + customerName + " on " + date + " at " + slot);
                return true;
            } else {
                System.out.println("You cannot cancel someone else's booking.");
                return false;
            }
        } else {
            System.out.println("No booking found for " + customerName + " on " + date + " at " + slot);
            return false;
        }
    }

    @Override
    public boolean checkAvailability(Date date, String slot) {
        if (bookings.containsKey(date) && bookings.get(date).containsKey(slot)) {
            System.out.println("Slot not available on " + date + " at " + slot);
            return false;
        } else {
            System.out.println("Slot available on " + date + " at " + slot);
            return true;
        }
    }

}

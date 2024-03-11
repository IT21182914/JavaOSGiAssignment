package gymmanagementproducer;



import java.util.Date;

public interface GymService {
    boolean bookSlot(String customerName, Date date, String slot);
    boolean cancelBooking(String customerName, Date date, String slot);
    boolean checkAvailability(Date date, String slot);
}

